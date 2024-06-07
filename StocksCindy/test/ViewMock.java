import java.util.List;

import view.View;

/**
 * mock of the view for testing.
 */
public class ViewMock implements View {

  private Appendable appendable;

  /**
   * constructor takes in nothing.
   */
  public ViewMock() {
    this.appendable = System.out;
  }

  /**
   * contructor takes in appendable.
   * @param appendable
   */
  public ViewMock(Appendable appendable) {
    this.appendable = appendable;
  }


  @Override
  public void printMenu() throws IllegalStateException {

  }

  @Override
  public void getTicker() throws IllegalStateException {

  }

  @Override
  public void getNameOfFile(String name) throws IllegalStateException {

  }

  @Override
  public void namePort() throws IllegalStateException {

  }

  @Override
  public void stockAdd() throws IllegalStateException {

  }

  @Override
  public void tickerType() throws IllegalStateException {

  }

  @Override
  public void nameNew() throws IllegalStateException {

  }

  @Override
  public void inputNumber() throws IllegalStateException {

  }

  @Override
  public void getDateUser1() throws IllegalStateException {

  }

  @Override
  public void getDateUser2() throws IllegalStateException {

  }

  @Override
  public void getDateUser3() throws IllegalStateException {

  }

  @Override
  public void print(String msg) throws IllegalStateException {

  }

  @Override
  public void success() throws IllegalStateException {

  }

  @Override
  public void printPortValue(String value) throws IllegalStateException {

  }

  @Override
  public void printNetGain(String value, String date1, String date2) throws IllegalStateException {

  }

  @Override
  public void getDays() throws IllegalStateException {

  }

  @Override
  public void movingAvg(String value) throws IllegalStateException {

  }

  @Override
  public void printCrossover(String value) throws IllegalStateException {

  }

  @Override
  public void terminating() throws IllegalStateException {

  }

  @Override
  public void emptyLine() throws IllegalStateException {

  }

  @Override
  public void printStockNames(String list) throws IllegalStateException {

  }

  @Override
  public void printSuccessAddStock(String ticker) throws IllegalStateException {

  }

  @Override
  public void getPath() throws IllegalStateException {

  }

  @Override
  public void getNameFile() throws IllegalStateException {

  }

  @Override
  public void invalidDate() throws IllegalStateException {

  }

  @Override
  public void invalidNumber() throws IllegalStateException {

  }

  @Override
  public void invalidFile() throws IllegalStateException {

  }

  @Override
  public void goodbye() throws IllegalStateException {

  }

  @Override
  public void welcomeMessage() throws IllegalStateException {

  }

  @Override
  public void invalidCommand() throws IllegalStateException {

  }

  @Override
  public void invalidStock() {

  }
}
