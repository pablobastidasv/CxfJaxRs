package co.pablobastidasv.cxfjaxrs.structure.converters;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.mongodb.morphia.converters.SimpleValueConverter;
import org.mongodb.morphia.converters.TypeConverter;
import org.mongodb.morphia.mapping.MappedField;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Converter to parse a BigDecimal object in a mongo object,
 * the mongo object has the next attributs:
 *  unscaled:int64 (To store the full number without decimal)
 *  scale:int32 (To store the numbers of decimal)
 *
 * @author pbastidas
 */
public class BigDecimalConverter extends TypeConverter implements SimpleValueConverter {

    @Override
    protected boolean isSupported(Class<?> c, MappedField optionalExtraInfo) {
        return BigDecimal.class.isAssignableFrom(c);
    }

    @Override
    public Object encode(Object value, MappedField optionalExtraInfo) {
        if (value == null) {
            return null;
        }
        BigDecimal bigDecimalValue = (BigDecimal) value;

        if (bigDecimalValue.scale() > 18) {
            bigDecimalValue = bigDecimalValue.setScale(18, BigDecimal.ROUND_DOWN);
        }

        DBObject dbo = new BasicDBObject();

        dbo.put("unscaled", bigDecimalValue.unscaledValue().longValue());
        dbo.put("scale", bigDecimalValue.scale());

        return dbo;
    }

    @Override
    public Object decode(Class<?> targetClass, Object fromDBObject, MappedField optionalExtraInfo) {
        DBObject dbo = (DBObject) fromDBObject;
        if (dbo == null) {
            return null;
        }

        BigDecimal bigDecimal = null;

        Long unscaled = (Long) dbo.get("unscaled");
        Integer scale = (Integer) dbo.get("scale");

        if (unscaled != null && scale != null) {
            bigDecimal = new BigDecimal(new BigInteger(unscaled.toString()), scale);
        }

        return bigDecimal;
    }
}
