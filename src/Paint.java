
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.AlphaComposite;
import java.awt.Rectangle;

public class Paint extends JPanel{
	
	private static Point mousePt;
	private String button_mode="";
	ArrayList<base_shape> shapes; 
	ArrayList<base_line> lines;
	ArrayList<group> groups;
	boolean is_association_select;
	boolean is_shape_select = false;
	boolean is_mouseReleased = false;
	boolean is_group_select = false;
	private int temp_association_select_index;
	private Point temp_mousePt;
	private int start_x;
	private int start_y;
	private int end_x;
	private int end_y;
	private int originalx ;
	private int originaly ;
	private int group_index=0;
	private int group_count=0;

	public Paint(){
		shapes = new ArrayList<base_shape>();
		lines  = new ArrayList<base_line>();	
		groups = new ArrayList<group>();
		
		this.addMouseListener(new MouseAdapter(){ 
			@Override
			public void mousePressed(MouseEvent e) {  
				mousePt = e.getPoint(); 
				is_mouseReleased=false;
				start_x=mousePt.x;
				start_y=mousePt.y;
				end_x=mousePt.x;
				end_y=mousePt.y;
				originalx = start_x;
				originaly = start_y;

				switch(button_mode){
					case "select":		
					is_group_select = false;
					
					//group			
				    for(int index=groups.size()-1 ; index>=0; index--) {			  
						groups.get(index).select(mousePt.x, mousePt.y);
						if (groups.get(index).isSelected == true) {
							is_group_select = true;
							group_index=index;
							for(base_shape S: shapes){
								if(S.last_group_index() == index) {
									for(int i=S.group_index.size()-1; i>=1; i--) {		
										groups.get(S.group_index.get(i)).isSelected = true;
									}
									S.isgroup = true;
								}
							}						
							break;							
						}
				    }	
				    			 
			    	for(int index=shapes.size()-1; index>=0; index--) {
						shapes.get(index).select(mousePt.x, mousePt.y);
						repaint();
						if(shapes.get(index).isSelected()==true) {
							for(int index1=shapes.size()-1; index1>=0; index1--) { //�M���Q���H�~��isSelected
								if(index==index1) {
									continue;
								}
								shapes.get(index1).isSelected = false;
							}																		
							is_shape_select=true;//�P�_���S����쪫��						
							repaint();
							break;
						}else {						
							is_shape_select=false;	
						}
					}
			    	
			    	if(is_shape_select == false) {
						for(base_shape S: shapes){
							S.unallselect();
						}
			    	}
			    	
			    	repaint();					    						  
					break;
					
					case "association":
						if(is_association_select==false) {
							for(int index=shapes.size()-1; index>=0; index--){
								shapes.get(index).select(mousePt.x, mousePt.y);
								if(shapes.get(index).isSelected()==true) {
									temp_association_select_index=index;	
									temp_mousePt=mousePt;
									is_association_select=true;						
								}
							}
						}
						break;
					case "generalization":
						if(is_association_select==false) {
							for(int index=shapes.size()-1; index>=0; index--){
								shapes.get(index).select(mousePt.x, mousePt.y);
								if(shapes.get(index).isSelected()==true) {
									temp_association_select_index=index;	
									temp_mousePt=mousePt;
									is_association_select=true;						
								}
							}
						}
						break;
					case "composition":
						if(is_association_select==false) {
							for(int index=shapes.size()-1; index>=0; index--){
								shapes.get(index).select(mousePt.x, mousePt.y);
								if(shapes.get(index).isSelected()==true) {
									temp_association_select_index=index;	
									temp_mousePt=mousePt;
									is_association_select=true;						
								}
							}
						}
						break;
					case "class":
						add_Rectangle(mousePt);
						break;
					case "use case":	
						add_Circle(mousePt);
						break;
					default:
						System.out.print("wrong");
					}				
			}
			
			public void mouseReleased(MouseEvent e) {  
				mousePt = e.getPoint(); 				
				if(button_mode == "select" && is_shape_select==false) {
					 for (base_shape S: shapes) {
			              S.range_selection(start_x, start_y, end_x, end_y);	
			              System.out.println(S.isSelected+" "+S.israngeSelected+" "+S.isgroup);
			         }    	
					 is_mouseReleased=true;
					 repaint();	
					 
				}				
     
	            if(button_mode=="association" || button_mode=="generalization" || button_mode=="composition") {
	            	if(is_association_select==true) {
		            	for(int index=shapes.size()-1; index>=0; index--){
							shapes.get(index).select(mousePt.x, mousePt.y);
							if(shapes.get(index).isSelected()==true) {
								add_line(select_port(temp_association_select_index,temp_mousePt),select_port(index,mousePt));
								is_association_select=false;							
							}
						}	
	            	}
	            }
	        }  			
		});		
		
		this.addMouseMotionListener(new MouseMotionAdapter() {    
	        @Override    
	        public void mouseDragged(MouseEvent e) {  
	        	mousePt = e.getPoint();
	        	end_x = mousePt.x;
				end_y = mousePt.y;
				
				int dx,dy;
				dx = end_x - originalx;
				dy = end_y - originaly;
				originalx = mousePt.x;
				originaly = mousePt.y;
				boolean isgroupmode=false;				
				ArrayList<base_shape> move_shape = new ArrayList<base_shape>();
				
	        	if (button_mode == "select") {        		
	        		if(is_shape_select==false) { 	        			
	        			repaint();
	        		}
	        		

	        		
	        		
	        		for(int index=groups.size()-1 ; index>=0; index--) {
						if (groups.get(index).isSelected == true) {
							for(base_shape S: shapes) {
								if(S.last_group_index() == index) {
									System.out.println(S.isSelected+" "+S.israngeSelected+" "+S.isgroup);
		        					S.move(dx, dy);    
								}else {
									if(S.israngeSelected == true) {
										S.move(dx, dy);
									}
								}
							}						
							groups.get(index).move(dx ,dy);
							isgroupmode = true;
						}	
	        		}
	        		
	        		if(isgroupmode == false) {
		        		for (base_shape S: shapes) {	
		        			if(S.last_group_index() > -1) {
		        				S.israngeSelected = false;
		        				continue;
		        			}
			                S.move(dx, dy);
			                System.out.println(S.isSelected+" "+S.israngeSelected+" "+S.isgroup);
			            }	  
	        		}  		            
		        }
	        	repaint();
	        }
		});   
	}

