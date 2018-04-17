import java.io.*;

public class KeysIO{

  private String filename;
  private int[] keys;

  public KeysIO(String userID, int[] _keys){
    filename = userID;
    keys = _keys;
  }

  public void save(){
    try(PrintWriter out = new PrintWriter("/SpamCoin/" + filename + ".wlt");){
      out.println(keys[0]);
      out.println(keys[1]);
      out.println(keys[2]);
    }
    catch(FileNotFoundException e){
    }
  }

  public static int[] load(String filename){
    int[] loadedkeys = new int[3];

    try{
      FileReader filereader = new FileReader(filename);
      BufferedReader buffered = new BufferedReader(filereader);
      for(int i = 0; i < 3; i++){
        loadedkeys[i] = Integer.parseInt(buffered.readLine());
      }
    }catch(IOException e){

    }
    return loadedkeys;

  }
}
