package longesttokensubsequences;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class Tokenizer
{

    public static void main(String[] argv) throws Exception
    {
        new Tokenizer().tokenize("Teste2.java");
    }

    private static void m2() throws FileNotFoundException, IOException
    {
        FileReader rd = new FileReader("Teste2.java");

        StreamTokenizer st = new StreamTokenizer(rd);
    }

    public List<String[]> tokenize(String filePath) throws FileNotFoundException, IOException
    {
        FileReader rd = new FileReader(filePath);
        StreamTokenizer st = new StreamTokenizer(rd);

        st.parseNumbers();
        st.wordChars('_', '_');

        st.eolIsSignificant(true);
        // If whitespace is not to be discarded, make this call
//        st.ordinaryChars(0, ' ');
//        st.ordinaryChars(0, '.');

        // These calls caused comments to be discarded
        st.slashSlashComments(true);
        st.slashStarComments(true);

        List<String[]> fileLines = new ArrayList<>();
        List<String> singleLine = new ArrayList<>();

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
//                    System.out.println(st.nval);
                    singleLine.add(String.valueOf(st.nval));
                    break;
                case StreamTokenizer.TT_WORD:
                    String word = st.sval;
//                    System.out.println(word);
                    singleLine.add(word);
                    break;
                case '"':
                    String dquoteVal = st.sval;
//                    System.out.println(token);
                    singleLine.add("\"" + dquoteVal + "\"");
//                    System.out.println(dquoteVal);                    
                    break;
                case '\'':
                    String squoteVal = st.sval;
//                    System.out.println(token);
//                    System.out.println(squoteVal);
                    singleLine.add("\'" + squoteVal + "\'");
                    break;
                case StreamTokenizer.TT_EOL: // End of line character found
                    if (!singleLine.isEmpty())
                    {
                        fileLines.add(singleLine.toArray(new String[singleLine.size()]));
                    }
                    singleLine.clear();
                    break;
                case StreamTokenizer.TT_EOF: // End of file has been reached
                    if (!singleLine.isEmpty())
                    {
                        fileLines.add(singleLine.toArray(new String[singleLine.size()]));
                    }
                    break;
                default:
                    char ch = (char) st.ttype;
                    singleLine.add(String.valueOf(ch));
//                    System.out.println(ch);
                    break;
            }
        }
        rd.close();
        return fileLines;
    }
}
