package com.emu.apps.qcm.rest;

import com.emu.apps.qcm.Application;
import com.emu.apps.qcm.services.dtos.QuestionDto;
import com.emu.apps.qcm.services.dtos.ResponseDto;
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

    @Test
    public void saveQuestionTest() throws Exception {
        final HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer FOO");
        headers.setContentType(MediaType.APPLICATION_JSON);

        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestion("c'est quoi la meilleure maniere ?");

        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponse("google");
        responseDto.setTrue(true);

        questionDto.setResponses(Lists.newArrayList(responseDto));

        final ResponseEntity<QuestionDto> response = restTemplate.exchange(createURLWithPort("/api/questions")
                , HttpMethod.POST,
                new HttpEntity<>(questionDto), QuestionDto.class);


        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);

        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getResponses()).isNotNull().isNotEmpty();


    }

}