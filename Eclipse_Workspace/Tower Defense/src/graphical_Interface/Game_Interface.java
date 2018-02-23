package graphical_Interface;

import java.util.Date;
import java.io.PrintStream;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Game_Interface{

	static PrintStream OUT = System.out;

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				startGUI();
			}
		});
	}

	private static void startGUI() 
	{
		JFrame window = new JFrame();
		window.setTitle("Master Defense");
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setUndecorated(true);
		window.setVisible(true);
	}

}
