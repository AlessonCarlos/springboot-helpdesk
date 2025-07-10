package helpdesk.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import helpdesk.domain.Chamado;
import helpdesk.domain.dtos.chamadoDTO;
import helpdesk.services.ChamadoService;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {

    @Autowired
    private ChamadoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<chamadoDTO> findById(@PathVariable Integer id){

        Chamado obj = service.findById(id);;
        return ResponseEntity.ok().body(new chamadoDTO(obj)); 

    }

      @GetMapping
    public ResponseEntity<List<chamadoDTO>> findAll(){
        List<Chamado>list = service.findAll();
        List<chamadoDTO> ListDTO = list.stream().map(chamadoDTO::new).toList();
        return ResponseEntity.ok().body(ListDTO);

    }

}
