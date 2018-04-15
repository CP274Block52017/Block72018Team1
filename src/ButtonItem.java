import javax.swing.ImageIcon;

public class ButtonItem {
	
	  private ImageIcon image;
	  private String name;
	  
	  public ButtonItem(String _name, ImageIcon _image) {
		  name = _name;
		  image = _image;
	  }
	  
	  public String getName() {
		  return name;
	  }
	  
	  public ImageIcon getImage() {
		  return image;
	  }

}
