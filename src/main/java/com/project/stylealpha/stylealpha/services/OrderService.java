package com.project.stylealpha.stylealpha.services;

import com.project.stylealpha.stylealpha.model.Order;
import com.project.stylealpha.stylealpha.model.OrderItem;
import com.project.stylealpha.stylealpha.model.Product;
import com.project.stylealpha.stylealpha.model.User;
import com.project.stylealpha.stylealpha.repositories.OrderItemRepository;
import com.project.stylealpha.stylealpha.repositories.OrderRepository;
import com.project.stylealpha.stylealpha.repositories.ProductRepository;
import com.project.stylealpha.stylealpha.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class OrderService {


    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;


    public void savePurcahsed(Order order) {

            User user = userRepository.findByUsername(order.getUser().getUsername());

            Order newOrder = new Order();

            newOrder.setUser(user);
            newOrder.setStatus(order.getStatus());
            newOrder.setOrder_date(order.getOrder_date());
            newOrder.setComment(order.getComment());
            orderRepository.save(newOrder);

            Order o = orderRepository.findTopByOrderByIdDesc();

            for(OrderItem oi: order.getOrderItems()){
                oi.setOrder(o);
                oi.setStatus("purchased");
                orderItemRepository.save(oi);
            }

            o.setOrderItems(order.getOrderItems());
            orderRepository.save(o);

    }

    public void addOrderItem(OrderItem orderItem) {

        OrderItem newOrderItem = new OrderItem();

        Product product = productRepository.findByPid(orderItem.getProduct().getPid());
        User user = userRepository.findByUsername(orderItem.getUser().getUsername());

        newOrderItem.setProduct(product);
        newOrderItem.setQuantity(orderItem.getQuantity());
        newOrderItem.setStatus(orderItem.getStatus());
        newOrderItem.setUser(user);

        orderItemRepository.save(newOrderItem);
    }

}
