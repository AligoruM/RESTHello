package com.netcracker;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class CalculationRestClient {

    public static void main(String[] args) {
        //Нифига не понял что тут надо было делать. Надеюсь это то, что нужно)
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/rest").path("calc").path("multiply").path("2").path("2");
        Invocation.Builder invocationBuilder = target.request();
        Response response = invocationBuilder.post(null);
        Response response2 = invocationBuilder.get();
        System.out.println(response.readEntity(String.class));
        System.out.println(response2.readEntity(String.class));
    }
}

