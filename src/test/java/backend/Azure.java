package backend;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;


import utils.Configuracion;

public class Azure {

    public static Integer PointId(Integer testPlan, Integer testSuite, Integer testCase) throws IOException {
        final String token = Configuracion.getPropiedad("General", "TOKEN");
        final String proyecto = Configuracion.getPropiedad("General", "PROYECTO");

        String url = "https://dev.azure.com/Provincia-ART/" + proyecto + "/_apis/testplan/Plans/" + testPlan + "/Suites/" + testSuite + "/TestPoint?testcaseid=" + testCase + "&api-version=7.1";

        // Crear un cliente HTTP
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            // Crear una solicitud GET
            HttpGet request = new HttpGet(url);
            // Añadir el token de seguridad en el encabezado
            request.addHeader("Authorization", "Bearer " + token);

            // Ejecutar la solicitud
            CloseableHttpResponse response = httpClient.execute(request);

            // Obtener la respuesta como una cadena JSON
            String jsonResponse = EntityUtils.toString(response.getEntity());

            JSONObject jsonObject = new JSONObject(jsonResponse);

            JSONArray valueArray = jsonObject.getJSONArray("value");

            JSONObject firstObject = valueArray.getJSONObject(0);

            // Obtener el valor del campo "id"
            Integer id = firstObject.getInt("id");

            return id;

        } finally {
            // Cerrar el cliente HTTP
            httpClient.close();
        }
    }

    public static void RunTest(int testPlan, int testSuite, int testCase) {
        final String user = Configuracion.getPropiedad("General", "USER");
        final String user_id = Configuracion.getPropiedad("General", "USER_ID");
        final String token = Configuracion.getPropiedad("General", "TOKEN");
        final String proyecto = Configuracion.getPropiedad("General", "PROYECTO");

        Integer testPoint = 0;

        try {
            testPoint = PointId(testPlan, testSuite, testCase);
        } catch (Exception e) {
            System.out.println("-> " + e.toString());
        }

        CloseableHttpClient httpClient = HttpClients.createDefault();

        String url = "https://dev.azure.com/Provincia-ART/" + proyecto + "/_apis/testplan/Plans/" + testPlan + "/Suites/" + testSuite + "/TestPoint?includePointDetails=true&api-version=7.1&returnIdentityRef=true";
        HttpPatch httpPatch = new HttpPatch(url);

        String json = "[\r\n" +
                "  {\r\n" +
                "    \"id\": " + testPoint + ",\r\n" +
                "    \"assignedTo\": {\r\n" +
                "        \"id\": \"" + user_id + "\",\r\n" +
                "        \"displayName\": \"" + user + "\"\r\n" +
                "    },\r\n" +
                "    \"results\":{\r\n" +
                "        \"outcome\": 2\r\n" +
                "    }\r\n" +
                "  }\r\n" +
                "]\r\n";


        try {
        	
            StringEntity entity = new StringEntity(json);
            httpPatch.setEntity(entity);
            httpPatch.setHeader("Accept", "application/json");
            httpPatch.setHeader("Content-type", "application/json");
            httpPatch.setHeader("Authorization", "Bearer " + token);

        	
            httpClient.execute(httpPatch);
            httpClient.close();
            // System.out.println(response);
        } catch (Exception e) {
            System.out.println("-> " + e.toString());
        }
    }
}