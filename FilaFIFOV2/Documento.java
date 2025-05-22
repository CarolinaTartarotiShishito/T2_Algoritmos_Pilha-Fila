import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Documento {
    private String nomeArquivo;
    private String usuario;
    private LocalDateTime horarioSolicitacao;

    public Documento(String nomeArquivo, String usuario) {
        this.nomeArquivo = nomeArquivo;
        this.usuario = usuario;
        this.horarioSolicitacao = LocalDateTime.now(); // pega o horário atual de quando o documento foi enviado para impressão
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public String getUsuario() {
        return usuario;
    }

    public LocalDateTime getHorarioSolicitacao() {
        return horarioSolicitacao;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss");
        return "Arquivo: " + nomeArquivo + ", Usuário: " + usuario + ", Solicitado às: " + horarioSolicitacao.format(fmt);
    }
}
