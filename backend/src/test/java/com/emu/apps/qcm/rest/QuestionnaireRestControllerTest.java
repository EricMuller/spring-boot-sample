package com.emu.apps.qcm.rest;

import com.emu.apps.qcm.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.test.RestTemplateHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestOperations;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "test")
public class QuestionnaireRestControllerTest implements RestTemplateHolder {

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
    public void shouldUploadFile() throws Exception {
        final HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer FOO");
        headers.setContentType(MediaType.APPLICATION_JSON);

        ClassPathResource resource = new ClassPathResource("javaquestions2017.json", getClass());
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("file", resource);

        final ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/questionnaires/upload"), HttpMethod.POST,
                new HttpEntity<>(map), String.class);

        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);

        //assertThat(response.getHeaders().getLocation().toString())
        //        .startsWith("http://localhost:" + this.port + "/");

        //then(storageService).should().store(any(MultipartFile.class));
    }








}

