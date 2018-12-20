package ifrn.tads.comandas.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import ifrn.tads.comandas.dominio.Comanda;
import ifrn.tads.comandas.dominio.Produto;
import ifrn.tads.comandas.dominio.Item;
import ifrn.tads.comandas.util.ComandaDAO;
import ifrn.tads.comandas.util.ComandaException;
import ifrn.tads.comandas.util.ProdutoDAO;


public class MenuPrincipal {

	static Scanner sc;
	static ComandaDAO comandaDAO = new ComandaDAO();
	static ProdutoDAO produtoDAO = new ProdutoDAO();

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		boolean encerrar = false;
		do {
			exibeMenu();
			int opcao = sc.nextInt();
			encerrar = tratarOpcao(opcao);
		} while (!encerrar);
		sc.close();
	}

	private static void exibeMenu() {
		System.out.println("\n\nSISTEMA DE COMANDAS");
		System.out.println("Opções:");
		System.out.println("1- Registrar pedidos em comanda");
		System.out.println("2- Listar comandas");
		System.out.println("3- Finalizar comanda");
		System.out.println("0- SAIR");
		System.out.print("Selecione uma opção --> ");
	}

	private static boolean tratarOpcao(int opcao) {
		switch (opcao) {
		case 0:
			return true;
		case 1:
			adicionaItemEmComanda();
			break;
		case 2:
			comandasAtivas();
			break;
		case 3:
			finalizarComanda();
			break;
		default:
			System.out.println("\nOpção INVÁLIDA, Tente novamente!");
			break;
		}
		return false;
	}

	private static void finalizarComanda(){
		System.out.println("Qual o número da comanda que deseja finalizar?");
		int numComanda = sc.nextInt();

		Comanda com = null;
		try {
			com = comandaDAO.recuperaComanda(numComanda);
			System.out.println(com);
		} catch (ComandaException e) {
			System.out.println("Comanda não encontrada");
		}
		System.out.println("Qual a opção de pagamento?");
		System.out.println("1- Dinheiro");
		System.out.println("2- Cartão");
		System.out.println("3- Cheque");
		int confirma = 0;
		while(confirma !=1){
				System.out.println("Quanto o cliente deu em dinheiro?");
				double dinheiro = sc.nextDouble();
				double troco = 0;
				if(dinheiro > com.totalGasto()) {
					 troco = dinheiro - com.totalGasto();
				} else{
					System.out.println("VEIACO");
				}
				System.out.println("O troco a ser dado é" + troco + "\n\n");

		System.out.println("Deseja confirmar o pagamento?");
		confirma = sc.nextInt();
		System.out.println("1- SIM");
		System.out.println("2- NÂO");
		if(confirma == 1) {
			com.setEstado(2);
			System.out.println("Comanda finalizada com sucesso!!");
		}else{
			break;
			}


		}

    }


	private static void comandasAtivas(){

		for(Comanda c : comandaDAO.getAtivas()){
			System.out.println("Comanda \n" + c.getNumero());
			System.out.println(c);
		}

	}

	private static void adicionaItemEmComanda() {
		System.out.print("Digite o nmero da comanda: ");
		int numero = sc.nextInt();
		Comanda comanda;
		try {
			comanda = comandaDAO.recuperaComanda(numero);
		} catch (ComandaException e) {
			System.out.print("\nErro: "+e.getMessage()+" Deseja criar (s/n)? ");
			String opcao = sc.next();
			if (opcao.toLowerCase().equals("s")) {
				comanda = comandaDAO.criarComanda(numero);
			} else {
				return;
			}
		}
		exibeParcial(comanda);
		int codigo;
		do {
			System.out.print("\nDigite o cdigo do produto a adicionar :");
			codigo = sc.nextInt();
			if (codigo == 0)
				break;
			Produto produto;
			try {
				produto = produtoDAO.buscarProduto(codigo);
			} catch (ComandaException e) {
				System.out.println("Erro: "+e.getMessage()+" Tente novamente.");
				continue;
			}
			System.out.print("\nDigite a quantidade de "+produto.getNome()+": ");
			int quantidade = -1;
			try {
				quantidade = sc.nextInt();
			} catch(InputMismatchException imex) {
				System.out.println("Quantidade precisa ser um inteiro! Tente novamente!");
				sc.nextLine();
				continue;
			}
			if (quantidade<=0) {
				System.out.println("Quantidade invlida! Tente novamente!");
				continue;
			}
			comanda.adicionaItem(produto, quantidade);
			exibeParcial(comanda);
		} while (codigo != 0);
	}


	private static void exibeParcial(Comanda comanda) {
		System.out.println(comanda);
	}

}
