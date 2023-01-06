package br.com.livrariareal.servicoproduto.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoForm {
	
	@Getter @Setter private String id;
	@NotEmpty(message="{form.produto.nome.nao.vazio}")
	@Size(min = 3, max = 200, message = "{form.produto.nome.min-max}")
	@Getter @Setter private String nome;
	@Size(min = 0, max = 2000, message = "{form.produto.descricao.max}")
	@Getter @Setter private String descricao;
	@Min(0)
	@Getter @Setter private BigDecimal preco;
	@NotNull(message="{form.produto.is.valid}")
	@Getter @Setter private Boolean ativo;
}
