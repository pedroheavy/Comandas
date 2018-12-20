package ifrn.tads.comandas.util;

import java.util.ArrayList;

import ifrn.tads.comandas.dominio.Produto;

public class ProdutoDAO extends ArrayList<Produto> {
	private static final long serialVersionUID = 1L;

	public ProdutoDAO() {
		add(new Produto(1, "X-Tudo", 5.90));
		add(new Produto(2, "Coca-cola", 3.50));
		add(new Produto(3, "Bauru", 4.0));
	}

	public Produto buscarProduto(int codigo) throws ComandaException {
		for (Produto p : this) {
			if (p.getCodigo() == codigo) {
				return p;
			}
		}
		throw new ComandaException("Código de produto inválido!");
	}
	
}
