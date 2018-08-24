package com.proconpb.sistramite;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.proconpb.sistramite.domain.Setor;
import com.proconpb.sistramite.repositories.SetorRepository;

@SpringBootApplication
public class SistramiteApplication implements CommandLineRunner{

	@Autowired
	private SetorRepository setorRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SistramiteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Setor s1 = new Setor(1, "TI", 1);
		Setor s2 = new Setor(2, "RH", 2);
		
		setorRepository.saveAll(Arrays.asList(s1, s2));
		
		
	}
}
