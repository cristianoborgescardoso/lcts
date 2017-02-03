package longesttokensubsequences;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristiano Borges Cardoso 
 */
public class Conversor
{

    private InputStream arquivoDeTexto;

    /**
     *
     * @param umArquivoDeTexto
     */
    public Conversor(InputStream umArquivoDeTexto)
    {
        this.arquivoDeTexto = umArquivoDeTexto;
    }

    /**
     *
     * @param umaClasseNoMesmoDiretorio
     * @param nomeDoArquivoPontoTipo
     */
    public Conversor(Class umaClasseNoMesmoDiretorio, String nomeDoArquivoPontoTipo)
    {
        this.arquivoDeTexto = umaClasseNoMesmoDiretorio.getResourceAsStream(nomeDoArquivoPontoTipo);

    }

    /**
     *
     * @param nomeDoArquivoPontoTipo
     * @return
     */
    public static InputStream getInputStream(String nomeDoArquivoPontoTipo)
    {
        return new Conversor().getClass().getResourceAsStream(nomeDoArquivoPontoTipo);
    }

    /**
     *
     * @param umaClasseNoMesmoDiretorio
     * @param nomeDoArquivoPontoTipo
     * @return
     */
    public static InputStream getInputStream(Class umaClasseNoMesmoDiretorio, String nomeDoArquivoPontoTipo)
    {
        return umaClasseNoMesmoDiretorio.getResourceAsStream(nomeDoArquivoPontoTipo);
    }

    /**
     *
     * @return @throws IOException
     */
    public String ConverterInputStreamToString() throws IOException
    {

        if (arquivoDeTexto != null)
        {
            Writer writer = new StringWriter();
            char[] buffer = new char[1024];
            try
            {
                Reader reader = new BufferedReader(
                        new InputStreamReader(arquivoDeTexto, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1)
                {
                    writer.write(buffer, 0, n);
                }
            } finally
            {
                arquivoDeTexto.close();
            }
            return writer.toString();
        } else
        {
            return "";
        }
    }

    /**
     *
     * @return @throws IOException
     */
    public List<String> ConverterInputStreamToStringPorLinha() throws IOException
    {

        List<String> linhas = new ArrayList<String>();
        if (arquivoDeTexto != null)
        {

            BufferedReader leitor = new BufferedReader(
                    new InputStreamReader(arquivoDeTexto, "UTF-8"));
            String linha = null;
            try
            {
                while (leitor.ready())
                {
                    linhas.add(leitor.readLine());
                }

            } finally
            {
                arquivoDeTexto.close();
            }
            return linhas;
        } else
        {
            return null;
        }
    }

    /**
     *
     */
    public Conversor()
    {
    }

    /**
     *
     * @return @throws IOException
     */
    public static String getModeloStringNomesPragas() throws IOException
    {
        return new Conversor(new Conversor().getClass(), "pragas.txt").ConverterInputStreamToString();
    }

    /**
     *
     * @return @throws IOException
     */
    public static List<String> getModeloStringNomesPragasList() throws IOException
    {
        return new Conversor(new Conversor().getClass(), "pragas.txt").ConverterInputStreamToStringPorLinha();
    }
}
