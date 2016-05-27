package com.example.farooqezhar.chickenriceapp;

public class ChickenRice {
    private String status;

    public ChickenRice(){}

    public String cook(int temperature){
        if(temperature > 110){
            status = "cooked";
        }else if(temperature >= 50 && temperature <= 110){
            status = "undercooked";
        }else if(temperature < 50){
            status = "uncooked";
        }
        return status;
    }
    public boolean eat(boolean ginger, boolean chili){
        if(ginger && chili){
            return true;
        }else {
            return false;
        }
    }
}
