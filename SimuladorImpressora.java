import java.util.ArrayList;
import java.util.Scanner;
import java.time.Duration;
import java.time.LocalDateTime;

public class SimuladorImpressora {
    private static final int CAPACIDADE = 5;
    private static Fila fila = new Fila(CAPACIDADE);
    private static ArrayList<Documento> documentos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n=== Menu Impressora ===");
            System.out.println("1 - Adicionar documento");
            System.out.println("2 - Imprimir documento");
            System.out.println("3 - Consultar documento");
            System.out.println("4 - Ver fila");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // sumidouro

            switch (opcao) {
                case 1 -> adicionarDocumento();
                case 2 -> imprimirDocumento();
                case 3 -> consultarDocumento();
                case 4 -> exibirFila();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void adicionarDocumento() {
        if (fila.filaCheia()) {
            System.out.println("⚠️  Fila cheia. Não é possível adicionar mais documentos.");
            return;
        }
        System.out.print("Nome do arquivo: ");
        String nomeArquivo = scanner.nextLine();
        System.out.print("Nome do usuário: ");
        String usuario = scanner.nextLine();

        Documento doc = new Documento(nomeArquivo, usuario);
        documentos.add(doc);
        fila.enfileira(documentos.size() - 1);
        System.out.println("✅ Documento adicionado à fila.");
    }

    private static void imprimirDocumento() {
        if (fila.filaVazia()) {
            System.out.println("Fila vazia. Nada a imprimir.");
            return;
        }

        int indice = fila.desenfileira();
        Documento doc = documentos.get(indice);
        LocalDateTime agora = LocalDateTime.now();
        long segundos = Duration.between(doc.getHorarioSolicitacao(), agora).getSeconds();

        System.out.println("📤 Documento impresso:");
        System.out.println(doc);
        System.out.println("Tempo de espera: " + segundos + " segundos");
    }

    private static void consultarDocumento() {
        System.out.print("Digite o nome do arquivo: ");
        String nome = scanner.nextLine();

        int posicao = 1;
        for (int i = fila.primeiro, cont = 0; cont < fila.ocupacao; cont++) {
            int indice = fila.dados[i];
            Documento doc = documentos.get(indice);
            if (doc.getNomeArquivo().equalsIgnoreCase(nome)) {
                System.out.println("📄 Documento encontrado na posição " + posicao + ":");
                System.out.println(doc);
                return;
            }
            i = (i + 1) % fila.dados.length;
            posicao++;
        }
        System.out.println("❌ Documento não está na fila.");
    }

    private static void exibirFila() {
        if (fila.filaVazia()) {
            System.out.println("Fila vazia.");
            return;
        }

        System.out.println("📋 Fila atual:");
        for (int i = fila.primeiro, cont = 0; cont < fila.ocupacao; cont++) {
            int indice = fila.dados[i];
            Documento doc = documentos.get(indice);
            System.out.println("- " + doc);
            i = (i + 1) % fila.dados.length;
        }
    }
}
