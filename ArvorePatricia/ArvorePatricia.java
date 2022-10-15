//package ArvorePatricia;
import java.util.ArrayList;

public class ArvorePatricia {

    private static abstract class PatNo {
    }

    private static class PatNoInt extends PatNo {

        int index;
        PatNo esq, dir;
    }

    private static class PatNoExt extends PatNo {

        String chave; // @{\it O tipo da chave depende da aplica\c{c}\~ao}@
        ArrayList<Integer> linha = new ArrayList<>();
        ArrayList<Integer> coluna = new ArrayList<>();
    }

    private PatNo raiz;
    private int nbitsChave;

    // @{\it Retorna o i-\'esimo bit da chave k a partir da esquerda}@
    private int bit(int i, String k) {
        i--;
        if (i == 0) {
            return 0;
        }
        char c = k.charAt(i);
        int myInt = Character.getNumericValue(c);
        //System.out.println(myInt);
        return myInt;
    }

    // @{\it Verifica se p \'e n\'o externo}@
    private boolean eExterno(PatNo p) {
        Class classe = p.getClass();
        return classe.getName().equals(PatNoExt.class.getName());
    }

    private PatNo criaNoInt(int i, PatNo esq, PatNo dir) {
        PatNoInt p = new PatNoInt();
        p.index = i;
        p.esq = esq;
        p.dir = dir;
        return p;
    }

    private PatNo criaNoExt(String k,int linhas, int colunas) {
        PatNoExt p = new PatNoExt();
        p.chave = k;
		p.linha.add(linhas);
		p.coluna.add(colunas);
        return p;
    }

    private void pesquisa(String k, PatNo t) {
        if (this.eExterno(t)) {
            PatNoExt aux = (PatNoExt) t;
            if (aux.chave.equals(k)) {
                System.out.println("Elemento encontrado");
                for (int i = 0; i < aux.linha.size(); i++) {
                    System.out.println("Linha: " + aux.linha.get(i) + " Coluna: " + aux.coluna.get(i));
                }
            } else {
                System.out.println("Elemento nao encontrado");
            }
        } else {
            PatNoInt aux = (PatNoInt) t;
            if (this.bit(aux.index, k) == 0) {
                pesquisa(k, aux.esq);
            } else {
                pesquisa(k, aux.dir);
            }
        }
    }

    private PatNo insereEntre(String k, PatNo t, int i, int linhas, int colunas) {
        PatNoInt aux = null;
        if (!this.eExterno(t)) {
            aux = (PatNoInt) t;
        }
        if (this.eExterno(t) || (i < aux.index)) { // @{\it Cria um novo n\'o externo}@
            PatNo p = this.criaNoExt(k,linhas, colunas);
            if (this.bit(i, k) == 1) {
                return this.criaNoInt(i, t, p);
            } else {
                return this.criaNoInt(i, p, t);
            }
        } else {
            if (this.bit(aux.index, k) == 1) {
                aux.dir = this.insereEntre(k, aux.dir, i, linhas, colunas);
            } else {
                aux.esq = this.insereEntre(k, aux.esq, i,linhas, colunas);
            }
            return aux;
        }
    }

    private PatNo insere(String k, PatNo t, int linhas, int colunas) {
        if (t == null) {
            return this.criaNoExt(k, linhas, colunas);
        } else {
            PatNo p = t;
            while (!this.eExterno(p)) {
                PatNoInt aux = (PatNoInt) p;
                if (this.bit(aux.index, k) == 1) {
                    p = aux.dir;
                } else {
                    p = aux.esq;
                }
            }
            PatNoExt aux = (PatNoExt) p;
            int i = 1; // @{\it acha o primeiro bit diferente}@
            while ((i <= this.nbitsChave)
                    && (this.bit(i, k) == this.bit(i, aux.chave))) {
                i++;
            }
            if (i > this.nbitsChave) {
                aux.linha.add(linhas);
                aux.coluna.add(colunas);
                return t;
            } else {
                return this.insereEntre(k, t, i, linhas, colunas);
            }
        }
    }

    private void central(PatNo pai, PatNo filho, String msg) {
        if (filho != null) {
            if (!this.eExterno(filho)) {
                PatNoInt aux = (PatNoInt) filho;
                central(filho, aux.esq, "ESQ");
                if (pai != null) {
                    System.out.println("Pai: " + ((PatNoInt) pai).index + " " + msg + " Int: " + aux.index);
                } else {
                    System.out.println("Pai: " + pai + " " + msg + " Int: " + aux.index);
                }
                central(filho, aux.dir, "DIR");
            } else {
                PatNoExt aux = (PatNoExt) filho;
                if (pai != null) {
                    System.out.println("Pai: " + ((PatNoInt) pai).index + " " + msg + " Ext: " + aux.chave);
                } else {
                    System.out.println("Pai: " + pai + " " + msg + " Ext: " + aux.chave);
                }
            }
        }
    }

    public void imprime() {
        this.central(null, this.raiz, "RAIZ");
    }

    public ArvorePatricia(int nbitsChave) {
        this.raiz = null;
        this.nbitsChave = nbitsChave;
    }

    public void pesquisa(String k) {
        this.pesquisa(k, this.raiz);
    }

    public void insere(String k, int linhas, int colunas) {
        this.raiz = this.insere(k, this.raiz, linhas, colunas);
    }
}