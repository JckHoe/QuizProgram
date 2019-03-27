import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DisplayCorrectPane extends Application {
	
	public void start(Stage primaryStage){
	Pane correctPane= new Pane();
	correctPane.setStyle("-fx-background-color:#98fb98");
	correctPane.setMinSize(1020, 750);
	
	Text correctT= new Text("Correct!");
	correctT.setX(190);
	correctT.setY(350);
	correctT.setStyle("-fx-text-fill:white");
	correctT.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 150));
	
	Text point= new Text("+1 point");
	point.setX(380);
	point.setY(450);
	point.setStyle("-fx-text-fill:white");
	point.setFont(Font.font("Verdana", FontWeight.NORMAL, 70));
	
	Button correctBtn= new Button("Next Question");
	correctBtn.setPrefSize(250, 80);
	correctBtn.setLayoutX(400);
	correctBtn.setLayoutY(500);
	correctBtn.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
	
	correctPane.getChildren().addAll(correctT,point, correctBtn);
	
	Scene correct = new Scene(correctPane);
	primaryStage.setTitle("Correct! ");
	primaryStage.setScene(correct);
	primaryStage.show();
	
	correctBtn.setOnMouseClicked(e->{
		comboAndText a=new comboAndText();
		try {
			a.start(primaryStage);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	});
	
	}
}
