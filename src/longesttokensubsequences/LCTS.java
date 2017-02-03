package longesttokensubsequences;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Cristiano Borges Cardoso 
 */
public class LCTS
{

    private Map<String, LCTSOccurences> lctsMap = new HashMap();

    private static enum Arrow
    {
        UP,
        UP_LEFT,
        LEFT;
    }
    
    private List<String> getLCS(String[] lineX, String[] lineY)
    {
        int m = lineX.length;
        int n = lineY.length;

        Arrow[][] b = new Arrow[m][n];
        int[][] c = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++)
        {
            c[i][0] = 0;
        }
        for (int j = 0; j <= n; j++)
        {
            c[0][j] = 0;
        }

        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (lineX[i].equals(lineY[j]))
                {
                    c[i + 1][j + 1] = c[i][j] + 1;
                    b[i][j] = Arrow.UP_LEFT;
                } else if (c[i][j + 1] >= c[i + 1][j])
                {
                    c[i + 1][j + 1] = c[i][j + 1];
                    b[i][j] = Arrow.UP;
                } else
                {
                    c[i + 1][j + 1] = c[i + 1][j];
                    b[i][j] = Arrow.LEFT;
                }
            }
        }
        List<String> tokens = new ArrayList<>();
        printLCS(b, lineX, m - 1, n - 1, tokens);
//        tokens.stream().forEach((token)
//                -> 
//                {
//                    System.out.print(token);
//        });
        return tokens;

    }

    private void printLCS(Arrow[][] b, String[] lineX, int i, int j, List<String> tokens)
    {
        if (i == -1 || j == -1)
        {
            return;
        }

        switch (b[i][j])
        {
            case UP_LEFT:
                printLCS(b, lineX, i - 1, j - 1, tokens);
                tokens.add(lineX[i]);
//                System.out.print(lineX[i]);
                break;
            case UP:
                printLCS(b, lineX, i - 1, j, tokens);
                break;
            default:
                printLCS(b, lineX, i, j - 1, tokens);
                break;
        }
    }

    private void addLTCS(List<String> tokenList, int i, int j)
    {
        if (tokenList.isEmpty())
        {
            return;
        }

        StringBuilder mergedTokens = new StringBuilder();

        for (String token : tokenList)
        {
            mergedTokens.append(token);
        }

        LCTSOccurences lctsOccurences = lctsMap.get(mergedTokens.toString());

        if (lctsOccurences == null)
        {
            lctsOccurences = new LCTSOccurences(tokenList);
            lctsMap.put(mergedTokens.toString(), lctsOccurences);
        }

        lctsOccurences.increaseOccurences(i);
        lctsOccurences.increaseOccurences(j);
    }

    private class LCTSOccurences
    {

        private final List<String> tokenSubsequence;
        private final HashSet<Integer> lineOcurreces = new HashSet<>();

        public LCTSOccurences(List<String> tokenSubsequence)
        {
            this.tokenSubsequence = tokenSubsequence;
        }

        public List<String> getTokenSubsequence()
        {
            return tokenSubsequence;
        }

        public int getOccurences()
        {
            return lineOcurreces.size();
        }

        public void increaseOccurences(int lineNumber)
        {
            if (!lineOcurreces.contains(lineNumber))
            {
                lineOcurreces.add(lineNumber);
            }
        }
    }

    public String getLTCSOcurrences(List<String[]> lines)
    {
        for (int i = 0; i < lines.size(); i++)
        {
            for (int j = i + 1; j < lines.size(); j++)
            {
                addLTCS(getLCS(lines.get(i), lines.get(j)), i, j);
            }
        }
        List<LCTSOccurences> ocurrencesList = new ArrayList<>();
        ocurrencesList.addAll(lctsMap.values());

        ocurrencesList.sort(Comparator.comparingInt((LCTSOccurences a) -> a.getTokenSubsequence().size()).reversed());

        StringBuilder stringBuilder = new StringBuilder();
        for (LCTSOccurences lctso : ocurrencesList)
        {
//            if (lctso.getOccurences() > 1)
            {
                stringBuilder.append(lctso.getTokenSubsequence().size());
                stringBuilder.append("\t");
                stringBuilder.append(lctso.getOccurences());
                stringBuilder.append("\t");
                for (String token : lctso.getTokenSubsequence())
                {
                    stringBuilder.append(token);
                    stringBuilder.append(" ");
                }
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

}
