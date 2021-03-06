package schach;

import figures.Bishop;
import figures.Knight;
import figures.Queen;
import figures.Rook;
import game.AI;
import game.Board;
import game.Figures;
import game.GuiCalcs;
import game.Zug;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Controller class of the GUI game
 * @author Martin Stuwe 676421
 * @author Zeyi Sun
 * @author Richard Tank
 * @author Fin Niklas Tiedemann
 * group 23
 * it3
 */
public class GuiController {

	
	/**
	 *  calculator of the game
	 */
	GuiCalcs rechner = new GuiCalcs();
	
	/**
	 * Ai of the game
	 */
	AI ki = new AI(1);
	
	/**
	 * check if it's an Ai game
	 */
	boolean aiGame =false;
	
	/**
	 * check if a game is to be loaded
	 */
	boolean saveGame = false;
	
	/**
	 * shown GUI in the programm
	 */
	GuiView gv = new GuiView(this);
	
	/**
	 * Ai depth 
	 */
	int depth;
	
	/**
	 * timer 
	 */
	private static Timer t = new Timer();
	
	/**
	 * list of undone Turns
	 */
	public List<Zug>undoneTurns = new ArrayList<Zug>();
	
	/**
	 * check if it's Ai's turn
	 */
	boolean aiTurn=false;
	
	
	/**
	 * List of Game Parameters 0=rotate , 1=showCheck, 2=showMove, 3=touchMove, 4=clicked, 5=aiGame, 6=saveGame
	 */
	public List<Boolean>gameParameters = new ArrayList<Boolean>();
	
	/**
	 * set start options of the options you have while playing the game
	 */
	public void setGameParameters() {
	    gameParameters.add(false);
	    gameParameters.add(true);
	    gameParameters.add(true);
	    gameParameters.add(false);
	    gameParameters.add(false);
	    gameParameters.add(false);
	    gameParameters.add(false);
	    gameParameters.add(false);
	   }
	  /**
     * set-method for rotate
     * @param isSelected current state of the rotate checkbox
     */
    public void setRotate(boolean isSelected) {
    	gameParameters.set(0, isSelected);
    }
    
    /**
     * set-method for showCheck
     * @param isSelected current state of the showCheck checkbox
     */
    public void setShowCheck(boolean isSelected) {
    	gameParameters.set(1, isSelected);
    }
    
    /**
     * set-method for showMove
     * @param isSelected current state of the show checkbox
     */
    public void setShowMove(boolean isSelected) {
    	gameParameters.set(2, isSelected);
    }
    
    /**
     * set-method for touchMove
     * @param isSelected current state of the rotate checkbox
     */
    public void setTouchMove(boolean isSelected) {
    	gameParameters.set(3, isSelected);
    }
    
    /**
     * set-method for clicked
     * @param clicked true if something was clicked
     */
    public void setClicked(boolean click) {
    	gameParameters.set(4, click);
    }
    

    
    /**
     * Starting stage of the game
     * @param primaryStage main stage of the game
     */
	public void start(Stage primaryStage) {
    	gv.start(primaryStage);
    	Button btn = (Button) primaryStage.getScene().lookup("#ngBtn");
    	btn.setOnAction(new EventHandler<ActionEvent>() {
  		  
            @Override
            public void handle(ActionEvent event) {
               gv.startOptions(primaryStage);
               setOptions(primaryStage);
            }
        });

	}
	
