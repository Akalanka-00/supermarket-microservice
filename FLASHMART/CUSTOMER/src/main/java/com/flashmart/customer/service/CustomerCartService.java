package com.flashmart.customer.service;

import com.flashmart.customer.dto.CartDTO;
import com.flashmart.customer.dto.CartItemDTO;
import com.flashmart.customer.dto.ItemDTO;
import com.flashmart.customer.exception.ResourceNotFoundException;
import com.flashmart.customer.model.Cart;
import com.flashmart.customer.model.CartItem;
import com.flashmart.customer.model.Customer;
import com.flashmart.customer.model.Item;
import com.flashmart.customer.repository.CustomerCartItemRepository;
import com.flashmart.customer.repository.CustomerItemRepository;
import com.flashmart.customer.repository.CustomerCartRepository;
import com.flashmart.customer.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerCartService {

    @Autowired
    private final CustomerCartRepository customerCartRepository;

    @Autowired
    private final CustomerItemRepository customerItemRepository;

    @Autowired
    private final CustomerCartItemRepository customerCartItemRepository;

    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerCartService(CustomerCartRepository customerCartRepositoryRepository, CustomerItemRepository customerItemRepository, CustomerCartItemRepository customerCartItemRepository, CustomerRepository customerRepository) {
        this.customerCartRepository = customerCartRepositoryRepository;
        this.customerItemRepository = customerItemRepository;
        this.customerCartItemRepository = customerCartItemRepository;
        this.customerRepository = customerRepository;
    }

    public List<CartDTO> getAllCarts(){
        List<Cart> carts = customerCartRepository.findAll();
        return mapCartsToDTOs(carts);
    }

    public ResponseEntity<CartDTO> getCartById(Long cartId) {
        Cart cart = customerCartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("The Cart does not exist with Cart ID: " + cartId ));
        return ResponseEntity.ok(mapCartToDTO(cart));
    }

    public ResponseEntity<CartDTO> updateCart(Long cartId, List<ItemDTO> itemDTOList) {
        Cart cart = customerCartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("The Cart does not exist with Cart ID: " + cartId ));

        List<CartItem> updatedCartItems = new ArrayList<>();

        // Map to keep track of item codes and their corresponding cart items
        Map<Long, CartItem> itemCodeToCartItemMap = new HashMap<>();
        for (CartItem cartItem : cart.getCartItems()) {
            itemCodeToCartItemMap.put(cartItem.getItem().getItemCode(), cartItem);
        }

        // Update/ add items
        for (ItemDTO itemDTO : itemDTOList) {
            long itemCode = itemDTO.getItemCode();
            int quantity = itemDTO.getQuantity();

            Item item = customerItemRepository.findById(itemCode)
                    .orElseThrow(() -> new ResourceNotFoundException("The Item does not exist with Item Code: " + itemCode));

            if (itemCodeToCartItemMap.containsKey(itemCode)) {
                CartItem existingCartItem = itemCodeToCartItemMap.get(itemCode);
                if (quantity <= existingCartItem.getItem().getQuantity()) {
                    existingCartItem.setQuantity(quantity);
                }
                updatedCartItems.add(existingCartItem);

            } else {
                CartItem cartItem = new CartItem();
                cartItem.setItem(item);
                cartItem.setCart(cart);
                if (quantity <= item.getQuantity()) {
                    cartItem.setQuantity(quantity);
                } else {
                    cartItem.setQuantity(item.getQuantity());
                }
                updatedCartItems.add(cartItem);
            }
        }

        double newTotalPrice = updatedCartItems.stream()
                .mapToDouble(cartItem -> cartItem.getQuantity() * cartItem.getItem().getPrice())
                .sum();
        int newNoOfItem = updatedCartItems.size();

        cart.setCartItems(updatedCartItems);
        cart.setTotalPrice(newTotalPrice);
        cart.setNoOfItem(newNoOfItem);

        Cart updatedCart = customerCartRepository.save(cart);
        CartDTO updatedCartDTO = mapCartToDTO(updatedCart);

        return ResponseEntity.ok(updatedCartDTO);
    }

    public ResponseEntity<CartDTO> updateCartOneItem(Long cartId, Long itemCode) {
        Cart cart = customerCartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("The Cart does not exist with Cart ID: " + cartId ));

        List<CartItem> updatedCartItems = new ArrayList<>();

        // Map to keep track of item codes and their corresponding cart items
        Map<Long, CartItem> itemCodeToCartItemMap = new HashMap<>();
        for (CartItem cartItem : cart.getCartItems()) {
            itemCodeToCartItemMap.put(cartItem.getItem().getItemCode(), cartItem);
        }

        // Update/ add items

        Item item = customerItemRepository.findById(itemCode)
                .orElseThrow(() -> new ResourceNotFoundException("The Item does not exist with Item Code: " + itemCode));

        if (itemCodeToCartItemMap.containsKey(itemCode)) {
            CartItem existingCartItem = itemCodeToCartItemMap.get(itemCode);
            if (existingCartItem.getQuantity() + 1 <= item.getQuantity()) {
                existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
            }
            updatedCartItems.add(existingCartItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setItem(item);
            cartItem.setCart(cart);
            cartItem.setQuantity(1);
            updatedCartItems.add(cartItem);
        }

        double newTotalPrice = updatedCartItems.stream()
                .mapToDouble(cartItem -> cartItem.getQuantity() * cartItem.getItem().getPrice())
                .sum();
        int newNoOfItem = updatedCartItems.size();

        cart.setCartItems(updatedCartItems);
        cart.setTotalPrice(newTotalPrice);
        cart.setNoOfItem(newNoOfItem);

        Cart updatedCart = customerCartRepository.save(cart);
        CartDTO updatedCartDTO = mapCartToDTO(updatedCart);

        return ResponseEntity.ok(updatedCartDTO);
    }

    public ResponseEntity<CartDTO> updateCartItemByOne(Long cartId, Long itemCode) {
        Cart cart = customerCartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("The Cart does not exist with Cart ID: " + cartId));

        List<CartItem> updatedCartItems = new ArrayList<>();

        Map<Long, CartItem> itemCodeToCartItemMap = new HashMap<>();
        for (CartItem cartItem : cart.getCartItems()) {
            itemCodeToCartItemMap.put(cartItem.getItem().getItemCode(), cartItem);
        }

        Item item = customerItemRepository.findById(itemCode)
                .orElseThrow(() -> new ResourceNotFoundException("The Item does not exist with Item Code: " + itemCode));

        if (itemCodeToCartItemMap.containsKey(itemCode)) {
            CartItem existingCartItem = itemCodeToCartItemMap.get(itemCode);
            if (existingCartItem.getQuantity() + 1 <= item.getQuantity()) {
                existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
            }
            updatedCartItems.add(existingCartItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setItem(item);
            cartItem.setCart(cart);
            cartItem.setQuantity(1);
            updatedCartItems.add(cartItem);
        }

        double newTotalPrice = updatedCartItems.stream()
                .mapToDouble(cartItem -> cartItem.getQuantity() * cartItem.getItem().getPrice())
                .sum();
        int newNoOfItem = updatedCartItems.size();

        cart.setCartItems(updatedCartItems);
        cart.setTotalPrice(newTotalPrice);
        cart.setNoOfItem(newNoOfItem);

        Cart updatedCart = customerCartRepository.save(cart);
        CartDTO updatedCartDTO = mapCartToDTO(updatedCart);

        return ResponseEntity.ok(updatedCartDTO);
    }

    @Transactional
    public ResponseEntity<CartDTO> updateCartbyDeletingItem(Long cartId, List<Long> itemCodes) {

        Cart cart = customerCartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("The Cart does not exist with Cart ID: " + cartId));

        List<CartItem> cartItems = cart.getCartItems();

        List<CartItem> updatedCartItems = new ArrayList<>();

        for (Long itemCode : itemCodes) {
            CartItem cartItemToRemove = null;
            for (CartItem cartItem : cartItems) {
                if (cartItem.getItem().getItemCode() == itemCode) {
                    cartItemToRemove = cartItem;
                    break;
                }
            }

            if (cartItemToRemove != null) {
                cartItems.remove(cartItemToRemove);
                customerCartItemRepository.delete(cartItemToRemove);
            }
        }

        double newTotalPrice = updatedCartItems.stream()
                .mapToDouble(cartItem -> cartItem.getQuantity() * cartItem.getItem().getPrice())
                .sum();
        int newNoOfItem = updatedCartItems.size();

        cart.setCartItems(updatedCartItems);
        cart.setTotalPrice(newTotalPrice);
        cart.setNoOfItem(newNoOfItem);

        Cart updatedCart = customerCartRepository.save(cart);
        CartDTO updatedCartDTO = mapCartToDTO(updatedCart);

        return ResponseEntity.ok(updatedCartDTO);
    }

    @Transactional
    public ResponseEntity<CartDTO> updateCartbyDeletingOneItem(Long cartId, Long itemCode) {

        Cart cart = customerCartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("The Cart does not exist with Cart ID: " + cartId));
        List<CartItem> cartItems = cart.getCartItems();
        List<CartItem> updatedCartItems = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            if (cartItem.getItem().getItemCode() == itemCode) {
                cartItems.remove(cartItem);
                customerCartItemRepository.delete(cartItem);
                break;
            }
        }

        double newTotalPrice = updatedCartItems.stream()
                .mapToDouble(cartItem -> cartItem.getQuantity() * cartItem.getItem().getPrice())
                .sum();
        int newNoOfItem = updatedCartItems.size();

        cart.setCartItems(updatedCartItems);
        cart.setTotalPrice(newTotalPrice);
        cart.setNoOfItem(newNoOfItem);

        Cart updatedCart = customerCartRepository.save(cart);
        CartDTO updatedCartDTO = mapCartToDTO(updatedCart);

        return ResponseEntity.ok(updatedCartDTO);
    }

    public ResponseEntity<CartDTO> updateCartItemDecreasingByOne(Long cartId, Long itemCode) {
        Cart cart = customerCartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("The Cart does not exist with Cart ID: " + cartId));

        List<CartItem> updatedCartItems = new ArrayList<>();
        Map<Long, CartItem> itemCodeToCartItemMap = new HashMap<>();
        for (CartItem cartItem : cart.getCartItems()) {
            itemCodeToCartItemMap.put(cartItem.getItem().getItemCode(), cartItem);
        }

        Item item = customerItemRepository.findById(itemCode)
                .orElseThrow(() -> new ResourceNotFoundException("The Item does not exist with Item Code: " + itemCode));

        if (itemCodeToCartItemMap.containsKey(itemCode)) {
            CartItem existingCartItem = itemCodeToCartItemMap.get(itemCode);
            if (existingCartItem.getQuantity() -1 >= 0) {
                existingCartItem.setQuantity(existingCartItem.getQuantity() - 1);
            }
            updatedCartItems.add(existingCartItem);
        }

        double newTotalPrice = updatedCartItems.stream()
                .mapToDouble(cartItem -> cartItem.getQuantity() * cartItem.getItem().getPrice())
                .sum();
        int newNoOfItem = updatedCartItems.size();

        cart.setCartItems(updatedCartItems);
        cart.setTotalPrice(newTotalPrice);
        cart.setNoOfItem(newNoOfItem);

        Cart updatedCart = customerCartRepository.save(cart);
        CartDTO updatedCartDTO = mapCartToDTO(updatedCart);

        return ResponseEntity.ok(updatedCartDTO);
    }


    public CartDTO mapCartToDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(cart.getCartId());

        List<CartItemDTO> itemDTOList = new ArrayList<>();
        Set<Long> itemCodes = new HashSet<>();
        double totalPrice = 0.0;

        for (CartItem cartItem : cart.getCartItems()) {
            CartItemDTO cartItemDTO = new CartItemDTO();
            cartItemDTO.setItemCode(cartItem.getItem().getItemCode());
            cartItemDTO.setItemName(cartItem.getItem().getItemName());
            cartItemDTO.setPrice(cartItem.getItem().getPrice());
            cartItemDTO.setQuantity(cartItem.getQuantity());
            itemDTOList.add(cartItemDTO);

            itemCodes.add(cartItem.getItem().getItemCode());
            totalPrice += cartItem.getQuantity() * cartItem.getItem().getPrice();
        }

        int noOfItem = itemCodes.size();
        cartDTO.setNoOfItem(noOfItem);
        cartDTO.setTotalPrice(totalPrice);
        cartDTO.setCustomerId(cart.getCustomer().getCustomerId());
        cartDTO.setItems(itemDTOList);

        return cartDTO;
    }

    public List<CartDTO> mapCartsToDTOs(List<Cart> carts) {
        List<CartDTO> cartDTOs = new ArrayList<>();
        for (Cart cart : carts) {
            cartDTOs.add(mapCartToDTO(cart));
        }
        return cartDTOs;
    }

    public List<String> checkCartAvailability(Long customerId ) {
        List<String> unavailableItems = new ArrayList<>();
        List<String> allItemsAvailable = new ArrayList<>();
        Customer customer = customerRepository.findById(customerId ).orElse(null);
        CartDTO cartDTO = getCartById(customer.getCart().getCartId()).getBody();
        List<CartItemDTO> cartItemsDTOS = cartDTO.getItems();

        for (CartItemDTO cartItemDTO : cartItemsDTOS) {

            Item item = customerItemRepository.findById(cartItemDTO.getItemCode()).orElse(null);
            if (item == null) {
                unavailableItems.add(item.getItemName() + ": Item not found in inventory");
            } else if (item.getQuantity() < cartItemDTO.getQuantity()) {
                unavailableItems.add(item.getItemName() + ": Insufficient quantity in inventory");
            }
        }

        if (!unavailableItems.isEmpty()) {
            return unavailableItems;
        }
        allItemsAvailable.add("All Items are Available. Proceed to checkout");
        return allItemsAvailable;
    }

}


