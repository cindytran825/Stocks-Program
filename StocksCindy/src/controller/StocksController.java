package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

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

  private String day;
  private String userInput;
  private String date;
  private String startDate;
  private String endDate;
  private String ticker;
  private String name;
  private String month;
  private String shares;
  private String year;
  private String daysAfter;
  private boolean finish;

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
    view.welcomeMessage(); //TODO take menu out of welcome message

    while (!quit) {
      view.printMenu(); //TODO clarify TA
      view.userInput();
      userInput = scan.next();

      switch (userInput) {

        // create new portfolio
        case "port-create":
          portfolioCreate();
          break;

        // make changes to existing portfolio
        case "port-manage":
          portManage();
          break;

        case "port-list":
          portfolioList();
          break;

        case "port-view":
          portView();
          break;

        //====================================

        case "stock-view":
          view.printStockNames(model.getStockNames());
          view.getTicker();
          ticker = scan.next();
          if (checkFile(ticker, stockDirectory)) {
            break;
          }

          finish = false;
          while (!finish) {
            view.printStockViewMenu();
            view.userInput();
            userInput = scan.next();

            switch (userInput) {
              case "finish":
                finish = true;
                break;

              // get net growth of stock
              case "value":
                view.startDate();
                startDate = getDatePeriodPart();
                view.endDate();
                endDate = getDatePeriodPart();
                checkEvaluationDate();
                view.printNetGain(
                        model.evaluateStock(ticker, startDate, endDate),
                        startDate,
                        endDate
                );
                view.emptyLine();
                break;

              case "moving-avg":
                if (dateAndDaysAfter()) {
                  break;
                }
                view.movingAvg(
                        model.movingAverage(ticker, startDate, Double.parseDouble(daysAfter)));
                view.emptyLine();
                break;

              case "crossover":
                if (dateAndDaysAfter()) {
                  break;
                }
                view.printCrossover(
                        model.getCrossoverDays(ticker, startDate, Double.parseDouble(daysAfter)));
                view.emptyLine();
                break;

              case "bar-chart":
// TODO CINDY PLEASE DO THIS PART
                view.startDate();
                String startDate = getDatePeriodPart();
                if (checkDate(startDate)) { // TODO CHECK IF DATE IS VALID AND IF THERE'S DATA POINTS AVAILABLE
                  break;
                }
                view.endDate();
                String endDate = getDatePeriodPart();
                if (checkDate(endDate)) { // TODO CHECK IF DATE IS VALID AND IF THERE'S DATA POINTS AVAILABLE
                  break;
                }
                //////////////////////////
                view.emptyLine();
                break;


              default:
                view.invalidCommand();
            }
          }

          break;


        //====================================


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

  private boolean dateAndDaysAfter() {
    view.startDate();
    startDate = getDatePeriodPart();
    if (checkDate(startDate)) {
      return true;
    }
    view.getDays();
    daysAfter = scan.next();
    if (!model.checkIfWholeNumber(daysAfter)) {
      view.invalidNumber();
      return true;
    }
    return false;
  }

  private void portView() {
    if (selectPortfolio()) {
      return;
    }
    finish = false;
    while (!finish) {
      view.printPortfolioViewMenu();
      view.userInput();
      userInput = scan.next();

      switch (userInput) {
        case "finish":
          finish = true;
          break;
        // get portfolio composition
        case "composition":
          portfolioComposition();
          break;
        // get portfolio distribution
        case "distribution":
          portfolioDistribution();
          break;
        // get portfolio value
        case "value":
          portfolioValue();
          break;
        case "bar-chart": // TODO CINDY PLEASE DO THIS PART
          view.startDate();
          String startDate = getDatePeriodPart();
          if (checkDate(startDate)) { // TODO CHECK IF DATE IS VALID AND IF THERE'S DATA POINTS AVAILABLE
            break;
          }
          view.endDate();
          String endDate = getDatePeriodPart();
          if (checkDate(endDate)) { // TODO CHECK IF DATE IS VALID AND IF THERE'S DATA POINTS AVAILABLE
            break;
          }
          //////////////////////////
          break;

        default:
          view.invalidCommand();
      }
    }
  }

  private String getDatePeriodPart() {
    view.getDay();
    day = scan.next();
    view.getMonth();
    month = scan.next();
    view.getYear();
    year = scan.next();
    String startDate = String.format("%s-%s-%s", day, month, year);
    return startDate;
  }

  private void portfolioValue() {
    if (checkEvaluationDate()) {
      return;
    }
    view.print(model.evaluatePortfolio(name, date));
    view.emptyLine();
  }

  private void portfolioDistribution() {
    if (checkEvaluationDate()) {
      return;
    }
    view.print(model.getPortfolioDistribution(name, date));
    view.emptyLine();
  }

  private void portfolioComposition() {
    if (checkEvaluationDate()) {
      return;
    }
    view.print(model.getPortfolioComposition(name, date));
    view.emptyLine();
  }

  private boolean checkEvaluationDate() {
    view.getDay();
    day = scan.next();
    view.getMonth();
    month = scan.next();
    view.getYear();
    year = scan.next();
    date = String.format("%s-%s-%s", day, month, year);
    if (checkDate(date)) { // TODO CHECK THAT DATA ALSO EXISTA
      return true;
    }
    return false;
  }

  private boolean selectPortfolio() {
    view.getNameOfFile(model.getPortfolioNames());
    view.namePort();
    name = scan.next(); // needs catcher for invalid names
    if (checkFile(name, "StocksCindy/UserPortfolio")) {
      return true;
    }
    return false;
  }

  private void portManage() {

    view.getNameOfFile(model.getPortfolioNames());
    name = getName();

    // checks if file exist
    if (checkFile(name, "StocksCindy/UserPortfolio")) {
      return;
    }

    boolean finish = false;
    while (!finish) {
      view.printEditPortfolioMenu();
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
          view.getMonth();
          month = scan.next();
          view.getYear();
          year = scan.next();
          date = String.format("%s-%s-%s", day, month, year);

          // TODO CHECK FOR DATE VALIDITY (chronological AND available data point)

          model.buyStock(name, ticker, shares, date);
          view.emptyLine();
          // promts ticker
          // primpts shares
          // checks that shares not fractional
          // prompts date of transaction
          // checks date of transaction
          break;

        case "sell":
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
          } // TODO check if there's enough shares?

          // process dates // TODO CAN DEFINITELY REFACTOR
          view.getDay();
          day = scan.next();
          view.getMonth();
          month = scan.next();
          view.getYear();
          year = scan.next();
          date = String.format("%s-%s-%s", day, month, year);

          // TODO CHECK FOR DATE VALIDITY (chronological AND available data point)

          model.sellStock(name, ticker, shares, date);
          view.emptyLine();
          // promts ticker
          // primpts shares
          // checks that shares not fractional
          // checks that there's enough shares for transaction
          // prompts date of transaction
          // checks date of transaction
          break;

        case "balance":
          view.balanceFormat();

          // process dates // TODO CAN DEFINITELY REFACTOR
          view.getDay();
          day = scan.next();
          view.getMonth();
          month = scan.next();
          view.getYear();
          year = scan.next();
          date = String.format("%s-%s-%s", day, month, year);
          // TODO CHECK FOR DATE VALIDITY (chronological AND available data point)

          // TODO IS THIS AN ALRIGHT AMOUNT OF LOGIC IN CONTROLLER
          Set<String> stockNames = model.getPortfolioStocks(name).keySet();
          Map<String, Double> percentages = new HashMap<>();
          String percentage;
          for (String stockName : stockNames) {
            view.print(stockName + " percentage: ");
            percentage = scan.next();
            // TODO CHECK PERCENTAGE IS A NUMBER
            percentages.put(stockName, Double.parseDouble(percentage));
          }
          // TODO CHECKS IF PERCENTAGE 100%
          model.balance(date, name, percentages);
          view.balanceSuccess();
          view.emptyLine();
          break;
        // [display ticker name]: prompts percentage
        // checks if percentage adds up to 100%

        default:
          view.invalidCommand();
      }
    }
    view.terminating();
    view.emptyLine();
  }

  private void portfolioCreate() {
    String name;
    name = getName();
    model.createPortfolio(name);
    view.success();
    view.emptyLine();
  }

  private void portfolioList() {
    view.print(model.getPortfolioNames());
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

  //

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

