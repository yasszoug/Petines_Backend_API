package com.project.stylealpha.stylealpha.controller;

import com.project.stylealpha.stylealpha.model.OrderItem;

import javax.persistence.Entity;
import java.util.List;


public class OrderItemWrapper {

    List<OrderItem> orderItemList;

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
