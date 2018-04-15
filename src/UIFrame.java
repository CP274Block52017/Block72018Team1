import javax.swing.*;
import java.awt.*;

public class UIFrame extends JFrame {
	private Node localNode;
	
	public UIFrame() {
		super("SpamCoin");
		
		setPreferredSize(new Dimension(600, 400));
		this.add(new MenuBarPanel(this));
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setState(new NewUserUIState());
	}
	
	public void initializeLocalNode(Node node) {
		if(localNode == null) {
			localNode = node;
		}
	}
	
	public Node getLocalNode() {
		return localNode;
	}
	
	public Font getStandardizedFont(int size) {
		return new Font("Comic Sans MS", Font.PLAIN, size);
	}
	
	public void setState(UIState state) {
		getContentPane().removeAll();
		state.addComponents(this);
		revalidate();
		repaint();
	}
}
