package com.proconpb.sistramite.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.proconpb.sistramite.domain.Pessoa;
import com.proconpb.sistramite.dto.PessoaNewDTO;
import com.proconpb.sistramite.repositories.PessoaRepository;
import com.proconpb.sistramite.resources.exception.FieldMessage;

public class PessoaInsertValidator implements ConstraintValidator<PessoaInsert, PessoaNewDTO> {
	
	@Autowired
	private PessoaRepository repo;
	
	@Override
	public void initialize(PessoaInsert ann) {
	}
	
	public boolean isValid(PessoaNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		Pessoa aux = repo.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
	

}
