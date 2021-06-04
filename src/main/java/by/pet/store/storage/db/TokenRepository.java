package by.pet.store.storage.db;

import by.pet.store.entity.Token;
import by.pet.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    boolean existsByUuid(String uuid);

    void deleteByUuid(String uuid);

    Token getByUuid(String uuid);

    boolean existsByUuidAndUser(String uuid, User user);
}
