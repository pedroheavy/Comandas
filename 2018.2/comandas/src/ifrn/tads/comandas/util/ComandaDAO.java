package ifrn.tads.comandas.util;

import java.lang.reflect.Array;
import java.util.ArrayList;

import ifrn.tads.comandas.dominio.Comanda;

public class ComandaDAO extends ArrayList<Comanda> {
	private static final long serialVersionUID = 8002444801632069740L;

	public ComandaDAO() {
		// Criar dados para testar a aplicação
		add(new Comanda(1));
		add(new Comanda(2));
		add(new Comanda(3));

	}

	public Comanda recuperaComanda(int numero) throws ComandaException {
		for (Comanda c : this) {
			if (c.getNumero() == numero) {
				return c;
			}
		}
		throw new ComandaException("Comanda inexistente");
	}

	public Comanda criarComanda(int numero) {
		Comanda nova = new Comanda(numero);
		add(nova);
		nova.setEstado(1);
		return nova;
	}

	public ArrayList<Comanda> getAtivas(){
		ArrayList ativas = new ArrayList();
		for(Comanda c : this){
			if(c.getEstado() == 1){
				ativas.add(c);
			}
		}
		return ativas;
	}

}
