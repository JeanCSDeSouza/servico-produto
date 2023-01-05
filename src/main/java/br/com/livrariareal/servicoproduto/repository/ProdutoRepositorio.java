package br.com.livrariareal.servicoproduto.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.livrariareal.servicoproduto.model.Produto;

/**
 * Repositorio de produto, classe responsavel por expor a interacao com o banco
 * 
 * @author C3PO
 */
public interface ProdutoRepositorio extends MongoRepository<Produto, String>{
	Page<Produto> findByAtivo(boolean ativo, Pageable pageable);
}
