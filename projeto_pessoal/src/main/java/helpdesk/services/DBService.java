package helpdesk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import helpdesk.domain.Chamado;
import helpdesk.domain.Cliente;
import helpdesk.domain.Tecnico;
import helpdesk.domain.enums.Perfil;
import helpdesk.domain.enums.Prioridade;
import helpdesk.domain.enums.Status;
import helpdesk.domain.repositories.ChamadoRepository;
import helpdesk.domain.repositories.ClienteRepository;
import helpdesk.domain.repositories.TecnicoRepository;

@Service
public class DBService {

    @Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

    public void instanciaDB(){

            // Técnicos
        Tecnico tec1 = new Tecnico(null, "Valdir Cezar", "55048215095", "valdir@mail.com", "123");
        tec1.addPerfis(Perfil.ADMIN);

        Tecnico tec2 = new Tecnico(null, "Richard Stallman", "90334707056", "stallman@mail.com", "123");
        tec2.addPerfis(Perfil.ADMIN);

        Tecnico tec3 = new Tecnico(null, "Claude Elwood Shannon", "27106847054", "shannon@mail.com", "123");
        tec3.addPerfis(Perfil.ADMIN);

        Tecnico tec4 = new Tecnico(null, "Tim Berners-Lee", "16272012039", "lee@mail.com", "123");
        tec4.addPerfis(Perfil.ADMIN);

        Tecnico tec5 = new Tecnico(null, "Linus Torvalds", "77855617027", "linus@mail.com", "123");
        tec5.addPerfis(Perfil.ADMIN);

        // Clientes
        Cliente cli1 = new Cliente(null, "Albert Einstein", "11166189074", "einstein@mail.com", "123");
        Cliente cli2 = new Cliente(null, "Marie Curie", "32242914006", "curie@mail.com", "123");
        Cliente cli3 = new Cliente(null, "Charles Darwin", "79204383062", "darwin@mail.com", "123");
        Cliente cli4 = new Cliente(null, "Stephen Hawking", "17740968030", "hawking@mail.com", "123");
        Cliente cli5 = new Cliente(null, "Max Planck", "08139930083", "planck@mail.com", "123");

        // Salvando técnicos e clientes
        tecnicoRepository.save(tec1);
        tecnicoRepository.save(tec2);
        tecnicoRepository.save(tec3);
        tecnicoRepository.save(tec4);
        tecnicoRepository.save(tec5);

        clienteRepository.save(cli1);
        clienteRepository.save(cli2);
        clienteRepository.save(cli3);
        clienteRepository.save(cli4);
        clienteRepository.save(cli5);

        // Chamados
        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO,
            "Chamado 1", "Teste chamado 1", tec1, cli1);

        Chamado c2 = new Chamado(null, Prioridade.ALTA, Status.ABERTO,
            "Chamado 2", "Teste chamado 2", tec1, cli2);

        Chamado c3 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO,
            "Chamado 3", "Teste chamado 3", tec2, cli3);

        Chamado c4 = new Chamado(null, Prioridade.ALTA, Status.ABERTO,
            "Chamado 4", "Teste chamado 4", tec3, cli3);

        Chamado c5 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO,
            "Chamado 5", "Teste chamado 5", tec2, cli1);

        Chamado c6 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO,
            "Chamado 6", "Teste chamado 6", tec1, cli5);

        // Salvando chamados
        chamadoRepository.save(c1);
        chamadoRepository.save(c2);
        chamadoRepository.save(c3);
        chamadoRepository.save(c4);
        chamadoRepository.save(c5);
        chamadoRepository.save(c6);

    }

}
