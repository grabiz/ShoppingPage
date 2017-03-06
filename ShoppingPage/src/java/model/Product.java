/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author NGUYEN
 */
public class Product implements Serializable{
    int prodID;
    String prodName;
    double prodPrice;
    String prodDescription;
    
     public Product() {
        this.prodID = 0;
        this.prodName = "";
        this.prodPrice = 0;
        this.prodDescription = "";
    }
    public Product(int prodID, String prodName, double prodPrice, String prodDescription) {
        this.prodID = prodID;
        this.prodName = prodName;
        this.prodPrice = prodPrice;
        this.prodDescription = prodDescription;
    }

    public int getProdID() {
        return prodID;
    }

    public void setProdID(int prodID) {
        this.prodID = prodID;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public double getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(double prodPrice) {
        this.prodPrice = prodPrice;
    }

    public String getProdDescription() {
        return prodDescription;
    }

    public void setProdDescription(String prodDescription) {
        this.prodDescription = prodDescription;
    }
    
    
}
