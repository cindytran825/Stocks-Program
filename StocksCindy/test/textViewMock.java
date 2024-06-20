
import view.TextView;

/**
 * mock of the view for testing.
 */
public class textViewMock implements TextView {

  private Appendable appendable;
  StringBuilder log = new StringBuilder();

  /**
   * constructor takes in appendable.
   * @param appendable .
   */
  public textViewMock(Appendable appendable) {
    this.appendable = appendable;
  }

  /**
   * constructor takes in nothing.
   */
  public textViewMock() {
    this.appendable = System.out;
  }


  @Override
  public void printMenu() throws IllegalStateException {
    log.append("print menu");
  }

  @Override
  public void getTicker() throws IllegalStateException {
    log.append("get ticker");
  }

  @Override
  public void getNameOfFile(String name) throws IllegalStateException {
    log.append("get name of file: " + name );
  }

  @Override
  public void namePort() throws IllegalStateException {
    log.append("");
  }

  @Override
  public void stockAdd() throws IllegalStateException {
    log.append("");
  }

  @Override
  public void tickerType() throws IllegalStateException {
    log.append("");
  }

  @Override
  public void getName(String object) {
    log.append("get name: " + object);
  }

  @Override
  public void getShares() {
    log.append("get shares");
  }

  @Override
  public void userInput() {
    log.append("user input");
  }

  @Override
  public void getDay() {
    log.append("get day");
  }

  @Override
  public void getMonth() {
    log.append("get month");
  }

  @Override
  public void getYear() {
    log.append("get year");
  }

  @Override
  public void getDateUser1() throws IllegalStateException {
    log.append("get date user 1");
  }

  @Override
  public void getDateUser2() throws IllegalStateException {
    log.append("");
  }

  @Override
  public void getDateUser3() throws IllegalStateException {
    log.append("");
  }

  @Override
  public void print(String msg) throws IllegalStateException {
    log.append("");
  }

  @Override
  public void success() throws IllegalStateException {
    log.append("");
  }

  @Override
  public void printNetGain(String value, String date1, String date2) throws IllegalStateException {
    log.append("");
  }

  @Override
  public void getDays() throws IllegalStateException {
    log.append("");
  }

  @Override
  public void movingAvg(String value) throws IllegalStateException {
    log.append("");
  }

  @Override
  public void printCrossover(String value) throws IllegalStateException {
    log.append("");
  }

  @Override
  public void terminating() throws IllegalStateException {
    log.append("");
  }

  @Override
  public void emptyLine() throws IllegalStateException {
    log.append("");
  }

  @Override
  public void printStockNames(String list) throws IllegalStateException {
    log.append("");
  }

  @Override
  public void balanceFormat() {
    log.append("");
  }

  @Override
  public void balanceSuccess() {
    log.append("");
  }

  @Override
  public void printSuccessAddStock(String ticker) throws IllegalStateException {
    log.append("");
  }

  @Override
  public void getPath() throws IllegalStateException {
    log.append("");
  }

  @Override
  public void getNameFile() throws IllegalStateException {
    log.append("");
  }

  @Override
  public void invalidDate() throws IllegalStateException {
    log.append("");
  }

  @Override
  public void invalidNumber() throws IllegalStateException {
    log.append("");
  }

  @Override
  public void invalidFile() throws IllegalStateException {
    log.append("");
  }

  @Override
  public void goodbye() throws IllegalStateException {
    log.append("");
  }

  @Override
  public void welcomeMessage() throws IllegalStateException {
    log.append("");
  }

  @Override
  public void invalidCommand() throws IllegalStateException {
    log.append("");
  }

  @Override
  public void newName() {
    log.append("");
  }

  @Override
  public void printEditPortfolioMenu() {
    log.append("");
  }

  @Override
  public void startDate() {
    log.append("");
  }

  @Override
  public void endDate() {
    log.append("");
  }

  @Override
  public void insufficientShares() {
    log.append("");
  }

  @Override
  public void invalidPercentage() {
    log.append("");
  }

  @Override
  public void notChronologicalOrDataInvalid() {
    log.append("");
  }

  @Override
  public void printStockViewMenu() {
    log.append("");
  }

  @Override
  public void printPortfolioViewMenu() {
    log.append("");
  }


  @Override
  public void invalidStock() {
    log.append("");
  }

  @Override
  public void invalidShares() {
    log.append("");
  }
}
