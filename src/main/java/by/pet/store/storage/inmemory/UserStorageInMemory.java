package by.pet.store.storage.inmemory;

import by.pet.store.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class UserStorageInMemory {

    private List<User> users = new ArrayList<>();

    public void add(User user){
        int size = users.size();
        user.setId(size);
        users.add(user);
    }

    public void addListUsers(List<User> userList){
        users.addAll(userList);
    }

    public User getByUserName(String userName){
        for (User user : users) {
            if (user.getUserName().equals(userName)){
                return user;
            }
        } throw new NoSuchElementException();
    }

    public void updateUserNameByUser(User user, String newUserName){
        for (User user1 : users) {
            if (user.equals(user1)){
                user1.setUserName(newUserName);
                return;
            }
        }
    }

    public void deleteUserByUserName(String userName){
        for (User user : users) {
            if (user.getUserName().equals(userName)){
                users.remove(user);
                return;
            }
        }
    }

    public boolean approve(String username, String key){
        User byUserName = getByUserName(username);
        if (byUserName.getValidateKey().equals(key)){
            byUserName.setApprove(true);
            return true;
        }
        return false;
    }


    public boolean contains(String userName){
        for (User user : users) {
            if (user.getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }
    public boolean contains(User user){
        for (User user1 : users) {
            if (user1.equals(user)){
                return true;
            }
        }
        return false;
    }

    public boolean contains(String userName, String password){
        for (User user : users) {
            if (user.getUserName().equals(userName)){
                if (user.getPassword().equals(password)){
                    return true;
                }
            }
        }
        return false;
    }
}
