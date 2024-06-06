
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
      case "1":
        mockView.nameNew();
        mockView.tickerType();
        mockView.stockAdd();
        break;
      case "2":
        mockView.namePort();
        mockView.tickerType();
        mockView.stockAdd();
        break;
      case "3":
        mockView.namePort();
        break;
      case "4":
        mockView.getTicker();
//        mockView.getDateUser2();
        break;
      case "5":
        mockView.getTicker();
//        mockView.getDateUser();
        break;
      case "6":
//        mockView.getTickerDate();
//        mockView.getDateUser();
        break;
      case "quit":
        mockView.goodbye();
        break;
    }
  }

}
