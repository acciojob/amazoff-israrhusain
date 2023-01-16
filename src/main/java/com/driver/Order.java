package com.driver;

public class Order {

    private String id;
    private int deliveryTime;
    
    public Order(String id, String deliveryTime) {
        this.id=id;
            int hour=Integer.parseInt(deliveryTime.split(":")[0]);  
            int minute=Integer.parseInt(deliveryTime.split(":")[1]);
            this.deliveryTime =minute + (60 * hour);    
         }
       // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
    

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
