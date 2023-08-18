import javax.swing.*;

/**
  *Row class which holds the method to create the guessBoard to fill with labels (grid on the left hand side of GUI)
 **/
public class Row{

    private JLabel[][] guessArray = new JLabel[6][4]; //array to display the empty guesses to the user

    /**
      *Method which created the 2D Array for the guesses, adds it to the panel, and sets the icon to empty.
      *@param guessPanel the panel to add the labels to 
      *@param empty the picture to set the icon of the labels to
     **/
    public void guessBoard(JPanel guessPanel, Picture empty){


        for (int row = 0; row < 6; row++) {
          
          for (int col = 0; col < 4; col++) {
            JLabel label = new JLabel();
            label.setIcon(empty);
            guessPanel.add(label);
            guessArray[row][col] = label;
				}
        }
    }

    /**
      *Allows guessArray to be used in different classes
	  *@return guessArray
     **/  
	public JLabel[][] getGuessArray(){
		return guessArray;
	}

}