package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * this represents a portfolio.
 * this is used when the user wants to create a new portfolio.
 */
public class Portfolio  {

  private Map<String, HashMap<String, Integer>> stock = new HashMap<>();
  List<Map<String, Integer>> listInventories;
  private Scanner scanner;

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
      System.out.println("You have successfully added a new portfolio!");
    }
    catch (IOException e) {
    }
  }

  /**
   * this returns the existing portfolios
   */
  public void getNameFile() {
    String directory = "StocksCindy/UserPortfolio/";
    File direct = new File(directory);
    File[] files = direct.listFiles();
    if (files != null) {
      for (File file : files) {
        System.out.println(file.getName());
      }
    }
    else {
      System.out.println("There are no existing portfolios.");
    }
  }

  public void editExistingPortfolio(String inputPrt, List<Map<String, Integer>> listInventories) {
    String temp = "StocksCindy/UserPortfolio/temp.csv";
    String actualFile = "StocksCindy/UserPortfolio/" + inputPrt;
    File oldFile = new File(actualFile);
    File newFile = new File(temp);
    try {
      FileWriter fw = new FileWriter(temp, true);
      BufferedWriter bw = new BufferedWriter(fw);
      PrintWriter pw = new PrintWriter(bw);
      scanner = new Scanner(new File(actualFile));
//      scanner.useDelimiter(",");

      String line;
      while (scanner.hasNext()) {
        line = scanner.nextLine();
        fw.write(listInventories.toString());
        // so if you want to add anything here to the file, you read everything here
        // and write everything it read
        fw.write(line); //this adds everything so far
      }

      //then we add user input here
    } catch (Exception e) {
      System.out.println("File cannot be found.");
    }
  }





}
