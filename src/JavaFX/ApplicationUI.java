import java.awt.GraphicsEnvironment;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ApplicationUI extends Application{
	
	private Button dashboard_btn;
	private Button transactions_btn;
	private Button mining_btn;
	private Button setting_btn;
	private double amount;
	private Label balance;
	private Label publicKey;
	private Scene curr_scene;
	private BorderPane border;
	private StackPane root;
	private Node localNode;
	private boolean firstTime = true;
	
	public static LocalNetwork GLOBAL_NETWORK = new LocalNetwork();
	
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 500;
	
	public static final Font TITLE_FONT = new Font("Aspergit Light", 60);

	

	@Override
	public void start(Stage primaryStage) throws Exception {
		border = new BorderPane();
		root = new StackPane();
		curr_scene = new Scene(border, WIDTH, HEIGHT);
		if(firstTime)
			border.setCenter(new InitialPage_fx(this));
		else
		{
			border.setCenter(new Dashboard_fx(this));
			setDashboardTemplate();
		}
		
		
			
		
		primaryStage.setTitle("Spam Coin Wallet");
		primaryStage.setResizable(true);
		primaryStage.setScene(curr_scene);
		primaryStage.show();

		
	}

	
	public void setDashboardTemplate()
	{
		amount = getLocalNode().getBalance();
		balance = new Label(amount + " Spam Coin");
		dashboard_btn = new Button("Dashboard");
		transactions_btn = new Button("Transactions");
		mining_btn = new Button("Mining");
		setting_btn = new Button("Setting");
		publicKey = new Label("Your Public Key: " + localNode.getPublicKey());
		
		ToolBar toolbar = new ToolBar(
				dashboard_btn,
				transactions_btn,
				mining_btn,
				setting_btn,
				new Separator(),
				balance,
				new Separator(),
				publicKey
				);
		
		initializeButtons(this);
		root.getChildren().add(toolbar);

		border.setTop(root);
	}
	
	public void updateBalance() {
		setDashboardTemplate();
	}
	
	public void initializeLocalNode(Node node) {
		if(localNode == null) {
			localNode = node;
		}
	}
	
	public Node getLocalNode() {
		return localNode;
	}
	
	/**
	 * Sets the tool bar buttons with functions
	 * @param curr_scene chages the 
	 * @param stage
	 */
	public void initializeButtons(ApplicationUI frame) {
		dashboard_btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				border.setCenter(new Dashboard_fx(frame));
			}
		});
		
		transactions_btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				border.setCenter(new Transaction_fx(frame));
			}
		});
		
		mining_btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				border.setCenter(new Mining_fx(frame));
			}
		});
		
		setting_btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				border.setCenter(new Setting_fx(frame));
			}
		});
	}	
	public void setPage(BorderPane b)
	{
		border.setCenter(b);
	}
	
	public static void main(String[] args) {
		Database.createDBAndUse();
		launch(args);
	}
}
