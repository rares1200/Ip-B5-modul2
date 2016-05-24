package main;

import java.util.List;

import buildingParts.Coordinates;
import buildingParts.Door;
import buildingParts.Wall;
import buildingParts.Window;
import graphModel.Graph;
import graphModel.Room;
import graphView.controller.MainController;
import graphscycles.Algorithm;
import graphscycles.Connector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by Rumpi on 5/10/2016.
 */
public class GraphMain extends Application {
    private FXMLLoader fxmlLoader;
    private Pane p;
    private MainController myController;
    private static Graph graph;
    private Stage stage;

    public GraphMain(){
        /*
    	Graph graph=new Graph();
        Room room1=new Room("camera 1","parter");
        room1.addBuildingPart(new Wall(new Coordinates(0,0),new Coordinates(0,5)));
        room1.addBuildingPart(new Wall(new Coordinates(0,5),new Coordinates(5,5)));
        room1.addBuildingPart(new Window(new Coordinates(3,0),new Coordinates(4,0)));
        room1.addBuildingPart(new Door(new Coordinates(5,2),new Coordinates(5,3)));
        room1.getCorners().add(new Coordinates((float)0.0,(float)0.5));
        Room room2=new Room("camera 2","parter");

        Room room3=new Room("camera 3","etaj 1");
        Room room4=new Room("camera 4","etaj 2");
        graph.addNode(room1);
        graph.addNode(room2);
        graph.addNode(room3);
        graph.addNode(room4);

        this.graph=graph;*/
    	Algorithm a  = new Algorithm();
		List<Room> rooms = a.getRoomList();
		System.out.println("Camere:");
		for(int i=0;i<rooms.size();i++){
			System.out.print("Camera " + (i+1) + ": ");
			for(int j=0;j<rooms.get(i).getParts().size();j++){
				System.out.print(rooms.get(i).getParts().get(j).toString() + "(" + 
			rooms.get(i).getParts().get(j).getStart().getX()+"," + 
			rooms.get(i).getParts().get(j).getStart().getY() + "-->" + 
			rooms.get(i).getParts().get(j).getEnd().getX()+"," + 
			rooms.get(i).getParts().get(j).getEnd().getY() + ") ");
			}
			System.out.println("");
		}
		Connector c = new Connector(rooms);
		c.connect();
		a.getStairsMatch();
		
		rooms = c.getRooms();
		
		graphModel.Graph g = new graphModel.Graph(rooms,c.getGraphEdges());
		this.graph = g;
		
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Scene scene = new Scene(new Pane());
            fxmlLoader = new FXMLLoader(
                    getClass().getResource("/graphView/GraphView.fxml")
            );
            scene.setRoot((Parent) fxmlLoader.load());
            myController =fxmlLoader.<MainController>getController();
//            myController.initManager(this);

            myController.setGraph(this);
            primaryStage.setScene(scene);
            primaryStage.resizableProperty().setValue(Boolean.TRUE);
//            primaryStage.setWidth(900);
//            primaryStage.setHeight(700);
            primaryStage.sizeToScene();
            primaryStage.setTitle("GraphView");


            primaryStage.show();
            this.stage=primaryStage;

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void  main(String[] args) {
       // GraphMain graphMain=new GraphMain();

        launch(args);
    }

    public FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }

    public void setFxmlLoader(FXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }

    public Pane getP() {
        return p;
    }

    public void setP(Pane p) {
        this.p = p;
    }

    public MainController getMyController() {
        return myController;
    }

    public void setMyController(MainController myController) {
        this.myController = myController;
    }

    public Graph getGraph() {
        return graph;
    }

    public  void setGraph(Graph graph) {
        this.graph = graph;
    }


    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
