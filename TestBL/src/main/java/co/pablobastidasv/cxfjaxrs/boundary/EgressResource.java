package co.pablobastidasv.cxfjaxrs.boundary;

import co.pablobastidasv.cxfjaxrs.model.Egress;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author pbastidas
 */
@Path( "/" )
public class EgressResource {

    @Inject
    private Manager manager;

    @GET
    @Produces("application/json")
    public List<Egress> findAll(){
        return manager.findAll();
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") String id){
        return Response.ok(new Egress("hola", BigDecimal.TEN)).build();
    }

    @POST
    public Response create(Egress egress){
        return Response
                .ok(new Egress("hola", BigDecimal.TEN))
                .build();
    }
}
