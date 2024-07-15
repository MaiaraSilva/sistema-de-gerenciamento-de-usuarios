package br.maiarasilva.demo.controller;

import br.maiarasilva.demo.model.UserModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import br.maiarasilva.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers(){
        List<UserModel> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Long id){
        UserModel userModel = userService.getUserById(id);
        return ResponseEntity.ok(userModel);
    }

    @PostMapping
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserModel userModel) {
        UserModel newUser = userService.saveUser(userModel);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<UserModel> updateUser(@RequestBody @Valid UserModel userModel){
        UserModel user = userService.updateUser(userModel);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserModel> deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
