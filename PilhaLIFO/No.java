package PilhaLIFO;

class No {
    private DocumentoReimpressao info;
    private No proximo;

    public No(DocumentoReimpressao info) {
        this.info = info;
    }

    public DocumentoReimpressao getInfo() {
        return info;
    }

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }

    @Override
    public String toString() {
        return info.toString();
    }
}
