package com.proconpb.sistramite;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.proconpb.sistramite.domain.Setor;
import com.proconpb.sistramite.domain.Tramite;
import com.proconpb.sistramite.repositories.SetorRepository;
import com.proconpb.sistramite.repositories.TramiteRepository;

@SpringBootApplication
public class SistramiteApplication implements CommandLineRunner{

	@Autowired
	private SetorRepository setorRepository;
	
	@Autowired
	private TramiteRepository tramiteRepository;
	
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
		
	}
}
