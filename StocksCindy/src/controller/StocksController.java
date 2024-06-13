package controller;

import java.util.Scanner;
import java.util.function.DoublePredicate;

import model.Model;
import view.View;

/**
 * this class represents the controller of the application.
 * it is connected the view where the user is able to type inputs.
 * and instructions.
 * It uses Scanner and Readable to read inputs.
 * and transmit outputs.
 */
public class StocksController implements Controller {
  private final View view;
  private final Readable readable;
  private final Model model;
  private final Scanner scan;
  private final String stockDirectory;
  private final String portfolioDirectory;

  /**
   * public constructor that takes in the model, view, and readable.
   * is called in the program display.
   *
   * @param model    model interface.
   * @param view     view interface.
   * @param readable reads the user inputs.
   */
  public StocksController(Model model, View view, Readable readable) {
    //null exception?
    this.view = view;
    this.model = model;
    this.readable = readable;
    this.scan = new Scanner(readable);
    this.stockDirectory = "StocksCindy/CSVFiles";
    this.portfolioDirectory = "StocksCindy/Portfolio";
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
    boolean quit = false;
    String name;
    String shares;
    String ticker;
    String userInput;
    String day;
    String month;
    String year;

    view.welcomeMessage(); //TODO take menu out of welcome message

    while (!quit) {
      view.printMenu(); //TODO clarify TA
//      view.inputNumber();
      userInput = scan.next();

      switch (userInput) {
        // create new portfolio
        case "port-create":
          portfolioCreate();
          break;
//          String shares;
//          String ticker;
//          while (true) {
//            view.tickerType();
//            ticker = scan.next();
//            if (ticker.equals("f")) {
//              break;
//            }
//            if (!model.checkIfFileExist("StocksCindy/CSVFiles/" + ticker + ".csv")) {
//              view.invalidStock();
//              break;
//            }
//            view.stockAdd();
//            shares = scan.next();
//            if (!model.checkIfNumber(shares)) {
//              view.invalidNumber();
//              break;
//            } else {
//              //TODO helper method for buy/sell fix in view
////              model.managePortfolio(name, ticker, Integer.parseInt(shares));
//            }
//          }
//          view.terminating();

        // make changes to existing portfolio
        case "port-manage":
          // TODO helper that gets buystock and sell
          // TODO REBALANCE in here
          view.getNameOfFile(model.getPortfolioNames());
          name = getName();

          // checks if file exist
          if (checkFile(name, "StocksCindy/UserPortfolio")) {
            break;
          }
          
          boolean finish = false;
          while (!finish) {
            view.editPortfolioMenu();
            view.userInput();
            userInput = scan.next();
            switch (userInput) {
              case "finish":
                finish = true;
                break;

              case "buy":
                // process ticker TODO CAN DEFINITELY REFACTOR THIS SECTION
                view.getTicker();
                ticker = scan.next();
                if (!model.checkIfFileExist(stockDirectory + "/" + ticker + ".csv")) {
                  view.invalidStock();
                  view.emptyLine();
                  break;
                }

                // process shares // TODO CAN DEFINITELY REFACTOR
                view.getShares();
                shares = scan.next();
                if (!model.checkIfWholeNumber(shares)) {
                  view.invalidShares();
                  view.emptyLine();
                  break;
                }

                // process dates // TODO CAN DEFINITELY REFACTOR
                view.getDay();
                day = scan.next();


                // promts ticker
                // primpts shares
                // checks that shares not fractional
                // prompts date of transaction
                // checks date of transaction
                break;

              case "sell":
                // promts ticker
                // primpts shares
                // checks that shares not fractional
                // checks that there's enough shares for transaction
                // prompts date of transaction
                // checks date of transaction
                break;
              case "balance":
                // [display ticker name]: prompts percentage
                // checks if percentage adds up to 100%
              default:
                view.invalidCommand();
            }
            
            
            
            view.tickerType();
            ticker = scan.next();
            if (ticker.equals("f")) {
              break;
            }
            if (!model.checkIfFileExist("StocksCindy/CSVFiles/" + ticker + ".csv")) {
              view.invalidStock();
              break;
            }
            view.stockAdd();
            shares = scan.next();
            view.getDateUser1();
            String date = scan.next();
            //adds to the list in portfolio
            if (!model.checkIfNumber(shares)) {
              view.invalidNumber();
              break;
            } else {
//              model.managePortfolio(name, ticker, Double.parseDouble(shares));
//              model.getBuyStock(name, ticker, Double.parseDouble(shares), date);

              //String name, String ticker, double share, String date
            }
          }
          view.terminating();
          view.emptyLine();
          break;

        // view existing portfolios
        //TODO composition
        case "port-view":
          view.getNameOfFile(model.getPortfolioNames());
          view.namePort();
          name = scan.next(); // needs catcher for invalid names
          if (checkFile(name, "StocksCindy/UserPortfolio")) {
            break;
          }
          view.print(model.getPortfolio(name));
          break;

        //TODO new command port-list does port-view

          // case"port-list"

        //TODO have the command port-view prompt user to get composition or distribution
        // overtime and get value in place of port-eval and put bar chart in

        //TODO put this into another case ? port-manage
        case "port-eval":
          view.getNameOfFile(model.getPortfolioNames());
          view.namePort();
          name = scan.next(); // needs catcher for invalid names
          if (checkFile(name, "StocksCindy/UserPortfolio")) {
            break;
          }

          view.getDateUser1();
          String date = scan.next();
          if (checkDate(date)) {
            break;
          }

          view.printPortValue(model.evaluatePortfolio(name, date));
          view.emptyLine();
          break;

          //TODO bar chart, examine gain/loss, stock-avg and cross
        // TODO put under stock-view in place of stock-eval
        // examine gain/ loss
        case "stock-eval":
          //call method that returns the files
          view.getTicker();
          ticker = scan.next();
          if (!model.checkIfFileExist("StocksCindy/CSVFiles/" + ticker + ".csv")) {
            view.invalidStock();
            view.terminating();
            view.emptyLine();
            break;
          }

          view.getDateUser2();
          String date1 = scan.next();
          if (checkDate(date1)) {
            break;
          }

          view.getDateUser3();
          String date2 = scan.next();
          if (checkDate(date2)) {
            break;
          }

          view.printNetGain(model.evaluateStock(ticker, date1, date2), date1, date2);
          view.emptyLine();
          break;

        //  examine x-day moving average
        case "stock-avg":
          view.getTicker();
          ticker = scan.next();
          if (!model.checkIfFileExist("StocksCindy/CSVFiles/" + ticker + ".csv")) {
            view.invalidStock();
            view.terminating();
            view.emptyLine();
            break;
          }

          view.getDateUser2();
          date = scan.next();
          if (checkDate(date)) {
            break;
          }

          view.getDays();
          String lastDays = scan.next();
          if (checkDays(lastDays)) {
            break;
          }

          view.movingAvg(model.movingAverage(ticker, date, Double.parseDouble(lastDays)));
          view.emptyLine();
          break;

        // determine which days are x-day crossover
        case "stock-cross":
          view.getTicker();
          ticker = scan.next();
          if (!model.checkIfFileExist("StocksCindy/CSVFiles/" + ticker + ".csv")) {
            view.invalidStock();
            view.terminating();
            view.emptyLine();
            break;
          }

          view.getDateUser2();
          date = scan.next();
          if (checkDate(date)) {
            break;
          }

          view.getDays();
          lastDays = scan.next();
          if (checkDays(lastDays)) {
            break;
          }

          view.printCrossover(model.getCrossoverDays(ticker, date, Double.parseDouble(lastDays)));
          view.emptyLine();
          break;

        // view the list of stocks on file
        case "stock-list":
          view.printStockNames(model.getStockNames());
          view.emptyLine();
          break;

          // to download / update stock information
        case "stock-download":
          view.getTicker();
          ticker = scan.next();
          model.generateStock(ticker);
          view.printSuccessAddStock(ticker);
          view.emptyLine();
          break;

          // upload stock csv file to add
        case "stock-upload":
          view.getPath();
          String path = scan.next();
          if (!model.checkIfFileExist(path)) {
            view.invalidFile();
            view.terminating();
            view.emptyLine();
            break;
          }

          view.getNameFile();
          ticker = scan.next();
          model.uploadStock(ticker, path);
          view.printSuccessAddStock(ticker);
          view.emptyLine();
          break;

//        case "eval-chart":
//          view.namePort();
//          name = scan.next();
//          view.getDateUser2();
//          String startDate = scan.next();
//          view.getDateUser3();
//          String endDate = scan.next();
//
//          view.returnBarChart(model.barChartInitialized(name, startDate, endDate));
//          view.emptyLine();
//          break;
          //view.movingAvg(model.movingAverage(ticker, date, Double.parseDouble(lastDays)));

        case "quit":
          quit = true;
          break;
        case "menu":
          view.printMenu();
          view.emptyLine();
          break;
        default:
          view.invalidCommand();
      }
    }
    view.goodbye();
  }

  private void portfolioCreate() {
    String name;
    name = getName();
    model.createPortfolio(name);
    view.success();
    view.emptyLine();
  }

  private String getName() {
    String name;
    view.newName();
    name = scan.next();
    return name;
  }

  private boolean checkDays(String lastDays) {
    if (!model.checkIfNumber(lastDays)) {
      view.invalidNumber();
      view.terminating();
      view.emptyLine();
      return true;
    }
    return false;
  }

  private boolean checkDate(String date1) {
    if (!model.checkIfDate(date1)) {
      view.invalidDate();
      view.terminating();
      view.emptyLine();
      return true;
    }
    return false;
  }

  private boolean checkFile(String ticker, String path) {
    if (!model.checkIfFileExist(path + "/" + ticker + ".csv")) {
      view.invalidFile();
      view.terminating();
      view.emptyLine();
      return true;
    }
    return false;
  }
}

