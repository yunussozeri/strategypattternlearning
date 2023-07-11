package pm2learn.patterns;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloController extends Application {
    public HelloController() {
        this.model = StrategyModel.getModel(this);
        this.labels = new ArrayList<>(6);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 600);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private StrategyModel model;
    private List<Label> labels;

    public List<Label> getLabels() {
        return labels;
    }

    private void addLabelsToList() {
        labels.add(line1item1);
        labels.add(line1item2);
        labels.add(line1item3);
        labels.add(line2item1);
        labels.add(line2item2);
        labels.add(line2item3);
    }

    private void setup() {
        strategy1.addEventHandler(ActionEvent.ACTION, handler);
        strategy2.addEventHandler(ActionEvent.ACTION, handler);
        strategy3.addEventHandler(ActionEvent.ACTION, handler);
        addLabelsToList();
    }

    @FXML
    private TextFlow textFlow = new TextFlow();

    @FXML
    private Label activeStrategy = new Label();
    @FXML
    private ToggleButton strategy1 = new ToggleButton();
    @FXML
    private ToggleButton strategy2 = new ToggleButton();
    @FXML
    private ToggleButton strategy3 = new ToggleButton();
    @FXML
    private Label line1item1 = new Label();
    @FXML
    private Label line1item2 = new Label();

    @FXML
    private Label line1item3 = new Label();

    @FXML
    private Label line2item1 = new Label();
    @FXML
    private Label line2item2 = new Label();
    @FXML
    private Label line2item3 = new Label();
    @FXML
    private Button setup = new Button();
    boolean firstSelected;
    boolean secondSelected;
    boolean thirdSelected;

    String actives = "Active Strategies: ";

    private EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent tick) {


            String boxId = ((ToggleButton) tick.getSource()).getId();
            boolean selected = ((ToggleButton) tick.getSource()).selectedProperty().get();
            System.out.println(boxId+ " is selected");
            switch (boxId) {
                case "strategy1" -> firstSelected = selected;
                case "strategy2" -> secondSelected = selected;
                case "strategy3" -> thirdSelected = selected;
            }

            switch (boxId) {
                case "strategy1" -> model.setStrategy(new NumberStrategy());
                case "strategy2" -> model.setStrategy(new NameStrategy());
                case "strategy3" -> model.setStrategy(new CityStrategy());
            }
            if(selected){
                addToActiveFiltersLabel(selected);
            } else{
                removeFromActiveFilters(!selected);
            }

            activeStrategy.setText(actives);
            List<ToggleButton> buttons = new ArrayList<>(3);
            buttons.add(strategy1);
            buttons.add(strategy2);
            buttons.add(strategy3);

            model.apply(selected);
        }
    };

    private void addToActiveFiltersLabel(boolean yes) {
        if (yes) {
            if (!actives.contains(model.getStrategyName())) {
                actives += model.getStrategyName() + " ";
            }
        }
    }
    private void removeFromActiveFilters(boolean no) {
        if(no) {
            if (actives.contains(model.getStrategyName())) {
                actives = actives.replace(model.getStrategyName() + " ", "");
            }
        }
    }

    private void reset(){
        final String reset = "reset/setup";
        labels.forEach(label -> label.setText(reset));
    }
    @FXML
    private void builder(){
        setup();
        reset();
    }
    @FXML
    private void imp(ActionEvent event) {
        handler.handle(event);
    }
}
