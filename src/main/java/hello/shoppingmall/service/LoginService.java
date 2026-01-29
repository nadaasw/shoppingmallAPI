package hello.shoppingmall.service;

import hello.shoppingmall.domain.User;
import hello.shoppingmall.dto.LoginRequest;
import hello.shoppingmall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("NO User Found"));
        return user;
    }

    public boolean checkUser(LoginRequest request){
        User user = findByEmail(request.getEmail());
        if(user == null) return false;

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(request.getPassword(), user.getPassword());
    }

}
