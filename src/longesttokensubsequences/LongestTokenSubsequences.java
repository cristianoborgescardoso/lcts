/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longesttokensubsequences;

import java.io.IOException;
import java.util.Arrays;

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
        // TODO code application logic here
        for (String[] line : new Tokenizer().tokenize("Teste2.java"))
        {
            System.out.println(Arrays.toString(line));
        }
        System.out.println(new LCTS().getLTCSOcurrences(new Tokenizer().tokenize("Teste2.java")));
    }

}
