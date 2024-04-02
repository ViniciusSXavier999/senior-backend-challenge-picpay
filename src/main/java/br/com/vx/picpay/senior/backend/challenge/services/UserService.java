package br.com.vx.picpay.senior.backend.challenge.services;

import br.com.vx.picpay.senior.backend.challenge.domain.user.User;
import br.com.vx.picpay.senior.backend.challenge.domain.user.UserType;
import br.com.vx.picpay.senior.backend.challenge.dtos.UserDTO;
import br.com.vx.picpay.senior.backend.challenge.repositores.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // VALIDAÇÃO ->  Lojistas só recebem transferências, não enviam dinheiro para ninguém.
    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Usuário do tipo lojista não está autorizado a realizar transações");
        }

        // VALIDAÇÃO -> Vamos verificar se o nosso sender tem saldo o suficiente para fazer a transferência
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente");
        }
    }


    /*Eu não quero que o meu TRANSACTION SERVICE tenha acesso direto ao meu USER REPOSITORY.
    ----------------------------------------------------------------------------------------
    * O TRANSACTION SERVICE só vai manipular o TRANSACTION REPOSITORY.
    ------------------------------------------------------------------
    Se ele quiser fazer manipulação a tabela de USER ele deve utilizar o USER SERVICE.
    ----------------------------------------------------------------------------------
    * */
    public User findUserById(Long id) throws Exception {
     return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuario não encontrado!"));
    }



    // MÉTODO PARA CRIAR NOVO USUARIO
    public User createUser(UserDTO data) {
    	   User newUser = new User(data);
           this.saveUser(newUser);
           return newUser;
    }


    // MÉTODO PARA RETORNAR UMA LISTA DE USUÁRIOS
    public List<User> getAllUsers() {
       return this.userRepository.findAll();
    }

    // MÉTODO PARA SALVER UM NOVO USUÁRIO
    public void saveUser(User user) {
        this.userRepository.save(user);
    }
}
