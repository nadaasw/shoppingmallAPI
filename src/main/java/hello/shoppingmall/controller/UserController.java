package hello.shoppingmall.controller;


import hello.shoppingmall.domain.User;
import hello.shoppingmall.dto.UserRequest;
import hello.shoppingmall.dto.UserResponse;
import hello.shoppingmall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<UserResponse> users = userService.getUsers()
                .stream()
                .map(UserResponse::new)
                .toList();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.findById(id);

        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody UserRequest request) {
        User savedUser = userService.save(request);
        return ResponseEntity.ok().body(savedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        userService.delete(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserRequest user) {
        User updatedUser = userService.update(id, user);
        return ResponseEntity.ok().body(updatedUser);
    }
}
