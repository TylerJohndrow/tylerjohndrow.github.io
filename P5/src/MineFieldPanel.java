import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Creates a mine field 
 * @author tylerjohndrow
 *
 */
@SuppressWarnings("serial")
public class MineFieldPanel extends JPanel {
	@SuppressWarnings("unused")
	private int gridsize;
	private JPanel scorePanel, keyPanel, controlPanel;
	private MineFieldButton[][] grid;
	private JLabel[] keylabels = new JLabel[8];
	private JLabel[] scoreLabels = new JLabel[2];
	private JButton showMines, showPath, giveUp;
	private ArrayList<Point> points;
	private Random rand = new Random();
	private int lives = 5;
	private int score = 500;
	
	
	//keyLabels
	{
		keylabels[0]= new JLabel("0 Nearby Mines");
		keylabels[0].setBackground(Color.GREEN);
		keylabels[0].setOpaque(true);
		keylabels[0].setPreferredSize(new Dimension(120, 75));
		
		keylabels[1]= new JLabel("1 Nearby Mines"); 
		keylabels[1].setBackground(Color.YELLOW);
		keylabels[1].setOpaque(true);
		keylabels[1].setPreferredSize(new Dimension(120, 75));
		
		keylabels[2]= new JLabel("2 Nearby Mines"); 
		keylabels[2].setBackground(Color.ORANGE);
		keylabels[2].setOpaque(true);
		keylabels[2].setPreferredSize(new Dimension(120, 75));
		
		keylabels[3]= new JLabel("3 Nearby Mines"); 
		keylabels[3].setBackground(Color.RED);
		keylabels[3].setOpaque(true);
		keylabels[3].setPreferredSize(new Dimension(120, 75));
		
		keylabels[4]= new JLabel("Exploded Mine"); 
		keylabels[4].setBackground(Color.BLACK);
		keylabels[4].setOpaque(true);
		keylabels[4].setPreferredSize(new Dimension(120, 75));
		
		keylabels[5]= new JLabel(""); 
		keylabels[5].setPreferredSize(new Dimension(120, 75));
		
		keylabels[6]= new JLabel("Start"); 
		keylabels[6].setBackground(Color.CYAN);
		keylabels[6].setOpaque(true);
		keylabels[6].setPreferredSize(new Dimension(120, 75));
		
		keylabels[7]= new JLabel("End"); 
		keylabels[7].setBackground(Color.MAGENTA);
		keylabels[7].setOpaque(true);
		keylabels[7].setPreferredSize(new Dimension(120, 75));
	}
	
	//Score Labels
	{
		scoreLabels[0] = new JLabel("Lives: " + lives);
		scoreLabels[1] = new JLabel("Score: " + score);
	}
	

	
	/**
	 * Creates the sub panels and actionlisteners for each panel and then add this to one main panel with it layout determined.
	 * @param gridlistener
	 * @param showminelistener
	 * @param pathlistener
	 * @param gamelistener
	 * @param gridsizeListener
	 * @param gridsize
	 */
	public MineFieldPanel(ActionListener gridlistener,ActionListener showminelistener, ActionListener pathlistener, ActionListener gamelistener, ActionListener gridsizeListener, int gridsize) 
	{
		this.setLayout(new BorderLayout());
		keyPanel = new JPanel();
		keyPanel.setPreferredSize(new Dimension(150, 700));
		
		
		//Creating the grid for the mine field
		this.gridsize = gridsize;
		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(gridsize, gridsize));
		gridPanel.setPreferredSize(new Dimension(700, 700));
		
		
		
		//Creating the score sub panel
		scorePanel = new JPanel();
		//scorePanel.setLayout(new BoxLayout());
		scorePanel.setPreferredSize(new Dimension(150,700));
		scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
		
		//Creating a control sub panel
		controlPanel = new JPanel();
		controlPanel.setPreferredSize(new Dimension (0,100));
		