	/**
	 * option screen of the game
	 * @param primaryStage main stage of the game
	 */
	public void setOptions(Stage primaryStage) {
		RadioButton white = (RadioButton) primaryStage.getScene().lookup("#white");
		RadioButton black = (RadioButton) primaryStage.getScene().lookup("#black");
		RadioButton pla = (RadioButton) primaryStage.getScene().lookup("#pla");
		RadioButton ai = (RadioButton) primaryStage.getScene().lookup("#ai");
		Button btn = (Button) primaryStage.getScene().lookup("#start");
		Button btn2 = (Button) primaryStage.getScene().lookup("#load");
		CheckBox enableTime = (CheckBox) primaryStage.getScene().lookup("#enable");
		TextField whiteTime = (TextField) primaryStage.getScene().lookup("#whTi");
		TextField blackTime = (TextField) primaryStage.getScene().lookup("#blTi");
		
		 white.setOnAction(new EventHandler<ActionEvent>() {
          	 
	            @Override
	            public void handle(ActionEvent event) {
	               white.setSelected(true);
	               black.setSelected(false);
	            }
	        });
	        
	        black.setOnAction(new EventHandler<ActionEvent>() {
	         	 
	            @Override
	            public void handle(ActionEvent event) {
	               black.setSelected(true);
	               white.setSelected(false);
	            }
	        });
	        
	        pla.setOnAction(new EventHandler<ActionEvent>() {
	         	 
	            @Override
	            public void handle(ActionEvent event) {
	               pla.setSelected(true);
	               ai.setSelected(false);
	            }
	        });
	        
	        ai.setOnAction(new EventHandler<ActionEvent>() {
	         	 
	            @Override
	            public void handle(ActionEvent event) {
	               ai.setSelected(true);
	               pla.setSelected(false);
	            }
	        });
	        
	      
	        
	        btn2.setOnAction(new EventHandler<ActionEvent>() {
	        	 
	            @Override
	            public void handle(ActionEvent event) {
	            	saveGame = true;
	            	
	            	if(enableTime.isSelected()) {
	            		try {
			        		gv.clock.timeWhite = Integer.parseInt(whiteTime.getText());
			        		
			        		
			        	}
			        	catch(NumberFormatException e){
			        		gv.clock.timeWhite = 600;
			        	}
	            		
	            		try {
	            			gv.clock.timeBlack = Integer.parseInt(blackTime.getText());
	            		}
	            		catch(NumberFormatException e) {
	            			gv.clock.timeBlack = 600;
	            		}
	            		
	            		gv.clock.active = true;
	            	}
	            	
	           		if(ai.isSelected()&& white.isSelected()) {
	        		aiGame =true;
	        		ki.setColor(1);
	           		}
	           		
	           		else if(ai.isSelected()&& black.isSelected()) {
	           			aiGame=true;
	           			ki.setColor(0);
	           			}
	           		else {
	           			aiGame=false;
	           		}
	           		changeDepth(primaryStage);
	           		gv.startGame(primaryStage, saveGame);
	           		startPlay(primaryStage);
	            }
	        });
	        
	        
	        btn.setOnAction(new EventHandler<ActionEvent>() {
	        	 
	            @Override
	            public void handle(ActionEvent event) {
	            	
	            	if(enableTime.isSelected()) {
	            		try {
			        		gv.clock.timeWhite = Integer.parseInt(whiteTime.getText());
			        		
			        		
			        	}
			        	catch(NumberFormatException e){
			        		gv.clock.timeWhite = 600;
			        	}
	            		
	            		try {
	            			gv.clock.timeBlack = Integer.parseInt(blackTime.getText());
	            		}
	            		catch(NumberFormatException e) {
	            			gv.clock.timeBlack = 600;
	            		}
	            		gv.clock.active = true;
	            	}
	            	if(ai.isSelected()&& white.isSelected()) {
	            		aiGame =true;
	            		ki.setColor(1);
	            	}
	            	else if(ai.isSelected()&& black.isSelected()) {
	            		aiGame=true;
	            		ki.setColor(0);
	            	}
	            	else {
	            		aiGame=false;
	            	}
	            	changeDepth(primaryStage);
	            	gv.startGame(primaryStage, saveGame);
	            	startPlay(primaryStage);
	            }
	        });
		
	}
	
	/**
	 * method to load the specific Bbard state
	 * @param k
	 */
	public void loadBoardState(int k) {
	
		Figures[][] figuren = gv.brett.movedList.get(k).getBoardState();
		gv.brett.setBoard(figuren,gv.brett.movedList.get(k).getTurn());
		int z = gv.brett.movedList.size()-1;
		for (int i = z;i>k; i--) {
			undoneTurns.add(gv.brett.movedList.get(i));
			gv.brett.movedList.remove(i);
			gv.historie.getItems().remove(i);
		}
		addUndoneTurnsToHistorie();
	}
	
	/**
	 * method to load the historie after moves have been undone
	 */
	public void addUndoneTurnsToHistorie() {

		gv.historie.getItems().clear();
		int k = gv.brett.movedList.size();
		for(int i=0;i<k;i++) {
			int n= i;
			Zug current = gv.brett.movedList.get(i);
			Label zug = rechner.convertInputToHistorie(current.getFrom1(), current.getFrom2(),""+current.getTo1()+current.getTo2());

			zug.setOnMouseClicked(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	            	if(!aiTurn) {
	            	loadBoardState(n);
	            	gv.drawBoard();
	            	System.out.println("Load Zug"+n);
	            	}
	            }

	        });
			
