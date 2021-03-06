/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baristathread;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import java.util.HashMap;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author melsayed
 */
public class ProcessingThread implements Runnable {

    private static final String UPDATE_DELETE_ORDER = "http://localhost:8080/api/v1/order/{id}";
    private static final String UPDATE_ORDER = "http://localhost:8080/api/v1/order/";

    private static final String PROCESSING_ORDERS = "http://localhost:9999/api/v1/process-order";
    private static final String UPDATE_BARISTA = "http://localhost:9999/api/v1/process-order/";

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(3000);
                // Invoke method(s) to do the work
                doPeriodicWork();

            } catch (IOException ex) {
                Logger.getLogger(ProcessingThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ProcessingThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void doPeriodicWork() throws IOException {

        List<OrderProcessing> orders = getOrderInService();
        if (orders.size() == 0) {
            System.out.println("no connection to barista micro-service");
        }
        for (OrderProcessing orderProcessing : orders) {
            States current = orderProcessing.getStatus();
            OrderRequest request = new OrderRequest();

            switch (orderProcessing.getStatus()) {
                case Waiting:
                    orderProcessing.setStatus(States.InPrepatation);
                    request.setStatus("Processing");
                    new UpdateBaristaHelper().updateOrdering(request, UPDATE_ORDER + orderProcessing.getOrderId());

                    break;
                case InPrepatation:
                    orderProcessing.setStatus(States.Finished);
                    request.setStatus("Completed");
                    new UpdateBaristaHelper().updateOrdering(request, UPDATE_ORDER + orderProcessing.getOrderId());

                    break;
             
//                    removeFromOrders(orderProcessing.getOrderId());

                   
                case Picked:
                    removeFromOrders(orderProcessing.getOrderId());
                    break;

                default:
                    break;
            }
            try {
                if (current != orderProcessing.getStatus()) {
                    new UpdateBaristaHelper().updateBarista(orderProcessing, UPDATE_BARISTA + orderProcessing.getOrderId());

                }

            } catch (IOException ex) {
                Logger.getLogger(ProcessingThread.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void removeFromOrders(long id) {

        Map<String, String> params = new HashMap<>();
        params.put("id", id + "");
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.delete(UPDATE_DELETE_ORDER, params);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public List<OrderProcessing> getOrderInService() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity< String> entity = new HttpEntity< String>("parameters", headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity< String> result;
        try {
            result = restTemplate.exchange(PROCESSING_ORDERS, HttpMethod.GET, entity,
                    String.class);
        } catch (Exception ex) {
            return new ArrayList<>();
        }

        System.out.println(result.getBody());
        return new Gson().fromJson(result.getBody(), new TypeToken<ArrayList<OrderProcessing>>() {
        }.getType());

    }
}
