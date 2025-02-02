package com.ragheb.bean;

public class PaymentService {

    private IPayment payment;

    public PaymentService() {
        System.out.println("PaymentService constructor");
    }

    public PaymentService(IPayment payment) {
        this.payment = payment;
    }

    public void doPayment(double billAmount) {
        boolean status = payment.precessPayment(billAmount);
        //payment.precessPayment(billAmount);
        if (status){
            System.out.println("Payment successful");
        }else {
            System.out.println("Payment failed");
        }
    }

    public void setPayment(IPayment payment) {
        System.out.println("setPayment method called...");
        this.payment = payment;
    }
}
