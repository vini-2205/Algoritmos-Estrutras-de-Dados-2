/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Item;

public class ArvoreSBB {
  private static class No { 
    Item reg; 
    No esq, dir; //cada no pode ter item outro no a sua esquerda ou a direita
    byte incE, incD; //por organização da árvore temos a inclinacao a direita ou a esquerda
  }
  private static final byte Horizontal = 0; //avaliar se quantos caminhos horizontais consecutivos existem
  private static final byte Vertical = 1; 
  private No raiz;
  private boolean propSBB;
  public int comparacoes = 0;

  private Item pesquisa (Item reg, No p) {//esse metodo ira pesquisar algum item na arvore, sendo apssado o item que deseja encontrar
    comparacoes++;//somar as comparações
    if (p == null) return null; // @{\it Registro n\~ao econtrado}@
    else if (reg.compara (p.reg) < 0) return pesquisa (reg, p.esq);
    else if (reg.compara (p.reg) > 0) return pesquisa (reg, p.dir);
    else return p.reg;
  }
  
  private No ee (No ap) {//funçõa para ver se a inconsistÊncia é esquerda esquerda
    No ap1 = ap.esq; ap.esq = ap1.dir; ap1.dir = ap;
    ap1.incE = Vertical; ap.incE = Vertical; ap = ap1;
    return ap; 
  }
  
  private No ed (No ap) {//funçõa para ver se a inconsistÊncia é esquerda direita
    No ap1 = ap.esq; No ap2 = ap1.dir; ap1.incD = Vertical;
    ap.incE = Vertical; ap1.dir = ap2.esq; ap2.esq = ap1;
    ap.esq = ap2.dir; ap2.dir = ap; ap = ap2;    
    return ap; 
  }

  private No dd (No ap) {//funçõa para ver se a inconsistÊncia é direita direita
    No ap1 = ap.dir; ap.dir = ap1.esq; ap1.esq = ap;
    ap1.incD = Vertical; ap.incD = Vertical; ap = ap1;
    return ap; 
  }

  private No de (No ap) {//funçõa para ver se a inconsistÊncia é direita esquerda
    No ap1 = ap.dir; No ap2 = ap1.esq; ap1.incE = Vertical;
    ap.incD = Vertical; ap1.esq = ap2.dir; ap2.dir = ap1;
    ap.dir = ap2.esq; ap2.esq = ap; ap = ap2;    
    return ap; 
  }

  private No insere (Item reg, No pai, No filho, boolean filhoEsq) {//inserir elemento
    if (filho == null) { //filho nulo, nó vazio, ou seja, pode inserir
      filho = new No (); filho.reg = reg; 
      filho.incE = Vertical; filho.incD = Vertical;
      filho.esq = null; filho.dir = null;
      if (pai != null)//pai diferente de nulo, significa u nó externo
        if (filhoEsq) pai.incE = Horizontal; else pai.incD = Horizontal;
      this.propSBB = false;
    }
    else if (reg.compara (filho.reg) < 0) { // saber qual lado temos que seguir, nesse caso temos que ir para a esquerda
      filho.esq = insere (reg, filho, filho.esq, true);//chamr a função novamenrte para encontrar um local para a inserção
      if (!this.propSBB) //verificar balanceamento da árvore
        if (filho.incE == Horizontal) { 
          if (filho.esq.incE == Horizontal) {
            filho = this.ee (filho); // @{\it trasforma\c{c}\~ao esquerda-esquerda}@
            if (pai != null)
              if (filhoEsq) pai.incE=Horizontal; else pai.incD=Horizontal;
          }
          else if (filho.esq.incD == Horizontal) {
            filho = this.ed (filho); // @{\it trasforma\c{c}\~ao esquerda-direita}@
            if (pai != null) 
              if (filhoEsq) pai.incE=Horizontal; else pai.incD=Horizontal;            
          }
        }
        else this.propSBB = true;
    }
    else if (reg.compara (filho.reg) > 0) {// saber qual lado temos que seguir, nesse caso temos que ir para a esquerda
      filho.dir = insere (reg, filho, filho.dir, false);//chamr a função novamenrte para encontrar um local para a inserção
      if (!this.propSBB) //verificar balanceamento da árvore
        if (filho.incD == Horizontal) {
          if (filho.dir.incD == Horizontal) {
            filho = this.dd (filho); // @{\it trasforma\c{c}\~ao direita-direita}@
            if (pai != null)
              if (filhoEsq) pai.incE=Horizontal; else pai.incD=Horizontal;
          }
          else if (filho.dir.incE == Horizontal) {
            filho = this.de (filho); // @{\it trasforma\c{c}\~ao direita-esquerda}@
            if (pai != null)
              if (filhoEsq) pai.incE=Horizontal; else pai.incD=Horizontal;            
          }
        }
        else this.propSBB = true;
    }
    else {      
      System.out.println ("Erro: Registro ja existente"); 
      this.propSBB = true;
    }
    return filho; 
  }
  
  public ArvoreSBB () {
    this.raiz = null;
    this.propSBB = true;
  }
  
  public Item pesquisa (Item reg) {
    return this.pesquisa (reg, this.raiz);
  }

  public void insere (Item reg) {
    this.raiz = insere (reg, null, this.raiz, true);
  }

}
