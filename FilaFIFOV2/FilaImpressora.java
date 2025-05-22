public class FilaImpressora {
    Documento[] dados;
    int primeiro, ultimo, ocupacao;
    public FilaImpressora (int capacidade) {
        dados = new Documento[capacidade];
        //por clareza
        primeiro = 0;
        ultimo = 0;
        ocupacao = 0;
    }

    public FilaImpressora () {
        this(10);
    }

    public boolean filaVazia() {
        return ocupacao == 0;
    }

    public boolean filaCheia() {
        return ocupacao == dados.length;
    }

    private int proxima (int pos) {
        return (pos + 1) % dados.length;
    }

    public void enfileira (Documento novo) {
        if (filaCheia()) throw new RuntimeException("A fila está cheia! Por favor aguarde a impressão dos arquivos!");
        dados[ultimo] = novo;
        ultimo = proxima(ultimo);
        ocupacao++;
    }

    public Documento desenfileira () {
        if (filaVazia()) throw new RuntimeException("falha no desenfileiramento");
        Documento temp = dados[primeiro];
        primeiro = proxima(primeiro);
        ocupacao--;
        return temp;
    }

    public String busca (String nome) {
        for (int i = primeiro, cont=0; cont < ocupacao; cont++){
            if (nome.equals(dados[i].getNomeArquivo())){
                return "O documento foi encontrado na " + (cont + 1) + "ª posição.\n" + dados[i];
            }
            i = proxima(i);
        }
        return "O documento não está na lista!";
    }

    @Override
    public String toString () {
        if (filaVazia()) return "fila vazia";
        String s = "";
        for (int i = primeiro, cont=0; cont < ocupacao; cont++) {
            s += (cont + 1) + "ª posição: " + dados[i] + "\n";
            i = proxima(i);
        }
        return s;
    }

}