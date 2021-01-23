package com.petines.RESTApi.controller;


import com.petines.RESTApi.model.Order;
import com.petines.RESTApi.model.OrderItem;
import com.petines.RESTApi.model.Product;
import com.petines.RESTApi.model.User;
import com.petines.RESTApi.repositories.OrderItemRepository;
import com.petines.RESTApi.repositories.OrderRepository;
import com.petines.RESTApi.repositories.ProductRepository;
import com.petines.RESTApi.repositories.UserRepository;
import com.petines.RESTApi.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;


    @Autowired
    OrderService orderService;

    @RequestMapping("/orders")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @RequestMapping("/oitems")
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @RequestMapping("/orderItems/cart")
    public List<Order> getAllCarts() {
        return orderRepository.findByStatus("cart");
    }

    @RequestMapping("/orders/purchased")
    public List<Order> getAllPurchasedItems() {
        return orderRepository.findByStatus("purchased");
    }


    @RequestMapping("/orders/{username}")
    public List<Order> getOrdersByUser(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        return orderRepository.findByUser(user);
    }

    @RequestMapping("/orders/orderitems/{username}")
    public List<OrderItem> getOrderItemsOfOrderByUser(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        return orderItemRepository.findPurcahsedOfUser(user);
    }

    @RequestMapping("/orders/orderitems/cart/{username}")
    public List<OrderItem> getOrderItemsOfOrderByUserAndByStatus(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        return orderItemRepository.findOrderitemsByUser(user);
    }



    @RequestMapping("/orders/purchased/{username}")
    public List<Order> getPurchasedByUsername(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        return orderRepository.findPurchasedByUser(user);
    }

    @RequestMapping("/orders/temp/{username}")
    public List<Order> getTempByUser(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        return orderRepository.findTempByUser(user);
    }

    @RequestMapping(method= RequestMethod.POST, value="/orders/{username}")
    @ResponseBody
    public void savePurcahsed(@RequestBody Order order) {

        orderService.savePurcahsed(order);

    }

    @RequestMapping(method= RequestMethod.POST, value="/defaultuser/{username}")
    @ResponseBody
    public void updateUserWithDefaultUser(@RequestBody String username) {

        User u = userRepository.findByUsername(username);
        User aDefault = userRepository.findByUsername("default");

        List<OrderItem> realUserList = orderItemRepository.findOrdersByUser(u);
        List<OrderItem> defaultList= orderItemRepository.findOrdersByUser(aDefault);

        for(OrderItem orderItem: defaultList){
            realUserList.add(orderItem);
        }
        orderItemRepository.saveAll(realUserList);
    }

    @RequestMapping("/orderItems/cart/orderItem/product/{username}")
    public List<OrderItem> getCartByUsername(@PathVariable String username) {
        User user = userRepository.findByUsername(username);

        return orderItemRepository.findCartByUser(user);
    }

    @RequestMapping("/orderItems/cart/orderItem/{username}")
    public List<OrderItem> getOrderItemByUser(@PathVariable String username) {
        User user = userRepository.findByUsername(username);

        return orderItemRepository.findOrderitemsByUser(user);
    }

    @RequestMapping(value = "/orderItems/{oiid}/{username}", method = RequestMethod.DELETE)
    public void deleteCartItem(@PathVariable("oiid") int oiid, @PathVariable("username") String username) {
        User u = userRepository.findByUsername(username);
        OrderItem oi = orderItemRepository.findById(oiid);

        OrderItem orderItem = orderItemRepository.findOrderItemByUserDelete(oi, u);
        orderItemRepository.delete(orderItem);

    }

    @PutMapping("/orderItems/cart/orderItem/{username}/purchased")
    public void purcahseCartItems(@PathVariable String username) {

        User user = userRepository.findByUsername(username);
        orderItemRepository.updateStatus(user);

    }

    @RequestMapping("/orderItems/cart/orderItem/product/{username}/{prodId}")
    public List<OrderItem> getProductFromCart(@PathVariable String username, @PathVariable String prodId) {
        User user = userRepository.findByUsername(username);
        Product product = productRepository.findByPid(Integer.parseInt(prodId));
        List<OrderItem> orderItems = orderItemRepository.findCartByUser(user);
        return orderItemRepository.findProductFromList(product);
    }

    @RequestMapping(method= RequestMethod.POST, value="/oitems")
    public void addOrderItem(@RequestBody OrderItem orderItem) {
        orderService.addOrderItem(orderItem);
    }

    @PutMapping("/orderItems/cart/orderItem/{username}/{pid}/{quantity}")
    public void updateCart(@PathVariable("username") String username,  @PathVariable("pid") int pid, @PathVariable("quantity") int quantity ) {
        User user = userRepository.findByUsername(username);
        Product product = productRepository.findByPid(pid);
        orderItemRepository.updateQuantity(product, quantity, user);
    }

    @RequestMapping("/orderItems/purchased/{username}")
    public List<OrderItem> getPurcahsed(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        return orderItemRepository.findPurcahsedOfUser(user);
    }

    @RequestMapping(value = "/orderItems/cart/{id}}", method = RequestMethod.DELETE)
    public void removeCartItem(@PathVariable int id) {
        OrderItem orderItem = orderItemRepository.findById(id);
        orderItemRepository.deleteById(id);
    }

}






