package hello.shoppingmall.dto;

import hello.shoppingmall.domain.Product;
import lombok.Getter;

import java.util.List;

@Getter
public class CartRequest {

    private Long userId;
    private List<Product> products;
}
