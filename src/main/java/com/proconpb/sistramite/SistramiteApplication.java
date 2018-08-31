package com.proconpb.sistramite;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.proconpb.sistramite.domain.Cidade;
import com.proconpb.sistramite.domain.Endereco;
import com.proconpb.sistramite.domain.Estado;
import com.proconpb.sistramite.domain.Pessoa;
import com.proconpb.sistramite.domain.Setor;
import com.proconpb.sistramite.domain.Tramite;
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
	
	public static void main(String[] args) {
		SpringApplication.run(SistramiteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Setor s1 = new Setor(null, "TI", 1);
		Setor s2 = new Setor(null, "RH", 2);
		
		Tramite t1 = new Tramite(null, s1);
		Tramite t2 = new Tramite(null, s1);
		
		s1.getTramites().addAll(Arrays.asList(t1, t2));
		
		setorRepository.saveAll(Arrays.asList(s1, s2));
		tramiteRepository.saveAll(Arrays.asList(t1, t2));
		
		Estado est1 = new Estado(null, "Paraíba");
		Estado est2 = new Estado(null, "Sergipe");
		
		Cidade c1 = new Cidade(null, "Patos", est1);
		Cidade c2 = new Cidade(null, "Aracajú", est2);
		Cidade c3 = new Cidade(null, "Sapé", est1);
		
		est1.getCidades().addAll(Arrays.asList(c1, c3));
		est2.getCidades().addAll(Arrays.asList(c2));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Pessoa p1 = new Pessoa(null, "Maria", "maria@gmail.com");
		Pessoa p2 = new Pessoa(null, "Jose", "jose@gmail.com");
		
		p1.getTelefones().addAll(Arrays.asList("32334286", "988532053"));
		p2.getTelefones().addAll(Arrays.asList("32334286", "988567422"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "912", "casa", "Geisel", "58079070", p1, c1);
		Endereco e2 = new Endereco(null, "Rua Ávidos", "1209", "casa", "Planalto", "58088010", p2, c1);
		
		p1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		pessoaRepository.saveAll(Arrays.asList(p1, p2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}
}
