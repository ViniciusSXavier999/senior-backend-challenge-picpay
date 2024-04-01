package br.com.vx.picpay.senior.backend.challenge.repositores;

import br.com.vx.picpay.senior.backend.challenge.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

// Responsável pela manipulação da tabela de transações
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
