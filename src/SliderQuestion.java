import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SliderQuestion extends Question{
	boolean checker;
	@Override
	GridPane display() {
		GridPane pane = new GridPane();
				//QUESTION 2
				StackPane secondQuestion = new StackPane();
				
				FileInputStream q2bg = null;
				try {
					q2bg = new FileInputStream("grassy_bg2.jpg");
				} catch (FileNotFoundException e2) {
					
				}
				Image bgImage2 = new Image(q2bg);
				ImageView bgImageView2 = new ImageView(bgImage2);
				bgImageView2.setX(1020);
				bgImageView2.setY(750);
				
				// include blur effect to background
				BoxBlur bbbg = new BoxBlur();
		        bbbg.setWidth(5);
		        bbbg.setHeight(5);
		        bbbg.setIterations(3);
		        bgImageView2.setEffect(bbbg);
				secondQuestion.getChildren().add(bgImageView2);
				
				// make new grid pane
				GridPane grid2 = new GridPane();
				grid2.setPadding(new Insets(11,12,13,14));
				grid2.setHgap(2);
				grid2.setVgap(2);
				grid2.setAlignment(Pos.CENTER);
				
				// inserting slider1 properties
				Slider slider1 = new Slider();
				slider1.setMin(1);
				slider1.setMax(10);
				slider1.setValue(1);
				slider1.setShowTickLabels(true);
				slider1.setShowTickMarks(true);
				slider1.setMajorTickUnit(1);
				slider1.setMinorTickCount(9);
				slider1.setBlockIncrement(1);
				slider1.setSnapToTicks(true);
				
				// inserting slider2 properties
				Slider slider2 = new Slider();
				slider2.setMin(1);
				slider2.setMax(10);
				slider2.setValue(1);
				slider2.setShowTickLabels(true);
				slider2.setShowTickMarks(true);
				slider2.setMajorTickUnit(1);
				slider2.setMinorTickCount(9);
				slider2.setBlockIncrement(1);

				Label quesSlider = new Label("Question 2: Change 1.5 to fraction.");
				quesSlider.setTextFill(Color.rgb(3, 54, 5));
				quesSlider.setFont(Font.font("Calibri", FontWeight.BOLD,20));
				
				Label blank = new Label("                                   ");
				blank.setFont(Font.font("Calibri", FontWeight.BOLD,40));

				FileInputStream greenButton = null;
				try {
					greenButton = new FileInputStream("next green button.png");
				} catch (FileNotFoundException e1) {
					
				}
				Image buttonImage = new Image(greenButton);
				ImageView buttonImageView = new ImageView(buttonImage);
				buttonImageView.setOnMouseClicked(e->{
					if((slider1.getValue()==3 && slider2.getValue()==2)){
						pane.getChildren().remove(secondQuestion);
						pane.add(correct(), 0, 0);
						totalScore = 100;
						checker = true;
					}
					else{
						pane.getChildren().remove(secondQuestion);
						pane.add(wrong("3/2"), 0, 0);
						checker = true;
					}
			
				}); 

				//input all elements to the panes
				grid2.add(quesSlider, 0, 0);
				grid2.add(blank, 1, 0);
				//grid2.add(new Label(slider1.getValue()), 2, 0);
				//grid2.add(new Label(slider2.getValue()), 3, 0);
				grid2.add(slider1, 0, 4);
				grid2.add(slider2, 1, 4);
				grid2.add(buttonImageView, 1, 5);
				secondQuestion.getChildren().addAll(grid2);
				pane.add(secondQuestion, 0, 0);
		return pane;
	}

}
