
import java.util.StringTokenizer;
import java.io.*;
public class ExtraiPalavra {
  private BufferedReader arqTxt;
  private StringTokenizer palavras;
  //private String delimitadores;
  private int linha=0;
  private int coluna=0;

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }


  public ExtraiPalavra (String nomeArqTxt) 
  throws Exception {
    //this.arqDelim = new BufferedReader (new FileReader (nomeArqDelim));
    this.arqTxt = new BufferedReader (new FileReader (nomeArqTxt));
    this.palavras = null;
  }  
  public String proximaPalavra () throws Exception{
    if (palavras == null || !palavras.hasMoreTokens()) {
        coluna=0;
        linha++;
      String linha = arqTxt.readLine();
      if (linha == null) return null; 
      this.palavras = new StringTokenizer (linha, " /()-,.?1234567890*");
      if (!palavras.hasMoreTokens()) return ""; // @{\it ignora delimitadores}@
    }
    coluna++;
    return this.palavras.nextToken ();
  }  
  public void fecharArquivos () throws Exception {
    //this.arqDelim.close(); 
    this.arqTxt.close();
  }
}