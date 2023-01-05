package br.com.livrariareal.servicoproduto.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Representação de produto
 * @author Jean Carlos
 */
@Document("produto")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Produto {
	
	@Id
	private String id;
	@Indexed(unique=true)
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private Boolean ativo;
}
