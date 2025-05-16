package PilhaLIFO;

import java.util.Scanner;

public class TestePilhaReimpressao {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PilhaReimpressao pilha = new PilhaReimpressao(5); // capacidade 5

        int opcao;
        do {
            System.out.println("\n1 - Solicitar reimpressão");
            System.out.println("2 - Executar reimpressão");
            System.out.println("3 - Consultar documento");
            System.out.println("4 - Exibir pilha");
            System.out.println("0 - Sair");

            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("Nome do arquivo: ");
                    String nome = scanner.nextLine();
                    System.out.print("Nome do usuário: ");
                    String usuario = scanner.nextLine();
                    boolean adicionado = pilha.push(new DocumentoReimpressao(nome, usuario));
                    if (!adicionado) {
                        System.out.println("Capacidade máxima atingida.");
                    }
                    break;
                case 2:
                    try {
                        DocumentoReimpressao doc = pilha.pop();
                        System.out.println("Documento reimpresso: " + doc);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Nome do arquivo para consulta: ");
                    String consulta = scanner.nextLine();
                    pilha.consultarDocumento(consulta);
                    break;
                case 4:
                    pilha.exibir();
                    break;
            }
        } while (opcao != 0);

        scanner.close();
    }
}