import java.awt.*;  
import javax.swing.*;
import java.awt.event.*;  
  
 class start extends JFrame implements ActionListener 
 {  

    JFrame f ; 
    JPanel p = new JPanel();
     //b[row][column] 
    JButton b[][]=new JButton[3][3];
    JButton src = new JButton();
    
    JMenuBar mb = new JMenuBar();
    JMenu file;//rs,ex;
    JMenuItem rs,ex;
    ImageIcon xi = new ImageIcon("blue x.png");
    ImageIcon oi = new ImageIcon("red o.png");
    ImageIcon xi1 = new ImageIcon("red x.png");
    ImageIcon oi2 = new ImageIcon("blue o.png");
    
    int x,y;
    int input=2;

    Font font;
    start()
    {
        
        f=new JFrame("Tic Tac Toe"); 
        file = new JMenu("File");
        
        rs = new JMenuItem ("Restart");
        rs.addActionListener(this);
        file.add(rs);
        ex = new JMenuItem("Exit");
        ex.addActionListener(this);
        file.add(ex);
        mb.add(file);
        f.setJMenuBar(mb);

        //b[row][column] 
      
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                b[i][j] = new JButton();
                f.add(b[i][j]);
                b[i][j].setText("");
                b[i][j].setBackground(Color.gray);
                /*if((j+1)%2==0 && (i+1)%2==0) 
                    b[i][j].setBackground(Color.gray);
                else if((j+1)%2!=0 && (i+1)%2!=0)
                    b[i][j].setBackground(Color.gray);
                else
                    b[i][j].setBackground(Color.white);*/
                
                b[i][j].addActionListener(this);
    
            }

        }  
  
        f.setLayout(new GridLayout(3,3,5,5));  
        //setting grid layout of 8 rows and 8 columns  
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        f.setSize(500,500);  
        f.setVisible(true);
        f.setResizable(false);

        font = new Font ("Courier",Font.BOLD,00);
    }


    public static void main(String[] args)
    {  
         new start();  
    }  

    String r = null;
    int cnt=0;

    public void actionPerformed(ActionEvent e)
    {
        //f.setDefaultCloseOperation(popup());
        if(e.getSource() == rs)restart();
        if(e.getSource() == ex)System.exit(0);        
        
        //if((int) e.getKeyCode()==27){restart();}
        if(e.getSource() instanceof JButton)
            src = (JButton)e.getSource();
        
      if(src.getText()!=""){JOptionPane.showMessageDialog(f,"invalid move"); }
      else
      {
       		 if(cnt%2!=0)
       		 {
       		     src.setText("o");
       		     src.setIcon(oi);
      		      src.setFont(font);
     		       
       		 }
      		  if(cnt%2==0)
       		 {
     		       src.setText("x");
     		       src.setIcon(xi);
      		      src.setFont(font);
     		       
        	}
        	cnt++;
        	
        	
      		for(int i=0;i<3;i++){
        	for(int j=0;j<3;j++){
            	if(e.getSource()==b[i][j])
                	{x=i;y=j;}
        	}
        	}          
        	if(b[0][0].getText()==b[1][1].getText()&&b[0][0].getText()==b[2][2].getText()&&b[0][0].getText()!="")
       		 {
        		 r=b[0][0].getText();endAction(x,y,cnt);
       		 }
       		 else if(b[0][0].getText()==b[0][1].getText()&&b[0][2].getText()==b[0][0].getText()&&b[0][0].getText()!="")
       		 {
       		     r=b[0][0].getText();endAction(x,y,cnt);
       		 }
       		 else if(b[0][0].getText()==b[1][0].getText()&&b[2][0].getText()==b[0][0].getText()&&b[0][0].getText()!="")
      		  {
       		     r=b[0][0].getText();endAction(x,y,cnt);
      		  }
       		 else if(b[1][0].getText()==b[1][1].getText()&&b[1][2].getText()==b[1][0].getText()&&b[1][0].getText()!="")
       		 {
      		      r=b[1][0].getText();endAction(x,y,cnt);
       		 }
       		 else if(b[0][1].getText()==b[1][1].getText()&&b[2][1].getText()==b[1][1].getText()&&b[1][1].getText()!="")
       		 {
       		     r=b[0][1].getText();endAction(x,y,cnt);
       		 }

       		 if(b[2][2].getText()==b[1][2].getText()&&b[2][2].getText()==b[0][2].getText()&&b[0][2].getText()!="")
       		 {
      		      r=b[2][2].getText();endAction(x,y,cnt);
       		 }
      		  else if(b[2][2].getText()==b[2][1].getText()&&b[2][2].getText()==b[2][0].getText()&&b[2][0].getText()!="")
       		 {
       		     r=b[2][2].getText(); endAction(x,y,cnt);
      		  }
       		 else if(b[2][0].getText()==b[1][1].getText()&&b[1][1].getText()==b[0][2].getText()&&b[0][2].getText()!="")
       		 {
       		     r=b[2][0].getText(); endAction(x,y,cnt);
      		  }
       		 else if(cnt==9)
       		 {
       		 	//x=-1;y=-1;
       		 	r="d";endAction(x,y,cnt);
       		     //endAction(x,y,cnt);
       		 }
       		 // if(r=="x"){end(r);}
       		 // else if(r=="0"){end(r);}
       		 // else if(r=="d"){end(r);}
       }
    }
    public void restart()
    {
    	f.dispose();
        new start();
    }

    public void endAction(int x1,int y1,int cnt1)
    {
    	
        	
    			input = JOptionPane.showConfirmDialog(f, b[x1][y1].getText()+" won \n want to restart?");
        		 if(input == 1)System.exit(0);
       			 else if(input == 0)restart();

       	
      }
      public void draw()
   	 {
          input = JOptionPane.showConfirmDialog(f,"draw\n want to restart ?");
          if(input == 1)System.exit(0);
          else if(input == 0)restart();
      	      //input = 2;
   	 }

   	
}


	
	
	
public class tictactoe {
	JButton b1;
	JLabel l1;
	private JFrame f = new JFrame("Login");
	private JButton bok = new JButton("Start the Game");
	ImageIcon st = new ImageIcon("tictactoe img.jpg");
	public tictactoe() {
	
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(bok);


		//bok.setBackground(Color.white);
		f.setLayout(new BorderLayout());
		JLabel background=new JLabel(new ImageIcon("tictactoe gif.gif"));
		//f.setForeground(new ImageIcon("tictactoe gif.gif"));
		f.add(background);
		background.setLayout(null);//new FlowLayout());
		//l1=new JLabel("Here is a button");
		//b1=new JButton("I am a button");
		background.setForeground(Color.gray);
		//background.add(bok);
		//background.add(b1);
		bok.setBounds(171,415,150,40);
		bok.setBackground(Color.lightGray);
		//bok.setVisible(false);
		//f.setIcon(st);
		bok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
				new start();
			}
		});
		f.setSize(500,505);
		f.setVisible(true);
		f.setResizable(false);
		//f.setLayout(null);
	}
	
	public static void main(String[] args) {
		new tictactoe();
	}
}