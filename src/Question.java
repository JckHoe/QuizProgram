import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public abstract class Question {
	private String question;
	protected int correct;
	protected boolean chk;
	protected int totalScore;
	//for the timer
	protected static final Integer STARTTIME = 20;
	protected Timeline timeline;
	protected Label timerLabel = new Label();
	protected Integer timeSeconds = STARTTIME;
	
	public Question(){
		question = "";
		correct = -1;
		chk = false;
	}
	
	public Question(String question, int correct){
		this.question = question;
		this.correct = correct;
	}
	
	public boolean isChk() {
		return chk;
	}

	public void setChk(boolean chk) {
		this.chk = chk;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	public GridPane correct(){
		GridPane mainPane = new GridPane();
		mainPane.setAlignment(Pos.CENTER);
		Label lbl1 = new Label("That is Correct! Good Job");
		lbl1.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 30));
		Label lbl2 = new Label("Your Scored : "+ totalScore);
		lbl2.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 30));
		GridPane.setHalignment(lbl1, HPos.CENTER);
		GridPane.setHalignment(lbl2, HPos.CENTER);
		mainPane.add(lbl1, 0, 0);
		mainPane.add(lbl2, 0, 1);
		return mainPane;
	}
	
	public GridPane wrong(String correctAnswer){
		GridPane mainPane = new GridPane();
		mainPane.setAlignment(Pos.CENTER);
		Label lbl1 = new Label("That is Incorrect! The Correct Answer is:");
		lbl1.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 30));
		mainPane.add(lbl1, 0, 0);
		Label lbl2 = new Label(correctAnswer);
		lbl2.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
		mainPane.add(lbl2, 0, 1);
		Label lbl3 = new Label("Your Scored : "+ totalScore);
		lbl3.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 30));
		mainPane.add(lbl3, 0, 2);
		GridPane.setHalignment(lbl1, HPos.CENTER);
		GridPane.setHalignment(lbl2, HPos.CENTER);
		GridPane.setHalignment(lbl3, HPos.CENTER);
		return mainPane;
	}
	
	public GridPane timeUp(String correctAnswer){
		GridPane mainPane = new GridPane();
		mainPane.setVgap(5);
		mainPane.setAlignment(Pos.CENTER);
		Label lbl1 = new Label("Times Up! Answer Quickly Next time!");
		lbl1.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 30));
		Label lbl2 = new Label("The correct Answer is : "+correctAnswer);
		lbl2.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
		Label lbl3 = new Label("Your Scored : "+ totalScore);
		lbl3.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 30));
		GridPane.setHalignment(lbl1, HPos.CENTER);
		GridPane.setHalignment(lbl2, HPos.CENTER);
		GridPane.setHalignment(lbl3, HPos.CENTER);
		mainPane.add(lbl1, 0, 0);
		mainPane.add(lbl2, 0, 1);
		mainPane.add(lbl3, 0, 2);
		return mainPane;
	}
	
	abstract GridPane display();
}
