import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Problem: https://www.hackerrank.com/challenges/maximizing-xor/problem
 * Given two integers, l and r, find the maximal value of 'a' xor 'b', written
 * 'a' ? 'b', where 'a' and 'b' satify the following condition:
 * l <= a <= b <= r
 * @author Ricardo Campos <ricardompcampos@gmail.com>
 */
public class MaximizingXor {
  public static void main(String[] args) throws Exception {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    int l = Integer.parseInt(bufferedReader.readLine().trim());
    int r = Integer.parseInt(bufferedReader.readLine().trim());


    bufferedReader.close();

    int result = Result.maximizingXor(l, r);

    System.out.println(String.valueOf(result));
  }
}

class Result {
  public static int maximizingXor(int l, int r) {
    if (l < 1) return 0;
    if (r > (10 * 10 * 10)) return 0;
    if (l > r) return 0;

    int maximal = 0;
    for (int a=l; a<=r; a++) {
      for (int b=l; b<=r; b++) {
        int current = a ^ b;
        if (current > maximal) {
          maximal = current;
        }
      }
    }
    return maximal;
  }
}