
package item;

public class ArvoreB {//construir a árvore
  private static class Pagina {
    int n; Item r[]; Pagina p[]; 
    public Pagina (int mm) {
      this.n = 0; this.r = new Item[mm]; this.p = new Pagina[mm+1];
    }
  }
  private Pagina raiz;
  private int m, mm;
  
  private int numComparacoes=0;//saber quantas comparações foram feitas
  
    public int getNumComparacoes() {
        return numComparacoes;
    }
    
  private Item pesquisa (Item reg, Pagina ap) {//pesquisar um elemento na árvore
    numComparacoes++;
    if (ap == null) return null; // Registro @{\it n\~ao}@ econtrado
    else {
      int i = 0;
      while ((i < ap.n-1) && (reg.compara (ap.r[i]) > 0)) i++; //procurar até não encontrar o último elemtno da árvore
      if (reg.compara (ap.r[i]) == 0) return ap.r[i];
      else if (reg.compara (ap.r[i]) < 0) return pesquisa (reg, ap.p[i]);
      else return pesquisa (reg, ap.p[i+1]);//chamar recursivamente
    }
  }
  
  private void insereNaPagina (Pagina ap, Item reg, Pagina apDir) {//inserir o elemento na página
    int k = ap.n - 1;
    while ((k >= 0) && (reg.compara (ap.r[k]) < 0)) { //procurar a posição para a inserção do elemento na paágina
      ap.r[k+1] = ap.r[k]; ap.p[k+2] = ap.p[k+1]; k--; 
    }
    ap.r[k+1] = reg; ap.p[k+2] = apDir; ap.n++;     
  }
  
  private Pagina insere (Item reg, Pagina ap, Item[] regRetorno, //inserir um elmento na árvore
                         boolean[] cresceu) {
    Pagina apRetorno = null;
    if (ap == null) { cresceu[0] = true; regRetorno[0] = reg; }  
    else {
      int i = 0;
      while ((i < ap.n-1) && (reg.compara (ap.r[i]) > 0)) i++;      
      if (reg.compara (ap.r[i]) == 0) {
        System.out.println ("Erro: Registro ja existente");
        cresceu[0] = false;
      } 
      else {
        if (reg.compara (ap.r[i]) > 0) i++;
        apRetorno = insere (reg, ap.p[i], regRetorno, cresceu);
        if (cresceu[0])          
          if (ap.n < this.mm) { // @{\it P\'agina tem espa\c{c}o}@
            this.insereNaPagina (ap, regRetorno[0], apRetorno);
            cresceu[0] = false; apRetorno = ap;
          } 
          else { // Overflow: @{\it P\'agina tem que ser dividida}@
            Pagina apTemp = new Pagina (this.mm); apTemp.p[0] = null;
            if (i <= this.m) {
              this.insereNaPagina (apTemp, ap.r[this.mm-1], ap.p[this.mm]);
              ap.n--;
              this.insereNaPagina (ap, regRetorno[0], apRetorno);
            } else this.insereNaPagina (apTemp, regRetorno[0], apRetorno);
            for (int j = this.m+1; j < this.mm; j++) {
              this.insereNaPagina (apTemp, ap.r[j], ap.p[j+1]);
              ap.p[j+1] = null; // @{\it transfere a posse da mem\'oria}@
            }
            ap.n = this.m; apTemp.p[0] = ap.p[this.m+1]; 
            regRetorno[0] = ap.r[this.m]; apRetorno = apTemp;
          }
      }        
    }
    return (cresceu[0] ? apRetorno : ap);
  }

  public ArvoreB (int m) { //ccontrutor da árvore
    this.raiz = null; this.m = m; this.mm = 2*m;
  }
  
  public Item pesquisa (Item reg) {
    return this.pesquisa (reg, this.raiz);
  }

  public void insere (Item reg) {
    Item regRetorno[] = new Item[1];
    boolean cresceu[] = new boolean[1];
    Pagina apRetorno = this.insere (reg, this.raiz, regRetorno, cresceu);
    if (cresceu[0]) {
      Pagina apTemp = new Pagina(this.mm);
      apTemp.r[0] = regRetorno[0];
      apTemp.p[0] = this.raiz;
      apTemp.p[1] = apRetorno;
      this.raiz = apTemp; this.raiz.n++;
    } else this.raiz = apRetorno;
  }

}

