package co.pablobastidasv.cxfjaxrs.boundary;

import co.pablobastidasv.cxfjaxrs.entities.Egress;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

/**
 * Manager for egress info in the database.
 *
 * @author pbastidas
 */
public class EgressManager extends BasicDAO<Egress, ObjectId> {
    public EgressManager(Datastore ds) {
        super(ds);
    }
}
