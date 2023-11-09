package com.flashmart.customer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flashmart.customer.dto.CartDTO;
import com.flashmart.customer.dto.CartItemDTO;
import com.flashmart.customer.dto.ItemDTO;
import com.flashmart.customer.dto.ProductDTO;
import com.flashmart.customer.exception.ResourceNotFoundException;
import com.flashmart.customer.model.Cart;
import com.flashmart.customer.model.CartItem;
import com.flashmart.customer.repository.CustomerCartItemRepository;
import com.flashmart.customer.repository.CustomerCartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@Service
public class CustomerCartService {

    @Autowired
    private final CustomerCartRepository customerCartRepository;

    @Autowired
    private final CustomerCartItemRepository customerCartItemRepository;

    public CustomerCartService(CustomerCartRepository customerCartRepositoryRepository, CustomerCartItemRepository customerCartItemRepository) {
        this.customerCartRepository = customerCartRepositoryRepository;
        this.customerCartItemRepository = customerCartItemRepository;
    }

    public String setCart(Long customerId){
        List<CartDTO> cartDTOS = getAllCarts();
        for(CartDTO cartDTO:cartDTOS) {
          if(cartDTO.getCustomerId() == customerId){
              return "Cart is already added to " + customerId;
          }
        }
        Cart cart = new Cart();
        cart.setCartId(customerId);
        cart.setCustomerId(customerId);
        cart.setNoOfItem(0);
        cart.setTotalPrice(0);
        customerCartRepository.save(cart);
        return "New Cart added to " + customerId;
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

    public ResponseEntity<CartDTO> getCartByCustomerId(Long customerId) {
        List<CartDTO> cartDTOS = getAllCarts();
        for(CartDTO cartDTO:cartDTOS){
            if(cartDTO.getCustomerId() == customerId){
                long cartId = cartDTO.getCartId();
                Cart cart = customerCartRepository.findById(cartId)
                        .orElseThrow(() -> new ResourceNotFoundException("The Cart does not exist with Cart ID: " + cartId ));
                return ResponseEntity.ok(mapCartToDTO(cart));
            }
        }
        return null;
    }

    public ResponseEntity<CartDTO> updateCart(Long cartId, List<ItemDTO> itemDTOList) {
        Cart cart = customerCartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("The Cart does not exist with Cart ID: " + cartId ));

        List<CartItem> updatedCartItems = new ArrayList<>();

        // Map to keep track of item codes and their corresponding cart items
        Map<Long, CartItem> itemCodeToCartItemMap = new HashMap<>();
        for (CartItem cartItem : cart.getCartItems()) {
            itemCodeToCartItemMap.put(cartItem.getItemCode(), cartItem);
        }

        // Update/ add items
        for (ItemDTO itemDTO : itemDTOList) {
            long itemCode = itemDTO.getItemCode();
            int quantity = itemDTO.getQuantity();

            try {
                ProductDTO productDTO = fetchAPI("http://localhost:8082/api/inventory/productById",itemCode, ProductDTO.class);

                if (itemCodeToCartItemMap.containsKey(itemCode)) {
                    CartItem existingCartItem = itemCodeToCartItemMap.get(itemCode);
                    if (quantity <= productDTO.getNoOfProducts()) {
                        existingCartItem.setQuantity(quantity);
                    }
                    updatedCartItems.add(existingCartItem);

                } else {
                    CartItem cartItem = new CartItem();
                    cartItem.setItemCode(productDTO.getItemCode());
                    cartItem.setCart(cart);
                    if (quantity <= productDTO.getNoOfProducts()) {
                        cartItem.setQuantity(quantity);
                    } else {
                        cartItem.setQuantity(productDTO.getNoOfProducts());
                    }
                    updatedCartItems.add(cartItem);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        double newTotalPrice = updatedCartItems.stream()
                .mapToDouble(cartItem -> {
                    try {
                        return cartItem.getQuantity() *
                                fetchAPI("http://localhost:8082/api/inventory/productById", cartItem.getItemCode(), ProductDTO.class).getPrice();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
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

        List<CartItem> updatedCartItems = cart.getCartItems();

        // Map to keep track of item codes and their corresponding cart items
        Map<Long, CartItem> itemCodeToCartItemMap = new HashMap<>();
        for (CartItem cartItem : cart.getCartItems()) {
            itemCodeToCartItemMap.put(cartItem.getItemCode(), cartItem);
        }

        // Update/ add items
        try {
            ProductDTO productDTO = fetchAPI("http://localhost:8082/api/inventory/productById", itemCode, ProductDTO.class);

            if (itemCodeToCartItemMap.containsKey(itemCode)) {
                CartItem existingCartItem = itemCodeToCartItemMap.get(itemCode);
                if (existingCartItem.getQuantity() + 1 <= productDTO.getNoOfProducts()) {
                    existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
                }
            } else {
                CartItem cartItem = new CartItem();
                cartItem.setItemCode(productDTO.getItemCode());
                cartItem.setCart(cart);
                cartItem.setQuantity(1);
                updatedCartItems.add(cartItem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        double newTotalPrice = updatedCartItems.stream()
                .mapToDouble(cartItem -> {
                    try {
                        return cartItem.getQuantity() *
                                fetchAPI("http://localhost:8082/api/inventory/productById", cartItem.getItemCode(), ProductDTO.class).getPrice();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
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

        List<CartItem> updatedCartItems = cart.getCartItems();

        Map<Long, CartItem> itemCodeToCartItemMap = new HashMap<>();
        for (CartItem cartItem : cart.getCartItems()) {
            itemCodeToCartItemMap.put(cartItem.getItemCode(), cartItem);
        }

        try {
            ProductDTO productDTO = fetchAPI("http://localhost:8082/api/inventory/productById",itemCode, ProductDTO.class);

            if (itemCodeToCartItemMap.containsKey(itemCode)) {
                CartItem existingCartItem = itemCodeToCartItemMap.get(itemCode);
                if (existingCartItem.getQuantity() + 1 <= productDTO.getNoOfProducts()) {
                    existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
                }
            } else {
                CartItem cartItem = new CartItem();
                cartItem.setItemCode(productDTO.getItemCode());
                cartItem.setCart(cart);
                cartItem.setQuantity(1);
                updatedCartItems.add(cartItem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        double newTotalPrice = updatedCartItems.stream()
                .mapToDouble(cartItem -> {
                    try {
                        return cartItem.getQuantity() *
                                fetchAPI("http://localhost:8082/api/inventory/productById", cartItem.getItemCode(), ProductDTO.class).getPrice();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
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

        List<CartItem> updatedCartItems = cart.getCartItems();

        for (Long itemCode : itemCodes) {
            CartItem cartItemToRemove = null;
            for (CartItem cartItem : cartItems) {
                if (cartItem.getItemCode() == itemCode) {
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
                .mapToDouble(cartItem -> {
                    try {
                        return cartItem.getQuantity() *
                                fetchAPI("http://localhost:8082/api/inventory/productById", cartItem.getItemCode(), ProductDTO.class).getPrice();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
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
        List<CartItem> updatedCartItems = cart.getCartItems();

        for (CartItem cartItem : cartItems) {
            if (cartItem.getItemCode() == itemCode) {
                cartItems.remove(cartItem);
                customerCartItemRepository.delete(cartItem);
                break;
            }
        }

        double newTotalPrice = updatedCartItems.stream()
                .mapToDouble(cartItem -> {
                    try {
                        return cartItem.getQuantity() *
                                fetchAPI("http://localhost:8082/api/inventory/productById", cartItem.getItemCode(), ProductDTO.class).getPrice();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
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

        Map<Long, CartItem> itemCodeToCartItemMap = new HashMap<>();
        for (CartItem cartItem : cart.getCartItems()) {
            itemCodeToCartItemMap.put(cartItem.getItemCode(), cartItem);
        }

        if (itemCodeToCartItemMap.containsKey(itemCode)) {
            CartItem existingCartItem = itemCodeToCartItemMap.get(itemCode);
            if (existingCartItem.getQuantity() -1 >= 0) {
                existingCartItem.setQuantity(existingCartItem.getQuantity() - 1);
            }
            if (existingCartItem.getQuantity() == 0) {
                updateCartbyDeletingOneItem(cartId,itemCode);
            }
        }
        List<CartItem> updatedCartItems = cart.getCartItems();
        double newTotalPrice = updatedCartItems.stream()
                .mapToDouble(cartItem -> {
                    try {
                        return cartItem.getQuantity() *
                                fetchAPI("http://localhost:8082/api/inventory/productById", cartItem.getItemCode(), ProductDTO.class).getPrice();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
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
            try {
                ProductDTO productDTO = fetchAPI("http://localhost:8082/api/inventory/productById",cartItem.getItemCode(), ProductDTO.class);
                CartItemDTO cartItemDTO = new CartItemDTO();
                cartItemDTO.setItemCode(cartItem.getItemCode());
                cartItemDTO.setItemName(productDTO.getItemName());
                cartItemDTO.setPrice(productDTO.getPrice());
                cartItemDTO.setQuantity(cartItem.getQuantity());
                itemDTOList.add(cartItemDTO);

                itemCodes.add(productDTO.getItemCode());
                totalPrice += cartItem.getQuantity() * productDTO.getPrice();
            } catch (IOException e) {
                // Handle the IOException, log it, or provide an error message
                e.printStackTrace();
            }
        }

        int noOfItem = itemCodes.size();
        cartDTO.setNoOfItem(noOfItem);
        cartDTO.setTotalPrice(totalPrice);
        cartDTO.setCustomerId(cart.getCustomerId());
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

    public static <T> T fetchAPI(URL url, Class<T> type) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(url, type);
    }

    public static <T> T fetchAPI(String baseUrl, Long pathVariable, Class<T> type) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String fullUrl = baseUrl + "/" + pathVariable;
        try {
            URL url = new URL(fullUrl);
            return mapper.readValue(url, type);
        } catch (MalformedURLException e) {
            throw new IOException("Malformed URL: " + fullUrl, e);
        }
    }

}


