import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	private Button mine;
	private VBox vbox;
	private Label label;
	private ApplicationUI frame;
	

	
	public Mining_fx(ApplicationUI _frame) {
		frame = _frame;
		addComponents();
		initializeButton();
	}
	
	
	@Override
	public void addComponents() {
		vbox = new VBox();
		label = new Label("Mine the spam coin");
		label.setFont(TITLE_FONT);
		mine = new Button("MINE");
		mine.setFont(TITLE_FONT);
		mine.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
		vbox.getChildren().add(label);
		vbox.getChildren().add(new Separator());
		this.setTop(vbox);
		this.setCenter(mine);
		}
	
	
	public void initializeButton() {
		mine.setOnAction(new EventHandler<ActionEvent>() {
			
			int[] keyPair = new KeyPairGenerator().generateNext();
			
			@Override
			public void handle(ActionEvent event) {
				Node localNode = new Node(keyPair[0], keyPair[1]);
				localNode.addToNetwork(Runner.GLOBAL_NETWORK);
				localNode.startWork();
				frame.initializeLocalNode(localNode);
			}
		});
	}
}
