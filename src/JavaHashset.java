// From: https://www.hackerrank.com/challenges/java-hashset/problem?isFullScreen=true

import java.util.*;

public class JavaHashset {

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int t = s.nextInt();
    String [] pair_left = new String[t];
    String [] pair_right = new String[t];

    for (int i = 0; i < t; i++) {
      pair_left[i] = s.next();
      pair_right[i] = s.next();
    }

    // Write your code here
    Set<String> uniqueSet = new TreeSet<>();
    for (int i=0; i<t; i++) {
      String combination = pair_left[i] + " " + pair_right[i];
      String reversed = pair_right[i] + " " + pair_left[i];
      if (!uniqueSet.contains(reversed)) {
        uniqueSet.add(combination);
      }
      System.out.println(uniqueSet.size());
    }
  }
}
