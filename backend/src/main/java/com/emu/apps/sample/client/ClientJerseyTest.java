package com.emu.apps.sample.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
/**
 * Created by eric on 23/05/2017.
 */
public class ClientJerseyTest {

    public static void main(String[] args) {
        new ClientJerseyTest().test();
    }

    public ClientJerseyTest() { }

    public void test() {
        try {
            Client client = Client.create();
            WebResource webResource = client
                    .resource("https://brix/test");
            ClientResponse response = webResource.accept("application/json")
                    .get(ClientResponse.class);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }
            String output = response.getEntity(String.class);
            System.out.println("Output from Server .... \n");
            System.out.println(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

