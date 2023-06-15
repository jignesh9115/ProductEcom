package com.ai.ozmasteel.POJOs;

public class AllProductsPOJO {

    String product_id,product_name,cat_id,cat_name,company_name,description,qty,price;

    public AllProductsPOJO(String product_id, String product_name, String cat_id, String cat_name, String company_name, String description, String qty, String price) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.cat_id = cat_id;
        this.cat_name = cat_name;
        this.company_name = company_name;
        this.description = description;
        this.qty = qty;
        this.price = price;
    }

        public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
