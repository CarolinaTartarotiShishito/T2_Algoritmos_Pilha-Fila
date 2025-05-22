import java.util.Scanner;
import java.time.Duration;
import java.time.LocalDateTime;

public class SimuladorImpressora {
    private static final int CAPACIDADE = 5;
    private static FilaImpressora fila = new FilaImpressora(CAPACIDADE); //cria uma fila de impressora com limite de 5 arquivos
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
            System.out.println("Fila cheia. Não é possível adicionar mais documentos.");
            return;
        }
        System.out.print("Nome do arquivo: ");
        String nomeArquivo = scanner.nextLine();
        System.out.print("Nome do usuário: ");
        String usuario = scanner.nextLine();

        Documento novo = new Documento(nomeArquivo, usuario);
        fila.enfileira(novo);
        System.out.println("Documento adicionado à fila.");
    }

    private static void imprimirDocumento() {
        if (fila.filaVazia()) {
            System.out.println("Fila vazia. Nada a imprimir.");
            return;
        }

        Documento impresso = fila.desenfileira();
        LocalDateTime agora = LocalDateTime.now();
        long segundos = Duration.between(impresso.getHorarioSolicitacao(), agora).getSeconds();

        System.out.println("Documento impresso:");
        System.out.println(impresso);
        System.out.println("Tempo de espera: " + segundos + " segundos");
    }

    private static void consultarDocumento() {
        if(fila.filaVazia()){
            System.out.println("A fila de impressão está vazia!");
        } else{
            System.out.print("Digite o nome do arquivo: ");
            String nome = scanner.nextLine();
            System.out.println(fila.busca(nome));
        }        
    }

    private static void exibirFila() {
        if (fila.filaVazia()) {
            System.out.println("Fila vazia.");
            return;
        }

        System.out.println("Fila atual:");
        System.out.println(fila);
    }
}
