package backend;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONObject;

public class PostLogin {
	
    public static String loginBack(String encryptedData){
    	CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String accesToken;
        
        
        //setoa el body para el request
        StringEntity customerEntity = new StringEntity(encryptedData);
        

        String url = "https://dev-apigateway.artprovincia.ar/hys/preventores/auth/v1/login";
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("consumidor", "Preventores");
        httpPost.setEntity(customerEntity);
        httpClient = HttpClients.createDefault();
        
        
        
        
        try {

        	//ejecuto el post
         	response = httpClient.execute(httpPost);
        	
        	String responseBody = EntityUtils.toString(response.getEntity());
        	
        	JSONObject objetoJson = new JSONObject(responseBody);

            //guarda el token
        	accesToken = objetoJson.getJSONObject("data").getJSONObject("userData").getJSONObject("token").getString("access_token");
        	
        	

        } catch(Exception e) {
        	System.out.println("-> " + e.toString());
        	return "";
        }
        
        
        
        return accesToken;
    }
    
}

