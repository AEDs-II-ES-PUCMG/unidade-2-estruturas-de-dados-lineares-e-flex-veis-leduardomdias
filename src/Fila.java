import java.util.NoSuchElementException;

public class Fila<E> {

    private Celula<E> primeiro;
    private Celula<E> ultimo;

    public Fila() {
        Celula<E> sentinela = new Celula<E>();
        primeiro = sentinela;
        ultimo = sentinela;
    }

    public boolean vazia() {
        return primeiro == ultimo;
    }

    public void enfileirar(E item) {
        ultimo.setProximo(new Celula<E>(item));
        ultimo = ultimo.getProximo();
    }

    public E desenfileirar() {
        E item = consultarPrimeiro();
        primeiro = primeiro.getProximo();
        return item;
    }

    public E consultarPrimeiro() {
        if (vazia()) {
            throw new NoSuchElementException("Não há nenhum item na fila!");
        }
        return primeiro.getProximo().getItem();
    }

    /**
     * Conta quantas vezes o elemento alvo aparece na fila sem modificá-la.
     *
     * @param alvo O elemento a ser contado (comparado via equals).
     * @return Número de ocorrências do alvo na fila.
     */
    public int contarOcorrencias(E alvo) {
        int count = 0;
        Celula<E> atual = primeiro.getProximo();
        while (atual != null) {
            if (atual.getItem().equals(alvo)) {
                count++;
            }
            atual = atual.getProximo();
        }
        return count;
    }

    /**
     * Desenfileira os primeiros numItens elementos da fila atual e os retorna
     * em uma nova fila, preservando a ordem de chegada.
     * Se a fila tiver menos de numItens elementos, extrai apenas os disponíveis.
     *
     * @param numItens Número máximo de elementos a extrair.
     * @return Nova Fila com os elementos extraídos.
     */
    public Fila<E> extrairLote(int numItens) {
        Fila<E> lote = new Fila<>();
        int extraidos = 0;
        while (!vazia() && extraidos < numItens) {
            lote.enfileirar(desenfileirar());
            extraidos++;
        }
        return lote;
    }
}
