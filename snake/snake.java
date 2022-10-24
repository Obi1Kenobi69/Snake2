package snake;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;

public class snake 
{
	public static void main(String[] args)
	{
		myFrame okno = new myFrame();
	}

}
class myFrame extends JFrame
{
	public myFrame()
	{
		myPanel pan = new myPanel();
		Container cont = getContentPane();
		cont.add(pan);
		setTitle("Игра\"Змейка\"");
		setBounds(0,0,800,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
}
class myPanel extends JPanel
{
	
	private game myGame;
	private Timer tmDraw, tmUpdate;
	private Image fon,telo,golova,ob,end;
	private JLabel lb;
	private JButton btn1,btn2;
	private myPanel pan;
	private class myKey implements KeyListener
	{
		public void keyPressed(KeyEvent e)
			{
			int key = e.getKeyCode();
			if (key==KeyEvent.VK_LEFT) myGame.napr = 0;
			else if (key==KeyEvent.VK_UP) myGame.napr = 1;
			else if (key==KeyEvent.VK_RIGHT) myGame.napr = 2;
			else if (key==KeyEvent.VK_DOWN) myGame.napr = 3;
			}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
	}
	public myPanel()
	{
		pan = this;
		this.addKeyListener(new myKey());
		this.setFocusable(true);
	try
	{
		fon = ImageIO.read(new File("C:\\Users\\student\\Sandrovskiy\\snake\\fon.png"));
		telo = ImageIO.read(new File("C:\\Users\\student\\Sandrovskiy\\snake\\telo.png"));
		golova = ImageIO.read(new File("C:\\Users\\student\\Sandrovskiy\\snake\\golova.png"));
		ob = ImageIO.read(new File("C:\\Users\\student\\Sandrovskiy\\snake\\ob.png"));
		end = ImageIO.read(new File("C:\\Users\\student\\Sandrovskiy\\snake\\end.png"));
	}
	catch(Exception ex){}
	myGame = new game();
	myGame.start();
	tmDraw = new Timer(90, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			repaint();
		}
	});
	tmDraw.start();
	setLayout(null);
	lb = new JLabel("Счет: 0");
	lb.setForeground(Color.WHITE);
	lb.setFont(new Font("serif",0,30));
	lb.setBounds(630,200,150,50);
	add(lb);
	btn1 = new JButton();
	btn1.setText("Новая игра");
	btn1.setForeground(Color.BLUE);
	btn1.setFont(new Font("serif",0,20));
	btn1.setBounds(630,30,150,50);
	btn1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			myGame.start();
			btn1.setFocusable(false);
			btn2.setFocusable(false);
			pan.setFocusable(true);
		}
	});
	add(btn1);
	btn2 = new JButton();
	btn2.setText("Выход");
	btn2.setForeground(Color.RED);
	btn2.setFont(new Font("serif",0,20));
	btn2.setBounds(630,100,150,50);
	btn2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
		}
	});
	add(btn2);
	}
	public void paintComponent(Graphics gr)
	{
		super.paintComponent(gr);
		gr.drawImage(fon, 0, 0, 800, 650, null);
		for(int i=0;i<30;i++) {
			for(int j=0;j<30;j++) {
				if(myGame.mas[i][j]!=0)
				{
					if(myGame.mas[i][j]==1)
					{
						gr.drawImage(golova, 10+j*20, 10+i*20, 20, 20, null);
					}
					else if(myGame.mas[i][j]==-1)
					{
						gr.drawImage(ob, 10+j*20, 10+i*20, 20, 20, null);
					}
				}
			}
		}
		gr.setColor(Color.BLUE);
		for(int i= 0;i<=30;i++)
		{
			gr.drawLine(10+i*20, 10, 10+i*20, 610);
			gr.drawLine(10, 10+i*20, 610, 10+i*20);
		}
		tmUpdate = new Timer(4500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				myGame.peremGolova();
				lb.setText("Счет: "+ myGame.kol);
			}
		});
		tmUpdate.start();
	}	
}

