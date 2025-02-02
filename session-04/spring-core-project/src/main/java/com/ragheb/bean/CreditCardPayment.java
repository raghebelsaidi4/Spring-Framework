package com.ragheb.bean;

public class CreditCardPayment implements IPayment{

    public CreditCardPayment() {
        System.out.println("CreditCardPayment Constructor");
    }

    @Override
    public boolean precessPayment(double billAmount) {

        System.out.println("Precess Payment credit card successful....");

        return true;
    }
}
