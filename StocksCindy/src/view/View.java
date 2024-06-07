package view;

public interface View {

  void printMenu() throws IllegalStateException;

  void getTicker() throws IllegalStateException;

  void getNameOfFile(String name) throws IllegalStateException;

  void goodbye() throws IllegalStateException;

  void namePort() throws IllegalStateException;

  void stockAdd() throws IllegalStateException;

  void tickerType() throws IllegalStateException;

  void nameNew() throws IllegalStateException;

  void inputNumber() throws IllegalStateException;

  void getDateUser1() throws IllegalStateException;

  void getDateUser2() throws IllegalStateException;

  void getDateUser3() throws IllegalStateException;

  void printPortfolio(String portfolio) throws IllegalStateException;

  void success() throws IllegalStateException;

  void printPortValue(String value) throws IllegalStateException;

  void printNetGain(String value, String date1, String date2) throws IllegalStateException;

  void getXDays() throws IllegalStateException;

  void movingAvg(String value) throws IllegalStateException;

  void printCrossover(String value) throws IllegalStateException;

  void terminating() throws IllegalStateException;
  void emptyLine() throws IllegalStateException;

  void welcomeMessage() throws IllegalStateException;

  void invalidCommand() throws IllegalStateException;
}
