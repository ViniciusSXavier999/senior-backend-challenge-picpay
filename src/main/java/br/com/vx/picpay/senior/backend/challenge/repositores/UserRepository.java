package br.com.vx.picpay.senior.backend.challenge.repositores;

import br.com.vx.picpay.senior.backend.challenge.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Método para buscar os usuarios pelo documento

    /*Exemplo: eu busco um cpf e quero verificar se esse usuario está cadastrado na minha tabela*/

    // isso pode ou não retornar um usuario, por isso estamos utilizando o OPTIONAL
  Optional<User> findUserByDocument(String document);

    Optional<User> findUserById(Long id);
}
