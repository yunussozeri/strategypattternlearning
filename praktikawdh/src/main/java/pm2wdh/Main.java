package pm2wdh;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

     var x = new NumberInputStream(new BufferedReader(new FileReader("zahlen_1.txt")));

     int num = x.read();
     while (num != -1){
         System.out.println(num);
         num = x.read();
     }
     x.close();
     x = new NumberInputStream(new BufferedReader(new FileReader("zahlen_2.txt")));
     num = x.read();
     while(num != -1){
         System.out.println(num);
         num = x.read();
     }
     x.close();

    }
}