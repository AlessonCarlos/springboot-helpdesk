package helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    
} 
