import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class MCQ extends Question{
	private final int SIZE = 4;
	private ArrayList<String> choice = new ArrayList<>(SIZE);
	
	public MCQ (){
		super();
	}
	
	public MCQ(String question,String [] string, int correct){
		super(question, correct);
		for(int i=0;i<string.length;i++){
			this.choice.add(string[i]);
		}
	}
	
	public ArrayList<String> getChoice() {
		return choice;
	}

	public void setChoice(ArrayList<String> choice) {
		this.choice = choice;
	}

	GridPane display() {
		GridPane mainPane = new GridPane();
		mainPane.setAlignment(Pos.CENTER);
		mainPane.setVgap(10);
		GridPane timerPane = new GridPane();
		timerPane.setAlignment(Pos.CENTER);
		mainPane.add(timerPane, 0, 0);
		timeSeconds = STARTTIME;	
		timerLabel.setText("Time left: "+timeSeconds.toString());
		timeline = new Timeline();
		
		Label lb1 = new Label(super.getQuestion());
		lb1.setWrapText(true);
		lb1.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		lb1.setTextAlignment(TextAlignment.CENTER);
		mainPane.add(lb1, 0, 1);
		GridPane.setHalignment(lb1, HPos.CENTER);
		
		GridPane answerPane = new GridPane();
		answerPane.setHgap(10);
		answerPane.setVgap(10);
		answerPane.setAlignment(Pos.CENTER);
		ToggleGroup group = new ToggleGroup();
		ArrayList<RadioButton> rbs = new ArrayList<>(SIZE);
		for(int i=0;i<SIZE;i++){
			rbs.add(new RadioButton());
			rbs.get(i).setToggleGroup(group);
		}
		answerPane.add(rbs.get(0), 0, 0);
		answerPane.add(rbs.get(1), 1, 0);
		answerPane.add(rbs.get(2), 0, 1);
		answerPane.add(rbs.get(3), 1, 1);
		rbs.get(0).setText("A. "+choice.get(0));
		rbs.get(0).setUserData(0);
		rbs.get(1).setText("B. "+choice.get(1));
		rbs.get(1).setUserData(1);
		rbs.get(2).setText("C. "+choice.get(2));
		rbs.get(2).setUserData(2);
		rbs.get(3).setText("D. "+choice.get(3));
		rbs.get(3).setUserData(3);
		mainPane.add(answerPane, 0, 2);
		Button submit = new Button("Submit Answer");
		submit.setId("shinyOrange");
		GridPane.setHalignment(submit, HPos.CENTER);
		mainPane.add(submit, 0, 3);
		
		submit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				try {
				if (checkAnswer((int)group.getSelectedToggle().getUserData()) == true){
					totalScore = timeSeconds*10;
					mainPane.getChildren().remove(timerPane);
					mainPane.getChildren().remove(lb1);
					mainPane.getChildren().remove(answerPane);
					mainPane.getChildren().remove(submit);
					mainPane.add(correct(), 0, 1);
				}
				else if (checkAnswer((int)group.getSelectedToggle().getUserData()) == false){
					mainPane.getChildren().remove(timerPane);
					mainPane.getChildren().remove(lb1);
					mainPane.getChildren().remove(answerPane);
					mainPane.getChildren().remove(submit);
					mainPane.add(wrong(choice.get(correct)), 0, 1);
				}
				}catch(NullPointerException e){
					mainPane.getChildren().remove(timerPane);
					mainPane.getChildren().remove(lb1);
					mainPane.getChildren().remove(answerPane);
					mainPane.getChildren().remove(submit);
					mainPane.add(wrong(choice.get(correct)), 0, 1);
				}
			}
		});
		
		timerLabel.setText("Time left: "+timeSeconds.toString());
		timerLabel.setFont(Font.font("Arial", 18));
		timerPane.add(timerLabel, 0, 1);
		if (timeline!= null){
			timeline.stop();
		}
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				timeSeconds--;
				if (timeSeconds <= 0){
					timerLabel.setText("Time left: "+timeSeconds.toString());
					timeline.stop();
					mainPane.getChildren().remove(timerPane);
					mainPane.getChildren().remove(lb1);
					mainPane.getChildren().remove(answerPane);
					mainPane.getChildren().remove(submit);
					mainPane.add(timeUp(choice.get(correct)), 0, 1);
				}
				else if (timeSeconds <= 5){
					timerLabel.setStyle("-fx-text-fill: Red");
					timerLabel.setText("Time left: "+timeSeconds.toString());
				}
				else {
					timerLabel.setText("Time left: "+timeSeconds.toString());
				}
			}
		}));
		
		return mainPane;
	}
	
	public boolean checkAnswer(int answer)throws NullPointerException{
		boolean chk = false;
		if(correct == answer){
			chk = true;
		}
		return chk;
	}

}
