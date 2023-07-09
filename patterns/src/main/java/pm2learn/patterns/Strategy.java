package pm2learn.patterns;

import javafx.scene.control.Label;

import java.util.List;

@FunctionalInterface
public interface Strategy {
    void show(List<Label> labels, boolean selected);
}
