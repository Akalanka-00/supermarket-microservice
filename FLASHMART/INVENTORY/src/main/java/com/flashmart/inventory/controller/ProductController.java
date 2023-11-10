package com.flashmart.inventory.controller;

import com.flashmart.inventory.dto.ProductDTO;
import com.flashmart.inventory.model.Product;
import com.flashmart.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("api/inventory")
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

    @GetMapping("/productById/{id}")
    public Product findProductById(@PathVariable Long id){
        return service.getProductId(id);
    }

    @GetMapping("/productBySKU/{skuCode}")
    public Product findProductBySkuCode(@PathVariable String skuCode){
        return service.getProductSkuCode(skuCode);
    }

    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product){            //post methods
        return  service.updateProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        return service.deleteProduct(id);
    }

    @PostMapping("/decrease")
    public void decreaseItemQuantities(@RequestBody List<ProductDTO> products) {
        service.decreaseItemQuantities(products);
    }
}
