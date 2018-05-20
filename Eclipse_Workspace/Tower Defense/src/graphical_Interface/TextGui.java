package graphical_Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

public class TextGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6557422167637163832L;
	private JPanel lobbyPanel;
	private JTextField textField;
	private JLabel lblContent;
	private TD_Client myClient;
	private JPanel playerPanel;
	private JPanel player_1_Panel;
	private JPanel player_2_Panel;
	private JPanel player_1_TagPanel;
	private JLabel lblPlayer_1;
	private JPanel player_2_TagPanel;
	private JLabel lblPlayer_2;
	private JPanel player_1_ReadyPanel;
	private JLabel lblReadyPlayer_1;
	private JPanel player_2_ReadyPanel;
	private JLabel lblReadyPlayer_2;
	private JPanel player_1_NamePanel;
	private JPanel player_2_NamePanel;
	private JLabel lblPlayer_2_Name;
	private JLabel lblPlayer_1_Name;
	private JPanel player_1_NamePanel_;
	private JPanel spacePanel_1;
	private Component rigidArea;
	private JPanel player_2_NamePanel_;
	private JPanel spacePanel_2;
	private Component rigidArea_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TextGui frame = new TextGui();
					frame.setVisible(true);
					TD_Client tdc = new TD_Client(args[0], Integer.parseInt(args[1]), frame);
					frame.setClient(tdc);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setClient(TD_Client pClient) {
		this.myClient = pClient;
	}

	/**
	 * Create the frame.
	 */
	public TextGui() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		lobbyPanel = new JPanel();
		lobbyPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(lobbyPanel);
		lobbyPanel.setLayout(new BorderLayout(0, 0));

		JButton ClientBtn = new JButton("SendMessage");
		ClientBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				schicken();
			}
		});
		lobbyPanel.add(ClientBtn, BorderLayout.EAST);

		textField = new JTextField();
		lobbyPanel.add(textField, BorderLayout.NORTH);
		textField.setColumns(10);

		lblContent = new JLabel("Content");
		lobbyPanel.add(lblContent, BorderLayout.SOUTH);
		
		playerPanel = new JPanel();
		lobbyPanel.add(playerPanel, BorderLayout.CENTER);
		playerPanel.setLayout(new BorderLayout(0, 0));
		
		player_1_Panel = new JPanel();
		playerPanel.add(player_1_Panel, BorderLayout.WEST);
		player_1_Panel.setLayout(new BorderLayout(0, 0));
		
		player_1_TagPanel = new JPanel();
		player_1_Panel.add(player_1_TagPanel, BorderLayout.NORTH);
		player_1_TagPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblPlayer_1 = new JLabel("Player 1");
		lblPlayer_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		player_1_TagPanel.add(lblPlayer_1);
		
		player_1_ReadyPanel = new JPanel();
		player_1_Panel.add(player_1_ReadyPanel, BorderLayout.SOUTH);
		player_1_ReadyPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblReadyPlayer_1 = new JLabel("_isReady_");
		lblReadyPlayer_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		player_1_ReadyPanel.add(lblReadyPlayer_1);
		
		player_1_NamePanel = new JPanel();
		player_1_Panel.add(player_1_NamePanel, BorderLayout.CENTER);
		player_1_NamePanel.setLayout(new BorderLayout(0, 0));
		
		player_1_NamePanel_ = new JPanel();
		player_1_NamePanel.add(player_1_NamePanel_, BorderLayout.NORTH);
		player_1_NamePanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblPlayer_1_Name = new JLabel("_NAME_");
		player_1_NamePanel_.add(lblPlayer_1_Name);
		lblPlayer_1_Name.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		spacePanel_1 = new JPanel();
		player_1_NamePanel.add(spacePanel_1, BorderLayout.CENTER);
		
		rigidArea = Box.createRigidArea(new Dimension(100, 20));
		spacePanel_1.add(rigidArea);
		
		player_2_Panel = new JPanel();
		playerPanel.add(player_2_Panel, BorderLayout.EAST);
		player_2_Panel.setLayout(new BorderLayout(0, 0));
		
		player_2_TagPanel = new JPanel();
		player_2_Panel.add(player_2_TagPanel, BorderLayout.NORTH);
		player_2_TagPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblPlayer_2 = new JLabel("Player 2");
		lblPlayer_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		player_2_TagPanel.add(lblPlayer_2);
		
		player_2_ReadyPanel = new JPanel();
		player_2_Panel.add(player_2_ReadyPanel, BorderLayout.SOUTH);
		
		lblReadyPlayer_2 = new JLabel("_isReady_");
		lblReadyPlayer_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		player_2_ReadyPanel.add(lblReadyPlayer_2);
		
		player_2_NamePanel = new JPanel();
		player_2_Panel.add(player_2_NamePanel, BorderLayout.CENTER);
		player_2_NamePanel.setLayout(new BorderLayout(0, 0));
		
		player_2_NamePanel_ = new JPanel();
		player_2_NamePanel.add(player_2_NamePanel_, BorderLayout.NORTH);
		
		lblPlayer_2_Name = new JLabel("_NAME_");
		player_2_NamePanel_.add(lblPlayer_2_Name);
		lblPlayer_2_Name.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		spacePanel_2 = new JPanel();
		player_2_NamePanel.add(spacePanel_2, BorderLayout.CENTER);
		
		rigidArea_1 = Box.createRigidArea(new Dimension(100, 20));
		spacePanel_2.add(rigidArea_1);
	}

	public void ausgeben(String message) {
		this.lblContent.setText(message);
	}
	
	public void setReadyPlayer1(String readyText) {
		this.lblReadyPlayer_1.setText(readyText);
	}
	
	public void setReadyPlayer2(String readyText) {
		this.lblReadyPlayer_2.setText(readyText);
	}
	
	public void setUsernamePlayer1(String name) {
		this.lblPlayer_1_Name.setText(name);
	}
	
	public void setUsernamePlayer2(String name) {
		this.lblPlayer_2_Name.setText(name);
	}

	public void schicken() {
		String message = this.textField.getText();
		this.myClient.send(message);
	}

}
