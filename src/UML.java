
import javax.swing.*;
import java.awt.*;

public class UML {
   public static void main(String[] args) {
      createWindow();
   
   }

   private static void createWindow() {    
      JFrame frame = new JFrame("UML");
      GridBagLayout gridBagLayout = new GridBagLayout();	
      frame.setLayout(gridBagLayout);	
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(1200, 700);
      new addComponentsToPane(frame);
      frame.setVisible(true);
   }

  
  
}