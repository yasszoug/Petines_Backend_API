package com.project.stylealpha.stylealpha.services;

import com.project.stylealpha.stylealpha.controller.OrderItemWrapper;
import com.project.stylealpha.stylealpha.model.OrderItem;
import com.project.stylealpha.stylealpha.model.Product;
import com.project.stylealpha.stylealpha.repositories.OrderItemRepository;
import com.project.stylealpha.stylealpha.repositories.OrderRepository;
import com.project.stylealpha.stylealpha.repositories.ProductRepository;
import com.project.stylealpha.stylealpha.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.xml.ws.ServiceMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;


    public void updateProduct(int prodId, Product product ) {

        Product mProduct = productRepository.findByPid(prodId);

        mProduct.setPrice(product.getPrice());
         mProduct.setQuantity(product.getQuantity());

        productRepository.save(mProduct);

    }

    public void updateProducts( OrderItemWrapper orderItemWrapper ) {
        Product product;
        for (OrderItem orderItem : orderItemWrapper.getOrderItemList()) {

             product = productRepository.findByPid(orderItem.getProduct().getPid());

            int oldQuantity = product.getQuantity();
            int newQuantity = orderItem.getQuantity();
            int updatedQuantity = oldQuantity - newQuantity;

            product.setPrice(orderItem.getProduct().getPrice());
            product.setProdImage(orderItem.getProduct().getProdImage());
            product.setProdStock(orderItem.getProduct().getProdStock());
            product.setProductName(orderItem.getProduct().getProductName());
            product.setStatus(orderItem.getProduct().getStatus());
            product.setProd_desc(orderItem.getProduct().getProd_desc());
            String sUpdatedQuantity = String.valueOf(updatedQuantity);

            product.setQuantity(updatedQuantity);

            productRepository.save(product);

        }
    }
}
