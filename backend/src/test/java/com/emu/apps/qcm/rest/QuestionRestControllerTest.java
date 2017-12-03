package com.emu.apps.qcm.rest;

import com.emu.apps.qcm.Application;
import com.emu.apps.qcm.services.dtos.QuestionDto;
import com.emu.apps.qcm.services.dtos.ResponseDto;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.test.RestTemplateHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "test")
public class QuestionRestControllerTest implements RestTemplateHolder {

    @LocalServerPort
    private int port;
    @Value("http://localhost:${local.server.port}")
    private String host;

    private RestOperations restTemplate = new TestRestTemplate().getRestTemplate();

    public String getHost() {
        return host;
    }

    @Override
    public RestOperations getRestTemplate() {
        return restTemplate;
    }

    @Override
    public void setRestTemplate(RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    private static final String QUESTION = "c'est quoi la meilleure maniere ?";

    private static final String RESPONSE = "google";
    private static final String RESPONSE2 = "cherche !";

    @Test
    public void saveQuestionTest() throws Exception {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer FOO");
        headers.setContentType(MediaType.APPLICATION_JSON);

        // POST
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestion(QUESTION);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponse(RESPONSE);
        responseDto.setTrue(true);

        questionDto.setResponses(Lists.newArrayList(responseDto));

        final ResponseEntity<QuestionDto> response = restTemplate.exchange(createURLWithPort("/api/v1/questions/")
                , HttpMethod.POST,
                new HttpEntity<>(questionDto), QuestionDto.class);

        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getResponses()).isNotNull().isNotEmpty();

        // GET
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(createURLWithPort("/api/v1/questions/{id}"));
        HttpEntity<?> entity = new HttpEntity<>(headers);
        // restTemplate.getForEntity(builder.build().encode().toUri(), QuestionDto.class);
        final ResponseEntity<QuestionDto> responseGet = restTemplate.exchange(
                builder.build().expand(response.getBody().getId()).encode().toUri(),
                HttpMethod.GET,
                entity,
                QuestionDto.class);

        QuestionDto responseDtoGet = response.getBody();
        assertThat(responseGet.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(responseDtoGet.getResponses()).isNotNull().isNotEmpty();
        ResponseDto first = Iterables.getFirst(responseDtoGet.getResponses(), null);
        assertThat(first).isNotNull();
        assertThat(first.getResponse()).isNotNull().isEqualTo(RESPONSE);


        // PUT
        first.setResponse(RESPONSE2);

        final ResponseEntity<QuestionDto> responsePut = restTemplate.exchange(createURLWithPort("/api/v1/questions/")
                , HttpMethod.PUT,
                new HttpEntity<>(responseDtoGet), QuestionDto.class);

        assertThat(responsePut.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);

        assertThat(responsePut.getBody().getId()).isNotNull();
        assertThat(responsePut.getBody().getResponses()).isNotNull().isNotEmpty();
        first = Iterables.getFirst(response.getBody().getResponses(), null);
        assertThat(first).isNotNull();
        assertThat(first.getResponse()).isNotNull().isEqualTo(RESPONSE2);

    }

}