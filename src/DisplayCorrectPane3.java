import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DisplayCorrectPane3 extends Application {
	
	public void start(Stage primaryStage){
	Pane correctPane= new Pane();
	correctPane.setStyle("-fx-background-color:#98fb98");
	correctPane.setMinSize(1020, 750);
	
	Text score= new Text("Correct!");
	score.setX(190);
	score.setY(350);
	score.setStyle("-fx-text-fill:white");
	score.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 150));
	
	Text point= new Text("+1 point");
	point.setX(380);
	point.setY(450);
	point.setStyle("-fx-text-fill:white");
	point.setFont(Font.font("Verdana", FontWeight.NORMAL, 70));
	
	Button scoreBtn= new Button("Completed");
	scoreBtn.setPrefSize(250, 80);
	scoreBtn.setLayoutX(400);
	scoreBtn.setLayoutY(500);
	scoreBtn.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
	
	correctPane.getChildren().addAll(score,point, scoreBtn);
	scoreBtn.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent event){
			Quiz quiz = new Quiz();
			String tempString="";
			try {
				File file = new File("tempUser.txt");
				if(file.exists()){
					Scanner input = new Scanner(file);
					while (input.hasNext()){
						quiz.curUser.setUsername(input.next());
						tempString = quiz.curUser.getUsername();
					}
					input.close();
				}
			} catch (FileNotFoundException e) {
				
			}
			String[] buffer;
			int i = 0;
			try {
				File file = new File("User.txt");
				if(file.exists()){
					Scanner input = new Scanner(file);
					while (input.hasNext()){
						buffer = input.nextLine().split(",");
						if(buffer[0].equals(tempString)){
							quiz.curUser.setInfo(buffer);
						}
					}
					input.close();
				}
			} catch (FileNotFoundException e) {
				
			}
			quiz.LoginPage(primaryStage);
		}
	});
	
	Scene correct = new Scene(correctPane);
	primaryStage.setTitle("Correct! ");
	primaryStage.setScene(correct);
	primaryStage.show();
	
//put setOnMouseClick here and the next pane to go to
	
	}
}
