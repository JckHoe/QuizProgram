
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class clickImage extends Application {
	
	public void start(Stage primaryStage) throws FileNotFoundException {

	Pane mainPane= new Pane();
	mainPane.setMinSize(1020, 750);

	GridPane grid= new GridPane();
	grid.setMinSize(1020, 750);
	
	//importing all images
	
	FileInputStream img= new FileInputStream("basketball_court.jpg");
	Image bg= new Image(img);
	ImageView imageview= new ImageView(bg);
	mainPane.getChildren().add(imageview);
	
	FileInputStream b135= new FileInputStream("basketball_135.png");
	Image i1= new Image(b135);
	ImageView imageview1= new ImageView(i1);
	imageview1.setX(150);
	imageview1.setY(100);
	mainPane.getChildren().add(imageview1);
	
	
	FileInputStream b108= new FileInputStream("basketball_108.png");
	Image i2= new Image(b108);
	ImageView imageview2= new ImageView(i2);
	imageview2.setX(600);
	imageview2.setY(100);
	mainPane.getChildren().add(imageview2);
	
	FileInputStream b90= new FileInputStream("basketball_90.png");
	Image i3= new Image(b90);
	ImageView imageview3= new ImageView(i3);
	imageview3.setX(150);
	imageview3.setY(450);
	mainPane.getChildren().add(imageview3);

	
	FileInputStream b120= new FileInputStream("basketball_120.png");
	Image i4= new Image(b120);
	ImageView imageview4= new ImageView(i4);
	imageview4.setX(600);
	imageview4.setY(450);
	mainPane.getChildren().add(imageview4);
	
	//add question
	Text ques =new Text("What is the interior angle of a regular hexagon?");
	ques.setFont(Font.font("Verdana", FontWeight.BOLD, 35));
	ques.setX(50);
	ques.setY(40);
	
	mainPane.getChildren().add(ques);

	//answer pane
	

	
	Scene scene= new Scene(mainPane);
	primaryStage.setTitle("Click The Correct Basketball");
	primaryStage.setScene(scene);
	primaryStage.show();
	
	//switching to correctPane
	imageview1.setOnMouseClicked(e->{
		DisplayWrongPane pane= new DisplayWrongPane();
		
		pane.start(primaryStage);
		
	});
	
	//switching to wrongPane
	imageview2.setOnMouseClicked(e->{
		DisplayWrongPane pane= new DisplayWrongPane();
		
		pane.start(primaryStage);
	});

	imageview3.setOnMouseClicked(e->{
		DisplayWrongPane pane= new DisplayWrongPane();
		
		pane.start(primaryStage);
	});

	imageview4.setOnMouseClicked(e->{
	
		DisplayCorrectPane pane= new DisplayCorrectPane();
		
		pane.start(primaryStage);
	});


	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
