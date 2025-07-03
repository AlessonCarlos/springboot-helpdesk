package helpdesk.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import helpdesk.domain.Pessoa;
import helpdesk.domain.Tecnico;
import helpdesk.domain.dtos.tecnicoDTO;
import helpdesk.domain.repositories.PessoaRepository;
import helpdesk.domain.repositories.TecnicoRepository;
import helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;


    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Tecnico> findAll() {
    return repository.findAll();
    }

    public Tecnico create(tecnicoDTO objDto){
        
        objDto.setId(null);
        validaPorCpfeEmail(objDto);
        Tecnico newObj = new Tecnico(objDto);
        return repository.save(newObj);
        
    }

    private void validaPorCpfeEmail(tecnicoDTO objDto) {
        
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDto.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()) {

            throw new DataIntegrityViolationException("CPF já cadastrado!");
            
        }

        obj = pessoaRepository.findByEmail(objDto.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()) {

            throw new DataIntegrityViolationException("Email já cadastrado!");
            
        }
    }

}
