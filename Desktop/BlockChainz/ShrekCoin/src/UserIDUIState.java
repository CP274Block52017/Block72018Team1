import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserIDUIState implements UIState {

	private JButton startButton;
	
	public UserIDUIState() {
		startButton = new JButton("Start");
	}
	@Override
	public void addComponents(UIFrame frame) {
		frame.setLayout(new GridLayout(3, 0));
		
		JLabel infoLabel = new JLabel("Your ID has been generated:",SwingConstants.CENTER);
		infoLabel.setFont(frame.getStandardizedFont(24));
		frame.add(infoLabel);	
		
		JLabel idLabel = new JLabel(IDGenerator.generateRandomID(), SwingConstants.CENTER);
		idLabel.setFont(frame.getStandardizedFont(32));
		frame.add(idLabel);

		startButton.setFont(frame.getStandardizedFont(32));
		startButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.setState(new KeyUIState());
					}
				});
		frame.add(startButton);
	}

}
