package Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class StocksController {
  //private Stocks stock;
  private String date;
  private Appendable appendable;
  private Readable readable;


  StocksController(String date, Appendable appendable, Readable readable) {
    //null exception?
    this.date = date;
    this.appendable = appendable;
    this.readable = readable;
  }

  //ask to put the two dates
  //another screen with what they want to do with those dates
  //call on the methods that implement
  //portfolio, buying stocks

  //
  public void goControl() throws IllegalStateException {
    Scanner scan = new Scanner(readable);
    boolean quit = false;
    String date = this.date;
    boolean checkPortfolio = false;

    this.welcomeMessage();

    while(!quit) {
      writeMessage("What're you here for?");
      writeMessage("Input number: ");




      String userNumber = scan.next();
      switch (userNumber) {
        case "1":
          writeMessage("Name of new portfolio: ");
          try {
            String directory = "portfolio/";
            File fw = new File(directory + scan.next());
            FileWriter writer = new FileWriter(fw);
            //writer.write(output.toString()); //the company name and the shares/values call end of try
          }
          catch (IOException e){
          }
          writeMessage("How many shares?"); //wording
          try {
            int shareAmount = scan.nextInt();
          }
          catch (IllegalArgumentException e){
          }
          writeMessage("Ticker: ");
          try {
            //call method that generates the value
          }
          catch (IllegalArgumentException e){

          }
          //when it returns the portfolio
          checkPortfolio = true;
          break;
        case "2" :
          if (checkPortfolio) {
            writeMessage(""); //list of the portfolios
          }
          else {
            writeMessage("There are no existing portfolios"); //call the quit scene
          }
          //whatever they click ask shares, ticks
          //break;

        case "3" :
          //menu of portfolio and name
//          case 1... 2





      }


    }
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
  }

  //not that necessary
  private void welcomeMessage() throws IllegalStateException {
    writeMessage("Welcome to the stocks program!" + System.lineSeparator());
    printMenu();
  }
}
