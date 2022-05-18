package nl.hu.josvanreenen.sandbox.webservices;

import nl.hu.josvanreenen.sandbox.model.Company;
import nl.hu.josvanreenen.sandbox.model.Customer;
import nl.hu.josvanreenen.sandbox.webservices.dto.CustomerRequest;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/customers")
public class CustomerResource {
    @GET
    @Path("{customerid}")
    @Produces("application/json")
    public Response getCustomer(@PathParam("customerid") int id) {
        Customer customer = Company.getCompany().getCustomerById(id);
        return Response.ok(customer).build();
    }


    @POST
    @Path("jackson")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCustomer(CustomerRequest customerRequest) {
        if (!Company.getCompany().addCustomer(customerRequest.name)) {
            Map<String, String> messages = new HashMap<>();
            messages.put("error", "Klant bestond al");
            return Response.status(Response.Status.CONFLICT).entity(messages).build();
        }
        return Response.ok(Company.getCompany().getCustomerByName(customerRequest.name)).build();

    }


    @POST
    @Path("nojackson")
    @Produces(MediaType.APPLICATION_JSON)
    public String createCustomer(@FormParam("name") String name) {
            JsonObjectBuilder job = Json.createObjectBuilder();
        if (Company.getCompany().addCustomer(name)) {
            Customer added = Company.getCompany().getCustomerByName(name);
            job.add("name", added.getName()); //multiple lines if multiple fields
            return job.build().toString();
        }
        job.add("error", "Klant bestond al");
        return job.build().toString();
    }

}
