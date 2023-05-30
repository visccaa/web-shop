package com.webshop.shop.classes;

public class Sale {
    private int id;
    private int productId;
    private int buyerId;
    private int quantity;

    public Sale(int id, int productId, int buyerId, int quantity){
        this.id = id;
        this.productId = productId;
        this.buyerId = buyerId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Buyer getProduct() {
        return null;
    }

    public Buyer getBuyer() {
        return null;
    }
}
