package helpdesk.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import helpdesk.domain.Pessoa;
import java.util.Optional;


public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    Optional<Pessoa> findByCpf(String cpf);
    Optional<Pessoa> findByEmail(String cpf);

    
} 
