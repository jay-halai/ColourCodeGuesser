import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

/**
  * Colours class which represents the creation of the colour board for the buttons
  * calcuates the score, random colours array for the answer, users row guess and button clicks.
 **/
public class Colours implements ActionListener{
    
    private JFrame tempFrame = new JFrame();
    private JButton[] clrButtonArray = new JButton[7]; //array of colour buttons at the bottom of GUI
    int[] allColors = new int[7]; //array of integer values to represent the colours
    int[] answerColors = new int[4]; //array of computer color code
    private JLabel[][] guessArray;
    private JLabel[][] scoreArray;
    private Picture[] tempArray; 
    private int[] colorArray = new int[4]; //the row of colours to be checked

    private int rowCounter = 0;
    private int colCounter = 0;
    private int scoreRowCounter = 0;
    private int scoreColCounter = 0;
    private int clickCounter = 0;
    private int dt = 0;
    private int st = 0;

    CodeBreakerGUI codeBreaker = new CodeBreakerGUI(); //creates an instance of the CodeBreakerGUI class

    /**
     * Constructor which creates new instances of guessArray and scoreArray to be accessed in colours class
     *
     * @param array to set the guessArray to
     * @param array2 to set the scoreArray to
     **/
    public Colours(JLabel[][] array,JLabel[][] array2){
        guessArray = array;
        scoreArray = array2;
    }

    /**
     * Method to add integer values 0 to 6 in colors array to be randomised
     **/
    public void allColorsAssignment()
    {
        allColors[0] = 0;
        allColors[1] = 1;
        allColors[2] = 2;
        allColors[3] = 3;
        allColors[4] = 4;
        allColors[5] = 5;
        allColors[6] = 6;

    }

    /**
     * Function to return the index of allColors so a random one can be chosen
     *
     * @param i index of allColors
     * @return allColors[i]
     **/
    public int allColorsRandom(int i)
    {
        return allColors[i];
    }


    /**
     * Function to add random integer value from allColors to answerColors for computer color code
     *
     *@param allColors The array to store 0 to 6 the colors
     *@param answerColors The array to store the computer color code
     **/
    public void getRandomAnswerArray(int[] allColors, int[] answerColors) {
        for(int i=0; i<4; i++)
        {
            int rnd = new Random().nextInt(allColors.length);
            answerColors[i] = allColorsRandom(rnd);
        }
    }
    
    /**
     * Method to add buttons to clrButtonArray[], set their icon, add action listener, add to clrsPanel.
     *
     *@param clrsPanel The panel to add buttons to
     *@param clrsArray The array for the pictures
     *@param frame setting frame = tempFrame so I can use it later in another method
     **/
    public void colourBoard(JPanel clrsPanel, Picture[] clrsArray, JFrame frame){
        
        for(int i=0;i<4;i++)
        {
            System.out.println(answerColors[i]);
        }

        tempFrame = frame;
        for (int i = 0; i < 7; i++) {
            JButton button = new JButton();
            button.setIcon(clrsArray[i]);
            button.addActionListener(this);
            clrsPanel.add(button);
            clrButtonArray[i] = button;
		}
    }

    /**
     * Method to add a guess to the guessBoard depending on which colour the user chooses
     * Method also displays the end message when they have either won (dt = 4) or lost.
     * Method then calls the startGUI function again from CodeBreakerGUI to restart the game
     *@param pic The picture to add to the label
     *@param guessArray The 2D array which holds the guess labels to change the pictures for
     **/
    public void addGuess(Picture pic, JLabel[][] guessArray)
    {        
        guessArray[rowCounter][colCounter].setIcon(pic);

        if (colCounter < 3)
        {
            colCounter++;
        }
        else if (rowCounter<6)
        {
            colCounter = 0;
            calcScore();
            addScore(codeBreaker.getScore_0(),codeBreaker.getScore_1(),scoreArray);
            rowCounter++;
        }

    }

