package backend;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

public class DeleteDesagendar {
	
	/**Funcion para desagendar una visita desde el backend
	 * Recibe el encrypted para el servicio y el id de visita
	 * Encrypted data son las credenciales encriptadas
	**/
    public static void desagendarBack(String encryptedData, String idVisita) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        String accessToken = PostLogin.loginBack(encryptedData);
                
        String url = "https://dev-apigateway.artprovincia.ar/hys/preventores/v1/agendar/" + idVisita;
        HttpDelete httpDelete = new HttpDelete(url);
        httpDelete.setHeader("Accept", "application/json");
        httpDelete.setHeader("Authorization", "Bearer " + accessToken);

        try {
            response = httpClient.execute(httpDelete);
            EntityUtils.toString(response.getEntity());
            //System.out.println(responseBody);
        } catch (Exception e) {
            System.out.println("-> " + e.toString());
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                System.out.println("-> " + e.toString());
            }
        }
    }
}