package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * This represents a stock portfolio. A user can create, edit, and view the portfolio
 */
public class Portfolio {

  private Map<String, HashMap<String, Integer>> stock = new HashMap<>();
  private List<Map<String, Integer>> listInventories;
  private Scanner scanner;

  public Portfolio() {
    this.listInventories = new ArrayList<>();
  }

//  private List<String> deepCopy(List<String> original) {
//    List<String> copy = new ArrayList<>(original.size());
//    copy.addAll(original);
//    return copy;
//  }

  public List<Map<String, Integer>> getListInventories() {
    return this.listInventories;
  }


  /**
   * this takes in the company name and the share from the user input.
   * it puts it in a hashmap and returns it in an arraylist which is later.
   * used in the createNewPortfolio() method to create a new CSV file.
   *
   * @param companyName ticker of the company.
   * @param share       the shares the user input.
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
   *
   * @param name            is the name of the portfolio that the user inputs.
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
    } catch (IOException e) {
    }
  }

  /**
   * this returns the names of the existing portfolios.
   * this is called in the controller for the 2nd and 3rd bullet of menu.
   */
  public List<String> getNameFile() {
    String directory = "StocksCindy/UserPortfolio/";
    File direct = new File(directory);
    File[] files = direct.listFiles();
    List<String> names = new ArrayList<>();
      for (File file : files) {
        names.add(file.getName());
      }
    return names;
  }

  /**
   * when the user wants to add anything to an existing portfolio, this is called.
   * It takes in the array in the existing file and creates a new file.
   * with the data from the original data and the new data that the user is inputting.
   *
   * @param inputPrt        the name of the file that the user inputs.
   *                        this is called in the controller.
   * @param listInventories the arraylist of the new data to be added to the new file.
   */
  public void editExistingPortfolio(String inputPrt, List<Map<String, Integer>> listInventories) {
    String temp = "StocksCindy/UserPortfolio/temp.csv";
    String actualFile = "StocksCindy/UserPortfolio/" + inputPrt + ".csv";
    File oldFile = new File(actualFile);
    File newFile = new File(temp);
    try {
      FileWriter fw = new FileWriter(temp, true);
      BufferedWriter bw = new BufferedWriter(fw);
      PrintWriter pw = new PrintWriter(bw);
      scanner = new Scanner(new File(actualFile));

      String line;
      while (scanner.hasNext()) {
        line = scanner.nextLine();
        fw.write(listInventories.toString());
        fw.write(line); //this adds everything so far
      }

      scanner.close();
      pw.flush();
      pw.close();
      oldFile.delete();
      File dump = new File(actualFile);
      newFile.renameTo(dump);

      //if the name of the file cannot be found, it throws message.
    } catch (Exception e) {
      System.out.println("File cannot be found.");
      newFile.delete();
    }

  }
}
