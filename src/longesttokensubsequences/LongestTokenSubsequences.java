package longesttokensubsequences;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristiano Borges Cardoso
 */
public class LongestTokenSubsequences
{

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException
    {
        long startTime = System.nanoTime();

        Conversor conversor = new Conversor(new FileInputStream(args[0]));

        List<String[]> allLines = new ArrayList<>();

        for (String fileName : conversor.ConverterInputStreamToStringPorLinha())
        {
//            for (String[] line : new Tokenizer().tokenize(fileName))
//            {
//                System.out.println(Arrays.toString(line));
//            }
            allLines.addAll(new Tokenizer().tokenize(fileName));
        }
        System.out.println(new LCTS().getLTCSOcurrences(allLines));
        System.out.println("Elapsed time: " + (double) (System.nanoTime() - startTime) / 1000000000.0);
    }
   
}
