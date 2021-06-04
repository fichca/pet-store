package by.pet.store.storage.db;

import by.pet.store.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void update(){
        User user = new User("test", "test", "test", "test", "test", "test", 1);
        userRepository.save(user);
        User test = userRepository.getByUserName("test");
        assertEquals(test.getId(), 1);
        userRepository.updateUsernameById(1, "TEST");
        User test1 = userRepository.getByUserName("TEST");
        assertEquals(test1.getUserName(),"TEST");
    }
}