import java.io.*;

/**
 *  This class implements I/O for the keys
 */

public class KeysIO{

  private final static String FILENAME = "SPAMCOIN.wlt";

  /**
   * This method saves the user keys
   * @param keys
   */
  public static void save(int[] keys){
    try(PrintWriter out = new PrintWriter(FILENAME);){
      out.println(keys[0]);
      out.println(keys[1]);
      out.println(keys[2]);
    }
    catch(FileNotFoundException e){
    }
  }

  
  /**
   * This method returns the loaded keys as integer array
   * @return loaded keys
   */
  public static int[] load(){
    int[] loadedkeys = new int[3];

    try{
      FileReader filereader = new FileReader(FILENAME);
      BufferedReader buffered = new BufferedReader(filereader);
      for(int i = 0; i < 3; i++){
        loadedkeys[i] = Integer.parseInt(buffered.readLine());
      }
    }catch(IOException e){

    }
    return loadedkeys;

  }
}

