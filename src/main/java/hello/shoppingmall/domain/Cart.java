package hello.shoppingmall.domain;

import hello.shoppingmall.dto.CartRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", updatable = false)
    private Long cartId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cart_product",
            joinColumns = @JoinColumn(name="cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;


    @Builder
    public Cart(Long userId, List<Product> products) {
        this.userId = userId;
        this.products = products;
    }

    public void update(CartRequest cartRequest) {
        this.userId = cartRequest.getUserId();
        this.products = cartRequest.getProducts();
    }
}
