package com.example.api_server.controller.rest;

import com.example.api_server.controller.services.ProductServices;
import com.example.api_server.model.MyRequest;
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
    public ResponseEntity<?> addProduct(@RequestBody MyRequest<Product> requestBody) {
        String token = requestBody.getToken();
        Product product = requestBody.getData();

        boolean isSaved = productServices.save(token, product);
        if (isSaved) {
            return new ResponseEntity<>(ResponseEntity.ok(product), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(
            value = "/products/addList",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> addListProduct(@RequestBody MyRequest<List<Product>> requestBody) {
        String token = requestBody.getToken();
        List<Product> products = requestBody.getData();

        boolean isSaved = productServices.saveAll(token, products);
        if (isSaved) {
            return new ResponseEntity<>(ResponseEntity.ok(products), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(
            value = "/products/delete",
            method = RequestMethod.POST
    )
    public ResponseEntity<?> removeProduct(@RequestBody MyRequest<Long> requestBody) {
        String token = requestBody.getToken();
        Long id = requestBody.getData();

        boolean isDeleted = productServices.deleteById(token, id);
        if (isDeleted) {
            return new ResponseEntity<>(ResponseEntity.ok(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
    }
}
