package PilhaLIFO;
public class PilhaReimpressao {
    private No topo;
    private int tamanho;
    private int capacidadeMaxima;   //construtor é padrão, herdado de Object, topo = null
                                    //o atributo de controle topo não é visível para o cliente
    public boolean pilhaVazia() {
        return topo == null;
    }

    public boolean push(DocumentoReimpressao doc) {
        if (estaCheia()) return false;
        No novo = new No(doc); 
        novo.setProximo(topo);
        topo = novo;
        tamanho++;  // esta coisa aumenta o tamanho da pilha
        return true;
    }
    
    public DocumentoReimpressao pop() {
        if (pilhaVazia()) throw new RuntimeException("falha no desempilhamento");
        DocumentoReimpressao doc = topo.getInfo();
        doc.registrarReimpressao();
        topo = topo.getProximo();
        tamanho--;
        return doc;
    }
    
    public DocumentoReimpressao peek() {
        if (pilhaVazia()) throw new RuntimeException("Pilha vazia");
        return topo.getInfo();
    }

    public PilhaReimpressao(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
        this.topo = null;
        this.tamanho = 0;
    }


    public boolean estaCheia() {
        return tamanho == capacidadeMaxima;
    }


    public void consultarDocumento(String nomeArquivo) {
        No atual = topo;
        int posicao = 1;
        while (atual != null) {
            if (atual.getInfo().getNomeArquivo().equals(nomeArquivo)) {
                System.out.println("Documento encontrado na posição " + posicao);
                System.out.println("Horário de solicitação: " + atual.getInfo().getHorarioSolicitacao());
                return;
            }
            atual = atual.getProximo();
            posicao++; 
        }
        System.out.println("Documento não encontrado.");
    }

    public void exibir() {
        if (pilhaVazia()) {
            System.out.println("Pilha vazia.");
            return;
        }
        No atual = topo;
        while (atual != null) {
            System.out.println(atual.getInfo());
            atual = atual.getProximo();
        }
    }
}