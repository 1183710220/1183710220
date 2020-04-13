
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PlayerPane extends JPanel {
	public final int FRAMEWIDTH = 130;
	public final int FRAMEHEIGHT = 200;
	public final int IMAGEWIDTH = 120;
	public final int IMAGEHEIGHT = 110;
	
	private Player player;
	public PlayerPane(Player p) {
		player = p;
		this.setBounds(new Rectangle(FRAMEWIDTH, FRAMEHEIGHT));
		this.setLayout(new BorderLayout());
		this.init();
		this.setVisible(true);
	}
	
	// 初始化工作
	public void init() {
		JLabel jImage = new JLabel();
		jImage.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel jQiZi = new JLabel();
		jImage.setBounds(new Rectangle(IMAGEWIDTH, IMAGEHEIGHT));
		JLabel jName = new JLabel("对局者："+player.getName());
		jName.setHorizontalAlignment(SwingConstants.CENTER);
		jImage.setIcon(player.getImage());
		if(player.getQiziColor() == QiPang.BLACK) {
			jQiZi.setText("黑棋");
		} else {
			jQiZi.setText("白棋");
		}
		jQiZi.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.add(jImage, BorderLayout.NORTH);
		this.add(jQiZi, BorderLayout.CENTER);
		this.add(jName, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		PlayerPane p = new PlayerPane(new Player(QiPang.BLACK));
		f.add(p);
		f.setBounds(new Rectangle(150, 200));
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
} 
