package longesttokensubsequences;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;

public class Teste
{

    public static void main(String[] argv) throws Exception
    {
        m1();
    }

    private static void m2() throws FileNotFoundException, IOException
    {
        FileReader rd = new FileReader("Teste2.java");
        
        StreamTokenizer st = new StreamTokenizer(rd);
    }

    private static void m1() throws FileNotFoundException, IOException
    {
        FileReader rd = new FileReader("Teste2.java");
        StreamTokenizer st = new StreamTokenizer(rd);

        st.parseNumbers();
        st.wordChars('_', '_');
        
        st.eolIsSignificant(true);        
        // If whitespace is not to be discarded, make this call
        st.ordinaryChars(0, ' '); 
        st.ordinaryChars(0, '.'); 
        
        // These calls caused comments to be discarded
        st.slashSlashComments(true);
        st.slashStarComments(true);

        int token = st.nextToken();
        while (token != StreamTokenizer.TT_EOF)
        {
            token = st.nextToken();
//            System.out.println(token);
            switch (token)
            {
                case StreamTokenizer.TT_NUMBER:
                    double num = st.nval;
//                    System.out.println(num);
                    System.out.println(st.nval);
                    break;
                case StreamTokenizer.TT_WORD:
                    String word = st.sval;
                    System.out.println(word);
                    break;
                case '"':
                    String dquoteVal = st.sval;
//                    System.out.println(token);
                    System.out.println(dquoteVal);
                    break;
                case '\'':
                    String squoteVal = st.sval;
//                    System.out.println(token);
                    System.out.println(squoteVal);
                    break;
                case StreamTokenizer.TT_EOL: // End of line character found
                    break;
                case StreamTokenizer.TT_EOF: // End of file has been reached
                    break;
                default:
                    char ch = (char) st.ttype;
                    System.out.println(ch);
                    break;
            }
        }
        rd.close();
    }
}