			gv.historie.getItems().add(zug);
		}
		int z = undoneTurns.size();

		for (int i = z;i>0; i--) {
			Label undone =rechner.convertInputToHistorie(undoneTurns.get(i-1).getFrom1(), undoneTurns.get(i-1).getFrom2(),""+undoneTurns.get(i-1).getTo1()+undoneTurns.get(i-1).getTo2());
			undone.setTextFill(Color.LIGHTGREY);
			int n=z-i+1;
			undone.setOnMouseClicked(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	            	if(!aiTurn) {
	             redoTurns(n);
	            	System.out.println("ReLoad Züge"+n);
	            	
	            	}
	            }

	        });
			gv.historie.getItems().add(undone);
		}
	}
	
	/**
	 * method to redo a turn
	 * @param k number of undone turn to return to
	 */
	public void redoTurns(int k) {
		for(int i=0;i<k;i++) {
			int z = undoneTurns.size();
			Figures[][] figuren = undoneTurns.get(z-1).getBoardState();
			gv.brett.setBoard(figuren, undoneTurns.get(z-1).getTurn());
			gv.brett.movedList.add(undoneTurns.get(z-1));
			undoneTurns.remove(z-1);
			
		}
		addUndoneTurnsToHistorie();
		gv.drawBoard();
		
	}
	
	/**
	 * game screen of the game
	 * @param primaryStage main stage of the game
	 */
	public void startPlay(Stage primaryStage) {
		gv.brett = new Board();
		gv.brett.setStart();
		primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
		    
		     if(!aiTurn) {
		    	 gv.setScreenHeight(Math.min(primaryStage.getWidth(),primaryStage.getHeight()));
		     gv.drawBoard();
		     }
		     
		});

		primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
		
			 if(!aiTurn) {
					gv.setScreenHeight(Math.min(primaryStage.getWidth(),primaryStage.getHeight()));
			     gv.drawBoard();
			     }
		});
		
        if(saveGame) {
        	rechner.loadGuiSave(gv.brett);
            saveGame = false;
        }
		
		gv.drawBoard();
		setTop(primaryStage);
		startAi();
		
	      
	      
	      
	      
	    Timeline clockUpdate = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
			
	    	/**
	    	 * method to update the time in GUI
	    	 */
	    	public void handle(ActionEvent event) {
	    		if(gv.clock.timeWhite > 0 && gv.clock.timeBlack > 0) {
	   	    	
	   	     
	    		Label whiteLa = (Label) primaryStage.getScene().lookup("#whiteLa");
	    		Label blackLa = (Label) primaryStage.getScene().lookup("#blackLa");
	    		gv.clock.updateTime(gv.brett);
	    		whiteLa.setText(" white time: " + gv.clock.timeWhite);
	    		blackLa.setText(" black time: " + gv.clock.timeBlack);
	    	   
	    	      	if(gv.clock.timeWhite <= 0 || gv.clock.timeBlack <= 0) {
	    	      		Stage window = new Stage();
			        	window.setTitle("Game End");
			            window.initModality(Modality.APPLICATION_MODAL);
			           
			            HBox box = new HBox();
			            if(gv.clock.timeWhite <= 0) {
			            	box.getChildren().add(new Label("white loss due time"));
			            }
			            else {
			            	box.getChildren().add(new Label("black loss due time"));
			            }
			            
			         
			            StackPane root = new StackPane();
			            root.getChildren().add(box);
			            window.setScene(new Scene(root, 200,200));
			            window.show();
	    	    	 
	    	      	}
	    		  
	    		 }
	    	}
	      }));
	    
	    if(gv.clock.timeWhite > 0 && gv.clock.timeBlack > 0) {
	    	clockUpdate.setCycleCount(Timeline.INDEFINITE);
		    clockUpdate.play();
	     }
	    
	     

	      
	}
	
	  /**
     * method for an ai move
     * @param brett board the game is on
     */
	public void aiMove(Board brett) {
		   
    	if(aiGame &&brett.getCurrentTurn()==ki.getColor()) {
    		aiTurn=true;

    		ki.lookAhead(gv.brett,depth,ki.getColor());
          
           
    		
    		Platform.runLater(new Runnable() {
    		      @Override
    		      public void run() {
    		         //Update your GUI here
    		    	  gv.drawBoard();
    		    		if (brett.movedList.size()!=0) {
    		    		int a = brett.movedList.get(brett.movedList.size()-1).getFrom1();
    		    		int b = brett.movedList.get(brett.movedList.size()-1).getFrom2();
    		    		String to  = Integer.toString(brett.movedList.get(brett.movedList.size()-1).getTo1())+Integer.toString(brett.movedList.get(brett.movedList.size()-1).getTo2());
    		    		gv.historie.getItems().add(rechner.convertInputToHistorie(a, b , to));}
    		    		aiTurn =false;
    		      }
    		  });
    		}
    	}
    
	/**
	 * method to place view Elements in the upper part of the game screen
	 * @param primaryStage main stage of the game
	 */
	public void setTop(Stage primaryStage) {
	    CheckBox check1 = (CheckBox) primaryStage.getScene().lookup("#check1");
        CheckBox check2 = (CheckBox) primaryStage.getScene().lookup("#check2");
        CheckBox check3 = (CheckBox) primaryStage.getScene().lookup("#check3");
        CheckBox check4 = (CheckBox) primaryStage.getScene().lookup("#check4");
        Button back = (Button) primaryStage.getScene().lookup("#back");
        Button undo = (Button) primaryStage.getScene().lookup("#undo");
        Button redo = (Button) primaryStage.getScene().lookup("#redo");
        Button testSave = (Button) primaryStage.getScene().lookup("#save");
		
        undo.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent event) {
               
                if(!aiTurn&& gv.brett.movedList.size()>0) {
                	int n = 1;
                	if(aiGame) {
                		n=2;
                	}
                	loadBoardState(gv.brett.movedList.size()-1-n);
                	gv.drawBoard();
                }
                
                
            }
        });
        redo.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent event) {
               

                if(!aiTurn&& undoneTurns.size()>0) {
                	int n = 1;
                	if(aiGame) {
                		n=2;
                	}
                	redoTurns(n);
                	gv.drawBoard();
                }
                
            }
        });
		check1.setOnAction(new EventHandler<ActionEvent>() {
	       	 
            @Override
            public void handle(ActionEvent event) {
                setRotate(check1.isSelected());
                if(!aiTurn) {
               gv.drawBoard();
                }
            }
        });
        check2.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
                setShowMove(check2.isSelected());
            }
        });
        
        check3.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
               setShowCheck(check3.isSelected());
               if(!aiTurn) {
                   gv.drawBoard();
                    }
            }
        });
        
        check4.setOnAction(new EventHandler<ActionEvent>() {
         	 
            @Override
            public void handle(ActionEvent event) {
               setTouchMove(check4.isSelected());
                
            }
        });
    	
        back.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
            	gv.clock.timeWhite = 0;
            	gv.clock.timeBlack = 0;
                gv.historie.getItems().clear();
                t.cancel();
            	start(primaryStage);
                
                
            }
        });
          
        testSave.setOnAction(new EventHandler<ActionEvent>() {
          	 
            @Override
            public void handle(ActionEvent event) {
         
				rechner.makeSaveGui(gv.historie);
		
            }
        });
	}
	
	  /**
     * method to show the symbol of the figure 
     * @param brett board the game is on
     * @param i x axis position
     * @param y y axis position
     * @return image visual of the figure
     */
	public Label getImage(Board brett, int i, int y) {
    	Label image = gv.drawImage(brett, i, y);
    	if(!gameParameters.get(0) && gameParameters.get(3) || !gameParameters.get(3)) {
    		
        	image.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                	if(!aiTurn) {
                	 setClicked(true);
                	 showPossibleMoves(brett,i,y);
                	}
                }

            	});
        	
        	}
    
    		return image;
        
}

	  /**
     * method to show possible moves of figures
     * @param brett board the game is on
     * @param i x axis position
     * @param y y axis position
     */	
	  public void showPossibleMoves(Board brett, int a, int b){
	    	GridPane possible = gv.drawBoard();
	    	for(int i =0; i<8;i++) {
				for(int y =0; y<8;y++) {
					String to = ""+i+y;
					int rrow =y;
					int rcol =i;
	            	if(gameParameters.get(0) && brett.getCurrentTurn()==1) {
	            		rrow = (rrow-7)*-1;
	            		rcol = (rcol-7)*-1;
	            	}
					if(brett.getField(a, b) != null && brett.getField(a, b).hasPossibleMove(brett, a, b,"" +i+y)) {
						Rectangle poss = new Rectangle(gv.screenHeight /10.1,gv.screenHeight /10.1);
						
						if(gameParameters.get(2)) {
							poss.setStroke(Color.RED);
							poss.setFill(Color.TRANSPARENT);
						}
						else {
							poss.setStroke(Color.TRANSPARENT);
							poss.setFill(Color.TRANSPARENT);
						}
						poss.setOnMouseClicked(new EventHandler<MouseEvent>() {
				            @Override
				            public void handle(MouseEvent event) {
				            	
				            	makeMove(brett,a,b,to );
				            	
				            }

				        });
						possible.add(poss, rcol,rrow);
						
					}
			}
				
			}
	    	gv.border.setCenter(possible);
	    }
	    
	  /**
		 * method to move figures on the board
	     * @param brett board the game is on
	     * @param i x axis starting position
	     * @param y y axis starting position
		 * @param to x and y axis ending position
		 */
	  public void makeMove(Board brett, int a, int b, String to ) {
			setClicked(false);
			int to1=Character.getNumericValue(to.charAt(0));
			int to2=Character.getNumericValue(to.charAt(1));

			if(undoneTurns.size()>0) {
				int k = undoneTurns.size();
				for(int i=k;i>0;i--) {
					gv.historie.getItems().remove(gv.historie.getItems().size()-1);
				}
				undoneTurns.clear();
			}
			brett.getField(a, b).move(brett, a, b, to);
			addUndoneTurnsToHistorie();
			if(brett.getField(to1, to2)!= null&&brett.getField(to1, to2).getType()==4 && (brett.getField(to1, to2).getColor()=="w"&& to2==0 ||brett.getField(to1, to2).getColor()=="b"&& to2==7  )) {
			gv.pawnPromo();
			Button queen = (Button) gv.window.getScene().lookup("#queen");
			queen.setOnAction(new EventHandler<ActionEvent>() {
				 
	            @Override
	            public void handle(ActionEvent event) {
	            	brett.setField(to1, to2, new Queen(to1,to2,brett.getField(to1, to2).getColor()));
	            	 gv.drawBoard();
	                gv.window.close();
	            }
	        });
			Button rook = (Button) gv.window.getScene().lookup("#rook");
	        rook.setOnAction(new EventHandler<ActionEvent>() {
	        	 
	            @Override
	            public void handle(ActionEvent event) {
	            	brett.setField(to1, to2, new Rook(to1,to2,brett.getField(to1, to2).getColor()));
	            	 gv.drawBoard();
	               gv.window.close();
	            }
	        });
	        Button knight = (Button) gv.window.getScene().lookup("#knight");
	       knight.setOnAction(new EventHandler<ActionEvent>() {
	        	 
	            @Override
	            public void handle(ActionEvent event) {
	            	brett.setField(to1, to2, new Knight(to1,to2,brett.getField(to1, to2).getColor()));
	            	 gv.drawBoard();
	                gv.window.close();
	            }
	        });
	       Button bishop = (Button) gv.window.getScene().lookup("#bishop");
	        bishop.setOnAction(new EventHandler<ActionEvent>() {
	        	 
	            @Override
	            public void handle(ActionEvent event) {
	            	brett.setField(to1, to2, new Bishop(to1,to2,brett.getField(to1, to2).getColor()));
	            	 gv.drawBoard();
	                gv.window.close();
	            }
	        });
			}
			gv.drawBoard();
			
		
	  }

	/**
	 * method to start the Ai
	 */
	public void startAi() {
		t = new Timer();
		long i = 500;
		t.scheduleAtFixedRate(new TimerTask() {		
			@Override
			public void run() {
				// TODO Auto-generated method stub
				aiMove(gv.brett);
				

			}
		}, i, i);
		
		
	}
	
	/**
	 * method to change the depth of the ai
	 * @param primaryStage main stage of the game
	 */
	public void changeDepth(Stage primaryStage) {
		RadioButton ai = (RadioButton) primaryStage.getScene().lookup("#ai");
		TextField aiDepth = (TextField) primaryStage.getScene().lookup("#depth");
		if(ai.isSelected()) {
			try {
				depth = Integer.parseInt(aiDepth.getText());
			}
			catch(NumberFormatException e) {
				depth = 1;
			}
		}
	}
}