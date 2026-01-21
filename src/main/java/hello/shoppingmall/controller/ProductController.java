package hello.shoppingmall.controller;

import hello.shoppingmall.domain.Product;
import hello.shoppingmall.dto.AddProductRequest;
import hello.shoppingmall.dto.ProductResponse;
import hello.shoppingmall.dto.UpdateProductRequest;
import hello.shoppingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        List<ProductResponse> products = productService.findAll()
                .stream()
                .map(ProductResponse::new)
                .toList();
        return ResponseEntity.ok().body(products);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody AddProductRequest request){
        Product product = productService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id){
        Product product = productService.findById(id);
        return ResponseEntity.ok().body(new ProductResponse(product));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        productService.delete(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody UpdateProductRequest request){
        Product product = productService.update(id, request);

        return ResponseEntity.ok().body(new ProductResponse(product));
    }
}
