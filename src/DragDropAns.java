import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class DragDropAns extends Application{
	
	public void start(Stage primaryStage) throws FileNotFoundException{
	StackPane mainPane= new StackPane();
	mainPane.setMinSize(1020, 750);
	mainPane.setAlignment(Pos.BOTTOM_CENTER);
	
	FileInputStream ans= new FileInputStream("dragDropAns.jpg");
	Image answer= new Image(ans);
	final ImageView ansImg= new ImageView(answer);
	
	mainPane.getChildren().add(ansImg);
	
	Button btn= new Button("Completed");
	btn.setPrefSize(300, 100);
	btn.setFont(Font.font("Verdana", FontWeight.NORMAL, 30));
	
	mainPane.getChildren().add(btn);
	btn.setOnAction(new EventHandler<ActionEvent>(){
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
	
	Scene scene= new Scene(mainPane);
	primaryStage.setScene(scene);
	primaryStage.show();
	
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}
