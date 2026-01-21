package hello.shoppingmall.repository;

import hello.shoppingmall.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
