package co.pablobastidasv.cxfjaxrs.entities;

import co.pablobastidasv.cxfjaxrs.structure.serializers.CustomDateSerializer;
import co.pablobastidasv.cxfjaxrs.structure.serializers.CustomObjectIdSerializer;
import co.pablobastidasv.cxfjaxrs.contants.PaymentMethod;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * DTO of the egress
 *
 * @author pbastidas
 */
@Data
@Entity(value = "egress", noClassnameStored = true)
public class Egress {

    @Id
    @JsonSerialize(using = CustomObjectIdSerializer.class)
    private ObjectId id;
    private String name;
    private BigDecimal totalValue;
    @JsonSerialize(using=CustomDateSerializer.class)
    private Date egressDate;
    private PaymentMethod paymentMethod;
    @Embedded
    private List<EgressItem> egressItems;

    public Egress() {
    }

    public Egress(String name, BigDecimal totalValue) {
        this.name = name;
        this.totalValue = totalValue;
        this.egressDate = new Date();
    }
}
