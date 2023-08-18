import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

/**
  * This class represents the GUI of the program and ulitmately is where all the functions 
  * and methods are called to be executed. It is essentially like the driver of the program.
**/
public class CodeBreakerGUI{

    private JFrame frame; //creating of frame to use later
    private JPanel guessPanel; //creating of guessPanel to use later
    private JPanel clrsPanel; //creating of clrsPanel to use later
    private JPanel scorePanel; //creating of scorePanel to use later

    private Picture[] clrsArray = new Picture[7]; //creating clrsArray to store the pictures in

    //initialising all the variables for the pictures to be called later (please make sure to use images I have provided)
    private Picture empty = new Picture("empty.png");
    private Picture red = new Picture("colour_0.png");
    private Picture orange = new Picture("colour_1.png");
    private Picture yellow = new Picture("colour_2.png");
    private Picture green = new Picture("colour_3.png");
    private Picture blue = new Picture("colour_4.png");
    private Picture indigo = new Picture("colour_5.png");
    private Picture violet = new Picture("colour_6.png");
    private Picture empty_score = new Picture("empty_score.png");
    private Picture score_0 = new Picture("score_0.png");
    private Picture score_1 = new Picture("score_1.png");

    /**
      *The getRed class allows you to get the get the variable red.
      *@return red The picture for the red button
     **/
    public Picture getRed(){
		return red;
	}

    /**
      *The getOrange class allows you to get the get the variable orange.
      *@return orange The picture for the orange.
     **/
    public Picture getOrange(){
		return orange;
	}
    
    /**
      *The getYellow class allows you to get the get the variable yellow.
      *@return yellow The picture for the yellow.
     **/
    public Picture getYellow(){
		return yellow;
	}

    /**
      *The getGreen class allows you to get the get the variable green.
      *@return green The picture for the green.
     **/
    public Picture getGreen(){
		return green;
	}

    /**
      *The getBlue class allows you to get the get the variable blue.
      *@return blue The picture for the blue.
     **/
    public Picture getBlue(){
		return blue;
	}

    /**
      *The getIndigo class allows you to get the get the variable indigo.
      *@return indigo The picture for the indigo.
     **/
    public Picture getIndigo(){
		return indigo;
	}

    /**
      *The getViolet class allows you to get the get the variable violet.
      *@return violet The picture for the violet.
     **/
    public Picture getViolet(){
		return violet;
	}

    /**
      *The getScore_0 class allows you to get the get the variable score_0.
      *@return score_0 The picture for the double tick.
     **/
    public Picture getScore_0(){
		return score_0;
	}

    /**
      *The getScore_1 class allows you to get the get the variable score_1.
      *@return score_1 The picture for the single tick.
     **/
    public Picture getScore_1(){
		return score_1;
	}

    /**
      *Storing all the colours in a clrsArray
     **/
    private void clrsArrayAssignment()
    {
        clrsArray[0] = red;
        clrsArray[1] = orange;
        clrsArray[2] = yellow;
        clrsArray[3] = green;
        clrsArray[4] = blue;
        clrsArray[5] = indigo;
        clrsArray[6] = violet;

    }
       
    /** 
      *This method is called in the main to start the GUI. Everything ultimately runs from here once called.
     **/
    public void startGUI()
    {
        Color myColour = Color.decode("#986504"); //sets the background colour I want to myColour

        Row row = new Row();
        Scores scores = new Scores();
        Colours colours = new Colours(row.getGuessArray(),scores.getScoreArray()); //an instance of the colours class (passing the constructors to colours)

        
        frame = new JFrame(); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(600,730);
        frame.setResizable(false);
        
        colours.allColorsAssignment();
        colours.getRandomAnswerArray(colours.allColors, colours.answerColors);

        //-------------------------------COMPONENTS FOR GUESS PANEL----------------------------------------

        guessPanel = new JPanel();
        guessPanel.setBackground(myColour);
        guessPanel.setBounds(0,0,350,600);
        guessPanel.setLayout(new GridLayout(6,4));
        frame.add(guessPanel);

        //-------------------------------COMPONENTS FOR COLOURS PANEL----------------------------------------

        clrsPanel = new JPanel();
        clrsPanel.setBackground(myColour);
        clrsPanel.setBounds(0,600,600,100);
        clrsPanel.setLayout(new GridLayout(1,7));
        frame.add(clrsPanel);

        row.guessBoard(guessPanel,empty);
        clrsArrayAssignment();
        colours.colourBoard(clrsPanel, clrsArray,frame);


        //--------------------------------------COMPONENTS FOR SCORE PANEL------------------------------------

        scorePanel = new JPanel();
        scorePanel.setBackground(myColour);
        scorePanel.setBounds(350,0,250,600);
        scorePanel.setLayout(new GridLayout(6,4));
        frame.add(scorePanel);
        scores.scoreBoard(scorePanel,empty_score);

        frame.setVisible(true);
    }

    /**
      * Instance of CodeBreakerGUI class is created.
      * startGUI() is called, this allows the contents of GUI to be created. 
      *
      *@param args stores java command line arguments and is array of type java
     **/
    public static void main(String[] args)
    {
		    CodeBreakerGUI cg = new CodeBreakerGUI();
        cg.startGUI();
    }

}
