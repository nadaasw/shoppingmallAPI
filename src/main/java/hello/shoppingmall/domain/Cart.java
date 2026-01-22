package hello.shoppingmall.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
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
    private List<Product> products = new ArrayList<>();


}
