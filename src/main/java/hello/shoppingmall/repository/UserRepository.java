package hello.shoppingmall.repository;

import hello.shoppingmall.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
