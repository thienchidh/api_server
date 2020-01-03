package com.example.api_server.controller;

import com.example.api_server.data_source.dao.ProductDAOImpl;
import com.example.api_server.model.Product;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductManagerController {
    private static final Logger logger = Logger.getLogger(ProductManagerController.class);

    private ProductDAOImpl productDAO;

    @Autowired
    public ProductManagerController(ProductDAOImpl productDAO) {
        this.productDAO = productDAO;
    }

    @RequestMapping(value = "/products",
            method = RequestMethod.GET
    )
    public ResponseEntity<List<Product>> fetchAllProducts() {
        List<Product> products = productDAO.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        Optional<Product> product = productDAO.findById(id);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.OK));
    }

    @RequestMapping(value = "/products/add",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> addProduct(@RequestBody Product product) {
        //TODO
        productDAO.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/products/delete",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> removeProduct(@RequestBody long ID) {
        productDAO.deleteById(ID);
        return new ResponseEntity<>(HttpStatus.OK, HttpStatus.OK);
    }
}
