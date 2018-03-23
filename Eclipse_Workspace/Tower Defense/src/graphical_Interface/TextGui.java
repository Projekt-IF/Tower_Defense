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

public class TextGui extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblContent;
	private TD_Client myClient;

	/**
	 * Launch the application.
	 */
	public static void main (String [] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TextGui frame = new TextGui();
					frame.setVisible(true);
					TD_Client tdc = new TD_Client (args [0], Integer.parseInt(args [1]), frame);
					frame.setClient(tdc);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setClient (TD_Client pClient) {
		this.myClient = pClient;
	}


	/**
	 * Create the frame.
	 */
	public TextGui() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JButton ServerBtn = new JButton("Server");
		contentPane.add(ServerBtn, BorderLayout.WEST);
		
		JButton ClientBtn = new JButton("SendMessage");
		ClientBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				schicken ();
			}
		});
		contentPane.add(ClientBtn, BorderLayout.EAST);
		
		textField = new JTextField();
		contentPane.add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		
		lblContent = new JLabel("Content");
		contentPane.add(lblContent, BorderLayout.SOUTH);
	}
	
	public void ausgeben(String message) {
		this.lblContent.setText(message);
	}
	
	public void schicken () {
		String message = this.textField.getText();
		this.myClient.send(message);
	}


}
