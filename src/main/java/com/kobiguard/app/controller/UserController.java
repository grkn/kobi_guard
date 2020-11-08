package com.kobiguard.app.controller;

import com.kobiguard.app.dto.UserDto;
import com.kobiguard.app.entity.User;
import com.kobiguard.app.resources.UserResource;
import com.kobiguard.app.service.UserService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController extends BaseController {

    private final UserService userService;
    private final ConversionService conversionService;

    public UserController(UserService userService, ConversionService conversionService) {
        this.userService = userService;
        this.conversionService = conversionService;
    }

    @GetMapping("/users")
    public ResponseEntity<Page<UserResource>> getUsers(@PageableDefault Pageable pageable) {
        Page<User> user = userService.findAllUsers(pageable);
        List<UserResource> userResourceList = new ArrayList<>();
        user.get().forEach(item -> userResourceList.add(conversionService.convert(item, UserResource.class)));
        return ResponseEntity.ok(new PageImpl<>(userResourceList, user.getPageable(), user.getTotalElements()));
    }

    @PostMapping("/user")
    public ResponseEntity<UserResource> createUser(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(conversionService.convert(userService.createUser(conversionService.convert(userDto, User.class)),
                UserResource.class));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserResource> getUser(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(conversionService.convert(userService.findUserById(userId), UserResource.class));
    }


    @PutMapping("/user/{userId}")
    public ResponseEntity<UserResource> updateUser(@PathVariable("userId") String userId, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(conversionService.convert(userService.updateUser(conversionService.
                convert(userDto, User.class), userId), UserResource.class));
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

}
