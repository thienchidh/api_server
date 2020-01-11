package com.example.api_server.controller.rest;

import com.example.api_server.controller.services.ProductServices;
import com.example.api_server.model.Product;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class ProductManagerController {
    private static final Logger logger = LoggerFactory.getLogger(ProductManagerController.class);

    private ProductServices productServices;

    @RequestMapping(value = "/products")
    public ResponseEntity<?> fetchAllProducts(@RequestParam(defaultValue = "0") Integer page,
                                              @RequestParam(defaultValue = "30") Integer limit) {
        List<Product> products = productServices.findAll(PageRequest.of(page, limit)).getContent();
        return new ResponseEntity<>(ResponseEntity.ok(products), HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Optional<Product> product = productServices.findById(id);
        return product.map(value -> new ResponseEntity<>(ResponseEntity.ok(value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND));
    }

    @RequestMapping(
            value = "/products/add",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> addProduct(@RequestBody String token, @RequestBody Product product) {
        productServices.save(token, product);
        return new ResponseEntity<>(ResponseEntity.ok(product), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/products/delete",
            method = RequestMethod.POST
    )
    public ResponseEntity<?> removeProduct(@RequestBody String token, @RequestBody Long ID) {
        productServices.deleteById(token, ID);
        return new ResponseEntity<>(ResponseEntity.ok(ID), HttpStatus.OK);
    }
}
