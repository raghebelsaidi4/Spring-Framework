package com.ragheb.bean;

public class DebitCardPayment implements IPayment {

    public DebitCardPayment() {
        System.out.println("DebitCardPayment constructor");
    }

    @Override
    public boolean precessPayment(double billAmount) {
        System.out.println("Precess Payment of Debit Card successful...");
        return true;
    }
}
