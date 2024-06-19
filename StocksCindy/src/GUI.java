import java.io.InputStreamReader;

import javax.swing.*;

import controller.Controller;
import controller.GUIController;
import controller.StocksController;
import model.Model;
import model.StocksModel;
import view.GUIView;
import view.StockProgramView;
import view.GUIViewImpl;
import view.View;

public class GUI {

  public static void execute() {
    GUIViewImpl.setDefaultLookAndFeelDecorated(false);
    GUIViewImpl frame = new GUIViewImpl();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    Readable rd = new InputStreamReader(System.in);
    Model model = new StocksModel();
    View view = new StockProgramView();
    GUIViewImpl guiView = new GUIViewImpl();

    Controller controller = new GUIController(model, view, rd, guiView);
    controller.goControl();


    try {
      // Set cross-platform Java L&F (also called "Metal")
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

      //UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());

      //   UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
      //    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      //    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
      //    {
      //       if ("Nimbus".equals(info.getName())) {
      //          UIManager.setLookAndFeel(info.getClassName());
      //         break;
      //    }
      // }
    } catch (UnsupportedLookAndFeelException e) {
      // handle exception
    } catch (ClassNotFoundException e) {
      // handle exception
    } catch (InstantiationException e) {
      // handle exception
    } catch (IllegalAccessException e) {
      // handle exception
    } catch (Exception e) {
    }

  }

}
