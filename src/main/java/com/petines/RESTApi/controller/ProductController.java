package com.petines.RESTApi.controller;

import com.petines.RESTApi.model.Product;
import com.petines.RESTApi.repositories.ProductRepository;
import com.petines.RESTApi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @RequestMapping("/products")
    public List<Product> getAllProducts()    {
         return productRepository.findAll();
    }

    @RequestMapping(method= RequestMethod.POST, value="/products")
    @ResponseBody
    public void addProduct(@RequestBody Product product) {
        productRepository.save(product);
    }

    @RequestMapping("/products/{productId}")
    public Product getProduct(@PathVariable Integer productId)
    {
        return productRepository.findByPid(productId);
    }

    @PutMapping("/products/{prodId}")
    public void updateProduct(@PathVariable int prodId, @RequestBody Product product ) {

       productService.updateProduct(prodId, product);

    }

    @PutMapping("/products")
    public void updateProducts(@RequestBody OrderItemWrapper orderItemWrapper ) {

       productService.updateProducts(orderItemWrapper);

    }

}
