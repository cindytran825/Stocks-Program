package view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * This class displays the GUI of the buy/selling user interface. It prompts the user for
 * a ticker, shares, year, month, and day to complete a transaction. It will also hold a
 * "buy" and "sell" button separately.
 */
public class GUIBuy extends JPanel implements IGUIBuy {
  private final JButton createPortButton;
  private final JButton sellPortButton;
  private final JComboBox<String> dayBox;
  private final JComboBox<String> monthBox;
  private final JComboBox<String> yearBox;
  private final JComboBox<String> tickerBox;
  private final JTextArea shareBox;


  /**
   * This constructs the buy/sell GUI and assign it default values. It creates a
   * drop-down for stock tickers, a textbox for shares, a drop-down menu for month, day, and year.
   *
   * @param tickers all the stock tickers (just the ticker names) that are available on file
   */
  public GUIBuy(String[] tickers) {
    super();
    JPanel mainPanel = new JPanel();
    this.add(mainPanel, BorderLayout.CENTER);

    // create warning label
    JLabel warning = new JLabel("Please make sure to choose from an existing"
            + " portfolio before submitting!");
    JPanel warningPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    warningPanel.add(warning);
    this.add(mainPanel);

    // creating ticker prompt
    JPanel comboboxPanel = new JPanel();
    comboboxPanel.setBounds(0, 200, 500, 100);
    JLabel tickerBoxDisplay = new JLabel("Ticker: ");
    comboboxPanel.add(tickerBoxDisplay);
    tickerBox = new JComboBox<>();
    tickerBox.setActionCommand("Ticker");
    for (String ticker : tickers) {
      tickerBox.addItem(ticker);
    }
    comboboxPanel.add(tickerBox);

    // creating shares prompt
    shareBox = new JTextArea(0, 10);
    JLabel shareBoxDisplay = new JLabel("Shares: ");
    comboboxPanel.add(shareBoxDisplay);
    comboboxPanel.add(shareBox);
    comboboxPanel.setLayout(new BoxLayout(comboboxPanel, BoxLayout.PAGE_AXIS));

    // creating years prompt
    java.util.List<String> yearing = new ArrayList<>();
    JLabel selectYear = new JLabel("Select Year");
    for (int i = 2000; i < 2025; i++) { // MAKE IT SO THAT ITS THE CURRENT YEAR
      yearing.add(String.valueOf(i));
    }
    String[] years = new String[yearing.size()];
    years = yearing.toArray(years);
    yearBox = new JComboBox<>();
    for (String year : years) {
      yearBox.addItem(year);
    }
    JPanel selectionListPanel = new JPanel();
    selectionListPanel.add(selectYear);
    selectionListPanel.add(yearBox);

    // creating month prompt
    java.util.List<String> monthList = new ArrayList<>();
    JLabel selectMonth = new JLabel("Select Month");
    for (int i = 1; i < 13; i++) {
      monthList.add(String.valueOf(i));
    }
    String[] listOfMonths = new String[monthList.size()];
    listOfMonths = monthList.toArray(listOfMonths);
    monthBox = new JComboBox<>();
    for (String listOfMonth : listOfMonths) {
      monthBox.addItem(listOfMonth);
    }
    JPanel monthListPanel = new JPanel();
    monthListPanel.add(selectMonth);
    monthListPanel.add(monthBox);

    // creating day prompt
    List<String> dayList = new ArrayList<>();
    JLabel selectDay = new JLabel("Select Day");
    for (int i = 1; i < 32; i++) {
      dayList.add(String.valueOf(i));
    }
    String[] listOfDays = new String[dayList.size()];
    listOfDays = dayList.toArray(listOfDays);
    dayBox = new JComboBox<>();
    for (String listOfDay : listOfDays) {
      dayBox.addItem(listOfDay);
    }

    JPanel dayListPanel = new JPanel();
    dayListPanel.add(selectDay);
    dayListPanel.add(dayBox);

    JPanel insidePanel = new JPanel();
    mainPanel.add(warningPanel);
    insidePanel.add(comboboxPanel);
    insidePanel.add(selectionListPanel);
    insidePanel.add(monthListPanel);
    insidePanel.add(dayListPanel);

    mainPanel.add(insidePanel);
    JPanel submitPanel = new JPanel();
    JLabel portLabel = new JLabel("");
    submitPanel.add(portLabel);
    createPortButton = new JButton("buy");
    createPortButton.setActionCommand("buy button");
    submitPanel.setLayout(new BoxLayout(submitPanel, BoxLayout.PAGE_AXIS));
    submitPanel.add(createPortButton);

    sellPortButton = new JButton("sell");
    sellPortButton.setActionCommand("sell button");
    submitPanel.setLayout(new BoxLayout(submitPanel, BoxLayout.PAGE_AXIS));
    submitPanel.add(sellPortButton);
    mainPanel.add(submitPanel);

  }

  private String getDay() {
    return (String) dayBox.getSelectedItem();
  }

  private String getMonth() {
    return (String) monthBox.getSelectedItem();
  }

  private String getYear() {
    return (String) yearBox.getSelectedItem();
  }

  @Override
  public String getDate() {
    return String.format("%s-%s-%s", this.getYear(), this.getMonth(), this.getDay());
  }

  @Override
  public String getShares() {
    return shareBox.getText();
  }

  @Override
  public String getTicker() {
    return (String) tickerBox.getSelectedItem();
  }

  @Override
  public void setListener(ActionListener listener) {
    createPortButton.addActionListener(listener);
    sellPortButton.addActionListener(listener);
  }

}
