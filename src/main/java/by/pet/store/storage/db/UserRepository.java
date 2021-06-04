package by.pet.store.storage.db;

import by.pet.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUserName(String username);

    boolean existsByUserNameAndPassword(String nameUser, String password);

    User getByUserName(String name);

    @Query(value = "update User set userName = :username where id = :id")
    void updateUsernameById(@Param("id") int id, @Param("username") String username);

    void deleteByUserName(String username);

    @Query(value = "update User set approve = :isApprove where id = :id")
    void updateIsApproveById(@Param("id") int id, @Param("isApprove") boolean approve);
}
