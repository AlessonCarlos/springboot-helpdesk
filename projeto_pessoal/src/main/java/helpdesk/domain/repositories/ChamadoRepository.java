package helpdesk.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import helpdesk.domain.Chamado;


public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

    
} 
