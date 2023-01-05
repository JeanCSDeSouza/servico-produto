package br.com.livrariareal.servicoproduto.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper=false)
public class ProdutoData extends RepresentationModel<ProdutoData> {
	@Getter @Setter private String id;
	@Getter @Setter private String nome;
	@Getter @Setter private String descricao;
	@Getter @Setter private BigDecimal preco;
	@Getter @Setter private boolean ativo;
}
