package com.liberty.technical.logic.entity;

/**
 * @author M-AKI
 */
public class OrderVO
{
    public int amount;
    public int price;

    public OrderVO(int price, int amount)
    {
        this.price = price;
        this.amount = amount;
    }

    int getAmount() {
        return amount;
    }

    void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
