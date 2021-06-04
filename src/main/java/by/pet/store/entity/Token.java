package by.pet.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String uuid;

    @OneToOne
    private User user;

    public Token(String uuid, User user) {
        this.uuid = uuid;
        this.user = user;
    }
}
