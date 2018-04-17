
import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class InitialPage_fx extends BorderPane implements PaneState {
	private Button joinButton;
	private ApplicationUI frame;
	private boolean isFirstUser;
	private File checker;
	
	private static int BUTTON_WIDTH = 300;
	private static int BUTTON_HEIGHT = 50;
	private static final Font TITLE_FONT = new Font("Aspergit Light", 60);
	
	public InitialPage_fx(ApplicationUI _frame) {
		checker = new File("./SPAMCOIN.wlt");
		frame = _frame;
		isFirstUser = !checker.exists();
		joinButton = new Button("Start");
		joinButton.setFont(TITLE_FONT);
		joinButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		addComponents();
		initializeButtons();
		
	}
	
	public void addComponents() {
		VBox vbox = new VBox();
		
		vbox.getChildren().add(joinButton);
		
		this.setCenter(vbox);
		this.setCenter(joinButton);
	}
	
	public void initializeButtons() {
		if(isFirstUser) {
			joinButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					int[] keyPair = new KeyPairGenerator().generateNext();
					KeysIO.save(keyPair);
					Node localNode = new Node(keyPair[0], keyPair[1]);
					localNode.addToNetwork(ApplicationUI.GLOBAL_NETWORK);
					frame.initializeLocalNode(localNode);
					NewUser_fx page = new NewUser_fx(frame);
					frame.setPage(page);
				}
			});
		}else {
			joinButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					int[] keyPair = KeysIO.load();
					Node localNode = new Node(keyPair[0], keyPair[1]);
					localNode.addToNetwork(ApplicationUI.GLOBAL_NETWORK);
					frame.initializeLocalNode(localNode);
					frame.setDashboardTemplate();
					frame.setPage(new Dashboard_fx(frame));
				}
			});
		}
	}

}