		//buttons
		grid = new MineFieldButton[gridsize][gridsize];
		//Creating the rows
		for (int i = 0; i < grid.length; i++) 
		{
			//Creating the columns
			for(int k = 0; k< grid[i].length; k++) {
				grid[i][k] = (new MineFieldButton());
				grid[i][k].addActionListener(gridlistener);
				
				
				gridPanel.add(grid[i][k]);
			}
		}
		
		
		//Adding key labels and colors to the keys
		for(int i = 0; i < keylabels.length; i++) 
			{

				keyPanel.add(keylabels[i]);
			}
		
		//Adding score labels
		scorePanel.add(Box.createVerticalGlue());
		scorePanel.add(scoreLabels[0]);
		scorePanel.add(scoreLabels[1]);
		scorePanel.add(Box.createVerticalGlue());
		

		//Creating the buttons for control panel
		showMines = new JButton();
		showMines.setText("Show Mines");
		showMines.addActionListener(showminelistener);
		controlPanel.add(showMines);
		
		showPath = new JButton();
		showPath.setText("Show Path");
		showPath.addActionListener(pathlistener);
		controlPanel.add(showPath);
		
		giveUp = new JButton();
		giveUp.setText("New Game");
		giveUp.addActionListener(gamelistener);
		controlPanel.add(giveUp);
		
		//Creating the random walk and adding the path to the game.
		RandomWalk path = new RandomWalk(gridsize);
		path.createWalk();
		points = path.getPath();
		for(int i = 0; i < points.size(); i++)
		{
			grid[(int) points.get(i).getX()][(int) points.get(i).getY()].setPathStatus();
		}
		
		int mineAmount = grid.length/25;
		//Generates mines
		for(int mines = 0; mines <= (mineAmount);) {
			for(int i = 0; i < grid.length; i++) 
			{
			
				for(int k = 0; k < grid[i].length; k++) 
				{
					if(grid[i][k].getPathStatus() == true)
					{
					
					}
					else if(grid[i][k].getPathStatus() == false)
					{
						
						int n = rand.nextInt(2) +1;
						if(n == 1) 
						{
							grid[i][k].SetMine();
							mines++;
						}
						
					}
				}
			}
		}
		
		//Creating a textfield to change the size of the grid
		JTextField textfield = new JTextField(5);
		String size = "10";
		textfield.setText(size );
		textfield.addActionListener(gridsizeListener);
		controlPanel.add(textfield);
		
