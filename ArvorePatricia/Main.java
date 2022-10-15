
import java.util.Scanner;
import java.nio.file.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArvorePatricia arvore = new ArvorePatricia(128);
        // char vetor[] = new char[128];
        System.out.println("Digite o arquivo:");
        String arquivo;
        arquivo = in.next();

        try {
            ExtraiPalavra palavras = new ExtraiPalavra(arquivo);
            String palavra = null;

            while ((palavra = palavras.proximaPalavra()) != null) {

                if (!palavra.equals("")) {
                    String resposta = "";
                    if (palavra.length() < 16) {
                        for (int k = palavra.length(); k < 16; k++) { // incrementa o tamanho da palavra com espaços
                                                                      // vazios para completar 16 caracteres
                            palavra += "0";
                        }
                        for (int k = 0; k < 16; k++) {

                            // instancia um "binary" que recebe a conversão do caracter 'k' em um número
                            // binário
                            String binary = Integer.toBinaryString((int) palavra.charAt(k));

                            if (binary.length() < 8) {
                                for (int j = 0; j < (8 - binary.length()); j++) {
                                    resposta += "0";
                                }
                            }
                            resposta += binary;
                        }
                    }
                    int linhas = palavras.getLinha();
                    int colunas = palavras.getColuna();
                    arvore.insere(resposta, linhas, colunas);
                }
            }
            palavras.fecharArquivos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String pesquisa = "S";
        System.out.println("Digite a palvra que gostaria de buscar: (Caso não deseje mais procurar palavra digite N)");
        pesquisa = in.next();
        while (!pesquisa.equals("N")) {
            String resposta = "";
            if (pesquisa.length() < 16) {
                for (int k = pesquisa.length(); k < 16; k++) { // incrementa o tamanho da palavra com espaços vazios
                                                               // para completar 16 caracteres
                    pesquisa += "0";
                }
                for (int k = 0; k < 16; k++) {

                    // instancia um "binary" que recebe a conversão do caracter 'k' em um número
                    // binário
                    String binary = Integer.toBinaryString((int) pesquisa.charAt(k));

                    if (binary.length() < 8) {
                        for (int j = 0; j < (8 - binary.length()); j++) {
                            resposta += "0";
                        }
                    }
                    resposta += binary;
                }
                // arvore.imprime();
                // System.out.println("Resposta: " + resposta);
                arvore.pesquisa(resposta);
                System.out.println("");
            }
            System.out.println("Caso queira interromper digite N");
            System.out.println("Digite a palvra que gostaria de buscar: ");
            pesquisa = in.next();
        }
    }
}