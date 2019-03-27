import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DragDrop extends Application{
	
	private boolean check1=true,check2 = true,check3=true,check4=true;
	 
	public void start(Stage primaryStage) throws FileNotFoundException{
		

		StackPane stack= new StackPane();
		

		FileInputStream bg= new FileInputStream("bg3.jpg");
		Image background= new Image(bg);
		ImageView backgroundImg= new ImageView(background);
		stack.getChildren().add(backgroundImg);
		
		GridPane mainPane= new GridPane();
		mainPane.setMinSize(1020, 750);
		
		
		GridPane top=new GridPane();
		top.setHgap(5.5);
		top.setVgap(5.5);
		
		VBox vb= new VBox();
		
		FileInputStream img1= new FileInputStream("decagon.jpg");
		Image deca= new Image(img1);
		ImageView decaImg= new ImageView(deca);
		
		final Text dropHere1= new Text("\n\nDrop Here\n\n");
		dropHere1.setFont(Font.font("Calibri", FontWeight.NORMAL, 40));
		
		Text decagon= new Text("Decagon");
		decagon.setFont(Font.font("Calibri", FontWeight.NORMAL, 40));
		
		vb.getChildren().addAll(decaImg, dropHere1, decagon);
		

		//Second image
		
		VBox vb2= new VBox();
	
		FileInputStream img2= new FileInputStream("nonagon.jpg");
		Image nona= new Image(img2);
		 ImageView nonaImg= new ImageView(nona);

		final Text dropHere2= new Text("\n\nDrop Here\n\n");
		dropHere2.setFont(Font.font("Calibri", FontWeight.NORMAL, 40));
		
		
		Text nonagon= new Text("Nonagon");
		nonagon.setFont(Font.font("Calibri", FontWeight.NORMAL, 40));
		
		vb2.getChildren().addAll(nonaImg, dropHere2, nonagon);
		
		//third image
		VBox vb3= new VBox();
		
		FileInputStream img3= new FileInputStream("parallelogram.jpg");
		Image para= new Image(img3);
		 ImageView paraImg= new ImageView(para);

		final Text dropHere3= new Text("\n\nDrop Here\n\n");
		dropHere3.setFont(Font.font("Calibri", FontWeight.NORMAL, 40));
		
		
		Text parallelogram= new Text("Parallelogram");
		parallelogram.setFont(Font.font("Calibri", FontWeight.NORMAL, 40));
		
		vb3.getChildren().addAll(paraImg, dropHere3, parallelogram);
		
		
		//fourth image
		VBox vb4= new VBox();
		
		
		FileInputStream img4= new FileInputStream("trapezium.png");
		Image trap= new Image(img4);
		 ImageView trapImg= new ImageView(trap);

		final Text dropHere4= new Text("\n\nDrop Here\n\n");
		dropHere4.setFont(Font.font("Calibri", FontWeight.NORMAL, 40));
		
		
		Text trapezium= new Text("Trapezium");
		trapezium.setFont(Font.font("Calibri", FontWeight.NORMAL, 40));
		
		vb4.getChildren().addAll(trapImg, dropHere4, trapezium);
		
		//question

		Text ques= new Text("Drag and Drop the following text to the correct polygon\nDrag the correct text into the 'Drop Here'"
				+ " ");
		ques.setFont(Font.font("Calibri", FontWeight.NORMAL, 40));
		
		//adding the submit button
		FlowPane btm= new FlowPane();
		btm.setAlignment(Pos.BOTTOM_CENTER);
		
		Button submit= new Button("Submit");
		submit.setFont(Font.font("Calibri", FontWeight.BOLD, 40));
		submit.setPrefSize(200, 80);
		submit.setAlignment(Pos.BOTTOM_CENTER);		
		
		btm.getChildren().add(submit);
		
		top.add(vb, 0, 0);
		top.add(vb2, 1, 0);
		top.add(vb3, 2, 0);
		top.add(vb4, 3, 0);
		
		mainPane.add(ques, 0, 0);
		mainPane.add(top, 0, 1);
		mainPane.add(btm, 0, 2);
		
		stack.getChildren().add(mainPane);
	
	
	//////////////////////////////////////////////////////////////////////////////	
	      decagon.setOnDragDetected(new EventHandler <MouseEvent>() {
	            public void handle(MouseEvent event) {
	                
	                /* allow any transfer mode */
	                Dragboard db = decagon.startDragAndDrop(TransferMode.ANY);
	                
	                /* put a string on dragboard */
	                ClipboardContent content = new ClipboardContent();
	                content.putString(decagon.getText());
	                db.setContent(content);
	                
	                event.consume();
	            }
	        });

	        dropHere1.setOnDragOver(new EventHandler <DragEvent>() {
	            public void handle(DragEvent event) {
	                
	                /* accept it only if it is  not dragged from the same node 
	                 * and if it has a string data */
	                if (event.getGestureSource() != dropHere1 &&
	                        event.getDragboard().hasString()) {
	                    /* allow for both copying and moving, whatever user chooses */
	                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
	                }
	                
	                event.consume();
	            }
	        });

            
           
	        dropHere1.setOnDragDropped(new EventHandler <DragEvent>() {
	            public void handle(DragEvent event) {
	                /* if there is a string data on dragboard, read it and use it */
	                Dragboard db = event.getDragboard();
	                boolean success = false;

	                
	                if (db.hasString()) {
	                    dropHere1.setText("\n\n"+db.getString()+"\n\n");
	                    success = true;
	                    
	                    if(db.getString().equals("Decagon")){
	                    	check1=true;
	                    }    
	                    else{
	                    	check1=false;
	                    }
	                  
	                }
	                /* let the source know whether the string was successfully 
	                 * transferred and used */
	                event.setDropCompleted(success);
	                
	                event.consume();
	            }
	        });
	        
	        
///////////////////////////////////////////////////////////////////////////////	        
		      nonagon.setOnDragDetected(new EventHandler <MouseEvent>() {
		            public void handle(MouseEvent event) {
		               
		                /* allow any transfer mode */
		                Dragboard db = nonagon.startDragAndDrop(TransferMode.ANY);
		                
		                /* put a string on dragboard */
		                ClipboardContent content = new ClipboardContent();
		                content.putString(nonagon.getText());
		                db.setContent(content);
		                
		                event.consume();
		            }
		        });

		        dropHere2.setOnDragOver(new EventHandler <DragEvent>() {
		            public void handle(DragEvent event) {
		                
		                
		                /* accept it only if it is  not dragged from the same node 
		                 * and if it has a string data */
		                if (event.getGestureSource() != dropHere2 &&
		                        event.getDragboard().hasString()) {
		                    /* allow for both copying and moving, whatever user chooses */
		                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		                }
		                
		                event.consume();
		            }
		        });

		        
                
		        dropHere2.setOnDragDropped(new EventHandler <DragEvent>() {
		            public void handle(DragEvent event) {
		                
		                /* if there is a string data on dragboard, read it and use it */
		                Dragboard db = event.getDragboard();
		                boolean success = false;
		               
		                if (db.hasString()) {
		                	dropHere2.setText("\n\n"+db.getString()+"\n\n");
		                    success = true;
		                    
		                    if(db.getString().equals("Nonagon")){
		                    	check2=true;
		                    }
		                    else
		                    {
		                    	check2=false;
		                    }
		                }
		                /* let the source know whether the string was successfully 
		                 * transferred and used */
		                event.setDropCompleted(success);
		                
		                event.consume();
		            }
		        });
		        
///////////////////////////////////////////////////////////////////////////////////////////
		        parallelogram.setOnDragDetected(new EventHandler <MouseEvent>() {
		            public void handle(MouseEvent event) {
		               
		                /* allow any transfer mode */
		                Dragboard db = decagon.startDragAndDrop(TransferMode.ANY);
		                
		                /* put a string on dragboard */
		                ClipboardContent content = new ClipboardContent();
		                content.putString(parallelogram.getText());
		                db.setContent(content);
		                
		                event.consume();
		            }
		        });

		        dropHere3.setOnDragOver(new EventHandler <DragEvent>() {
		            public void handle(DragEvent event) {
		                
		                /* accept it only if it is  not dragged from the same node 
		                 * and if it has a string data */
		                if (event.getGestureSource() != dropHere3 &&
		                        event.getDragboard().hasString()) {
		                    /* allow for both copying and moving, whatever user chooses */
		                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		                }
		                
		                event.consume();
		            }
		        });

		        
                
		        dropHere3.setOnDragDropped(new EventHandler <DragEvent>() {
		            public void handle(DragEvent event) {
		               
		                /* if there is a string data on dragboard, read it and use it */
		                Dragboard db = event.getDragboard();
		                boolean success = false;
		       
		                if (db.hasString()) {
		                    dropHere3.setText("\n\n"+db.getString()+"\n\n");
		                    success = true;
		                    
		                    if(db.getString().equals("Parallelogram")){
		                    	check3=true;
		                    	
		                    }    
		                    else{
		                    	check3=false;
		                    }
		                }
		                /* let the source know whether the string was successfully 
		                 * transferred and used */
		                event.setDropCompleted(success);
		                
		                event.consume();
		            }
		        });
		        
///////////////////////////////////////////////////////////////////////////////////////////	 
		        trapezium.setOnDragDetected(new EventHandler <MouseEvent>() {
		            public void handle(MouseEvent event) {
		                
		                /* allow any transfer mode */
		                Dragboard db = decagon.startDragAndDrop(TransferMode.ANY);
		                
		                /* put a string on dragboard */
		                ClipboardContent content = new ClipboardContent();
		                content.putString(trapezium.getText());
		                db.setContent(content);
		                
		                event.consume();
		            }
		        });

		        dropHere4.setOnDragOver(new EventHandler <DragEvent>() {
		            public void handle(DragEvent event) {
		             
		                /* accept it only if it is  not dragged from the same node 
		                 * and if it has a string data */
		                if (event.getGestureSource() != dropHere4 &&
		                        event.getDragboard().hasString()) {
		                    /* allow for both copying and moving, whatever user chooses */
		                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		                }
		                
		                event.consume();
		            }
		        });

		        
	
		        dropHere4.setOnDragDropped(new EventHandler <DragEvent>() {
		            public void handle(DragEvent event) {
		               
		                /* if there is a string data on dragboard, read it and use it */
		                Dragboard db = event.getDragboard();
		                boolean success = false;
		           
		                if (db.hasString()) {
		                    dropHere4.setText("\n\n"+db.getString()+"\n\n");
		                    success = true;
		                    
		                    if(db.getString().equals("Trapezium")){
		                    	check4=true;
		                    	
		                    }    
		                    else{
		                    	check4=false;
		                    }
		                  
		                }
		                /* let the source know whether the string was successfully 
		                 * transferred and used */
		                event.setDropCompleted(success);
		                
		                event.consume();
		            }
		        });
		        
		        submit.setOnMouseClicked(e->{
		        	if(check1==true && check2==true && check3==true && check4==true){
		        		DisplayCorrectPane3 correct= new DisplayCorrectPane3();
						correct.start(primaryStage);
		        	}
		        	else {
		        		DisplayWrongPane3 wrong= new DisplayWrongPane3();
						
						wrong.start(primaryStage);
		        	}
		        });
				
				Scene scene= new Scene(stack);
				primaryStage.setScene(scene);
				primaryStage.show();		        

	}
	
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
