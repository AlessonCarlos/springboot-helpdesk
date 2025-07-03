package helpdesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import helpdesk.domain.Tecnico;
import helpdesk.domain.dtos.tecnicoDTO;
import helpdesk.services.TecnicoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;



@RestController
@RequestMapping(value ="/tecnicos")
public class TecnicoResource {

    //localhost:8081/tecnicos

    @Autowired
    private TecnicoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<tecnicoDTO> findById(@PathVariable Integer id){
        Tecnico obj = this.service.findById(id);
        return ResponseEntity.ok().body(new tecnicoDTO(obj));
       

    }
    @GetMapping
    public ResponseEntity<List<tecnicoDTO>> findAll(){
        List<Tecnico> list = service.findAll();
        List<tecnicoDTO> listDTO = list.stream().map(obj -> new tecnicoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }


    @PostMapping
    public ResponseEntity<tecnicoDTO> create(@RequestBody tecnicoDTO objDto) {
        Tecnico newObj = service.create(objDto);
        //configuração de acesso
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();


    }




}
