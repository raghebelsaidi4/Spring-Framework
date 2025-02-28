package com.ragheb.ecommerce.kafka.payment;

import com.ragheb.ecommerce.notification.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification,String> {
}
