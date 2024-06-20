import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.Controller;
import view.IGUIViewMain;

/**
 * this is a mock of the gui controller, it is used for testing.
 */
public class GUIControllerMock implements ActionListener, Controller {
  ModelMock mockModel;
  IGUIViewMain mockView;
  String userInput;

  /**
   * this is a constructor for the gui controller mock.
   * @param mockModel is the mock model class that is a mock of the model.
   * @param mockView is the gui view.
   * @param userInput is the input that the user puts.
   */
  public GUIControllerMock(ModelMock mockModel, IGUIViewMain mockView, String userInput) {
    this.mockModel = mockModel;
    this.mockView = mockView;
    this.userInput = userInput;
  }


  @Override
  public void goControl() {
    mockView.setListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    mockView.checkComponent();
    String portName = mockView.getSelectedPortfolioName();

    switch (e.getActionCommand()) {

      case "Portfolio Selection":
        if (e.getSource() instanceof JComboBox) {
          JComboBox<String> box = (JComboBox<String>) e.getSource();
          mockView.setComboboxDisplay((String) box.getSelectedItem());
        }
        break;

      case "Create":
        System.out.println("Created");
        String newName = mockView.getCreateName();
        if (!newName.isEmpty()) {
          mockModel.createPortfolio(newName);
          mockView.updatePortfolio(newName);
          mockView.createSuccess();
        }
        break;


      case "buy button":
        String ticker = mockView.getPurchaseTicker();
        String share = mockView.getPurchaseShares();
        String date = mockView.getPurchaseDate();
        try {
          mockModel.buyStock(portName, ticker, share, date);
        } catch (Exception exception) {
          mockView.invalidInput();
        }
        mockView.buySuccess();
//        buildBuySell();
        break;

      case "sell button":
        ticker = mockView.getPurchaseTicker();
        share = mockView.getPurchaseShares();
        date = mockView.getPurchaseDate();
        try {
          mockModel.sellStock(portName, ticker, share, date);
        } catch (Exception exception) {
          mockView.invalidInput();
        }
        mockView.sellSuccess();
//        buildBuySell();
        break;

      case "comp button":
        System.out.println("composition");
        date = mockView.getAnalysisDate();
        try {
          System.out.println(portName);
          System.out.println(date);
          String valueText = mockModel.getPortfolioComposition(portName, date);
          System.out.println(valueText);
          mockView.setCompositionText(valueText);
        } catch (Exception exception) {
          mockView.invalidInput();
        }
        mockView.compSuccess();
//        buildCompVal();
        break;

      case "value button":
        date = mockView.getAnalysisDate();
        try {
          String valueText = mockModel.getPortfolioDistribution(portName, date)
                  + "\n\nTotal Value: "
                  + mockModel.evaluatePortfolio(portName, date);
          mockView.setCompositionText(valueText);
        } catch (Exception exception) {
          mockView.invalidInput();
        }
        mockView.valueSuccess();
//        buildCompVal();,
        break;
      default:
    }
  }
}
