package by.pet.store.storage.inmemory;

import by.pet.store.entity.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

@Component
public class TokenStorageInMemory {
    private Map<UUID, User> tokens = new HashMap<>();

    public String generate(User user) {
        UUID uuid = UUID.randomUUID();
        tokens.put(uuid, user);
        return uuid.toString();
    }

    public boolean validate(String token) {
        if (!token.isEmpty()) {
            String reg = "[\\w]{8}(-[\\w]{4}){3}-[\\w]{12}";
            Pattern compile = Pattern.compile(reg);
            if (compile.matcher(token).find()) {
                UUID uuid = UUID.fromString(token);
                return tokens.containsKey(uuid);
            }
        }
        return false;
    }

    public User getUser(String token) {
        UUID uuid = UUID.fromString(token);
        return tokens.get(uuid);
    }

    public void delete(String token) {
        UUID uuid = UUID.fromString(token);
        tokens.remove(uuid);
    }
}
