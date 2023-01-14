package com.driver;

public class Order {

    private String id;
    private int deliveryTime;
    
    public Order(String id, String deliveryTime) {
        this.id=id;
        deliveryTime = String.format("%02d") + ":" + String.format("%02d");
        String[] timef=deliveryTime.split(":");  
        
        int hour=Integer.parseInt(timef[0]);  
        int minute=Integer.parseInt(timef[1]);  
         
        
          
        this.deliveryTime =minute + (60 * hour);  
        
        
         
        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
