package com.example.demo.model;

public class Order {
    private long m_id;
    private String dish;
    private int price;
    private int qunt;

    public Order(){
    }

    public Order(long m_id, String dish, int price, int qunt)
    {
        this.m_id=m_id;
        this.dish=dish;
        this.price=price;
        this.qunt=qunt;
    }

    public Order(String dish, int price, int qunt)
    {
        this.dish=dish;
        this.price=price;
        this.qunt=qunt;
    }

    public long getM_id() {
        return m_id;
    }
    public void setM_id(long m_id) {
        this.m_id = m_id;
    }

    public String getDish() {
        return dish;
    }
    public void setDish(String dish) {
        this.dish = dish;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public int getQunt() { return qunt; }
    public void setQunt(int qunt) { this.qunt = qunt; }
}
