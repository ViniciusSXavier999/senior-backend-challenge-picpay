package br.com.vx.picpay.senior.backend.challenge.domain.user;

import jakarta.persistence.*;


import java.math.BigDecimal;

import br.com.vx.picpay.senior.backend.challenge.dtos.UserDTO;
import lombok.*;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // primeiro nome
    private String firstName;

    // segundo nome
    private String lastName;

    // CPF
    @Column(unique = true)
    private String document;


    @Column(unique = true)
    private String email;

    private String password;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User(UserDTO data){
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.document = data.document();
        this.balance = data.balance();
        this.userType = data.userType();
        this.password = data.password();
        this.email = data.email();
    }

}
