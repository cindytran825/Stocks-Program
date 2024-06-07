import java.util.List;

import view.View;

public class ViewMock implements View {

  private Appendable appendable;

  public ViewMock() {
    this.appendable = System.out;
  }

  public ViewMock(Appendable appendable) {
    this.appendable = appendable;
  }

  @Override
  public void printMenu() {

  }

  @Override
  public void getTicker() throws IllegalStateException {

  }

  @Override
  public void getNameOfFile(String name) {

  }

  @Override
  public void goodbye() {

  }

  @Override
  public void namePort() {

  }

  @Override
  public void stockAdd() {

  }

  @Override
  public void tickerType() {

  }

  @Override
  public void nameNew() {

  }

  @Override
  public void inputNumber() {

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
  public void printPortfolio(String portfolio) throws IllegalStateException {

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
  public void getXDays() throws IllegalStateException {

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
  public void welcomeMessage() {

  }

  @Override
  public void invalidCommand() {

  }
}
