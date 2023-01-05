package br.com.livrariareal.servicoproduto.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.livrariareal.servicoproduto.controller.ProdutoControlador;

/**
 * Classe para tratar a conversao de produto para uso do HATEOAS
 * @author Jean Carlos
 */
@Component
public class ProdutoRepresentationAssembler implements RepresentationModelAssembler<Produto, ProdutoData>{

	@Override
	public ProdutoData toModel(Produto entity) {
		return ProdutoData.builder()
			.id( entity.getId() )
			.nome( entity.getNome() )
			.descricao( entity.getDescricao() )
			.preco( entity.getPreco() )
			.ativo( entity.getAtivo() )
			.build().add( linkTo( methodOn( ProdutoControlador.class ).produto(entity.getId()) ).withSelfRel() );
	}

	@Override
	public CollectionModel<ProdutoData> toCollectionModel(Iterable<? extends Produto> entities) {
		return RepresentationModelAssembler.super.toCollectionModel(entities);
	}
	
	
	
}
