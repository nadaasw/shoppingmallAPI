package hello.shoppingmall.service;

import hello.shoppingmall.domain.Cart;
import hello.shoppingmall.dto.CartRequest;
import hello.shoppingmall.repository.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public Cart save(CartRequest cartRequest) {
        Cart cart = Cart.builder()
                .userId(cartRequest.getUserId())
                .products(cartRequest.getProducts())
                .build();

        return cartRepository.save(cart);
    }

    public List<Cart> getAllCarts(){
        return cartRepository.findAll();
    }

    public Cart findById(Long id){
        return cartRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found  " + id ));
    }

    public void delete(Long id){
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found  " + id ));

        cartRepository.delete(cart);
    }

    @Transactional
    public Cart update(Long id, CartRequest cartRequest) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found  " + id ));

        cart.update(cartRequest);
        return cart;
    }
}
