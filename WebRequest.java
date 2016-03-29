package com.healthycocktails.trujillo.healthycocktails;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JennyG on 3/25/2016.
 */
public class WebRequest {

    static String response = null;
    public final static int GET = 1;
    public final static int POST = 2;

    //Constructor with no parameter
    public WebRequest(){

    }

    /***
     * Making web service call
     *
     * @url - makes request
     * @requestMethod - request method
     ***/

    public String makeWebServiceCall(String url, int requestMethod){
        return this.makeWebServiceCall(url, requestMethod, null);
    }

    /***
     *@urlAddress - makes request
     *@requestMethod = request method
     * @params - request params
     ***/

    public String makeWebServiceCall(String urlAddress, int requestMethod,
                                     HashMap<String, String>params){
        URL url;
        String response = "";
        try
            {
                url = new URL(urlAddress);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setDoInput(true);
                conn.setDoOutput(true);

                if (requestMethod == POST){
                    conn.setRequestMethod("POST");
                } else if (requestMethod == GET){
                    conn.setRequestMethod("GET");
                }

                if (params != null){
                    OutputStream os = conn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter((os), "UTF-8"));

                    StringBuilder result = new StringBuilder();
                    boolean first = true;
                    for (Map.Entry<String, String> entry : params.entrySet()){
                        if(first)
                            first = false;
                        else
                            result.append("&");

                        result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                        result.append("=");
                        result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));

                    }
                    writer.write(result.toString());
                    writer.flush();
                    os.close();

                }
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK){
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line = br.readLine()) != null){
                        response += line;
                    }
                } else {
                    response ="";
                }
            }catch (Exception e){
                e.printStackTrace();
        }
        return response;
    }
}
