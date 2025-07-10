package helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import helpdesk.domain.Chamado;
import helpdesk.domain.dtos.chamadoDTO;
import helpdesk.services.ChamadoService;
import jakarta.validation.Valid;

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
        List<chamadoDTO> ListDTO = list.stream().map( obj -> new chamadoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(ListDTO);

    }

    @PostMapping
    public ResponseEntity<chamadoDTO> createChamado(@Valid @RequestBody chamadoDTO objDto){
        Chamado obj = service.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @PutMapping(value =  "/{id}")
    public ResponseEntity<chamadoDTO> updateChamado(@PathVariable Integer id, @RequestBody chamadoDTO objDto){
        Chamado newObj = service.update(id, objDto);


        return ResponseEntity.ok().body(new chamadoDTO(newObj));
    }

    



}
