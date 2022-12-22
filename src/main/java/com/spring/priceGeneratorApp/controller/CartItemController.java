package com.spring.priceGeneratorApp.controller;

import com.spring.priceGeneratorApp.dto.AddToCartDTO;
import com.spring.priceGeneratorApp.dto.UserCartDTO;
import com.spring.priceGeneratorApp.model.CartItem;
import com.spring.priceGeneratorApp.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartItemController {

    private CartItemService cartItemService;

    @Autowired
    CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping("/add")
    public CartItem addCartItem(@RequestBody AddToCartDTO addToCartDTO) {
        //iau utilizatorul log at
        //imi caut produsul daca nuil gasesc arunc exceptie, daca il gasesc il adaug in cos cu cantitatea dorita
        //la produsul existent ii updatez valoare in functie de ce am trimis eu in request
        //
        return cartItemService.addCartItems(addToCartDTO);

    }

    @DeleteMapping("/delete/{cartItemId}")
    public void deleteCartItem(@PathVariable Long cartItemId) {
        cartItemService.deleteCartItem(cartItemId);
    }

    @PutMapping("/update/{cartItemId}")
    public CartItem updateCartItemQuantity( @RequestBody AddToCartDTO addToCartDTO,@PathVariable Long cartItemId){
        return cartItemService.updateCartItemQuantity(addToCartDTO,cartItemId);
    }
    @GetMapping("/{userId}")
    public UserCartDTO viewCart(@PathVariable Long userId){
        return cartItemService.viewCart(userId);
    }
}
