package item;

public class Item {

    private int chave;

    public Item(int chave) {
        this.chave = chave;
    }

    public int compara(Item it) {
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

    public void setChave(Object chave) {
        this.chave = (int)chave;
    }

}
