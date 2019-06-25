import javax.swing.JFrame;

public class MineWalker 
{
	public static int size = 10;
	public static void main(String args[])
	{
		JFrame frame = new JFrame("Mine Walker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new MineWalkerPanel(size));
		frame.pack();
		frame.setVisible(true);
	}
}
