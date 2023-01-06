package br.com.livrariareal.servicoproduto.controller;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.mongodb.DuplicateKeyException;

import br.com.livrariareal.servicoproduto.services.BancoNaoModificadoException;
import br.com.livrariareal.servicoproduto.services.ExcecaoBean;
import br.com.livrariareal.servicoproduto.services.ProdutoNaoEncontradoException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public final ResponseEntity<Map<String, String>> handleValidationException( MethodArgumentNotValidException ex, WebRequest request ){
		Map<String, String> errors = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach( (error) -> {
			errors.put( ( (FieldError) error ).getField() , error.getDefaultMessage() );
		});
		return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);
	}
	/**
	 * Trata chamadas a api cujo produto não foi encontrado 
	 * @param exception
	 * @param request
	 * @return
	 */
	@ExceptionHandler( ProdutoNaoEncontradoException.class ) 
	public final ResponseEntity<ExcecaoBean> handleAlunoNotFoundException( ProdutoNaoEncontradoException exception, WebRequest request ){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body( ExcecaoBean.builder()
				.timestamp( LocalDateTime.now() )
				.message( exception.getMessage()  )
				.details( request.getDescription(false))
				.build());
	}
	/**
	 * Trata chamadas da api que deram erro ao tentar insert, update ou delete no banco
	 * @param exception
	 * @param request
	 * @return
	 */
	@ExceptionHandler( BancoNaoModificadoException.class )
	public final ResponseEntity<ExcecaoBean> handleDataBaseNotModified( BancoNaoModificadoException exception, WebRequest request ) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( ExcecaoBean.builder()
				.timestamp( LocalDateTime.now() )
				.message( exception.getMessage()  )
				.details( request.getDescription(false))
				.build());
	}
	@ExceptionHandler(DuplicateKeyException.class)
	public final ResponseEntity<ExcecaoBean> handleDuplicateKeyException( DuplicateKeyException exception, WebRequest request ) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( ExcecaoBean.builder()
				.timestamp( LocalDateTime.now() )
				.message( exception.getMessage()  )
				.details( request.getDescription(false))
				.build());
	}
}
