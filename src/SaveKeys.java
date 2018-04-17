import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class SaveKeys{

  private String filename;
  private int[] keys;

  public SaveKeys(String userID, int[] _keys){
    filename = userID;
    keys = _keys;
  }

  public void save(){
    try(PrintWriter out = new PrintWriter(filename + ".wlt");){
      out.println(keys[0]);
      out.println(keys[1]);
      out.println(keys[2]);
    }
    catch(FileNotFoundException e){
    }
  }
}
