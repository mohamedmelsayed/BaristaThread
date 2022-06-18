/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baristathread;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.OutputStreamWriter;
import static java.lang.Math.random;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author melsayed
 */
public class UpdateBaristaHelper {
public UpdateBaristaHelper(){
    
}
    public void updateBarista(OrderProcessing body,String url2) throws MalformedURLException, IOException   {
                Random random = new Random();

 URL url = new URL(url2);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
    try (OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream())) {
        osw.write(String.format(new Gson().toJson(body), random.nextInt(30), random.nextInt(20)));
        osw.flush();
    }
        System.err.println(connection.getResponseCode());
    }
}