	public void add_Rectangle(Point mousePt) {
		shapes.add(new shape_Rectangle(mousePt,shapes.size()+1));
		repaint();
	}	
	
	public void add_Circle(Point mousePt) {
		shapes.add(new shape_circle(mousePt,shapes.size()+1));
		repaint();
	}
	
	public void add_line(connection_port src_port, connection_port dest_port) {
		if(button_mode=="association") {
			lines.add(new association_line(src_port, dest_port));
			repaint();
		}else if (button_mode=="generalization") {
			lines.add(new generalization_line(src_port, dest_port));
			repaint();
		}else if (button_mode=="composition") {
			lines.add(new composition_line(src_port, dest_port));
			repaint();
		}
		
	}
	
	public void set_Menu_mode(String mode) {
		System.out.println(mode);
		System.out.println(this.button_mode);
		System.out.println(start_x+" "+start_y+" "+end_x+" "+end_y);
		int min_x1 = Integer.MAX_VALUE;
		int min_y1 = Integer.MAX_VALUE;
		int max_x2 = Integer.MIN_VALUE;
		int max_y2 = Integer.MIN_VALUE;
		
		if(mode == "Group") {
			int count = 0;	
			group gr = new group(0,0,0,0);

			
			for(base_shape S: shapes) {
				if(S.israngeSelected == true) {
					if(S.last_group_index() > -1) { 
						S.isgroup = true;
						if (groups.get(S.last_group_index()).is_select_contain(start_x, start_y, end_x, end_y) == false) {
							continue;
						}
					}
					count++;
					S.group_index.add(this.group_count);
					min_x1 = Math.min(min_x1, S.x);
					min_y1 = Math.min(min_y1, S.y);		
					max_x2 = Math.max(max_x2, S.port_e.x);
					max_y2 = Math.max(max_y2, S.port_s.y);
				}
			}

			if(count>1) {
				this.group_count++;
				System.out.println(count);
				gr.x = min_x1;
				gr.y = min_y1;
				gr.width  = max_x2-gr.x;
				gr.height = max_y2-gr.y;
				groups.add(gr);
			}else {
				for(base_shape S: shapes) {
					if(S.last_group_index() == this.group_count) {
						S.isgroup = false;
						S.group_index.remove(S.group_index.size()-1);
					}
				}
			}
			for(base_shape S: shapes) {
				S.israngeSelected = false;
				S.isSelected = false;
			}		
		}else if (mode == "unGroup") {
			if(is_group_select = true) {
				for(int index=groups.size()-1; index>=0; index--) {
					if(index == group_index) {
						groups.get(index).ungroup();
						for(base_shape S: shapes){
							if(S.last_group_index() == index) {							
								S.ungroup();
							}
						}						
					}
				}
				is_group_select = false;
			}
			
		}else if (mode == "rename"){
			for(base_shape S: shapes) {
				if(S.isSelected == true) {
					JFrame jFrame = new JFrame();
			        String getMessage = JOptionPane.showInputDialog(jFrame, "Enter your message");
			        JOptionPane.showMessageDialog(jFrame, "Your message: "+getMessage);
			        if(getMessage==null) return;
			        S.set_name(getMessage);
				}
			}
		}
		repaint();
	}

