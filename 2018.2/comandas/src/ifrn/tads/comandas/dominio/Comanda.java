package ifrn.tads.comandas.dominio;

import java.util.ArrayList;
import java.util.List;

public class Comanda {
	private static final int ATIVA = 1;
	private static final int FECHADA = 2;

	private int numero;
	private List<Item> itens;

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	private int estado;

	public Comanda(int numero) {
		super();
		this.numero = numero;
		itens = new ArrayList<Item>();
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	@Override
	public String toString() {
		String parcial = "";
		double total = 0;
		for (Item it : itens) {
			parcial += it.getNome()+" - R$ "+it.getProduto().getPreco()+"(unidade) - "+it.getQuantidade()+" unid. - subtotal = R$ "+it.getSubTotal()+"\n";
			total += it.getSubTotal();
		}
		if (itens.isEmpty()) {
			parcial += "Ainda no h itens associados a essa comanda!\n";
		}
		parcial += "TOTAL = R$ "+total+"\n";
		return parcial;
	}

	public void adicionaItem(Produto produto, int quantidade) {
		Item item = new Item();
		item.setProduto(produto);
		item.setQuantidade(quantidade);
		itens.add(item);
	}

	public double totalGasto(){
		Item item = new Item();
		return item.getSubTotal();

	}

}

