
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class addComponentsToPane {
	addComponentsToPane(JFrame frame){
		    GridBagConstraints c = new GridBagConstraints();	//�ŧi��������ܼ�
  
		    //��mMenu��panel
		    JPanel panel1 = new JPanel();  
		    c.fill = GridBagConstraints.BOTH;
		    c.gridx = 0;
		    c.gridy = 0;
		    c.weightx = 0;
		    c.weighty = 0;
		    c.gridwidth= 2;
		    c.ipady = 2;
		    panel1.setBackground(Color.GREEN);
		    frame.add(panel1,c);
		    
		    //�s�WMenu
		    JMenuItem item1, item2, item3;
		    JMenu Menu1 = new JMenu("File");



		    JMenu Menu2 = new JMenu("Edit");
		    item1 = new JMenuItem("Group");
		    item1.setActionCommand("Group");
		    item2 = new JMenuItem("unGroup");
		    item2.setActionCommand("unGroup");
		    item3 = new JMenuItem("rename");
		    item3.setActionCommand("rename");
		    Menu2.add(item1);
		    Menu2.add(item2);
		    Menu2.add(item3);
		    
		    JMenuBar menubar = new JMenuBar();
		    menubar.add(Menu1);
		    menubar.add(Menu2);
	    
		    panel1.setLayout(new GridLayout(1,1));	//�]�w�G���覡�� GridBagLayout	
		    panel1.add(menubar);

		    
		  
		    //�e����panel
		    Paint paint = new Paint();	  
		    c.fill = GridBagConstraints.BOTH;
		    c.gridx = 1;
		    c.gridy = 1;
		    c.weightx = 1;
		    c.weighty = 1;
		    c.gridwidth= 1;
		    c.ipadx = 700;		    
		    paint.setBackground(Color.WHITE);
		    frame.add(paint,c); 
		    
		    
		    //��mbutton��panel
		    JPanel panel2 = new JPanel();
		    c.fill = GridBagConstraints.BOTH;
		    c.gridx = 0;
		    c.gridy = 1;
		    c.weightx = 0;
		    c.weighty = 0;
		    c.gridwidth= 1;
		    c.ipadx = 50;
		    panel2.setBackground(Color.white);
		    frame.add(panel2,c);
		    
		    //�سybutton
		    Icon icon0 = new ImageIcon("src//source/select.png");
		    JToggleButton btn0 = new JToggleButton(icon0);
		    btn0.setActionCommand("select"); //setActionCommand means select event
		    
		    Icon icon1 = new ImageIcon("src//source/association.png");
		    JToggleButton btn1 = new JToggleButton(icon1);
		    btn1.setActionCommand("association");
		    
		    Icon icon2 = new ImageIcon("src//source/gene.png");
		    JToggleButton btn2 = new JToggleButton(icon2);
		    btn2.setActionCommand("generalization");
		    
		    Icon icon3 = new ImageIcon("src//source/composition.png");
		    JToggleButton btn3 = new JToggleButton(icon3);
		    btn3.setActionCommand("composition");
		    
		    Icon icon4 = new ImageIcon("src//source/class.png");
		    JToggleButton btn4 = new JToggleButton(icon4);
		    btn4.setActionCommand("class");
		    
		    Icon icon5 = new ImageIcon("src//source/case.png");
		    JToggleButton btn5 = new JToggleButton(icon5);
		    btn5.setActionCommand("use case");
		    
		    JToggleButton[] btn = {btn0,btn1,btn2,btn3,btn4,btn5};
	    
		    //��btn���U��ť		   		 		    
		    ButtonGroup buttonGroup = new ButtonGroup();
		    for(int i=0; i<6; i++) {
		    	buttonGroup.add(btn[i]);
		    	btn[i].addActionListener(new ButtonHandler(paint)); //addActionListener need ActionListener type 的 object
		    }   
    
		    panel2.setLayout(new GridLayout(6,1));	//�]�w�G���覡�� GridBagLayout	
		    for(int i=0; i<6; i++) {
		    	panel2.add(btn[i]);
		    }
		    
    	    
		    item1.addActionListener(new MenuHandler(paint));
		    item2.addActionListener(new MenuHandler(paint));	
		    item3.addActionListener(new MenuHandler(paint));
	}
}



class ButtonHandler implements ActionListener
{
	public String button_mode="";
	public Paint paint;
	
	public ButtonHandler(Paint paint){
		this.paint=paint;
	}
	
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		button_mode=str;
		paint.select_mode(button_mode);
	}
}



class MenuHandler implements ActionListener
{
	public String Menu_mode="";
	public Paint paint;
	
	public MenuHandler(Paint paint){
		this.paint=paint;
	}
	
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		Menu_mode=str;
		paint.set_Menu_mode(Menu_mode);
	}
}
