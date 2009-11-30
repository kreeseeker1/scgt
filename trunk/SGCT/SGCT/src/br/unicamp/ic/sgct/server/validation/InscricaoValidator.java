package br.unicamp.ic.sgct.server.validation;

import br.unicamp.ic.sgct.server.dominio.entidades.Usuario;
import it.biobytes.ammentos.validation.Validator;

/**
 * 
 * @author Grupo 03 - INF_329
 *
 */
public class InscricaoValidator extends Validator<Usuario>{

	//recursos Inscricao UI
	private static final String MSG_PASSWD_SIZE_ERR = "Senha fornecida n\u00e3o \u00e9 v\u00e1lida !";
	private static final String MSG_NOME_ERR = "Nome informado n\u00e3o \u00e9 v\u00e1lido!";
	private static final String MSG_EMAIL_ERR = "Email informado n\u00e3o \u00e9 v\u00e1lido!";
	private static final String MSG_SOBRENOME_ERR = "Sobrenome informado n\u00e3o \u00e9 v\u00e1lido!";
	private static final String MSG_CELULAR_ERR = "Telefone celular informado n\u00e3o \u00e9 v\u00e1lido!";
	private static final String MSG_FONE_ERR = "Telefone residencial informado n\u00e3o \u00e9 v\u00e1lido";
	
	@Override
	public void performValidation(Usuario usuario) {
		
		// Validador de emails
        // Email: tamanho m�ximo 30 caracteres, presen�a caractere '@';
		checkTrue(usuario.getEmail().length() <= 30 || !usuario.getEmail().contains("@"), MSG_EMAIL_ERR);

		// Validador de senha
	    //Senha: tamanho m�nimo 4 caracteres e m�ximo 8 caracteres;
		checkTrue(usuario.getSenha().length() > 4 || usuario.getSenha().length() < 8, MSG_PASSWD_SIZE_ERR);
		
	    
	    // Validador de nomes
	    // Nome: tamanho m�ximo 15 caracteres, apenas letras;
		checkTrue(usuario.getPessoa().getNome().length() < 15, MSG_NOME_ERR);
		

        
	    // Validador de sobrenomes
        // Sobrenome: tamanho m�ximo 15 caracteres, apenas letras;
	    checkTrue(usuario.getPessoa().getSobreNome().length() < 15, MSG_SOBRENOME_ERR);

	    
	    // Validador de fone residencial
        // Fone residencial: tamanho m�ximo 18 caracteres, somente d�gitos num�ricos e caracteres padr�es de separa��o dos d�gitos;
	    checkTrue(usuario.getPessoa().getFoneResidencial().length() < 18, MSG_FONE_ERR);
	    

	    // Validador de fone celular
	    // Fone celular:  tamanho m�ximo 18 caracteres, somente d�gitos num�ricos e caracteres padr�es de separa��o dos d�gitos.
	    checkTrue(usuario.getPessoa().getCelular().length() < 18, MSG_CELULAR_ERR);
		
	}
}
