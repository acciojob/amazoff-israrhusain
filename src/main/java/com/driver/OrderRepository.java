package com.driver;
import java.util.*;


import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {
    
     
    private HashMap<String,Order> ordermap;
    private HashMap<String,DeliveryPartner> deliverypartnerrmap;
    private HashMap<String,String> orderpartnermap;

    private HashMap<String,List<String>> partnerorderpairmap;

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
  
   

  //2 save deliverpartner
   public void addDeliverypartner(DeliveryPartner partner){
         deliverypartnerrmap.put(partner.getId(),partner);
      }
      
   
   public void addorderpartner(String orderId, String partnerId){
        
        DeliveryPartner partner =deliverypartnerrmap.get(partnerId);
         int n=partner.getNumberOfOrders();
         partner.setNumberOfOrders(n+1);
        orderpartnermap.put(partnerId,orderId);


       List<String> orderlist=new ArrayList<>();
       if(partnerorderpairmap.size()==0){
            orderlist.add(orderId);
       }
       if(partnerorderpairmap.containsKey(partnerId)){
        
        orderlist=partnerorderpairmap.get(partnerId);
        orderlist.add(orderId);
       }
         partnerorderpairmap.put(partnerId,orderlist);
      
     }
 
             
       
public Order getOrderById(String orderId){
    return ordermap.get(orderId);
    
}



public DeliveryPartner getPartner(String partnerId){
    return deliverypartnerrmap.get(partnerId);
}



public Integer getNumbersOrderbypartnerId(String partnerId){
      Integer c=0;   
     DeliveryPartner partner=deliverypartnerrmap.get(partnerId);
     c=partner.getNumberOfOrders();
     return c;
}
 public List<String> getListorder(String partnerId){
      List<String> list =new ArrayList<>();
       
      list=partnerorderpairmap.get(partnerId);
      return list;
 }


 public List<String> getallOrderList(){
      return new ArrayList<>(ordermap.keySet());
        
}

public Integer getUnassignedCountorder(){
     Integer cnt=0;
     for(String Id:deliverypartnerrmap.keySet()){
        for(String s:orderpartnermap.keySet())
         if(!(Id.equals(s))){
            cnt++;
         }
       }
       return cnt;
}
public Integer getOrdersLeft(String time,String partnerId){
        int cnt=0;
      
        List<String> orders=new ArrayList<>();
         if(partnerorderpairmap.containsKey(partnerId)){
             orders=partnerorderpairmap.get(partnerId);
             for(String orderId: orders){
                Order order=ordermap.get(orderId);
                String gettime=order.getDeliveryTime()+"";
                if(!gettime.equals(time)){
                  cnt++;
                }
             }
         }
     return  cnt;
}
public int getLasttime(String partnerId){
  
  int ans=0;
  List<String> orders=new ArrayList<>();
  if(partnerorderpairmap.containsKey(partnerId)){
     orders=partnerorderpairmap.get(partnerId);
      String lastorderId=orders.get(orders.size()-1);
       Order order=ordermap.get(lastorderId);
        ans=order.getDeliveryTime();
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

