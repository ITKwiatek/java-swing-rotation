import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Rotation extends JFrame{

	MyComponent komponent;
	int N=400;
	int n=0;
	int alfa=0;
	Timer timer;
	class MyComponent extends JComponent{

		@Override
		protected void paintComponent(Graphics g) {
			int w=getWidth();
			int h=getHeight();
			int x=0,y=0;
			int x0=w/2;
			int y0=h/2;
			int r=((w<h)?w:h)/3;
			
			int xSR1=x0-r+(int)Math.round(r*Math.cos(Math.toRadians(270+alfa)));
			int ySR1=y0-r+(int)Math.round(r*Math.sin(Math.toRadians(270+alfa)));
			int xSR2=x0-r+(int)Math.round(r*Math.cos(Math.toRadians(90+alfa)));
			int ySR2=y0-r+(int)Math.round(r*Math.sin(Math.toRadians(90+alfa)));
			int xSR3=x0-r+(int)Math.round(r*Math.cos(Math.toRadians(0+alfa)));
			int ySR3=y0-r+(int)Math.round(r*Math.sin(Math.toRadians(0+alfa)));
			int xSR4=x0-r+(int)Math.round(r*Math.cos(Math.toRadians(0+alfa)));
			int ySR4=y0-r+(int)Math.round(r*Math.sin(Math.toRadians(0+alfa)));	
			
			int xS1=xSR1+(int)Math.round(r*Math.cos(Math.toRadians(180+alfa)));
			int yS1=ySR1+(int)Math.round(r*Math.sin(Math.toRadians(180+alfa)));
			int xS2=xSR2+(int)Math.round(r*Math.cos(Math.toRadians(180+alfa)));
			int yS2=ySR2+(int)Math.round(r*Math.sin(Math.toRadians(180+alfa)));
			int xS3=xSR3+(int)Math.round(r*Math.cos(Math.toRadians(90+alfa)));
			int yS3=ySR3+(int)Math.round(r*Math.sin(Math.toRadians(90+alfa)));
			int xS4=xSR4+(int)Math.round(r*Math.cos(Math.toRadians(270+alfa)));
			int yS4=ySR4+(int)Math.round(r*Math.sin(Math.toRadians(270+alfa)));
			
			g.drawLine(x0, y0-300, x0, y0+300);
			g.drawLine(x0-300, y0, x0+300, y0);
			g.drawArc(xS1, yS1, 2*r, 2*r, 0-alfa, -90); //WN
			g.drawArc(xS2, yS2, 2*r, 2*r, 90-alfa, -90); //SW
			g.drawArc(xS3, yS3, 2*r, 2*r, 180-alfa, -90); //SE
			g.drawArc(xS4, yS4, 2*r, 2*r, 270-alfa, -90); //NE
			
			
			int b=(int)Math.round(Math.PI*2*r*(90)/360);
			int obw = 4*b;
			int nb=b*N/obw;
			double kat;
			
			if(n<nb){
				kat=Math.toRadians(alfa)+Math.toRadians(-90)*(n)/nb;
				x=xS2+r+(int)Math.round(r*Math.cos(kat));
				y=yS2+r+(int)Math.round(r*Math.sin(kat));
			}else if(n<nb+nb){
				kat=Math.toRadians(180+alfa)+Math.toRadians(-90)*(n)/nb;
				x=xS1+r+(int)Math.round(r*Math.cos(kat));
				y=yS1+r+(int)Math.round(r*Math.sin(kat));
			}else if(n<nb+nb+nb){
				kat=Math.toRadians(360+alfa)+Math.toRadians(-90)*(n)/nb;
				x=xS4+r+(int)Math.round(r*Math.cos(kat));
				y=yS4+r+(int)Math.round(r*Math.sin(kat));
			}else if(n<4*nb){
				kat=Math.toRadians(180+alfa)+Math.toRadians(-90)*(n)/nb;
				x=xS3+r+(int)Math.round(r*Math.cos(kat));
				y=yS3+r+(int)Math.round(r*Math.sin(kat));
			}
			
			
			g.setColor(Color.RED);
			g.fillOval(x-5, y-5, 10, 10);
			
			g.setColor(Color.BLACK);
			super.paintComponent(g);
			
		}
	}
	public Rotation(String string) {
		super(string);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension d=kit.getScreenSize();
		setBounds(d.width/4, d.height/4, d.width/2, d.height/2);
		add(komponent=new MyComponent());
		timer=new Timer(20,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				n++;
				if(n>=N)
					n-=N;
				alfa++;
				komponent.repaint();
			}
		});
		timer.start();
		setVisible(true);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new Rotation("Rotation");
			}
		});
	}

}
