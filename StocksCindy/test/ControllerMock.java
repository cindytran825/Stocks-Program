
import java.util.Scanner;

import controller.Controller;
import model.Model;
import model.StocksModel;
import view.StockProgramView;
import view.View;

public class ControllerMock implements Controller {
  Model mockModel;
  View mockView;
  String userInput;


  private Scanner scan;
  private String name;

  ControllerMock(Model mockModel, View mockView, String userInput) {
    this.mockModel = mockModel;
    this.mockView = mockView;
    this.userInput = userInput;
  }


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
        break;
      case "stock-avg":
        mockView.getTicker();
        mockView.getDateUser2();
        ;
        mockView.getXDays();
        mockView.getDateUser2();
        mockView.getXDays();
        break;
      case "stock-cross":
        mockView.getTicker();
        break;
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
