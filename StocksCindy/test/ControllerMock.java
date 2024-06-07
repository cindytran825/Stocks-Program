
import java.util.Scanner;

import controller.Controller;
import model.Model;
import model.StocksModel;
import view.StockProgramView;
import view.View;

/**
 * Controller mock for testing.
 */
public class ControllerMock implements Controller {
  Model mockModel;
  View mockView;
  String userInput;


  private Scanner scan;
  private String name;

  /**
   * constructor for testing.
   * takes in mockview and model.
   * @param mockModel
   * @param mockView
   * @param userInput
   */
  ControllerMock(Model mockModel, View mockView, String userInput) {
    this.mockModel = mockModel;
    this.mockView = mockView;
    this.userInput = userInput;
  }


  /**
   * mock of the goControl method.
   * Used for testing.
   */
  @Override
  public void goControl() {
    mockView.welcomeMessage();
    switch (userInput) {
      case "port-create":
        mockView.nameNew();
        mockView.success();
        mockView.tickerType();
        mockView.stockAdd();
        mockView.terminating();
        break;
      case "port-manage":
        mockView.namePort();
        mockView.tickerType();
        mockView.stockAdd();
        mockView.terminating();
        mockView.emptyLine();
        break;
      case "port-view":
        mockView.namePort();
        mockView.emptyLine();
        break;
      case "port-eval":
        mockView.namePort();
        mockView.getTicker();
        mockView.getDateUser1();
        mockView.emptyLine();
        break;
      case "stock-eval":
        mockView.getTicker();
        mockView.getDateUser2();
        mockView.getDateUser3();
        mockView.emptyLine();
        break;
      case "stock-avg":
        mockView.getTicker();
        mockView.getTicker();
        mockView.getDateUser2();
        mockView.getDays();
        break;
      case "stock-cross":
        mockView.getTicker();
        mockView.getDateUser2();
        mockView.getDays();
        mockView.emptyLine();
        break;
      case "stock-list":
        mockView.emptyLine();
      case "stock-download":
        mockView.getTicker();
        mockView.emptyLine();
      case "stock-upload":
        mockView.getPath();
        mockView.getNameFile();
      case "menu":
        mockView.printMenu();
        mockView.emptyLine();
        break;
      case "quit":
        mockView.goodbye();
        break;
      default:
        mockView.invalidCommand();
    }
    mockView.goodbye();
  }
}
