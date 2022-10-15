/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package item;

/**
 *
 * @author user
 */
public class HeapSort {

    private Heap fpHeap;

    public void heapSort(int n) {
        int dir = n;
        while (dir > 1) { // @{\it ordena o vetor}@//ordenação do heap
            Item x = fpHeap.getV(1);
            fpHeap.setV(fpHeap.getV(dir), 1);//olhar o número a direita para saber em qual poosição colocar
            fpHeap.setV(x, dir);
            dir--;
            fpHeap.refaz(1, dir);
        }
    }

    public HeapSort(Item v[]) { //construtor do heap, passando como parêmetros o vetor.
        this.fpHeap = new Heap(v);//criar um heap
        this.fpHeap.constroi(); // @{\it constroi o heap}@
    }

    public Heap getFpHeap() {
        return fpHeap;
    }

    public void setFpHeap(Heap fpHeap) {
        this.fpHeap = fpHeap;
    }

}