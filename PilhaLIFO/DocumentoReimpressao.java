package PilhaLIFO;

import java.util.Calendar;

class DocumentoReimpressao {
    String nomeArquivo;
    String usuario;
    Calendar horarioSolicitacao;
    Calendar horarioReimpressao;

    DocumentoReimpressao(String nomeArquivo, String usuario) {
        this.nomeArquivo = nomeArquivo;
        this.usuario = usuario;
        this.horarioSolicitacao = Calendar.getInstance();
    }
}
