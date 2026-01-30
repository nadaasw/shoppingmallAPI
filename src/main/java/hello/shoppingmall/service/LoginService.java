package hello.shoppingmall.service;

import hello.shoppingmall.config.jwt.TokenProvider;
import hello.shoppingmall.domain.RefreshToken;
import hello.shoppingmall.domain.User;
import hello.shoppingmall.dto.LoginRequest;
import hello.shoppingmall.dto.LoginResponse;
import hello.shoppingmall.repository.RefreshTokenRepository;
import hello.shoppingmall.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.cors.PreFlightRequestHandler;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PreFlightRequestHandler preFlightRequestHandler;

    public LoginResponse login(LoginRequest req, HttpServletResponse response) {
        // ID/ PW 인증
        Authentication auth =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));

        // User 찾기
        User user = userRepository.findByEmail(req.getEmail()).orElseThrow(() -> new IllegalArgumentException("not found"));

        // 토큰 발급
        String accessToken = tokenProvider.generateToken(user, Duration.ofHours(1));
        String refreshToken = tokenProvider.generateToken(user, Duration.ofHours(4));

        // 토큰 저장
        if(refreshTokenRepository.findByUserId(user.getUserId()).isPresent()) {
            RefreshToken token = refreshTokenRepository.findByUserId(user.getUserId()).orElseThrow(() -> new IllegalArgumentException("not found"));
            token.update(refreshToken);
        }else{
            refreshTokenRepository.save(new RefreshToken(user.getUserId(), refreshToken));
        }



        return new LoginResponse(accessToken,refreshToken,user.getUserId());
    }



}
