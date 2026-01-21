package hello.shoppingmall.dto;

import hello.shoppingmall.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddProductRequest {
    private String title;
    private BigDecimal price;
    private String description;
    private String category;
    private String image;

    public Product toEntity() {
        return Product.builder()
                .title(title)
                .price(price)
                .description(description)
                .category(category)
                .image(image).build();
    }
}
