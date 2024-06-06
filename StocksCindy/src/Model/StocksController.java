package Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class StocksController {
  private Stocks stock;
  //  private String date;
  private Appendable appendable;
  private Readable readable;

  private String output;
  private Scanner scan;


  public StocksController(Stocks stock, Appendable appendable, Readable readable) {
    //null exception?
    this.stock = stock;
    this.appendable = appendable;
    this.readable = readable;
  }

  private StocksController() {
    this.output = output;
    this.scan = scan;
  }


  public void goControl() throws IllegalStateException {
    Map<String, Integer> inventory = new HashMap<>();
    List<Map<String, Integer>> listInventories = new ArrayList<>();
    Scanner scan = new Scanner(readable);
    boolean quit = false;
    //if there is an existing portfolio
    boolean checkPortfolio = false;

    this.welcomeMessage();

    while (!quit) {
      writeMessage("Input number: ");
      String userNumber = scan.next();
      switch (userNumber) {
        case "1":
          writeMessage("Name of new portfolio: ");
          String name = scan.next();
          boolean yesAddStock = true;

          int shares = 0;
          String ticker = "";

          Portfolio p = new Portfolio();

          while (yesAddStock) {
            writeMessage("Ticker: "); //wording
            ticker = scan.next();

            writeMessage("How many shares do you want to add for this stock?");
            shares = scan.nextInt();
//            this.output = output.concat(ticker + " " + shares + "\n");

            //adds to the list in portfolio
            listInventories = p.addToPortfolio(ticker, shares);

            writeMessage("Do you want to add another stock? (type yes or no)");
            String userAddStock = scan.next();
            if (userAddStock.equals("no")) {
              yesAddStock = false;
            }
          }
          System.out.println("output: " + this.output);
//          try {
          Portfolio port = new Portfolio();
          port.createNewPortfolio(name, listInventories);

//          } catch (IOException e) {
//            writeMessage("Error:" + e.getMessage());
//          }

          checkPortfolio = true;
          //ask if they want to see the value of portfolio
          break;

        case "2":
          if (checkPortfolio) {
            writeMessage(""); //list of the portfolios
          } else {
            writeMessage("There are no existing portfolios"); //call the quit scene
          }
          yesAddStock = true;
          while (yesAddStock) {
            writeMessage("Ticker: "); //wording
            ticker = scan.next();

            writeMessage("How many shares do you want to add for this stock?");

            shares = scan.nextInt();

            this.output = output.concat(ticker + " " + shares + "\n");
            writeMessage("Do you want to add another stock? (type yes or no)");
            String userAddStock = scan.next();
            if (userAddStock.equals("no")) {
              yesAddStock = false;
            }
            //update the file
          }
          break;
        case "3":
          //menu of portfolio and name
          if (checkPortfolio) {
            writeMessage(""); //list of portfolios
          } else {
            writeMessage("There are no existing portfolios");
          }
          writeMessage("choose from existing file (#)"); //wording
          try {
            scan.next(); //.get the file
          } catch (Exception e) {
          }
          break;

        case "4":
          getTickDates();
          try {
            scan.next();
            //call method that calculates the dates between
            //call the method that calculates gain/loss
          } catch (Exception e) {

          }
          break;

        case "5":
          getTickDates();
          try {
            scan.next(); //average methods
          } catch (Exception e) {

          }
          break;
        case "6":
          getTickDates();
          try {
            scan.next(); //call method determines which days are x-day crossover
          } catch (Exception e) {

          }
          break;
        case "quit":
          quit = true;
          break;
        //default;

      }
      break;
    }
    goodbye();

  }


  private Stocks getTickDates(){
    Scanner scan = new Scanner(readable);
    writeMessage("Ticker:");
    String ticker = scan.next();
    writeMessage("Type the first date (YYYY-MM-DD): "); //wording
    String date1 = scan.next();
    //substring and save this date to call
    writeMessage("Type the last date (YYYY-MM-DD): ");
    String date2 = scan.next();

    return new StocksModel(ticker, date1, date2);
  }


  private void writeMessage(String message) throws IllegalStateException {
    try {
      appendable.append(message);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  //this should print the menu
  private void printMenu() throws IllegalStateException {
    // need to be able to upload their own stock data (csv file) or look up (this is sorta in between)
    // create portfolio
    // read portfolio
    // check stock gain or loss over a specified time period
    // x-day moving average for a specified date and specified value of x
    // determine which days are x-day crossovers for a specified date and specified value of x

    writeMessage("1. Create new portfolio" + System.lineSeparator());
    writeMessage("2. Add to existing portfolio" + System.lineSeparator());
    writeMessage("3. View existing portfolio" + System.lineSeparator());
    writeMessage("4. Examine gain/loss" + System.lineSeparator());
    writeMessage("5. Examine x-day move average" + System.lineSeparator());
    writeMessage("6. Determine which days are x-day crossover" + System.lineSeparator());
    writeMessage("'quit' to quit" + System.lineSeparator());
    writeMessage("What're you here for?" + System.lineSeparator());

  }

  //
  private void welcomeMessage() throws IllegalStateException {
    writeMessage("Welcome to the stocks program!" + System.lineSeparator());
    printMenu();
  }

  //would this return the menu again?? or just end
  private void goodbye() throws IllegalStateException {
    writeMessage("Thank you for using our program!" + System.lineSeparator());
//    printMenu();
  }

}

