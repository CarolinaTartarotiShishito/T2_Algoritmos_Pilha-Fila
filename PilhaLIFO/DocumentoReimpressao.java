package PilhaLIFO;
import java.time.LocalDateTime; // perguntar se é necessário esse aquivo ou tanto faz.
import java.time.Duration;

public class DocumentoReimpressao {
    private String nomeArquivo;
    private String nomeUsuario;
    private LocalDateTime horarioSolicitacao;
    private LocalDateTime horarioReimpressao;
    private DocumentoReimpressao proximo;

    public DocumentoReimpressao(String nomeArquivo, String nomeUsuario) {
        this.nomeArquivo = nomeArquivo;
        this.nomeUsuario = nomeUsuario;
        this.horarioSolicitacao = LocalDateTime.now();
    }

    public void registrarReimpressao() {
        this.horarioReimpressao = LocalDateTime.now();
    }

    public long tempoEsperaSegundos() {
        if (horarioReimpressao == null) return -1;
        return Duration.between(horarioSolicitacao, horarioReimpressao).getSeconds();
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public LocalDateTime getHorarioSolicitacao() { // os horarios estão saindo meio estranhos mas da pra entender, só não achei como formatar.
        return horarioSolicitacao;
    }

    public DocumentoReimpressao getProximo() {
        return proximo;
    }

    public void setProximo(DocumentoReimpressao proximo) {
        this.proximo = proximo;
    }

    @Override
    public String toString() {
        return "Arquivo: " + nomeArquivo + ", Usuário: " + nomeUsuario +
               ", Solicitado: " + horarioSolicitacao +
               (horarioReimpressao != null ? ", Reimpresso: " + horarioReimpressao +
               ", Espera: " + tempoEsperaSegundos() + "s" : "");
    }
}