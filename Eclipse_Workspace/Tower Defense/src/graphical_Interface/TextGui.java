package graphical_Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import network.Protocol;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.Box;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TextGui extends JFrame {

	/**
	 * 
	 */
	private static final String USERNAMEPRESET = "USERNAME : ";
	private static final long serialVersionUID = 6557422167637163832L;
	private JPanel switchPanel;
	private JTextField lobbyTextField;
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
	private JPanel lobbyPanel;
	private JPanel loginPanel;
	private JPanel loginInputPanel;
	private JPanel loginTextPanel;
	private JLabel lblTextOutput;
	private JTextField loginTextField;
	private JPanel switchButtonPanel;
	private JPanel switchablePanel;
	private JButton switchButton;
	private JPanel usernamePasswordSwitchPanel;
	private JPanel usernamePanel;
	private JPanel usernameTextPanel;
	private JLabel usernameTextLabel;
	private JPanel usernameInputPanel;
	private JTextField usernameInputTextField;
	private JPanel passwordPanel;
	private JPanel passwordTextPanel;
	private JPanel passwordInputPanel;
	private JLabel passwordTextLabel;
	private JTextField passwordInputTextField;
	private JPanel passwordUsernameTextPanel;
	private JLabel passwordUsernameLabel;
	private JPanel usernameResponsePanel;
	private JLabel usernameResponseLabel;

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

		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("Images/windowIcon.png"));
		} catch (Exception ex) {

		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setIconImage(image);
		switchPanel = new JPanel();
		switchPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(switchPanel);
		switchPanel.setLayout(new BorderLayout(0, 0));

		switchButtonPanel = new JPanel();
		switchPanel.add(switchButtonPanel, BorderLayout.EAST);
		switchButtonPanel.setLayout(new BorderLayout(0, 0));

		switchButton = new JButton("SwitchLobby");
		switchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				switchPanel();
			}
		});
		switchButtonPanel.add(switchButton, BorderLayout.CENTER);

		switchablePanel = new JPanel();
		switchPanel.add(switchablePanel, BorderLayout.CENTER);
		switchablePanel.setLayout(new CardLayout(0, 0));

		loginPanel = new JPanel();
		switchablePanel.add(loginPanel, "loginPanel");
		loginPanel.setLayout(new BorderLayout(0, 0));

		loginInputPanel = new JPanel();
		loginPanel.add(loginInputPanel, BorderLayout.NORTH);
		loginInputPanel.setLayout(new BorderLayout(0, 0));

		loginTextField = new JTextField();
		loginTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		loginInputPanel.add(loginTextField);
		loginTextField.setColumns(10);

		loginTextPanel = new JPanel();
		loginPanel.add(loginTextPanel, BorderLayout.SOUTH);
		loginTextPanel.setLayout(new BorderLayout(0, 0));

		lblTextOutput = new JLabel("_TEXT_");
		lblTextOutput.setFont(new Font("Tahoma", Font.PLAIN, 14));
		loginTextPanel.add(lblTextOutput, BorderLayout.SOUTH);

		usernamePasswordSwitchPanel = new JPanel();
		loginPanel.add(usernamePasswordSwitchPanel, BorderLayout.CENTER);
		usernamePasswordSwitchPanel.setLayout(new CardLayout(0, 0));

		usernamePanel = new JPanel();
		usernamePasswordSwitchPanel.add(usernamePanel, "usernamePanel");
		usernamePanel.setLayout(new BorderLayout(0, 0));

		usernameTextPanel = new JPanel();
		usernamePanel.add(usernameTextPanel, BorderLayout.NORTH);

		usernameTextLabel = new JLabel("Username : ");
		usernameTextLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		usernameTextPanel.add(usernameTextLabel);

		usernameInputPanel = new JPanel();
		usernamePanel.add(usernameInputPanel, BorderLayout.CENTER);

		usernameInputTextField = new JTextField();
		usernameInputTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		usernameInputTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					sendToServer(Protocol.CS_LOGIN_USERNAME + Protocol.SEPARATOR + usernameInputTextField.getText());
				}
			}
		});
		usernameInputTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				usernameInputTextField.setText("");
			}
		});
		usernameInputTextField.setText("USERNAME");
		usernameInputPanel.add(usernameInputTextField);
		usernameInputTextField.setColumns(20);
		
		usernameResponsePanel = new JPanel();
		usernamePanel.add(usernameResponsePanel, BorderLayout.SOUTH);
		
		usernameResponseLabel = new JLabel(" ");
		usernameResponseLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		usernameResponsePanel.add(usernameResponseLabel);

		passwordPanel = new JPanel();
		usernamePasswordSwitchPanel.add(passwordPanel, "passwordPanel");
		passwordPanel.setLayout(new BorderLayout(0, 0));

		passwordTextPanel = new JPanel();
		passwordPanel.add(passwordTextPanel, BorderLayout.NORTH);

		passwordTextLabel = new JLabel("Password :");
		passwordTextLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordTextPanel.add(passwordTextLabel);

		passwordInputPanel = new JPanel();
		passwordPanel.add(passwordInputPanel, BorderLayout.CENTER);

		passwordInputTextField = new JTextField();
		passwordInputTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passwordInputTextField.setText("");
			}
		});
		passwordInputTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					sendToServer(Protocol.CS_LOGIN_PASSWORD + Protocol.SEPARATOR + passwordInputTextField.getText());
				}
			}
		});
		passwordInputTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordInputTextField.setText("PASSWORD");
		passwordInputPanel.add(passwordInputTextField);
		passwordInputTextField.setColumns(20);
		
		passwordUsernameTextPanel = new JPanel();
		passwordPanel.add(passwordUsernameTextPanel, BorderLayout.SOUTH);
		
		passwordUsernameLabel = new JLabel("Username : ");
		passwordUsernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordUsernameTextPanel.add(passwordUsernameLabel);

		lobbyPanel = new JPanel();
		switchablePanel.add(lobbyPanel, "lobbyPanel");
		lobbyPanel.setLayout(new BorderLayout(0, 0));

		JButton sendButton = new JButton("SendMessage");
		sendButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lobbyPanel.add(sendButton, BorderLayout.EAST);

		lobbyTextField = new JTextField();
		lobbyTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lobbyPanel.add(lobbyTextField, BorderLayout.NORTH);
		lobbyTextField.setColumns(10);

		lblContent = new JLabel("Content");
		lblContent.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendToServer(lobbyTextField.getText());
			}
		});
	}

	public void switchPanel() {
		CardLayout cl = (CardLayout) switchablePanel.getLayout();
		cl.next(switchablePanel);
	}

	public void switchPanelLoginUsername() {
		CardLayout cl = (CardLayout) switchablePanel.getLayout();
		cl.show(switchablePanel, "loginPanel");
		CardLayout clUP = (CardLayout) usernamePasswordSwitchPanel.getLayout();
		clUP.show(usernamePasswordSwitchPanel, "usernamePanel");
	}
	
	public void switchPanelLoginPassword(String pUsername) {
		CardLayout cl = (CardLayout) switchablePanel.getLayout();
		cl.show(switchablePanel, "loginPanel");
		CardLayout clUP = (CardLayout) usernamePasswordSwitchPanel.getLayout();
		clUP.show(usernamePasswordSwitchPanel, "passwordPanel");
		this.passwordUsernameLabel.setText(USERNAMEPRESET + pUsername);
	}

	public void switchPanelLobby() {
		CardLayout cl = (CardLayout) switchablePanel.getLayout();
		cl.show(switchablePanel, "lobbyPanel");
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

	public void sendToServer(String pMessage) {
		this.myClient.send(pMessage);
	}

	public void setUsernameResponseLabelText(String pString) {
		this.usernameResponseLabel.setText(pString);
	}

}
