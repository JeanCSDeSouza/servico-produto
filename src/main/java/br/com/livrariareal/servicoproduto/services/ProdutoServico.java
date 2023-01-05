package br.com.livrariareal.servicoproduto.services;

import org.springframework.hateoas.CollectionModel;

import br.com.livrariareal.servicoproduto.model.ProdutoData;
import br.com.livrariareal.servicoproduto.model.ProdutoForm;

public interface ProdutoServico {

	ProdutoData buscarPorId(String id);

	CollectionModel<ProdutoData> buscarTodos();

	ProdutoRetornoPaginacao buscarTodosAtivosPaginavel(int page, int size);

	ProdutoData salvar(ProdutoForm produto);

	ProdutoData inativar(String id);

	ProdutoData alterar(ProdutoForm produto);

}