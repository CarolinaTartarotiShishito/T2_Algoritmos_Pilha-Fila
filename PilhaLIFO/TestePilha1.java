package PilhaLIFO;

import java.util.Scanner;
import java.util.Calendar;

public class TestePilha1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReimpressaoPilha pilha = new ReimpressaoPilha(5);

        int opcao = menu(scanner);
        while (opcao != 0) {
            switch (opcao) {
                case 1:
                    if (pilha.estaCheio()) {
                        System.out.println("Pilha cheia! Não é possível adicionar mais documentos.");
                        break;
                    }
                    System.out.print("Nome do arquivo: ");
                    String nomeArquivo = scanner.nextLine();
                    System.out.print("Nome do usuário: ");
                    String usuario = scanner.nextLine();
                    pilha.solicitarReimpressao(nomeArquivo, usuario);
                    System.out.println(
                            "Solicitação registrada com sucesso! (Horário: " + Calendar.getInstance().getTime() + ")");

                    break;
                case 2:
                    DocumentoReimpressao doc = pilha.executarReimpressao();
                    if (doc == null) {
                        System.out.println("Pilha vazia! Não há documentos para reimprimir.");
                    } else {
                        long tempo = ReimpressaoPilha.tempoDecorridoSegundos(doc);
                        System.out.printf("Reimprimindo: %s (Usuário: %s)\n", doc.nomeArquivo, doc.usuario);
                        System.out.printf("Horário da solicitação: %tc\n", doc.horarioSolicitacao);
                        System.out.printf("Horário da reimpressão: %tc\n", doc.horarioReimpressao);
                        System.out.printf("Tempo decorrido desde a solicitação: %d segundos\n", tempo);
                    }
                    break;
                case 3:
                    System.out
                            .print("Digite o nome do arquivo para consultar (ou deixe em branco para listar todos): ");
                    String consulta = scanner.nextLine();
                    if (consulta.isEmpty()) {
                        pilha.relatorio();
                    } else {
                        DocumentoReimpressao docConsulta = pilha.consultarDocumento(consulta);
                        int pos = pilha.posicaoNaPilha(consulta);
                        if (docConsulta == null) {
                            System.out.println("Documento não está na pilha.");
                        } else {
                            System.out.printf("Arquivo: %s | Usuário: %s | Posição (a partir do topo): %d | Solicitado: %tc\n",
                            docConsulta.nomeArquivo, docConsulta.usuario, pos, docConsulta.horarioSolicitacao);
                        }
                    }
                    break;
                default:
                    System.out.println("Opcao invalida");
                    break;
            }
            opcao = menu(scanner);
        }
        System.out.println("obrigado, volte sempre!");
        scanner.close();
    }

    static int menu(Scanner scanner) {
        System.out.println("\nescolha uma opcao:");
        System.out.println("1 - Colocar para Reimpressao");
        System.out.println("2 - Reimprimir");
        System.out.println("3 - Consultar pilha de reimpressao");
        System.out.println("0 - sair");
        String entrada = scanner.nextLine();
        try {
            return Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            return -1; // Esse treco faz cair no DEFAULT do switch case caso o usuario digite algo
                       // diferente de um numero
        }
    }
}
