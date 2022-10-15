/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pratica5;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner ler = new Scanner(System.in);
        System.out.println("O grafo eh direcionado? Sim(1) Nao(2)");
        int direcionado = ler.nextInt();
        System.out.print("No. vertices: ");
        int nVertices = ler.nextInt();
        System.out.print("No. arestas: ");
        int nArestas = ler.nextInt();
        XGrafo grafo = new XGrafo(nVertices);
        int peso = 1;
        if (direcionado == 2) {
            System.out.println("Digite os pares v1 e v2: ");
            for (int i = 0; i < nArestas; i++) {
                int v1 = ler.nextInt();
                int v2 = ler.nextInt();
                grafo.insereAresta(v1, v2, peso); // @{\it Duas chamadas porque}@
                grafo.insereAresta(v2, v1, peso); // @{\it grafo n\~ao direcionado}@
            }
            grafo.imprime();
            XCiclo dfs = new XCiclo(grafo);
            dfs.buscaEmProfundidade();
            if (dfs.getCiclos() > 0) {
                System.out.println("O grafo tem ciclos.");
            } else {
                System.out.println("O grafo nao tem ciclos.");
            }
        }
        else{
            System.out.println("Digite os pares v1 e v2: ");
            for (int i = 0; i < nArestas; i++) {
                int v1 = ler.nextInt();
                int v2 = ler.nextInt();
                grafo.insereAresta(v1, v2, peso); // @{\it Duas chamadas porque}@
            }
            grafo.imprime();
            XCiclo dfs = new XCiclo(grafo);
            dfs.buscaEmProfundidade();
            if (dfs.getCiclos() > 0) {
                System.out.println("O grafo tem ciclos.");
            } else {
                System.out.println("O grafo nao tem ciclos.");
            }
        }
    }
}
