package backend;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;
import java.io.IOException;

public class PostLogin {

	/**Funcion protected para obtener el token backend que se usa para las funciones PostAgendar y DeleteDesagendar
	 * Recibe el encrypted data que son las credenciales encriptadas
	**/
    protected static String loginBack(String encryptedData) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String accessToken = "";

        // Setear el body para el request
        StringEntity customerEntity = new StringEntity(encryptedData, "UTF-8");

        String url = "https://dev-apigateway.artprovincia.ar/hys/preventores/auth/v1/login";
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("consumidor", "Preventores");
        httpPost.setEntity(customerEntity);
        httpClient = HttpClients.createDefault();

        try {
            // Ejecutar el post
            response = httpClient.execute(httpPost);

            String responseBody = EntityUtils.toString(response.getEntity());

            JSONObject objetoJson = new JSONObject(responseBody);

            // Guardar el token
            accessToken = objetoJson.getJSONObject("data").getJSONObject("userData").getJSONObject("token").getString("access_token");

        } catch (Exception e) {
            System.out.println("-> " + e.toString());
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                System.out.println("-> " + e.toString());
            }
        }

        return accessToken;
    }
}