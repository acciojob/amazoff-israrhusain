package com.driver;

public class Order {

    private String id;
    private int deliveryTime;
    
    public Order(String id, String deliveryTime) {
        this.id=id;
<<<<<<< HEAD
         
         String[] time=deliveryTime.split(":");
        int hour=Integer.parseInt(timef[0]);  
        int minute=Integer.parseInt(timef[1]);  
         
        
          
        this.deliveryTime =minute + (60 * hour);  
        
        
         
=======
        int h=Integer.parseInt(deliveryTime.split(":")[0]);
        int m=Integer.parseInt(deliveryTime.split(":")[1]);
>>>>>>> 471fe755bbd9d6b3f9df39ee123df30489f141f4
        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.deliveryTime=m+h*60;
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
