package com.ragheb.ecommerce.order;

import com.ragheb.ecommerce.customer.CustomerClient;
import com.ragheb.ecommerce.exception.BusinessException;
import com.ragheb.ecommerce.kafka.OrderConfirmation;
import com.ragheb.ecommerce.kafka.OrderProducer;
import com.ragheb.ecommerce.orderline.OrderLineRequest;
import com.ragheb.ecommerce.orderline.OrderLineService;
import com.ragheb.ecommerce.payment.PaymentClient;
import com.ragheb.ecommerce.payment.PaymentRequest;
import com.ragheb.ecommerce.prodcut.ProductClient;
import com.ragheb.ecommerce.prodcut.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(@Valid OrderRequest request) {
        // check the customer --> OpenFeign
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: no customer exists with the provide ID :: "));
        // purchase the products -> product-ms (RestTemplate)
        var purchasedProducts = this.productClient.purchaseProducts(request.products());
        // persist order
        var order = this.repository.save(mapper.toOrder(request));
        // persist order lines
        for (PurchaseRequest purchaseRequest : request.products()) {
            this.orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    ));
        }
        // start payment process
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        // request payment
        paymentClient.requestOrderPayment(paymentRequest);

        // send the order confirmation --> notification-ms (kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );
        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return this.repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find order with the provided ID :: " + orderId));
    }
}
