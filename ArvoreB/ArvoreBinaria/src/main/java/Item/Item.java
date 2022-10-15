/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Item;

public class Item {

    private int chave;

    public Item(int chave) {
        this.chave = chave;//Colocar a numeracao no item
    }

    public int compara(Item it) {//esse metodo serve para compara o item com algum outro na arvore
        Item item = it;
        if (this.chave < item.chave) {
            return -1;
        } else if (this.chave > item.chave) {
            return 1;
        }
        return 0;
    }

    public int getChave() {
        return chave;
    }
}
