package com.proconpb.sistramite.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.proconpb.sistramite.domain.Auto;
import com.proconpb.sistramite.domain.Cidade;
import com.proconpb.sistramite.domain.Endereco;
import com.proconpb.sistramite.domain.Estado;
import com.proconpb.sistramite.domain.Fornecedor;
import com.proconpb.sistramite.domain.PessoaJuridica;
import com.proconpb.sistramite.domain.Servidor;
import com.proconpb.sistramite.domain.Setor;
import com.proconpb.sistramite.domain.Tramite;
import com.proconpb.sistramite.domain.Usuario;
import com.proconpb.sistramite.domain.enums.Perfil;
import com.proconpb.sistramite.repositories.AutoRepository;
import com.proconpb.sistramite.repositories.CidadeRepository;
import com.proconpb.sistramite.repositories.EnderecoRepository;
import com.proconpb.sistramite.repositories.EstadoRepository;
import com.proconpb.sistramite.repositories.FornecedorRepository;
import com.proconpb.sistramite.repositories.ServidorRepository;
import com.proconpb.sistramite.repositories.SetorRepository;
import com.proconpb.sistramite.repositories.TramiteRepository;
import com.proconpb.sistramite.repositories.UsuarioRepository;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;
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
	private UsuarioRepository usuarioRepository;
	@Autowired
	private ServidorRepository servidorRepository;
	@Autowired
	private FornecedorRepository fornecedorRepository;
	@Autowired
	private AutoRepository autoRepository;

	
	public void instantiateTestDatabase() throws ParseException {
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
		
		Fornecedor emp1 = new PessoaJuridica(null, "Lojas Americanas LTDA", "Americanas", "sac@americanas.com", "76293655000167");
		Fornecedor emp2 = new PessoaJuridica(null, "Carrefour LTDA", "Carrefour", "sac@carrefour.com", "60046057000153");
		
		Servidor fsc1 = new Servidor(null, "Nataluan", "1759842", "Fiscal");
		Servidor fsc2 = new Servidor(null, "Santana", "885421", "Fiscal");
		
		Endereco e1 = new Endereco(null, "Rua Urquiza Leal", "900", "casa", "Salgado Filho", "49020490", emp1, c2);
		Endereco e2 = new Endereco(null, "Rua Recife", "89", "casa", "Grotão", "58079786", emp2, c1);
		
		emp1.getEnderecos().addAll(Arrays.asList(e1));
		emp1.getTelefones().addAll(Arrays.asList("32335689", "986007619"));
		emp2.getEnderecos().addAll(Arrays.asList(e2));
		
		servidorRepository.saveAll(Arrays.asList(fsc1, fsc2));
		fornecedorRepository.saveAll(Arrays.asList(emp1, emp2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		Setor s1 = new Setor(null, "Divida Ativa");
		Setor s2 = new Setor(null, "Cartório");
		Setor s3 = new Setor(null, "Fiscalização");
		Setor s4 = new Setor(null, "Financeiro");
		Setor s5 = new Setor(null, "Administração");
		Setor s6 = new Setor(null, "Chefia de Gabinete");
		Setor s7= new Setor(null, "T.I.");
				
		Auto auto1 = new Auto(null, 25625, emp1, "Auto de Apreensão", "Reclamação", sdf.parse("30/09/2017 10:32"), fsc1);
		Auto auto2 = new Auto(null, 58962, emp2, "Auto de Constatação", "Reclamação", sdf.parse("31/09/2017 09:50"), fsc2);
		
		emp1.getAutos().addAll(Arrays.asList(auto1));
		emp2.getAutos().addAll(Arrays.asList(auto2));
		
		fsc1.getAutos().addAll(Arrays.asList(auto1));
		fsc2.getAutos().addAll(Arrays.asList(auto2));
		
		Usuario usu1 = new Usuario(null, "maria@procon.pb.gov.br", pe.encode("123456"));
		usu1.addPerfil(Perfil.OPERADOR);
		Usuario usu2 = new Usuario(null, "darcio@procon.pb.gov.br", pe.encode("123"));
		usu2.addPerfil(Perfil.ADMIN);

		
		Tramite t1 = new Tramite(null, sdf.parse("10/09/2018 08:10"), s3, s1, auto1, usu1);
		Tramite t2 = new Tramite(null, sdf.parse("10/09/2018 08:20"), s3, s1, auto2, usu1);
		Tramite t3 = new Tramite(null, sdf.parse("12/09/2018 08:30"), s1, s4, auto2, usu2);
		
		//s1.getTramites().addAll(Arrays.asList(t1));
		//s1.getTramites().addAll(Arrays.asList(t2));
		//s4.getTramites().addAll(Arrays.asList(t3));
		
		auto1.getTramites().addAll(Arrays.asList(t1));
		auto2.getTramites().addAll(Arrays.asList(t2));
		auto2.getTramites().addAll(Arrays.asList(t3));
		
		usuarioRepository.saveAll(Arrays.asList(usu1, usu2));
		setorRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5, s6, s7));
		autoRepository.saveAll(Arrays.asList(auto1, auto2));
		tramiteRepository.saveAll(Arrays.asList(t1, t2, t3));
	}
}
