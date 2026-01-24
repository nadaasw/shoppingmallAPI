package hello.shoppingmall.service;

import hello.shoppingmall.domain.Product;
import hello.shoppingmall.dto.AddProductRequest;
import hello.shoppingmall.dto.UpdateProductRequest;
import hello.shoppingmall.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public Product save(AddProductRequest addProductRequest) {
        return productRepository.save(addProductRequest.toEntity());
    }

    public List<Product> findAll() {return productRepository.findAll();}

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("not found : " + id));
    }

    public void delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("not found : " + id));

        productRepository.delete(product);
    }

    @Transactional
    public Product update(Long id, UpdateProductRequest updateProductRequest) {
        Product product = productRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("not found : " + id));

        product.update(updateProductRequest);

        return product;
    }
}
