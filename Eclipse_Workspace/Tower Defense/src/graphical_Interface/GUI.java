package graphical_Interface;

import java.awt.Toolkit;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.io.*;
import javax.imageio.*;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.Image;

//MainGui
public class GUI extends GUI_Content {

	private static final Color Color = null;
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
		window_1.setTitle("Master Defense");
		window_1.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window_1.setUndecorated(true);
		window_1.setVisible(true);
		window_1.setResizable(false);
		window_1.getContentPane().setBackground(Color.BLACK);
	}

	// Setup the GUI functions.
	private static void setupContent(JFrame window) {

		// I try to make the Background beautiful but it won�t work :(

		JMenuBar menuBar = new JMenuBar();
		window_1.setJMenuBar(menuBar);

		// GUI settings
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);

		// Options begin here!!
		JMenu mnLanguage = new JMenu("Language");
		mnOptions.add(mnLanguage);

		JMenuItem mntmDeutsch = new JMenuItem("Deutsch");

		// Sets the programs language to German
		mntmDeutsch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchLanguage();
			}
		});
		mnLanguage.add(mntmDeutsch);

		JMenuItem mntmEnglish = new JMenuItem("English");

		// Sets the programs language to English
		mntmEnglish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchLanguage();
			}
		});
		mnLanguage.add(mntmEnglish);

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
		content.setLayout(new BorderLayout());
		try {
			content.add(new JLabel(new ImageIcon(ImageIO
					.read(new File("H:\\git\\Tower_Defense\\TestFile\\Eclipse_Workspace\\Tower Defense\\Images\\1.png"))
					.getScaledInstance(Toolkit.getDefaultToolkit()
				            .getScreenSize().width, Toolkit.getDefaultToolkit()
				            .getScreenSize().height, Image.SCALE_FAST))), BorderLayout.CENTER);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		window.setContentPane(content);

	}
}