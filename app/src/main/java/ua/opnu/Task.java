package ua.opnu;

import java.util.*;

public class Task {

    // ======= Завдання 1 =======
    public static void removeShorterStrings(List<String> list) {
        int i = 0;
        while (i + 1 < list.size()) {
            String first = list.get(i);
            String second = list.get(i + 1);
            // якщо однакова довжина – видаляємо перший
            if (first.length() <= second.length()) {
                list.remove(i);
            } else {
                list.remove(i + 1);
            }
            i++; // переходимо до наступної пари
        }
    }

    // ======= Завдання 2 =======
    public static void stutter(List<String> list) {
        int i = 0;
        while (i < list.size()) {
            String s = list.get(i);
            list.add(i, s); // вставляємо дублікат ПЕРЕД поточним
            i += 2;         // пропускаємо щойно вставлену пару
        }
    }

    // ======= Завдання 3 =======
    public static void switchPairs(List<String> list) {
        for (int i = 0; i + 1 < list.size(); i += 2) {
            String a = list.get(i);
            String b = list.get(i + 1);
            list.set(i, b);
            list.set(i + 1, a);
        }
    }

    // ======= Завдання 4 =======
    public static void removeDuplicates(List<String> list) {
        int i = 1;
        while (i < list.size()) {
            if (list.get(i).equals(list.get(i - 1))) {
                list.remove(i); // поточний – дублікат попереднього
            } else {
                i++;
            }
        }
    }

    // ======= Завдання 5 =======
    public static void markLength4(List<String> list) {
        int i = 0;
        while (i < list.size()) {
            if (list.get(i).length() == 4) {
                list.add(i, "****");
                i += 2; // перескакуємо "****" і сам рядок
            } else {
                i++;
            }
        }
    }

    // ======= Завдання 6 =======
    public static boolean isPalindrome(Queue<Integer> q) {
        int size = q.size();
        Deque<Integer> stack = new ArrayDeque<>();

        // 1. Копіюємо в стек і повертаємо в чергу
        for (int i = 0; i < size; i++) {
            int val = q.remove();
            q.add(val);
            stack.push(val);
        }

        boolean result = true;

        // 2. Порівнюємо й одночасно відновлюємо чергу
        for (int i = 0; i < size; i++) {
            int val = q.remove();
            int fromStack = stack.pop();
            if (val != fromStack) {
                result = false;
            }
            q.add(val); // повертаємо елемент назад
        }

        return result;
    }

    // ======= Завдання 7 =======
    // front [1, 2, -2, 4, -5, 8, -8, 12, -15, 23] back
    // -> front [-15, -8, -5, -2, 1, 2, 4, 8, 12, 23] back
    public static void reorder(Queue<Integer> q) {
        int originalSize = q.size();
        Deque<Integer> stack = new ArrayDeque<>();
        int positiveCount = 0;

        // розділяємо на негативні (у стек) та позитивні (залишаються в черзі)
        for (int i = 0; i < originalSize; i++) {
            int val = q.remove();
            if (val < 0) {
                stack.push(val); // порядок у стеку буде: top = найменше число
            } else {
                q.add(val);
                positiveCount++;
            }
        }

        // додаємо всі негативні в чергу
        while (!stack.isEmpty()) {
            q.add(stack.pop());
        }

        // переносячи перші positiveCount елементів у кінець,
        // отримуємо: спочатку всі негативні, потім позитивні
        for (int i = 0; i < positiveCount; i++) {
            q.add(q.remove());
        }
    }

    // ======= Завдання 8 =======
    public static void rearrange(Queue<Integer> q) {
        int size = q.size();
        Deque<Integer> aux = new ArrayDeque<>(); // використаємо як допоміжну чергу

        // перший прохід: відокремлюємо парні (залишаємо в q), непарні – в aux
        for (int i = 0; i < size; i++) {
            int val = q.remove();
            if (val % 2 == 0) {
                q.add(val);     // парні залишаються в черзі
            } else {
                aux.add(val);   // непарні збираємо окремо
            }
        }

        // тепер у q – тільки парні в потрібному порядку,
        // в aux – непарні в потрібному порядку
        while (!aux.isEmpty()) {
            q.add(aux.remove());
        }
    }

    // ======= Завдання 9 =======
    public static int maxLength(Set<String> set) {
        int max = 0;
        for (String s : set) {
            if (s.length() > max) {
                max = s.length();
            }
        }
        return max;
    }

    // ======= Завдання 10 =======
    public static void removeEvenLength(Set<String> set) {
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String s = it.next();
            if (s.length() % 2 == 0) {
                it.remove();
            }
        }
    }

    // ======= Завдання 11 =======
    public static int numInCommon(List<Integer> a, List<Integer> b) {
        Set<Integer> setA = new HashSet<>(a);
        Set<Integer> intersection = new HashSet<>();
        for (Integer x : b) {
            if (setA.contains(x)) {
                intersection.add(x); // тільки унікальні
            }
        }
        return intersection.size();
    }

    // ======= Завдання 12 =======
    public static boolean isUnique(Map<String, String> map) {
        Set<String> values = new HashSet<>();
        for (String v : map.values()) {
            if (!values.add(v)) { // значення вже було
                return false;
            }
        }
        return true;
    }

    // ======= Завдання 13 =======
    public static Map<String, Integer> intersect(Map<String, Integer> m1,
                                                 Map<String, Integer> m2) {
        Map<String, Integer> result = new HashMap<>();

        for (Map.Entry<String, Integer> e : m1.entrySet()) {
            String key = e.getKey();
            Integer value = e.getValue();

            if (m2.containsKey(key) && Objects.equals(m2.get(key), value)) {
                result.put(key, value);
            }
        }
        return result;
    }

    // ======= Завдання 14 =======
    public static Map<String, Integer> reverse(Map<Integer, String> map) {
        Map<String, Integer> result = new HashMap<>();
        for (Map.Entry<Integer, String> e : map.entrySet()) {
            result.put(e.getValue(), e.getKey());
        }
        return result;
    }

    // ======= Завдання 15 =======
    public static int rarest(Map<String, Integer> map) {
        if (map.isEmpty()) {
            throw new IllegalArgumentException("Map is empty");
        }

        // значення -> кількість появ
        Map<Integer, Integer> freq = new HashMap<>();
        for (Integer value : map.values()) {
            freq.put(value, freq.getOrDefault(value, 0) + 1);
        }

        int bestValue = Integer.MAX_VALUE;
        int bestCount = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            int value = e.getKey();
            int count = e.getValue();

            if (count < bestCount || (count == bestCount && value < bestValue)) {
                bestCount = count;
                bestValue = value;
            }
        }
        return bestValue;
    }

    // ======= Завдання 16 =======
    public static int maxOccurrences(List<Integer> list) {
        if (list.isEmpty()) {
            return 0;
        }

        Map<Integer, Integer> freq = new HashMap<>();
        int max = 0;

        for (Integer x : list) {
            int c = freq.getOrDefault(x, 0) + 1;
            freq.put(x, c);
            if (c > max) {
                max = c;
            }
        }
        return max;
    }
}
