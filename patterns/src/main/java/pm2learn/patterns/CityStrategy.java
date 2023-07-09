package pm2learn.patterns;

import javafx.scene.control.Label;

import java.util.List;
import java.util.Random;

public class CityStrategy implements Strategy {

    String[] cities = {"Paris","Londra","Berlin","Antep","Urfa","Maras","Mardin","Hakkari","Mülheim"};


    @Override
    public void show(List<Label> labels, boolean selected){

        System.out.println(" City Strateji basildi: "+ selected);

        Random random = new Random();
        if(!selected){
            for(Label l : labels){
                if(l.getId().contains("item3")) {
                    l.setText("city: unselected");
                }
            }
        }
        if(selected) {
            System.out.println(" City aktif");
            for(Label l : labels){
                if(l.getId().contains("item3")) {
                    l.setText("City: "+ cities[random.nextInt(cities.length)]);
                }
            }

        }

    }
}
