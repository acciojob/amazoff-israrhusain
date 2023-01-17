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
         DeliveryPartner par=orderRepository.getPartner(partnerId);
         return par;
    }
    
    public int getNumbersOrder(String partnerId){

         int c=orderRepository.getNumbersOrderbypartnerId(partnerId);
         return c;
    }


     public List<String> getListorderbyId(String partnerId){
             
              List<String> res=orderRepository.getListorder(partnerId);

              return res;
            }
         
          

     public List<String> getallOrder(){
       
        List<String> list=orderRepository.getallOrderList();

          return list;
            
    }
    
    public Integer UnassignedCountorder(){
             Integer c=0; 
             c=orderRepository.getUnassignedCountorder();
             return c;
        }


    public Integer getOrdersLeftdelivered(String time,String partnerId){
          Integer c=0;  
        c=orderRepository.getOrdersLeft(time,partnerId);
        return c;
    }



    public String getLastOrdertime(String partnerId){
        String res="";
         res= orderRepository.getLasttime(partnerId);
         return res;
    }


    public String deletePartnerbyId(String partnerId){
        String res="";
      res=orderRepository.deletePartner(partnerId);
      return res;
    }
    
    
    public  String deleteOrderbyId(String orderId){
       String s="";
       s=orderRepository.deleteOrder(orderId);
       return s;
    
    }

    }
    
    

