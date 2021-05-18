import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;


/**
 * HackerRank: Sam and Substrings
 * https://www.hackerrank.com/challenges/sam-and-substrings/problem
 */
public class SamAndSubstrings {


    // **** as required by problem ****
    public static final int MOD = 1000000007;

    // ???? call counter ????
    static int callCounter = 0;


    /**
     * Complete the 'substrings' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING n as parameter.
     * 
     * Entry point.
     */

    public static int substrings0(String s) {

        // **** sanity check(s) ****
        if (s == null | s.length() == 0)
            return 0;

        // **** initialization ****
        int ans     = 0;
        callCounter = 0;

        // **** recursive call ****
        ans = substrings0(s, 1, s.length());

        // ???? display call counter ????
        System.out.println("<<< callCounter: " + callCounter);

        // **** return answer ****
        return ans;
    }


    /**
     * Complete the 'substrings' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING n as parameter.
     * 
     * Recursive call.
     * 
     * !!!! THIS APPROACH IS TOO SLOW !!!!
     */
    public static int substrings0(String s, int len, int maxLen) {

        // ???? count this call ????
        callCounter++;

        // **** end condition ****
        if (len > maxLen)
            return 0;

        // **** initialization ****
        long result = 0;

        // **** compute sum of substrings O(n) ****
        for (int i = 0; i <= maxLen - len; i++) {

            // **** build substring ****
            String str = s.substring(i, i + len);
          
            // **** add to result ****
            result += (Integer.parseInt(str) % MOD);
        }

        // **** recursive call ****
        result += substrings0(s, len + 1, s.length());

        // **** return result ****
        return (int)result;
    }


    /**
     * Complete the 'substrings' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     * 
     * Execution time: O(n)
     */
    public static int substrings(String s) {

        // **** initialization ****
        int n       = s.length();

        long[] a    = new long[n];          // multiples of 10 
        long[] b    = new long[n];          // weighted sum
        b[0] = a[0] = 1;

        long ans    = 0;

        // **** populate arrays O(n) ****
        for (int i = 1; i < n; i++) {
            a[i] = (a[i - 1] * 10) % MOD;
            b[i] = (a[i] + b[i - 1]) % MOD;
        }

        // ???? ????
        System.out.println("<<< a: " + Arrays.toString(a));
        System.out.println("<<< b: " + Arrays.toString(b));

        // **** compute the value associated with the string O(n) ****
        for (int i = 0; i < n; i++) {

            // **** add the contribution of this digit in the string ****
            ans += ((s.charAt(i) - '0') * b[n - i - 1] * (i + 1));

            // **** prevent overflow ****
            ans %= MOD;

            // ???? ????
            System.out.println("<<< i: " + i + " char: '" + s.charAt(i) + "' ans: " + ans);
        }

        // **** return answer (as an integer) ****
        return (int)ans;
    }


    /**
     * Test scaffold.
     * 
     * @throws IOException
     * 
     */
    public static void main(String[] args) throws IOException {

        // **** ****
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        // **** ****
        String n = bufferedReader.readLine();

        bufferedWriter.write("main <<< n ==>" + n + "<== len: " + n.length() + "\n");

        // **** compute result ****
        int result = substrings0(n);

        // **** display result ****
        bufferedWriter.write("main <<< output: " + String.valueOf(result));
        bufferedWriter.newLine();

        // **** compute result ****
        result = (int)substrings(n);

        // **** display result ****
        bufferedWriter.write("main <<< output: " + String.valueOf(result));
        bufferedWriter.newLine();

        // **** ****
        bufferedReader.close();
        bufferedWriter.close();
    }

}