package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class GUIComposition extends JPanel implements GUIView {
  private JPanel mainPanel;
  private JPanel insidePanel;
  private JButton compButton;
  private JButton valueButton;
  private JPanel submitPanel;
  private JLabel portLabel;


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
    List<String> yearing = new ArrayList<>();
    JLabel selectYear = new JLabel("Select Year");

    // TODO WHY LIMIT YEAR? SHOULD NOT SET IT TO 2025, but set it to the latest year

    for (int i = 2000;  i < 2025 ; i++) {
      yearing.add(String.valueOf(i));
    }
    String[] years = new String[yearing.size()];
    years = yearing.toArray(years);
    JComboBox<String> combobox = new JComboBox<String>();
    for (int i = 0; i < years.length; i++) {
      combobox.addItem(years[i]);
    }
    JPanel selectionListPanel = new JPanel();
    selectionListPanel.add(selectYear);
    selectionListPanel.add(combobox);

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
    JComboBox<String> monthBox = new JComboBox<String>();
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
    JComboBox<String> dayBox = new JComboBox<String>();
    for (int i = 0; i < listOfDays.length; i++) {
      dayBox.addItem(listOfDays[i]);
    }

    JPanel dayListPanel = new JPanel();
    dayListPanel.add(selectDay);
    dayListPanel.add(dayBox);


    insidePanel = new JPanel();
    mainPanel.add(warningPanel);
    insidePanel.add(selectionListPanel);
    insidePanel.add(monthListPanel);
    insidePanel.add(dayListPanel);

    mainPanel.add(insidePanel);

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
}
