package backend;


import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;

public class PostAgendar {
	
    public static void agendarBack(String acces_token, String idVisita, String fechainicio, String fechafin){
    	CloseableHttpClient httpClient = HttpClients.createDefault();
        //CloseableHttpResponse response = null;                
        
        String url = "https://dev-apigateway.artprovincia.ar/hys/preventores/v1/agendar/"+idVisita;
        HttpPost httpPost = new HttpPost(url);
        
        String json = "{"+"\"fechainicio\":"+"\""+fechainicio+"\""+",\"fechafin\":"+"\""+fechafin+"\""+"}";
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("Authorization", "Bearer " + acces_token);
   
        
      try {
    	     CloseableHttpResponse response = httpClient.execute(httpPost);
    	     httpClient.close();

        	        	

        } catch(Exception e) {
        	System.out.println("-> " + e.toString());
        }
        
    }
    
}


