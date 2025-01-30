package backend;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;
import java.io.IOException;

public class PostAgendar {

	/**Funcion para Agendar una visita desde el backend
	 * Recibe el encryptedData para el servicio, el id de visita, la fecha de inicio y la fecha de fin de la visita
	 * Encrypted data son las credenciales encriptadas
	 * Formato fecha DD/MM/AAAA HH:MM:SS
	**/
    public static void agendarBack(String encryptedData, String idVisita, String fechainicio, String fechafin) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String url = "https://dev-apigateway.artprovincia.ar/hys/preventores/v1/agendar/" + idVisita;
        HttpPost httpPost = new HttpPost(url);

        String accessToken = PostLogin.loginBack(encryptedData);
        
        String json = "{" + "\"fechainicio\":" + "\"" + fechainicio + "\"" + ",\"fechafin\":" + "\"" + fechafin + "\"" + "}";
        StringEntity entity = new StringEntity(json, "UTF-8");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("Authorization", "Bearer " + accessToken);

        try {
            CloseableHttpResponse response = httpClient.execute(httpPost);
            EntityUtils.toString(response.getEntity());
//            System.out.println(responseBody);
            response.close();
        } catch (Exception e) {
            System.out.println("-> " + e.toString());
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                System.out.println("-> " + e.toString());
            }
        }
    }
}