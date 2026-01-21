package hello.shoppingmall.dto;

import hello.shoppingmall.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateProductRequest {
    private String title;
    private BigDecimal price;
    private String description;
    private String category;
    private String image;


}
