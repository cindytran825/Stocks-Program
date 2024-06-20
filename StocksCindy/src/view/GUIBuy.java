package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

/**
 * This class displays the GUI of the buy/selling user interface. It prompts the user for
 * a ticker, shares, year, month, and day to complete a transaction. It will also hold a
 * "buy" and "sell" button separately.
 */
public class GUIBuy extends JPanel implements IGUIBuy {
  private final JLabel tickerBoxDisplay;
  private final JPanel mainPanel;
  private final JPanel insidePanel;
  private final JButton createPortButton;
  private final JButton sellPortButton;
  private final JPanel submitPanel;
  private final JLabel portLabel;
  private final JComboBox<String> dayBox;
  private final JComboBox<String> monthBox;
  private final JComboBox<String> yearBox;
  private final JComboBox<String> tickerBox;
  private final JTextArea shareBox;
  private final JLabel warning;
  private final JPanel warningPanel;
  private final JLabel shareBoxDisplay;
  private final JPanel comboboxPanel;
  private final JLabel selectYear;
  private final JPanel selectionListPanel;
  private final JLabel selectMonth;
  private final JPanel monthListPanel;
  private final JLabel selectDay;
  private final JPanel dayListPanel;


  /**
   * This constructs the buy/sell GUI and assign it default values. It creates a
   * drop-down for stock tickers, a textbox for shares, a drop-down menu for month, day, and year.
   *
   * @param tickers all the stock tickers (just the ticker names) that are available on file
   */
  public GUIBuy(String[] tickers) {
    super();
    mainPanel = new JPanel();
    this.add(mainPanel, BorderLayout.CENTER);

    // create warning label
    warning = new JLabel("Please make sure to choose from an existing portfolio before submitting!");
    warningPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    warningPanel.add(warning);
    this.add(mainPanel);

    // creating ticker prompt
    comboboxPanel = new JPanel();
    comboboxPanel.setBounds(0, 200, 500, 100);
    tickerBoxDisplay = new JLabel("Ticker: ");
    comboboxPanel.add(tickerBoxDisplay);
    tickerBox = new JComboBox<String>();
    tickerBox.setActionCommand("Ticker");
    for (int i = 0; i < tickers.length; i++) {
      tickerBox.addItem(tickers[i]);
    }
    comboboxPanel.add(tickerBox);

    // creating shares prompt
    shareBox = new JTextArea(0, 10);
    shareBoxDisplay = new JLabel("Shares: ");
    comboboxPanel.add(shareBoxDisplay);
    comboboxPanel.add(shareBox);
    comboboxPanel.setLayout(new BoxLayout(comboboxPanel, BoxLayout.PAGE_AXIS));

    // creating years prompt
    java.util.List<String> yearing = new ArrayList<>();
    selectYear = new JLabel("Select Year");
    for (int i = 2000; i < 2025; i++) { // MAKE IT SO THAT ITS THE CURRENT YEAR
      yearing.add(String.valueOf(i));
    }
    String[] years = new String[yearing.size()];
    years = yearing.toArray(years);
    yearBox = new JComboBox<String>();
    for (int i = 0; i < years.length; i++) {
      yearBox.addItem(years[i]);
    }
    selectionListPanel = new JPanel();
    selectionListPanel.add(selectYear);
    selectionListPanel.add(yearBox);

    // creating month prompt
    java.util.List<String> monthList = new ArrayList<>();
    selectMonth = new JLabel("Select Month");
    for (int i = 1; i < 13; i++) {
      monthList.add(String.valueOf(i));
    }
    String[] listOfMonths = new String[monthList.size()];
    listOfMonths = monthList.toArray(listOfMonths);
    monthBox = new JComboBox<String>();
    for (int i = 0; i < listOfMonths.length; i++) {
      monthBox.addItem(listOfMonths[i]);
    }
    monthListPanel = new JPanel();
    monthListPanel.add(selectMonth);
    monthListPanel.add(monthBox);

    // creating day prompt
    List<String> dayList = new ArrayList<>();
    selectDay = new JLabel("Select Day");
    for (int i = 1; i < 32; i++) {
      dayList.add(String.valueOf(i));
    }
    String[] listOfDays = new String[dayList.size()];
    listOfDays = dayList.toArray(listOfDays);
    dayBox = new JComboBox<String>();
    for (int i = 0; i < listOfDays.length; i++) {
      dayBox.addItem(listOfDays[i]);
    }

    dayListPanel = new JPanel();
    dayListPanel.add(selectDay);
    dayListPanel.add(dayBox);

    insidePanel = new JPanel();
    mainPanel.add(warningPanel);
    insidePanel.add(comboboxPanel);
    insidePanel.add(selectionListPanel);
    insidePanel.add(monthListPanel);
    insidePanel.add(dayListPanel);

    mainPanel.add(insidePanel);
    submitPanel = new JPanel();
    portLabel = new JLabel("");
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
