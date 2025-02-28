package com.ragheb.ecommerce.payment;

import com.ragheb.ecommerce.customer.CustomerResponse;
import com.ragheb.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
