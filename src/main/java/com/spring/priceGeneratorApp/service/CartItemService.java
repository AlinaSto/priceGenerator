package com.spring.priceGeneratorApp.service;

import com.spring.priceGeneratorApp.dto.AddToCartDTO;
import com.spring.priceGeneratorApp.dto.UserCartDTO;
import com.spring.priceGeneratorApp.model.CartItem;
import com.spring.priceGeneratorApp.model.Product;
import com.spring.priceGeneratorApp.model.User;
import com.spring.priceGeneratorApp.repository.CartItemRepository;
import com.spring.priceGeneratorApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    private CartItemRepository cartItemRepository;
    private UserService userService;
    private ProductRepository productRepository;

    @Autowired

    public CartItemService(CartItemRepository cartItemRepository, UserService userService, ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
        this.productRepository = productRepository;
    }

    public CartItem addCartItems(AddToCartDTO addToCartDTO) {
        User foundUser = userService.findLoggedInUser();
        Product foundProduct = productRepository.findProductById(addToCartDTO.getProductId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
//        Optional<CartItem> foundCartItem = cartItemRepository.findById(cartItemId);
//        if (foundCartItem.isPresent()) {
//            updateCartItemQuantity(addToCartDTO, cartItemId);
//        }
        CartItem cartItem = new CartItem();
        cartItem.setProduct(foundProduct);
        cartItem.setUser(foundUser);
        cartItem.setQuantity(addToCartDTO.getQuantity());
        updatedProductQuantity(addToCartDTO, foundProduct);
        return cartItemRepository.save(cartItem);
    }

    private void updatedProductQuantity(AddToCartDTO addToCartDTO, Product foundProduct) {
        if (foundProduct.getProductQuantity() < addToCartDTO.getQuantity()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "there are just " + foundProduct.getProductQuantity() + " more products ");
        }
        int updatedQuantity = foundProduct.getProductQuantity() - addToCartDTO.getQuantity();
        foundProduct.setProductQuantity(updatedQuantity);
    }

    public void deleteCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " cart item you not found"));
        cartItemRepository.delete(cartItem);

    }

    public CartItem updateCartItemQuantity(AddToCartDTO addToCartDTO, Long cartItemId) {
         User foundUser = userService.findLoggedInUser();
          Product foundProduct = productRepository.findProductById(addToCartDTO.getProductId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
        CartItem foundCartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "cart item not found"));
        int updatedQuantity = getUpdatedQuantity(addToCartDTO, foundProduct, foundCartItem);
        foundProduct.setProductQuantity(updatedQuantity);
         foundCartItem.setProduct(foundProduct);
         foundCartItem.setUser(foundUser);
        return cartItemRepository.save(foundCartItem);

    }

    private int getUpdatedQuantity(AddToCartDTO addToCartDTO, Product foundProduct, CartItem foundCartItem) {
        if (foundProduct.getProductQuantity() < addToCartDTO.getQuantity()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "there are just " + foundProduct.getProductQuantity() + " more products ");
        }
        int updatedQuantity = foundProduct.getProductQuantity() - addToCartDTO.getQuantity();
        foundCartItem.setQuantity(addToCartDTO.getQuantity());
        return updatedQuantity;
    }
    public UserCartDTO viewCart(Long userId) {
        List<CartItem> cartItems = cartItemRepository.findAllByUser_Id(userId);
        UserCartDTO userCartDTO = new UserCartDTO();
        userCartDTO.setCartItemList(cartItems);
        userCartDTO.setTotalPrice(computeTotalPrice(cartItems));
        return userCartDTO;
    }

    public Double computeTotalPrice(List<CartItem> cartItems) {
        Optional<Double> totalPrice = cartItems.stream()
                .map(cartItem -> cartItem.getQuantity() * cartItem.getProduct().getProductPrice())
                .reduce(Double::sum);



        return totalPrice.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "total price could not be calculated"));
    }
}
