package Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * this represents a portfolio.
 * this is used when the user wants to create a new portfolio.
 */
public class Portfolio  {

  private Map<String, HashMap<String, Integer>> stock = new HashMap<>();
  List<Map<String, Integer>> listInventories;

  Portfolio() {
    this.listInventories = new ArrayList<>();
  }


  /**
   * this takes in the company name and the share from the user input.
   * it puts it in a hashmap and returns it in an arraylist which is later.
   * used in the createNewPortfolio() method to create a new CSV file.
   * @param companyName ticker of the company.
   * @param share the shares the user input.
   * @return a list.
   */
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

  /**
   * this creates the portfolio.
   * it puts it in the "UserPortfolio" directory.
   * @param name is the name of the portfolio that the user inputs.
   * @param listInventories this is the list of the current inventories in the portfolio.
   */
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
