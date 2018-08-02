package com.mirt;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * code war
 *
 * @authur Administrator
 * @create 2018/3/27.
 */
public class CodeWar {
    /**
     * The parameter of the function findNb (find_nb, find-nb, findNb) will be an integer m
     * and you have to return the integer n such as n^3 + (n-1)^3 + ... + 1^3 = m if such a n exists or -1 if there is no such n.
     * <p>
     * Examples:
     * findNb(1071225) --> 45
     * findNb(91716553919377) --> -1
     *
     * @param m
     * @return
     */
    public static long findNb(long m) {
        // your
        //请考虑基本数据类型的位数
        if (m <= 0)
            return -1;
        long n = 1;
        while (m > 0) {
            m -= n * n * n;
            n++;
        }
        return m == 0 ? n - 1 : -1;
    }

    /**
     * Complete the solution so that it reverses the string value passed into it.
     * Kata.solution("world") //return "dlrow"
     *
     * @param str
     * @return
     */
    public static String solution(String str) {
        // Your code here...
        return str == null ? str : new StringBuffer(str).reverse().toString();
    }

    /**
     * Given a list lst and a number N, create a new list that contains each number of lst at most N times without reordering.
     * For example if N = 2, and the input is [1,2,3,1,2,1,2,3], you take [1,2,3,1,2], drop the next [1,2] since this would lead to 1 and 2 being in the result 3 times, and then take 3, which leads to [1,2,3,1,2,3].
     * <p>
     * Example
     * EnoughIsEnough.deleteNth(new int[] {20,37,20,21}, 1) // return [20,37,21]
     * EnoughIsEnough.deleteNth(new int[] {1,1,3,3,7,2,2,2,2}, 3) // return [1, 1, 3, 3, 7, 2, 2, 2]
     */
    public static int[] deleteNth(int[] elements, int maxOcurrences) {
        //Code here ;)
        if (maxOcurrences < 1 || elements == null || elements.length < 1)
            return new int[0];
        Map<Integer, Integer> table = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < elements.length; i++) {
            if (table.get(elements[i]) == null) {
                result.add(elements[i]);
                table.put(elements[i], 1);
                continue;
            }
            if (table.get(elements[i]) < maxOcurrences) {
                result.add(elements[i]);
                table.put(elements[i], table.get(elements[i]) + 1);
            }
        }
        int[] r = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            r[i] = result.get(i);
        }
        return r;
    }

    /**
     * In this little assignment you are given a string of space separated numbers, and have to return the highest and lowest number.
     * <p>
     * Example:
     * <p>
     * HighAndLow("1 2 3 4 5") // return "5 1"
     * HighAndLow("1 2 -3 4 5") // return "5 -3"
     * HighAndLow("1 9 3 4 -5") // return "9 -5"
     *
     * @param numbers
     * @return
     */
    public static String HighAndLow(String numbers) {
        // Code here or
        String[] nums = numbers.split(" ");
        Integer max = Integer.valueOf(nums[0]);
        Integer min = Integer.valueOf(nums[0]);
        for (String s : nums) {
            if (Integer.valueOf(s) > max)
                max = Integer.valueOf(s);
            if (Integer.valueOf(s) < min)
                min = Integer.valueOf(s);
        }
        return max.toString() + " " + min.toString();
    }

    /**
     * Given two arrays of strings a1 and a2 return a sorted array r in lexicographical order of the strings of a1 which are substrings of strings of a2.
     * <p>
     * #Example 1: a1 = ["arp", "live", "strong"]
     * <p>
     * a2 = ["lively", "alive", "harp", "sharp", "armstrong"]
     * <p>
     * returns ["arp", "live", "strong"]
     * <p>
     * #Example 2: a1 = ["tarp", "mice", "bull"]
     * <p>
     * a2 = ["lively", "alive", "harp", "sharp", "armstrong"]
     * <p>
     * returns []
     *
     * @param array1
     * @param array2
     * @return
     */
    public static String[] inArray(String[] array1, String[] array2) {
        if (array1.length < 1 || array2.length < 1)
            return new String[0];
        StringBuilder string2 = new StringBuilder();
        for (String s : array2) {
            string2.append(s);
        }
        List<String> result = new ArrayList<>();
        for (String s : array1) {
            if (string2.indexOf(s) != -1)
                result.add(s);
        }
        Collections.sort(result);
        String[] r = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            r[i] = result.get(i);
        }
        return r;
    }

    /**
     * Divisors of 42 are : 1, 2, 3, 6, 7, 14, 21, 42. These divisors squared are: 1, 4, 9, 36, 49, 196, 441, 1764.
     * The sum of the squared divisors is 2500 which is 50 * 50, a square!
     * <p>
     * Given two integers m, n (1 <= m <= n) we want to find all integers between m and n whose sum of squared divisors is itself a square. 42 is such a number.
     * list_squared(1, 250) --> [[1, 1], [42, 2500], [246, 84100]]
     * list_squared(42, 250) --> [[42, 2500], [246, 84100]]
     *
     * @param m
     * @param n
     * @return
     */
    public static String listSquared(long m, long n) {
        // your code
        StringBuilder sb = new StringBuilder();
        Map<Long, Long> result = new LinkedHashMap<>();
        while (m <= n) {
            if (isSquare(squaredDivisorsSum(m))) {
                result.put(m, squaredDivisorsSum(m));
            }
            m++;
        }
        sb.append("[");
        if (!result.isEmpty()) {
            for (Map.Entry<Long, Long> entry : result.entrySet()) {
                sb.append("[");
                sb.append(entry.getKey());
                sb.append(", ");
                sb.append(entry.getValue());
                sb.append("], ");
            }
        }
        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }

    private static long squaredDivisorsSum(long n) {
        List<Long> divisors = new ArrayList<>();
        for (long i = 1; i <= n; i++) {
            if (n % i == 0)
                divisors.add(i);
        }
        return divisors.stream().mapToLong(d -> d * d).sum();
    }

    private static boolean isSquare(long n) {
        return Math.round(Math.sqrt(n)) == Math.sqrt(n);
    }

    /**
     * Complete the function scramble(str1, str2)
     * that returns true
     * if a portion of str1 characters can be rearranged to match str2,
     * otherwise returns false.
     * <p>
     * scramble('rkqodlw', 'world') ==> True
     * scramble('cedewaraaossoqqyt', 'codewars') ==> True
     * scramble('katas', 'steak') ==> False
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean scramble(String str1, String str2) {
        // your code
        if (str1 == null || str2 == null)
            return false;
        if (str1.length() < str2.length())
            return false;
        Map<Character, List<Integer>> dict = new HashMap<>();
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        for (int i = 0; i < chars2.length; i++) {
            int l1 = dict.get(chars2[i]) != null ? dict.get(chars2[i]).size() : 0;
            for (int j = 0; j < chars1.length; j++) {
                List<Integer> indexs = dict.get(chars2[i]);
                if (indexs == null && chars1[j] == chars2[i]) {
                    indexs = new ArrayList<>();
                    indexs.add(j);
                    dict.put(chars2[i], indexs);
                    break;
                }
                if (chars1[j] == chars2[i] && !indexs.contains(j)) {
                    indexs.add(j);
                    dict.put(chars2[i], indexs);
                    break;
                }
            }
            if (dict.get(chars2[i]) == null || dict.get(chars2[i]).size() == l1)
                return false;
        }
        return true;
    }

    /**
     * Complete the solution so that it strips all text that follows any of a set of comment markers passed in.
     * Any whitespace at the end of the line should also be stripped out.
     * <p>
     * 消除所有注释 之后取消句子后空格，前空格不取消
     *
     * @param text           需要取消注释的句子
     * @param commentSymbols 注释符号比如 # ！ 等
     * @return
     */
    public static String stripComments(String text, String[] commentSymbols) {
        if (text == null)
            return "";
        if (commentSymbols.length < 1)
            return text.trim();
        return Arrays.stream(text.split("\n"))
                .map(s -> {
                    for (String c : commentSymbols) {
                        int index = s.indexOf(c);
                        if (index != -1) {
                            s = s.substring(0, index);
                        }
                    }
                    return s.substring(0, s.indexOf(s.trim()) + s.trim().length());
                })
                .collect(Collectors.joining("\n"));
    }

    /**
     * You are given a string s. Every letter in s appears once.
     * <p>
     * Consider all strings formed by rearranging the letters in s.
     * After ordering these strings in dictionary order, return the middle term.
     * (If the sequence has a even length n, define its middle term to be the (n/2)th term.)
     * <p>
     * 将字符串所有排列组合 排序之后 返回中间的字符串
     *
     * @param strng
     * @return
     */
    public static String findMidPerm(String strng) {

        char[] arrStr = strng.toCharArray();
        Arrays.sort(arrStr);
        StringBuilder s = new StringBuilder(new String(arrStr)).reverse();

        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(s.length() / 2, (s.length() + 3) / 2));
        sb.append(s.substring(0, s.length() / 2));
        sb.append(s.substring((s.length() + 3) / 2));

        return sb.toString();
    }

    public static String findMidPerm1(String string) {
        // your code here!
        if (string == null || string.length() < 1)
            return "";
        if (string.length() < 2)
            return string;
        StringBuilder sb = new StringBuilder();
        char[] chars = string.toCharArray();
        Arrays.sort(chars);
        List<Character> characters = new ArrayList<>();
        for (Character c : chars)
            characters.add(c);
        int n = chars.length;
        BigDecimal a = prod(n).divide(new BigDecimal("2"));
        BigDecimal b = prod(n - 1);
        for (int i = chars.length - 1; i >= 0; i--) {
            int x = solve(a, b);
            sb.append(characters.get(x));
            characters.remove(x);
            if (characters.isEmpty())
                break;
            a = a.subtract(new BigDecimal(x).multiply(b));
            b = prod(i - 1);
        }
        return sb.toString();
    }

    private static int solve(BigDecimal a, BigDecimal b) {
        int x = 0;
        while (a.subtract(new BigDecimal(x).multiply(b)).compareTo(BigDecimal.ZERO) > 0) {
            x++;
        }
        return x - 1;
    }

    private static List<List<Character>> dfsForfindMidPerm(char[] chars, int length, List<Character> list, List<List<Character>> result) {
        if (list.size() == chars.length) {
            result.add(new ArrayList<>(list));
            return result;
        }
        if (result.size() == length)
            return result;
        for (int i = 0; i < chars.length; i++) {
            if (!list.contains(chars[i])) {
                list.add(chars[i]);
                dfsForfindMidPerm(chars, length, list, result);
                list.remove(list.size() - 1);
            }
        }
        return result;
    }

    private static BigDecimal prod(int n) {
        BigDecimal r = BigDecimal.ONE;
        while (n > 0) {
            r = r.multiply(new BigDecimal(String.valueOf(n)));
            n--;
        }
        return r;
    }

    /**
     * Consider a sequence u where u is defined as follows:
     * <p>
     * The number u(0) = 1 is the first one in u.
     * For each x in u, then y = 2 * x + 1 and z = 3 * x + 1 must be in u too.
     * There are no other numbers in u.
     * Ex: u = [1, 3, 4, 7, 9, 10, 13, 15, 19, 21, 22, 27, ...]
     * <p>
     * 1 gives 3 and 4, then 3 gives 7 and 10, 4 gives 9 and 13, then 7 gives 15 and 22 and so on...
     * <p>
     * #Task: Given parameter n the function dbl_linear (or dblLinear...)
     * returns the element u(n) of the ordered (with <) sequence u.
     *
     * @param n
     * @return
     */
    public static int dblLinear(int n) {
        // your code
        List<Integer> u = new ArrayList<>();
        u.add(1);
        //用两个指针 一个指向乘2一个指向乘3 存入较小的数 并且指针向后移动
        int i2 = 0;
        int i3 = 0;
        while (u.size() - 1 < n) {
            int d2 = u.get(i2) * 2 + 1;
            int d3 = u.get(i3) * 3 + 1;
            if (d2 < d3) {
                u.add(d2);
                i2++;
            } else if (d3 < d2) {
                u.add(d3);
                i3++;
            } else {
                u.add(d2);
                i2++;
                i3++;
            }
        }
        return u.get(n);
    }


    ///**
    // * The function must accept a non-negative integer. If it is zero, it just returns "now".
    // * Otherwise, the duration is expressed as a combination of years, days, hours, minutes and seconds.
    // *
    // * TimeFormatter.formatDuration(62)   //returns "1 minute and 2 seconds"
    // * TimeFormatter.formatDuration(3662) //returns "1 hour, 1 minute and 2 seconds"
    // * @param seconds
    // * @return
    // */
    //public static String formatDuration(int seconds) {
    //    if (seconds < 0)
    //        return "";
    //    if (seconds == 0)
    //        return "now";
    //    int minute = 60;
    //    int hour = 60*minute;
    //    int day = 24*hour;
    //    int year = 365*day;
    //
    //
    //}

    /**
     * Given an array of positive or negative integers
     * <p>
     * I= [i1,..,in]
     * <p>
     * you have to produce a sorted array P of the form
     * <p>
     * [ [p, sum of all ij of I for which p is a prime factor (p positive) of ij] ...]
     * <p>
     * P will be sorted by increasing order of the prime numbers. The final result has to be given as a string in Java, C#, C, C++ and as an array of arrays in other languages.
     * <p>
     * Example:
     * <p>
     * I = {12, 15}; // result = "(2 12)(3 27)(5 15)"
     *
     * @param l
     * @return
     */
    public static String sumOfDivided(int[] l) {
        // your code
        Map<Integer, Integer> result = new HashMap<>();
        Arrays.stream(l).forEach(i -> {
            for (int p = 2; p <= i; p++) {
                if (isPrime(p) && i % p == 0) {
                    Integer sum = result.get(p);
                    if (sum == null) {
                        result.put(p, i);
                    } else {
                        result.put(p, sum + i);
                    }
                }
            }
        });
        StringBuilder sb = new StringBuilder();
        Object[] key = result.keySet().toArray();
        Arrays.sort(key);
        for (Object i : key) {
            sb.append("(");
            sb.append(i);
            sb.append(" ");
            sb.append(result.get(i));
            sb.append(")");
        }
        return sb.toString();
    }

    private static boolean isPrime(int p) {
        for (int i = 2; i * i <= p; i++) {
            if (p % i == 0)
                return false;
        }
        return true;
    }

    /**
     * A Hamming number is a positive integer of the form 2i3j5k, for some non-negative integers i, j, and k.
     * <p>
     * Write a function that computes the nth smallest Hamming number.
     * <p>
     * Specifically:
     * <p>
     * The first smallest Hamming number is 1 = 203050
     * The second smallest Hamming number is 2 = 213050
     * The third smallest Hamming number is 3 = 203150
     * The fourth smallest Hamming number is 4 = 223050
     * The fifth smallest Hamming number is 5 = 203051
     *
     * @param n
     * @return
     */
    public static long hamming(int n) {
        // TODO: Program me
        if (n <= 0)
            return 0L;
        List<Long> result = new ArrayList<>();
        result.add(1L);
        int i = 0, j = 0, k = 0;
        int index = n - 1;
        while (n > 0) {
            long r2 = result.get(i) * 2;
            long r3 = result.get(j) * 3;
            long r5 = result.get(k) * 5;
            if (r2 == getMin(r2, r3, r5)) {
                i++;
                if (!result.contains(r2)) {
                    result.add(r2);
                    n--;
                }
                continue;
            }
            if (r3 == getMin(r2, r3, r5)) {
                j++;
                if (!result.contains(r3)) {
                    result.add(r3);
                    n--;
                }
                continue;
            }
            if (r5 == getMin(r2, r3, r5)) {
                k++;
                if (!result.contains(r5)) {
                    result.add(r5);
                    n--;
                }
                continue;
            }
        }
        return result.get(index);
    }

    private static long getMin(long i, long j, long k) {
        return Math.min(Math.min(i, j), k);
    }

    /**
     * This is a very simply formulated task. Let's call an integer number N 'green'
     * if N² ends with all of the digits of N. Some examples:
     * <p>
     * 5 is green, because 5² = 25 and 25 ends with 5.
     * <p>
     * 11 is not green, because 11² = 121 and 121 does not end with 11.
     * <p>
     * 376 is green, because 376² = 141376 and 141376 ends with 376.
     * <p>
     * Your task is to write a function green that returns nth green number, starting with 1 - green (1) == 1
     *
     * @param n
     * @return
     */
    public static BigInteger get(int n) {
        // Your code here;
        //todo
        if (n <= 0)
            return null;
        if (n == 1)
            return BigInteger.ONE;
        if (n == 2)
            return BigInteger.valueOf(5);
        if (n == 3)
            return BigInteger.valueOf(6);
        BigInteger result = BigInteger.ONE;
        StringBuilder endWith5 = new StringBuilder("5");
        StringBuilder endWith6 = new StringBuilder("6");
        int i5 = 1;
        int i6 = 1;
        while (n > 3) {
            for (int i = 1; i < 10; i++) {
                endWith5.insert(0, i);
                if (isN2EqualN(BigInteger.valueOf(Long.parseLong(endWith5.toString()))))
                    endWith5.deleteCharAt(0);
            }

            for (int i = 1; i < 10; i++) {
                endWith6.insert(0, i);
                if (isN2EqualN(BigInteger.valueOf(Long.parseLong(endWith6.toString()))))
                    endWith6.deleteCharAt(0);
            }
            n--;
        }
        return result;
    }

    private static boolean isN2EqualN(BigInteger bigInteger) {
        return bigInteger.pow(2).toString().indexOf(bigInteger.toString()) == bigInteger.pow(2).toString().length() - bigInteger.toString().length();
    }

    /**
     * Create a function taking a positive integer as its parameter and returning a string containing the Roman Numeral representation of that integer.
     * <p>
     * Modern Roman numerals are written by expressing each digit separately starting with the left most digit and skipping any digit with a value of zero. In Roman numerals 1990 is rendered: 1000=M, 900=CM, 90=XC; resulting in MCMXC.
     * 2008 is written as 2000=MM, 8=VIII; or MMVIII. 1666 uses each Roman symbol in descending order: MDCLXVI.
     * <p>
     * Symbol    Value
     * I          1
     * V          5
     * X          10
     * L          50
     * C          100
     * D          500
     * M          1,000
     *
     * @param n
     * @return
     */
    public static String solution(int n) {
        int i = n % 10;
        int x = (n % 100 - i) / 10;
        int c = (n % 1000 - 10 * x - i) / 100;
        int m = (n % 10000 - 100 * c - 10 * x - i) / 1000;
        String I = makeRoman(i, "I", "V", "X");
        String X = makeRoman(x, "X", "L", "C");
        String C = makeRoman(c, "C", "D", "M");
        String M = makeRoman(m, "M");


        return M + C + X + I;
    }

    private static String makeRoman(int n, String i, String i5, String i10) {
        switch (n) {
            case 0:
                return "";
            case 1:
                return i;
            case 2:
                return i + i;
            case 3:
                return i + i + i;
            case 4:
                return i + i5;
            case 5:
                return i5;
            case 6:
                return i5 + i;
            case 7:
                return i5 + i + i;
            case 8:
                return i5 + i + i + i;
            case 9:
                return i + i10;
        }
        return "";
    }

    private static String makeRoman(int n, String m) {
        if (n == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(m);
        }
        return sb.toString();
    }

    /**
     * If n is the numerator and d the denominator of a fraction, that fraction is defined a (reduced) proper fraction if and only if GCD(n,d)==1.
     * <p>
     * For example 5/16 is a proper fraction, while 6/16 is not, as both 6 and 16 are divisible by 2, thus the fraction can be reduced to 3/8.
     * <p>
     * Now, if you consider a given number d, how many proper fractions can be built using d as a denominator?
     * <p>
     * For example, let's assume that d is 15: you can build a total of 8 different proper fractions between 0 and 1 with it: 1/15, 2/15, 4/15, 7/15, 8/15, 11/15, 13/15 and 14/15.
     * <p>
     * You are to build a function that computes how many proper fractions you can build with a given denominator:
     * <p>
     * proper_fractions(1)==0
     * proper_fractions(2)==1
     * proper_fractions(5)==4
     * proper_fractions(15)==8
     * proper_fractions(25)==20
     *
     * @param n
     * @return
     */
    public static long properFractions(long n) {
        if (n == 1)
            return 0L;
        return LongStream.range(1, n).parallel()
                .filter(i -> {
                    long a = i;
                    long b = n;
                    long m = b % a;
                    while (m != 0) {
                        b = a;
                        a = m;
                        m = b % a;
                    }
                    return a == 1;
                }).count();
    }

    /**
     * Write a program that will calculate the number of trailing zeros in a factorial of a given number.
     * <p>
     * N! = 1 * 2 * 3 * ... * N
     * <p>
     * Be careful 1000! has 2568 digits...
     *
     * @param n
     * @return
     */
    public static int zeros(int n) {
        int result = 0;
        for (int i = 5; i < n; i *= 5) {
            result += n / i;
        }
        return result;
    }

    /**
     * At a job interview, you are challenged to write an algorithm to check if a given string, s, can be formed from two other strings, part1 and part2.
     * <p>
     * The restriction is that the characters in part1 and part2 are in the same order as in s.
     * <p>
     * The interviewer gives you the following example and tells you to figure out the rest from the given test cases.
     * <p>
     * For example:
     * <p>
     * 'codewars' is a merge from 'cdw' and 'oears':
     * <p>
     * s:  c o d e w a r s   = codewars
     * part1:  c   d   w         = cdw
     * part2:    o   e   a r s   = oears
     *
     * @param s
     * @param part1
     * @param part2
     * @return
     */
    public static boolean isMerge(String s, String part1, String part2) {
        if (s == null || part1 == null || part2 == null)
            return false;
        if (s.length() != part1.length() + part2.length())
            return false;
        int n1 = part1.length();
        int n2 = part2.length();
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < n1 || j < n2) {
            if (i < n1 && s.charAt(k) == part1.charAt(i)) {
                i++;
                k++;
                continue;
            }
            if (j < n2 && s.charAt(k) == part2.charAt(j)) {
                j++;
                k++;
                continue;
            }
            return false;
        }
        return true;
    }

    /**
     * Write a function that takes a string of braces, and determines if the order of the braces is valid. It should return true if the string is valid, and false if it's invalid.
     * <p>
     * This Kata is similar to the Valid Parentheses Kata, but introduces new characters: brackets [], and curly braces {}. Thanks to @arnedag for the idea!
     * <p>
     * All input strings will be nonempty, and will only consist of parentheses, brackets and curly braces: ()[]{}.
     * <p>
     * What is considered Valid?
     * A string of braces is considered valid if all braces are matched with the correct brace.
     * <p>
     * Examples
     * "(){}[]"   =>  True
     * "([{}])"   =>  True
     * "(}"       =>  False
     * "[(])"     =>  False
     * "[({})](]" =>  False
     *
     * @param braces
     * @return
     */
    public static boolean isValid(String braces) {
        // Add code here
        if (braces == null || braces.length() % 2 != 0)
            return false;
        char[] chars = braces.toCharArray();
        Stack<Character> characters = new Stack<>();
        for (char c : chars) {
            if ('(' == c || '[' == c || '{' == c) {
                characters.push(c);
                continue;
            }
            if (characters.isEmpty())
                return false;
            char t = characters.pop();
            if (t + 2 != c && t + 1 != c)
                return false;
        }
        return true;
    }

    /**
     * Your objective is to complete a function createSpiral(N) that receives an integer N and returns an NxN two-dimensional array with numbers 1 through N^2 represented as a clockwise spiral.
     * <p>
     * Return an empty array if N < 1 or N is not int/number
     * <p>
     * Examples:
     * <p>
     * N = 3 Output: [[1,2,3],[8,9,4],[7,6,5]]
     * <p>
     * 1    2    3
     * 8    9    4
     * 7    6    5
     * N = 4 Output: [[1,2,3,4],[12,13,14,5],[11,16,15,6],[10,9,8,7]]
     * <p>
     * 1   2   3   4
     * 12  13  14  5
     * 11  16  15  6
     * 10  9   8   7
     *
     * @param N
     * @return
     */
    public static int[][] createSpiral(int N) {
        // your code here
        int[][] result = new int[N][N];
        int c = (N + 1) / 2;
        for (int i = 0; i < c; i++) {
            result = circle(result, i);
        }
        return result;
    }

    private static int[][] circle(int[][] r, int n) {
        int i = n;
        int j = r.length - n;
        int start = 0;
        if (n == 0)
            start = 1;
        if (n > 0)
            start = r[i][i - 1] + 1;
        for (int k = i; k < j; k++) {
            r[i][k] = start++;
        }
        for (int k = i + 1; k < j; k++) {
            r[k][j - 1] = start++;
        }
        for (int k = j - 2; k >= i; k--) {
            r[j - 1][k] = start++;
        }
        for (int k = j - 2; k >= i + 1; k--) {
            r[k][i] = start++;
        }
        return r;
    }

    /**
     * Sheldon, Leonard, Penny, Rajesh and Howard are in the queue for a "Double Cola" drink vending machine; there are no other people in the queue. The first one in the queue (Sheldon) buys a can, drinks it and doubles! The resulting two Sheldons go to the end of the queue. Then the next in the queue (Leonard) buys a can, drinks it and gets to the end of the queue as two Leonards, and so on.
     * <p>
     * For example, Penny drinks the third can of cola and the queue will look like this:
     * <p>
     * Rajesh, Howard, Sheldon, Sheldon, Leonard, Leonard, Penny, Penny
     * Write a program that will return the name of the person who will drink the n-th cola.
     * <p>
     * Note that in the very beginning the queue looks like that:
     * <p>
     * Sheldon, Leonard, Penny, Rajesh, Howard
     * ##Input
     * <p>
     * The input data consist of an array which contains at least 1 name, and single integer n.
     * <p>
     * (1 ≤ n ≤ 1000000000).
     *
     * @param names
     * @param n
     * @return
     */
    public static String WhoIsNext(String[] names, int n) {
        // Your code is here...
        if (n <= 5)
            return names[n - 1];
        int k = 0;
        while (5 * Math.pow(2, k) - 5 < n) {
            k++;
        }
        int index = (int) (n - 5 * Math.pow(2, k - 1) + 5);
        int realIndex = 1;
        while (realIndex * Math.pow(2, k - 1) < index) {
            realIndex++;
        }
        return names[realIndex - 1];
    }

    /**
     * Create an endless stream of prime numbers - a bit like IntStream.of(2,3,5,7,11,13,17), but infinite.
     * The stream must be able to produce a million primes in a few seconds.
     *
     * @return
     */
    public static IntStream stream() {
        return IntStream.rangeClosed(2, Integer.MAX_VALUE).filter(i -> isPrime(i));
    }

    /**
     * 1-10000之间有多少个升数字（低位数的数字大于高位数的数字）
     *
     * @param n
     */
    public static int incrementNum(int n) {
        if (n < 10)
            return 0;
        int count = 0;
        for (int i = 12; i < n; i++) {
            if (isIncrement(i))
                count++;
        }
        return count;
    }

    private static boolean isIncrement(int n) {
        if (n > 123456789)
            return false;
        while (n / 10 > 0) {
            int end = n % 10;
            int pre = (n / 10) % 10;
            if (pre >= end)
                return false;
            n /= 10;
        }
        return true;
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
        System.out.println(incrementNum(Integer.MAX_VALUE));
        System.out.println((System.nanoTime() - start) / 100000.0);
    }
}
