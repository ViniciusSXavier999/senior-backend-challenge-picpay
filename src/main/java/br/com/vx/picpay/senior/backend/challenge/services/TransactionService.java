package br.com.vx.picpay.senior.backend.challenge.services;

import br.com.vx.picpay.senior.backend.challenge.domain.transaction.Transaction;
import br.com.vx.picpay.senior.backend.challenge.domain.user.User;
import br.com.vx.picpay.senior.backend.challenge.dtos.TransactionDTO;
import br.com.vx.picpay.senior.backend.challenge.repositores.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private NotificationService notificationService;


    // MÉTODO QUE VAI SER CHAMADO PARA CRIAR UMA TRANSAÇÃO
    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        // Vamos pegar o usuario para fazer a validação sobre ele
        User senderr = this.userService.findUserById(transaction.senderId());
        User receiverr = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(senderr, transaction.value());


       /*  VALIDAÇÃO -> Antes de finalizar a transferência, deve-se consultar um serviço
         autorizador externo (mocy) */

     //   boolean isAuthorized = !this.authService.authorizeTransaction(senderr, transaction.value());
     //   if (isAuthorized) {
      //      throw new Exception("Transações não autorizadas");
     //   }

        // Criando uma nova transação na tabela caso tudo passe pela verificação
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(senderr);
        newTransaction.setReceiver(receiverr);
        newTransaction.setTimesTamp(LocalDateTime.now());


        // Por fim vamos atualizar o saldo dos nossos usuarios e vamos salvar no banco de dados
        senderr.setBalance(senderr.getBalance().subtract(transaction.value()));
        receiverr.setBalance(receiverr.getBalance().add(transaction.value()));

        // PERSISTINDO ESSES DADOS NO NOSSO BANCO DE DADOS
        this.transactionRepository.save(newTransaction);
        this.userService.saveUser(senderr);
        this.userService.saveUser(receiverr);


        // NOTIFICAÇÃO DAS TRANSAÇÕES
        this.notificationService.sendNotification(senderr, "Transação realizada com sucesso");
        this.notificationService.sendNotification(receiverr, "Transação recebida com sucesso");



        return newTransaction;

    }

}


