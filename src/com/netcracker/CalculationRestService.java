package com.netcracker;


import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/calc")
public class CalculationRestService {
    @GET
    @Path("add/{a}/{b}")
    @Produces(MediaType.TEXT_HTML)
    public String addBrowser(@PathParam("a") double a, @PathParam("b") double b) {
        return assembleHtmlAnswer(a + b);
    }

    @POST
    @Path("add/{a}/{b}")
    @Produces(MediaType.APPLICATION_JSON)
    public String add(@PathParam("a") double a, @PathParam("b") double b) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("answer", a + b);
        return jsonObject.toString();
    }

    @GET
    @Path("subtract/{a}/{b}")
    @Produces(MediaType.TEXT_PLAIN)
    public String subtractBrowser(@PathParam("a") double a, @PathParam("b") double b) {
        return Double.toString(a - b);
    }

    @POST
    @Path("subtract/{a}/{b}")
    @Produces(MediaType.APPLICATION_XML)
    public String subtract(@PathParam("a") double a, @PathParam("b") double b) {
        return "<answer>"+ (a - b) + "</answer>";
    }

    @GET
    @Path("multiply/{a}/{b}")
    @Produces(MediaType.TEXT_HTML)
    public String multiplyBrowser(@PathParam("a") double a, @PathParam("b") double b) {
        return assembleHtmlAnswer(a * b);
    }

    @POST
    @Path("multiply/{a}/{b}")
    public double multiply(@PathParam("a") double a, @PathParam("b") double b) {
        return a * b;
    }

    @GET
    @Path("divide/{a}/{b}")
    @Produces(MediaType.TEXT_HTML)
    public String divideBrowser(@PathParam("a") double a, @PathParam("b") double b) {
        return assembleHtmlAnswer(a / b);
    }

    @POST
    @Path("divide/{a}/{b}")
    public double divide(@PathParam("a") double a, @PathParam("b") double b) {
        return a / b;
    }

    private String assembleHtmlAnswer(double ans) {
        return "<html>" +
                "<title>" + "Calculating..." + "</title>" +
                "<body>" + ans + "</body>" +
                "</html>";
    }
}
