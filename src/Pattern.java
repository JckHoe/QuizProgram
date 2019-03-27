import java.util.ArrayList;
import java.util.Collections;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class Pattern extends Question{
	private final int SIZE = 6;
	private ArrayList<Integer> numbers = new ArrayList<>(SIZE);
	private boolean order; //true = ascending , false = descending
	private int clicks;
	
	public Pattern(String question,Integer [] tempNumbers,boolean order) {
		super(question, -1);
		for(int i = 0;i<tempNumbers.length;i++){
			this.numbers.add(tempNumbers[i]);
		}
		this.order = order;
		clicks = 0;
	}

	Pattern(){
		super();
		numbers = null;
		order = true;
		clicks = 0;
	}

	public ArrayList<Integer> getNumbers() {
		return numbers;
	}

	public void setNumbers(ArrayList<Integer> numbers) {
		this.numbers = numbers;
	}

	public boolean isOrder() {
		return order;
	}

	public void setOrder(boolean order) {
		this.order = order;
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
		ArrayList<Integer> tempAnswer = new ArrayList<>(6);
		ArrayList<Button> btns = new ArrayList<>();
		GridPane btnPane = new GridPane();
		btnPane.setVgap(20);
		btnPane.setHgap(20);
		btnPane.setMinWidth(200);
		btnPane.setMinHeight(100);
		for(int i=0;i<SIZE;i++){
			btns.add(new Button());
		}
		btnPane.add(btns.get(0), 0, 0);
		btnPane.add(btns.get(1), 1, 0);
		btnPane.add(btns.get(2), 2, 0);
		btnPane.add(btns.get(3), 0, 1);
		btnPane.add(btns.get(4), 1, 1);
		btnPane.add(btns.get(5), 2, 1);
		Collections.shuffle(btns);
		for (int i=0;i<SIZE;i++){
			btns.get(i).setText(Integer.toString(numbers.get(i)));
			btns.get(i).setMinSize(50, 50);
			btns.get(i).setId("iphone");
		}
		btnPane.setAlignment(Pos.CENTER);
		btns.get(0).setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				clicks++;
				tempAnswer.add(Integer.parseInt(btns.get(0).getText()));
				if (checkAnswer(tempAnswer) == true){
					btnPane.getChildren().remove(btns.get(0));
				}
				else if (checkAnswer(tempAnswer)==false){
					mainPane.getChildren().remove(btnPane);
					if (order == true){
						Collections.sort(numbers);
					}
					else if (order == false){
						Collections.sort(numbers);
						Collections.reverse(numbers);
					}
					mainPane.getChildren().remove(lb1);
					mainPane.getChildren().remove(timerPane);
					mainPane.add(wrong(numbers.toString()), 0, 0);
				}
				if (clicks == 6){
					mainPane.getChildren().remove(lb1);
					mainPane.getChildren().remove(timerPane);
					mainPane.getChildren().remove(btnPane);
					totalScore = (timeSeconds * 10);
					mainPane.add(correct(), 0, 0);
				}
			}
		});
		btns.get(1).setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				clicks++;
				tempAnswer.add(Integer.parseInt(btns.get(1).getText()));
				if (checkAnswer(tempAnswer) == true){
					btnPane.getChildren().remove(btns.get(1));
				}
				else if (checkAnswer(tempAnswer)==false){
					mainPane.getChildren().remove(btnPane);
					if (order == true){
						Collections.sort(numbers);
					}
					else if (order == false){
						Collections.sort(numbers);
						Collections.reverse(numbers);
					}
					mainPane.getChildren().remove(lb1);
					mainPane.getChildren().remove(timerPane);
					mainPane.add(wrong(numbers.toString()), 0, 0);
				}
				if (clicks == 6){
					mainPane.getChildren().remove(lb1);
					mainPane.getChildren().remove(timerPane);
					mainPane.getChildren().remove(btnPane);
					totalScore = (timeSeconds * 10);
					mainPane.add(correct(), 0, 0);
				}
			}
		});
		btns.get(2).setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				clicks++;
				tempAnswer.add(Integer.parseInt(btns.get(2).getText()));
				if (checkAnswer(tempAnswer) == true){
					btnPane.getChildren().remove(btns.get(2));
				}
				else if (checkAnswer(tempAnswer)==false){
					mainPane.getChildren().remove(btnPane);
					if (order == true){
						Collections.sort(numbers);
					}
					else if (order == false){
						Collections.sort(numbers);
						Collections.reverse(numbers);
					}
					mainPane.getChildren().remove(lb1);
					mainPane.getChildren().remove(timerPane);
					mainPane.add(wrong(numbers.toString()), 0, 0);
				}
				if (clicks == 6){
					mainPane.getChildren().remove(lb1);
					mainPane.getChildren().remove(timerPane);
					mainPane.getChildren().remove(btnPane);
					totalScore = (timeSeconds * 10);
					mainPane.add(correct(), 0, 0);
				}
			}
		});
		btns.get(3).setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				clicks++;
				tempAnswer.add(Integer.parseInt(btns.get(3).getText()));
				if (checkAnswer(tempAnswer) == true){
					btnPane.getChildren().remove(btns.get(3));
				}
				else if (checkAnswer(tempAnswer)==false){
					mainPane.getChildren().remove(btnPane);
					if (order == true){
						Collections.sort(numbers);
					}
					else if (order == false){
						Collections.sort(numbers);
						Collections.reverse(numbers);
					}
					mainPane.getChildren().remove(lb1);
					mainPane.getChildren().remove(timerPane);
					mainPane.add(wrong(numbers.toString()), 0, 0);
				}
				if (clicks == 6){
					mainPane.getChildren().remove(lb1);
					mainPane.getChildren().remove(timerPane);
					mainPane.getChildren().remove(btnPane);
					totalScore = (timeSeconds * 10);
					mainPane.add(correct(), 0, 0);
				}
			}
		});
		btns.get(4).setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				clicks++;
				tempAnswer.add(Integer.parseInt(btns.get(4).getText()));
				if (checkAnswer(tempAnswer) == true){
					btnPane.getChildren().remove(btns.get(4));
				}
				else if (checkAnswer(tempAnswer)==false){
					mainPane.getChildren().remove(btnPane);
					if (order == true){
						Collections.sort(numbers);
					}
					else if (order == false){
						Collections.sort(numbers);
						Collections.reverse(numbers);
					}
					mainPane.getChildren().remove(lb1);
					mainPane.getChildren().remove(timerPane);
					mainPane.add(wrong(numbers.toString()), 0, 0);
				}
				if (clicks == 6){
					mainPane.getChildren().remove(lb1);
					mainPane.getChildren().remove(timerPane);
					mainPane.getChildren().remove(btnPane);
					totalScore = (timeSeconds * 10);
					mainPane.add(correct(), 0, 0);
				}
			}
		});
		btns.get(5).setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				clicks++;
				tempAnswer.add(Integer.parseInt(btns.get(5).getText()));
				if (checkAnswer(tempAnswer) == true){
					btnPane.getChildren().remove(btns.get(5));
				}
				else if (checkAnswer(tempAnswer)==false){
					mainPane.getChildren().remove(btnPane);
					if (order == true){
						Collections.sort(numbers);
					}
					else if (order == false){
						Collections.sort(numbers);
						Collections.reverse(numbers);
					}
					mainPane.getChildren().remove(lb1);
					mainPane.getChildren().remove(timerPane);
					mainPane.add(wrong(numbers.toString()), 0, 0);
				}
				if (clicks == 6){
					mainPane.getChildren().remove(lb1);
					mainPane.getChildren().remove(timerPane);
					mainPane.getChildren().remove(btnPane);
					totalScore = (timeSeconds * 10);
					mainPane.add(correct(), 0, 0);
				}
			}
		});
		mainPane.add(btnPane, 0, 2);
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
					mainPane.getChildren().remove(btnPane);
					mainPane.add(timeUp(numbers.toString()), 0, 1);
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

	boolean checkAnswer(ArrayList <Integer> answer) {
		boolean chk = true;
		if (order == true){
			Collections.sort(numbers);
			for(int i=0;i<answer.size();i++){
				if(answer.get(i) == numbers.get(i)){
					chk = true;
				}
				else {
					chk = false;
				}
			}
		}
		else if (order == false){
			Collections.sort(numbers);
			Collections.reverse(numbers);
			for(int i=0;i<answer.size();i++){
				if(answer.get(i) == numbers.get(i)){
					chk = true;
				}
				else {
					chk = false;
				}
			}
		}
		return chk;
	}
}
