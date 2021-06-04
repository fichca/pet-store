package by.pet.store.resource;

import by.pet.store.entity.User;
import by.pet.store.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createWithList")
    public ResponseEntity<?> addListUsers(@RequestBody List<User> users) {
        userService.addListUsers(users);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String userName){
        try {
            User byUserName = userService.getByUserName(userName);
            return new ResponseEntity<>(byUserName, HttpStatus.CREATED);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{userName}")
    public ResponseEntity<?> updateNameByUser(@RequestBody User user, @PathVariable String userName){
        try {
        userService.updateUserNameByUser(user, userName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteByUserName(@PathVariable String username){
        try {
            userService.deleteUserByUserName(username);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user){
        userService.add(user);
        log.info("User is created " + user);
        //info
        //debug
        //trace
        //warning
        //error
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validate(@RequestParam String username, @RequestParam String key){
        if (userService.approve(username, key)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/login")
    public ResponseEntity<String> logIn(@RequestParam String username, @RequestParam String password){
        if (userService.validUser(username, password)){
            User byUserName = userService.getByUserName(username);
            if (byUserName.isApprove()){
                String token = userService.logIn(byUserName);
                return new ResponseEntity<>(token, HttpStatus.ACCEPTED);
            }else {
                return  new ResponseEntity<>("Confirm your account by mail!", HttpStatus.BAD_REQUEST);
            }
        }else {
            return  new ResponseEntity<>("Username and password not found!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logOut(@RequestHeader("X-Api-Token") String token){
        if (userService.logOut(token)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
