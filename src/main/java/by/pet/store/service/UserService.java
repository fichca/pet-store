package by.pet.store.service;

import by.pet.store.entity.Token;
import by.pet.store.entity.User;
import by.pet.store.storage.db.TokenRepository;
import by.pet.store.storage.db.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Component
@AllArgsConstructor
public class UserService {

    private final JavaMailSender emailSender;
    private final UserRepository userStorage;
    private final TokenRepository tokenStorage;

    public boolean add(User user) {

        if (!userStorage.existsById(user.getId())) {

            String key = UUID.randomUUID().toString();
            user.setValidateKey(key);
            userStorage.save(user);
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom("w580ii@mail.ru");
            message.setTo(user.getEmail());
            message.setSubject("subject");
            message.setText("<a href='localhost:8080/user/validate?username=" + user.getUserName() + "&key=" + key + "'>Enter</a>");
//            emailSender.send(message);
            return true;
        } else {
            return false;
        }
    }

    public void addListUsers(List<User> userList) {
        userStorage.saveAll(userList);
    }

    public User getByUserName(String name) {
        if (userStorage.existsByUserName(name)) {
            return userStorage.getByUserName(name);
        }
        throw new NoSuchElementException();
    }

    public void updateUserNameByUser(User user, String newUserName) {
        if (userStorage.existsById(user.getId())) {
            userStorage.updateUsernameById(user.getId(), newUserName);
        } else {
            throw new NoSuchElementException();
        }
    }

    public void deleteUserByUserName(String userName) {
        if (userStorage.existsByUserName(userName)) {
            userStorage.deleteByUserName(userName);
        } else {
            throw new NoSuchElementException();
        }
    }

    public String logIn(User user) {
        if (user.isApprove()) {
            UUID uuid = UUID.randomUUID();
            Token token = new Token(uuid.toString(), user);
            tokenStorage.save(token);
            return uuid.toString();
        }
        throw new NoSuchElementException();
    }

    public boolean logOut(String token) {
        if (validate(token)) {
            tokenStorage.deleteByUuid(token);
            return true;
        } else {
            return false;
        }
    }

    public User getUserByToken(String token) {
        if (validate(token)) {
            Token byUuid = tokenStorage.getByUuid(token);
            return byUuid.getUser();
        } else {
            throw new NoSuchElementException();
        }
    }

    public boolean validUser(String nameUser, String password) {
        return userStorage.existsByUserNameAndPassword(nameUser, password);
    }

    public boolean approve(String username, String key) {
        if (userStorage.existsByUserName(username) && validate(key)) {
            User user = userStorage.getByUserName(username);
            if (user.getValidateKey().equals(key)) {
                userStorage.updateIsApproveById(user.getId(), true);
                return true;
            }
        }
        return false;

//        if (userStorage.existsByUserName(username) && validate(token)) {
//            User user = userStorage.getByUserName(username);
//            if (tokenStorage.existsByUuidAndUser(token, user)) {
//                userStorage.updateIsApproveById(user.getId(), true);
//                return true;
//            }
//        }
//        return false;
    }

    public boolean validate(String token) {
        return true;
//        if (!token.isEmpty()) {
//            String reg = "[\\w]{8}(-[\\w]{4}){3}-[\\w]{12}";
//            Pattern compile = Pattern.compile(reg);
//            return compile.matcher(token).find();
//        }
//        return false;
    }
}
