package Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * this class represents the controller of the application.
 * it is connected the view where the user is able to type inputs.
 * and instructions.
 * It uses Appendable and Readable to read inputs.
 * and transmit outputs.
 */
public class StocksController {
  private Stocks stock;
  private Appendable appendable;
  private Readable readable;

  private Scanner scan;
  private String name;

  /**
   * this constructor takes in Stocks, Appendable and Readable.
   *
   * @param stock      is the interface used to represent data associated with stocks.
   * @param appendable used to transmit output.
   * @param readable   used to read inputs.
   */
  public StocksController(Stocks stock, Appendable appendable, Readable readable) {
    //null exception?
    this.stock = stock;
    this.appendable = appendable;
    this.readable = readable;
  }

  private StocksController() {
    this.name = name;
    this.scan = scan;

  }

  /**
   * this is called in the view and is used to read.
   * the inputs that the user puts.
   * it also calls methods when there is a command from.
   * the user to display later in the view.
   *
   * @throws IllegalStateException is called when method isn't at an appropriate state.
   */
  public void goControl() throws IllegalStateException {
    Map<String, Integer> inventory = new HashMap<>();
    List<Map<String, Integer>> listInventories = new ArrayList<>();
    Scanner scan = new Scanner(readable);
    boolean quit = false;
    String name = this.name;
    //if there is an existing portfolio
    boolean checkPortfolio = false;

    this.welcomeMessage();

    while (!quit) {
      writeMessage("Input number: ");
      String userNumber = scan.next();
      switch (userNumber) {
        case "1":
          writeMessage("Name of new portfolio: ");
          name = scan.next();
          boolean yesAddStock = true;

          int shares = 0;
          String ticker = "";

          Portfolio p = new Portfolio();

          while (yesAddStock) {
            writeMessage("Enter Ticker (type 'f' to complete portfolio): "); //wording
            ticker = scan.next();
            if (ticker.equals("f")) {
              break;
            }
            writeMessage("How many shares do you want to add for this stock? ");
            shares = scan.nextInt();
            //adds to the list in portfolio
            listInventories = p.addToPortfolio(ticker, shares);
//            writeMessage("Do you want to add another stock? (type yes or no) ");
//            String userAddStock = scan.next();
//            if (userAddStock.equals("no")) {
//              yesAddStock = false;
//            }
          }

          Portfolio port = new Portfolio();
          port.createNewPortfolio(name, listInventories);

          checkPortfolio = true;
          //ask if they want to see the value of portfolio
          break;

        case "2":
          Portfolio port1 = new Portfolio();
          port1.getNameFile();
          writeMessage("Type just the NAME of the portfolio: ");
          String inputPrt = scan.next();

            yesAddStock = true;
            while (yesAddStock) {
              writeMessage("Enter Ticker (type 'f' to complete portfolio): "); //wording
              ticker = scan.next();
              if (ticker.equals("f")) {
                break;
              }
              writeMessage("How many shares do you want to add for this stock? ");
              shares = scan.nextInt();
              //adds to the list in portfolio
              listInventories = port1.addToPortfolio(ticker, shares);
//              writeMessage("Do you want to add another stock? (type 'yes' or 'no') ");
//              String userAddStock = scan.next();
//              if (userAddStock.equals("no")) {
//                yesAddStock = false;
//              }
            }
          port1.editExistingPortfolio(inputPrt, listInventories);
            //update the file
          break;
        case "3":
          try {
            Portfolio por = new Portfolio();
            por.getNameFile();
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
//      goodbye();
      break;

    }
    goodbye();
//    writeMessage("Input number: ");


  }

  /**
   * this reads the dates that the user inputs when.
   * they press 3, 4, or 5 from the menu.
   *
   * @return
   */
  private StocksModel getTickDates() {
    Scanner scan = new Scanner(readable);
    writeMessage("Ticker: ");
    String ticker = scan.next();
    writeMessage("Type the first date (YYYY-MM-DD): "); //wording
    String date1 = scan.next();
    //substring and save this date to call
    writeMessage("Type the last date (YYYY-MM-DD): ");
    String date2 = scan.next();

    return new StocksModel();
  }

  /**
   * this writes the message.
   *
   * @param message a string.
   * @throws IllegalStateException when the input isn't a string.
   */
  private void writeMessage(String message) throws IllegalStateException {
    try {
      appendable.append(message);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  /**
   * this prints the menu, first thing user sees.
   *
   * @throws IllegalStateException when it's at an illegal state.
   */
  private void printMenu() throws IllegalStateException {
    // need to be able to upload their own stock data (csv file) or look up (this is sorta in between)
    // create portfolio
    // read portfolio
    // check stock gain or loss over a specified time period
    // x-day moving average for a specified date and specified value of x
    // determine which days are x-day crossovers for a specified date and specified value of x

    writeMessage("1. Create new portfolio" + System.lineSeparator());
    writeMessage("2. Add to existing portfolio" + System.lineSeparator());
    writeMessage("3. View existing portfolios" + System.lineSeparator());
    writeMessage("4. Examine gain/loss" + System.lineSeparator());
    writeMessage("5. Examine x-day move average" + System.lineSeparator());
    writeMessage("6. Determine which days are x-day crossover" + System.lineSeparator());
    writeMessage("'quit' to quit" + System.lineSeparator());
    writeMessage("Enter in a number corresponding to the action you'd like to take!"
            + System.lineSeparator());

  }

  /**
   * this prints the welcome message.
   *
   * @throws IllegalStateException when it's at an illegal state.
   */
  private void welcomeMessage() throws IllegalStateException {
    writeMessage("Welcome to the stocks program!" + System.lineSeparator());
    printMenu();
  }

  /**
   * this prints the goodbye message.
   *
   * @throws IllegalStateException when it's at an illegal state.
   */
  private void goodbye() throws IllegalStateException {
    writeMessage("Thank you for using our program!" + System.lineSeparator());

  }

}