	public void select_mode(String mode) {
		button_mode=mode;
		System.out.println(button_mode);
	}
	
	@Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(base_shape S : shapes) {
			S.paint(g);
		}
		for(base_line L : lines) {
			L.paint(g);
		}
		
		for(group G : groups) {
			G.paint(g);
		}
		
		if(button_mode=="select" && is_shape_select==false && is_mouseReleased==false &&  is_group_select==false ) {
			paint_selectrange(start_x, start_y, end_x, end_y, g);
		}				
    }	
	
    public void paint_selectrange(int start_x, int start_y, int end_x, int end_y, Graphics g) {
    	Graphics2D g2 = (Graphics2D)g.create();
    	g2.setColor(Color.green);
    	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.3f));
    	System.out.println(start_x+" "+start_y+" "+end_x+" "+end_y);
    	g2.fillRect(start_x , start_y, end_x-start_x, end_y-start_y);
    }	
		
	public connection_port select_port(int index,Point mousePt){//���I�� �ݯण��A��@�U
		int port_n_x=shapes.get(index).port_n.x;
		int port_n_y=shapes.get(index).port_n.y;
		int port_n_total_Subtract=Math.abs(mousePt.x-port_n_x)+Math.abs(mousePt.y-port_n_y);
		
		int port_w_x=shapes.get(index).port_w.x;
		int port_w_y=shapes.get(index).port_w.y;
		int port_w_total_Subtract=Math.abs(mousePt.x-port_w_x)+Math.abs(mousePt.y-port_w_y);	
		
		int port_e_x=shapes.get(index).port_e.x;
		int port_e_y=shapes.get(index).port_e.y;
		int port_e_total_Subtract=Math.abs(mousePt.x-port_e_x)+Math.abs(mousePt.y-port_e_y);	
		
		int port_s_x=shapes.get(index).port_s.x;
		int port_s_y=shapes.get(index).port_s.y;
		int port_s_total_Subtract=Math.abs(mousePt.x-port_s_x)+Math.abs(mousePt.y-port_s_y);
		connection_port port =new connection_port(0,0);
		
		int min=port_n_total_Subtract;	
		port=shapes.get(index).port_n;
		
		if(port_w_total_Subtract < min) {
			min=port_w_total_Subtract;			
			port=shapes.get(index).port_w;
		}
		if(port_e_total_Subtract < min) {
			min=port_e_total_Subtract;
			port=shapes.get(index).port_e;
		}
		if(port_s_total_Subtract < min) {
			min=port_s_total_Subtract;
			port=shapes.get(index).port_s;
		}
		
		return port;
			
	}
	

}
