import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Mining_fx extends BorderPane implements PaneState{
	
	private static final Font TITLE_FONT = new Font("Aspergit Light", 60);
	private static final int BUTTON_SIZE = 300;

	
	public Mining_fx() {
		addComponents();
	}
	
	
	@Override
	public void addComponents() {
		VBox vbox = new VBox();
		Label label = new Label("Mine the spam coin");
		label.setFont(TITLE_FONT);
		Button mine = new Button("MINE");
		mine.setFont(TITLE_FONT);
		mine.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
		vbox.getChildren().add(label);
		vbox.getChildren().add(new Separator());
		this.setTop(vbox);
		this.setCenter(mine);
		}
}
