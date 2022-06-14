package com.example.lab2_mehulbhai_c0849394_android;

public class ModelClass {
    String id="";
    String product_id="";
    String product_name ="";
    String product_description ="";
    String product_price ="";

    public ModelClass(String id, String product_id, String product_name, String product_description, String product_price) {
        this.id = id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_description = product_description;
        this.product_price = product_price;
    }

    public ModelClass() {

    }

    //getter and setter method for attribute

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String id) {
        this.product_id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

}
