import javax.swing.*;
import java.awt.*;

public class UIFrame extends JFrame {
	
	
	public UIFrame() {
		setPreferredSize(new Dimension(600, 400));
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void setStates(UIState state) {
		getContentPane().removeAll();
		state.addComponents(this);
	}
	
	public static void main(String[] args) {
		new UIFrame();
	}
}
