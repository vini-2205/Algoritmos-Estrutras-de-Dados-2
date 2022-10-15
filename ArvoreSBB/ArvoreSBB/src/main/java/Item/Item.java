package Item;

public class Item {

    private int chave;

    public Item(int chave) {
        this.chave = chave;//cada item que será inseido na Arvore é criado por essa classe e com uma chave tendo seu valor
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
