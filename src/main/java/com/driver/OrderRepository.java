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
   public String addOrder(Order order){
      
       ordermap.put(order.getId(),order);
       return "added";
   }
  
   

  //2 save deliverpartner
   public String addDeliverypartner(String partnerId){
        DeliveryPartner partner=new DeliveryPartner(partnerId);
         deliverypartnerrmap.put(partner.getId(),partner);
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
    return ordermap.get(orderId);
    
}



public DeliveryPartner getPartner(String partnerId){
    return deliverypartnerrmap.get(partnerId);
}



public int getNumbersOrderbypartnerId(String partnerId){
      int c=0;   
     DeliveryPartner partner=deliverypartnerrmap.get(partnerId);
     c=partner.getNumberOfOrders();
     return c;
}
 public List<String> getListorder(String partnerId){
      List<String> list=partnerorderpairmap.get(partnerId);
       if(partnerorderpairmap.size()==0){
              list=new ArrayList<>();
       }
      
      return list;
 }


 public List<String> getallOrderList(){
      return new ArrayList<>(ordermap.keySet());
        
}

public int getUnassignedCountorder(){
      int count=0;
      count=ordermap.size()-orderpartnermap.size();
      return count;
}
public int getOrdersLeft(String time,String partnerId){
        int cnt=0;
        
           List<String> orders=partnerorderpairmap.get(partnerId);
           int derliveryTime=Integer.parseInt(time.substring(0,2))*60+Integer.parseInt(time.substring(3));
             for(String orderId: orders){
                Order order=ordermap.get(orderId);
                int gettime=order.getDeliveryTime();
                if(gettime>derliveryTime){
                  cnt++;
                }
            
         }
       return  cnt;
}
public String getLasttime(String partnerId){
  
  int deliveryTime=0;
  String ans="";
  List<String> orders=new ArrayList<>();
  if(partnerorderpairmap.containsKey(partnerId)){
     orders=partnerorderpairmap.get(partnerId);
      String lastorderId=orders.get(orders.size()-1);
       Order order=ordermap.get(lastorderId);
        deliveryTime=order.getDeliveryTime();
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
        for(String s:list){
            if(s.equals(orderId)){
                list.remove(s);
            }
        }
          partnerorderpairmap.put(partnerId,list);
        return "deleted";
    }
}


