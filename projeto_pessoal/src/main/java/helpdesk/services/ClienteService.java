package helpdesk.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import helpdesk.domain.Pessoa;
import helpdesk.domain.Cliente;
import helpdesk.domain.dtos.ClienteDTO;
import helpdesk.domain.repositories.PessoaRepository;
import helpdesk.domain.repositories.ClienteRepository;
import helpdesk.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;


    public Cliente findById(Integer id){
        Optional<Cliente> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Cliente> findAll() {
    return repository.findAll();
    }

    public Cliente create(ClienteDTO objDto){
        
        objDto.setId(null);
        validaPorCpfeEmail(objDto);
        Cliente newObj = new Cliente(objDto);
        return repository.save(newObj);
        
    }

    public Cliente update(Integer id, @Valid ClienteDTO objDto) {
       objDto.setId(id);
       Cliente oldObj = findById(id);
       validaPorCpfeEmail(objDto);
       oldObj = new Cliente(objDto);
       return repository.save(oldObj);

    }

     public void delete(Integer id) {
        Cliente Obj = findById(id);
        if (Obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Técnico ordens de serviços em aberto!");
            
        } else {
            repository.deleteById(id);
        }
        
    }

    private void validaPorCpfeEmail(ClienteDTO objDto) {
        
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
