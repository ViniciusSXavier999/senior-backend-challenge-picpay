package br.com.vx.picpay.senior.backend.challenge.domain.transaction;

import br.com.vx.picpay.senior.backend.challenge.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // valor da transação
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    // remetente
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    // recebedor
    private User receiver;

    // data de quando foi realizada essa nossa transação
    private LocalDateTime timesTamp;

}
