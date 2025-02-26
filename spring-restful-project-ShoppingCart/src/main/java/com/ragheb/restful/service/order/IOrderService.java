package com.ragheb.restful.service.order;

import com.ragheb.restful.dto.OrderDto;
import com.ragheb.restful.model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId);
    List<OrderDto> getUserOrders(Long userId);
}
