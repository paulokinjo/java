package com.hoax.ify.user;

import com.hoax.ify.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/api/1.0/users")
    GenericResponse createUser(@RequestBody User user) {
        userService.save(user);
        GenericResponse body = new GenericResponse("User saved");
        return body;
    }
}
