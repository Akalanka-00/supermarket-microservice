package com.flashmart.inventory.controller;

import com.flashmart.inventory.model.Product;
import com.flashmart.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {




        @Autowired
        private ProductService service;

        @PostMapping("/addProduct")
        public Product addProduct(@RequestBody Product product){            //post methods
            return  service.saveProduct(product);
        }

        @PostMapping("/addProducts")
        public List<Product> addProducts(@RequestBody List<Product> products){
            return service.saveProducts(products);
        }

        @GetMapping("/products")
        public List<Product> findAllProducts(){
            return service.getProducts();
        }

        @GetMapping("/product/{id}")
        public Product findProductById(@PathVariable int id){
            return service.getProductId(id);
        }

        @GetMapping("/product/{skuCode}")
        public Product findProductBySkuCode(@PathVariable String skuCode){
            return service.getProductSkuCode(skuCode);
        }

        @PutMapping("/update")
        public Product updateProduct(@RequestBody Product product){            //post methods
            return  service.updateProduct(product);
        }

        @DeleteMapping("/delete/{id}")
        public String deleteProduct(@PathVariable int id){
            return service.deleteProduct(id);
        }


    }
