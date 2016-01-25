package co.pablobastidasv.cxfjaxrs.boundary;

import co.pablobastidasv.cxfjaxrs.entities.Egress;
import org.mongodb.morphia.Key;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Resource to manage the request sent to the root egress path
 *
 * @author pbastidas
 */
@Path( "/" )
@Produces(MediaType.APPLICATION_JSON)
public class EgressesResource {

    @Inject
    private EgressManager egressManager;

    @GET
    public List<Egress> findAll(){
        return egressManager.find().asList();
    }

    @Path("{id}")
    public EgressResource find(@PathParam("id") String id){
        return new EgressResource(id, egressManager);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Context UriInfo uriInfo, Egress egress){

        final Key<Egress> egressKey = egressManager.save(egress);

        return Response
                .ok()
                .status(Response.Status.CREATED)
                .header("Location", uriInfo.getAbsolutePath() + "/" + egressKey.getId())
                .build();
    }
}
