/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class Fruit {
    private int ID, price, quantity, numStorage, discount;
    private double amount;
    private String name, origin;

    public Fruit() {
    }

    public Fruit(int ID, int price, String name, String origin, int numStorage) {
        this.ID = ID;
        this.price = price;
        this.quantity = 0;
        this.origin = origin;
        this.name = name;
        this.numStorage = numStorage;
        this.amount = this.price * this.quantity;
    }

    public int getID() {
        return ID;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public String getOrigin() {
        return origin;
    }

    public int getNumStorage() {
        return numStorage;
    }

    public int getAmount() {
        return quantity * price;
    }
    
    public double getTotal(){
        return amount;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumStorage(int numStorage) {
        this.numStorage = numStorage;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
