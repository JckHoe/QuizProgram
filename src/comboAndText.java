import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class comboAndText extends Application {
	public void start (Stage primaryStage) throws FileNotFoundException {
		
		StackPane stack= new StackPane();
		stack.setMinSize(1020, 750);
		
		GridPane pane= new GridPane();
		pane.setMinSize(1020, 750);
		
		GridPane mainPane= new GridPane();
		
		mainPane.setHgap(10);
		mainPane.setVgap(10);
		
		VBox v1= new VBox();
		v1.setSpacing(10);
		
		FlowPane fp= new FlowPane();
		Text question= new Text("Solve the following below: ");
		question.setFont(Font.font("Calibri", FontWeight.NORMAL, 50));
		fp.getChildren().add(question);
		
		pane.add(fp, 0, 0);
		
		//adding background picture
		FileInputStream img= new FileInputStream("snow.jpg");
		Image back= new Image(img);
		ImageView background= new ImageView(back);
		stack.getChildren().add(background);
		
		
		//First question
		FileInputStream img1= new FileInputStream("trapeziumQ.jpg");
		Image trap= new Image(img1);
		ImageView trapezium= new ImageView(trap);
		v1.getChildren().add(trapezium);
		
		Text shape1= new Text("Choose the following shape: ");
		shape1.setFont(Font.font("Calibri", FontWeight.NORMAL, 20));
		
		ComboBox<String> combobox1 = new ComboBox<>();
		combobox1.getItems().addAll("Rectangle","Quadrilateral","Trapezium","Square");
		combobox1.setMinSize(320, 50);
		
		Text ques1= new Text("Find the area of the following shape: ");
		ques1.setFont(Font.font("Calibri", FontWeight.NORMAL, 20));
		
		TextArea txt1= new TextArea();
		txt1.setMaxSize(320, 50);
		
		v1.getChildren().addAll(ques1,txt1,shape1, combobox1);
		mainPane.add(v1, 0, 1);
		
		//Second Question
		VBox v2= new VBox();
		v2.setSpacing(10);
		
		FileInputStream img2= new FileInputStream("rhombusQ.jpg");
		Image rho= new Image(img2);
		ImageView rhombus= new ImageView(rho);
		v2.getChildren().add(rhombus);
		
		Text shape2= new Text("Choose the following shape: ");
		shape2.setFont(Font.font("Calibri", FontWeight.NORMAL, 20));
		
		ComboBox<String> combobox2 = new ComboBox<>();
		combobox2.getItems().addAll("Quadrilateral","Rhombus","Cube", "Trapezium");
		combobox2.setMinSize(320, 50);
		
		Text ques2= new Text("Find the angle of the ? : ");
		ques2.setFont(Font.font("Calibri", FontWeight.NORMAL, 20));
		
		TextArea txt2= new TextArea();
		txt2.setMaxSize(320, 50);
		
		v2.getChildren().addAll(ques2,txt2,shape2,combobox2);
		mainPane.add(v2, 1, 1);
		
		//Third Question
		VBox v3= new VBox();
		v3.setSpacing(10);
		
		FileInputStream img3= new FileInputStream("triQ.jpg");
		Image tri= new Image(img3);
		ImageView triangle= new ImageView(tri);
		v3.getChildren().add(triangle);
		
		Text shape3= new Text("Choose the following shape: ");
		shape3.setFont(Font.font("Calibri", FontWeight.NORMAL, 20));
		
		ComboBox<String> combobox3 = new ComboBox<>();
		combobox3.getItems().addAll("Isosceles","Acute","Right-angle", "Equilateral");
		combobox3.setMinSize(320, 50);
		
		Text ques3= new Text("Find the value of x : ");
		ques3.setFont(Font.font("Calibri", FontWeight.NORMAL, 20));
		
		TextArea txt3= new TextArea();
		txt3.setMaxSize(320, 50);
		
		v3.getChildren().addAll(ques3,txt3,shape3,combobox3);
		mainPane.add(v3, 2, 1);
		
		//adding the submit button
		Button submit= new Button("Submit");
		submit.setFont(Font.font("Calibri", FontWeight.BOLD, 40));
		submit.setPrefSize(200, 80);
		submit.setAlignment(Pos.BOTTOM_CENTER);
		
		v2.getChildren().add(submit);
		
		//adding all of the questions
		pane.add(mainPane, 0, 1);
		stack.getChildren().add(pane);
		
		Scene scene= new Scene(stack);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Solve the following");
		primaryStage.show();
		
		submit.setOnMouseClicked(e->{
			if(combobox1.getValue().equals("Trapezium") && txt1.getText().equals("35") 
					&& combobox2.getValue().equals("Rhombus") && txt2.getText().equals("146") 
					&& combobox3.getValue().equals("Right-angle") && txt3.getText().equals("60")){
				
				DisplayCorrectPane correct= new DisplayCorrectPane();
				correct.start(primaryStage);
			}
			else{
				DisplayWrongPane2 wrong= new DisplayWrongPane2();
				
				wrong.start(primaryStage);
			}
			
		});
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}
