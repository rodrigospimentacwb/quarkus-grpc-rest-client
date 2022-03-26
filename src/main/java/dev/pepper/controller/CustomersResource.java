package dev.pepper.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import dev.pepper.integration.CustomerApiInterface;
import dev.pepper.model.Customer;
import dev.pepper.service.CustomersService;

@Path("client/customers")
public class CustomersResource {

    @Inject
    CustomersService service;

    @Inject
    @RestClient
    CustomerApiInterface customerAPIInterface;

    @GET
    @Path("rest/{multiplyer}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> restListAll(@PathParam("multiplyer") int multiplyer) {
        return customerAPIInterface.getAll(multiplyer);
    }

    @GET
    @Path("rest/find{id}/name")
    @Produces(MediaType.TEXT_PLAIN)
    public String restListAll(@PathParam("id") Long id) {
        return customerAPIInterface.findNameByid(id);
    }

    @GET
    @Path("grpc/{multiplyer}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> gRPCListAll(@PathParam("multiplyer") int multiplyer) {
        return service.listAllByGRPC(multiplyer);
    }

    @GET
    @Path("grpc/{id}/name")
    @Produces(MediaType.TEXT_PLAIN)
    public String gRPCListOneName(@PathParam("id") Long id) {
        return service.findNameById(id);
    }
}