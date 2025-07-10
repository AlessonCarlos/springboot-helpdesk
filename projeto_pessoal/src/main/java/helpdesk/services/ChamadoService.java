package helpdesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import helpdesk.domain.Chamado;
import helpdesk.domain.Cliente;
import helpdesk.domain.Tecnico;
import helpdesk.domain.dtos.chamadoDTO;
import helpdesk.domain.enums.Prioridade;
import helpdesk.domain.enums.Status;
import helpdesk.domain.repositories.ChamadoRepository;
import helpdesk.services.exceptions.ObjectNotFoundException;


@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Integer id){
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID:" + id));

    }

   public List<Chamado> findAll() {
    return repository.findAll();
}

   public Chamado create(chamadoDTO objDto) {
   
    return repository.save(newChamado(objDto));
   }

   private Chamado newChamado(chamadoDTO obj){
    Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
    Cliente cliente = clienteService.findById(obj.getCliente());

    Chamado chamado = new Chamado();
    if(obj.getId() != null){
        chamado.setId(obj.getId());
    }

    if(obj.getStatus().equals(2)){
        chamado.setDataFechamento(LocalDate.now());
    }

    chamado.setTecnico(tecnico);
    chamado.setCliente(cliente);
    chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
    chamado.setStatus(Status.toEnum(obj.getStatus()));
    chamado.setTitulo(obj.getTitulo());
    chamado.setObservacoes(obj.getObservacoes());

    return chamado;





   }

   public Chamado update(Integer id, chamadoDTO objDto) {
    objDto.setId(id);
    Chamado oldObj = findById(id);
    oldObj = newChamado(objDto);

    return repository.save(oldObj);
   }

}
