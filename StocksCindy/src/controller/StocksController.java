package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.Model;
import view.StockProgramView;

import model.Portfolio;
import model.Stocks;
import model.StocksModel;
import view.View;

/**
 * this class represents the controller of the application.
 * it is connected the view where the user is able to type inputs.
 * and instructions.
 * It uses Scanner and Readable to read inputs.
 * and transmit outputs.
 */
public class StocksController implements Controller {
  private View view;
  private Readable readable;
  private Model model;

  private Scanner scan;
  private String name;

  /**
   * public constructor that takes in the model, view, and readable.
   * is called in the program display.
   * @param model model interface.
   * @param view view interface.
   * @param readable reads the user inputs.
   */
  public StocksController(Model model, View view, Readable readable) {
    //null exception?
    this.view = view;
    this.model = model;
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
    view.welcomeMessage();
    while (!quit) {
      view.inputNumber();
      String userNumber = scan.next();
      switch (userNumber) {
        // create new portfolio
        case "port-create":
          view.nameNew();
          name = scan.next();
          model.createPortfolio(name);

          int shares;
          String ticker;
          while (true) {
            view.tickerType();
            ticker = scan.next();
            if (ticker.equals("f")) {
              break;
            }
            view.stockAdd();
            shares = scan.nextInt();
            //adds to the list in portfolio
            model.managePortfolio(name, ticker, shares);
          }
          break;
          // make changes to existing portfolio
        case "port-manage":
          view.getNameOfFile(model.getPortfolioNames());
          view.namePort();
          name = scan.next(); // needs catcher for invalid names
          Portfolio port = new Portfolio(name, "StocksCindy/UserPortfolio", true);
          while (true) {
            view.tickerType();
            ticker = scan.next();
            if (ticker.equals("f")) {
              break;
            }
            view.stockAdd();
            shares = scan.nextInt();
            //adds to the list in portfolio
            model.managePortfolio(name, ticker, shares);
            view.success();
          }
          break;

          // view existing portfolios
        case "port-view":
          view.getNameOfFile(model.getPortfolioNames());
          view.namePort();
          name = scan.next(); // needs catcher for invalid names
          view.printPortfolio(model.getPortfolio(name));
          break;

        case "port-eval":
          view.getNameOfFile(model.getPortfolioNames());
          view.namePort();
          name = scan.next(); // needs catcher for invalid names
          view.getDateUser1();
          String date = scan.next();
          view.printPortValue(model.evaluatePortfolio(name, date));
          break;

          // examine gain/ loss
        case "stock-eval":

          //call method that returns the files
          view.getTicker();
          ticker = scan.next();
          view.getDateUser2();
          String date1 = scan.next();
          view.getDateUser3();
          String date2 = scan.next();
          view.printNetGain(model.evaluateStock(ticker, date1, date2), date1, date2);
          break;

          //  examine x-day moving average
        case "stock-avg":
          view.getTicker();
          ticker = scan.next();
          view.getDateUser2();;
          date = scan.next();
          view.getXDays();
          int lastXDays = scan.nextInt();
          view.movingAvg(model.movingAverage(ticker, date, lastXDays));
          break;

          // determine which days are x-day crossover
        case "stock-cross":
          view.getTicker();
          ticker = scan.next();
          view.getDateUser2();
          date = scan.next();
          view.getXDays();
          lastXDays = scan.nextInt();
          view.printCrossover(model.getCrossoverDays(ticker, date, lastXDays));
          break;
        case "quit":
          quit = true;
          break;
        case "menu":
          view.printMenu();
          break;
        default:
          view.invalidCommand();
      }
      break;
    }
    view.goodbye();
  }
}

