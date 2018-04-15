import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MiningUIState implements UIState{
	

	@Override
	public void addComponents(UIFrame frame) {
		frame.setLayout(new GridLayout(0, 2));
		
		JLabel infoLabel = new JLabel("Mine Spam Coin!");
		infoLabel.setFont(frame.getStandardizedFont(24));
		frame.add(infoLabel);
		
		int[] keyPair = new KeyPairGenerator().generateNext();

		Icon icon = new ImageIcon("Spam_can.png");
		JButton mine = new JButton(icon);
		mine.setPreferredSize(new Dimension(200,190));
		mine.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Node localNode = new Node(keyPair[0], keyPair[1]);
					localNode.addToNetwork(Runner.GLOBAL_NETWORK);
					localNode.startWork();
					frame.initializeLocalNode(localNode);
			}
		});
		frame.add(mine);
	}

}
