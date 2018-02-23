package graphical_Interface;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//MainGui
public class Game_Interface {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				startGUI();
			}
		});
	}

	//Setup the GUI
	private static void startGUI() {
		JFrame window = new JFrame();
		setupContent(window);
		//IconJFrame(window);
		window.setTitle("Master Defense");
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setUndecorated(true);
		window.setVisible(true);
		window.setResizable(false);
	}
	
	//Setup the GUI functions.
	private static void setupContent(JFrame window) {
		JPanel content = new JPanel();
		window.setContentPane(content);
	}

	//Just a bit fun :D
	private static void IconJFrame(JFrame window) {
		ImageIcon icon;
		icon = new ImageIcon("");			//Still need a path an a picture :(
		setIconImage(icon.getImage());
	}

}
