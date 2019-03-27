import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Quiz extends Application{
	int tempInt;
	int totalScore;
	String tempText;
	GridPane tempPane = new GridPane();
	private ArrayList<User> user = new ArrayList<>();
	//0-Username,1-Password,2-Fullname,3-Bdate,4-Gender,5-lvl1,6-lvl2,7-lvl3,8-lvl4
	//0-Username,1-Password,2-Fullname,3-ChildUsername
	private Integer timeSeconds;
	
	protected User curUser = new User();
	//Start() method is the first interface that the user will see after program is run
	public void start (Stage primaryStage){
		GridPane bgPane = new GridPane();
		bgPane.setMinWidth(1020);
		bgPane.setMinHeight(750);
		bgPane.setAlignment(Pos.CENTER);
		String [] filenames = {"Images/bg1.jpg","Images/bg2.jpg","Images/bg3.jpg","Images/bg4.jpg","Images/bg5.jpg"};
		setPaneBg(bgPane,filenames,5);
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(20, 20, 20, 20));
		mainPane.setVgap(5);
		mainPane.setHgap(5);
		StudentLogin(mainPane, primaryStage,"User.txt");
		
		GridPane ParentPane = new GridPane();
		ParentPane.setPadding(new Insets(20, 20, 20, 20));
		ParentPane.setVgap(5);
		ParentPane.setHgap(5);
		ParentLogin(ParentPane, primaryStage,"Parent.txt");
		
		GridPane AdminPane = new GridPane();
		AdminPane.setPadding(new Insets(20,20,20,20));
		AdminPane.setVgap(5);
		AdminPane.setHgap(5);
		AdminLogin(AdminPane, primaryStage);
		
		TabPane pane = new TabPane();
		pane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		pane.setStyle("-fx-background-color: #ffffe0");
		Tab student = new Tab();
		student.setText("Student Login");
		student.setContent(mainPane);
		student.setStyle("-fx-background-color: #add8e6");
		Tab parent = new Tab();
		parent.setText("Parent Login");
		parent.setContent(ParentPane);
		parent.setStyle("-fx-background-color: #00ff00");
		Tab admin = new Tab();
		admin.setText("Admin Login");
		admin.setContent(AdminPane);
		admin.setStyle("-fx-background-color: #ef3d47");
		pane.getTabs().addAll(student,parent,admin);
		pane.getSelectionModel().select(student);
		bgPane.add(pane, 0, 0);
		bgPane.setAlignment(Pos.CENTER);
		Button exit = new Button("Exit Program");
		exit.setStyle("-fx-background-color: #add8e6");
		exit.setFont(Font.font("Arial", FontWeight.BOLD, 18));
		exit.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				exit.setStyle("-fx-background-color: #ef3d47");
			}
		});
		exit.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				exit.setStyle("-fx-background-color: #add8e6");
			}
		});
		exit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				System.exit(1);
			}
		});
		bgPane.add(exit, 0, 1);
		GridPane.setHalignment(exit, HPos.CENTER);
		
		
		Scene scene = new Scene(bgPane,1020,750);
		scene.getStylesheets().add("TabPane.css");
		primaryStage.setTitle("Quiz");
		primaryStage.setScene(scene);
		primaryStage.show(); 
	}
	
	//AdminLogin() method is used to display the admin login pane.
	public void AdminLogin (GridPane mainPane, Stage primaryStage){
		GridPane loginPane = new GridPane();
		loginPane.setVgap(8);
		TextField txtUsername = new TextField();
		txtUsername.setPromptText("Enter Username");
		PasswordField txtPassword = new PasswordField();
		txtPassword.setPromptText("Enter Password");
		Label lblUsername = new Label("Username : ");
		Label lblPassword = new Label("Password  : ");
		lblUsername.setMinWidth(150);
		txtUsername.setMinWidth(200);
		lblUsername.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
		lblPassword.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
		loginPane.add(lblUsername, 0, 0);
		loginPane.add(txtUsername, 1, 0);
		loginPane.add(lblPassword, 0, 1);
		loginPane.add(txtPassword, 1, 1);
		Label error = new Label("");
		error.setStyle("-fx-text-fill: Red");
		loginPane.add(error, 0, 3);
		
		Button login = new Button("Login");
		login.setFont(Font.font("Arial", FontWeight.BOLD, 18));
		GridPane.setHalignment(login, HPos.RIGHT);
		login.setStyle("-fx-background-color: #add8e6");
		loginPane.add(login, 1, 3);
		
		login.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				login.setStyle("-fx-background-color: #ef3d47");
			}
		});
		
		login.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				login.setStyle("-fx-background-color: #add8e6");
			}
		});
		
		login.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				error.setText("");
				final String admin = "ADMIN";
				final String password = "admin";
				if (txtUsername.getText().equals(admin) && txtPassword.getText().equals(password)){
					setTimeSeconds(-1);
					adminPage(primaryStage);
				}
				else {
					error.setText("Invalid Login Details");
				}
			}
		});
		
		Label title = new Label("Admin Login");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		GridPane.setHalignment(title, HPos.CENTER);
		mainPane.add(title, 0, 0);
		mainPane.add(loginPane, 0, 1);
		mainPane.setAlignment(Pos.CENTER);
	}
	
	//StudentLogin() method is used to display the student login pane.
	public void StudentLogin(GridPane mainPane, Stage primaryStage, String filename){
		GridPane loginPane = new GridPane();
		loginPane.setVgap(8);
		TextField txtUsername = new TextField();
		txtUsername.setPromptText("Enter Username");
		PasswordField txtPassword = new PasswordField();
		txtPassword.setPromptText("Enter Password");
		Label lblUsername = new Label("Username : ");
		Label lblPassword = new Label("Password  : ");
		lblUsername.setMinWidth(150);
		txtUsername.setMinWidth(200);
		lblUsername.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
		lblPassword.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
		loginPane.add(lblUsername, 0, 0);
		loginPane.add(txtUsername, 1, 0);
		loginPane.add(lblPassword, 0, 1);
		loginPane.add(txtPassword, 1, 1);
		Label error = new Label("");
		error.setStyle("-fx-text-fill: Red");
		loginPane.add(error, 0, 3);
		Button register = new Button("Student Register");
		register.setFont(Font.font("Arial", FontWeight.BOLD, 18));
		register.setStyle("-fx-background-color: #add8e6");
		
		register.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				register.setStyle("-fx-background-color: #ef3d47");
			}
		});
		
		register.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				register.setStyle("-fx-background-color: #add8e6");
			}
		});
		
		register.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				setTimeSeconds(-1);
				RegisterPage(primaryStage);
			}
		});
		GridPane.setHalignment(register, HPos.RIGHT);
		loginPane.add(register, 1, 2);
		
		Button login = new Button("Login");
		login.setFont(Font.font("Arial", FontWeight.BOLD, 18));
		login.setStyle("-fx-background-color: #add8e6");
		login.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				login.setStyle("-fx-background-color: #ef3d47");
			}
		});
		
		login.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				login.setStyle("-fx-background-color: #add8e6");
			}
		});
		
		login.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				String buffer;
				int i=0;
				try {
					File file = new File(filename);
					if(file.exists()){
						Scanner input = new Scanner(file);
						user.clear();
						while (input.hasNext()){
							buffer = input.nextLine();
							user.add(new User());
							user.get(i).setInfo(buffer.split(","));
							user.get(i).setUsername(user.get(i).getInfo()[0]);
							i++;
						}
						input.close();
					}
				} catch (FileNotFoundException e) {
					
				}
				boolean checker = true;
				for(int y =0;y<user.size();y++){
					if(txtUsername.getText().equals(user.get(y).getUsername()) && txtPassword.getText().equals(user.get(y).getInfo()[1])){
						checker = false;
						curUser.setUsername(user.get(y).getUsername());
						curUser.setInfo(user.get(y).getInfo());
						y = user.size() + 1;
						setTimeSeconds(-1);
						LoginPage(primaryStage);
					}
				}
				if (checker == true){
					error.setText("Invalid Login Details");
					txtUsername.setText("");
					txtPassword.setText("");
				}
			}
		});
		GridPane.setHalignment(login, HPos.RIGHT);
		loginPane.add(login, 1, 3);
		
		Label title = new Label("Student Login");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		GridPane.setHalignment(title, HPos.CENTER);
		mainPane.add(title, 0, 0);
		mainPane.add(loginPane, 0, 1);
		mainPane.setAlignment(Pos.CENTER);
	}
	
	//ParentLogin() method is used to display the parent login pane
	public void ParentLogin(GridPane mainPane, Stage primaryStage, String filename){
		GridPane loginPane = new GridPane();
		loginPane.setVgap(8);
		TextField txtUsername = new TextField();
		txtUsername.setPromptText("Enter Username");
		PasswordField txtPassword = new PasswordField();
		txtPassword.setPromptText("Enter Password");
		Label lblUsername = new Label("Username : ");
		Label lblPassword = new Label("Password  : ");
		lblUsername.setMinWidth(150);
		txtUsername.setMinWidth(200);
		lblUsername.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
		lblPassword.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
		loginPane.add(lblUsername, 0, 0);
		loginPane.add(txtUsername, 1, 0);
		loginPane.add(lblPassword, 0, 1);
		loginPane.add(txtPassword, 1, 1);
		Label error = new Label("");
		error.setStyle("-fx-text-fill: Red");
		loginPane.add(error, 0, 3);
		Button parentReg = new Button("Parent Register");
		parentReg.setFont(Font.font("Arial", FontWeight.BOLD, 18));
		GridPane.setHalignment(parentReg, HPos.RIGHT);
		mainPane.add(parentReg, 1, 6);
		parentReg.setStyle("-fx-background-color: #add8e6");
		parentReg.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				parentReg.setStyle("-fx-background-color: #ef3d47");
			}
		});
		
		parentReg.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				parentReg.setStyle("-fx-background-color: #add8e6");
			}
		});
		parentReg.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				setTimeSeconds(-1);
				ParentReg(primaryStage);
			}
		});
		loginPane.add(parentReg, 1, 2);
		
		Button login = new Button("Login");
		login.setFont(Font.font("Arial", FontWeight.BOLD, 18));
		login.setStyle("-fx-background-color: #add8e6");
		login.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				login.setStyle("-fx-background-color: #ef3d47");
			}
		});
		
		login.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				login.setStyle("-fx-background-color: #add8e6");
			}
		});
		
		login.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				String buffer;
				int i = 0;
				try {
					File file = new File(filename);
					if(file.exists()){
						Scanner input = new Scanner(file);
						user.clear();
						while (input.hasNext()){
							buffer = input.nextLine();
							user.add(new User());
							user.get(i).setInfo(buffer.split(","));
							user.get(i).setUsername(user.get(i).getInfo()[0]);
							i++;
						}
						input.close();
					}
				} catch (FileNotFoundException e) {
					
				}
				boolean checker = true;
				for(int y =0;y<user.size();y++){
					if(txtUsername.getText().equals(user.get(y).getUsername()) && txtPassword.getText().equals(user.get(y).getInfo()[1])){
						checker = false;
						curUser.setUsername(user.get(y).getUsername());
						curUser.setInfo(user.get(y).getInfo());
						y = user.size() + 1;
						setTimeSeconds(-1);
						parentPage(primaryStage);
					}
				}
				if (checker == true){
					error.setText("Invalid Login Details");
					txtUsername.setText("");
					txtPassword.setText("");
				}
			}
		});
		GridPane.setHalignment(login, HPos.RIGHT);
		loginPane.add(login, 1, 3);
		
		Label title = new Label("Parent Login");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		GridPane.setHalignment(title, HPos.CENTER);
		mainPane.add(title, 0, 0);
		mainPane.add(loginPane, 0, 1);
		mainPane.setAlignment(Pos.CENTER);
	}
	
	//RegisterPage() method is used to display the student register page
	public void RegisterPage (Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(20, 20, 20, 20));
		mainPane.setVgap(5);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		mainPane.setBackground(new Background(getBgImg("Images/reg.jpg")));
		
		Label lb1 = new Label("Register Page");
		lb1.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		lb1.setMinWidth(300);
		mainPane.add(lb1, 0, 0);
		mainPane.add(new Label("New Username"), 0, 1);
		TextField txt1 = new TextField();
		txt1.setPromptText("Enter New Username");
		mainPane.add(txt1, 1, 1);
		mainPane.add(new Label("New Password"), 0, 2);
		PasswordField txt2 = new PasswordField();
		txt2.setPromptText("Enter New Password");
		mainPane.add(txt2, 1, 2);
		mainPane.add(new Label("Confirm Password"), 0, 3);
		PasswordField txt3 = new PasswordField();
		txt3.setPromptText("Reenter New Passwprd");
		mainPane.add(txt3, 1, 3);
		mainPane.add(new Label("Enter Full Name"), 0, 4);
		TextField txt4 = new TextField();
		txt4.setPromptText("Enter Full Name");
		mainPane.add(txt4, 1, 4);
		mainPane.add(new Label("Date of Birth"), 0, 5);
		final DatePicker datePicker = new DatePicker();
		datePicker.setPromptText("Select the Date ->");
		mainPane.add(datePicker, 1, 5);
		mainPane.add(new Label("Gender"), 0, 6);
		GridPane genderPane = new GridPane();
		ToggleGroup group = new ToggleGroup();
		RadioButton rb1 = new RadioButton("Boy");
		rb1.setUserData("Boy");
		rb1.setToggleGroup(group);
		RadioButton rb2 = new RadioButton("Girl");
		rb2.setUserData("Girl");
		rb2.setToggleGroup(group);
		GridPane.setHalignment(rb1, HPos.CENTER);
		GridPane.setHalignment(rb2, HPos.CENTER);
		genderPane.add(rb1, 0, 0);
		genderPane.add(rb2, 1, 0);
		mainPane.add(genderPane, 1, 6);
		Button register = new Button("Register");
		Label errorMsg = new Label();
		mainPane.add(errorMsg, 0, 7);
		errorMsg.setStyle("-fx-text-fill: Red");
		register.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		register.setStyle("-fx-background-color: #add8e6");
		register.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				register.setStyle("-fx-background-color: #ef3d47");
			}
		});
		
		register.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				register.setStyle("-fx-background-color: #add8e6");
			}
		});
		
		register.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				errorMsg.setText("");
				LocalDate date = LocalDate.now();
				String [] Bday = datePicker.getValue().toString().split("-");
				if (txt1.getText().equals("")){
					errorMsg.setText("Please Enter a Username");
				}
				else if (txt2.getText().equals("")){
					errorMsg.setText("Please Enter a Password");
				}
				else if (txt3.getText().equals("")){
					errorMsg.setText("Please Confirm your Password");
				}
				else if (txt4.getText().equals("")){
					errorMsg.setText("Please enter your Fullname");
				}
				else if (datePicker.getValue()== null){
					errorMsg.setText("Incorrect Birth Date");
				}
				else if (!rb1.isSelected() && !rb2.isSelected()){
					errorMsg.setText("Please State your gender");
				}
				else if (date.getYear()-Integer.parseInt(Bday[0]) < 3){
					errorMsg.setText("You must be above 3 Years Old to register");
				}
				else {
					if (txt2.getText().equals(txt3.getText())){
						String buffer;
						int i = 0;
						try {
							File file = new File("User.txt");
							if(file.exists()){
								Scanner input = new Scanner(file);
								user.clear();
								while (input.hasNext()){
									buffer = input.nextLine();
									user.add(new User());
									user.get(i).setInfo(buffer.split(","));
									user.get(i).setUsername(user.get(i).getInfo()[0]);
									i++;
								}
								input.close();
							}
						} catch (FileNotFoundException e) {
							
						}
						boolean checker = true;
						for(int y=0;y<user.size();y++){
							if(txt1.getText().equals(user.get(y).getUsername())){
								checker = false;
								y = user.size() + 1;
								errorMsg.setText("Username Already Exist!");
							}
						}
						if (checker == true ){
							String [] temp = {txt1.getText(),txt2.getText(),txt4.getText(),datePicker.getValue().toString(),(String)group.getSelectedToggle().getUserData(),"0","0","0","0"};
							final int SIZETEMP = 8;
							user.add(new User(txt1.getText(), temp));
							try {
								File file = new File ("User.txt");
								PrintWriter output = new PrintWriter(file);
								if (file.exists()){
									for(int y=0;y<user.size();y++){
										for(int j = 0;j<SIZETEMP;j++){
											output.print(user.get(y).getInfo()[j]+",");
										}
										output.println(user.get(y).getInfo()[SIZETEMP]);
									}
								}
								output.flush();
								output.close();
								RegSuccessful(primaryStage);
							} catch (FileNotFoundException e) {
								
							}
						}
					}
				else {
					errorMsg.setText("Passwords do not Match, Please Try again.");
				}
				}
			}
		});
		GridPane.setHalignment(register, HPos.RIGHT);
		mainPane.add(register, 1, 7);
		Button exit = new Button ("Cancel");
		exit.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		exit.setStyle("-fx-background-color: #add8e6");
		exit.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				exit.setStyle("-fx-background-color: #ef3d47");
			}
		});
		exit.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				exit.setStyle("-fx-background-color: #add8e6");
			}
		});
		exit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				start(primaryStage);
			}
		});
		mainPane.add(exit, 1, 8);
		GridPane.setHalignment(exit, HPos.RIGHT);
		
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Quiz");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	//RegSuccessful() method is used to display a register successful Message
	public void RegSuccessful (Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(20, 20, 20, 20));
		mainPane.setVgap(5);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		
		mainPane.add(new Label("Account Successfully Registered!"), 0, 0);
		Button mainMenu = new Button("Go to Main Menu");
		mainMenu.setTextFill(Color.BLUE);
		mainMenu.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				start(primaryStage);
			}
		});
		mainPane.add(mainMenu, 0, 1);
		GridPane.setHalignment(mainMenu, HPos.CENTER);
		
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Quiz");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	//ParentReg() method is used to display the parent registration page
	public void ParentReg (Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(20, 20, 20, 20));
		mainPane.setVgap(5);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		mainPane.setBackground(new Background(getBgImg("Images/reg.jpg")));
		
		Label lb1 = new Label("Register Page");
		lb1.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		lb1.setMinWidth(300);
		mainPane.add(lb1, 0, 0);
		mainPane.add(new Label("Enter Child's Username: "), 0, 1);
		TextField txt1 = new TextField();
		txt1.setPromptText("Enter Your Children's username");
		mainPane.add(txt1, 1, 1);
		mainPane.add(new Label("Enter Parent Username"), 0, 2);
		TextField txt2 = new TextField();
		txt2.setPromptText("Enter New Username");
		mainPane.add(txt2, 1, 2);
		mainPane.add(new Label("Enter Password"),0,3);
		PasswordField txt3 = new PasswordField();
		txt3.setPromptText("Enter Password");
		mainPane.add(txt3, 1, 3);
		mainPane.add(new Label("Confirm Password"), 0, 4);
		PasswordField txt4 = new PasswordField();
		txt4.setPromptText("Confirm Your Password");
		mainPane.add(txt4, 1, 4);
		mainPane.add(new Label("Full Name"), 0, 5);
		TextField txt5 = new TextField();
		txt5.setPromptText("Enter your Fullname");
		mainPane.add(txt5, 1, 5);
		
		Label errorMsg = new Label("");
		mainPane.add(errorMsg, 0, 6);
		Button register = new Button("Register");
		errorMsg.setStyle("-fx-text-fill: Red");
		register.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		register.setStyle("-fx-background-color: #add8e6");
		register.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				register.setStyle("-fx-background-color: #ef3d47");
			}
		});
		
		register.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				register.setStyle("-fx-background-color: #add8e6");
			}
		});
		
		register.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				errorMsg.setText("");
				String buffer;
				int i=0;
				try {
					File file = new File("User.txt");
					if(file.exists()){
						Scanner input = new Scanner(file);
						user.clear();
						while (input.hasNext()){
							buffer = input.nextLine();
							user.add(new User());
							user.get(i).setInfo(buffer.split(","));
							user.get(i).setUsername(user.get(i).getInfo()[0]);
							i++;
						}
						input.close();
					}
				} catch (FileNotFoundException e) {
					
				}
				boolean userCheck = false;
				for(int y=0;y<user.size();y++){
					if (user.get(y).getUsername().equals(txt1.getText())){
						userCheck = true;
					}
				}
				if (userCheck == false){
					errorMsg.setText("Please Enter a Valid Child Username");
				}
				else if (txt2.getText().equals("")){
					errorMsg.setText("Please Enter a Username");
				}
				else if (txt3.getText().equals("")){
					errorMsg.setText("Please Enter a Password");
				}
				else if (txt4.getText().equals("")){
					errorMsg.setText("Please Confirm your Password");
				}
				else if (txt5.getText().equals("")){
					errorMsg.setText("Please enter your Fullname");
				}
				else {
					if (txt3.getText().equals(txt4.getText())){
						i = 0;
						try {
							File file = new File("Parent.txt");
							if(file.exists()){
								Scanner input = new Scanner(file);
								user.clear();
								while (input.hasNext()){
									buffer = input.nextLine();
									user.add(new User());
									user.get(i).setInfo(buffer.split(","));
									user.get(i).setUsername(user.get(i).getInfo()[0]);
									i++;
								}
								input.close();
							}
						} catch (FileNotFoundException e) {
							
						}
						boolean checker = true;
						for(int y=0;y<user.size();y++){
							if(txt2.getText().equals(user.get(y).getUsername())){
								checker = false;
								y = user.size() + 1;
								errorMsg.setText("Username Already Exist!");
							}
						}
						if (checker == true ){
							String [] temp = {txt2.getText(),txt3.getText(),txt5.getText(),txt1.getText()};
							final int SIZETEMP = 3;
							user.add(new User(txt1.getText(), temp));
							try {
								File file = new File ("Parent.txt");
								PrintWriter output = new PrintWriter(file);
								if (file.exists()){
									for(int y=0;y<user.size();y++){
										for(int j = 0;j<SIZETEMP;j++){
											output.print(user.get(y).getInfo()[j]+",");
										}
										output.println(user.get(y).getInfo()[SIZETEMP]);
									}
								}
								output.flush();
								output.close();
								RegSuccessful(primaryStage);
							} catch (FileNotFoundException e) {
								
							}
						}
					}
				else {
					errorMsg.setText("Passwords do not Match, Please Try again.");
				}
				}
			}
		});
		GridPane.setHalignment(register, HPos.RIGHT);
		mainPane.add(register, 1, 6);
		Button exit = new Button ("Cancel");
		exit.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		exit.setStyle("-fx-background-color: #add8e6");
		exit.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				exit.setStyle("-fx-background-color: #ef3d47");
			}
		});
		exit.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				exit.setStyle("-fx-background-color: #add8e6");
			}
		});
		exit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				start(primaryStage);
			}
		});
		mainPane.add(exit, 1, 7);
		GridPane.setHalignment(exit, HPos.RIGHT);
		
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Quiz");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	//LoginPage() method is used to display the main menu after student has login
	public void LoginPage (Stage primaryStage){
		GridPane bgPane = new GridPane();
		bgPane.setAlignment(Pos.CENTER);
		bgPane.setBackground(new Background(getBgImg("Images/userBG.jpg")));
		GridPane mainPane = new GridPane();
		bgPane.add(mainPane, 0, 0);
		mainPane.setPadding(new Insets(0, 5, 20, 5));
		mainPane.setVgap(30);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		mainPane.setStyle("-fx-background-color: #add8e6");
		
		Label lb1 = new Label();
		lb1.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		LocalDate date = LocalDate.now();
		String [] Bday = curUser.getInfo()[3].split("-");
		if (Integer.parseInt(Bday[1]) == date.getMonthValue() && Integer.parseInt(Bday[2]) == date.getDayOfMonth()){
			int age = date.getYear()-Integer.parseInt(Bday[0]);
			lb1.setText("Happy Birthday "+curUser.getUsername()+"! You are now "+Integer.toString(age)+" years old.");
		}
		else {
			lb1.setText("Welcome "+curUser.getUsername()+" to the Quiz Program");
		}
		lb1.setId("welcome-text");
		mainPane.add(lb1, 0, 1);
		Button lvl1 = new Button();
		lvl1.setGraphic(profilePic("Images/Dragon1.jpg"));
		Button lvl2 = new Button();
		lvl2.setGraphic(profilePic("Images/Dragon2.jpg"));
		Button lvl3 = new Button();
		lvl3.setGraphic(profilePic("Images/Dragon3.jpg"));
		Button lvl4 = new Button();
		lvl4.setGraphic(profilePic("Images/Dragon4.jpg"));
		GridPane.setHalignment(lb1, HPos.CENTER);
		GridPane choice = new GridPane();
		choice.setVgap(15);
		choice.setHgap(15);
		Label lvl1lb = new Label("Level 1");
		lvl1lb.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		lvl1lb.setTextFill(Color.WHITE);
		GridPane.setHalignment(lvl1lb, HPos.CENTER);
		Label lvl2lb = new Label("Level 2");
		lvl2lb.setTextFill(Color.WHITE);
		lvl2lb.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		GridPane.setHalignment(lvl2lb, HPos.CENTER);
		Label lvl3lb = new Label("Level 3");
		lvl3lb.setTextFill(Color.WHITE);
		lvl3lb.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		GridPane.setHalignment(lvl3lb, HPos.CENTER);
		Label lvl4lb = new Label("Level 4");
		lvl4lb.setTextFill(Color.WHITE);
		lvl4lb.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		GridPane.setHalignment(lvl4lb, HPos.CENTER);
		choice.add(lvl1, 0, 0);
		choice.add(lvl2, 1, 0);
		choice.add(lvl3, 0, 1);
		choice.add(lvl4, 1, 1);
		choice.add(lvl1lb, 0, 0);
		choice.add(lvl2lb, 1, 0);
		choice.add(lvl3lb, 0, 1);
		choice.add(lvl4lb, 1, 1);
		mainPane.add(choice, 0, 2);
		GridPane.setHalignment(lvl2, HPos.RIGHT);
		GridPane.setHalignment(lvl4, HPos.RIGHT);
		choice.setAlignment(Pos.CENTER);
		
		MenuBar menuBar = new MenuBar();
        Menu acc = new Menu("Account");
        MenuItem vP = new MenuItem("View Profile");
        MenuItem Logout = new MenuItem("Log Out");
        MenuItem Exit = new MenuItem("Exit Program");
        acc.getItems().addAll(vP,Logout,Exit);
        menuBar.getMenus().addAll(acc);
        mainPane.add(menuBar, 0, 0);
        
        Scene scene = new Scene(bgPane,1020,750);
        scene.getStylesheets().add("TabPane.css");
		primaryStage.setTitle("Quiz");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		lvl1.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
					Level1(primaryStage);
				}
		});
		
        lvl2.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
					Level2(primaryStage);
				}
		});
        
        lvl3.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
					Level3(primaryStage);
				}
		});
        
        lvl4.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
					Level4(primaryStage);
				}
		});
        
        vP.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				UserProfile(primaryStage);
			}
		});
        
        Logout.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				start(primaryStage);
			}
		});
        
        Exit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				System.exit(0);
			}
		});
	}
	
	//Level1() method is used to display all the level 1 questions
	public void Level1 (Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(20, 20, 20, 20));
		mainPane.setVgap(20);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		mainPane.setId("level1");
		
		ArrayList<Question> question = new ArrayList<>();
		String [] buffer;
		try {
			File file = new File("MCQ.txt");
			if(file.exists()){
				Scanner input = new Scanner(file);
				while (input.hasNextLine()){
					buffer = input.nextLine().split(";");
					String [] tempString = {buffer[1],buffer[2],buffer[3],buffer[4]};
					question.add(new MCQ(buffer[0],tempString,Integer.parseInt(buffer[5])));
				}
				input.close();
			}
		} catch (FileNotFoundException e) {
			
		}
		Collections.shuffle(question);
		tempInt = 0;
		totalScore = 0;
		tempPane = question.get(tempInt).display();
		question.get(tempInt).timeline.playFromStart();
		mainPane.add(tempPane, 0, 1);
		Button next = new Button("Next Question");
		next.setId("shinyOrange");
		next.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				if (tempInt < question.size()-1){
					totalScore = totalScore + question.get(tempInt).totalScore;
					tempInt++;
					mainPane.getChildren().remove(tempPane);
					tempPane = question.get(tempInt).display();
					question.get(tempInt).timeline.playFromStart();
					mainPane.add(tempPane, 0, 1);
				}
				else if (tempInt == question.size()-1){
					totalScore = totalScore + question.get(tempInt).totalScore;
					mainPane.getChildren().remove(tempPane);
					mainPane.setVgap(30);
					next.setText("Back to Main Menu");
					Label lb1 = new Label("All Question Answered!");
					Label lb2 = new Label("Your Score is: " + Integer.toString(totalScore));
					lb1.setFont(Font.font("Arial", FontWeight.BOLD, 14));
					lb2.setFont(Font.font("Arial", FontWeight.BOLD, 14));
					GridPane.setHalignment(lb1, HPos.CENTER);
					GridPane.setHalignment(lb2, HPos.CENTER);
					mainPane.add(lb1, 0, 0);
					mainPane.add(lb2, 0, 1);
					tempInt++;
				}
				else {
					if (totalScore > Integer.parseInt(curUser.getInfo()[5])){
						curUser.getInfo()[5] = Integer.toString(totalScore);
						curUser.setInfo(curUser.getInfo());
						UserUpdate();
					}
					LoginPage(primaryStage);
				}
			}
		});
		GridPane.setHalignment(next, HPos.CENTER);
		mainPane.add(next, 0, 2);
		
		Scene scene = new Scene(mainPane,1020,750);
		scene.getStylesheets().add("Question.css");
		primaryStage.setTitle("Quiz");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	//Level2() method is used to display all the level 2 questions
	public void Level2 (Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setVgap(20);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		mainPane.setId("level2");
		ArrayList<Question> question = new ArrayList<>();
		String[] buffer;
		try {
			File file = new File("CompareQuestion.txt");
			if(file.exists()){
				Scanner input = new Scanner(file);
				while (input.hasNext()){
					buffer = input.nextLine().split(",");
					question.add(new CompareQuestions(buffer[0],Integer.parseInt(buffer[1]),Integer.parseInt(buffer[2]),Integer.parseInt(buffer[3])));
				}
				input.close();
			}
		} catch (FileNotFoundException e) {
			
		}
		
		
		try {
			File file = new File("PatternQuestion.txt");
			if(file.exists()){
				Scanner input = new Scanner(file);
				while (input.hasNext()){
					buffer = input.nextLine().split(",");
					Integer[] tempNumber = {Integer.parseInt(buffer[1]),Integer.parseInt(buffer[2]),Integer.parseInt(buffer[3]),Integer.parseInt(buffer[4]),Integer.parseInt(buffer[5]),Integer.parseInt(buffer[6])};
					if (buffer[7].equals("true")){
						question.add(new Pattern(buffer[0],tempNumber,true));
					}
					else if (buffer[7].equals("false")){
						question.add(new Pattern(buffer[0],tempNumber,false));
					}
				}
				input.close();
			}
		} catch (FileNotFoundException e) {
			
		}
		Collections.shuffle(question);
		tempInt = 0;
		totalScore = 0;
		tempPane = question.get(tempInt).display();
		question.get(tempInt).timeline.playFromStart();
		mainPane.add(tempPane, 0, 1);
		Button next = new Button("Next Question");
		next.setId("ipad-grey");
		next.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				if (tempInt < question.size()-1){
					totalScore = totalScore + question.get(tempInt).totalScore;
					tempInt++;
					mainPane.getChildren().remove(tempPane);
					tempPane = question.get(tempInt).display();
					question.get(tempInt).timeline.playFromStart();
					mainPane.add(tempPane, 0, 1);
				}
				else if (tempInt == question.size()-1){
					totalScore = totalScore + question.get(tempInt).totalScore;
					mainPane.getChildren().remove(tempPane);
					mainPane.setVgap(30);
					next.setText("Back to Main Menu");
					Label lb1 = new Label("All Question Answered!");
					Label lb2 = new Label("Your Score is: " + Integer.toString(totalScore));
					lb1.setFont(Font.font("Arial", FontWeight.BOLD, 14));
					lb2.setFont(Font.font("Arial", FontWeight.BOLD, 14));
					GridPane.setHalignment(lb1, HPos.CENTER);
					GridPane.setHalignment(lb2, HPos.CENTER);
					mainPane.add(lb1, 0, 0);
					mainPane.add(lb2, 0, 1);
					tempInt++;
				}
				else {
					if (totalScore > Integer.parseInt(curUser.getInfo()[6])){
						curUser.getInfo()[6] = Integer.toString(totalScore);
						curUser.setInfo(curUser.getInfo());
						UserUpdate();
					}
					LoginPage(primaryStage);
				}
			}
		});
		GridPane.setHalignment(next, HPos.CENTER);
		mainPane.add(next, 0, 2);
		
		Scene scene = new Scene(mainPane,1020,750);
		scene.getStylesheets().add("Question.css");
		primaryStage.setTitle("Quiz");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	//Level3() method is used to display all the level 3 questions
	public void Level3 (Stage primaryStage){
		tempInt = Integer.parseInt(curUser.getInfo()[7]);
		curUser.getInfo()[7] = Integer.toString(tempInt+1);
		UserUpdate();
		try {
			File file = new File ("tempUser.txt");
			PrintWriter output = new PrintWriter(file);
			if (file.exists()){
			output.println(curUser.getUsername());
			}
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			
		}
		clickImage question = new clickImage();
		try {
			question.start(primaryStage);
		} catch (FileNotFoundException e) {
			
		}
	}
	
	//Level4() method is used to display all the level 4 questions
	public void Level4 (Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setVgap(20);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		mainPane.setStyle("-fx-background-color: green");
		
		FillInTheBlanks question1 = new FillInTheBlanks("Question 1: Solve the following problems.");
		tempPane = question1.display();
		tempPane.setAlignment(Pos.CENTER);
		mainPane.add(tempPane, 0, 0);
		Button continueBtn = new Button("Continue");
		continueBtn.setFont(Font.font("Calibri",40));
		continueBtn.setTextFill(Color.BLACK);
		GridPane.setHalignment(continueBtn, HPos.CENTER);
		continueBtn.setOnMouseClicked(e->{
			totalScore = totalScore+question1.totalScore;
			mainPane.getChildren().remove(tempPane);
			SliderQuestion question2 = new SliderQuestion();
			tempPane = question2.display();
			mainPane.add(tempPane, 0, 0);
			mainPane.getChildren().remove(continueBtn);
			Button rtn = new Button ("return to main menu");
			rtn.setFont(Font.font("Calibri",40));
			rtn.setTextFill(Color.BLACK);
			GridPane.setHalignment(rtn, HPos.CENTER);
			rtn.setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent event){
					totalScore = totalScore+question1.totalScore;
					if (totalScore > Integer.parseInt(curUser.getInfo()[8])){
						curUser.getInfo()[8] = Integer.toString(totalScore);
						curUser.setInfo(curUser.getInfo());
						UserUpdate();
					}
					LoginPage(primaryStage);
				}
			});
			mainPane.add(rtn, 0, 1);
		}); // end setOnMouseClicked event handler
		mainPane.add(continueBtn, 0, 1);
		
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Quiz");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	//parentPage() method is used to display main menu after parent has login
	public void parentPage (Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(20, 20, 20, 20));
		mainPane.setVgap(5);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		mainPane.setBackground(new Background(getBgImg("Images/parentBg.jpg")));
		
		Label lb1 = new Label("Welcome "+curUser.getUsername()+" !");
		lb1.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 50));
		lb1.setTextFill(Color.WHITE);
		GridPane.setHalignment(lb1, HPos.CENTER);
		mainPane.add(lb1, 0, 0);
		Button ViewStat = new Button();
		ViewStat.setGraphic(profilePic("Images/stats.jpg"));
		Label viewStat = new Label("View Child Stats");
		viewStat.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		GridPane.setHalignment(viewStat, HPos.CENTER);
		viewStat.setVisible(false);
		ViewStat.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				ViewChild(primaryStage);
			}
		});
		ViewStat.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				viewStat.setVisible(true);
			}
		});
		
		ViewStat.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				viewStat.setVisible(false);
			}
		});
		GridPane.setHalignment(ViewStat, HPos.CENTER);
		GridPane.setHalignment(viewStat, HPos.CENTER);
		mainPane.add(ViewStat, 0, 1);
		mainPane.add(viewStat, 0, 1);
		
		Button logout = new Button("Log Out");
		mainPane.add(logout, 0, 2);
		logout.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				start(primaryStage);
			}
		});
		logout.setFont(Font.font("Arial", FontWeight.BOLD, 18));
		logout.setStyle("-fx-background-color: #add8e6");
		logout.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				logout.setStyle("-fx-background-color: #ef3d47");
			}
		});
		logout.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				logout.setStyle("-fx-background-color: #add8e6");
			}
		});
		
		Button exit = new Button("Exit Program");
		mainPane.add(exit, 0, 3);
		exit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				System.exit(1);
			}
		});
		exit.setFont(Font.font("Arial", FontWeight.BOLD, 18));
		exit.setStyle("-fx-background-color: #add8e6");
		exit.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				exit.setStyle("-fx-background-color: #ef3d47");
			}
		});
		exit.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				exit.setStyle("-fx-background-color: #add8e6");
			}
		});
		GridPane.setHalignment(logout, HPos.CENTER);
		GridPane.setHalignment(exit, HPos.CENTER);
		
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Quiz");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	//modQuestion() method is used to display the menu to modify question.
	public void modQuestion(Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(20, 20, 20, 20));
		mainPane.setVgap(5);
		mainPane.setHgap(15);
		mainPane.setAlignment(Pos.CENTER);
		
		Label lb1 = new Label("Modify a question type");
		lb1.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 50));
		lb1.setTextFill(Color.BLACK);
		GridPane.setHalignment(lb1, HPos.CENTER);
		mainPane.add(lb1, 0, 0);
		
		Button patternQ = new Button("Modify Pattern Question");
		patternQ.setId("big-yellow");
		patternQ.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				modPattern(primaryStage);
			}
		});
		GridPane.setHalignment(patternQ, HPos.CENTER);
		mainPane.add(patternQ, 0, 1);
		
		Button mcqQ = new Button("Modify MCQ Question");
		mcqQ.setId("big-yellow");
		mcqQ.setVisible(false);
		mcqQ.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				
			}
		});
		GridPane.setHalignment(mcqQ, HPos.CENTER);
		mainPane.add(mcqQ, 0, 2);
		
		Button compareQ = new Button("Modify Compare Question");
		compareQ.setId("big-yellow");
		compareQ.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				modCompare(primaryStage);
			}
		});
		GridPane.setHalignment(compareQ, HPos.CENTER);
		mainPane.add(compareQ, 0, 3);
		
		Button rtrn = new Button("Return to Menu");
		GridPane.setHalignment(rtrn, HPos.CENTER);
		rtrn.setFont(Font.font("Times New Roman", 25));
		rtrn.setStyle("-fx-background-color: #add8e6");
		rtrn.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				rtrn.setStyle("-fx-background-color: #ef3d47");
			}
		});
		
		rtrn.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				rtrn.setStyle("-fx-background-color: #add8e6");
			}
		});
		rtrn.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				adminPage(primaryStage);
			}
		});
		mainPane.add(rtrn, 0, 4);
		
		Scene scene = new Scene(mainPane,1020,750);
		scene.getStylesheets().add("modify.css");
		primaryStage.setTitle("Quiz");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	//modPattern() method is to modify the pattern questions
	public void modPattern (Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(20, 20, 20, 20));
		mainPane.setVgap(5);
		mainPane.setHgap(15);
		mainPane.setAlignment(Pos.CENTER);
		
		Label lb1 = new Label("Pattern Questions");
		lb1.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 50));
		lb1.setTextFill(Color.BLACK);
		GridPane.setHalignment(lb1, HPos.CENTER);
		mainPane.add(lb1, 0, 0);
		
		Map <String, String[]> line = new HashMap<String,String[]>();
		line = readFile("PatternQuestion.txt");
		GridPane userPane = new GridPane();
		userPane.setAlignment(Pos.CENTER);
		userPane.setHgap(25);
		userPane.setVgap(20);
		Label lb2 = new Label("Question Title");
		lb2.setWrapText(true);
		lb2.setMaxWidth(300);
		lb2.setStyle("-fx-underline: true");
		lb2.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		GridPane.setHalignment(lb2, HPos.CENTER);
		userPane.add(lb2, 0, 0);
		Label lb3 = new Label("Values");
		lb3.setStyle("-fx-underline: true");
		lb3.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		GridPane.setHalignment(lb3, HPos.CENTER);
		userPane.add(lb3, 1, 0);
		Label lb4 = new Label("Is Ascending?");
		lb4.setStyle("-fx-underline: true");
		lb4.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		GridPane.setHalignment(lb4, HPos.CENTER);
		userPane.add(lb4, 2, 0);
		ArrayList<Label> input = new ArrayList<>();
		for(String key: line.keySet()){
			input.add(new Label(key));
			tempText = line.get(key)[1]+","+line.get(key)[2]+","+line.get(key)[3]+","+line.get(key)[4]+","+line.get(key)[5]+","+line.get(key)[6];
			input.add(new Label(tempText));
			input.add(new Label(line.get(key)[7]));
		}
		tempInt = 0;
		for(int i=0;i<line.size();i++){
			for(int j=0;j<3;j++){
				if (tempInt<input.size()){
					input.get(tempInt).setFont(Font.font("Arial", 20));
					userPane.add(input.get(tempInt), j, i+1);
					tempInt++;
				}
			}
		}
		mainPane.add(userPane, 0, 1);
		
		Label errorMsg = new Label();
		mainPane.add(errorMsg, 0, 2);
		GridPane.setHalignment(errorMsg, HPos.CENTER);
		Button add = new Button("Add new Pattern Question");
		mainPane.add(add, 0, 3);
		GridPane.setHalignment(add, HPos.CENTER);
		add.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				mainPane.getChildren().remove(add);
				GridPane addPane = new GridPane();
				addPane.setHgap(10);
				addPane.setAlignment(Pos.CENTER);
				mainPane.add(addPane, 0, 3);
				addPane.add(new Label("Enter Question"), 0, 0);
				TextField txt1 = new TextField();
				txt1.setPromptText("Enter question");
				addPane.add(txt1, 1, 0);
				addPane.add(new Label("Enter 6 values"), 0, 1);
				TextField txt2 = new TextField();
				txt2.setPromptText("?,?,?,?,?,?");
				addPane.add(txt2, 1, 1);
				Button create = new Button("Create Ascending");
				addPane.add(create, 0, 2);
				create.setOnAction(new EventHandler<ActionEvent>(){
					public void handle(ActionEvent event){
						String [] string = txt2.getText().split(",");
						Map <String, String[]> line = new HashMap<String,String[]>();
						line = readFile("PatternQuestion.txt");
						boolean chker = true;
						for(String key: line.keySet()){
							if(key.equals(txt1.getText())){
								errorMsg.setText("Invalid, Please Try Again");
								txt1.setText("");
								chker = false;
							}
						}
						if(string.length != 6){
							errorMsg.setText("Invalid, Please Try Again");
							txt2.setText("");
							chker = false;
						}
						if (chker == true){
							String[] newString = {txt1.getText(),string[0],string[1],string[2],string[3],string[4],string[5],"true"};
							line.put(newString[0], newString);
							txt1.setText("");
							txt2.setText("");
							chker = false;
							updateFile("PatternQuestion.txt",line);
							modPattern(primaryStage);
						}
					}
				});
				Button create2 = new Button("Create Descending");
				addPane.add(create2, 1, 2);
				create2.setOnAction(new EventHandler<ActionEvent>(){
					public void handle(ActionEvent event){
						String [] string = txt2.getText().split(",");
						Map <String, String[]> line = new HashMap<String,String[]>();
						line = readFile("PatternQuestion.txt");
						boolean chker = true;
						for(String key: line.keySet()){
							if(key.equals(txt1.getText())){
								errorMsg.setText("Invalid, Please Try Again");
								txt1.setText("");
								chker = false;
							}
						}
						if(string.length != 6){
							errorMsg.setText("Invalid, Please Try Again");
							txt2.setText("");
							chker = false;
						}
						if (chker == true){
							String[] newString = {txt1.getText(),string[0],string[1],string[2],string[3],string[4],string[5],"false"};
							line.put(newString[0], newString);
							txt1.setText("");
							txt2.setText("");
							chker = false;
							updateFile("PatternQuestion.txt",line);
							modPattern(primaryStage);
						}
					}
				});
			}
		});
		Button delete = new Button("Reset All Question");
		mainPane.add(delete, 0, 4);
		GridPane.setHalignment(delete, HPos.CENTER);
		delete.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				Map <String, String[]> line = new HashMap<String,String[]>();
				updateFile("PatternQuestion.txt",line);
				modPattern(primaryStage);
			}
		});
		
		Button rtrn = new Button("Return to Modify Question");
		GridPane.setHalignment(rtrn, HPos.CENTER);
		rtrn.setFont(Font.font("Times New Roman", 25));
		rtrn.setStyle("-fx-background-color: #add8e6");
		rtrn.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				rtrn.setStyle("-fx-background-color: #ef3d47");
			}
		});
		
		rtrn.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				rtrn.setStyle("-fx-background-color: #add8e6");
			}
		});
		rtrn.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				adminPage(primaryStage);
			}
		});
		mainPane.add(rtrn, 0, 5);
		
		Scene scene = new Scene(mainPane,1020,750);
		scene.getStylesheets().add("modify.css");
		primaryStage.setTitle("Quiz");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	//modCompare()method is to modify the compare questions
	public void modCompare (Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(20, 20, 20, 20));
		mainPane.setVgap(5);
		mainPane.setHgap(15);
		mainPane.setAlignment(Pos.CENTER);
		
		Label lb1 = new Label("Question Questions");
		lb1.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 50));
		lb1.setTextFill(Color.BLACK);
		GridPane.setHalignment(lb1, HPos.CENTER);
		mainPane.add(lb1, 0, 0);
		
		Map <String, String[]> line = new HashMap<String,String[]>();
		line = readFile("CompareQuestion.txt");
		GridPane userPane = new GridPane();
		userPane.setAlignment(Pos.CENTER);
		userPane.setHgap(25);
		userPane.setVgap(20);
		Label lb2 = new Label("Question Title");
		lb2.setWrapText(true);
		lb2.setMaxWidth(300);
		lb2.setStyle("-fx-underline: true");
		lb2.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		GridPane.setHalignment(lb2, HPos.CENTER);
		userPane.add(lb2, 0, 0);
		Label lb3 = new Label("Values in the Question");
		lb3.setStyle("-fx-underline: true");
		lb3.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		GridPane.setHalignment(lb3, HPos.CENTER);
		userPane.add(lb3, 1, 0);
		
		ArrayList<Label> input = new ArrayList<>();
		for(String key: line.keySet()){
			input.add(new Label(key));
			if (line.get(key)[3].equals("1")){
				tempText = line.get(key)[1]+">"+line.get(key)[2];
			}
			else if (line.get(key)[3].equals("2")){
				tempText = line.get(key)[1]+"<"+line.get(key)[2];
			}
			else if (line.get(key)[3].equals("3")){
				tempText = line.get(key)[1]+"="+line.get(key)[2];
			}
			input.add(new Label(tempText));
		}
		tempInt = 0;
		for(int i=0;i<line.size();i++){
			for(int j=0;j<2;j++){
				if (tempInt<input.size()){
					input.get(tempInt).setFont(Font.font("Arial", 20));
					userPane.add(input.get(tempInt), j, i+1);
					tempInt++;
				}
			}
		}
		mainPane.add(userPane, 0, 1);
		
		Label errorMsg = new Label();
		mainPane.add(errorMsg, 0, 2);
		GridPane.setHalignment(errorMsg, HPos.CENTER);
		Button add = new Button("Add new Compare Question");
		mainPane.add(add, 0, 3);
		GridPane.setHalignment(add, HPos.CENTER);
		add.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				mainPane.getChildren().remove(add);
				GridPane addPane = new GridPane();
				addPane.setHgap(10);
				addPane.setAlignment(Pos.CENTER);
				mainPane.add(addPane, 0, 3);
				addPane.add(new Label("Enter Question"), 0, 0);
				TextField txt1 = new TextField();
				txt1.setPromptText("Enter question");
				addPane.add(txt1, 1, 0);
				addPane.add(new Label("Enter values"), 0, 1);
				TextField txt2 = new TextField();
				txt2.setPromptText("?,Operator,?");
				addPane.add(txt2, 1, 1);
				Button create = new Button("Create");
				addPane.add(create, 0, 2);
				create.setOnAction(new EventHandler<ActionEvent>(){
					public void handle(ActionEvent event){
						String [] string = txt2.getText().split(",");
						Map <String, String[]> line = new HashMap<String,String[]>();
						line = readFile("CompareQuestion.txt");
						boolean chker = true;
						for(String key: line.keySet()){
							if(key.equals(txt1.getText())){
								errorMsg.setText("Invalid, Please Try Again");
								txt1.setText("");
								chker = false;
							}
						}
						if(string.length != 3){
							errorMsg.setText("Invalid, Please Try Again");
							txt2.setText("");
							chker = false;
						}
						if (chker == true){
							if (string[1].equals(">")){
								String[] newString = {txt1.getText(),string[0],string[2],"1"};
								line.put(newString[0], newString);
							}
							else if (string [1].equals("<")){
								String[] newString = {txt1.getText(),string[0],string[2],"2"};
								line.put(newString[0], newString);
							}
							else if (string[1].equals("=")){
								String[] newString = {txt1.getText(),string[0],string[2],"3"};
								line.put(newString[0], newString);
							}
							txt1.setText("");
							txt2.setText("");
							chker = false;
							updateFile("CompareQuestion.txt",line);
							modCompare(primaryStage);
						}
					}
				});
			}
		});
		Button delete = new Button("Reset All Question");
		mainPane.add(delete, 0, 4);
		GridPane.setHalignment(delete, HPos.CENTER);
		delete.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				Map <String, String[]> line = new HashMap<String,String[]>();
				updateFile("CompareQuestion.txt",line);
				modCompare(primaryStage);
			}
		});
		
		Button rtrn = new Button("Return to Modify Question");
		GridPane.setHalignment(rtrn, HPos.CENTER);
		rtrn.setFont(Font.font("Times New Roman", 25));
		rtrn.setStyle("-fx-background-color: #add8e6");
		rtrn.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				rtrn.setStyle("-fx-background-color: #ef3d47");
			}
		});
		
		rtrn.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				rtrn.setStyle("-fx-background-color: #add8e6");
			}
		});
		rtrn.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				adminPage(primaryStage);
			}
		});
		mainPane.add(rtrn, 0, 5);
		
		Scene scene = new Scene(mainPane,1020,750);
		scene.getStylesheets().add("modify.css");
		primaryStage.setTitle("Quiz");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	//ViewChild() method display the child's information
	public void ViewChild(Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(20, 20, 20, 20));
		mainPane.setVgap(5);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		
		User child = new User();
		String[] buffer;
		try {
			File file = new File("User.txt");
			if(file.exists()){
				Scanner input = new Scanner(file);
				user.clear();
				while (input.hasNext()){
					buffer = input.nextLine().split(",");
					if(buffer[0].equals(curUser.getInfo()[3])){
						child.setUsername(buffer[0]);
						child.setInfo(buffer);
					}
				}
				input.close();
			}
		} catch (FileNotFoundException e) {
			
		}
		
		Label lb1 = new Label();
		if (child.getInfo()[4].equals("Boy")){
			lb1.setText("Your Son, "+child.getUsername()+"'s Information");
		}
		else if (child.getInfo()[4].equals("Girl")){
			lb1.setText("Your Daughter, "+child.getUsername()+"'s Information");
		}
		else {
			lb1.setText("Your child's Information");
		}
		lb1.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
		GridPane.setHalignment(lb1, HPos.CENTER);
		mainPane.add(lb1, 0, 0);
		
		ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Level 1", Integer.parseInt(child.getInfo()[5])),
                new PieChart.Data("Level 2", Integer.parseInt(child.getInfo()[6])),
                new PieChart.Data("Level 3", Integer.parseInt(child.getInfo()[7])),
                new PieChart.Data("Level 4", Integer.parseInt(child.getInfo()[8])));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Scores And Attempts");
        
        GridPane.setHalignment(chart, HPos.CENTER);
        mainPane.add(chart, 0, 1);
        
        Button rtrn = new Button("Return to Menu");
		GridPane.setHalignment(rtrn, HPos.CENTER);
		rtrn.setFont(Font.font("Times New Roman", 25));
		rtrn.setStyle("-fx-background-color: #add8e6");
		rtrn.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				rtrn.setStyle("-fx-background-color: #ef3d47");
			}
		});
		
		rtrn.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				rtrn.setStyle("-fx-background-color: #add8e6");
			}
		});
		rtrn.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				parentPage(primaryStage);
			}
		});
		mainPane.add(rtrn, 0, 2);
		
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Quiz");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	//adminPage() method is used to display main menu after admin has login
	public void adminPage (Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(20, 20, 20, 20));
		mainPane.setVgap(15);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		mainPane.setBackground(new Background(getBgImg("Images/adminBG.jpg")));
		
		Label lb1 = new Label("Welcome ADMIN!!!");
		lb1.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 50));
		lb1.setTextFill(Color.WHITE);
		GridPane.setHalignment(lb1, HPos.CENTER);
		mainPane.add(lb1, 0, 0);
		
		GridPane settingPane = new GridPane();
		settingPane.setVgap(20);
		settingPane.setHgap(20);
		settingPane.setAlignment(Pos.CENTER);
		Button ViewUser = new Button();
		ViewUser.setGraphic(profilePic("Images/Settings.png"));
		Label viewUser = new Label("View Users");
		viewUser.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		GridPane.setHalignment(viewUser, HPos.CENTER);
		viewUser.setVisible(false);
		ViewUser.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				ViewUser(primaryStage);
			}
		});
		ViewUser.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				viewUser.setVisible(true);
			}
		});
		
		ViewUser.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				viewUser.setVisible(false);
			}
		});
		Button modify = new Button();
		modify.setGraphic(profilePic("Images/Settings.png"));
		Label Modify = new Label("Modify Question");
		Modify.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		GridPane.setHalignment(Modify, HPos.CENTER);
		Modify.setVisible(false);
		modify.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				Modify.setVisible(true);
			}
		});
		
		modify.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				Modify.setVisible(false);
			}
		});
		modify.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				modQuestion(primaryStage);
			}
		});
		
		Button ViewScores = new Button();
		ViewScores.setGraphic(profilePic("Images/Settings.png"));
		Label viewScores = new Label("View Scores");
		viewScores.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		GridPane.setHalignment(viewScores, HPos.CENTER);
		viewScores.setVisible(false);
		ViewScores.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				viewScores.setVisible(true);
			}
		});
		
		ViewScores.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				viewScores.setVisible(false);
			}
		});
		ViewScores.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				ViewScores(primaryStage);
			}
		});
		
		Button ViewStats = new Button();
		ViewStats.setGraphic(profilePic("Images/Settings.png"));
		Label viewStats = new Label("View Stats");
		viewStats.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		GridPane.setHalignment(viewStats, HPos.CENTER);
		viewStats.setVisible(false);
		ViewStats.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				viewStats.setVisible(true);
			}
		});
		
		ViewStats.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				viewStats.setVisible(false);
			}
		});
		ViewStats.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				ViewStats(primaryStage);
			}
		});
		settingPane.add(ViewUser, 0, 0);
		settingPane.add(viewUser, 0, 0);
		settingPane.add(modify, 1, 0);
		settingPane.add(Modify, 1, 0);
		settingPane.add(ViewScores, 0, 1);
		settingPane.add(viewScores, 0, 1);
		settingPane.add(ViewStats, 1, 1);
		settingPane.add(viewStats, 1, 1);
		mainPane.add(settingPane, 0, 1);
		Button logout = new Button("Log Out");
		mainPane.add(logout, 0, 2);
		logout.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				start(primaryStage);
			}
		});
		logout.setFont(Font.font("Arial", FontWeight.BOLD, 18));
		logout.setStyle("-fx-background-color: #add8e6");
		logout.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				logout.setStyle("-fx-background-color: #ef3d47");
			}
		});
		logout.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				logout.setStyle("-fx-background-color: #add8e6");
			}
		});
		
		Button exit = new Button("Exit Program");
		mainPane.add(exit, 0, 3);
		exit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				System.exit(1);
			}
		});
		exit.setFont(Font.font("Arial", FontWeight.BOLD, 18));
		exit.setStyle("-fx-background-color: #add8e6");
		exit.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				exit.setStyle("-fx-background-color: #ef3d47");
			}
		});
		exit.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				exit.setStyle("-fx-background-color: #add8e6");
			}
		});
		GridPane.setHalignment(logout, HPos.CENTER);
		GridPane.setHalignment(exit, HPos.CENTER);
		
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Quiz");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	//ViewUser() method is used to display all user for the admin
	public void ViewUser(Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(20, 20, 20, 20));
		mainPane.setVgap(20);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		
		Label lb1 = new Label("User Information");
		lb1.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 40));
		GridPane.setHalignment(lb1, HPos.CENTER);
		mainPane.add(lb1, 0, 0);
		Map <String, String[]> line = new HashMap<String,String[]>();
		line = readFile("User.txt");
		//0-Username,1-Password,2-Fullname,3-Bdate,4-Gender,5-lvl1,6-lvl2,7-lvl3,8-lvl4
		GridPane userPane = new GridPane();
		userPane.setAlignment(Pos.CENTER);
		userPane.setHgap(15);
		userPane.setVgap(15);
		Label lb2 = new Label("Username");
		lb2.setStyle("-fx-underline: true");
		lb2.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		GridPane.setHalignment(lb2, HPos.CENTER);
		userPane.add(lb2, 0, 0);
		Label lb3 = new Label("Password");
		lb3.setStyle("-fx-underline: true");
		lb3.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		GridPane.setHalignment(lb3, HPos.CENTER);
		userPane.add(lb3, 1, 0);
		Label lb4 = new Label("Full Name");
		lb4.setStyle("-fx-underline: true");
		lb4.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		GridPane.setHalignment(lb4, HPos.CENTER);
		userPane.add(lb4, 2, 0);
		Label lb5 = new Label("Date of Birth");
		lb5.setStyle("-fx-underline: true");
		lb5.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		GridPane.setHalignment(lb5, HPos.CENTER);
		userPane.add(lb5, 3, 0);
		Label lb6 = new Label("Gender");
		lb6.setStyle("-fx-underline: true");
		lb6.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		GridPane.setHalignment(lb6, HPos.CENTER);
		userPane.add(lb6, 4, 0);
		ArrayList<Label> input = new ArrayList<>();
		for(String key: line.keySet()){
			input.add(new Label(key));
			input.add(new Label(line.get(key)[1]));
			input.add(new Label(line.get(key)[2]));
			input.add(new Label(line.get(key)[3]));
			input.add(new Label(line.get(key)[4]));
		}
		tempInt = 0;
		for(int i=0;i<line.size();i++){
			for(int j=0;j<5;j++){
				if (tempInt<input.size()){
					input.get(tempInt).setFont(Font.font("Arial", 20));
					userPane.add(input.get(tempInt), j, i+1);
					tempInt++;
				}
			}
		}
		mainPane.add(userPane, 0, 1);
		
		Button rtrn = new Button("Return to Menu");
		GridPane.setHalignment(rtrn, HPos.CENTER);
		rtrn.setFont(Font.font("Times New Roman", 25));
		rtrn.setStyle("-fx-background-color: #add8e6");
		rtrn.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				rtrn.setStyle("-fx-background-color: #ef3d47");
			}
		});
		
		rtrn.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				rtrn.setStyle("-fx-background-color: #add8e6");
			}
		});
		rtrn.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				adminPage(primaryStage);
			}
		});
		mainPane.add(rtrn, 0, 2);
		
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Quiz");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	//reads File and returns HashMap.
	public Map<String, String[]> readFile (String filename){
		Map <String, String[]> line = new HashMap<String,String[]>();
		String[] buffer;
		try {
			File file = new File(filename);
			if(file.exists()){
				Scanner input = new Scanner(file);
				while (input.hasNext()){
					buffer = input.nextLine().split(",");
					line.put(buffer[0], buffer);
				}
				input.close();
			}
		} catch (FileNotFoundException e) {
			
		}
		return line;
	}
	
	//updates a file by taking in the filename and the map with the file's Information
	public void updateFile (String filename, Map<String,String[]> line){
		try {
			File file = new File (filename);
			PrintWriter output = new PrintWriter(file);
			if (file.exists()){
				for(String key: line.keySet()){
					for (int i=0;i<line.get(key).length;i++){
						if (i == line.get(key).length-1){
							output.println(line.get(key)[i]);
						}else {
							output.print(line.get(key)[i]+",");
						}
					}
				}
			}
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			
		}
	}
	
	//ViewScores() method is used to display all the scores of the user for the admin
	public void ViewScores(Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(20, 20, 20, 20));
		mainPane.setVgap(5);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		
		Label lb1 = new Label("Scores Of All User");
		lb1.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
		GridPane.setHalignment(lb1, HPos.CENTER);
		mainPane.add(lb1, 0, 0);
		Map <String, String[]> line = new HashMap<String,String[]>();
		line = readFile("User.txt");
		
		GridPane userPane = new GridPane();
		userPane.setAlignment(Pos.CENTER);
		userPane.setHgap(15);
		userPane.setVgap(15);
		Label lb2 = new Label("Username");
		lb2.setStyle("-fx-underline: true");
		lb2.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		GridPane.setHalignment(lb2, HPos.CENTER);
		userPane.add(lb2, 0, 0);
		Label lb3 = new Label("Level 1 Score");
		lb3.setStyle("-fx-underline: true");
		lb3.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		GridPane.setHalignment(lb3, HPos.CENTER);
		userPane.add(lb3, 1, 0);
		Label lb4 = new Label("Level 2 Score");
		lb4.setStyle("-fx-underline: true");
		lb4.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		GridPane.setHalignment(lb4, HPos.CENTER);
		userPane.add(lb4, 2, 0);
		Label lb5 = new Label("Level 3 Attempts");
		lb5.setStyle("-fx-underline: true");
		lb5.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		GridPane.setHalignment(lb5, HPos.CENTER);
		userPane.add(lb5, 3, 0);
		Label lb6 = new Label("Level 4 Score");
		lb6.setStyle("-fx-underline: true");
		lb6.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		GridPane.setHalignment(lb6, HPos.CENTER);
		userPane.add(lb6, 4, 0);
		ArrayList<Label> input = new ArrayList<>();
		for(String key: line.keySet()){
			input.add(new Label(key));
			input.add(new Label(line.get(key)[5]));
			input.add(new Label(line.get(key)[6]));
			input.add(new Label(line.get(key)[7]));
			input.add(new Label(line.get(key)[8]));
		}
		tempInt = 0;
		for(int i=0;i<line.size();i++){
			for(int j=0;j<5;j++){
				if (tempInt<input.size()){
					input.get(tempInt).setFont(Font.font("Arial", 20));
					userPane.add(input.get(tempInt), j, i+1);
					tempInt++;
				}
			}
		}
		mainPane.add(userPane, 0, 1);
		
		GridPane resetPane = new GridPane();
		resetPane.setAlignment(Pos.CENTER);
		resetPane.setHgap(10);
		resetPane.setVisible(false);
		Label username = new Label("Enter Username to Reset : ");
		username.setFont(Font.font("Times New Roman", 25));
		resetPane.add(username, 0, 0);
		TextField txtUsername = new TextField();
		txtUsername.setPromptText("Enter Username");
		resetPane.add(txtUsername, 1, 0);
		Button submit = new Button("Reset");
		GridPane.setHalignment(submit, HPos.CENTER);
		submit.setFont(Font.font("Times New Roman", 25));
		submit.setStyle("-fx-background-color: #add8e6");
		submit.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				submit.setStyle("-fx-background-color: #ef3d47");
			}
		});
		
		submit.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				submit.setStyle("-fx-background-color: #add8e6");
			}
		});
		submit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				Map <String, String[]> line = new HashMap<String,String[]>();
				line = readFile("User.txt");
				for(String key: line.keySet()){
					if(key.equals(txtUsername.getText())){
						line.get(key)[5]= "0";
						line.get(key)[6]= "0";
						line.get(key)[7]= "0";
						line.get(key)[8]= "0";
					}
				}
				updateFile("User.txt",line);
				ViewScores(primaryStage);
			}
		});
		resetPane.add(submit, 2, 0);
		mainPane.add(resetPane, 0, 2);
		
		Button reset = new Button("Reset Score for:");
		GridPane.setHalignment(reset, HPos.CENTER);
		reset.setFont(Font.font("Times New Roman", 25));
		reset.setStyle("-fx-background-color: #add8e6");
		reset.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				reset.setStyle("-fx-background-color: #ef3d47");
			}
		});
		
		reset.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				reset.setStyle("-fx-background-color: #add8e6");
			}
		});
		reset.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				mainPane.getChildren().remove(reset);
				resetPane.setVisible(true);
			}
		});
		mainPane.add(reset, 0, 2);
		
		Button rtrn = new Button("Return to Menu");
		GridPane.setHalignment(rtrn, HPos.CENTER);
		rtrn.setFont(Font.font("Times New Roman", 25));
		rtrn.setStyle("-fx-background-color: #add8e6");
		rtrn.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				rtrn.setStyle("-fx-background-color: #ef3d47");
			}
		});
		
		rtrn.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				rtrn.setStyle("-fx-background-color: #add8e6");
			}
		});
		rtrn.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				adminPage(primaryStage);
			}
		});
		mainPane.add(rtrn, 0, 3);
		
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Quiz");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	//ViewStats() method will display the BarChart graph for user scores for the admin
	public void ViewStats(Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(20, 20, 20, 20));
		mainPane.setVgap(5);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		
		Label lb1 = new Label("BarChart of User Scores");
		lb1.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
		GridPane.setHalignment(lb1, HPos.CENTER);
		mainPane.add(lb1, 0, 0);
		Map <String, String[]> line = new HashMap<String,String[]>();
		line = readFile("User.txt");
		GridPane bcPane = new GridPane();
		bcPane.setAlignment(Pos.CENTER);
		final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
		final BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);
		bc.setTitle("Scores");
		xAxis.setLabel("Username");       
		yAxis.setLabel("Scores");
		XYChart.Series<String,Number> series1 = new XYChart.Series<>();
		series1.setName("Level 1");
		for(String key: line.keySet()){
			series1.getData().add(new XYChart.Data<String,Number>(key, Integer.parseInt(line.get(key)[5])));
		}
		
		XYChart.Series<String,Number> series2 = new XYChart.Series<>();
		series2.setName("Level 2");
		for(String key: line.keySet()){
			series2.getData().add(new XYChart.Data<String,Number>(key, Integer.parseInt(line.get(key)[6])));
		}
		
		XYChart.Series<String,Number> series3 = new XYChart.Series<>();
		series3.setName("Level 3");
		for(String key: line.keySet()){
			series3.getData().add(new XYChart.Data<String,Number>(key, Integer.parseInt(line.get(key)[7])));
		}
		
		XYChart.Series<String,Number> series4 = new XYChart.Series<>();
		series4.setName("Level 4");
		for(String key: line.keySet()){
			series4.getData().add(new XYChart.Data<String,Number>(key, Integer.parseInt(line.get(key)[8])));
		}
		bc.getData().add(series1);
		bc.getData().add(series2);
		bc.getData().add(series3);
		bc.getData().add(series4);
		bcPane.add(bc, 0, 0);
		GridPane.setHalignment(bc, HPos.LEFT);
		mainPane.add(bcPane, 0, 1);
		
		Button rtrn = new Button("Return to Menu");
		GridPane.setHalignment(rtrn, HPos.CENTER);
		rtrn.setFont(Font.font("Times New Roman", 25));
		rtrn.setStyle("-fx-background-color: #add8e6");
		rtrn.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				rtrn.setStyle("-fx-background-color: #ef3d47");
			}
		});
		
		rtrn.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				rtrn.setStyle("-fx-background-color: #add8e6");
			}
		});
		rtrn.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				adminPage(primaryStage);
			}
		});
		mainPane.add(rtrn, 0, 2);
		
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Quiz");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	//UserUpdate() method will update the User.txt file.
	public void UserUpdate (){
		String buffer;
		int i = 0;
		try {
			File file = new File("User.txt");
			if(file.exists()){
				Scanner input = new Scanner(file);
				user.clear();
				while (input.hasNext()){
					buffer = input.nextLine();
					user.add(new User());
					user.get(i).setInfo(buffer.split(","));
					user.get(i).setUsername(user.get(i).getInfo()[0]);
					i++;
				}
				input.close();
			}
		} catch (FileNotFoundException e) {
			
		}
		final int SIZETEMP = 8;
		try {
			File file = new File ("User.txt");
			PrintWriter output = new PrintWriter(file);
			if (file.exists()){
				for(int y=0;y<user.size();y++){
					if (user.get(y).getUsername().equals(curUser.getUsername())){
						for(int j = 0;j<SIZETEMP;j++){
							output.print(curUser.getInfo()[j]+",");
						}
						output.println(curUser.getInfo()[SIZETEMP]);
					}
					else {
						for(int j = 0;j<SIZETEMP;j++){
							output.print(user.get(y).getInfo()[j]+",");
						}
						output.println(user.get(y).getInfo()[SIZETEMP]);
					}
				}
			}
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			
		}
	}
	
	//UserProfile() method is used to display the profile page of the user
	public void UserProfile(Stage primaryStage){
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(20, 20, 20, 20));
		mainPane.setVgap(5);
		mainPane.setHgap(5);
		mainPane.setAlignment(Pos.CENTER);
		
		Map <String, String> directory = new HashMap<String,String>();
		String[] temp;
		try {
			File file = new File("ProfilePicture.txt");
			if(file.exists()){
				Scanner input = new Scanner(file);
				user.clear();
				while (input.hasNext()){
					temp = input.nextLine().split(",");
					directory.put(temp[0], temp[1]);
				}
				input.close();
			}
		} catch (FileNotFoundException e) {
			
		}
		
		Label lb1 = new Label("Profile Information");
		lb1.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
		mainPane.add(lb1, 0, 0);
		GridPane.setHalignment(lb1, HPos.CENTER);
		
		Button profile = new Button();
		boolean chker = false;
		for(String key: directory.keySet()){
			if(key.equals(curUser.getUsername())){
				chker = true;
				profile.setGraphic(profilePic(directory.get(key)));
			}
		}
		if (chker == false){
			profile.setGraphic(profilePic("Images/NoPic.jpg"));
		}
		profile.setMinWidth(150);
		profile.setMinHeight(150);
		profile.setMaxWidth(150);
		profile.setMaxHeight(150);
		mainPane.add(profile, 0, 1);
		GridPane.setHalignment(profile, HPos.CENTER);
		Label changeProfile = new Label("Change Profile Picture");
		changeProfile.setFont(Font.font("Arial",FontWeight.BOLD, 14));
		GridPane.setHalignment(changeProfile, HPos.CENTER);
		mainPane.add(changeProfile,0,1);
		changeProfile.setVisible(false);
		
		profile.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				changeProfile.setVisible(true);
			}
		});
		
		profile.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				changeProfile.setVisible(false);
			}
		});
		final FileChooser choose = new FileChooser();
		FileChooser.ExtensionFilter imageFileOnly = new FileChooser.ExtensionFilter( "jpg","*.jpg", "*.png");
		choose.getExtensionFilters().add(imageFileOnly);
		profile.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				File selected = choose.showOpenDialog(primaryStage);
				if (selected != null){
					try {
						File file = new File ("ProfilePicture.txt");
						PrintWriter output = new PrintWriter(file);
						if (file.exists()){
							directory.put(curUser.getUsername(), selected.toString());
							for(String key: directory.keySet()){
								output.print(key +",");
								output.println(directory.get(key));
							}
						}
						output.flush();
						output.close();
					} catch (FileNotFoundException e) {
						
					}
					UserProfile(primaryStage);
				}
			}
		});
		
		GridPane infoPane = new GridPane();
		infoPane.setAlignment(Pos.CENTER);
		infoPane.setVgap(10);
		infoPane.setHgap(10);
		infoPane.add(new Label("Username     \t\t\t: "), 0, 0);
		infoPane.add(new Label(curUser.getUsername()), 1, 0);
		infoPane.add(new Label("Full Name     \t\t\t: "),0,1);
		infoPane.add(new Label(curUser.getInfo()[2]), 1, 1);
		infoPane.add(new Label("Birth Date     \t\t\t: "), 0, 2);
		infoPane.add(new Label(curUser.getInfo()[3]), 1, 2);
		infoPane.add(new Label("Gender         \t\t\t: "), 0, 3);
		infoPane.add(new Label(curUser.getInfo()[4]), 1, 3);
		infoPane.add(new Label("Level 1 Question High Score : "), 0, 4);
		infoPane.add(new Label(curUser.getInfo()[5]), 1, 4);
		infoPane.add(new Label("Level 2 Question High Score : "), 0, 5);
		infoPane.add(new Label(curUser.getInfo()[6]), 1, 5);
		infoPane.add(new Label("Level 3 Question   Attempts : "), 0, 6);
		infoPane.add(new Label(curUser.getInfo()[7]), 1, 6);
		infoPane.add(new Label("Level 4 Question High Score : "), 0, 7);
		infoPane.add(new Label(curUser.getInfo()[8]), 1, 7);
		mainPane.add(infoPane, 0, 2);
		
		Button rtn = new Button("Return to Main Menu");
		rtn.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		rtn.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				LoginPage(primaryStage);
			}
		});
		mainPane.add(rtn, 0, 3);
		GridPane.setHalignment(rtn, HPos.CENTER);
		
		if (curUser.getInfo()[4].equals("Boy")){
			mainPane.setStyle("-fx-background-color: #add8e6");
		}
		else if (curUser.getInfo()[4].equals("Girl")){
			mainPane.setStyle("-fx-background-color: #ffc0cb");
		}
		Scene scene = new Scene(mainPane,1020,750);
		primaryStage.setTitle("Quiz");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	//profilePic() method will return an ImageView object
	public ImageView profilePic (String filename){
		FileInputStream imageStream = null;
		Image image = null;
		ImageView view = null;
		try {
			imageStream = new FileInputStream(filename);
			image = new Image(imageStream);
			view = new ImageView(image);
			view.setFitHeight(150);
			view.setFitWidth(150);
		} catch (FileNotFoundException e) {
			view = null;
		}catch (NullPointerException t){
			view = null;
		}
		return view;
	}
	
	//setPaneBg() method will take in file directory and set the pane's background image
	public void setPaneBg (GridPane mainPane, String[] directory, int duration){
		Timeline timeline;
		setTimeSeconds(directory.length*duration);
		
		BackgroundImage[] bgImg = new BackgroundImage[directory.length];
		for(int i=0;i<directory.length;i++){
			bgImg[i] = getBgImg(directory[i]);
		}
		
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				for(int i=0;i<directory.length;i++){
					if (timeSeconds == directory.length*duration -(i*duration)){
						mainPane.setBackground(new Background(bgImg[i]));
					}
				}
				timeSeconds--;
				if (timeSeconds <= 0){
					setTimeSeconds(directory.length*duration);
					timeline.playFromStart();
				}
				if (timeSeconds <0){
					timeline.stop();
				}
			}
		}));
		timeline.playFromStart();
	}
	
	//getBgImg() method will return BackgroundImage objects to be set by the setPaneBg() method
	public BackgroundImage getBgImg (String filename){
		FileInputStream imageStrm = null;
		Image bg = null;
		BackgroundImage bgImg = null;
		try {
			imageStrm = new FileInputStream(filename);
			bg = new Image(imageStrm);
			bgImg = new BackgroundImage(bg, 
				    BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
				    BackgroundPosition.CENTER, 
				    new BackgroundSize(1020, 750, true, true, true, true));
		} catch (FileNotFoundException e) {
			bgImg = null;
		}
		
		return bgImg;
	}
	
	//setTimeSeconds method will set the timeSeconds variable
	public void setTimeSeconds (int value){
		this.timeSeconds = value;
	}
	
	//main() method will launch the application
	public static void main (String [] args){
		launch(args);
	}
}
