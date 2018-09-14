package com.proconpb.sistramite;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.proconpb.sistramite.domain.Auto;
import com.proconpb.sistramite.domain.Cidade;
import com.proconpb.sistramite.domain.Endereco;
import com.proconpb.sistramite.domain.Estado;
import com.proconpb.sistramite.domain.Pessoa;
import com.proconpb.sistramite.domain.PessoaFisica;
import com.proconpb.sistramite.domain.PessoaJuridica;
import com.proconpb.sistramite.domain.Setor;
import com.proconpb.sistramite.domain.Tramite;
import com.proconpb.sistramite.domain.Usuario;
import com.proconpb.sistramite.repositories.AutoRepository;
import com.proconpb.sistramite.repositories.CidadeRepository;
import com.proconpb.sistramite.repositories.EnderecoRepository;
import com.proconpb.sistramite.repositories.EstadoRepository;
import com.proconpb.sistramite.repositories.PessoaRepository;
import com.proconpb.sistramite.repositories.SetorRepository;
import com.proconpb.sistramite.repositories.TramiteRepository;

@SpringBootApplication
public class SistramiteApplication implements CommandLineRunner{

	@Autowired
	private SetorRepository setorRepository;
	
	@Autowired
	private TramiteRepository tramiteRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private AutoRepository autoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SistramiteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");
				
		Estado est1 = new Estado(null, "Paraíba");
		Estado est2 = new Estado(null, "Sergipe");
		
		Cidade c1 = new Cidade(null, "João Pessoa", est1);
		Cidade c2 = new Cidade(null, "Aracajú", est2);
		Cidade c3 = new Cidade(null, "Bayeux", est1);
		
		est1.getCidades().addAll(Arrays.asList(c1, c3));
		est2.getCidades().addAll(Arrays.asList(c2));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Pessoa usu1 = new Usuario(null, "Maria", "maria@procon.pb.gov.br", "Maria", "123456");
		Pessoa usu2 = new Usuario(null, "Darcio", "darcio@procon.pb.gov.br", "Darcio", "123");
		
		usu1.getTelefones().addAll(Arrays.asList("32334286", "988532053"));
		usu2.getTelefones().addAll(Arrays.asList("32334286", "988567422"));
		
		Endereco e1 = new Endereco(null, "Rua Juscelino Kubitscheck", "692", "casa", "Geisel", "58075400", usu1, c1);
		Endereco e2 = new Endereco(null, "Rua Eng. Ávidos", "1209", "casa", "J. Planalto", "58088010", usu2, c1);
		
		usu1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		pessoaRepository.saveAll(Arrays.asList(usu1, usu2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		Pessoa emp1 = new PessoaJuridica(null, "Americanas", "sac@americanas.com", "12789456000125", "Lojas Americanas LTDA");
		Pessoa emp2 = new PessoaJuridica(null, "Carrefour", "sac@carrefour.com", "98654321000154", "Carrefour LTDA");
		
		Pessoa fsc1 = new PessoaFisica(null, "Nataluan", "nataluan@procon.pb.gov.br", "12378945612", "1759842", "Fiscal");
		Pessoa fsc2 = new PessoaFisica(null, "Santana", "santana@procon.pb.gov.br", "98732165423", "885421", "Fiscal");
		
		Endereco e3 = new Endereco(null, "Rua Urquiza Leal", "900", "casa", "Salgado Filho", "49020490", emp1, c2);
		Endereco e4 = new Endereco(null, "Rua Tomé de Souza", "299", "casa", "Imaculada", "58309250", fsc2, c3);
		Endereco e5 = new Endereco(null, "Rua Ladislau", "86", "casa", "Funcionarios II", "58079070", fsc1, c1);
		Endereco e6 = new Endereco(null, "Rua Recife", "89", "casa", "Grotão", "58079786", emp2, c1);
		
		emp1.getEnderecos().addAll(Arrays.asList(e3));
		emp1.getTelefones().addAll(Arrays.asList("32335689", "986007619"));
		emp2.getEnderecos().addAll(Arrays.asList(e6));
		
		fsc1.getEnderecos().addAll(Arrays.asList(e5));
		fsc1.getTelefones().addAll(Arrays.asList("32241960", "988556699"));
		fsc2.getEnderecos().addAll(Arrays.asList(e4));
		
		pessoaRepository.saveAll(Arrays.asList(fsc1, fsc2, emp1, emp2));
		enderecoRepository.saveAll(Arrays.asList(e3, e4, e5, e6));
		
		Setor s1 = new Setor(null, "Divida Ativa", 1);
		Setor s2 = new Setor(null, "Cartório", 2);
				
		Auto auto1 = new Auto(null, 25625, emp1, "Auto de Apreensão", "Reclamação", sdf.parse("30/09/2017 10:32"), fsc1);
		Auto auto2 = new Auto(null, 58962, emp2, "Auto de Constatação", "Reclamação", sdf.parse("31/09/2017 09:50"), fsc2);
		
		emp1.getAutos().addAll(Arrays.asList(auto1));
		emp2.getAutos().addAll(Arrays.asList(auto2));
		
		fsc1.getAutos().addAll(Arrays.asList(auto1));
		fsc2.getAutos().addAll(Arrays.asList(auto2));
		
		Tramite t1 = new Tramite(null, sdf.parse("10/09/2018 08:10"), s1, auto1, usu1);
		
		s1.getTramites().addAll(Arrays.asList(t1));

		setorRepository.saveAll(Arrays.asList(s1, s2));
		tramiteRepository.saveAll(Arrays.asList(t1));	
		autoRepository.saveAll(Arrays.asList(auto1, auto2));
		
	}
}
