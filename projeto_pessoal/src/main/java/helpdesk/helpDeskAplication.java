package helpdesk;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import helpdesk.domain.Chamado;
import helpdesk.domain.Cliente;
import helpdesk.domain.Tecnico;
import helpdesk.domain.enums.Perfil;
import helpdesk.domain.enums.Prioridade;
import helpdesk.domain.enums.Status;
import helpdesk.domain.repositories.ChamadoRepository;
import helpdesk.domain.repositories.ClienteRepository;
import helpdesk.domain.repositories.TecnicoRepository;

@SpringBootApplication
public class helpDeskAplication implements CommandLineRunner {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(helpDeskAplication.class, args);
	}

	@Override
public void run(String... args) throws Exception {
    Tecnico tec1 = new Tecnico(null, "Valdir Cezar", "63653230268", "valdir@mail.com", "123");
    tec1.addPerfis(Perfil.ADMIN);

    Cliente cli1 = new Cliente(null, "Linus Torvalds", "80527954780", "torvalds@mail.com", "123");

    tecnicoRepository.save(tec1);
    clienteRepository.save(cli1);

    Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO,
        "Chamado de teste", "Primeiro chamado criado no sistema", tec1, cli1);

    chamadoRepository.save(c1);
}

}
