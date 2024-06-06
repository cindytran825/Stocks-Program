package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import view.StockProgramView;

import model.Portfolio;
import model.Stocks;
import model.StocksModel;

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
    StockProgramView s = new StockProgramView();
    boolean quit = false;
    String name = this.name;
    //if there is an existing portfolio
    boolean checkPortfolio = false;

    s.welcomeMessage();

    while (!quit) {
      s.inputNumber();
      String userNumber = scan.next();
      switch (userNumber) {

        // create new portfolio
        case "1":
          s.nameNew();
          name = scan.next();
          boolean yesAddStock = true;

          int shares = 0;
          String ticker = "";

          Portfolio p = new Portfolio();

          while (yesAddStock) {
            s.tickerType(); //wording
            ticker = scan.next();
            if (ticker.equals("f")) {
              break;
            }
            s.stockAdd();
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

          // add to existing portfolio
        case "2":
          Portfolio port1 = new Portfolio();
          port1.getNameFile();
          s.namePort();
          String inputPrt = scan.next();

          yesAddStock = true;
          while (yesAddStock) {
            s.tickerType(); //wording
            ticker = scan.next();
            if (ticker.equals("f")) {
              break;
            }
            s.stockAdd();
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

          // view existing portfolios
        case "3":
          try {
            Portfolio por = new Portfolio();
            por.getNameFile();
          } catch (Exception e) {
          }
          break;

          // examine gain/ loss
        case "4":
          getTickDates();
          try {
            scan.next();
            //call method that calculates the dates between
            //call the method that calculates gain/loss
          } catch (Exception e) {

          }
          break;

          //  examine x-day moving average
        case "5":
          getTickDates();
          try {
            scan.next(); //average methods
          } catch (Exception e) {

          }
          break;

          // determine which days are x-day corssover
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
        //default; default can call the view and just go like, hey tis isn't a command

      }
//      goodbye();
      break;

    }
    s.goodbye();
//    writeMessage("Input number: ");


  }

  /**
   * this reads the dates that the user inputs when.
   * they press 3, 4, or 5 from the menu.
   *
   * @return
   */
  private StocksModel getTickDates() {
    StockProgramView s = new StockProgramView();
    Scanner scan = new Scanner(readable);
    s.getTickerDate();
    String ticker = scan.next();
    s.getDateUser(); //wording
    String date1 = scan.next();
    //substring and save this date to call
    s.getDateUser();
    String date2 = scan.next();
    return new StocksModel();
  }



}

