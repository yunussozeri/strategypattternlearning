package pm2learn.patterns;

import javafx.scene.control.Label;

import java.util.List;

public class StrategyModel {

    static StrategyModel  instance;
    private final HelloController controller;
    private Strategy strategy;
    private List<Label> labelList;


    public StrategyModel(HelloController controller){
        this(new NumberStrategy(),controller);
    }

    public StrategyModel(Strategy strategy, HelloController controller){
        this.strategy = strategy;
        this.controller = controller;

    }
    public static StrategyModel getModel(HelloController controller){
        return instance == null ? new StrategyModel(controller): instance;
    }

    public void setStrategy(Strategy strategy){
        this.strategy = strategy;
    }

    public String getStrategyName(){
        return this.strategy.getClass().getSimpleName();
    }

    public void apply(boolean s1) {
        this.labelList = controller.getLabels();
        strategy.show(labelList, s1);
        }
    }


