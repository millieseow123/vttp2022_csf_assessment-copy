package vttp2022.assessment.csf.orderbackend.controllers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2022.assessment.csf.orderbackend.models.Order;
import vttp2022.assessment.csf.orderbackend.models.OrderSummary;
import vttp2022.assessment.csf.orderbackend.services.OrderService;

@RestController
@RequestMapping(path="/api/order")
public class OrderRestController {

    @Autowired
    private OrderService orderSvc;

    Order order;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postOrder(@RequestBody Order order) {
                
        try {
           orderSvc.createOrder(order);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ResponseEntity.ok("{}");
    }

   @GetMapping(path="{email}")
   public ResponseEntity<String> getOrder(@PathVariable String email) {
        Optional<OrderSummary> opt = orderSvc.getOrdersByEmail(email);
        
        OrderSummary orderSummary = opt.get();
        return ResponseEntity.ok(orderSummary.toJson().toString());
   }



}
