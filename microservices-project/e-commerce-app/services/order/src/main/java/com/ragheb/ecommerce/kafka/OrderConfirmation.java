package com.ragheb.ecommerce.kafka;

import com.ragheb.ecommerce.customer.CustomerResponse;
import com.ragheb.ecommerce.order.PaymentMethod;
import com.ragheb.ecommerce.prodcut.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
