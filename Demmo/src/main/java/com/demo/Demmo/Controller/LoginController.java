package com.demo.Demmo.Controller;

import com.demo.Demmo.dto.UserDto;
import com.demo.Demmo.entity.User;
import com.demo.Demmo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * Endpoint to handle user login.
     *
     * @param user The User object containing username and password from the request body.
     * @return ResponseEntity with a success message and token if login is successful,
     *         or an error message if login fails.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        // Check if username or password is null
        if (Objects.isNull(username) || Objects.isNull(password)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
        }

        // Call the login service to authenticate user and generate a token
        String token = loginService.loginAndGetToken(username, password);

        // If token is generated, return success message with token
        if (token != null) {
            return ResponseEntity.ok("Login successful! Token: " + token);
        } else {
            // If authentication fails, return unauthorized status
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    /**
     * Endpoint to retrieve authenticated user details.
     *
     * @return ResponseEntity with user details if user is authenticated,
     *         or unauthorized status if user is not authenticated.
     */
    @GetMapping("/user-details")
    public ResponseEntity<?> getUserDetails() {
        // Get authentication object from SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if authentication object exists and user is authenticated
        if (authentication != null && authentication.isAuthenticated()) {
            // Get principal (authenticated user details) from authentication object
            Object principal = authentication.getPrincipal();

            // Check if principal is an instance of UserDetails
            if (principal instanceof UserDetails) {
                // Cast principal to UserDetails to retrieve username
                UserDetails userDetails = (UserDetails) principal;
                String username = userDetails.getUsername();

                // Return user details in response entity
                return ResponseEntity.ok(new UserDto(username, null)); // Adjust as needed to include additional user details
            }
        }

        // Return unauthorized status if user is not authenticated
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
    }

    // Add detailed explanation of each part here if needed
}
