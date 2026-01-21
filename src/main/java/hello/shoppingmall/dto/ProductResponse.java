package hello.shoppingmall.dto;

import hello.shoppingmall.domain.Product;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductResponse {
    private final Long id;
    private final String title;
    private final BigDecimal price;
    private final String description;
    private final String category;
    private final String image;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.category = product.getCategory();
        this.image = product.getImage();
    }
}
