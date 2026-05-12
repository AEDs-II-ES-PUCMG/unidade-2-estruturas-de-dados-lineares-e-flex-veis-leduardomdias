import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;

public class Lista<E> implements Iterable<E> {

    private Celula<E> primeiro;
    private Celula<E> ultimo;
    private int tamanho;

    public Lista() {
        Celula<E> sentinela = new Celula<>();
        primeiro = ultimo = sentinela;
        tamanho = 0;
    }

    public boolean vazia() {
        return primeiro == ultimo;
    }

    public int tamanho() {
        return tamanho;
    }

    public void inserirFinal(E item) {
        Celula<E> nova = new Celula<>(item);
        ultimo.setProximo(nova);
        ultimo = nova;
        tamanho++;
    }

    public void inserirInicio(E item) {
        Celula<E> nova = new Celula<>(item, primeiro.getProximo());
        if (vazia()) ultimo = nova;
        primeiro.setProximo(nova);
        tamanho++;
    }

    public E removerInicio() {
        if (vazia()) throw new NoSuchElementException("Lista vazia!");
        Celula<E> removida = primeiro.getProximo();
        primeiro.setProximo(removida.getProximo());
        if (removida == ultimo) ultimo = primeiro;
        removida.setProximo(null);
        tamanho--;
        return removida.getItem();
    }

    public void imprimir() {
        if (vazia()) {
            System.out.println("A lista está vazia!");
        } else {
            Celula<E> aux = primeiro.getProximo();
            while (aux != null) {
                System.out.println(aux.getItem());
                aux = aux.getProximo();
            }
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Celula<E> atual = primeiro.getProximo();

            @Override
            public boolean hasNext() {
                return atual != null;
            }

            @Override
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                E item = atual.getItem();
                atual = atual.getProximo();
                return item;
            }
        };
    }

    // Tarefa 1
    public E buscarPor(Comparator<E> criterioDeBusca, E item) {
        for (E elemento : this) {
            if (criterioDeBusca.compare(elemento, item) == 0) {
                return elemento;
            }
        }
        return null;
    }

    // Tarefa 2
    public double somarMultiplicacoes(Function<E, Double> extratorValor, Function<E, Integer> extratorFator) {
        if (vazia()) throw new IllegalStateException("Lista vazia!");
        double soma = 0;
        for (E elemento : this) {
            soma += extratorValor.apply(elemento) * extratorFator.apply(elemento);
        }
        return soma;
    }

    // Tarefa 3
    public Lista<E> filtrar(Predicate<E> condicional) {
        if (vazia()) throw new IllegalStateException("Lista vazia!");
        Lista<E> resultado = new Lista<>();
        for (E elemento : this) {
            if (condicional.test(elemento)) {
                resultado.inserirFinal(elemento);
            }
        }
        return resultado;
    }
}
