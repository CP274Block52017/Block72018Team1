import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class Mining_fx extends PaneState{
	
	public Mining_fx() {
		addComponents();
	}
	
	
	@Override
	public void addComponents() {
		
		Label label = new Label("Mine the spam coin");
		Button mine = new Button("MINE");
		this.getChildren().add(label);
		this.getChildren().add(mine);	
	}
}
