package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class GUIComposition extends JPanel implements IGUIComposition {
  private JPanel mainPanel;
  private JPanel insidePanel;
  private final JButton compButton;
  private final JButton valueButton;
  private JPanel submitPanel;
  private JLabel portLabel;

  private final JComboBox<String> yearBox;

  private final JComboBox<String> monthBox;

  private final JComboBox<String> dayBox;
  private  JTextArea sTextArea;
  private final JScrollPane scrollPane;


  /**
   * this is called when we just need the date from the user.
   */
  public GUIComposition() {
    super();
    mainPanel = new JPanel();
    this.add(mainPanel, BorderLayout.CENTER);

    JLabel warning = new JLabel("Please make sure to choose from an existing portfolio before submitting!");
    JPanel warningPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    warningPanel.add(warning);
    this.add(mainPanel);

    /**
     * drop down for year list
     */
    List<String> year = new ArrayList<>();
    JLabel selectYear = new JLabel("Select Year");

    // TODO WHY LIMIT YEAR? SHOULD NOT SET IT TO 2025, but set it to the latest year

    for (int i = 2000;  i < 2025 ; i++) {
      year.add(String.valueOf(i));
    }
    String[] years = new String[year.size()];
    years = year.toArray(years);
    yearBox = new JComboBox<String>();
    for (int i = 0; i < years.length; i++) {
      yearBox.addItem(years[i]);
    }
    JPanel selectionListPanel = new JPanel();
    selectionListPanel.add(selectYear);
    selectionListPanel.add(yearBox);

    /**
     * drop down for month list
     */
    List<String> monthList = new ArrayList<>();
    JLabel selectMonth = new JLabel("Select Month");
    for (int i = 1;  i < 13 ; i++) {
      monthList.add(String.valueOf(i));
    }
    String[] listOfMonths = new String[monthList.size()];
    listOfMonths = monthList.toArray(listOfMonths);
    monthBox = new JComboBox<String>();
    for (int i = 0; i < listOfMonths.length; i++) {
      monthBox.addItem(listOfMonths[i]);
    }
    JPanel monthListPanel = new JPanel();
    monthListPanel.add(selectMonth);
    monthListPanel.add(monthBox);

    /**
     * dropdown for daylist
     */
    List<String> dayList = new ArrayList<>();
    JLabel selectDay = new JLabel("Select Day");
    for (int i = 1;  i < 32 ; i++) {
      dayList.add(String.valueOf(i));
    }
    String[] listOfDays = new String[dayList.size()];
    listOfDays = dayList.toArray(listOfDays);
    dayBox = new JComboBox<String>();
    for (int i = 0; i < listOfDays.length; i++) {
      dayBox.addItem(listOfDays[i]);
    }

    JPanel dayListPanel = new JPanel();
    dayListPanel.add(selectDay);
    dayListPanel.add(dayBox);


    sTextArea = new JTextArea(10, 20);
    scrollPane = new JScrollPane(sTextArea);
    sTextArea.setLineWrap(true);
    scrollPane.setBorder(BorderFactory.createTitledBorder("Scrollable Result"));


    insidePanel = new JPanel();
    mainPanel.add(warningPanel);
    insidePanel.add(selectionListPanel);
    insidePanel.add(monthListPanel);
    insidePanel.add(dayListPanel);

    mainPanel.add(insidePanel);
    mainPanel.add(scrollPane);

    /**
     * call the button class
     */
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(insidePanel);

    submitPanel = new JPanel();
    portLabel = new JLabel("");
    submitPanel.add(portLabel);
    compButton = new JButton("composition");
    submitPanel.setLayout(new BoxLayout(submitPanel, BoxLayout.PAGE_AXIS));
    compButton.setActionCommand("comp button");
    submitPanel.add(compButton);

    valueButton = new JButton("value");
    submitPanel.setLayout(new BoxLayout(submitPanel, BoxLayout.PAGE_AXIS));
    submitPanel.add(valueButton);
    valueButton.setActionCommand("value button");
    mainPanel.add(submitPanel);
  }

  @Override
  public void setListener(ActionListener listener) {
    compButton.addActionListener(listener);
    valueButton.addActionListener(listener);
  }

  public void setResultText(String result) {
    sTextArea.setText(result);
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

  public String getDate() {
    return String.format("%s-%s-%s", this.getYear(), this.getMonth(), this.getDay());
  }
}
