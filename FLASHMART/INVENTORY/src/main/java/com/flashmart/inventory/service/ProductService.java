package com.flashmart.inventory.service;

import com.flashmart.inventory.model.Product;
import com.flashmart.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product){                //post method     //save single product
        return repository.save(product);
    }
    public List<Product> saveProducts(List<Product> products){    //save list of products
        return repository.saveAll(products);
    }

    public List<Product> getProducts(){   //get method
        return repository.findAll();
    }

    public Product getProductId(Long id){
        return repository.findById(id).orElse(null);    //return by Id
    }

    public Product getProductSkuCode(String skuCode){   //return by sku code
        return repository.findBySkuCode(skuCode);      //manually written method. So this method is written in product repository
    }

    public String deleteProduct(Long id){
        repository.deleteById(id);
        return "product removed" +id;
    }

    public Product updateProduct(Product product){
        Product existingProduct=repository.findById(product.getItemCode()).orElse(null);
        existingProduct.setSkuCode(product.getSkuCode());
        existingProduct.setItemName(product.getItemName());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategoryId(product.getCategoryId());
        existingProduct.setCategoryName(product.getCategoryName());
        existingProduct.setNoOfProducts(product.getNoOfProducts());

        return repository.save(existingProduct);
    }
}
