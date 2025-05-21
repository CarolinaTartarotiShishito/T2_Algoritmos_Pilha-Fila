public class FilaImpressora {
    int capacidade;
    Documento primeiro, ultimo;
    int ocupacao;

    public FilaImpressora (int capacidade) {
        this.capacidade = capacidade;
        //por clareza
        primeiro = null;
        ultimo = null;
        ocupacao = 0;
    }

    public FilaImpressora (){
        this(10); // cria uma fila padrão com 10 de capacidade como padrão
    }

    public boolean filaVazia() {
        return primeiro == null;
    }

    public boolean filaCheia(){
        return ocupacao == capacidade;
    }

    public void enfileira (Documento doc) {
        Documento novo = doc;
        if (filaVazia()){
            primeiro = novo;
        } else if (filaCheia()) throw new RuntimeException("A fila está cheia! Não é possível adicionar mais documentos na fila!");
        else {
            ultimo.setProximo(novo);
        }
        ultimo =  novo;
        ocupacao++;
    }

    public Documento desenfileira () {
        if (filaVazia()) throw new RuntimeException("A fila está vazia! Não há documentos para imprimir");
        Documento temp = primeiro;
        primeiro = primeiro.getProximo();
        ocupacao--;
        if (primeiro == null) //esvaziou a fila
            ultimo = null; //corta todas as referências
        return temp;
    }

    public void busca (String nome) {
        Documento runner = primeiro;
        int posicao = 1;
        while (runner != null) {
            if(runner.getNomeArquivo().equalsIgnoreCase(nome)) { // utiliza ".equalIgnoreCase" para caso o nome do documento tenha sido escrito utilizando cases diferentes em cada entrada (ao enviar para fila e ao buscar)
                System.out.println("O documento está na " + posicao + "ª posição na fila de impressão."); // imprime a posição do documento na lista
                System.out.println(runner); // imprime as informações do documento
            }
            posicao ++; // enquanto nao encontrar o documento, aumenta a posição na fila
            runner = runner.getProximo();
        }
        System.out.println("O documento não está na lista!");
    }
    
    @Override
    public String toString () {
        if (filaVazia()) return "fila vazia";
        String s = "";
        Documento runner = primeiro;
        int contador = 1;
        while (runner != null) {
            s += contador++ + "º: " + runner + "\n";
            runner = runner.getProximo();
        }
        return s;
    }
}