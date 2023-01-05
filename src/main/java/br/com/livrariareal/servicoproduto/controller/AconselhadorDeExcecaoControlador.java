package br.com.livrariareal.servicoproduto.controller;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import br.com.livrariareal.servicoproduto.services.BancoNaoModificadoException;
import br.com.livrariareal.servicoproduto.services.ExcecaoBean;
import br.com.livrariareal.servicoproduto.services.ProdutoNaoEncontradoException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

@ControllerAdvice
public class AconselhadorDeExcecaoControlador {
	
	/**
	 * Trata as excecoes de validaca de formulario 
	 * @param ex
	 * @param request
	 * @return mapa com os erros de validação e suas respectivas mensagens
	 */
	@ResponseStatus( HttpStatus.BAD_REQUEST )
	@ExceptionHandler( MethodArgumentNotValidException.class )
	public final Map<String,String> handleValidationException( MethodArgumentNotValidException ex, WebRequest request ){
		Map<String, String> errors = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach( (error) -> {
			errors.put( ( (FieldError) error ).getField() , error.getDefaultMessage() );
		});
		return errors;
	}
	/**
	 * Trata chamadas a api cujo produto não foi encontrado 
	 * @param exception
	 * @param request
	 * @return
	 */
	@ResponseStatus( HttpStatus.NOT_FOUND )
	@ExceptionHandler( ProdutoNaoEncontradoException.class ) 
	public final ExcecaoBean handleAlunoNotFoundException( ProdutoNaoEncontradoException exception, WebRequest request ){
		return ExcecaoBean.builder()
				.timestamp( LocalDateTime.now() )
				.message( exception.getMessage() )
				.details( request.getDescription(false) )
				.build();
	}
	/**
	 * Trata chamadas da api que deram erro ao tentar insert, update ou delete no banco
	 * @param exception
	 * @param request
	 * @return
	 */
	@ResponseStatus( HttpStatus.NOT_MODIFIED )
	@ExceptionHandler( BancoNaoModificadoException.class )
	public final ExcecaoBean handleDataBaseNotModified( BancoNaoModificadoException exception, WebRequest request ) {
		return ExcecaoBean.builder()
				.timestamp( LocalDateTime.now() )
				.message( exception.getMessage() )
				.details( request.getDescription(false) )
				.build();
	}
}
