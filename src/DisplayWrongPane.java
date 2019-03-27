import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DisplayWrongPane extends Application{
	
	public void start(Stage primaryStage){
	Pane wrongPane= new Pane();
	wrongPane.setStyle("-fx-background-color:#ff0000");
	wrongPane.setMinSize(1020, 750);
	
	Text wrongT= new Text("Wrong!");
	wrongT.setX(200);
	wrongT.setY(350);
	wrongT.setStyle("-fx-text-fill:white");
	wrongT.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 150));
	
	Text seeAns= new Text("See Answer:");
	seeAns.setX(200);
	seeAns.setY(450);
	seeAns.setStyle("-fx-text-fill:white");
	seeAns.setFont(Font.font("Verdana", FontWeight.NORMAL, 70));
	
	Button wrongBtn= new Button("Answer");
	wrongBtn.setPrefSize(200, 80);
	wrongBtn.setLayoutX(650);
	wrongBtn.setLayoutY(390);
	wrongBtn.setFont(Font.font("Verdana", FontWeight.NORMAL, 30));
	
	wrongPane.getChildren().addAll(wrongT,seeAns,wrongBtn);
	
	Scene wrong= new Scene(wrongPane);
	primaryStage.setTitle("Wrong! ");
	primaryStage.setScene(wrong);
	primaryStage.show();
	
	
	wrongBtn.setOnMouseClicked(e->{
		clickImageAns a= new clickImageAns();
		try {
			a.start(primaryStage);
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
		}
	});

	
	
	}
}
