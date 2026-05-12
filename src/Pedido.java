import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pedido implements Comparable<Pedido> {

	private static int ultimoID = 1;

	private static final double DESCONTO_PG_A_VISTA = 0.15;

	private int idPedido;

	private Lista<ItemDePedido> itensDePedido;

	private LocalDate dataPedido;

	private int formaDePagamento;

	public Pedido(LocalDate dataPedido, int formaDePagamento) {
		idPedido = ultimoID++;
		itensDePedido = new Lista<>();
		this.dataPedido = dataPedido;
		this.formaDePagamento = formaDePagamento;
	}

	public Lista<ItemDePedido> getItensDoPedido() {
		return itensDePedido;
	}

	public ItemDePedido existeNoPedido(Produto produto) {
		ItemDePedido itemBuscado = new ItemDePedido(produto, 0, 0.0);
		return itensDePedido.buscarPor((a, b) -> a.getProduto().descricao.compareTo(b.getProduto().descricao), itemBuscado);
	}

	public boolean incluirProduto(Produto novo, int quantidade) {
		ItemDePedido itemExistente = existeNoPedido(novo);
		if (itemExistente != null) {
			itemExistente.setQuantidade(quantidade + itemExistente.getQuantidade());
			return true;
		}
		itensDePedido.inserirFinal(new ItemDePedido(novo, quantidade, novo.valorDeVenda()));
		return true;
	}

	public double valorFinal() {
		double valorPedido = itensDePedido.somarMultiplicacoes(
			item -> item.getPrecoVenda(),
			item -> item.getQuantidade()
		);

		if (formaDePagamento == 1) {
			valorPedido = valorPedido * (1.0 - DESCONTO_PG_A_VISTA);
		}

		BigDecimal valorPedidoBD = new BigDecimal(Double.toString(valorPedido));
		valorPedidoBD = valorPedidoBD.setScale(2, RoundingMode.HALF_UP);
		return valorPedidoBD.doubleValue();
	}

	@Override
	public String toString() {
		StringBuilder stringPedido = new StringBuilder();

		stringPedido.append("==============================\n");
		stringPedido.append("ID do pedido: " + idPedido + "\n");

		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		stringPedido.append("Data do pedido: " + formatoData.format(dataPedido) + "\n");

		stringPedido.append("Pedido com " + itensDePedido.tamanho() + " itens.\n");
		stringPedido.append("Itens de pedido no pedido:\n");
		for (ItemDePedido item : itensDePedido) {
			stringPedido.append(item.toString() + "\n");
		}

		stringPedido.append("Pedido pago ");
		if (formaDePagamento == 1) {
			stringPedido.append("à vista. Percentual de desconto: " + String.format("%.2f", DESCONTO_PG_A_VISTA * 100) + "%\n");
		} else {
			stringPedido.append("parcelado.\n");
		}

		stringPedido.append("Valor total do pedido: R$ " + String.format("%.2f", valorFinal()));

		return stringPedido.toString();
	}

	@Override
	public int hashCode() {
		return idPedido;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if ((obj == null) || (!(obj instanceof Pedido))) return false;
		Pedido outro = (Pedido) obj;
		return this.hashCode() == outro.hashCode();
	}

	@Override
	public int compareTo(Pedido outroPedido) {
		return (this.hashCode() - outroPedido.hashCode());
	}
}
