package com.driver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public String addOrderobject(Order order){
        String result=orderRepository.addOrder(order);
        return result;
    }

    
    public String addpartnerBy(String partneId){
        String res=orderRepository.addDeliverypartner(partneId);
        return res;
    }


    public String addorderpartnerpair(String orderId,String partnerId){
        String res=orderRepository.addorderpartner(orderId,partnerId);
        return res;
    }

    // public void addDeliverypartnerpair(String orderId, String partnerId){

    //     orderRepository.addDeliverypartnerpair(orderId,partnerId);
    // }
    
    // get order
    
    public Order getOrderwithId(String orderId){
         Order order=orderRepository.getOrderbyId(orderId);
         return order;
        
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
    
    public int UnassignedCountorder(){
             
            int c=orderRepository.getUnassignedCountorder();
             return c;
        }


    public int getOrdersLeftdelivered(String time,String partnerId){
           
        int c=orderRepository.getOrdersLeft(time,partnerId);
        return c;
    }



    public String getLastOrdertime(String partnerId){
        String res="";
         res= orderRepository.getLasttime(partnerId);
         return res;
    }


    public String deletePartnerbyId(String partnerId){
       String res=orderRepository.deletePartner(partnerId);
      return res;
    }
    
    
    public  String deleteOrderbyId(String orderId){
       
       String s=orderRepository.deleteOrder(orderId);
       return s;
    
    }

    }
    
    

