package co.pablobastidasv.cxfjaxrs.boundary;

import co.pablobastidasv.cxfjaxrs.entities.Egress;
import com.mongodb.WriteResult;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.math.BigDecimal;

/**
 * Resource to manage the request sent to the root egress path
 *
 * @author pbastidas
 */
public class EgressResource {

    private String id;
    private EgressManager egressesDao;

    public EgressResource(String id, EgressManager egressManager) {
        this.id = id;
        this.egressesDao = egressManager;
    }

    @GET
    public Response findById() {
        final Egress egress = egressesDao.get(new ObjectId(id));

        if(egress == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(egress).build();
    }

    @POST
    @Path("/attachment")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response create(
            @Multipart(value = "attachment") Attachment attachment,
            @Multipart(value = "attachment") byte[] bytes
    ) {
        return Response
                .ok(new Egress("hola", BigDecimal.TEN))
                .build();
    }

    @PUT
    public Response update(@Context UriInfo uriInfo, Egress egress){

        final Query<Egress> query = egressesDao.getDatastore().createQuery(Egress.class)
                .field("id").equal(new ObjectId(this.id));
        final UpdateOperations<Egress> updateOperation = egressesDao.getDatastore().createUpdateOperations(Egress.class)
                .set("name", egress.getName())
                .set("totalValue", egress.getTotalValue())
                .set("egressDate", egress.getEgressDate())
                .set("paymentMethod", egress.getPaymentMethod())
                ;

        final UpdateResults updateResults = egressesDao.update(query, updateOperation);

        if(updateResults.getUpdatedExisting()){
            return Response
                    .ok()
                    .status(Response.Status.NO_CONTENT)
                    .header("Location", uriInfo.getAbsolutePath())
                    .build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    public Response delete() {
        final WriteResult writeResult = egressesDao.deleteById(new ObjectId(id));
        if (writeResult.getN() == 1) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
