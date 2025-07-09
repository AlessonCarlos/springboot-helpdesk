package helpdesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import helpdesk.domain.Cliente;
import helpdesk.domain.dtos.ClienteDTO;
import helpdesk.services.ClienteService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping(value ="/clientes")
public class ClienteResource {

    //localhost:8081/tecnicos

    @Autowired
    private ClienteService service;

    // faz busca do tecnico pelo ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
        Cliente obj = this.service.findById(id);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
       

    }

    // endpoint listar todos os tecnicos
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<Cliente> list = service.findAll();
        List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

// endpoint criação novo tecnico
    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objDto) {
        Cliente newObj = service.create(objDto);
        //configuração de acesso
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    //endpoint Update do tecnico
    @PutMapping(value ="/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @RequestBody ClienteDTO objDto){

        Cliente oldObj = service.update(id, objDto);

        return ResponseEntity.ok().body(new ClienteDTO(oldObj));
    }

    //endpoint delete tecnico
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id){
        service.delete(id);
        
        return ResponseEntity.noContent().build();
    }



}
