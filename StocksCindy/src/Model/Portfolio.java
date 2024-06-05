package Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Portfolio  {

  private Map<String, Integer> inventory = new HashMap<>();

  private Map<String, HashMap<String, Integer>> stock = new HashMap<>();

  Portfolio() {
  }

  Portfolio(Map<String, Integer> inventory) {
    this.inventory = inventory;
  }

  public Map<String, Integer> addToPortfolio(String companyName, Integer share) {

    inventory.put(companyName, share);
    return inventory;

  }

  private Map<String, Integer> getInventory() {
    return this.inventory;
  }

  public void createNewPortfolio(String name) {

    String directory = "StocksCindy/UserPortfolio/";
    try {
      File fw = new File(directory + name + ".csv");
//      getPortfolio(inventory);
      FileWriter writer = new FileWriter(fw);
      for(Map.Entry<String, Integer> entry : inventory.entrySet()) {
        writer.write(entry.getKey() + "," + entry.getValue() + "\n");
      }

//      writer.write(this.inventory.toString()); //the company name and the shares/values call end of try
      System.out.println("You have successfully added a new portfolio!");
//      System.out.println(inventory.toString());
      writer.close();
    }
    catch (IOException e) {

    }
  }

//  public String getPortfolio(Map<String, Integer> inventory) {
//    String directory = "StocksCindy/UserPortfolio/";
//    for(Map.Entry<String, Integer> entry : inventory.entrySet()) {
//
//      entry.getKey();
//      entry.getValue();
//    }
//    return "";
//  }


}
