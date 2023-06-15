package com.ai.ozmasteel.POJOs;

public class POJOProductAddToCart {

    String cartId="",productId="",cartQty="";

    public POJOProductAddToCart(String cartId, String productId, String cartQty) {
        this.cartId = cartId;
        this.productId = productId;
        this.cartQty = cartQty;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCartQty() {
        return cartQty;
    }

    public void setCartQty(String cartQty) {
        this.cartQty = cartQty;
    }
}
