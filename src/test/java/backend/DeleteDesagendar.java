package backend;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

public class DeleteDesagendar {

    public static void desagendarBack(String accessToken, String idVisita) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        String url = "https://dev-apigateway.artprovincia.ar/hys/preventores/v1/agendar/" + idVisita;
        HttpDelete httpDelete = new HttpDelete(url);
        httpDelete.setHeader("Accept", "application/json");
        httpDelete.setHeader("Authorization", "Bearer " + accessToken);

        try {
            response = httpClient.execute(httpDelete);
            String responseBody = EntityUtils.toString(response.getEntity());
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