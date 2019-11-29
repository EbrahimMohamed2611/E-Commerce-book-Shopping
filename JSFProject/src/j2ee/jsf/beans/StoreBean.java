package j2ee.jsf.beans;

import j2ee.jsf.classes.JDBCClass;
import j2ee.jsf.classes.Products;

import j2ee.jsf.classes.ProductsCart;

import java.io.Serializable;

import java.util.ArrayList;

public class StoreBean implements Serializable {
    public StoreBean() {
    }
    
    private ArrayList<Products> productsList = new ArrayList<Products>();
    private ArrayList<ProductsCart> productsCartList = new ArrayList<ProductsCart>();


    public void setProductsCartList(ArrayList<ProductsCart> productsCartList) {
        this.productsCartList = productsCartList;
    }

    public ArrayList<ProductsCart> getProductsCartList() {
        return productsCartList;
    }

    public void setProductsList(ArrayList<Products> productsList) {
        this.productsList = productsList;
    }

    public ArrayList<Products> getProductsList() {
        this.productsList =  JDBCClass.getAllProducts();
        return productsList;
    }


    public Object addProductToCart(Products product) {
        // Add event code here...
        
        int index = productExistOnCart(product);
        
        if(index == -1)
                this.productsCartList.add(new ProductsCart(product, 1));
        else {
            // Increment Quantity
            int newQuantity = this.productsCartList.get(index).getQuantity() + 1;
                this.productsCartList.get(index).setQuantity(newQuantity);
        }
        
        return "ShowCart";
    }
    
    public int productExistOnCart(Products product){
        for(int i = 0; i < productsCartList.size(); i++){
            if( productsCartList.get(i).getProduct().getProductId()
                                == product.getProductId() )
                    return i;           
        }
        return -1;
    }
    
    public double sumCart(){
        double sum = 0;
        for(ProductsCart item : productsCartList){
                sum = sum + item.getProduct().getProductPrice() * item.getQuantity();
        }
        
        return Math.round(sum);
    }
    
    
    public Object removeProduct(ProductsCart productCartItem){
        this.productsCartList.remove( productCartItem );
        
        return "Store";
    }
    
    
    
}
