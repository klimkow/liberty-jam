package com.liberty.technical.logic.entity;

/**
 * @author M-AKI
 */
public class OrderVO
{
    public Long id;
    public int amount;
    public int price;
    public String name;
    public String surname;
    public String email;
    public String phone;
    public String st1;
    public String st2;
    public String st3;
    public String st4;
    public String st5;

    public OrderVO(Long id, int price, String name, String surname, String email, String phone)
    {
        this.id = id;
        this.price = price;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    public OrderVO(int price, int amount, String st1, String st2, String st3, String st4, String st5)
    {
        this.price = price;
        this.amount = amount;
        this.st1 = st1;
        this.st2 = st2;
        this.st3 = st3;
        this.st4 = st4;
        this.st5 = st5;
    }


    public Long getId()
    {
        return id;
    }


    public String getPhone()
    {
        return phone;
    }


    public void setPhone(String phone)
    {
        this.phone = phone;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public String getName()
    {
        return name;
    }


    public void setName(String name)
    {
        this.name = name;
    }


    public String getSurname()
    {
        return surname;
    }


    public void setSurname(String surname)
    {
        this.surname = surname;
    }


    public String getEmail()
    {
        return email;
    }


    public void setEmail(String email)
    {
        this.email = email;
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

    public String getSt1() {
        return st1;
    }

    public void setSt1(String st1) {
        this.st1 = st1;
    }

    public String getSt2() {
        return st2;
    }

    public void setSt2(String st2) {
        this.st2 = st2;
    }

    public String getSt3() {
        return st3;
    }

    public void setSt3(String st3) {
        this.st3 = st3;
    }

    public String getSt4() {
        return st4;
    }

    public void setSt4(String st4) {
        this.st4 = st4;
    }

    public String getSt5() {
        return st5;
    }

    public void setSt5(String st5) {
        this.st5 = st5;
    }
}
