package dev.pepper.integration;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import dev.pepper.model.Customer;

@Path("/server/customers")
@RegisterRestClient(configKey="customer-api")
public interface CustomerApiInterface {

    @GET
    @Path("/rest/{multiplyer}")
    List<Customer> getAll(@PathParam Integer multiplyer);

    @GET
    @Path("/rest/{id}/name")
    String findNameByid(@PathParam Long id);
}
