package com.proconpb.sistramite.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.proconpb.sistramite.domain.Fornecedor;
import com.proconpb.sistramite.dto.FornecedorNewDTO;
import com.proconpb.sistramite.repositories.FornecedorRepository;
import com.proconpb.sistramite.resources.exception.FieldMessage;

public class FornecedorInsertValidator implements ConstraintValidator<FornecedorInsert, FornecedorNewDTO> {
	
	@Autowired
	private FornecedorRepository repo;
	
	@Override
	public void initialize(FornecedorInsert ann) {
	}
	
	public boolean isValid(FornecedorNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		Fornecedor aux = repo.findByEmail(objDto.getEmail());
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
