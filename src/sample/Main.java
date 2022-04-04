package sample;

import javafx.scene.input.MouseButton;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.gillius.jfxutils.chart.ChartPanManager;
import org.gillius.jfxutils.chart.JFXChartUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;

public class Main extends Application {
    private static int nameCount = 1;
    public static String globalPath="";

    /**
     * This is the main method of the program.
     * It generates application of program.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Flower by Mykhailenko Oleksandra");
        Label lbl = new Label("  Функція: \n  X= ρ cos(φ)\n  Y= ρ sin(φ)" +
                "\n  ρ= Ro+A cos(φ 2π/S)\n  φ, S є [0; 360]\n  φ, S задаються в градусах\n\n  Задайте параметри:");
        lbl.setFont(Font.font("Cambria", 20));

        Label rText = new Label("  R₀ =");
        rText.setFont(Font.font("Cambria", 20));
        TextField rValue = new TextField();
        rValue.setPrefColumnCount(15);
        FlowPane R = new FlowPane(Orientation.HORIZONTAL, 10, 10,rText, rValue);

        Label aText = new Label("  A =");
        aText.setFont(Font.font("Cambria", 20));
        TextField aValue = new TextField();
        aValue.setPrefColumnCount(16);
        FlowPane A = new FlowPane(Orientation.HORIZONTAL, 10, 10,aText, aValue);

        Label sText = new Label("  S =");
        sText.setFont(Font.font("Cambria", 20));
        TextField sValue = new TextField();
        sValue.setPrefColumnCount(16);
        FlowPane S = new FlowPane(Orientation.HORIZONTAL, 10, 10,sText, sValue);

        Label stepText = new Label("  Крок φ =");
        stepText.setFont(Font.font("Cambria", 20));
        TextField stepValue = new TextField();
        stepValue.setPrefColumnCount(13);
        FlowPane step = new FlowPane(Orientation.HORIZONTAL, 10, 10,stepText, stepValue);

        Label rangeTextOne = new Label("  Діапазон φ: від");
        rangeTextOne.setFont(Font.font("Cambria", 20));
        Label rangeTextTwo = new Label("до");
        rangeTextTwo.setFont(Font.font("Cambria", 20));
        TextField rangeValueFrom = new TextField();
        rangeValueFrom.setPrefColumnCount(3);
        TextField rangeValueTo = new TextField();
        rangeValueTo.setPrefColumnCount(3);
        FlowPane range = new FlowPane(Orientation.HORIZONTAL, 10, 10,rangeTextOne, rangeValueFrom, rangeTextTwo,rangeValueTo);

        FlowPane allValue = new FlowPane();
        allValue.setPadding(new Insets(10, 0, 0, 20));

        allValue.getChildren().add(lbl);
        allValue.getChildren().add(R);
        allValue.getChildren().add(A);
        allValue.getChildren().add(S);
        allValue.getChildren().add(step);
        allValue.getChildren().add(range);

        final double[] x = {55};
        final double[] minusX = {-55};
        final double[] y = {55};
        final double[] minusY = {-55};
        final NumberAxis[] xAxis = {new NumberAxis(-55, 55, 1)};
        final NumberAxis[] yAxis = {new NumberAxis(-55, 55, 1)};
        final LineChart<Number, Number>[] sc = new LineChart[]{new LineChart<Number, Number>(xAxis[0], yAxis[0])};
        xAxis[0].setLabel("OX");
        yAxis[0].setLabel("OY");
        sc[0].setTitle("Графік функції");
        sc[0].setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
        sc[0].setCreateSymbols(false);

        final int[] counter = {0};
        Button build = new Button("Побудувати графік");
        build.setFont(Font.font("Cambria", 20));
        XYChart.Series series = new XYChart.Series();
        final XYChart.Series[][] series1 = {{series, series, series, series,series, series,series, series}};
        build.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Controller c = new Controller();
               if(c.check(rValue.getText(), aValue.getText(), sValue.getText(),stepValue.getText(),rangeValueFrom.getText(), rangeValueTo.getText())){

                   double R=Double.parseDouble(rValue.getText());
                   double A=Double.parseDouble(aValue.getText());
                   double S=Double.parseDouble(sValue.getText());
                   double step=Double.parseDouble(stepValue.getText());
                   double range1=Double.parseDouble(rangeValueFrom.getText());
                   double range2=Double.parseDouble(rangeValueTo.getText());
                   double ro;
                   double X;
                   double Y;
                   ro = R + A * Math.cos(Math.toRadians(range1) * 2 * Math.PI / Math.toRadians(S));
                   double X1=ro*Math.cos(Math.toRadians(range1));
                   double Y1=ro*Math.sin(Math.toRadians(range1));
                   series1[0][counter[0]] = new XYChart.Series();
                   series1[0][counter[0]].setName("Функція");
                   while(range1<=range2) {
                       ro = R + A * Math.cos(Math.toRadians(range1) * 2 * Math.PI / Math.toRadians(S));
                       X=ro*Math.cos(Math.toRadians(range1));
                       Y=ro*Math.sin(Math.toRadians(range1));
                       range1 = range1 + step;
                       series1[0][counter[0]].getData().add(new XYChart.Data(X, Y));
                   }
                   if(range2==360) {
                       series1[0][counter[0]].getData().add(new XYChart.Data(X1, Y1));
                   }
                   sc[0].getData().addAll(series1[0][counter[0]]);
                   sc[0].setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
                   sc[0].setCreateSymbols(false);
                   counter[0]++;
               }
            }
        });
        Button clear = new Button("Очистити");
        clear.setFont(Font.font("Cambria", 20));
        LineChart<Number, Number> finalSc1 = sc[0];
        clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc[0].getData().clear();
                counter[0]=0;
            }
        });

        Button save = new Button("Зберегти графік");
        save.setFont(Font.font("Cambria", 20));
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!globalPath.equals(null) && !globalPath.equals("")) {
                    String name = rValue.getText() + ";" + aValue.getText() + ";" + sValue.getText() + ";" + stepValue.getText() + ";" + rangeValueFrom.getText() + ";" + rangeValueTo.getText();
                    String path = globalPath + name;
                    savePngOfChart(sc[0], path);
                    JOptionPane.showMessageDialog(null, "Зображення графіку вдало збережено у " + globalPath, "Сповіщення", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Ви не вказали папку, куди потрібно зберігати фото", "Помилка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        allValue.getChildren().add(build);
        allValue.getChildren().add(clear);
        allValue.getChildren().add(save);
        allValue.setOrientation(Orientation.VERTICAL);

        Button increase = new Button("+");
        increase.setFont(Font.font("Cambria", 20));
        Button decrease = new Button("-");
        decrease.setFont(Font.font("Cambria", 20));
        FlowPane scale = new FlowPane(Orientation.HORIZONTAL, 10, 10,increase, decrease);

        BorderPane fin = new BorderPane ();

        increase.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                x[0] = x[0] -10;
                y[0] = y[0] -10;
                minusX[0] = minusX[0] +10;
                minusY[0] = minusY[0] +10;
                if(x[0]>15) {
                    sc[0] = generateChart(x[0], minusX[0], y[0], minusY[0]);
                    for(int i = 0; i< counter[0]; i++) {
                        sc[0].getData().addAll(series1[0][i]);
                    }
                    sc[0].setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
                    sc[0].setCreateSymbols(false);

                    ChartPanManager panner = new ChartPanManager(sc[0]);
                    //while presssing the left mouse button, you can drag to navigate
                    panner.setMouseFilter(mouseEvent -> {
                        if (mouseEvent.getButton() == MouseButton.PRIMARY) {//set your custom combination to trigger navigation
                            // let it through
                        } else {
                            mouseEvent.consume();
                        }
                    });
                    panner.start();

                    //holding the right mouse button will draw a rectangle to zoom to desired location
                    JFXChartUtil.setupZooming(sc[0], mouseEvent -> {
                        if (mouseEvent.getButton() != MouseButton.SECONDARY)//set your custom combination to trigger rectangle zooming
                            mouseEvent.consume();
                    });

                    fin.setCenter(sc[0]);
                }
            }
        });

        decrease.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                x[0] = x[0] +10;
                y[0] = y[0] +10;
                minusX[0] = minusX[0] -10;
                minusY[0] = minusY[0] -10;
                sc[0] =  generateChart( x[0], minusX[0], y[0], minusY[0] );
                for(int i = 0; i< counter[0]; i++) {
                    sc[0].getData().addAll(series1[0][i]);
                }
                sc[0].setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
                sc[0].setCreateSymbols(false);

                ChartPanManager panner = new ChartPanManager(sc[0]);
                //while presssing the left mouse button, you can drag to navigate
                panner.setMouseFilter(mouseEvent -> {
                    if (mouseEvent.getButton() == MouseButton.PRIMARY) {//set your custom combination to trigger navigation
                        // let it through
                    } else {
                        mouseEvent.consume();
                    }
                });
                panner.start();

                //holding the right mouse button will draw a rectangle to zoom to desired location
                JFXChartUtil.setupZooming(sc[0], mouseEvent -> {
                    if (mouseEvent.getButton() != MouseButton.SECONDARY)//set your custom combination to trigger rectangle zooming
                        mouseEvent.consume();
                });

                fin.setCenter(sc[0]);
            }
        });


        Button set = new Button();
        Image imageSet = new Image(getClass().getResourceAsStream("SetImg.png"));
        set.graphicProperty().setValue(new ImageView(imageSet));
        set.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    Setting s=new Setting(globalPath);
                 //   s.setVisible(true);

                }
        });

        fin.setTop(set);
        fin.setLeft(allValue);
        fin.setCenter(sc[0]);
//        fin.setBottom(scale);
//        BorderPane.setMargin(scale, new Insets(10.0, 0.0, 5.0, 550.0));

        Scene scene  = new Scene(fin, 1000, 600);
        primaryStage.setScene(scene);

        fin.setStyle("-fx-background-color: #f5c9a2;");
        primaryStage.show();


        ChartPanManager panner = new ChartPanManager(sc[0]);
        //while presssing the left mouse button, you can drag to navigate
        panner.setMouseFilter(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {//set your custom combination to trigger navigation
                // let it through
            } else {
                mouseEvent.consume();
            }
        });
        panner.start();

        //holding the right mouse button will draw a rectangle to zoom to desired location
        JFXChartUtil.setupZooming(sc[0], mouseEvent -> {
            if (mouseEvent.getButton() != MouseButton.SECONDARY)//set your custom combination to trigger rectangle zooming
                mouseEvent.consume();
        });

    }

    /**
     * The method launches  a program
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The method generates a new chart
     * @param x
     * @param minusX
     * @param y
     * @param minusY
     * @return
     */
    public LineChart<Number,Number> generateChart(double x, double minusX, double y, double minusY){
        final NumberAxis xAxis = new NumberAxis(minusX, x, 1);
        final NumberAxis yAxis = new NumberAxis(minusY, y, 1);
        final LineChart<Number,Number> sc = new LineChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("OX");
        yAxis.setLabel("OY");
        sc.setTitle("Графік функції");
        return sc;
    }

    /**
     * The method creates screenshots of the graphic and saves them to the desired folder
     * @param chart
     * @param path
     */
        public static void savePngOfChart(LineChart<Number, Number> chart, String path){
            WritableImage image = chart.snapshot(new SnapshotParameters(),null);
            String modernPath=path+nameCount+".png";
            File file = new File(modernPath);
            while(file.exists()) {
                nameCount++;
                modernPath=path+nameCount+".png";
                file = new File(modernPath);
            }
            try{
                ImageIO.write(SwingFXUtils.fromFXImage(image,null),"png",file);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
}
