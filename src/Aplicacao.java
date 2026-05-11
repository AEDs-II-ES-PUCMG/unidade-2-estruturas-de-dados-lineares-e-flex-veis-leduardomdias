public class Aplicacao {

    /**
     * Empilha os dígitos do número de matrícula sem repetição,
     * na ordem em que aparecem, e imprime o conteúdo da pilha.
     * Matrícula: 877540 → dígitos únicos (em ordem): 8, 7, 5, 4, 0
     */
    static void testarPilhaMatricula() {

        System.out.println("=== Tarefa 1: Pilha de dígitos da matrícula (877540) ===");

        Pilha<Integer> pilha = new Pilha<>();
        int[] matricula = { 8, 7, 7, 5, 4, 0 };
        boolean[] empilhado = new boolean[10]; // índice = dígito (0-9)

        for (int digito : matricula) {
            if (!empilhado[digito]) {
                pilha.empilhar(digito);
                empilhado[digito] = true;
                System.out.println("Empilhado: " + digito);
            } else {
                System.out.println("Ignorado (repetido): " + digito);
            }
        }

        System.out.println("\n--- Conteúdo da pilha (topo → fundo) ---");
        Pilha<Integer> aux = new Pilha<>();
        while (!pilha.vazia()) {
            int item = pilha.desempilhar();
            System.out.println(item);
            aux.empilhar(item);
        }

        // Restaura a pilha original
        while (!aux.vazia()) {
            pilha.empilhar(aux.desempilhar());
        }

        testarEmpilharDesempilhar();
    }

    /** Testa empilhar e desempilhar com casos de uso e exceção. */
    static void testarEmpilharDesempilhar() {

        System.out.println("\n=== Teste de empilhar e desempilhar ===");

        Pilha<String> pilha = new Pilha<>();

        System.out.println("Pilha vazia? " + pilha.vazia());

        pilha.empilhar("A");
        pilha.empilhar("B");
        pilha.empilhar("C");

        System.out.println("Após empilhar A, B, C:");
        System.out.println("  Topo: " + pilha.consultarTopo());

        String removido = pilha.desempilhar();
        System.out.println("Desempilhado: " + removido);
        System.out.println("  Novo topo: " + pilha.consultarTopo());

        pilha.desempilhar();
        pilha.desempilhar();

        System.out.println("Pilha vazia após remover tudo? " + pilha.vazia());

        System.out.println("Tentando desempilhar pilha vazia...");
        try {
            pilha.desempilhar();
        } catch (java.util.NoSuchElementException e) {
            System.out.println("  Exceção capturada corretamente: " + e.getMessage());
        }

        System.out.println("=== Testes concluídos ===\n");
    }

    public static void main(String[] args) {
        testarPilhaMatricula();
    }
}