    /**
     * Method to add a score to the scoreBoard so the user can see which they have got right
     *
     *
     *@param score_0 The double tick picture to add to the label
     *@param score_1 The single tick picture to add to the label
     *@param scoreArray The array which hold the score labels to change the pictures for
     **/
    public void addScore(Picture score_0, Picture score_1, JLabel[][] scoreArray)
    {        

        if (scoreColCounter < 3)
        {
                if(dt != 0)
                {
                    for(int i =0; i<dt;i++)
                    {
                        scoreArray[scoreRowCounter][scoreColCounter].setIcon(score_0);
                        scoreColCounter++;
                    }
                }
                
                if(st != 0)
                {

                    for(int j =0; j<st;j++)
                    {
                        scoreArray[scoreRowCounter][scoreColCounter].setIcon(score_1);
                        scoreColCounter++;
                    }
                }

                int x = 4 - dt - st;
                scoreColCounter = scoreColCounter + x;

                if(scoreColCounter == 4)
                {
                    scoreColCounter = 0;
                    scoreRowCounter++;
                }

                if(dt == 4)
                {
                    JOptionPane.showMessageDialog(tempFrame,"You guessed the pattern correctly!");
                    tempFrame.dispose();
                    codeBreaker.startGUI();

                }

                if(clickCounter == 24 && dt != 4)
                {
                    JOptionPane.showMessageDialog(tempFrame,"Sorry, please try again");
                    tempFrame.dispose();
                    codeBreaker.startGUI();
                }


        }

    }

    /**
     * Method to calculate the score. Firstly, copies of both the computer color code and the row color array are made
     * this is because ,within the function, values are changed and need to be reset at the start each time.
     * 1st for loop is to check if 1st index of users guess is equal to color code then increment 4 times and change
     * values in both to integers out side of the 0 to 6 range
     * 2nd is nested loop to check each index of color code with each index of user guess to see if it is even in array at all
     * If true then increment dt and st counters.
     **/
    public void calcScore()
    {
        int[] copyAnswerColors = new int[4];
        copyAnswerColors = answerColors.clone();

        int[] copyColorArray = new int[4];
        copyColorArray = colorArray.clone();
        dt = 0;
        st = 0;
           
            for(int i=0;i<4;i++)
            {
                if(copyAnswerColors[i] == copyColorArray[i])
                {
                    dt++;
                    copyAnswerColors[i] = 11;
                    copyColorArray[i] = 12;
                } 
            }
        
            for(int j = 0;j<4; j++){
                for(int k=0;k<4;k++)
                {
                    if(copyColorArray[k] == copyAnswerColors[j])
                    {
                        st++;
                        copyAnswerColors[j] = 11;
                        copyColorArray[k] = 12;
                    }
                }
            }
    }

    /**
     * Method for the button clicks. In order to change the guesses we need to know when each button has been pressed.
     * Also setting up the colorArray by adding integer which corresponds to the colour each time a button is pressed.
     *
     *@param e When an action occurs it is saved to e.
     **/
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == clrButtonArray[0])
        {
            clickCounter++;
            colorArray[colCounter] = 0;
            addGuess(codeBreaker.getRed(),guessArray);
        }

        if(e.getSource() == clrButtonArray[1])
        {
            clickCounter++;
            colorArray[colCounter] = 1;
            addGuess(codeBreaker.getOrange(),guessArray);
        }

        if(e.getSource() == clrButtonArray[2])
        {
            clickCounter++;
            colorArray[colCounter] = 2;
            addGuess(codeBreaker.getYellow(),guessArray);
        }

        if(e.getSource() == clrButtonArray[3])
        {
            clickCounter++;
            colorArray[colCounter] = 3;
            addGuess(codeBreaker.getGreen(),guessArray);
        }

        if(e.getSource() == clrButtonArray[4])
        {
            clickCounter++;
            colorArray[colCounter] = 4;
            addGuess(codeBreaker.getBlue(),guessArray);
        }

        if(e.getSource() == clrButtonArray[5])
        {
            clickCounter++;
            colorArray[colCounter] = 5;
            addGuess(codeBreaker.getIndigo(),guessArray);
        }

        if(e.getSource() == clrButtonArray[6])
        {
            clickCounter++;
            colorArray[colCounter] = 6;
            addGuess(codeBreaker.getViolet(),guessArray);
        }

    }
}