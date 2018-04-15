
public abstract class ExistingUserUIState implements UIState {

	public abstract void addComponents(UIFrame frame);
	
	public void addMenuBar(UIFrame frame) {
		frame.add(new MenuBarPanel(frame));
	}

}
