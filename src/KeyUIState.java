import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KeyUIState implements UIState {
	private JButton startButton;
	
	public KeyUIState() {
		startButton = new JButton("Start");
	}
	

	public void addComponents(UIFrame frame) {
		frame.setLayout(new GridLayout(3, 0));
				
		JLabel infoLabel = new JLabel("A new keypair has been generated for you", SwingConstants.CENTER);
		infoLabel.setFont(frame.getStandardizedFont(24));
		frame.add(infoLabel);	
		
		int[] keyPair = new KeyPairGenerator().generateNext();
		
		JLabel keyLabel = new JLabel("Your public key: " + keyPair[0], SwingConstants.CENTER);
		keyLabel.setFont(frame.getStandardizedFont(32));
		frame.add(keyLabel);
		
		startButton.setFont(frame.getStandardizedFont(32));
		startButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Node localNode = new Node(keyPair[0], keyPair[1]);
						localNode.addToNetwork(Runner.GLOBAL_NETWORK);
						localNode.startWork();
						frame.initializeLocalNode(localNode);
						frame.setState(new NewUserUIState());
					}
				});
		frame.add(startButton);
	}

}