package hello.shoppingmall.controller;

import hello.shoppingmall.domain.User;
import hello.shoppingmall.dto.LoginRequest;
import hello.shoppingmall.dto.LoginResponse;
import hello.shoppingmall.dto.SignupRequest;
import hello.shoppingmall.dto.UserRequest;
import hello.shoppingmall.service.LoginService;
import hello.shoppingmall.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final UserService userService;

    @PostMapping("/api/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req, HttpServletResponse resp) {
        LoginResponse result = loginService.login(req, resp);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/api/signup")
    public ResponseEntity<LoginRequest> signUp(@RequestBody UserRequest req){
        User user = userService.save(req);
        LoginRequest response = new LoginRequest(user.getEmail(), user.getPassword());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
