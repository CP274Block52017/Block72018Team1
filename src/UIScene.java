import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class UIScene extends Scene {
	
	Parent parent;
	
	public UIScene(Parent _parent, double width, double height) {
		super(_parent, width, height);
	}

	public void setState(Parent state) {
		this.parent = state;
		PaneState ps = (PaneState) state;
		ps.addComponents();
	}

}
