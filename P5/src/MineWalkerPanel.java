import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * Creates a new minefield gui with a slide for size
 * @param width The number of pegs in the horizontal axis.
 * @param height The number of pegs in the vertical axis.
 * @author tylerjohndrow
 *
 */
@SuppressWarnings("serial")
public class MineWalkerPanel extends JPanel 
{
	private MineFieldPanel board;
	
	public MineWalkerPanel(int size)
	{
		
		// Create new LiteBriteBoard with specified dimensions
		board = new MineFieldPanel(new MineFieldButtonListener(),new ShowMineListener(), new ShowPathListener(), new NewGameListener(), new gridSizeListener(), size);
		
		// Add sub-components to this main panel.
		board.NewGame();
		this.add(board);
	}
	/**
	 * ActionListener for the show mine button
	 */
	private class ShowMineListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			board.ShowMine();
			
		}
		
	}
	/**
	 * ActionListener for the textfield box
	 */
	private class gridSizeListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			JTextField text = (JTextField) e.getSource();
			int temp = Integer.parseInt(text.getText());
			if(temp >= 5 && temp <= 20) 
			{
			MineWalker.size = temp;
			}
			else 
			{
				String GridSize;
				GridSize = "Please keep grid size in between 5 and 20";
				JOptionPane.showMessageDialog(null, GridSize);
			}
			
		}
		
	}
	/**
	 * ActionListener for the show path button
	 */
	private class ShowPathListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			board.ShowPath();
			

			
		}
		
	}
	/**
	 * ActionListener for the new game button
	 */
	private class NewGameListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			
			MineWalkerPanel.this.remove(board);
			MineWalkerPanel.this.revalidate();
			board = new MineFieldPanel(new MineFieldButtonListener(),new ShowMineListener(), new ShowPathListener(), new NewGameListener(), new gridSizeListener(), MineWalker.size);
			MineWalkerPanel.this.add(board);
			MineWalkerPanel.this.revalidate();
			board.NewGame();
			
		}
		
	}
	/**
	 * ActionListener for when you click the mine field
	 */
	private class MineFieldButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			MineFieldButton clicked = (MineFieldButton)(e.getSource());
			//Checking to see if the button pressed is a mine
			if(clicked.getMineStatus() == true) 
			{
				clicked.setEnabled(false);
				board.LifeCounter();
				MineWalkerPanel.this.revalidate();
				clicked.setBackground(Color.BLACK);
				board.ScoreMine();
				
				
			}
			
			//Doing score moving one spot if it is not a mine
			else 
			{
				board.ScoreMove();
				board.MarkerRemover();
				clicked.Marker();
				clicked.isClicked();
				board.NumberOfMines();
				board.Winner();
			}
			//Repainting the color of the mine based on how many mines are nearby/ if it is a mine
			clicked.repaint();
			
			
		}
		
	}
}
