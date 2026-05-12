import java.util.function.Predicate;

public class CondicaoFiltrarPedido implements Predicate<Pedido> {

    private final String descricaoProduto;

    public CondicaoFiltrarPedido(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    @Override
    public boolean test(Pedido pedido) {
        Produto produtoFicticio = new ProdutoNaoPerecivel(descricaoProduto, 0.01);
        ItemDePedido itemBuscado = new ItemDePedido(produtoFicticio, 0, 0.0);
        CriterioDeBuscaPorDescricao criterio = new CriterioDeBuscaPorDescricao();
        return pedido.getItensDoPedido().buscarPor(criterio, itemBuscado) != null;
    }
}
