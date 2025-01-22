package backend;

import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

public class DeleteDesagendar {
	
    public static void desagendarBack(String acces_token, String idVisita){
    	CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;                
        
        
        String url = "https://dev-apigateway.artprovincia.ar/hys/preventores/v1/agendar/"+idVisita;
        HttpDelete httpDelete = new HttpDelete(url);
        httpDelete.setHeader("Accept", "application/json");
        httpDelete.setHeader("Content-type", "application/json");
        httpDelete.setHeader("Authorization", "Bearer " + acces_token);
        httpClient = HttpClients.createDefault();
        
        
        try {


         	response = httpClient.execute(httpDelete);

        	        	

        } catch(Exception e) {
        	System.out.println("-> " + e.toString());
        }
        
    }
    
}

