package helpdesk;

import java.util.Arrays;

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
import helpdesk.repositories.ChamadoRepository;
import helpdesk.repositories.ClienteRepository;
import helpdesk.repositories.TecnicoRepository;

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

		Cliente cli1 = new Cliente(null, "Linus Torvalds", "80527954780", "Torvalds@mail.com", "123");

		Chamado c1= new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado de teste", "Primeiro chamado criado no sistema", tec1, cli1);


		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}

}
