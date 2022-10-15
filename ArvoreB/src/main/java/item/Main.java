
package item;

public class Main {

    public static void main(String[] args){
        Item item, pesquisa;
        int num,comparacao,max=10000;
        int opcao = 2;
        for(int i=0;i<3;i++){//criará as árvores de ordem2,4 e 6
            System.out.printf("\nOrdem da árvore é %d\n",opcao);
            for (int j = 0; j < 10; j++) {//esse for irá criar as 10 árvores, de 10000 até 100000
                ArvoreB arvore = new ArvoreB(opcao);
                for (int k = 0; k < max; k++) {//irá inserir os números em ordem na arvore
                    num = k;
                    item = new Item(num);
                    arvore.insere(item);
                }
                System.out.printf("Numero de elementos: %d ", max);
                max = max + 10000;//para a proxima árvore será incrementado 10000
                pesquisa = new Item(120000);//pesquisará o item 120000 para ver quantas comparaçoes  eo tempo gasto
                arvore.pesquisa(pesquisa);//pesquisar
                comparacao = arvore.getNumComparacoes();
                System.out.printf("Quantidade: %d \n", comparacao);//printa o numero de comparacoes feitas
            }
            max = 10000;
            opcao = opcao+2;
        }
    }
}
