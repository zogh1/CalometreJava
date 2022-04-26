/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


/**
 *
 * @author seifd
 */
public class product {

    private int id;
    private String name;
    private double price;
    private String description;
    private int quantity;
    private category category_id;
    private String image;
    private int count;
    private String color;

    public product(int id, String name, double price, String description, int quantity, category category_id, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.category_id = category_id;
        this.image = image;
    }

    public product(int id, String name, category category_id, int count) {
        this.id = id;
        this.name = name;
        this.category_id = category_id;
        this.count = count;
    }

    public product(String name, double price, String description, int quantity, category category_id, String image) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.category_id = category_id;
        this.image = image;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public category getCategory_id() {
        return category_id;
    }

    public void setCategory_id(category category_id) {
        this.category_id = category_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public product() {
    }

    public void setPrice(String price) {
       
    }
  public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

   
}
