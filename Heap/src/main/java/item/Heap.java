package item;

public class Heap {

    private Item v[];
    private int n;
    private int comp = 0;//número de comparações

    public int getComp() {
        return comp;
    }

    public void setComp(int comp) {
        this.comp = comp;
    }

    public Item getV(int x) { //pegar posição do vetor passado pelo heap na posição x
        return v[x];
    }

    public void setV(Item v, int x) {
        this.v[x] = v;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public Heap(int maxTam) { // @{\it Cria uma fila de prioridades vazia}@
        this.v = new Item[maxTam + 1];
        this.n = 0;
    }

    public Heap(Item v[]) { // @{\it Cria uma fila de prioridades vazia}@
        this.v = v;
        this.n = this.v.length - 1;
    }

    public void refaz(int esq, int dir) { //função para refazer o heap
        int j = esq * 2;
        Item x = this.v[esq];
        while (j <= dir) { //ele irá  olhando as posições inseridas
            comp++;
            if ((j < dir) && (this.v[j].compara(this.v[j + 1]) < 0)) {//caso estejacerto, vai para a posição posição
                j++;
            }
            if (x.compara(this.v[j]) >= 0) {
                break;
            }
            this.v[esq] = this.v[j];//caso esteja alguma posição trocada, realizar a troca
            esq = j;
            j = esq * 2;
        }
        this.v[esq] = x;
    }

    public void constroi() {//realizar a construção do heap
        int esq = n / 2 + 1;
        while (esq > 1) {
            esq--;
            this.refaz(esq, this.n);
        }
    }

    public void aumentaChave(int i, Object chaveNova) throws Exception {
        Item x = this.v[i];
        if (chaveNova == null) {
            throw new Exception("Erro: chaveNova com valor null");
        }
        x.setChave(chaveNova);
        while ((i > 1) && (x.compara(this.v[i / 2]) >= 0)) {
            this.v[i] = this.v[i / 2];
            i /= 2;
        }
        this.v[i] = x;
    }

    public void insere(Item x) throws Exception { // insere um novo item
        this.n++; //aumenta tamanho
        if (this.n == this.v.length) {
            throw new Exception("Erro: heap cheio");
        }
        Object chaveNova = x.getChave();
        this.v[this.n] = x; //insere item
        this.v[this.n].setChave(new Integer(Integer.MIN_VALUE));
        this.aumentaChave(this.n, chaveNova); // aumenta a chave
    }

}
