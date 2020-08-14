package com.consultorio.validation;

import java.time.LocalDate;

import com.consultorio.application.ValidationException;
import com.consultorio.model.Pessoa;
import com.consultorio.repository.UsuarioRepository;

public class PessoaValidation implements Validation<Pessoa> {

	@Override
	public void validate(Pessoa entity) throws ValidationException {
		
		if (entity != null) {
			validaCpfUsuario(entity);

			validaEmailUsuario(entity);

			validaRgUsuario(entity);
			
			if (entity.getMedico() != null || entity.getAdm() != null) {
				validaDataAniversarioUsuario(entity);
			} 
			
			else {
				validaDataAniversarioPaciente(entity);
			}
			
		}
		
	}

	// Paciente 
	
	private void validaDataAniversarioPaciente(Pessoa entity) throws ValidationException {
		LocalDate data = new java.sql.Date(entity.getDataNascimento().getTime()).toLocalDate();
		
		LocalDate hoje = LocalDate.now();
		
		if (data.isAfter(hoje)) {
			throw new ValidationException("Data Inválida");
		}
	}

	// Usuario

	private void validaCpfUsuario(Pessoa entity) throws ValidationException {
		UsuarioRepository repo = new UsuarioRepository();
		ValidaCpf valida = new ValidaCpf();
		valida.TirarMascara(entity.getCpf());
		if (repo.containsCpf(entity.getCpf(), entity.getId())) {
			throw new ValidationException("Este CPF já está sendo utilizado.");
		}	
		
		else {
			if (!valida.isCPF()) {
				throw new ValidationException("CPF inválido.");
			}
		}
	}

	private void validaEmailUsuario(Pessoa entity) throws ValidationException {
		UsuarioRepository repo = new UsuarioRepository();
		if (repo.containsEmail(entity.getId(), entity.getEmail())) {
			throw new ValidationException("Email Inválido. Este e-mail já está sendo utilizado.");
		}
	}

	private void validaRgUsuario(Pessoa entity) throws ValidationException {
		UsuarioRepository repo = new UsuarioRepository();
		if (repo.containsRg(entity.getRg(), entity.getId())) {
			throw new ValidationException("Rg Inválido. Este rg já está sendo utilizado.");
		}
	}

	private void validaDataAniversarioUsuario(Pessoa entity) throws ValidationException {
		LocalDate data = new java.sql.Date(entity.getDataNascimento().getTime()).toLocalDate();
		LocalDate dataLimite = LocalDate.now().minusYears(18);

		if (data.isAfter(dataLimite)) {
			if (entity.getMedico() != null) {				
				throw new ValidationException("Data Inválida. O Médico não pode ser menor de idade.");
			} else {
				throw new ValidationException("Data Inválida. O Admnistrador não pode ser menor de idade.");
			}
		}
	}

}
