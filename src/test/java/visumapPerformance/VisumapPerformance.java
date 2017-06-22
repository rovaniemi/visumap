package visumapPerformance;

import com.google.gson.Gson;
import javafx.event.Event;
import javafx.scene.control.Button;
import visumap.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import visumap.Statistic.StatsJson;

import java.util.ArrayList;
import java.util.List;


public class VisumapPerformance extends Application {

    private XYChart.Series timeAsar;
    private XYChart.Series timeDijkstra;
    private XYChart.Series memoryAstar;
    private XYChart.Series memoryDijkstra;
    private Text iterations;
    private List<AvarageCalculator> astarTimeList;
    private List<AvarageCalculator> dijkstraTimeList;
    private int i;
    private Text fullTime;
    private long startTime;
    private static Router router;
    private boolean onOff;
    private Timeline timeline;

    @Override
    public void start(Stage stage) throws InterruptedException {
        this.startTime = System.currentTimeMillis();
        formatLists();
        formatSeries();
        this.iterations = new Text("Iteratios: " + this.i);
        this.fullTime = new Text("Time: " + (System.currentTimeMillis() - startTime));
        LineChart<Number, Number> time = getTimeChart();
        time.getData().add(this.timeAsar);
        time.getData().add(this.timeDijkstra);
        time.setCreateSymbols(false);
        LineChart<Number, Number> memory = getMemoryChart();
        memory.getData().add(this.memoryAstar);
        memory.getData().add(this.memoryDijkstra);
        VBox vBox = new VBox();
        vBox.getChildren().add(time);
        vBox.getChildren().add(memory);
        vBox.getChildren().add(iterations);
        vBox.getChildren().add(fullTime);
        vBox.getChildren().add(greateButton());
        Scene scene = new Scene(vBox, 1000, 1000);
        stage.setScene(scene);
        stage.show();
    }


    public void formatSeries(){
        this.timeAsar = getSeries("Astar");
        this.timeDijkstra = getSeries("Dijkstra");
        this.memoryAstar = getSeries("Astar");
        this.memoryDijkstra = getSeries("Dijkstra");
    }

    public void formatLists(){
        this.astarTimeList = new ArrayList<>();
        this.dijkstraTimeList = new ArrayList<>();
        for (int j = 0; j < 3050; j++) {
            this.astarTimeList.add(new AvarageCalculator());
            this.dijkstraTimeList.add(new AvarageCalculator());
        }
    }

    public static void main(String[] args) {
        router = new Router();
        launch(VisumapPerformance.class);
    }

    public LineChart<Number, Number> getTimeChart(){
        NumberAxis x = new NumberAxis(-25, 3025, 150);
        NumberAxis y = new NumberAxis(0, 200, 100);
        x.setLabel("Nodes in shortest path");
        y.setLabel("Time");
        LineChart<Number, Number> time = new LineChart<>(x, y);
        time.setTitle("Time testing");
        return time;
    }

    public LineChart<Number, Number> getMemoryChart(){
        NumberAxis x = new NumberAxis(-25, 3025, 150);
        NumberAxis y = new NumberAxis(0, 1000, 100);
        x.setLabel("Nodes in shortest path");
        y.setLabel("Memory");
        LineChart<Number, Number> memory = new LineChart<>(x, y);
        memory.setTitle("Memory testing");
        return memory;
    }

    public XYChart.Series getSeries(String name){
        XYChart.Series series = new XYChart.Series();
        series.setName(name);
        return series;
    }

    public void refreshChart(){
        this.timeAsar.getData().clear();
        this.timeDijkstra.getData().clear();
        for (int j = 0; j < 3050; j++) {
            if(this.astarTimeList.get(j).getAvarage() != 0){
                this.timeAsar.getData().add(new XYChart.Data(j, this.astarTimeList.get(j).getAvarage()));
            }
            if(this.dijkstraTimeList.get(j).getAvarage() != 0){
                this.timeDijkstra.getData().add(new XYChart.Data(j, this.dijkstraTimeList.get(j).getAvarage()));
            }
        }
    }

    public void performance(){
        int[] randomPoints = new Gson().fromJson(router.randomPoints(),int[].class);
        StatsJson dijkstra = new Gson().fromJson(router.visualizeAlgorithm("dijkstra",randomPoints), StatsJson.class);
        StatsJson astar = new Gson().fromJson(router.visualizeAlgorithm("astar", randomPoints), StatsJson.class);
        if(dijkstra.shortestPath.size() < 3050 && dijkstra.time < 200){
            dijkstraTimeList.get(dijkstra.shortestPath.size()).addTime((int) dijkstra.time);
        }
        if(astar.shortestPath.size() < 3050 && astar.time < 200){
            astarTimeList.get(astar.shortestPath.size()).addTime((int) astar.time);
        }
    }

    public Button greateButton() {
        Button button = new Button();
        button.setId("Start testing");
        button.setText("Start/Stop");
        button.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (onOff) {
                    onOff = false;
                    refreshChart();
                    if(timeline != null){
                        timeline.stop();
                    }
                } else {
                    onOff = true;
                    timeline = new Timeline(
                            new KeyFrame(Duration.millis(1000),
                                    new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent t) {
                                            iterations.setText("Iteratios: " + i++);
                                            fullTime.setText("Time: " + (System.currentTimeMillis() - startTime));
                                            performance();
                                        }
                                    })
                    );
                    timeline.setCycleCount(Timeline.INDEFINITE);
                    timeline.play();
                }
            }
        });
        return button;
    }
}