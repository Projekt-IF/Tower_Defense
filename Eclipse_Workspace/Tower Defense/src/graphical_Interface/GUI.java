package graphical_Interface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//MainGui
public class GUI {
	private static JFrame window_1;

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				startGUI();
			}
		});
	}

	// Setup the GUI
	private static void startGUI() {
		window_1 = new JFrame();
		setupContent(window_1);
		// IconJFrame(window);
		window_1.setTitle("Master Defense");
		window_1.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window_1.setUndecorated(true);
		window_1.setVisible(true);
		window_1.setResizable(false);
		window_1.getContentPane().setBackground(Color.BLACK);
	}

	// Setup the GUI functions.
	private static void setupContent(JFrame window) {

		JMenuBar menuBar = new JMenuBar();
		window_1.setJMenuBar(menuBar);

		// Gui settings
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);

		// Options begin here!!
		JMenu mnLanguage = new JMenu("Language");
		mnOptions.add(mnLanguage);

		JMenuItem mntmDeutsch = new JMenuItem("Deutsch");

		// Sets the programs language to German
		mntmDeutsch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setGerman();
			}
		});
		mnLanguage.add(mntmDeutsch);

		JMenuItem mntmEnglish = new JMenuItem("English");

		// Sets the programs language to English
		mntmEnglish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEnglish();
			}
		});
		mnLanguage.add(mntmEnglish);

		JMenu mnGameSettings = new JMenu("Game Settings");
		mnOptions.add(mnGameSettings);

		JMenuItem mntmSound = new JMenuItem("Sound");

		// sets the programs sound volume
		mntmSound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSound();
			}
		});
		mnGameSettings.add(mntmSound);
		// Sound option ends here

		JSeparator separator = new JSeparator();
		mnOptions.add(separator);

		JMenuItem mntmLogin = new JMenuItem("Login");
		mnOptions.add(mntmLogin);

		JMenuItem mntmLogout = new JMenuItem("Logout");
		mnOptions.add(mntmLogout);

		JSeparator separator_1 = new JSeparator();
		mnOptions.add(separator_1);

		JMenuItem mntmExitGame = new JMenuItem("Exit Game");

		// End the program
		mntmExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		mnOptions.add(mntmExitGame);

		// Options end here!!

		JPanel content = new JPanel();
		window.setContentPane(content);
		content.setLayout(new BorderLayout(0, 0));
	}

	// Sets the programs language to German
	private static void setGerman() {

	}

	// Sets the programs language to German
	private static void setEnglish() {

	}
	
	// Sets the programs sound volume
	private static void setSound() {

	}

}