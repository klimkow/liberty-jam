package com.liberty.technical.logic.entity;

/**
 * @author M-AKI
 */
public class OrderVO
{
    public int amount;

    public OrderVO(int amount)
    {
        this.amount = amount;
    }

    int getAmount() {
        return amount;
    }

    void setAmount(int amount) {
        this.amount = amount;
    }
}
