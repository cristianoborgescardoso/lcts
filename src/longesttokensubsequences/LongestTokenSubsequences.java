/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longesttokensubsequences;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author cbcardoso
 */
public class LongestTokenSubsequences
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {
        List<String[]> allLines = new ArrayList<>();
        // TODO code application logic here
        Conversor conversor = new Conversor(new FileInputStream("fileList"));
        for (String fileName : conversor.ConverterInputStreamToStringPorLinha())
        {
//            for (String[] line : new Tokenizer().tokenize(fileName))
//            {
//                System.out.println(Arrays.toString(line));
//            }
            allLines.addAll(new Tokenizer().tokenize(fileName));
        }
        System.out.println(new LCTS().getLTCSOcurrences(allLines));
    }
}
