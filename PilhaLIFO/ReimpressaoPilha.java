package PilhaLIFO;

import java.util.Calendar;

public class ReimpressaoPilha {
    private int topo;
    private final int tamanho;
    private DocumentoReimpressao[] pilha;

    public ReimpressaoPilha(int tamanho) {
        this.tamanho = tamanho;
        this.pilha = new DocumentoReimpressao[tamanho];
        this.topo = 0;
    }

    public boolean estaCheio() {
        return topo == tamanho;
    }

    public boolean estaVazio() {
        return topo == 0;
    }

    public int getTamanho() {
        return tamanho;
    }



    public boolean solicitarReimpressao(String nomeArquivo, String usuario) {
        if (estaCheio()) return false;
        pilha[topo++] = new DocumentoReimpressao(nomeArquivo, usuario);
        return true;
    }

    public DocumentoReimpressao executarReimpressao() {
        if (estaVazio()) return null;
        DocumentoReimpressao doc = pilha[--topo];
        doc.horarioReimpressao = Calendar.getInstance();
        return doc;
    }

    public int posicaoNaPilha(String nomeArquivo) {
        for (int i = topo - 1, pos = 0; i >= 0; i--, pos++) {
            if (pilha[i].nomeArquivo.equals(nomeArquivo)) return pos;
        }
        return -1;
    }

    public DocumentoReimpressao consultarDocumento(String nomeArquivo) {
        for (int i = topo - 1; i >= 0; i--) {
            if (pilha[i].nomeArquivo.equals(nomeArquivo)) return pilha[i];
        }
        return null;
    }

    public void relatorio() {
        if (estaVazio()) {
            System.out.println("Pilha vazia!");
            return;
        }
        System.out.println("Documentos na pilha de reimpressão:");
        for (int i = topo - 1, pos = 0; i >= 0; i--, pos++) {
            DocumentoReimpressao doc = pilha[i];
            System.out.printf("Posição (a partir do topo): %d | Arquivo: %s | Usuário: %s | Solicitado: %tc\n",
                pos, doc.nomeArquivo, doc.usuario, doc.horarioSolicitacao);
        }
    }

    public static long tempoDecorridoSegundos(DocumentoReimpressao doc) {
        if (doc.horarioReimpressao == null) return -1;
        long millis = doc.horarioReimpressao.getTimeInMillis() - doc.horarioSolicitacao.getTimeInMillis();
        return millis / 1000;
    }
}