import java.awt.Color;

import javax.swing.JButton;

/**
 * Creates single button within the mine field
 * @author tylerjohndrow
 *
 */
@SuppressWarnings("serial")
public class MineFieldButton extends JButton
{
	
	private Color[] Colors = new Color[5];
	{
		Colors[0] = Color.GREEN;
		Colors[1] = Color.YELLOW;
		Colors[2] = Color.ORANGE;
		Colors[3] = Color.RED;
		Colors[4] = Color.BLACK;
	}
	private int colorIndex = 0;
	private boolean minestatus;
	private boolean pathStatus = false;
	private boolean clickedStatus = false;
	/**
	 * Creates one button
	 */
	public MineFieldButton() 
	{
		minestatus = false;
	}
	
	/**
	 * Get the status of the Button
	 */
	public boolean getMineStatus() 
	{
		return minestatus;
	}
	
	/**
	 *sets the button to a mine
	 * @return
	 */
	public boolean SetMine() 
	{
		minestatus = true;
		colorIndex = 4;
		return minestatus;
	}
	
	/**
	 * Gets the number of mines nearby
	 * @return
	 */
	public int getcolorIndex() 
	{
		colorIndex = colorIndex + 1;
		return colorIndex;
	}
	/**
	 * Returns the color value
	 * @return
	 */
	public Color getColor() {
		
		return Colors[colorIndex];
		
	}
	/**
	 * Returns if the button is on the path
	 * @return
	 */
	public boolean getPathStatus() {
		
		return pathStatus;
		
	}
	/**
	 * Set the status of a button for a path to true
	 * @return
	 */
	public boolean setPathStatus() 
	{
		pathStatus = true;
		return pathStatus;
		
	}
	
	/**
	 * Puts a marker on the button
	 */
	void Marker() 
	{
		MineFieldButton.this.setText("X");
		
	}

	/**
	 * Lets you know if the button is clicked
	 * @return
	 */
	public boolean isClicked() 
	{
		clickedStatus = true;
		return clickedStatus;
	}
	
	/**
	 * Lets you know if the button is not clicked.
	 * @return
	 */
	public boolean isNotClicked() 
	{
		clickedStatus = false;
		return clickedStatus;
	}
	
	/**
	 * Returns the clicked status
	 * @return
	 */
	public boolean getClickedStatus() 
	{
		return clickedStatus;
		
	}
	
	public int ResetColorIndex() 
	{
		colorIndex = 0;
		return colorIndex;
		
	}
	
}
