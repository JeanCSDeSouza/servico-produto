package br.com.livrariareal.servicoproduto.services;

import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import br.com.livrariareal.servicoproduto.model.Produto;
import br.com.livrariareal.servicoproduto.model.ProdutoData;
import br.com.livrariareal.servicoproduto.model.ProdutoForm;
import br.com.livrariareal.servicoproduto.model.ProdutoRepresentationAssembler;
import br.com.livrariareal.servicoproduto.repository.ProdutoRepositorio;

@Service
public class ProdutoServicoImpl implements ProdutoServico {
	
	@Autowired
	private ProdutoRepositorio produtoRepositorio;
	@Autowired
	private ProdutoRepresentationAssembler produtoAssembler; 
	@Autowired
	private MessageSource messageSource;
	
	@Override
	public ProdutoData buscarPorId(String id) {
		Optional<Produto> produto = produtoRepositorio.findById(id);
		if( produto.isPresent() ) {
			return produtoAssembler.toModel( produto.get() );
		}else {
			throw new ProdutoNaoEncontradoException( messageSource.getMessage("api.erro.produto.nao.encontrado", null, Locale.getDefault()) );
		}
		
	}
	
	@Override
	public CollectionModel<ProdutoData> buscarTodos() {
		return produtoAssembler.toCollectionModel(produtoRepositorio.findAll());
	}
	
	@Override
	public ProdutoRetornoPaginacao buscarTodosAtivosPaginavel(int page, int size){
		Pageable paging = PageRequest.of(page, size);
		
		Page<Produto> pageProduto;
		
		pageProduto = produtoRepositorio.findByAtivo(true, paging);
		
		return ProdutoRetornoPaginacao.builder()
				.produtos( produtoAssembler.toCollectionModel( pageProduto.getContent() ) )	
				.paginaAtual( pageProduto.getNumber() )
				.totalItems( pageProduto.getNumberOfElements() )
				.totalPaginas( pageProduto.getTotalPages() )
				.build();
	}
	
	@Override
	public ProdutoData salvar(ProdutoForm produto) {
		Produto persistir = Produto.builder()
				.nome( produto.getNome() )
				.descricao( produto.getDescricao() )
				.preco( produto.getPreco() )
				.ativo( produto.getAtivo() )
				.build();
		try {
			persistir.setId( produtoRepositorio.save(persistir).getId() );
			return produtoAssembler.toModel(persistir);
		}catch(Exception iae){//IllegalArgumentException & OptimisticLockingFailureException
			throw new BancoNaoModificadoException( messageSource.getMessage("api.erro.produto.modificacao.nao.realizada", null, Locale.getDefault()) );
		}
	}
	
	@Override
	public ProdutoData inativar(String id) {
		Optional<Produto> produto = produtoRepositorio.findById(id);
		if( produto.isPresent() ) {
			produto.get().setAtivo(false); 
			return produtoAssembler.toModel( produtoRepositorio.save( produto.get() ) );
		}else {
			throw new ProdutoNaoEncontradoException( messageSource.getMessage("api.erro.produto.nao.encontrado", null, Locale.getDefault()) );
		}
	}
	
	@Override
	public ProdutoData alterar(ProdutoForm produto) {
		if(produtoRepositorio.existsById(produto.getId())) {
			Produto persistido = produtoRepositorio.findById( produto.getId() ).get();
			persistido.setDescricao( produto.getDescricao() );
			persistido.setPreco(produto.getPreco());
			return produtoAssembler.toModel( produtoRepositorio.save(persistido) );
		}else {
			throw new ProdutoNaoEncontradoException( messageSource.getMessage("api.erro.produto.nao.encontrado", null, Locale.getDefault()) );
		}
	}
}
