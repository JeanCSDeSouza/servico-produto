package br.com.livrariareal.servicoproduto.model;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProdutoForm {
	
	@Getter @Setter private String id;
	@NotEmpty(message="{form.produto.nome.nao.vazio}")
	@Size(min = 1, max = 200, message = "{form.produto.nome.min-max}")
	@Getter @Setter private String nome;
	@Size(min = 0, max = 2000, message = "{form.produto.descricao.max}")
	@Getter @Setter private String descricao;
	@Min(0)
	@Getter @Setter private BigDecimal preco;
	@NotNull(message="{form.produto.is.valid}")
	@Getter @Setter private Boolean ativo;
}
