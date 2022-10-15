/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Item;

public class ArvoreBinaria {

    private static class No {//cada no vai ter o item que esta nele e os seus descendentes, sendo esq e direita

        Item reg;
        No esq, dir;
    }
    private No raiz;

    public static int comparacoes = 0;

    private Item pesquisa(Item reg, No p) { //esse metodo ira pesquisar algum item na arvore, sendo apssado o item que deseja encontrar
        comparacoes++;
        if (p == null) {//caso alguma posicao for nula, o item não existe
            return null; // @{\it Registro n\~ao econtrado}@
        } else if (reg.compara(p.reg) < 0) {//se o item for menor que o a posicao do No ele vai para a esquerda
            return pesquisa(reg, p.esq);
        } else if (reg.compara(p.reg) > 0) {//se o item for maior que o a posicao do No ele vai para a direita
            return pesquisa(reg, p.dir);
        } else {
            return p.reg;
        }
    }

    private No insere(Item reg, No p) {//esse metodo serve para inserir algum item na arvore

        if (p == null) {//se encontrar alguma posicao vazia, sera onde o item sera inserido
            p = new No();
            p.reg = reg;
            p.esq = null;
            p.dir = null;
        } else if (reg.compara(p.reg) < 0) {//analisa se o item escirto é menor que o No existente
            p.esq = insere(reg, p.esq);
        } else if (reg.compara(p.reg) > 0) {//analisa se o item escirto é menor que o No existente
            p.dir = insere(reg, p.dir);
        } else {
            System.out.println("Erro: Registro ja existente");
        }
        return p;
    }

    public void insere(Item reg) {//metodo chamaddo para inserir
        this.raiz = this.insere(reg, this.raiz);
    }

    public Item pesquisa(Item reg) {//metodo chamaddo para pesquisar
        return this.pesquisa(reg, this.raiz);
    }

}
