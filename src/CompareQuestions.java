import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class CompareQuestions extends Question{
	private int num1;
	private int num2;

	
	public CompareQuestions (){
		num1 = -1;
		num2 = -1;
	}
	
	public CompareQuestions(String question, int num1, int num2, int correct){
		super(question,correct);
		this.num1 = num1;
		this.num2 = num2;
	}

	public int getNum1() {
		return num1;
	}

	public void setNum1(int num1) {
		this.num1 = num1;
	}

	public int getNum2() {
		return num2;
	}

	public void setNum2(int num2) {
		this.num2 = num2;
	}
	
	public GridPane display(){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(20, 20, 20, 20));
		mainPane.setVgap(5);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		
		GridPane timerPane = new GridPane();
		timerPane.setAlignment(Pos.CENTER);
		mainPane.add(timerPane, 0, 0);
		
		Label lb1 = new Label(super.getQuestion());
		lb1.setWrapText(true);
		lb1.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		lb1.setTextAlignment(TextAlignment.CENTER);
		GridPane.setHalignment(lb1, HPos.CENTER);
		mainPane.add(lb1, 0, 1);
		Label lb2 = new Label(Integer.toString(num1));
		lb2.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		Label lb3 = new Label(Integer.toString(num2));
		lb3.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(20, 20, 20, 20));
		pane.setVgap(5);
		pane.setHgap(5);
		pane.setAlignment(Pos.CENTER);
		pane.add(lb2, 0, 1);
		pane.add(lb3, 2, 1);
		Button btn1 = new Button (">");
		Button btn2 = new Button ("<");
		Button btn3 = new Button ("=");
		btn1.setId("iphone");
		btn2.setId("iphone");
		btn3.setId("iphone");
		pane.add(btn1, 1, 0);
		pane.add(btn2, 1, 1);
		pane.add(btn3, 1, 2);
		mainPane.add(pane, 0, 2);
		timeSeconds = STARTTIME;	
		timerLabel.setText("Time left: "+timeSeconds.toString());
		timeline = new Timeline();
		btn1.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				mainPane.getChildren().remove(timerPane);
				mainPane.getChildren().remove(lb1);
				mainPane.getChildren().remove(pane);
				chk = checkAnswer(1);
				if (chk == true){
					totalScore = (timeSeconds * 10);
					mainPane.add(correct(), 0, 1);
				}
				else if (chk == false && correct == 1){
					String temp = Integer.toString(num1) + ">" + Integer.toString(num2);
					mainPane.add(wrong(temp), 0, 1);
				}
				else if (chk == false && correct == 2){
					String temp = Integer.toString(num1) + "<" + Integer.toString(num2);
					mainPane.add(wrong(temp), 0, 1);
				}
				else if (chk == false && correct == 3){
					String temp = Integer.toString(num1) + "=" + Integer.toString(num2);
					mainPane.add(wrong(temp), 0, 1);
				}
			}
		});
		btn2.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				mainPane.getChildren().remove(timerPane);
				mainPane.getChildren().remove(lb1);
				mainPane.getChildren().remove(pane);
				chk = checkAnswer(2);
				if (chk == true){
					totalScore = (timeSeconds * 10);
					mainPane.add(correct(), 0, 1);
				}
				else if (chk == false && correct == 1){
					String temp = Integer.toString(num1) + ">" + Integer.toString(num2);
					mainPane.add(wrong(temp), 0, 1);
				}
				else if (chk == false && correct == 2){
					String temp = Integer.toString(num1) + "<" + Integer.toString(num2);
					mainPane.add(wrong(temp), 0, 1);
				}
				else if (chk == false && correct == 3){
					String temp = Integer.toString(num1) + "=" + Integer.toString(num2);
					mainPane.add(wrong(temp), 0, 1);
				}
			}
		});
		btn3.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				mainPane.getChildren().remove(timerPane);
				mainPane.getChildren().remove(lb1);
				mainPane.getChildren().remove(pane);
				chk = checkAnswer(3);
				if (chk == true){
					totalScore = (timeSeconds * 10);
					mainPane.add(correct(), 0, 1);
				}
				else if (chk == false && correct == 1){
					String temp = Integer.toString(num1) + ">" + Integer.toString(num2);
					mainPane.add(wrong(temp), 0, 1);
				}
				else if (chk == false && correct == 2){
					String temp = Integer.toString(num1) + "<" + Integer.toString(num2);
					mainPane.add(wrong(temp), 0, 1);
				}
				else if (chk == false && correct == 3){
					String temp = Integer.toString(num1) + "=" + Integer.toString(num2);
					mainPane.add(wrong(temp), 0, 1);
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
					mainPane.getChildren().remove(pane);
					String temp;
					if (correct == 1){
						temp = Integer.toString(num1) + ">" + Integer.toString(num2);
						mainPane.add(timeUp(temp), 0, 1);
					}else if (correct == 2){
						temp = Integer.toString(num1) + "<" + Integer.toString(num2);
						mainPane.add(timeUp(temp), 0, 1);
					}
					else if (correct == 3){
						temp = Integer.toString(num1) + "=" + Integer.toString(num2);
						mainPane.add(timeUp(temp), 0, 1);
					}
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
	
	public boolean checkAnswer(int answer){
		boolean chk = false;
		if(correct == answer){
			chk = true;
		}
		return chk;
	}
}
