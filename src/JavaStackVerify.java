package br.com.ricardocampos.silent_guard_api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Simple implementation of a Stack in Java
// Example of usages:
// "({}[])" -> true
// "(({()})))" -> false
// "({(){}()})()({(){}()})(){()}" -> true
// "{}()))(()()({}}{}" -> false
// From https://www.hackerrank.com/challenges/java-stack/problem?isFullScreen=true
public class MyClass {

  // Working implemementation
  public static String verify(List<Character> list) {
    Map<Character, Character> pairs = new HashMap<>();
    pairs.put('(', ')');
    pairs.put('{', '}');
    pairs.put('[', ']');

    pairs.put(')', '(');
    pairs.put('}', '{');
    pairs.put(']', '[');

    List<Character> stacked = new ArrayList<>();
    for (Character current : list) {
      if (current.equals('(') || current.equals('{') || current.equals('[')) {
        stacked.add(current);
      } else {
        int index = list.indexOf(current);
        if (index == 0) {
          return "false";
        }
        if (stacked.isEmpty()) {
          return "false";
        }
        Character previous = stacked.getLast();
        if (pairs.containsKey(current) && previous.equals(pairs.get(current))) {
          stacked.removeLast();
        }
      }
    }

    return stacked.isEmpty() ? "true" : "false";
  }

  // Failing implementation
  public static String verifyOld(List<Character> list) {
    // processing
    if (list.size() % 2 == 1) {
      return "false";
    }
    // remove the pair ones
    Map<Character, Character> pairs = new HashMap<>();
    pairs.put('(', ')');
    pairs.put('{', '}');
    pairs.put('[', ']');
    int pairCount = 0;
    for (int i = 0; i < list.size(); i += 2) {
      Character current = list.get(i);
      Character next = list.get(i + 1);
      boolean isPair = pairs.containsKey(current) && pairs.get(current).equals(next);
      if (!isPair) {
        // if it's not a pair, check if it has a matching position at the end of the stack
        Character matching = list.get(list.size() - 1 - i);
        if (!pairs.containsKey(current) || !matching.equals(pairs.get(current))) {
          // matched!
          break;
        }
        else {
          pairCount++;
        }
        // if not, it should be a stack
      } else {
        pairCount++;
      }
    }
    if (pairCount == list.size() / 2) {
      return "true";
    }
    // handle it like a stack
    for (int i = 0; i < list.size() / 2; i++) {
      Character current = list.get(i);
      Character matching = list.get(list.size() - 1 - i);
      boolean isPair = pairs.containsKey(current) && pairs.get(current).equals(matching);
      if (!isPair) {
        return "false";
      }
    }
    return "true";
  }
}
