package br.com.livrariareal.servicoproduto.services;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Builder;

@Builder
public class ExcecaoBean {
	@Getter private final LocalDateTime timestamp;
	@Getter private final String message;
	@Getter private final String details;
}