/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Item;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class Main {
    public static void main (String[] args) {
        Scanner ler = new Scanner(System.in);
        Item item, pesquisa;
        int num, opcao;
        int max = 10000;
        System.out.println("Numeros em ordem digite (0)\n"//menu para selecionarmos qual tipo de inserção gostariamos na árvore
                + "Numeros aleatorios digite (1)");
        opcao = ler.nextInt();

        if (opcao == 0) {//opcao para inserir numero em ordem 
            for (int j = 0; j < 10; j++) {//esse for irá criar as 9 árvores, de 1000 até 9000
                ArvoreSBB arvore = new ArvoreSBB();
                for (int i = 0; i < max; i++) {//irá inserir os números em ordem na arvore
                    num = i;
                    item = new Item(num);
                    arvore.insere(item);
                }
                System.out.printf("Numero de elementos: %d ", max);
                max = max + 10000;//para a proxima árvore será incrementado 1000
                pesquisa = new Item(10000);//pesquisará o item 10000 para ver quantas comparaçoes  eo tempo gasto
                long tempo = System.nanoTime();//tempo gasto até entrar na função pesquisa
                arvore.pesquisa(pesquisa);//pesquisar
                long tempo2 = System.nanoTime();//tempo gasto após a funcao pesquisa
                System.out.printf("Quantidade: %d ", arvore.comparacoes);//printa o numero de comparacoes feitas
                System.out.printf("Tempo: %d\n", (tempo2 - tempo));//subtrair o tempo gasto antes da funcao e o depois
                arvore.comparacoes = 0;
            }
        }
        if (opcao == 1) {
            for (int j = 0; j < 10; j++) {
                ArvoreSBB arvore = new ArvoreSBB();
                Random gerador = new Random();//gerar numeros aleatorios
                for (int i = 0; i < max; i++) {
                    num = gerador.nextInt();//gerar numeros aleatorios
                    item = new Item(num);
                    arvore.insere(item);
                }
                System.out.printf("Numero de elementos: %d ", max);
                max = max + 10000;
                pesquisa = new Item(10000);
                long tempo = System.nanoTime();
                arvore.pesquisa(pesquisa);
                long tempo2 = System.nanoTime();
                System.out.printf("Quantidade: %d ", arvore.comparacoes);
                System.out.printf("Tempo: %d\n", (tempo2 - tempo));
                arvore.comparacoes = 0;
            }
        }
    }
}
