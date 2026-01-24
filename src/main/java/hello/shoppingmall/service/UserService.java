package hello.shoppingmall.service;

import hello.shoppingmall.domain.User;
import hello.shoppingmall.dto.UserRequest;
import hello.shoppingmall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("not found :" + id));
    }

    public User save(UserRequest request){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = User.builder()
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .email(request.getEmail())
                .build();

        return userRepository.save(user);
    }

    public void delete(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("not found :" + id));
        userRepository.delete(user);
    }

    public User update(Long id, UserRequest request){
        User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("not found :" + id));

        user.update(request);

        return user;
    }
}
