package backend;

import org.apache.hc.client5.http.classic.methods.HttpPatch;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;

import utils.Configuracion;

public class Azure {
	
	public static void RunTest (int testPlan,  int testSuite, int testPoint) {
		
		final String user=Configuracion.getPropiedad("General","USER");
		final String user_id=Configuracion.getPropiedad("General","USER_ID");
		final String token=Configuracion.getPropiedad("General","TOKEN");
		final String proyecto=Configuracion.getPropiedad("General","PROYECTO");
		
    	CloseableHttpClient httpClient = HttpClients.createDefault();
        //CloseableHttpResponse response = null;                
        
    	
        String url = "https://dev.azure.com/Provincia-ART/"+proyecto+"/_apis/testplan/Plans/"+testPlan+"/Suites/"+testSuite+"/TestPoint?includePointDetails=true&api-version=7.1&returnIdentityRef=true";
        HttpPatch httpPatch = new HttpPatch(url);
        
        String json = "[\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": "+testPoint+",\r\n"
        		+ "    \"assignedTo\": {\r\n"
        		+ "        \"id\": \""+user_id+"\",\r\n"
        		+ "        \"displayName\": \""+user+"\"\r\n"
        		+ "    },\r\n"
        		+ "    \"results\":{\r\n"
        		+ "        \"outcome\": 2\r\n"
        		+ "    }\r\n"
        		+ "  }\r\n"
        		+ "]\r\n"
        		+ "";
        StringEntity entity = new StringEntity(json);
        httpPatch.setEntity(entity);
        httpPatch.setHeader("Accept", "application/json");
        httpPatch.setHeader("Content-type", "application/json");
        httpPatch.setHeader("Authorization", "Bearer " + token);
        
        try {
   	     CloseableHttpResponse response = httpClient.execute(httpPatch);
   	     httpClient.close();
   	     //System.out.println(response);

       	        	

       } catch(Exception e) {
       	System.out.println("-> " + e.toString());
       }
		
	}

}
