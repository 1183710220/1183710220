import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import javax.swing.ImageIcon;


public class Player {
	private int qiziColor;
	private ImageIcon image = null; 
	private String name = null;
	
	public Player(int qiziColor) {
		this.setQiziColor(qiziColor);
		try {
			this.init();
		} catch (IOException e) {
			e.printStackTrace();
//			System.out.println("找不到配置文件WeiQi.properties");
		}
	}
	
	// 初始化对局者信息
	public void init() throws IOException {
		File file = new File("src/WeiQi.properties");
		Reader in = new BufferedReader(new FileReader(file));
		Properties p = new Properties();
		p.load(in);
		if(this.getQiziColor() == QiPang.BLACK) {
			this.setImage(new ImageIcon(p.getProperty("bSourceImage")));
			this.setName((String)p.getProperty("bName"));
		} else {
			this.setImage(new ImageIcon(p.getProperty("wSourceImage")));
			this.setName((String)p.getProperty("wName"));
		}
	}
	
	public int getQiziColor() {
		return qiziColor;
	}

	public void setQiziColor(int qiziColor) {
		this.qiziColor = qiziColor;
	}

	public ImageIcon getImage() {
		return image;
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}	
