package hello.shoppingmall.dto;

import hello.shoppingmall.domain.User;

public class UserResponse {
    private Long userId;
    private String userName;
    private String password;
    private String email;

    public UserResponse(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
    }
}
