package com.petines.RESTApi.controller;

import com.petines.RESTApi.model.OrderItem;

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
