public class AplicacaoFila {

    /**
     * Tarefa 1 — enfileira os caracteres do primeiro e segundo nome
     * ("Luis Eduardo") um a um e imprime a fila.
     * Em seguida testa contarOcorrencias, enfileirar e desenfileirar.
     */
    static void testarFilaNome() {

        System.out.println("=== Tarefa 1: Fila de caracteres do nome (Luis Eduardo) ===");

        Fila<Character> fila = new Fila<>();
        String nome = "Luis Eduardo";

        System.out.println("Enfileirando caracteres de \"" + nome + "\":");
        for (char c : nome.toCharArray()) {
            fila.enfileirar(c);
            System.out.println("  Enfileirado: '" + c + "'");
        }

        System.out.println("\n--- Conteúdo da fila (frente → fundo) ---");
        Fila<Character> aux = new Fila<>();
        while (!fila.vazia()) {
            char item = fila.desenfileirar();
            System.out.print("'" + item + "' ");
            aux.enfileirar(item);
        }
        System.out.println();

        // Restaura a fila original para os testes de contagem
        while (!aux.vazia()) {
            fila.enfileirar(aux.desenfileirar());
        }

        testarContarOcorrencias(fila, nome);
        testarEnfileirarDesenfileirar();
    }

    /** Conta ocorrências de caracteres de interesse na fila do nome. */
    static void testarContarOcorrencias(Fila<Character> fila, String nome) {

        System.out.println("\n=== Teste de contarOcorrencias ===");
        char[] alvosTeste = { 'u', 'a', 'e', 'o', ' ', 'z' };

        for (char alvo : alvosTeste) {
            int ocorrencias = fila.cont_ocorrencias(alvo);
            System.out.println("  '" + alvo + "' aparece " + ocorrencias + " vez(es) em \"" + nome + "\"");
        }
    }

    /** Testa enfileirar, desenfileirar, vazia e exceção em fila vazia. */
    static void testarEnfileirarDesenfileirar() {

        System.out.println("\n=== Teste de enfileirar e desenfileirar ===");

        Fila<String> fila = new Fila<>();

        System.out.println("Fila vazia? " + fila.vazia());

        fila.enfileirar("X");
        fila.enfileirar("Y");
        fila.enfileirar("Z");

        System.out.println("Após enfileirar X, Y, Z:");
        System.out.println("  Primeiro: " + fila.consultarPrimeiro());

        String removido = fila.desenfileirar();
        System.out.println("Desenfileirado: " + removido);
        System.out.println("  Novo primeiro: " + fila.consultarPrimeiro());

        fila.desenfileirar();
        fila.desenfileirar();

        System.out.println("Fila vazia após remover tudo? " + fila.vazia());

        System.out.println("Tentando desenfileirar fila vazia...");
        try {
            fila.desenfileirar();
        } catch (java.util.NoSuchElementException e) {
            System.out.println("  Exceção capturada corretamente: " + e.getMessage());
        }

        System.out.println("=== Testes concluídos ===\n");
    }

    public static void main(String[] args) {
        testarFilaNome();
    }
}
