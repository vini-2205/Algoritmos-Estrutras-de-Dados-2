/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package item;

import java.util.Random;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicComboBoxUI.ItemHandler;

/**
 *
 * @author user
 */
public class Main {

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        int opcao;
        int max = 10000;
        System.out.println("Numeros em ordem digite crescente(0)\n"// menu para selecionarmos qual tipo de inserção
                                                                   // gostariamos na árvore
                + "Numeros em ordem decrescente(1)\n"
                + "Numeros aleatorios digite (2)");
        opcao = ler.nextInt();//ler do teclado

        if (opcao == 0) {// opcao para inserir numero em ordem
            for (int j = 0; j < 10; j++) {// esse for irá criar as 9 , de 10000 até 100000
                int v[] = new int[max];//criar um vetor de inteiros
                Item[] crescente = new Item[max];//armazenar esse vetor de interios em um vetor de itens
                for (int i = 0; i < max; i++) {// irá inserir os números em ordem
                    v[i] = i;
                    crescente[i] = new Item(v[i]);
                }
                HeapSort fpHeap = new HeapSort(crescente);//criar um heap com o vetor de itens
                fpHeap.heapSort(max - 1);
                System.out.printf("Numero de elementos: %d ", max);
                System.out.printf("Quantidade: %d \n", fpHeap.getFpHeap().getComp());// printa o numero de comparacoes
                                                                                     // feita
                max = max + 10000;
            }
        }
        if (opcao == 1) {// opcao para inserir numero em ordem
            for (int j = 0; j < 10; j++) {// esse for irá criar as 9 , de 10000 até 100000
                int v[] = new int[max];
                int ordenar = max - 1;
                Item[] decrescente = new Item[max];
                for (int i = 0; i < max; i++) {//irá inserir em ordem decrescente
                    v[i] = ordenar;
                    decrescente[i] = new Item(v[i]);
                    ordenar--;
                }
                HeapSort fpHeap = new HeapSort(decrescente);
                fpHeap.heapSort(max - 1);
                System.out.printf("Numero de elementos: %d ", max);
                System.out.printf("Quantidade: %d \n", fpHeap.getFpHeap().getComp());// printa o numero de comparacoes
                                                                                     // feitas
                max = max + 10000;
            }
        }
        if (opcao == 2) {
            for (int j = 0; j < 10; j++) {
                Random gerador = new Random();// gerar numeros aleatorios
                int v[] = new int[max];
                Item[] aleatorio = new Item[max];
                for (int i = 0; i < max; i++) {//inserir em ordem aleatória
                    v[i] = gerador.nextInt();
                    aleatorio[i] = new Item(v[i]);
                }
                HeapSort fpHeap = new HeapSort(aleatorio);
                fpHeap.heapSort(max - 1);
                System.out.printf("Numero de elementos: %d ", max);
                System.out.printf("Quantidade: %d \n", fpHeap.getFpHeap().getComp());// printa o numero de comparacoes
                                                                                     // feitas
                max = max + 10000;
            }
        }
    }
}
