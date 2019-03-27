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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class comboAndTextAns extends Application {
	public void start(Stage primaryStage ) throws FileNotFoundException{
		
		StackPane ansPane= new StackPane();
		
		//background image for answer pane		
		FileInputStream ansBg= new FileInputStream("answerBg.jpg");
		Image iAns= new Image(ansBg);
		ImageView imageviewBg= new ImageView(iAns);
		ansPane.getChildren().add(imageviewBg);
		
		
		GridPane mainPane= new GridPane();
		mainPane.setMinSize(1020, 750);
		mainPane.setAlignment(Pos.CENTER);
		
		Text q1= new Text("1) Since it is a trapezium, "
				+ "\nwe need to do two shapes seperately"
				+ "\nArea: 4x5= 20 (rectangle)"
				+ "\n10-4=6cm ,6 x 5 x1/2= 15 (triangle)"
				+ "\nTotal: 20 + 15= 35");
		q1.setFont(Font.font("Calibri", FontWeight.NORMAL, 40));
		q1.setFill(Color.WHITE);
		
		Text q2= new Text("\n2) Since it is a rhombus, opposite angles are the same."
				+ "\n Thus, answer is 146");
		q2.setFont(Font.font("Calibri", FontWeight.NORMAL, 40));
		q2.setFill(Color.WHITE);
		
		Text q3= new Text("\n3) Since it is a right-angle triangle, "
				+ "\n180 - 90 - 30= 60");
		q3.setFont(Font.font("Calibri", FontWeight.NORMAL, 40));
		q3.setFill(Color.WHITE);
		
		VBox vPane= new VBox();
		vPane.setAlignment(Pos.BASELINE_CENTER);
		Button nextBtn= new Button("Next Question");
		nextBtn.setPrefSize(300, 100);
		nextBtn.setFont(Font.font("Verdana", FontWeight.NORMAL, 30));
		vPane.getChildren().add(nextBtn);
		
		mainPane.add(q1, 0, 0);
		mainPane.add(q2, 0, 1);
		mainPane.add(q3, 0, 2);
		
		ansPane.getChildren().addAll(mainPane,vPane);
		
		Scene scene= new Scene(ansPane);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		nextBtn.setOnMouseClicked(e->{
			DragDrop a=new DragDrop(); //test class
			try {
				a.start(primaryStage);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}
