import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * a UIState that new users go through to get started with identification info
 * @author Case Regan
 *
 */
public class NewUserUIState implements UIState {
	private JButton joinButton;
	
	public NewUserUIState() {
		joinButton = new JButton("Join");
	}
	
	public void addComponents(UIFrame frame) {
		frame.setLayout(new GridLayout(2, 0));
		
		JLabel infoLabel = new JLabel("New users start here", SwingConstants.CENTER);
		infoLabel.setFont(frame.getStandardizedFont(48));
		frame.add(infoLabel);
		
		joinButton.setFont(frame.getStandardizedFont(32));
		joinButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.setState(new UserIDUIState());
					}
				});
		frame.add(joinButton);
	}
}
