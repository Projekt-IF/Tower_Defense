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
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import javax.swing.JSplitPane;

public class TextGui extends JFrame {

	/**
	 * 
	 */
	private Integer chosenTowerType;
	private Integer chosenEnemyType;
	
	private static final String USERNAMEPRESET = "USERNAME : ";
	private static final long serialVersionUID = 6557422167637163832L;
	private JPanel switchPanel;
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
	private JPanel player_2_ReadyPanel;
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
	private JPanel switchablePanel;
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
	private JPanel loggedInPanel;
	private JPanel loggedIn_TextPanel;
	private JLabel loggedInAsLabel;
	private JPanel loggedInUsernamePanel;
	private JLabel loggedInUsernameLabel;
	private JPanel loggedInLobbySwitchPanel;
	private JButton loggedInSearchLobbyButton;
	private JPanel gamePanel;
	private JLabel readyPlayer_2_Label;
	private JLabel readyPlayer_1_Label;
	private JPanel readyPlayerButtonMapPanel;
	private JButton readyPlayerButton;
	private Component readyPlayerButtonSpace;
	private JPanel switchGamePanel;
	private JPanel gameBuyTowerPanel;
	private JPanel gameBuyEnemiesPanel;
	private JPanel gamePlayPanel;
	private JPanel gameTowerBuyTextPanel;
	private JPanel gameTowerBuyOptionsPanel;
	private JLabel gameTowerBuyTextLabel;
	private JPanel gameEnemyBuyTextPanel;
	private JPanel gameEnemyBuyChosenPanel;
	private JPanel gameEnemyBuyOptionsPanel;
	private JLabel gameEnemyBuyTextLabel;
	private JSplitPane gameTowerBuyMapOptionsSplitPanel;
	private JPanel gameTowerBuyMapPanel;
	private JPanel gameTowerBuyChosenInfoPanel;
	private JButton gameTowerBuyOptions_Tower_1_Button;
	private JButton gameTowerBuyOptions_Tower_2_Button;
	private JButton gameTowerBuyOptions_Tower_3_Button;
	private JButton gameEnemyBuyOptions_Enemy_1_Button;
	private JButton gameEnemyBuyOptions_Enemy_2_Button;
	private JButton gameEnemyBuyOptions_Enemy_3_Button;
	private JLabel readyPlayerMap_Map_Label;
	private JPanel readyPlayerButtonMapSplitPanel;
	private JPanel readyPlayerButtonPanel;
	private JPanel readyPlayerMapPanel;
	private JLabel readyPlayerMap_Name_Label;
	private JLabel readyPlayerMap_Picture_Label;
	private JLabel gameTowerBuyMapPictureLabel;

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
		
