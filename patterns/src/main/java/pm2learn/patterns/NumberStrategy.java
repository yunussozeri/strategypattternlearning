package pm2learn.patterns;

import javafx.scene.control.Label;
import java.util.List;
import java.util.Random;


public class NumberStrategy implements Strategy {

    int[] numbers = {31,52,69,666,999,254,610};

    @Override
    public void show(List<Label> labels, boolean selected){
        System.out.println(" Number Strateji basildi: "+ selected);
        Random random = new Random();


        if (!selected) {
            for (Label l : labels) {
                if (l.getId().contains("item1")) {
                    l.setText("number: unselected");
                }
            }
        }
        if(selected) {
            for(Label l : labels){
                if(l.getId().contains("item1")) {
                    l.setText("Name: "+ numbers[random.nextInt(numbers.length)]);
                }
            }
        }

    }

}
