/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arvoreminima;

/**
 *
 * @author user
 */
public class XAGM {
      private int antecessor[];
      private double p[];
      private XGrafo grafo;
      public int pesoT = 0;
    
      public int getPesoT() {
        return pesoT;
      }
      
      public XAGM (XGrafo grafo) { this.grafo = grafo; }  
      public void obterAgm (int raiz) throws Exception {
        int n = this.grafo.numVertices();
        this.p = new double[n]; // @{\it peso dos v\'ertices}@
        int vs[] = new int[n+1]; // @{\it v\'ertices}@
        boolean itensHeap[] = new boolean[n]; this.antecessor = new int[n];
        for (int u = 0; u < n; u ++) {
          this.antecessor[u] = -1;
          p[u] = Double.MAX_VALUE; // @$\infty$@
          vs[u+1] = u; // @{\it Heap indireto a ser constru\'{\i}do}@
          itensHeap[u] = true;
        }
        p[raiz] = 0;
        FPHeapMinIndireto heap = new FPHeapMinIndireto (p, vs); 
        heap.constroi ();
        while (!heap.vazio ()) {
          int u = heap.retiraMin (); itensHeap[u] = false;
          if (!this.grafo.listaAdjVazia (u)) {
            XGrafo.Aresta adj = grafo.primeiroListaAdj (u);
            while (adj != null) {
              int v = adj.v2 ();
              if (itensHeap[v] && (adj.peso () < this.peso (v))) {
                antecessor[v] = u; heap.diminuiChave (v, adj.peso ());
              }
              adj = grafo.proxAdj (u);
            }
          }
        }
      }
      public int antecessor (int u) { return this.antecessor[u]; }
      public double peso (int u) { return this.p[u]; }
      public void imprime () {
        for (int u = 0; u < this.p.length; u++)
          if (this.antecessor[u] != -1) 
            System.out.println ("(" +antecessor[u]+ "," +u+ ") -- p:" +
                                peso (u));
      }
      public void calcularPeso () {
        for (int u = 0; u < this.p.length; u++)
          if (this.antecessor[u] != -1) 
            pesoT+= peso(u);
      }
  }
    
