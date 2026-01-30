package hello.shoppingmall.dto;

import hello.shoppingmall.domain.User;
import lombok.Getter;

@Getter
public class SignupRequest {
    private String username;
    private String email;
    private String password;

    public User toEntity(){
        return User.builder()
                .email(email)
                .password(password)
                .username(username)
                .build();
    }
}
