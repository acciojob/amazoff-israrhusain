package com.driver;
import java.util.*;


import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {
    
     
    private HashMap<String,Order> ordermap;
    private HashMap<String,DeliveryPartner> deliverypartnerrmap;
    private HashMap<String,String> orderpartnermap;

    private HashMap<String,ArrayList<String>> partnerorderpairmap;

    OrderRepository(){
        this.deliverypartnerrmap=new HashMap<>();
        this.ordermap=new HashMap<>();
        this.orderpartnermap=new HashMap<>();
        this.partnerorderpairmap=new HashMap<>();
    }
  //1 save order
   public void addOrder(Order order){
      ordermap.put(order.getId(), order);
   } 
  
  
 
   //3 save order partner 
   public void addorderpartner(String orderId, String partnerId){
       if(ordermap.containsKey(orderId) && deliverypartnerrmap.containsKey(partnerId)){
          orderpartnermap.put(partnerId,orderId);
       }
        
   }

  //2 save deliverpartner
   public void addDeliverypartner(String partnerId){
    DeliveryPartner deliveryPartner=new DeliveryPartner(partnerId);
      deliverypartnerrmap.put(partnerId,deliveryPartner);
   }

   //4 save orderpartner pair
   public void addDeliverypartnerpair(String partnerId, String orderId){

    if(orderpartnermap.containsKey(partnerId)  && ordermap.containsKey(orderId)){
   ArrayList<String> orderlist=new ArrayList<>();

     if(partnerorderpairmap.containsKey(partnerId)){
        orderlist=partnerorderpairmap.get(partnerId);
        orderlist.add(orderId);
      }
      partnerorderpairmap.put(partnerId,orderlist) ;
  }
}

// get order

public Order getOrderById(String orderId){
    return ordermap.get(orderId);
    
}

public DeliveryPartner getPartner(String partnerId){
    return deliverypartnerrmap.get(partnerId);
}

public Integer getNumbersOrderbypartnerId(String partnerId){
 int count=0;
  while(true){
    if(orderpartnermap.containsKey(partnerId)){
         count++;
    }
    else{
      break;
    }
     
  }
  return count;
}
 public List<String> getListorder(String partnerId){
      List<String> list =new ArrayList<>();
      if(partnerorderpairmap.containsKey(partnerId)){
           list=partnerorderpairmap.get(partnerId);
           
        }
     
      return list;
 }
 public List<String> getallOrderList(){
      return new ArrayList<>(ordermap.keySet());
        
}

public Integer getUnassignedCountorder(){
     for(String orderId: ordermap.keySet()){
        for(String s:orderpartnermap.keySet())
         if(!orderId.equals(s)){
            ordermap.remove(orderId);
         }
       }
       return ordermap.size();
}
public Integer getOrdersLeft(String time,String partnerId){
        int cnt=0;
        DeliveryPartner partner=new DeliveryPartner(partnerId);
        List<String> orders=new ArrayList<>();
         if(partnerorderpairmap.containsKey(partnerId)){
             orders=partnerorderpairmap.get(partnerId);
             for(String orderId: orders){
                Order order=ordermap.get(orderId);
                String gettime=order.getDeliveryTime()+"";
                if(gettime.equals(time)){
                  cnt++;
                }
             }
         }
     return  partner.getNumberOfOrders()-cnt;
}
public String getLasttime(String partnerId){
  
  String ans="";
  List<String> orders=new ArrayList<>();
  if(partnerorderpairmap.containsKey(partnerId)){
     orders=partnerorderpairmap.get(partnerId);
      String lastorderId=orders.get(orders.size()-1);
       Order order=ordermap.get(lastorderId);
       ans+=order.getDeliveryTime();
   }
  return ans;
}
public  void deletePartner(String partnerId){
  List<String> orders=new ArrayList<>();

  if(partnerorderpairmap.containsKey(partnerId)){
     orders= partnerorderpairmap.get(partnerId);
  for(String order: orders){
     if(ordermap.containsKey(order)){
         ordermap.remove(order);
     }
  }
  partnerorderpairmap.remove(partnerId);
 }
 if(deliverypartnerrmap.containsKey(partnerId)){
     deliverypartnerrmap.remove(partnerId);
 }
}


public  void deleteOrder(String orderId){
  
  if(orderpartnermap.containsKey(orderId)){
       ordermap.remove(orderpartnermap.get(orderId));
    }
    
  orderpartnermap.remove(orderId);
 if(deliverypartnerrmap.containsKey(orderId)){
     deliverypartnerrmap.remove(orderId);
 }

}
}

