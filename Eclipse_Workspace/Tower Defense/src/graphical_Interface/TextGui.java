package graphical_Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import network.Protocol;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.DefaultListModel;
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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.GridLayout;

public class TextGui extends JFrame {

	/**
	 * 
	 */
	private Integer chosenTowerType;
	private Integer chosenEnemyType;

	private JButton[][] buttonArray;

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
	private JPanel passwordInputResponsePanel;
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
	private JPanel gamePlayMapPanel;
	private JPanel gameTowerBuyTextPanel;
	private JPanel gameTowerBuyOptionsPanel;
	private JLabel gameTowerBuyTextLabel;
	private JPanel gameEnemyBuyTextPanel;
	private JPanel gameEnemyBuyChosenPanel;
	private JPanel gameEnemyBuyOptionsPanel;
	private JLabel gameEnemyBuyTextLabel;
	private JSplitPane gameTowerBuyMapChosenSplitPanel;
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
	private JPanel gameTowerBuyMapPicturePanel;
	private JPanel gameTowerBuyMapPositionPanel;
	private JSpinner gameTowerBuyMapPosition_YSpinner;
	private JSpinner gameTowerBuyMapPosition_XSpinner;
	private JLabel gameTowerBuyMapPositionYLabel;
	private JLabel gameTowerBuyMapPositionXLabel;
	private JPanel gameTowerBuyOptionsTypesPanel;
	private JPanel gameTowerBuyOptionsConfirmPanel;
	private JButton gameTowerBuyOptionsConfirm;
	private JList<String> gameTowerBuyChosenList;
	private JLabel gameTowerBuyChosenResponseLabel;
	private JLabel gameTowerBuyTextMoneyLabel;
	private JLabel gameEnemyBuyTextMoneyLabel;
	private JList<String> gameEnemyBuyChosenList;
	private JPanel gameEnemyBuyOptionsTypesPanel;
	private JPanel gameEnemyBuyOptionsConfirmPanel;
	private JButton gameEnemyBuyOptionsConfirm;
	private JLabel gameEnemyBuyChosenResponseLabel;
	private JPanel gameBuyWaitPanel;
	private JLabel gameBuyWaitingLabel;
	private JPanel gamePlayPanel;
	private JPanel gamePlayHealthPanel;
	private JLabel gamePlayHealthOwnLabel;
	private JLabel gamePlayHealthOtherLabel;
	private JLabel gameTowerBuyTextHealthLabel;
	private JLabel gameEnemyBuyTextHealthLabel;
	private JPanel gameEndScreenPanel;
	private JLabel gameEndScreenThisPlayer_PlacingLabel;
	private JPanel gameEndScreenLevelPanel;
	private JLabel gameEndScreenLevelLabel;
	private JLabel gameEndScreenText_PlayerLabel;
	private JLabel gameEndScreenThisPlayer_NameLabel;
	private JLabel gameEndScreenThisPlayer_HealthLabel;
	private JLabel gameEndScreenThisPlayer_MoneyLabel;
	private JLabel gameEndScreenThisPlayer_EnemyLabel;
	private JLabel gameEndScreenThisPlayer_TowerLabel;
	private Component gameEndScreenThisPlayer_PlacingName_VerticalStrut;
	private Component gameEndScreenThisPlayer_HealthMoney_VerticalStrut;
	private Component gameEndScreenThisPlayer_EnemyTower_VerticalStrut;
	private Box gameEndScreenText_VerticalBox;
	private Box gameEndScreenThisPlayer_VerticalBox;
	private Component gameEndScreenThisPlayer_NameHealth_VerticalStrut;
	private Component gameEndScreenThisPlayer_MoneyEnemy_VerticalStrut;
	private Box gameEndScreenOtherPlayer_VerticalBox;
	private JLabel gameEndScreenOtherPlayer_PlacingLabel;
	private Component gameEndScreenOtherPlayer_PlacingName_VerticalStrut;
	private JLabel gameEndScreenOtherPlayer_NameLabel;
	private Component gameEndScreenOtherPlayer_NameHealth_VerticalStrut;
	private JLabel gameEndScreenOtherPlayer_HealthLabel;
	private Component gameEndScreenOtherPlayer_HealthMoney_VerticalStrut;
	private JLabel gameEndScreenOtherPlayer_MoneyLabel;
	private Component gameEndScreenOtherPlayer_MoneyEnemy_VerticalStrut;
	private JLabel gameEndScreenOtherPlayer_EnemyLabel;
	private Component gameEndScreenOtherPlayer_EnemyTower_VerticalStrut;
	private JLabel gameEndScreenOtherPlayer_TowerLabel;
	private Component gameEndScreenText_Player_VerticalStrut;
	private Component gameEndScreenText_PlayerHealth_VerticalStrut;
	private JLabel gameEndScreenText_HealthLabel;
	private Component gameEndScreenText_HealthMoney_VerticalStrut;
	private JLabel gameEndScreenText_MoneyLabel;
	private Component gameEndScreenText_MoneyEnemy_VerticalStrut;
	private JLabel gameEndScreenText_EnemyLabel;
	private JLabel gameEndScreenText_TowerLabel;
	private Component gameEndScreenText_EnemyTower_VerticalStrut;
	private JPanel gameEndScreenExitPanel;
	private JButton gameEndScreenExitButton;
	private JPanel passwordInputPanel;
	private JPanel passwordResponsePanel;
	private JLabel passwordResponselabel;

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
	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	public TextGui() {

		this.chosenTowerType = null;
		this.chosenEnemyType = null;

		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResource("/pictures/windowIcon.png"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 595, 450);
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

		passwordInputResponsePanel = new JPanel();
		passwordPanel.add(passwordInputResponsePanel, BorderLayout.CENTER);
		passwordInputResponsePanel.setLayout(new BorderLayout(0, 0));

		passwordInputPanel = new JPanel();
		passwordInputResponsePanel.add(passwordInputPanel, BorderLayout.CENTER);

		passwordInputTextField = new JTextField();
		passwordInputPanel.add(passwordInputTextField);
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
		passwordInputTextField.setColumns(20);

		passwordResponsePanel = new JPanel();
		passwordInputResponsePanel.add(passwordResponsePanel, BorderLayout.SOUTH);

		passwordResponselabel = new JLabel("");
		passwordResponselabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordResponsePanel.add(passwordResponselabel);

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

		gameTowerBuyTextMoneyLabel = new JLabel("               Money: ");
		gameTowerBuyTextMoneyLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gameTowerBuyTextPanel.add(gameTowerBuyTextMoneyLabel);

		gameTowerBuyTextHealthLabel = new JLabel("               Health:");
		gameTowerBuyTextHealthLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gameTowerBuyTextPanel.add(gameTowerBuyTextHealthLabel);

		gameTowerBuyOptionsPanel = new JPanel();
		gameBuyTowerPanel.add(gameTowerBuyOptionsPanel, BorderLayout.SOUTH);
		gameTowerBuyOptionsPanel.setLayout(new BorderLayout(0, 0));

		gameTowerBuyOptionsTypesPanel = new JPanel();
		gameTowerBuyOptionsPanel.add(gameTowerBuyOptionsTypesPanel);

		gameTowerBuyOptions_Tower_1_Button = new JButton("T-Type 1");
		gameTowerBuyOptions_Tower_1_Button.setToolTipText(
				"\t\tTower Small:\r\n\r\nCost: 100\t\t\tRange: 1\r\n\r\n\t\t\t\t\tDamage: 5\r\n\r\n\t\t\t\t\tCooldown: 5 sec");
		gameTowerBuyOptionsTypesPanel.add(gameTowerBuyOptions_Tower_1_Button);
		gameTowerBuyOptions_Tower_1_Button.setFont(new Font("Tahoma", Font.PLAIN, 14));

		gameTowerBuyOptions_Tower_2_Button = new JButton("T-Type 2");
		gameTowerBuyOptions_Tower_2_Button.setToolTipText(
				"\t\tTower Medium:\r\n\r\nCost: 300\t\t\tRange: 2\r\n\r\n\t\t\t\t\tDamage: 10\r\n\r\n\t\t\t\t\tCooldown: 7 sec");
		gameTowerBuyOptionsTypesPanel.add(gameTowerBuyOptions_Tower_2_Button);
		gameTowerBuyOptions_Tower_2_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chosenTowerType = 2;
				buyTower();
			}
		});
		gameTowerBuyOptions_Tower_2_Button.setFont(new Font("Tahoma", Font.PLAIN, 14));

		gameTowerBuyOptions_Tower_3_Button = new JButton("T-Type 3");
		gameTowerBuyOptions_Tower_3_Button.setToolTipText(
				"\t\tTower Big:\r\n\r\nCost: 500\t\t\tRange: 3\r\n\r\n\t\t\t\t\tDamage: 15\r\n\r\n\t\t\t\t\tCooldown: 10 sec");
		gameTowerBuyOptionsTypesPanel.add(gameTowerBuyOptions_Tower_3_Button);
		gameTowerBuyOptions_Tower_3_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chosenTowerType = 3;
				buyTower();
			}
		});
		gameTowerBuyOptions_Tower_3_Button.setFont(new Font("Tahoma", Font.PLAIN, 14));

		gameTowerBuyOptionsConfirmPanel = new JPanel();
		gameTowerBuyOptionsPanel.add(gameTowerBuyOptionsConfirmPanel, BorderLayout.EAST);

		gameTowerBuyOptionsConfirm = new JButton("Confirm");
		gameTowerBuyOptionsConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				sendToServer(Protocol.CS_READY_TOWERPLACING);
			}
		});
		gameTowerBuyOptionsConfirm.setBackground(Color.BLACK);
		gameTowerBuyOptionsConfirm.setFont(new Font("Tahoma", Font.PLAIN, 30));
		gameTowerBuyOptionsConfirmPanel.add(gameTowerBuyOptionsConfirm);
		gameTowerBuyOptions_Tower_1_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				chosenTowerType = 1;
				buyTower();
			}
		});

		gameTowerBuyMapChosenSplitPanel = new JSplitPane();
		gameTowerBuyMapChosenSplitPanel.setResizeWeight(0.5);
		gameBuyTowerPanel.add(gameTowerBuyMapChosenSplitPanel, BorderLayout.CENTER);

		gameTowerBuyMapPanel = new JPanel();
		gameTowerBuyMapPanel.setToolTipText("");
		gameTowerBuyMapPanel.setBackground(Color.BLACK);
		gameTowerBuyMapChosenSplitPanel.setLeftComponent(gameTowerBuyMapPanel);
		gameTowerBuyMapPanel.setLayout(new BorderLayout(0, 0));

		gameTowerBuyMapPicturePanel = new JPanel();
		gameTowerBuyMapPicturePanel.setBackground(Color.BLACK);
		gameTowerBuyMapPicturePanel.setForeground(Color.WHITE);
		gameTowerBuyMapPanel.add(gameTowerBuyMapPicturePanel, BorderLayout.CENTER);

		gameTowerBuyMapPictureLabel = new JLabel("_MAP_PICTURE_");
		gameTowerBuyMapPicturePanel.add(gameTowerBuyMapPictureLabel);
		gameTowerBuyMapPictureLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		gameTowerBuyMapPictureLabel.setForeground(Color.WHITE);

		gameTowerBuyMapPositionPanel = new JPanel();
		gameTowerBuyMapPositionPanel.setForeground(Color.WHITE);
		gameTowerBuyMapPositionPanel.setBackground(Color.BLACK);
		gameTowerBuyMapPanel.add(gameTowerBuyMapPositionPanel, BorderLayout.SOUTH);

		gameTowerBuyMapPositionYLabel = new JLabel("Y: ");
		gameTowerBuyMapPositionYLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gameTowerBuyMapPositionYLabel.setBackground(Color.BLACK);
		gameTowerBuyMapPositionYLabel.setForeground(Color.WHITE);
		gameTowerBuyMapPositionPanel.add(gameTowerBuyMapPositionYLabel);

		gameTowerBuyMapPosition_YSpinner = new JSpinner();
		gameTowerBuyMapPosition_YSpinner.setModel(new SpinnerNumberModel(1, 1, 9, 1));
		gameTowerBuyMapPosition_YSpinner.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gameTowerBuyMapPositionPanel.add(gameTowerBuyMapPosition_YSpinner);

		gameTowerBuyMapPositionXLabel = new JLabel("X: ");
		gameTowerBuyMapPositionXLabel.setForeground(Color.WHITE);
		gameTowerBuyMapPositionXLabel.setBackground(Color.BLACK);
		gameTowerBuyMapPositionXLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gameTowerBuyMapPositionPanel.add(gameTowerBuyMapPositionXLabel);

		gameTowerBuyMapPosition_XSpinner = new JSpinner();
		gameTowerBuyMapPosition_XSpinner.setModel(new SpinnerNumberModel(1, 1, 9, 1));
		gameTowerBuyMapPosition_XSpinner.setForeground(Color.WHITE);
		gameTowerBuyMapPosition_XSpinner.setBackground(Color.WHITE);
		gameTowerBuyMapPosition_XSpinner.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gameTowerBuyMapPositionPanel.add(gameTowerBuyMapPosition_XSpinner);

		gameTowerBuyChosenInfoPanel = new JPanel();
		gameTowerBuyChosenInfoPanel.setBackground(Color.WHITE);
		gameTowerBuyMapChosenSplitPanel.setRightComponent(gameTowerBuyChosenInfoPanel);
		gameTowerBuyChosenInfoPanel.setLayout(new BorderLayout(0, 0));

		gameTowerBuyChosenList = new JList();
		gameTowerBuyChosenList.setModel(new AbstractListModel() {
			String[] values = new String[] { "Towers:" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		gameTowerBuyChosenList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gameTowerBuyChosenInfoPanel.add(gameTowerBuyChosenList);

		gameTowerBuyChosenResponseLabel = new JLabel("_ERROR_");
		gameTowerBuyChosenResponseLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gameTowerBuyChosenInfoPanel.add(gameTowerBuyChosenResponseLabel, BorderLayout.SOUTH);

		gameBuyEnemiesPanel = new JPanel();
		switchGamePanel.add(gameBuyEnemiesPanel, "gameBuyEnemiesPanel");
		gameBuyEnemiesPanel.setLayout(new BorderLayout(0, 0));

		gameEnemyBuyTextPanel = new JPanel();
		gameBuyEnemiesPanel.add(gameEnemyBuyTextPanel, BorderLayout.NORTH);

		gameEnemyBuyTextLabel = new JLabel("Buy Enemy : ");
		gameEnemyBuyTextLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gameEnemyBuyTextPanel.add(gameEnemyBuyTextLabel);

		gameEnemyBuyTextMoneyLabel = new JLabel("               Money: ");
		gameEnemyBuyTextMoneyLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gameEnemyBuyTextPanel.add(gameEnemyBuyTextMoneyLabel);

		gameEnemyBuyTextHealthLabel = new JLabel("               Health:");
		gameEnemyBuyTextHealthLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gameEnemyBuyTextPanel.add(gameEnemyBuyTextHealthLabel);

		gameEnemyBuyChosenPanel = new JPanel();
		gameBuyEnemiesPanel.add(gameEnemyBuyChosenPanel, BorderLayout.CENTER);
		gameEnemyBuyChosenPanel.setLayout(new BorderLayout(0, 0));

		gameEnemyBuyChosenList = new JList();
		gameEnemyBuyChosenList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gameEnemyBuyChosenList.setModel(new AbstractListModel() {
			String[] values = new String[] { "Next Wave : " };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		gameEnemyBuyChosenPanel.add(gameEnemyBuyChosenList);

		gameEnemyBuyChosenResponseLabel = new JLabel("_ERROR_");
		gameEnemyBuyChosenResponseLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gameEnemyBuyChosenPanel.add(gameEnemyBuyChosenResponseLabel, BorderLayout.SOUTH);

		gameEnemyBuyOptionsPanel = new JPanel();
		gameBuyEnemiesPanel.add(gameEnemyBuyOptionsPanel, BorderLayout.SOUTH);
		gameEnemyBuyOptionsPanel.setLayout(new BorderLayout(0, 0));

		gameEnemyBuyOptionsTypesPanel = new JPanel();
		gameEnemyBuyOptionsPanel.add(gameEnemyBuyOptionsTypesPanel, BorderLayout.CENTER);

		gameEnemyBuyOptions_Enemy_1_Button = new JButton("E-Type 1");
		gameEnemyBuyOptions_Enemy_1_Button.setToolTipText(
				"\t\tEnemy Weak:\r\n\r\nCost: 50\t\t\tLife: 10\r\n\r\n\t\t\t\t\tDamage: 1\r\n\r\n\t\t\t\t\tSpeed: 1 sec");
		gameEnemyBuyOptionsTypesPanel.add(gameEnemyBuyOptions_Enemy_1_Button);
		gameEnemyBuyOptions_Enemy_1_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chosenEnemyType = 1;
				buyEnemy();
			}
		});
		gameEnemyBuyOptions_Enemy_1_Button.setFont(new Font("Tahoma", Font.PLAIN, 14));

		gameEnemyBuyOptions_Enemy_2_Button = new JButton("E-Type 2");
		gameEnemyBuyOptions_Enemy_2_Button.setToolTipText(
				"\t\tEnemy Medium:\r\n\r\nCost: 100\t\t\tLife: 15\r\n\r\n\t\t\t\t\tDamage: 2\r\n\r\n\t\t\t\t\tSpeed: 2 sec");
		gameEnemyBuyOptionsTypesPanel.add(gameEnemyBuyOptions_Enemy_2_Button);
		gameEnemyBuyOptions_Enemy_2_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chosenEnemyType = 2;
				buyEnemy();
			}
		});
		gameEnemyBuyOptions_Enemy_2_Button.setFont(new Font("Tahoma", Font.PLAIN, 14));

		gameEnemyBuyOptions_Enemy_3_Button = new JButton("E-Type 3");
		gameEnemyBuyOptions_Enemy_3_Button.setToolTipText(
				"\t\tEnemy Strong:\r\n\r\nCost: 250\t\t\tLife: 20\r\n\r\n\t\t\t\t\tDamage: 5\r\n\r\n\t\t\t\t\tSpeed: 4 sec");
		gameEnemyBuyOptionsTypesPanel.add(gameEnemyBuyOptions_Enemy_3_Button);
		gameEnemyBuyOptions_Enemy_3_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chosenEnemyType = 3;
				buyEnemy();
			}
		});
		gameEnemyBuyOptions_Enemy_3_Button.setFont(new Font("Tahoma", Font.PLAIN, 14));

		gameEnemyBuyOptionsConfirmPanel = new JPanel();
		gameEnemyBuyOptionsPanel.add(gameEnemyBuyOptionsConfirmPanel, BorderLayout.EAST);

		gameEnemyBuyOptionsConfirm = new JButton("Confirm");
		gameEnemyBuyOptionsConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				sendToServer(Protocol.CS_READY_ENEMIESPURCHASED);
			}
		});
		gameEnemyBuyOptionsConfirm.setFont(new Font("Tahoma", Font.PLAIN, 30));
		gameEnemyBuyOptionsConfirm.setBackground(Color.BLACK);
		gameEnemyBuyOptionsConfirmPanel.add(gameEnemyBuyOptionsConfirm);

		gameBuyWaitPanel = new JPanel();
		switchGamePanel.add(gameBuyWaitPanel, "gameBuyWaitPanel");

		gameBuyWaitingLabel = new JLabel("Waiting...");
		gameBuyWaitingLabel.setBackground(Color.BLACK);
		gameBuyWaitingLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
		gameBuyWaitPanel.add(gameBuyWaitingLabel);

		gamePlayPanel = new JPanel();
		switchGamePanel.add(gamePlayPanel, "gamePlayPanel");
		gamePlayPanel.setLayout(new BorderLayout(0, 0));

		gamePlayMapPanel = new JPanel();
		gamePlayPanel.add(gamePlayMapPanel);
		gamePlayMapPanel.setBackground(Color.ORANGE);
		gamePlayMapPanel.setLayout(new GridLayout(6, 6, 1, 1));

		gamePlayHealthPanel = new JPanel();
		gamePlayPanel.add(gamePlayHealthPanel, BorderLayout.NORTH);
		gamePlayHealthPanel.setLayout(new BorderLayout(0, 0));

		gamePlayHealthOwnLabel = new JLabel("Your Health: ");
		gamePlayHealthOwnLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gamePlayHealthPanel.add(gamePlayHealthOwnLabel, BorderLayout.WEST);

		gamePlayHealthOtherLabel = new JLabel("Other Health: ");
		gamePlayHealthOtherLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gamePlayHealthPanel.add(gamePlayHealthOtherLabel, BorderLayout.EAST);

		gameEndScreenPanel = new JPanel();
		switchGamePanel.add(gameEndScreenPanel, "gameEndScreenPanel");
		gameEndScreenPanel.setLayout(new BorderLayout(0, 0));

		gameEndScreenLevelPanel = new JPanel();
		gameEndScreenPanel.add(gameEndScreenLevelPanel, BorderLayout.NORTH);

		gameEndScreenLevelLabel = new JLabel("Level: ");
		gameEndScreenLevelLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gameEndScreenLevelPanel.add(gameEndScreenLevelLabel);

		gameEndScreenThisPlayer_VerticalBox = Box.createVerticalBox();
		gameEndScreenPanel.add(gameEndScreenThisPlayer_VerticalBox, BorderLayout.WEST);

		gameEndScreenThisPlayer_PlacingLabel = new JLabel("_PLACE_");
		gameEndScreenThisPlayer_PlacingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		gameEndScreenThisPlayer_VerticalBox.add(gameEndScreenThisPlayer_PlacingLabel);
		gameEndScreenThisPlayer_PlacingLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));

		gameEndScreenThisPlayer_PlacingName_VerticalStrut = Box.createVerticalStrut(30);
		gameEndScreenThisPlayer_VerticalBox.add(gameEndScreenThisPlayer_PlacingName_VerticalStrut);

		gameEndScreenThisPlayer_NameLabel = new JLabel("_USERNAME_");
		gameEndScreenThisPlayer_NameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		gameEndScreenThisPlayer_VerticalBox.add(gameEndScreenThisPlayer_NameLabel);
		gameEndScreenThisPlayer_NameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));

		gameEndScreenThisPlayer_NameHealth_VerticalStrut = Box.createVerticalStrut(30);
		gameEndScreenThisPlayer_VerticalBox.add(gameEndScreenThisPlayer_NameHealth_VerticalStrut);

		gameEndScreenThisPlayer_HealthLabel = new JLabel("_HEALTH_");
		gameEndScreenThisPlayer_HealthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		gameEndScreenThisPlayer_VerticalBox.add(gameEndScreenThisPlayer_HealthLabel);
		gameEndScreenThisPlayer_HealthLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));

		gameEndScreenThisPlayer_HealthMoney_VerticalStrut = Box.createVerticalStrut(30);
		gameEndScreenThisPlayer_VerticalBox.add(gameEndScreenThisPlayer_HealthMoney_VerticalStrut);

		gameEndScreenThisPlayer_MoneyLabel = new JLabel("_Money_");
		gameEndScreenThisPlayer_MoneyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		gameEndScreenThisPlayer_VerticalBox.add(gameEndScreenThisPlayer_MoneyLabel);
		gameEndScreenThisPlayer_MoneyLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));

		gameEndScreenThisPlayer_MoneyEnemy_VerticalStrut = Box.createVerticalStrut(30);
		gameEndScreenThisPlayer_VerticalBox.add(gameEndScreenThisPlayer_MoneyEnemy_VerticalStrut);

		gameEndScreenThisPlayer_EnemyLabel = new JLabel("_ENEMY_");
		gameEndScreenThisPlayer_EnemyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		gameEndScreenThisPlayer_VerticalBox.add(gameEndScreenThisPlayer_EnemyLabel);
		gameEndScreenThisPlayer_EnemyLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));

		gameEndScreenThisPlayer_EnemyTower_VerticalStrut = Box.createVerticalStrut(30);
		gameEndScreenThisPlayer_VerticalBox.add(gameEndScreenThisPlayer_EnemyTower_VerticalStrut);

		gameEndScreenThisPlayer_TowerLabel = new JLabel("_TOWER_");
		gameEndScreenThisPlayer_TowerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		gameEndScreenThisPlayer_VerticalBox.add(gameEndScreenThisPlayer_TowerLabel);
		gameEndScreenThisPlayer_TowerLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));

		gameEndScreenOtherPlayer_VerticalBox = Box.createVerticalBox();
		gameEndScreenPanel.add(gameEndScreenOtherPlayer_VerticalBox, BorderLayout.EAST);

		gameEndScreenOtherPlayer_PlacingLabel = new JLabel("_PLACE_");
		gameEndScreenOtherPlayer_PlacingLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gameEndScreenOtherPlayer_PlacingLabel.setAlignmentX(0.5f);
		gameEndScreenOtherPlayer_VerticalBox.add(gameEndScreenOtherPlayer_PlacingLabel);

		gameEndScreenOtherPlayer_PlacingName_VerticalStrut = Box.createVerticalStrut(30);
		gameEndScreenOtherPlayer_VerticalBox.add(gameEndScreenOtherPlayer_PlacingName_VerticalStrut);

		gameEndScreenOtherPlayer_NameLabel = new JLabel("_USERNAME_");
		gameEndScreenOtherPlayer_NameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gameEndScreenOtherPlayer_NameLabel.setAlignmentX(0.5f);
		gameEndScreenOtherPlayer_VerticalBox.add(gameEndScreenOtherPlayer_NameLabel);

		gameEndScreenOtherPlayer_NameHealth_VerticalStrut = Box.createVerticalStrut(30);
		gameEndScreenOtherPlayer_VerticalBox.add(gameEndScreenOtherPlayer_NameHealth_VerticalStrut);

		gameEndScreenOtherPlayer_HealthLabel = new JLabel("_HEALTH_");
		gameEndScreenOtherPlayer_HealthLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gameEndScreenOtherPlayer_HealthLabel.setAlignmentX(0.5f);
		gameEndScreenOtherPlayer_VerticalBox.add(gameEndScreenOtherPlayer_HealthLabel);

		gameEndScreenOtherPlayer_HealthMoney_VerticalStrut = Box.createVerticalStrut(30);
		gameEndScreenOtherPlayer_VerticalBox.add(gameEndScreenOtherPlayer_HealthMoney_VerticalStrut);

		gameEndScreenOtherPlayer_MoneyLabel = new JLabel("_Money_");
		gameEndScreenOtherPlayer_MoneyLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gameEndScreenOtherPlayer_MoneyLabel.setAlignmentX(0.5f);
		gameEndScreenOtherPlayer_VerticalBox.add(gameEndScreenOtherPlayer_MoneyLabel);

		gameEndScreenOtherPlayer_MoneyEnemy_VerticalStrut = Box.createVerticalStrut(30);
		gameEndScreenOtherPlayer_VerticalBox.add(gameEndScreenOtherPlayer_MoneyEnemy_VerticalStrut);

		gameEndScreenOtherPlayer_EnemyLabel = new JLabel("_ENEMY_");
		gameEndScreenOtherPlayer_EnemyLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gameEndScreenOtherPlayer_EnemyLabel.setAlignmentX(0.5f);
		gameEndScreenOtherPlayer_VerticalBox.add(gameEndScreenOtherPlayer_EnemyLabel);

		gameEndScreenOtherPlayer_EnemyTower_VerticalStrut = Box.createVerticalStrut(30);
		gameEndScreenOtherPlayer_VerticalBox.add(gameEndScreenOtherPlayer_EnemyTower_VerticalStrut);

		gameEndScreenOtherPlayer_TowerLabel = new JLabel("_TOWER_");
		gameEndScreenOtherPlayer_TowerLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gameEndScreenOtherPlayer_TowerLabel.setAlignmentX(0.5f);
		gameEndScreenOtherPlayer_VerticalBox.add(gameEndScreenOtherPlayer_TowerLabel);

		gameEndScreenText_VerticalBox = Box.createVerticalBox();
		gameEndScreenPanel.add(gameEndScreenText_VerticalBox, BorderLayout.CENTER);

		gameEndScreenText_Player_VerticalStrut = Box.createVerticalStrut(60);
		gameEndScreenText_VerticalBox.add(gameEndScreenText_Player_VerticalStrut);

		gameEndScreenText_PlayerLabel = new JLabel("Player");
		gameEndScreenText_PlayerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		gameEndScreenText_VerticalBox.add(gameEndScreenText_PlayerLabel);
		gameEndScreenText_PlayerLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));

		gameEndScreenText_PlayerHealth_VerticalStrut = Box.createVerticalStrut(30);
		gameEndScreenText_VerticalBox.add(gameEndScreenText_PlayerHealth_VerticalStrut);

		gameEndScreenText_HealthLabel = new JLabel("Health");
		gameEndScreenText_HealthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		gameEndScreenText_HealthLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gameEndScreenText_VerticalBox.add(gameEndScreenText_HealthLabel);

		gameEndScreenText_HealthMoney_VerticalStrut = Box.createVerticalStrut(30);
		gameEndScreenText_VerticalBox.add(gameEndScreenText_HealthMoney_VerticalStrut);

		gameEndScreenText_MoneyLabel = new JLabel("Money");
		gameEndScreenText_MoneyLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gameEndScreenText_MoneyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		gameEndScreenText_VerticalBox.add(gameEndScreenText_MoneyLabel);

		gameEndScreenText_MoneyEnemy_VerticalStrut = Box.createVerticalStrut(30);
		gameEndScreenText_VerticalBox.add(gameEndScreenText_MoneyEnemy_VerticalStrut);

		gameEndScreenText_EnemyLabel = new JLabel("Enemies Killed");
		gameEndScreenText_EnemyLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gameEndScreenText_EnemyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		gameEndScreenText_VerticalBox.add(gameEndScreenText_EnemyLabel);

		gameEndScreenText_EnemyTower_VerticalStrut = Box.createVerticalStrut(30);
		gameEndScreenText_VerticalBox.add(gameEndScreenText_EnemyTower_VerticalStrut);

		gameEndScreenText_TowerLabel = new JLabel("Towers Placed");
		gameEndScreenText_TowerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		gameEndScreenText_TowerLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gameEndScreenText_VerticalBox.add(gameEndScreenText_TowerLabel);

		gameEndScreenExitPanel = new JPanel();
		gameEndScreenPanel.add(gameEndScreenExitPanel, BorderLayout.SOUTH);

		gameEndScreenExitButton = new JButton("EXIT");
		gameEndScreenExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		gameEndScreenExitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				sendToServer(Protocol.CS_EXIT_ENDSCREEN);
			}
		});
		gameEndScreenExitButton.setBackground(Color.DARK_GRAY);
		gameEndScreenExitButton.setForeground(Color.RED);
		gameEndScreenExitButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gameEndScreenExitPanel.add(gameEndScreenExitButton);
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
		// this.clearGameTowerBuyChosenList();
		this.setErrorTowerBuy("");
	}

	public void switchPanelGameBuyEnemies() {
		CardLayout cl = (CardLayout) switchGamePanel.getLayout();
		cl.show(switchGamePanel, "gameBuyEnemiesPanel");
		this.clearGameEnemyBuyChosenList();
		this.setErrorEnemyBuy("");
	}

	public void clearGameEnemyBuyChosenList() {
		DefaultListModel<String> dListModel = new DefaultListModel<String>();
		dListModel.addElement("Next Wave : ");
		gameEnemyBuyChosenList.setModel(dListModel);
	}

	public void clearGameTowerBuyChosenList() {
		DefaultListModel<String> dListModel = new DefaultListModel<String>();
		dListModel.addElement("Towers:");
		gameTowerBuyChosenList.setModel(dListModel);
	}

	public void switchPanelGameBuyWait() {
		CardLayout cl = (CardLayout) switchGamePanel.getLayout();
		cl.show(switchGamePanel, "gameBuyWaitPanel");
	}

	public void switchPanelGamePlay() {
		CardLayout cl = (CardLayout) switchGamePanel.getLayout();
		cl.show(switchGamePanel, "gamePlayPanel");
	}

	public void switchPanelGameEndScreen() {
		CardLayout cl = (CardLayout) switchGamePanel.getLayout();
		cl.show(switchGamePanel, "gameEndScreenPanel");
	}

	public void createMapLayout(int height, int length, String[][] tileTypes) {
		gamePlayMapPanel.setLayout(new GridLayout(height, length, 0, 0));
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

	public void setTowerBuyModelYSpinner(int maximum) {
		gameTowerBuyMapPosition_YSpinner.setModel(new SpinnerNumberModel(1, 1, maximum, 1));
	}

	public void setTowerBuyModelXSpinner(int maximum) {
		gameTowerBuyMapPosition_XSpinner.setModel(new SpinnerNumberModel(1, 1, maximum, 1));
	}

	public void setMapName(String pMapName) {
		this.readyPlayerMap_Name_Label.setText(pMapName);
	}

	public void setMapPicture(String pMapPictureName) {
		readyPlayerMap_Picture_Label.setText("");
		gameTowerBuyMapPictureLabel.setText("");
		BufferedImage pPicture = null;
		try {
			pPicture = ImageIO.read(getClass().getResource("/pictures/" + pMapPictureName + ".png"));
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

	public void setPasswordResponseLabelText(String pString) {
		this.passwordResponselabel.setText(pString);
	}

	public void buyTower() {
		int type = chosenTowerType;
		int posY = Integer.parseInt(gameTowerBuyMapPosition_YSpinner.getValue().toString());
		int posX = Integer.parseInt(gameTowerBuyMapPosition_XSpinner.getValue().toString());
		this.sendToServer(Protocol.CS_PURCHASE_TOWER + Protocol.SEPARATOR + posX + Protocol.SEPARATOR + posY
				+ Protocol.SEPARATOR + type);
	}

	public void addTowerChosen(String string) {
		ListModel<String> listModel = gameTowerBuyChosenList.getModel();
		ArrayList<String> elements = new ArrayList<String>();
		for (int i = 0; i < listModel.getSize(); i++) {
			elements.add(listModel.getElementAt(i));
		}
		DefaultListModel<String> dListModel = new DefaultListModel<String>();
		for (int j = 0; j < listModel.getSize(); j++) {
			dListModel.addElement(elements.get(j));
		}
		dListModel.addElement(string);
		gameTowerBuyChosenList.setModel(dListModel);
	}

	public void addEnemyChosen(String string) {
		ListModel<String> listModel = gameEnemyBuyChosenList.getModel();
		ArrayList<String> elements = new ArrayList<String>();
		for (int i = 0; i < listModel.getSize(); i++) {
			elements.add(listModel.getElementAt(i));
		}
		DefaultListModel<String> dListModel = new DefaultListModel<String>();
		for (int j = 0; j < listModel.getSize(); j++) {
			dListModel.addElement(elements.get(j));
		}
		dListModel.addElement(string);
		gameEnemyBuyChosenList.setModel(dListModel);
	}

	public void updateMoney(String string) {
		this.gameTowerBuyTextMoneyLabel.setText("				Money: " + string);
		this.gameEnemyBuyTextMoneyLabel.setText("				Money: " + string);
	}

	public void setErrorTowerBuy(String string) {
		this.gameTowerBuyChosenResponseLabel.setText(string);
	}

	public void setErrorEnemyBuy(String string) {
		this.gameEnemyBuyChosenResponseLabel.setText(string);
	}

	public void buyEnemy() {
		int type = chosenEnemyType;
		this.sendToServer(Protocol.CS_PURCHASE_ENEMY + Protocol.SEPARATOR + type);
	}

	public void setGameMapBoundries(int height, int length) {
		this.gamePlayMapPanel.setLayout(new GridLayout(height, length, 1, 1));
		this.buttonArray = new JButton[height][length];
	}

	public void setGameMapType(int posY, int posX, int type) {
		JButton newButton = new JButton();
		newButton.setBackground(generateGameMapButtonColor(type, newButton));
		newButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		buttonArray[posY][posX] = newButton;
		this.gamePlayMapPanel.add(newButton);
	}

	public void increaseEnemyCount(Integer x, Integer y) {
		// TODO: The Grid does not really save the location and error for not casting
		// panel to button on pc but not laptop
		JButton button = buttonArray[y][x];
		Integer number = Integer.parseInt(button.getText()) + 1;
		button.setText(number.toString());
	}

	public void decreaseEnemyCount(Integer x, Integer y) {
		// TODO: The Grid does not really save the location and error for not casting
		// panel to button on pc but not laptop
		JButton button = buttonArray[y][x];
		Integer number = Integer.parseInt(button.getText()) - 1;
		button.setText(number.toString());
	}

	private Color generateGameMapButtonColor(int type, JButton newButton) {
		Color color;
		switch (type) {
		case 0:
			color = Color.GREEN;
			break;
		case 1:
			color = Color.BLUE;
			newButton.setText("0");
			break;
		case 2:
			color = Color.ORANGE;
			newButton.setText("0");
			break;
		case 3:
			color = Color.RED;
			newButton.setText("0");
			break;
		case 4:
			color = Color.DARK_GRAY;
			break;
		default:
			color = Color.WHITE;
			break;
		}
		return color;
	}

	public void resetGameMap() {
		this.gamePlayMapPanel.removeAll();
	}

	public void updateOwnHealth(Integer health) {
		this.gameTowerBuyTextHealthLabel.setText("               Health: " + health);
		this.gameEnemyBuyTextHealthLabel.setText("               Health: " + health);
		this.gamePlayHealthOwnLabel.setText("Your Health: " + health.toString());
	}

	public void updateOtherHealth(Integer health) {
		this.gamePlayHealthOtherLabel.setText("Other Health: " + health.toString() + "        ");
	}

	public void updateEndScreenLevel(String levelCount) {
		this.gameEndScreenLevelLabel.setText("Level: " + levelCount);
	}

	public void updateEndScreenOwn(String ownState, String ownName, String own_Health, String ownMoney,
			String ownEnemies, String ownTowers) {
		this.gameEndScreenThisPlayer_PlacingLabel.setText(ownState);
		this.gameEndScreenThisPlayer_NameLabel.setText(ownName);
		this.gameEndScreenThisPlayer_HealthLabel.setText(own_Health);
		this.gameEndScreenThisPlayer_MoneyLabel.setText(ownMoney);
		this.gameEndScreenThisPlayer_EnemyLabel.setText(ownEnemies);
		this.gameEndScreenThisPlayer_TowerLabel.setText(ownTowers);
	}

	public void updateEndScreenOther(String otherState, String otherName, String other_Health, String otherMoney,
			String otherEnemies, String otherTowers) {
		this.gameEndScreenOtherPlayer_PlacingLabel.setText(otherState);
		this.gameEndScreenOtherPlayer_NameLabel.setText(otherName);
		this.gameEndScreenOtherPlayer_HealthLabel.setText(other_Health);
		this.gameEndScreenOtherPlayer_MoneyLabel.setText(otherMoney);
		this.gameEndScreenOtherPlayer_EnemyLabel.setText(otherEnemies);
		this.gameEndScreenOtherPlayer_TowerLabel.setText(otherTowers);
	}

	public void clearEndScreen() {
		this.gameEndScreenThisPlayer_PlacingLabel.setText("_PLACING_");
		this.gameEndScreenThisPlayer_NameLabel.setText("_NAME_");
		this.gameEndScreenThisPlayer_HealthLabel.setText("_HEALTH_");
		this.gameEndScreenThisPlayer_MoneyLabel.setText("_MONEY_");
		this.gameEndScreenThisPlayer_EnemyLabel.setText("_ENEMY_");
		this.gameEndScreenThisPlayer_TowerLabel.setText("_TOWER_");

		this.gameEndScreenOtherPlayer_PlacingLabel.setText("_PLACING_");
		this.gameEndScreenOtherPlayer_NameLabel.setText("_NAME_");
		this.gameEndScreenOtherPlayer_HealthLabel.setText("_HEALTH_");
		this.gameEndScreenOtherPlayer_MoneyLabel.setText("_MONEY_");
		this.gameEndScreenOtherPlayer_EnemyLabel.setText("_ENEMY_");
		this.gameEndScreenOtherPlayer_TowerLabel.setText("_TOWER_");
	}

	public void sendToServer(String pMessage) {
		this.myClient.send(pMessage);
	}
}
