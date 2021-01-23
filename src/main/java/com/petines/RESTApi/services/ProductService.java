package com.petines.RESTApi.services;

import com.petines.RESTApi.controller.OrderItemWrapper;
import com.petines.RESTApi.model.OrderItem;
import com.petines.RESTApi.model.Product;
import com.petines.RESTApi.repositories.OrderItemRepository;
import com.petines.RESTApi.repositories.OrderRepository;
import com.petines.RESTApi.repositories.ProductRepository;
import com.petines.RESTApi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
