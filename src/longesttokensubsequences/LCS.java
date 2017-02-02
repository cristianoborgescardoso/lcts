/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longesttokensubsequences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author cbcardoso
 */
public class LCS
{

    private Map<String, LCTSOccurences> lctsMap = new HashMap();

    private static enum Arrow
    {
        UP,
        UP_LEFT,
        LEFT;
    }

    public static void main(String[] args)
    {
        String X = "ABCDABCADBC";
        String Y = "AXBDCABC";
        String[] arrX =
        {
            "A", "B", "C", "D", "A", "B", "C", "A", "D", "B", "C"
        };
        String[] arrY =
        {
            "A", "X", "B", "D", "C", "A", "B", "C"
        };
        LCS driver = new LCS();
        int[][] dpLCS = driver.dpLCS(X, Y);
        System.out.println(driver.backtrack(dpLCS, X.toCharArray(), Y.toCharArray(), X.length(), Y.length()));
        new LCS().getLCS(arrX, arrY);
    }

    private StringBuilder backtrack(int[][] dpTable, char[] charX, char[] charY, int i, int j)
    {
        StringBuilder sb = new StringBuilder();
        if (i == 0 || j == 0)
        {
            return sb;
        } else if (charX[i - 1] == charY[j - 1])
        {
            return backtrack(dpTable, charX, charY, i - 1, j - 1).append(charX[i - 1]);
        } else if (dpTable[i][j - 1] > dpTable[i - 1][j])
        {
            return backtrack(dpTable, charX, charY, i, j - 1);
        } else
        {
            return backtrack(dpTable, charX, charY, i - 1, j);
        }
    }

    private int[][] dpLCS(String X, String Y)
    {
        if (X == null || X.length() <= 0 || Y == null || Y.length() <= 0)
        {
            return null;
        }
        int[][] dpTable = new int[X.length() + 1][Y.length() + 1];
        char[] charX = X.toCharArray();
        char[] charY = Y.toCharArray();
        for (int i = 0; i < charX.length; i++)
        {
            for (int j = 0; j < charY.length; j++)
            {
                if (charX[i] == charY[j])
                {
                    dpTable[i + 1][j + 1] = dpTable[i][j] + 1;
                } else if (charX[i] != charY[j])
                {
                    dpTable[i + 1][j + 1] = Math.max(dpTable[i + 1][j], dpTable[i][j + 1]);
                }
            }
        }
        return dpTable;
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
//                if (lineX[i].equals(lineY[j]))
//                {
//                    c[i][j] = c[i - 1][j - 1] + 1;
//                    b[i][j] = Arrow.UP_LEFT;
//                } else if (c[i - 1][j] >= c[i][j - 1])
//                {
//                    c[i][j] = c[i - 1][j];
//                    b[i][j] = Arrow.UP;
//                } else
//                {
//                    c[i][j] = c[i][j - 1];
//                    b[i][j] = Arrow.LEFT;
//                }
            }
        }
        List<String> tokens = new ArrayList<>();
        printLCS(b, lineX, m - 1, n - 1, tokens);
        tokens.stream().forEach((token)
                -> 
                {
                    System.out.print(token);
        });
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

    private void addLTCS(List<String> tokenList)
    {
        StringBuilder mergedTokens = new StringBuilder();

        for (String token : tokenList)
        {
            mergedTokens.append(token);
        }
        LCTSOccurences lctsOccurences = lctsMap.get(mergedTokens.toString());

        if (lctsOccurences != null)
        {
            lctsOccurences.increaseOccurences();
        } else
        {
            lctsMap.put(mergedTokens.toString(), new LCTSOccurences(tokenList, 1));
        }
    }

    private class LCTSOccurences
    {

        private final List<String> tokenSubsequence;
        private int occurences;

        public LCTSOccurences(List<String> tokenSubsequence, int occurences)
        {
            this.tokenSubsequence = tokenSubsequence;
            this.occurences = occurences;
        }

        public List<String> getTokenSubsequence()
        {
            return tokenSubsequence;
        }

        public int getOccurences()
        {
            return occurences;
        }

        public int increaseOccurences()
        {
            return occurences;
        }
    }

    private String getLTCSOcurrences(List<String[]> lines)
    {
        for (int i = 0; i < lines.size(); i++)
        {
            for (int j = i + 1; j < lines.size(); j++)
            {
                addLTCS(getLCS(lines.get(i), lines.get(j)));
            }
        }

    }

}