/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pflorderingproblem;

import javax.json.*;

/**
 *
 * @author Emmanuel
 */
public class Package {
    private int id;
    private int productID;
    public String name;
    public String description;
    public String imageURL;
    private boolean hasTemplate;
    private int quantityDefault;
    private int quantityIncrement;
    private int quantityMaximum;
    private int quantityMinimum;
    private String shippingMethodDefault;
    private JsonArray deliveredPrices;

    public JsonArray getDeliveredPrices() {
        return deliveredPrices;
    }

    public void setDeliveredPrices(JsonArray deliveredPrices) {
        this.deliveredPrices = deliveredPrices;
    }

    public Package(int id, int productID, String name) {
        this.id = id;
        this.productID = productID;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean isHasTemplate() {
        return hasTemplate;
    }

    public void setHasTemplate(boolean hasTemplate) {
        this.hasTemplate = hasTemplate;
    }

    public int getQuantityDefault() {
        return quantityDefault;
    }

    public void setQuantityDefault(int quantityDefault) {
        this.quantityDefault = quantityDefault;
    }

    public int getQuantityIncrement() {
        return quantityIncrement;
    }

    public void setQuantityIncrement(int quantityIncrement) {
        this.quantityIncrement = quantityIncrement;
    }

    public int getQuantityMaximum() {
        return quantityMaximum;
    }

    public void setQuantityMaximum(int quantityMaximum) {
        this.quantityMaximum = quantityMaximum;
    }

    public int getQuantityMinimum() {
        return quantityMinimum;
    }

    public void setQuantityMinimum(int quantityMinimum) {
        this.quantityMinimum = quantityMinimum;
    }

    public String getShippingMethodDefault() {
        return shippingMethodDefault;
    }

    public void setShippingMethodDefault(String shippingMethodDefault) {
        this.shippingMethodDefault = shippingMethodDefault;
    }
    
    
    
    
}
