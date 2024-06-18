package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
//todo implements view?????

public class SwingFeaturesFrame extends JFrame implements ActionListener, GUIView {
  private JPanel mainPanel;
  private JScrollPane mainScrollPane;
  private JLabel radioDisplay;
  private JLabel fileOpenDisplay;
  private JLabel fileSaveDisplay;

  public SwingFeaturesFrame() {
    super();
    setTitle("Stocks Program");
    setSize(400, 400);

    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    //text area
    JPanel radioPanel = new JPanel();
    radioPanel.setBorder(BorderFactory.createTitledBorder("Welcome to the Stocks Program!"));
//    mainPanel.add(radioPanel);

    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
    //Todo have something that will display what each item in menu does
    // in new view ?
    JRadioButton[] radioButtons = new JRadioButton[5];
    //create portfolio
    radioButtons[0] = new JRadioButton("port-create");
    //buy/sell
    radioButtons[1] = new JRadioButton("port-manage");
    //buy/sell stock
    radioButtons[2] = new JRadioButton("stock-view");
    //download //TODO wouldn't really need this if we have open file
    radioButtons[3] = new JRadioButton("port-download");
    //composition and value of portfolio
    radioButtons[4] = new JRadioButton("port-view");
    radioButtons[0].setActionCommand("port-create");
    radioButtons[1].setActionCommand("port-manage");
    radioButtons[2].setActionCommand("stock-view");
    radioButtons[3].setActionCommand("port-download");
    radioButtons[4].setActionCommand("port-view");
    for (int i = 0; i < radioButtons.length; i++) {
      radioButtons[i].addActionListener(this);
      radioPanel.add(radioButtons[i]);
    }

    radioDisplay = new JLabel("Which one did the user select?");
    radioPanel.add(radioDisplay);
    mainPanel.add(radioPanel);
//    add(radioPanel);

    //dialog boxes
    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Dialog boxes"));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(dialogBoxesPanel);

    //file open

    JPanel fileopenPanel = new JPanel();
    fileopenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileopenPanel);
    JButton fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileOpenButton.addActionListener(this);
    fileopenPanel.add(fileOpenButton);
    fileOpenDisplay = new JLabel("File path will appear here");
    fileopenPanel.add(fileOpenDisplay);

    //file save
    JPanel filesavePanel = new JPanel();
    filesavePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(filesavePanel);
    JButton fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("Save file");
    fileSaveButton.addActionListener(this);
    filesavePanel.add(fileSaveButton);
    fileSaveDisplay = new JLabel("File path will appear here");
    filesavePanel.add(fileSaveDisplay);

  }

  //todo is this supposed to be here or controller ?
  // would the setTest be considered view ?
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "port-create":
        radioDisplay.setText("port-create was selected");
        break;
      case "port-manage":
        radioDisplay.setText("port-manage was selected");
        break;
      case "stock-view":
        radioDisplay.setText("stock-view was selected");
        break;
      case "port-download":
        radioDisplay.setText("port-download was selected");
        break;
      case "port-view":
        radioDisplay.setText("port-view was selected");
        break;
      case "Open file": {
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(SwingFeaturesFrame.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          fileOpenDisplay.setText(f.getAbsolutePath());
        }
      }
      break;
      case "Save file": {
        final JFileChooser fchooser = new JFileChooser(".");
        int retvalue = fchooser.showSaveDialog(SwingFeaturesFrame.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          fileSaveDisplay.setText(f.getAbsolutePath());
        }
      }
      break;
    }

  }
}