		this.chosenTowerType = null;
		this.chosenEnemyType = null;

		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("Images/windowIcon.png"));
		} catch (Exception ex) {

		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		setIconImage(image);
		switchPanel = new JPanel();
		switchPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(switchPanel);
		switchPanel.setLayout(new BorderLayout(0, 0));

		switchablePanel = new JPanel();
		switchPanel.add(switchablePanel, BorderLayout.CENTER);
		switchablePanel.setLayout(new CardLayout(0, 0));

		loginPanel = new JPanel();
		switchablePanel.add(loginPanel, "loginPanel");
		loginPanel.setLayout(new BorderLayout(0, 0));

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

		loggedInPanel = new JPanel();
		usernamePasswordSwitchPanel.add(loggedInPanel, "loggedInPanel");
		loggedInPanel.setLayout(new BorderLayout(0, 0));

		loggedIn_TextPanel = new JPanel();
		loggedInPanel.add(loggedIn_TextPanel, BorderLayout.NORTH);

		loggedInAsLabel = new JLabel("Logged in as : ");
		loggedInAsLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		loggedIn_TextPanel.add(loggedInAsLabel);

		loggedInUsernamePanel = new JPanel();
		loggedInPanel.add(loggedInUsernamePanel, BorderLayout.CENTER);

		loggedInUsernameLabel = new JLabel(" ");
		loggedInUsernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		loggedInUsernamePanel.add(loggedInUsernameLabel);

		loggedInLobbySwitchPanel = new JPanel();
		loggedInPanel.add(loggedInLobbySwitchPanel, BorderLayout.SOUTH);

		loggedInSearchLobbyButton = new JButton("Search Lobby");
		loggedInSearchLobbyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				sendToServer(Protocol.CS_SEARCH_LOBBY);
			}
		});
		loggedInSearchLobbyButton.setForeground(Color.BLACK);
		loggedInSearchLobbyButton.setToolTipText("");
		loggedInSearchLobbyButton.setBackground(Color.WHITE);
		loggedInSearchLobbyButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		loggedInLobbySwitchPanel.add(loggedInSearchLobbyButton);

		lobbyPanel = new JPanel();
		switchablePanel.add(lobbyPanel, "lobbyPanel");
		lobbyPanel.setLayout(new BorderLayout(0, 0));

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

		readyPlayer_1_Label = new JLabel("_isReady_");
		readyPlayer_1_Label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		player_1_ReadyPanel.add(readyPlayer_1_Label);

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

		readyPlayer_2_Label = new JLabel("_isReady_");
		readyPlayer_2_Label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		player_2_ReadyPanel.add(readyPlayer_2_Label);

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

		readyPlayerButtonMapPanel = new JPanel();
		lobbyPanel.add(readyPlayerButtonMapPanel, BorderLayout.EAST);
		readyPlayerButtonMapPanel.setLayout(new BorderLayout(0, 0));

		readyPlayerButtonSpace = Box.createRigidArea(new Dimension(150, 26));
		readyPlayerButtonMapPanel.add(readyPlayerButtonSpace, BorderLayout.NORTH);

		readyPlayerButtonMapSplitPanel = new JPanel();
		readyPlayerButtonMapPanel.add(readyPlayerButtonMapSplitPanel, BorderLayout.CENTER);
		readyPlayerButtonMapSplitPanel.setLayout(new BorderLayout(0, 0));

		readyPlayerButtonPanel = new JPanel();
		readyPlayerButtonMapSplitPanel.add(readyPlayerButtonPanel, BorderLayout.NORTH);
		readyPlayerButtonPanel.setLayout(new BorderLayout(0, 0));

		readyPlayerButton = new JButton("NOT READY");
		readyPlayerButtonPanel.add(readyPlayerButton);
		readyPlayerButton.setForeground(Color.RED);
		readyPlayerButton.setFont(new Font("Tahoma", Font.PLAIN, 14));

		readyPlayerMapPanel = new JPanel();
		readyPlayerButtonMapSplitPanel.add(readyPlayerMapPanel, BorderLayout.CENTER);
		readyPlayerMapPanel.setLayout(new BorderLayout(0, 0));

		readyPlayerMap_Map_Label = new JLabel("         Map:");
		readyPlayerMapPanel.add(readyPlayerMap_Map_Label, BorderLayout.NORTH);
		readyPlayerMap_Map_Label.setFont(new Font("Tahoma", Font.PLAIN, 18));

		readyPlayerMap_Name_Label = new JLabel("_MAPNAME_");
		readyPlayerMap_Name_Label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		readyPlayerMapPanel.add(readyPlayerMap_Name_Label, BorderLayout.SOUTH);

		readyPlayerMap_Picture_Label = new JLabel("_MAP_PICTURE_");
		readyPlayerMap_Picture_Label.setIcon(null);
		readyPlayerMapPanel.add(readyPlayerMap_Picture_Label, BorderLayout.CENTER);
		readyPlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendToServer(Protocol.CS_READY_LOBBY);
			}
		});

		gamePanel = new JPanel();
		switchablePanel.add(gamePanel, "gamePanel");
		gamePanel.setLayout(new BorderLayout(0, 0));

		switchGamePanel = new JPanel();
		gamePanel.add(switchGamePanel, BorderLayout.CENTER);
		switchGamePanel.setLayout(new CardLayout(0, 0));

		gameBuyTowerPanel = new JPanel();
		switchGamePanel.add(gameBuyTowerPanel, "gameBuyTowerPanel");
		gameBuyTowerPanel.setLayout(new BorderLayout(0, 0));

		gameTowerBuyTextPanel = new JPanel();
		gameBuyTowerPanel.add(gameTowerBuyTextPanel, BorderLayout.NORTH);

		gameTowerBuyTextLabel = new JLabel("Buy Tower : ");
		gameTowerBuyTextLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gameTowerBuyTextPanel.add(gameTowerBuyTextLabel);

		gameTowerBuyOptionsPanel = new JPanel();
		gameBuyTowerPanel.add(gameTowerBuyOptionsPanel, BorderLayout.SOUTH);

		gameTowerBuyOptions_Tower_1_Button = new JButton("T-Type 1");
		gameTowerBuyOptions_Tower_1_Button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gameTowerBuyOptions_Tower_1_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				chosenTowerType = 1;
			}
		});
		gameTowerBuyOptionsPanel.add(gameTowerBuyOptions_Tower_1_Button);

		gameTowerBuyOptions_Tower_2_Button = new JButton("T-Type 2");
		gameTowerBuyOptions_Tower_2_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chosenTowerType = 2;
			}
		});
		gameTowerBuyOptions_Tower_2_Button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gameTowerBuyOptionsPanel.add(gameTowerBuyOptions_Tower_2_Button);

		gameTowerBuyOptions_Tower_3_Button = new JButton("T-Type 3");
		gameTowerBuyOptions_Tower_3_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chosenTowerType = 3;
			}
		});
		gameTowerBuyOptions_Tower_3_Button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gameTowerBuyOptionsPanel.add(gameTowerBuyOptions_Tower_3_Button);

		gameTowerBuyMapOptionsSplitPanel = new JSplitPane();
		gameTowerBuyMapOptionsSplitPanel.setResizeWeight(0.5);
		gameBuyTowerPanel.add(gameTowerBuyMapOptionsSplitPanel, BorderLayout.CENTER);

		gameTowerBuyMapPanel = new JPanel();
		gameTowerBuyMapPanel.setToolTipText("\"Hwllo\"");
		gameTowerBuyMapPanel.setBackground(Color.BLACK);
		gameTowerBuyMapOptionsSplitPanel.setLeftComponent(gameTowerBuyMapPanel);
		gameTowerBuyMapPanel.setLayout(new BorderLayout(0, 0));
		
		gameTowerBuyMapPictureLabel = new JLabel("_MAP_PICTURE_");
		gameTowerBuyMapPictureLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		gameTowerBuyMapPictureLabel.setForeground(Color.WHITE);
		gameTowerBuyMapPanel.add(gameTowerBuyMapPictureLabel);

		gameTowerBuyChosenInfoPanel = new JPanel();
		gameTowerBuyChosenInfoPanel.setBackground(Color.WHITE);
		gameTowerBuyMapOptionsSplitPanel.setRightComponent(gameTowerBuyChosenInfoPanel);

		gameBuyEnemiesPanel = new JPanel();
		switchGamePanel.add(gameBuyEnemiesPanel, "gameBuyEnemiesPanel");
		gameBuyEnemiesPanel.setLayout(new BorderLayout(0, 0));

		gameEnemyBuyTextPanel = new JPanel();
		gameBuyEnemiesPanel.add(gameEnemyBuyTextPanel, BorderLayout.NORTH);

		gameEnemyBuyTextLabel = new JLabel("Buy Enemy : ");
		gameEnemyBuyTextLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gameEnemyBuyTextPanel.add(gameEnemyBuyTextLabel);

		gameEnemyBuyChosenPanel = new JPanel();
		gameBuyEnemiesPanel.add(gameEnemyBuyChosenPanel, BorderLayout.CENTER);

		gameEnemyBuyOptionsPanel = new JPanel();
		gameBuyEnemiesPanel.add(gameEnemyBuyOptionsPanel, BorderLayout.SOUTH);

		gameEnemyBuyOptions_Enemy_1_Button = new JButton("E-Type 1");
		gameEnemyBuyOptions_Enemy_1_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chosenEnemyType = 1;
			}
		});
		gameEnemyBuyOptions_Enemy_1_Button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gameEnemyBuyOptionsPanel.add(gameEnemyBuyOptions_Enemy_1_Button);

		gameEnemyBuyOptions_Enemy_2_Button = new JButton("E-Type 2");
		gameEnemyBuyOptions_Enemy_2_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chosenEnemyType = 2;
			}
		});
		gameEnemyBuyOptions_Enemy_2_Button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gameEnemyBuyOptionsPanel.add(gameEnemyBuyOptions_Enemy_2_Button);

		gameEnemyBuyOptions_Enemy_3_Button = new JButton("E-Type 3");
		gameEnemyBuyOptions_Enemy_3_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chosenEnemyType = 3;
			}
		});
		gameEnemyBuyOptions_Enemy_3_Button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gameEnemyBuyOptionsPanel.add(gameEnemyBuyOptions_Enemy_3_Button);

		gamePlayPanel = new JPanel();
		switchGamePanel.add(gamePlayPanel, "gamePlayPanel");
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

	public void switchPanelLoggedIn(String pUsername) {
		CardLayout cl = (CardLayout) switchablePanel.getLayout();
		cl.show(switchablePanel, "loginPanel");
		CardLayout clUP = (CardLayout) usernamePasswordSwitchPanel.getLayout();
		clUP.show(usernamePasswordSwitchPanel, "loggedInPanel");
		this.loggedInUsernameLabel.setText(pUsername);

	}

	public void switchPanelLobby() {
		CardLayout cl = (CardLayout) switchablePanel.getLayout();
		cl.show(switchablePanel, "lobbyPanel");
	}
	
	public void switchPanelGame() {
		CardLayout cl = (CardLayout) switchablePanel.getLayout();
		cl.show(switchablePanel, "gamePanel");
	}
	
	public void switchPanelGameBuyTowers() {
		CardLayout cl = (CardLayout) switchGamePanel.getLayout();
		cl.show(switchGamePanel, "gameBuyTowerPanel");
	}
	public void switchPanelGameBuyEnemies() {
		CardLayout cl = (CardLayout) switchGamePanel.getLayout();
		cl.show(switchGamePanel, "gameBuyEnemiesPanel");
	}
	
	public void switchPanelGamePlay() {
		CardLayout cl = (CardLayout) switchGamePanel.getLayout();
		cl.show(switchGamePanel, "gamePlayPanel");
	}

	public void ausgeben(String message) {
		this.lblContent.setText(message);
	}

	public void setReadyPlayer1(String readyText) {
		this.readyPlayer_1_Label.setText(readyText);
		if (readyText.equals("READY")) {
			this.readyPlayer_1_Label.setForeground(Color.GREEN);
		} else if (readyText.equals("NOT READY")) {
			this.readyPlayer_1_Label.setForeground(Color.RED);
		}
	}

	public void setReadyPlayer2(String readyText) {
		this.readyPlayer_2_Label.setText(readyText);
		if (readyText.equals("READY")) {
			this.readyPlayer_2_Label.setForeground(Color.GREEN);
		} else if (readyText.equals("NOT READY")) {
			this.readyPlayer_2_Label.setForeground(Color.RED);
		}
	}

	public void setReadyButton(String playerStatus) {
		if (playerStatus.equals("true")) {
			readyPlayerButton.setText("READY");
			readyPlayerButton.setForeground(Color.GREEN);
		} else if (playerStatus.equals("false")) {
			readyPlayerButton.setText("NOT READY");
			readyPlayerButton.setForeground(Color.RED);
		}
	}
	
	public void setMapName(String pMapName) {
		this.readyPlayerMap_Name_Label.setText(pMapName);
	}
	
	public void setMapPicture(String pMapPictureName) {
		readyPlayerMap_Picture_Label.setText("");
		gameTowerBuyMapPictureLabel.setText("");
		System.out.println("Images/MapImages/" + pMapPictureName + ".png");
		BufferedImage pPicture = null;
		try {
			pPicture = ImageIO.read(new File("Images/MapImages/" + pMapPictureName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.readyPlayerMap_Picture_Label.setIcon(new ImageIcon(pPicture));
		this.gameTowerBuyMapPictureLabel.setIcon(new ImageIcon(pPicture));
	}

	public void setUsernamePlayer1(String name) {
		this.lblPlayer_1_Name.setText(name);
	}

	public void setUsernamePlayer2(String name) {
		this.lblPlayer_2_Name.setText(name);
	}

	public void setUsernameResponseLabelText(String pString) {
		this.usernameResponseLabel.setText(pString);
	}

	public void buyTowerType_1() {
		this.sendToServer(Protocol.CS_PURCHASE_TOWER + Protocol.SEPARATOR + 1);
	}

	public void buyTowerType_2() {
		this.sendToServer(Protocol.CS_PURCHASE_TOWER + Protocol.SEPARATOR + 2);
	}

	public void buyTowerType_3() {
		this.sendToServer(Protocol.CS_PURCHASE_TOWER + Protocol.SEPARATOR + 3);
	}

	public void buyEnemyType_1() {
		this.sendToServer(Protocol.CS_PURCHASE_ENEMY + Protocol.SEPARATOR + 1);
	}

	public void buyTEnemyType_2() {
		this.sendToServer(Protocol.CS_PURCHASE_ENEMY + Protocol.SEPARATOR + 2);
	}

	public void buyEnemyType_3() {
		this.sendToServer(Protocol.CS_PURCHASE_ENEMY + Protocol.SEPARATOR + 3);
	}

	public void sendToServer(String pMessage) {
		this.myClient.send(pMessage);
	}
}