		//Adding the sub panels
		this.add(keyPanel, BorderLayout.WEST);
		this.add(gridPanel, BorderLayout.CENTER);
		this.add(scorePanel, BorderLayout.EAST);
		this.add(controlPanel, BorderLayout.SOUTH);
		
	}
	
	/**
	 * Gets the number of mines nearby and changes the color of the button.
	 */
	void NumberOfMines() 
	{
		for(int i = 0; i < grid.length; i++) 
		{

			for(int k = 0; k < grid[i].length; k++) 
			{
				if(grid[i][k].getClickedStatus()) 
				{
					
					grid[i][k].ResetColorIndex();
					
					if(i+1 >= 0 && i+1 < grid.length) 
					{
						if(grid[i+1][k].getMineStatus() == true) 
						{
							grid[i][k].getcolorIndex();
						}
						grid[i + 1][k].setEnabled(true);
					}
					
					if(k+1 >= 0 && k+1 <grid.length) 
					{
						if(grid[i][k+1].getMineStatus() == true) 
						{
							grid[i][k].getcolorIndex();
						}	
						grid[i][k + 1].setEnabled(true);
					}
					
					if(k-1 >= 0 && k-1 < grid.length) 
					{
						if(grid[i][k - 1].getMineStatus() == true) 
						{
							grid[i][k].getcolorIndex();
						}
						grid[i][k - 1].setEnabled(true);
					}
					
					if(i-1 >= 0 && i-1 < grid.length) 
					{
						if(grid[i - 1][k].getMineStatus() == true) 
						{
							grid[i][k].getcolorIndex();
						}
						grid[i - 1][k].setEnabled(true);	
					}
					
					grid[i][k].setBackground(grid[i][k].getColor());
					
					
				}
			}
		}
	}
	
	private int clickedPathButton = 0;
	
	/**
	 * Shows the path to the user when they click the show path button.
	 */
	void ShowPath() 
	{
		if(clickedPathButton == 0) 
		{
			for(int i = 0; i < grid.length; i++) 
			{
		
			for(int k = 0; k < grid[i].length; k++) 
			{
				if(grid[i][k].getPathStatus() == true)
				{
					grid[i][k].setBackground(Color.CYAN);
				}
			}
			}
		showPath.setText("Hide Path");
		clickedPathButton = 1;
		}
		else if (clickedPathButton == 1) 
		{
			for(int i = 0; i < grid.length; i++) 
			{
		
			for(int k = 0; k < grid[i].length; k++) 
			{
				if(grid[i][k].getPathStatus() == true)
				{
					grid[i][k].setBackground(null);
				}
			}
			}
			showPath.setText("Show Path");
			clickedPathButton = 0;
		}
	}
	
	private int clickedMineButton = 0;
	/**
	 * Shows the mines
	 */
	void ShowMine() 
	{
		if(clickedMineButton == 0) 
		{
			for(int i = 0; i < grid.length; i++) 
			{
		
			for(int k = 0; k < grid[i].length; k++) 
			{
				if(grid[i][k].getMineStatus() == true)
				{
					grid[i][k].setBackground(Color.BLACK);
				}
			}
			}
		showMines.setText("Hide Mine");
		clickedMineButton = 1;
		}
		else if (clickedMineButton == 1) 
		{
			for(int i = 0; i < grid.length; i++) 
			{
		
			for(int k = 0; k < grid[i].length; k++) 
			{
				if(grid[i][k].getMineStatus() == true)
				{
					grid[i][k].setBackground(null);
				}
			}
			}
			showMines.setText("Show Mine");
			clickedMineButton = 0;
		}
	}
	
	/**
	 * Makes the color of everything but the starter point grey
	 */

	void NewGame() 
	{
		
		for(int i = 0; i < grid.length; i++) 
			{
	
				for(int k = 0; k < grid[i].length; k++) 
				{
				grid[i][k].setBackground(null);
				grid[i][k].setEnabled(false);
				}
			}

				grid[(grid.length -1)][0].setBackground(Color.CYAN);
				grid[(grid.length -1)][0].setEnabled(true);
				

				grid[0][(grid.length -1)].setBackground(Color.MAGENTA);

	}
	
	/**
	 * Brings the life count down one if they clicked a mine
	 */
	void LifeCounter() 
	{
		lives = lives -1;
		scoreLabels[0].setText("Lives: " + lives);
		if(lives == 0) 
		{
			String GameOver;
			GameOver = "You Lose Game Over";
			JOptionPane.showMessageDialog(null, GameOver);
			for(int i = 0; i < grid.length; i++) 
			{
	
				for(int k = 0; k < grid[i].length; k++) 
				{
				grid[i][k].setEnabled(false);
				}
			}
			
		}
	}
	
	/**
	 * Subtracts the score of hitting a mine
	 */
	void ScoreMine() 
	{
		score = score - 100;
		scoreLabels[1].setText("Score: " + score);
		
	}
	
	/**
	 * Subtracts the score for moving one spot
	 */
	void ScoreMove() 
	{
		score = score - 1;
		scoreLabels[1].setText("Score: " + score);
	}
	
	/**
	 * Marker Remover
	 */
	void MarkerRemover() 
	{
		for(int i = 0; i < grid.length; i++) 
		{

			for(int k = 0; k < grid[i].length; k++) 
			{
			grid[i][k].setText(null);
			grid[i][k].isNotClicked();
			grid[i][k].setEnabled(false);
			}
		}
	}
	
	/**
	 * Lets the user know if they won or not
	 */
	void Winner() 
	{
		for(int i = 0; i < grid.length; i++) 
		{

			for(int k = 0; k < grid[i].length; k++) 
			{
				if(grid[i][k].getClickedStatus()) 
				{
					if(grid[i][k].equals(grid[0][(grid.length -1)])) 
					{
						String GameOver;
						GameOver = "You Win and your Score is " + score;
						JOptionPane.showMessageDialog(null, GameOver);
						
						for(int x = 0; x < grid.length; x++) 
						{
				
							for(int y = 0; y < grid[x].length; y++) 
							{
							grid[x][y].setEnabled(false);
							}
						}
					}
				}
			}
		}
	}
}
	
