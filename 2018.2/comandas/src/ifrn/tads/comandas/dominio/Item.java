package ifrn.tads.comandas.dominio;

public class Item {
	private int quantidade;
	private Produto produto;
	public Item() {
		super();
	}
	public Item(int quantidade, Produto produto) {
		super();
		this.quantidade = quantidade;
		this.produto = produto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public String getNome() {
		return produto.getNome();
	}
	public double getSubTotal() {
		return quantidade * produto.getPreco();
	}
}
