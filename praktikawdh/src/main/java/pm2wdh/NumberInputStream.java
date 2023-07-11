package pm2wdh;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class NumberInputStream extends InputStream {

    private final Reader reader;

    public NumberInputStream(Reader reader) {
        this.reader = reader;

    }

    public int read() throws IOException {


        StringBuilder number = new StringBuilder();

        int r;
        while(( r = reader.read()) != -1){

            if(Character.isWhitespace(r) && number.length() > 0) {

                int num;
                num = Integer.parseInt(number.toString());

                if(num > 255){
                    throw new NumberTooLargeException();
                } return num;

            } else if(Character.isDigit(r)){
                number.append(Character.toString(r));
            }
        }
        return -1;
    }

}
