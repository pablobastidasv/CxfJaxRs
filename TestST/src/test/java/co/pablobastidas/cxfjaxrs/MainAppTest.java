package co.pablobastidas.cxfjaxrs;

import com.airhacks.rulz.jaxrsclient.JAXRSClientProvider;
import org.junit.Rule;
import org.junit.Test;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author pbastidas
 */
public class MainAppTest {

    final String END_POINT = "http://docker.me:8080/service/api/egresos";

    @Rule
    public JAXRSClientProvider provider = JAXRSClientProvider.buildWithURI(END_POINT);

    @Test
    public void egressCreation(){
        final Response response = this.provider.target().request(MediaType.APPLICATION_JSON).get();

        assertThat(response.getStatus()).isEqualTo(200);

        final JsonArray allEgress = response.readEntity(JsonArray.class);
        System.out.println("payload "+ allEgress);

        assertThat(allEgress.isEmpty()).isFalse();

        final JsonObject jsonObject = allEgress.getJsonObject(0);
        assertThat(jsonObject.getString("name")).isEqualTo("Laptop");

    }

}
