import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Task5 {
    public static class Spyder {
        private char name;
        private Integer count;
        public Spyder(char name, int count) {
            this.name = name;
            this.count = count;
        }
        public void setCount(int val) { this.count = val; }
        public void setName(char val) { this.name = val; }
    }
    public static void main(String[] args) {
        List<Integer> chisl6 = new ArrayList<Integer>();
        List<Integer> chisl7 = new ArrayList<Integer>();
        Scanner c = new Scanner(System.in);
        /////№1
        String st1 = c.nextLine();
        String st2 = c.nextLine();
        System.out.println(sameLetterPattern(st1,st2));
        /////№2
        c = new Scanner(System.in);
        String str = c.nextLine();
        char[] arr = str.toCharArray();
        Spyder pa = new Spyder(arr[0],Character.getNumericValue(arr[1]));
       str = c.nextLine();
        char[] arr1 = str.toCharArray();
        Spyder pc = new Spyder(arr1[0],Character.getNumericValue(arr1[1]));
        System.out.println(spiderVsFly(pa,pc));
        /////№3
        c = new Scanner(System.in);
        int a3 = c.nextInt();
       System.out.println(digitCount(a3));
        /////№4
        c = new Scanner(System.in);
        List<String> sugges= new ArrayList<String>();
        while (c.hasNext()) {
            String s = c.nextLine();
            if (s.equals("exit"))
                break;
            sugges.add(s);
        }
        c = new Scanner(System.in);
        String sug = c.nextLine();
        System.out.println(totalPoints(sugges,sug));
        /////№5
        c = new Scanner(System.in);
        while (c.hasNextInt()) {
            int chislo6 = c.nextInt();
            chisl6.add(chislo6);
        }
       System.out.println(longestRun(chisl6));
        /////№6
              c = new Scanner(System.in);
             while (c.hasNextInt()) {
                int chislo7 = c.nextInt();
              chisl7.add(chislo7);
          }
          System.out.println(takeDownAverage(chisl7));
        /////№7
        c = new Scanner(System.in);
        String st7 = c.nextLine();
        System.out.println(rearrange(st7));
        /////№8
        c = new Scanner(System.in);
        int b = c.nextInt();
        int a = c.nextInt();
        System.out.println(maxPossible(b,a));
        /////№9
        timeDifference("New York", "December 31, 1970 13:40", "Beijing");
        /////№10
        c = new Scanner(System.in);
        int bc = c.nextInt();
        System.out.println(isNew(bc));

    }
    public static boolean sameLetterPattern(String s1, String s2) { // шаблон
        if (s1.length() != s2.length())
            return false;
        for (int i = 0; i < s1.length() - 1; i++) {
            if (Math.abs(s1.charAt(i) - s1.charAt(i + 1)) != Math.abs(s2.charAt(i) - s2.charAt(i + 1)))
                return false;
        }
        return true;
    }
    public static String spiderVsFly(Spyder p1, Spyder p2) {
        String res = p1.name + "" + p1.count + " ";
        do {
            if (Math.abs(((int) p1.name - (int) p2.name)) <= 2 && p1.count == p2.count) {
                while (((int) p1.name != (int) p2.name) && p1.count == p2.count) {
                    if ((int) p1.name < (int) p2.name) {
                        p1.setName((char) ((int) p1.name + 1));
                    } else {
                        p1.setName((char) ((int) p1.name - 1));
                    }
                    res += p1.name + "" + p1.count + " ";
                }
            }
            else if (p1.name=='A' && p2.name == 'H' || p1.name=='H' && p2.name == 'A'){
                p1.setName(p2.name);
                res += p1.name + "" + p1.count + " ";
            }
            else if (p1.name == 'A' && p2.name == 'G' || p1.name=='G' && p2.name == 'A'){
                p1.setName('H');
                res += p1.name + "" + p1.count + " ";
            }
            else {
                  if (((int) p1.name != (int) p2.name) && p1.count > 0) {
                        p1.setCount(p1.count - 1);
                    }
                    if (p1.count == 0) {
                        res += "A0" + " ";
                        p1.setName(p2.name);
                    }
                    if (((int) p1.name == (int) p2.name)) {
                        p1.setCount(p1.count + 1);
                    }
                    res += p1.name + "" + p1.count + " ";
                }
            }while (((int) p1.name != (int) p2.name) || p1.count != p2.count);
        return res;
        }
    public static int digitCount(int digit) { // кол-во цифр
        if (digit < 10)
            return 1;
        else
            return 1 + digitCount(digit / 10);
    }
    public static int getPointsByLength(int len) { // распредление очков от кол-во букв
        switch (len) {
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
                return 3;
            case 6:
                return 54;
            default:
                return 0;
        }
    }
    public static boolean areAnagram(String a, String b) { // вхождение в строку
        for (int i = 0; i < a.length(); ++i) {
            int finalI = i;
            long letterCountInA = a.chars().filter(ch -> ch == a.charAt(finalI)).count();
            long letterCountInB = b.chars().filter(ch -> ch == a.charAt(finalI)).count();
            if (letterCountInA > letterCountInB)
                return false;
        }
        return true;
    }
    public static int totalPoints(List<String> suggestions, String word) { // результат
        int score = 0;
        for (String suggestion : suggestions)
            if (areAnagram(suggestion, word))
                score += getPointsByLength(suggestion.length());
        return score;
    }
    public static int longestRun(List<Integer> array) { // длинный массив
        int max = 1;
        int count = 1;
        for (int i = 0; i < array.size() - 1; i++) {
            if (array.get(i) == array.get(i + 1) + 1) {
                count++;
                if (max < count)
                    max = count;
            } else
                count = 1;
        }
        for (int i = 0; i < array.size() - 1; i++) {
            if (array.get(i) == array.get(i + 1) - 1) {
                count++;
                if (max < count)
                    max = count;
            } else
                count = 1;
        }
        return max;
    }
    public static long takeDownAverage(List<Integer> pros) { // занижение на 5%
        double sum = 0;
        for (double mpros: pros) sum += mpros;
        double aver = sum / pros.size();
        double newaver = aver - 5;
        double x = (pros.size() + 1) * newaver - sum;
        return Math.round(x);
    }
    public static String rearrange(String text) { // упорядочивание текста по цифрам
        List<String> word = Arrays.asList(text.split(" "));
        List<String> stroka = new ArrayList<>();
        for (int i = 0; i < word.size(); ++i) stroka.add("");
        Pattern chisla = Pattern.compile("\\d+");
        for (String words : word) {
            Matcher p = chisla.matcher(words);
            p.find();
            int num = Integer.parseInt(p.group());
            stroka.set(num - 1, words.replaceAll("\\d+", ""));
        }
        return String.join(" ", stroka);
    }
    public static int maxPossible(int a, int b) { // максимальное число
        List<Integer> A = new ArrayList<>();
        while (a > 0) {
            A.add(a % 10);
            a /= 10;
        }
        Collections.reverse(A);
        List<Integer> B = new ArrayList<>();
        while (b > 0) {
            B.add(b % 10);
            b /= 10;
        }
        Collections.sort(B, Collections.reverseOrder());

        int result = 0;
        int i = 0;
        int j = 0;
        while (true) {
            if (A.get(i) > B.get(j)) {
                result = result * 10 + A.get(i);
                ++i;
            } else {
                result = result * 10 + B.get(j);
                ++j;
                ++i;
            }
            if (i == A.size())
                break;
            if (j == B.size()) {
                while (i != A.size())
                    result = result * 10 + A.get(i++);
                break;
            }
        }
        return result;
    }
    public static void timeDifference(String cityA, String today, String cityB) {
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy HH:mm", Locale.UK);
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-M-d HH:mm", Locale.UK);
        Calendar calendar1 = Calendar.getInstance();
        try {
            calendar1.setTime(formatter.parse(today));
            Map<String, Double> map = new HashMap<>();
            map.put("Los Angeles", -8.0);
            map.put("New York",-5.0);
            map.put("Caracas", -4.3);
            map.put("Buenos Aires",-3.0);
            map.put("London",0.0);
            map.put("Rome", 1.0);
            map.put("Moscow",3.0);
            map.put("Tehran", 3.3);
            map.put("New Delhi",5.3);
            map.put("Beijing",8.0);
            map.put("Canberra",10.0);

            Double timeA = 0.0, timeB = 0.0, timeDiff = 0.0;
            for (Map.Entry<String,Double> pair : map.entrySet()) {
                if (pair.getKey() == cityA) {
                    timeA = pair.getValue();
                }
                if (pair.getKey() == cityB) {
                    timeB = pair.getValue();
                }
            }
            if (timeA < 0 && timeB > 0 || timeA > 0 && timeB < 0) {
                timeDiff = Math.abs(timeA) + Math.abs(timeB);
            }
            else if (timeA > 0 && timeB > 0)
                timeDiff = Math.abs(timeA - timeB);
            else
                timeDiff = timeA - timeB;
            double d = timeDiff;
            int h = (int)d, m = 0;
            String res = Double.toString(d);
            if (h < 0) {
                m = -Integer.parseInt(res.substring(res.length() - 1));
            }
            else
                m = Integer.parseInt(res.substring(res.length() - 1));
            calendar1.add(Calendar.HOUR, h);
            calendar1.add(Calendar.MINUTE, m);
            System.out.println(formatter1.format(calendar1.getTime()));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static boolean isNew(int a) {
        String str = String.valueOf(a);
        List<Integer> array = new ArrayList<>();
        for (int i = 0; i < str.length(); ++i) {
            array.add(Integer.parseInt(String.valueOf(str.charAt(i))));
        }
        int i = 0;
        while (i < array.size()) {
            Stream<Integer> aStream = array.subList(i, array.size()).stream();
            int fin = i;
            if (aStream.anyMatch(d -> d < array.get(fin) && d > 0))
                return false;
            ++i;
        }
        return true;
    }
    }