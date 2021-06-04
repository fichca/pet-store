package by.pet.store.service;

import by.pet.store.entity.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

    private static final User user = new User("test", "test" ,"test", "test@mail.ru", "test", "test", 1);

    @Autowired
    UserService userService;

    @Test
    @Order(1)
    void add(){
        boolean add = userService.add(user);
        assertTrue(add);
    }

    @Test
    @Order(2)
    void getByUserName(){
        User test = userService.getByUserName("test");
        assertEquals(test, user);
    }

    @Test
    void updateUserNameByUser(){
        userService.add(user);
        User test = userService.getByUserName("test");
        userService.updateUserNameByUser(test, "TEST");
        User test1 = userService.getByUserName("TEST");
        assertEquals(test1.getUserName(), "TEST");
    }

    @Test
    void approve(){
        userService.add(user);
        User test = userService.getByUserName("test");
        String validateKey = test.getValidateKey();
        userService.approve(test.getUserName(), validateKey);
        User test1 = userService.getByUserName("test");
        boolean approve = test1.isApprove();
        assertTrue(approve);
    }

}