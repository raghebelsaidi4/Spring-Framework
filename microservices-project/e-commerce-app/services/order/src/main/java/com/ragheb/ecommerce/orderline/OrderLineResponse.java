package com.ragheb.ecommerce.orderline;

import com.ragheb.ecommerce.order.PaymentMethod;

public record OrderLineResponse(
        Integer id,
        double quantity
) {
}
