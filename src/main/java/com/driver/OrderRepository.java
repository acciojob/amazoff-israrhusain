package com.driver;
import java.util.*;


import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {
    
     
            HashMap<String,Order> ordermap;
           HashMap<String,DeliveryPartner> deliverypartnerrmap;
           HashMap<String,String> orderpartnermap;

          HashMap<String,List<String>> partnerorderpairmap;

    OrderRepository(){
        this.deliverypartnerrmap=new HashMap<>();
        this.ordermap=new HashMap<>();
        this.orderpartnermap=new HashMap<>();
        this.partnerorderpairmap=new HashMap<>();
    }
  //1 save order
   public String addOrder(Order order){
      
       ordermap.put(order.getId(),order);
       return "added";
   }
  
   

  //2 save deliverpartner
   public String addDeliverypartner(String partnerId){
        DeliveryPartner partner=new DeliveryPartner(partnerId);
         deliverypartnerrmap.put(partnerId,partner);
         return "added";
      }
      
   
   public String addorderpartner(String orderId, String partnerId){
        
       List<String> orderlist=partnerorderpairmap.getOrDefault(partnerId,new ArrayList<>());
        orderlist.add(orderId);
       partnerorderpairmap.put(partnerId,orderlist);
       
       orderpartnermap.put(orderId,partnerId);
       DeliveryPartner partner =deliverypartnerrmap.get(partnerId);
       partner.setNumberOfOrders(orderlist.size());
      


         return "updated";
      
     }
 
             
       
public Order getOrderbyId(String orderId){
    for(String s:ordermap.keySet()){
        if(s.equals(orderId)){
            return ordermap.get(s);
        }
     }
    return null;
    
}



public DeliveryPartner getPartner(String partnerId){
    if(deliverypartnerrmap.containsKey(partnerId)){
       return deliverypartnerrmap.get(partnerId);
    }
    return null;
}



public int getNumbersOrderbypartnerId(String partnerId){
     
     int orders=partnerorderpairmap.getOrDefault(partnerId,new ArrayList<>()).size();
     return orders;
}
 public List<String> getListorder(String partnerId){
      List<String> list=partnerorderpairmap.getOrDefault(partnerId,new ArrayList<>());
      
      return list;
 }


 public List<String> getallOrderList(){
      return new ArrayList<>(ordermap.keySet());
        
}

public int getUnassignedCountorder(){
      
      int count=ordermap.size()-orderpartnermap.size();
      return count;
}
public int getOrdersLeft(String time,String partnerId){
        int cnt=0;
        
           List<String> orders=partnerorderpairmap.get(partnerId);
           int derliveryTime=Integer.parseInt(time.substring(0,2))*60+Integer.parseInt(time.substring(3));
             for(String orderId: orders){
                Order order=ordermap.get(orderId);
                
                if(order.getDeliveryTime()>derliveryTime){
                  cnt++;
                }
            
         }
       return  cnt;
}
public String getLasttime(String partnerId){
  
  int deliveryTime=0;
  String ans="";
  List<String> orderslist=new ArrayList<>();
  
     orderslist=partnerorderpairmap.get(partnerId);
       for(String id: orderslist){
        Order order=ordermap.get(id);
        deliveryTime=Math.max(order.getDeliveryTime(),deliveryTime);
       }
   
        int hour=deliveryTime/60;
        String shour="";
        if(hour<10){
            shour+="0"+String.valueOf(hour);
        }
        else{
            shour=String.valueOf(hour);
        }
        int min=deliveryTime%60;
        String minute="";
        if(min<10){
            minute="0"+String.valueOf(min);
        }
        else{
            minute=String.valueOf(min);
        }
        ans=shour+":"+minute;
        return ans;
}
public String deletePartner(String partnerId){
  
      deliverypartnerrmap.remove(partnerId);
      List<String> list=partnerorderpairmap.getOrDefault(partnerId, new ArrayList<>());
      ListIterator<String> itr=list.listIterator();
       while(itr.hasNext()){
         String s=itr.next();
         partnerorderpairmap.remove(s);
       }
       partnerorderpairmap.remove(partnerId);
       return "deleted";
    }
  
public String deleteOrder(String orderId){
  
  if(ordermap.containsKey(orderId)){
       ordermap.remove(orderId);
    }
    
          
        String partnerId=orderpartnermap.get(orderId);
        List<String> list=partnerorderpairmap.get(partnerId);
        ListIterator<String> itr=list.listIterator();
        while(itr.hasNext()){
            String s=itr.next();
            if(s.equals(orderId)){
                itr.remove();
            }
        }
          partnerorderpairmap.put(partnerId,list);
        return "deleted";
    }
}


