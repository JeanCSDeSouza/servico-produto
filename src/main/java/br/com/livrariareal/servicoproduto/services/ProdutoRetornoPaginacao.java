package br.com.livrariareal.servicoproduto.services;

import org.springframework.hateoas.CollectionModel;

import br.com.livrariareal.servicoproduto.model.ProdutoData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode
public class ProdutoRetornoPaginacao {
	CollectionModel<ProdutoData> produtos;
	Integer paginaAtual;
	Integer totalPaginas;
	Integer totalItems;
}
