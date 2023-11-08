package com.flashmart.customer.controller;

import com.flashmart.customer.dto.CartDTO;
import com.flashmart.customer.dto.ItemDTO;
import com.flashmart.customer.service.CustomerCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("api/v1/cart")
public class CustomerCartController {

    @Autowired
    private CustomerCartService customerCartService;

    //get all cart details
    @GetMapping("/getCart")
    public List<CartDTO> getAllCarts(){
        return customerCartService.getAllCarts();
    }

    // get cart by id
    @GetMapping("/getCart/{cartId}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable Long cartId){
        return customerCartService.getCartById(cartId);
    }

    //update cart by adding multiple Items
    @PutMapping("/addItem/{cartId}")
    public ResponseEntity<CartDTO> updateCart(@PathVariable Long cartId, @RequestBody List<ItemDTO> itemDTO){
        return customerCartService.updateCart(cartId, itemDTO);
    }

    //update cart by adding one cart Item
    @PutMapping("/addItem/{cartId}/{itemCode}")
    public ResponseEntity<CartDTO> updateCartOneItem(@PathVariable Long cartId, @PathVariable Long itemCode){
        return customerCartService.updateCartOneItem(cartId, itemCode);
    }

    //update cart by increasing specific item one by one
    @PutMapping("/increaseItem/{cartId}/{itemCode}")
    public ResponseEntity<CartDTO> updateCartItemByOne(@PathVariable Long cartId, @PathVariable Long itemCode){
        return customerCartService.updateCartItemByOne(cartId, itemCode);
    }

    //    update cart by deleting cart items
    @PutMapping("/deleteItem/{cartId}")
    public ResponseEntity<CartDTO> updateCartbyDeletingItem(@PathVariable Long cartId, @RequestBody List<Long> itemCodes){
        return customerCartService.updateCartbyDeletingItem(cartId, itemCodes);
    }

    //    update cart by deleting cart items by one
    @PutMapping("/deleteItem/{cartId}/{itemCode}")
    public ResponseEntity<CartDTO> updateCartbyDeletingOneItem(@PathVariable Long cartId, @PathVariable Long itemCode){
        return customerCartService.updateCartbyDeletingOneItem(cartId, itemCode);
    }

    //update cart by decreasing specific item one by one
    @PutMapping("/decreaseItem/{cartId}/{itemCode}")
    public ResponseEntity<CartDTO> updateCartItemDecreasingByOne(@PathVariable Long cartId, @PathVariable Long itemCode){
        return customerCartService.updateCartItemDecreasingByOne(cartId, itemCode);
    }

    //check availability of cart items with inventory
    @GetMapping("/checkAvailability/{customerId}")
    public List<String> checkCartAvailability( @PathVariable Long customerId) {
        return customerCartService.checkCartAvailability(customerId);
    }

}
