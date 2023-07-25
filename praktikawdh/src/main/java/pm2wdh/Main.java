package pm2wdh;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        var y = new ArrayList<String>();
        y.add("Banahne");
        y.add("apfel");
        y.add("zort");
        y.add("bum");

        Iterator<String> z = y.iterator();

        while(z.hasNext()){
            String curr = z.next();
            if("Banahne".equals(curr)){
                z.remove();
               // int a = y.indexOf(curr);
                //y.set(a, "Banane");

            }

        }
        HashSet<String> a = new HashSet<>();
        a.add("a");
        y.add("Banane");
        y.stream().forEach(System.out::println);

        var strings = Arrays.asList("Egon","Rieke","duHs","Max","Sami","furz");
        //Collections.sort(namen, Comparator.comparingInt(o -> o.charAt(o.length() - 1)));

        moreY(strings).stream().forEach(System.out::println);

        var n = Arrays.asList("Eins","Sechs","Elf");


        n.stream().sorted((zort,b)-> zort.compareTo(b)).forEach(System.out::println);




     var x = new NumberInputStream(new BufferedReader(new FileReader("zahlen_1.txt")));

     var zahlen = new ArrayList<Integer>();
     int num = x.read();
     while (num != -1){
         zahlen.add(num);
         System.out.println(num);
         num = x.read();
     }
     zahlen.stream().map(i -> Integer.toHexString(i)).collect(Collectors.toList()).forEach(System.out::println);
     x.close();
     x = new NumberInputStream(new BufferedReader(new FileReader("zahlen_2.txt")));
     num = x.read();
     while(num != -1){
         System.out.println(num);
         num = x.read();
     }
     x.close();

    }
    public static List<String> moreY(List<String> strings) {
        List<String> a = strings.stream().map(s -> "y" + s + "y").collect(Collectors.toList());
        //strings.(s -> "y" + s + "y");
        return a;
    }
}