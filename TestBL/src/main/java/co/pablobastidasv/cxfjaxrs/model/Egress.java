package co.pablobastidasv.cxfjaxrs.model;

import co.pablobastidasv.cxfjaxrs.contants.PaymentType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author pbastidas
 */
@Data
public class Egress {
    private String name;
    private BigDecimal totalValue;
    private Date egressDate;
    private List<EgressItem> items;
    private PaymentType paymentType;

    public Egress() {
    }

    public Egress(String name, BigDecimal totalValue) {
        this.name = name;
        this.totalValue = totalValue;
        this.egressDate = new Date();
    }
}
