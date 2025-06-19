package helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

    
} 
