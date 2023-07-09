package pm2learn.patterns;

import javafx.scene.control.Label;

import java.util.List;
import java.util.Random;

public class NameStrategy implements Strategy {

    String[] names = {"Emir","Anabel","Ceyda","Kate","Vank","Rahul","Francisca","Gaddafi"};
    @Override
    public void show(List<Label> labels, boolean selected) {
        System.out.println(" Name Strateji basildi: " + selected);
        Random random = new Random();

        if (!selected) {
            for (Label l : labels) {
                if (l.getId().contains("item2")) {
                    l.setText("name: unselected");
                }
            }
        }
        System.out.println(" Name Strateji aktif");
        if (selected) {
            for (Label l : labels) {
                if (l.getId().contains("item2")) {
                    l.setText("Name: " + names[random.nextInt(names.length)]);
                }
            }
        }
    }
}
