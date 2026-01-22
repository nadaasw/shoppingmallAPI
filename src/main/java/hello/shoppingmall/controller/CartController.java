package hello.shoppingmall.controller;

import hello.shoppingmall.domain.Cart;
import hello.shoppingmall.dto.CartRequest;
import hello.shoppingmall.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/carts")
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();

        return ResponseEntity.ok().body(carts);
    }

    @PostMapping("/carts")
    public ResponseEntity<Cart> createCart(@RequestBody CartRequest request) {
        Cart savedCart = cartService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCart);
    }

    @GetMapping("/carts/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable Long id) {
        Cart cart = cartService.findById(id);
        return ResponseEntity.ok().body(cart);
    }

    @PutMapping("/carts/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody CartRequest request) {
        Cart cart = cartService.update(id, request);

        return ResponseEntity.ok().body(cart);
    }

    @DeleteMapping("/carts/{id}")
    public ResponseEntity<Cart> deleteCart(@PathVariable Long id) {
        cartService.delete(id);

        return ResponseEntity.ok().build();
    }

}
