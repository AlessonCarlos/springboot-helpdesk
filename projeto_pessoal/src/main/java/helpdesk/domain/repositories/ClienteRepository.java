package helpdesk.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import helpdesk.domain.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    
} 
