package br.com.vx.picpay.senior.backend.challenge.domain.transaction;

import br.com.vx.picpay.senior.backend.challenge.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@Table(name = "transactions")
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
    
    public Transaction() {
    	
    }

	public Transaction(Long id, BigDecimal amount, User sender, User receiver, LocalDateTime timesTamp) {
		super();
		this.id = id;
		this.amount = amount;
		this.sender = sender;
		this.receiver = receiver;
		this.timesTamp = timesTamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public LocalDateTime getTimesTamp() {
		return timesTamp;
	}

	public void setTimesTamp(LocalDateTime timesTamp) {
		this.timesTamp = timesTamp;
	}
    
    

}
