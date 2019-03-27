import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FillInTheBlanks extends Question{
	
	public FillInTheBlanks(String question){
		super(question,-1);
	}

	public GridPane display() {
		GridPane pane = new GridPane();
				//QUESTION 1
				StackPane firstQuestion = new StackPane();
				
				GridPane grid1 = new GridPane();
				grid1.setPadding(new Insets(11,12,13,14));
				grid1.setHgap(2);
				grid1.setVgap(2);
				grid1.setAlignment(Pos.CENTER);
				
				FlowPane question1 = new FlowPane(); 
				
				// inserting background image for Question 1
				FileInputStream q1bg = null;
				try {
					q1bg = new FileInputStream("grassy_bg.jpg");
				} catch (FileNotFoundException e2) {
					
				}
				Image bgImage = new Image(q1bg);
				ImageView bgImageView = new ImageView(bgImage);
				bgImageView.setX(1020);
				bgImageView.setY(750);
				firstQuestion.getChildren().add(bgImageView);
				
				//set flow pane settings
				question1.setPadding(new Insets(11,12,13,14));
				question1.setHgap(2);
				question1.setVgap(2);
				
				// creating first question
				Label titleQuestion1 = new Label(getQuestion());
				titleQuestion1.setFont(Font.font("Calibri", FontWeight.BOLD,20));
				titleQuestion1.setTextFill(Color.BLACK);
				Label ques1 = new Label("9.6 + 3.2 = ");
				ques1.setTextFill(Color.DARKGREEN);
				ques1.setFont(Font.font("Calibri", FontWeight.BOLD,60));
				Label ques2 = new Label("1/2 - 2/5 = ");
				ques2.setTextFill(Color.DARKGREEN);
				ques2.setFont(Font.font("Calibri", FontWeight.BOLD,60));
				// text field to write answers
				TextField answer1 = new TextField();
				answer1.setPrefColumnCount(3);
				answer1.setStyle("-fx-text-fill: darkgreen");
				answer1.setFont(Font.font("Calibri", FontWeight.BOLD,60));
				
				TextField answer2 = new TextField();
				answer2.setPrefColumnCount(3);
				answer2.setStyle("-fx-text-fill: darkgreen");
				answer2.setFont(Font.font("Calibri", FontWeight.BOLD,60));
				
				// next button
				FileInputStream greenButton = null;
				try {
					greenButton = new FileInputStream("next green button.png");
				} catch (FileNotFoundException e1) {
					
				}
				Image buttonImage = new Image(greenButton);
				ImageView buttonImageView = new ImageView(buttonImage);
				buttonImageView.setOnMouseClicked(e->{
					if((answer1.getText().equals("12.8") || answer1.getText().equals("64/5")) && ((answer2.getText().equals("0.1") || (answer2.getText().equals("1/10"))))){
						pane.getChildren().remove(firstQuestion);
						pane.add(correct(), 0, 0);
						totalScore = 100;
					}
					else
						pane.getChildren().remove(firstQuestion);
						pane.add(wrong("12.8"), 0, 0);
			
				}); // end setOnMouseClicked event handler
				
				// add elements to grid pane
				grid1.add(titleQuestion1, 1, 0);
				grid1.add(ques1, 1, 1);
				grid1.add(answer1, 2, 1);
				grid1.add(buttonImageView, 2, 3);
				grid1.add(ques2, 1, 2);
				grid1.add(answer2, 2, 2);
				firstQuestion.getChildren().add(grid1);
		pane.add(firstQuestion, 0, 1);
		return pane;
	}

}
