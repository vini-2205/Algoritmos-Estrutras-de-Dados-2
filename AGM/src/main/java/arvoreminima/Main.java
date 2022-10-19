/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arvoreminima;

import java.util.Scanner;

/**
 *
 * @author user
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Scanner ler = new Scanner(System.in);
        System.out.print("No. vertices: ");
        int nVertices = ler.nextInt();
        System.out.print("No. arestas: ");
        int nArestas = ler.nextInt();
        System.out.print("Raiz: ");
        int raiz = ler.nextInt();
        XGrafo grafo = new XGrafo(nVertices);
        System.out.println("Digite os pares v1,v2 e o Peso: ");
        for (int i = 0; i < nArestas; i++) {// insercao dos vertices e pesos
            int v1 = ler.nextInt();
            int v2 = ler.nextInt();
            int peso = ler.nextInt();
            grafo.insereAresta(v1, v2, peso); // @{\it Duas chamadas porque}@
            grafo.insereAresta(v2, v1, peso); // @{\it grafo n\~ao direcionado}@
        }
        grafo.imprime();
        XAGM dj = new XAGM(grafo);
        dj.obterAgm(raiz);
        dj.calcularPeso();
        System.out.println("O peso da arvore eh: " + dj.getPesoT());
    }
}
