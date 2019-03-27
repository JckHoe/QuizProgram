import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class clickImageAns extends Application{
	public void start(Stage primaryStage) throws FileNotFoundException{

	StackPane ansPane= new StackPane();
		
	//background image for answer pane		
	FileInputStream ansBg= new FileInputStream("answerBg.jpg");
	Image iAns= new Image(ansBg);
	ImageView imageviewBg= new ImageView(iAns);
	imageviewBg.setX(600);
	imageviewBg.setY(450);
	ansPane.getChildren().add(imageviewBg);
	
	
	GridPane ansGrid= new GridPane();
	ansGrid.setAlignment(Pos.CENTER);
	ansGrid.setMinSize(1020, 750);
	Text ans= new Text("If the polygon has n sides, "
			+ "\n the angle sum is (n-2)x180"
			+ "\n Divide answer by n to get size of one angle"
			+ "\n Total: 720"
			+ "\n One angle: 720    6 =120");
	ans.setFill(Color.WHITE);
	ans.setFont(Font.font("Calibri", FontWeight.NORMAL, 50));
	
	VBox vPane= new VBox();
	vPane.setAlignment(Pos.BASELINE_CENTER);
	Button nextBtn= new Button("Next Question");
	nextBtn.setPrefSize(300, 100);
	nextBtn.setFont(Font.font("Verdana", FontWeight.NORMAL, 30));
	vPane.getChildren().add(nextBtn);

	ansGrid.add(ans, 0, 0);
	ansPane.getChildren().addAll(ansGrid,vPane);
	
	Scene answer= new Scene(ansPane);
	primaryStage.setTitle("Answer ");
	primaryStage.setScene(answer);
	primaryStage.show();
	

	nextBtn.setOnMouseClicked(e->{
		comboAndText a=new comboAndText();
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
