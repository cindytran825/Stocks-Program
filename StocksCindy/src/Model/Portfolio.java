package Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Portfolio  {

  private Map<String, HashMap<String, Integer>> stock = new HashMap<>();
  List<Map<String, Integer>> listInventories;

  Portfolio() {
    this.listInventories = new ArrayList<>();
  }


  public List<Map<String, Integer>> addToPortfolio(String companyName, Integer share) {
    Map<String, Integer> inventory = new HashMap<>();
    List<Map<String, Integer>> listInventories = this.listInventories;

    inventory.put(companyName, share);
//      inventory.remove("=");
    listInventories.add(inventory);

    System.out.println(inventory);
    System.out.println(listInventories);
    return listInventories;
  }

  public void createNewPortfolio(String name, List<Map<String, Integer>> listInventories) {
    String directory = "StocksCindy/UserPortfolio/";
    try {
      File fw = new File(directory + name + ".csv");
      FileWriter writer = new FileWriter(fw);
      writer.write(listInventories.toString());
      writer.close();
      writer.write(listInventories.toString());
//      for(Map<String, Integer> map : listInventories) {
//        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//          writer.write(entry.getKey() + "," + entry.getValue());
//        }
//      }

      System.out.println("You have successfully added a new portfolio!");


    }
    catch (IOException e) {

    }
  }


}
