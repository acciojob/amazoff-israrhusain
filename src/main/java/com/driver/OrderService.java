package com.driver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public void addOrderobject(Order order){
        orderRepository.addOrder(order);
    }

    
    public  void addpartnerBy(String partneId){
        orderRepository.addDeliverypartner(partneId);
    }


    public void addorderpartnerpair(String orderId,String partnerId){
        orderRepository.addorderpartner(orderId,partnerId);;
    }

    // public void addDeliverypartnerpair(String orderId, String partnerId){

    //     orderRepository.addDeliverypartnerpair(orderId,partnerId);
    // }
    
    // get order
    
    public Order getOrderwithId(String orderId){
        return orderRepository.getOrderbyId(orderId);
        
    }
    
    public DeliveryPartner getPartnerbyId(String partnerId){
        return orderRepository.getPartner(partnerId);
    }
    
    public Integer getNumbersOrder(String partnerId){
        return orderRepository.getNumbersOrderbypartnerId(partnerId);
    }


     public List<String> getListorderbyId(String partnerId){
              List<String> list=new ArrayList<>();
              list=orderRepository.getListorder(partnerId);

              return list;
            }
         
          

     public List<String> getallOrder(){
        List<String> list=new ArrayList<>(); 
          list=orderRepository.getallOrderList();

          return list;
            
    }
    
    public Integer UnassignedCountorder(){
              
            return orderRepository.getUnassignedCountorder();

        }


    public Integer getOrdersLeftdelivered(String time,String partnerId){
            
        return orderRepository.getOrdersLeft(time,partnerId);
    }



    public String getLastOrdertime(String partnerId){
        return orderRepository.getLasttime(partnerId);
    }


    public  void deletePartnerbyId(String partnerId){
      orderRepository.deletePartner(partnerId);
    }
    
    
    public  void deleteOrderbyId(String orderId){
      
       orderRepository.deleteOrder(orderId);
    
    }

    }
    
    

