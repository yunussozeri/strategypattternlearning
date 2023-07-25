package pm2learn.patterns;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;

import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

public class HelloController   {
    public HelloController() {
        this.model = StrategyModel.getModel(this);
        this.labels = new ArrayList<>(6);
    }


    private final StrategyModel model;
    private final List<Label> labels;

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


    private final EventHandler<ActionEvent> handler = new EventHandler<>() {

        boolean selected;
        private Runnable runner = new Runnable() {


            @Override
            public void run() {
                startLabelChangeTimeline(selected);
            }
        };
        @Override
        public void handle(ActionEvent tick) {
            Object obj = new Object();
            obj.equals(this);

            setup.setOnAction((event)-> System.out.println("Hallo"));
            String boxId = ((ToggleButton) tick.getSource()).getId();
            boolean selected = ((ToggleButton) tick.getSource()).selectedProperty().get();
            this.selected = selected;
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
            if (selected) {
                addToActiveFiltersLabel(selected);
                startLabelChangeTimeline(selected);
                Platform.runLater(runner);
            } else {
                removeFromActiveFilters(!selected);
                model.apply(selected);
                stopLabelChangeTimeline();
            }

            activeStrategy.setText(actives);

            //model.apply(selected);
        }
    };


        private Timeline labelChangeTimeline;
        private static final int LABEL_CHANGE_INTERVAL = 1000; // 5 seconds

    private void startLabelChangeTimeline(boolean s) {
        labelChangeTimeline = new Timeline(new KeyFrame(Duration.millis(LABEL_CHANGE_INTERVAL), event -> changeLabelContent(s)));
        labelChangeTimeline.setCycleCount(5);
        labelChangeTimeline.play();
    }

    private void stopLabelChangeTimeline() {
        if (labelChangeTimeline != null) {
            labelChangeTimeline.stop();
            labelChangeTimeline = null;
        }
    }

    private int labelChangeCount;

    private void changeLabelContent(boolean selected) {
        if (labelChangeCount < labels.size()) {
            model.apply(selected);
            labelChangeCount++;
        } else {
            labelChangeCount = 0;
        }
    }
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