import javax.swing.*;

/**
  *Scores class which holds the method to create the scoreBoard to fill with labels (grid on the right hand side of GUI)
 **/
public class Scores{

    private JLabel[][] scoreArray = new JLabel[6][4]; //array to display the scores to the user

    /**
      *Method which created the 2D Array for the score, adds it to the panel, and sets the icon to empty.
      *@param scorePanel the panel to add the labels to 
      *@param empty_score the picture to set the icon of the labels to
     **/
    public void scoreBoard(JPanel scorePanel, Picture empty_score){


        for (int row = 0; row < 6; row++) {
			
				for (int col = 0; col < 4; col++) {
					JLabel label = new JLabel();
          label.setIcon(empty_score);
					scorePanel.add(label);
					scoreArray[row][col] = label;
				}
		}
    }

    /**
      *Allows scoreArray to be used in different classes
      *@return scoreArray
     **/  
    public JLabel[][] getScoreArray(){
		return scoreArray;
	}







}


