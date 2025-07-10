package helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import helpdesk.domain.Chamado;
import helpdesk.domain.dtos.chamadoDTO;
import helpdesk.domain.repositories.ChamadoRepository;
import helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;

    public Chamado findById(Integer id){
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID:" + id));

    }

   public List<Chamado> findAll() {
    return repository.findAll();
}

}
