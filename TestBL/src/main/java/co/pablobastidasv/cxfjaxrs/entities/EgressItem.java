package co.pablobastidasv.cxfjaxrs.entities;

import lombok.Data;
import org.mongodb.morphia.annotations.Embedded;

import java.math.BigDecimal;

/**
 * Detail for the egress when this apply
 *
 * @author pbastidas
 */
@Data
@Embedded
public class EgressItem {

    public String description;
    public BigDecimal value;

}
