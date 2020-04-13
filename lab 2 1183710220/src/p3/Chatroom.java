import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Vector;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Chatroom extends JPanel {
	public final int WIDTH = 100;
	public final int HEIGHT = 500;
	public Chatroom() {
		this.init();
		this.setBounds(new Rectangle(WIDTH, HEIGHT));
	}
	
	public void init() {
		Vector<String> names = new Vector<String>();
		String[] strs = new String[]{"多云居士", "湖畔散人", "张果老山人", "大港拿下", 
									"杨歌西溪1", "大庆冷牙", "龙羽马骁", "动动我试试", 
									"雕刻朋友", "清芬", "尘沙无明", "泉挪接触", "百万富翁008", 
									"好吃的眼睛", "心动的风", "棋力上升中", "蜈蚣流", "秋天的红番薯"};
		for(int i=0; i<strs.length; i++) {
			names.add(strs[i]);
		}
		
		// 1:当前棋友标签
		JLabel label1 = new JLabel("当前棋友:");
		
		// 2:棋友名单Pane
		JPanel listPane = new JPanel();
		JList playerList = new JList(names);
		JScrollPane scroll = new JScrollPane(listPane);
		listPane.setLayout(new BorderLayout());
		listPane.add(playerList, BorderLayout.WEST);
		listPane.setBackground(Color.white);
		scroll.setBounds(WIDTH - 50, 20, WIDTH - 50, 200);
		listPane.setPreferredSize(new Dimension(scroll.getWidth()/2, 800));
		
		// 3:聊天室标签
		JLabel label2 = new JLabel("聊天室");
		
		// 4:聊天室显示信息
		JTextArea textArea = new JTextArea(50, 100);
		
		// 5:棋友下拉列表
		JComboBox listBox = new JComboBox(names);
		
		// 6:输入对话信息
		JTextField textField = new JTextField(50);
		
		
		// 设置布局
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		// 水平布置
		GroupLayout.SequentialGroup hsp = layout.createSequentialGroup()
										.addComponent(listBox)
										.addComponent(textField);
		GroupLayout.ParallelGroup hp1 = layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(label1)
										.addComponent(scroll)
										.addComponent(label2)
										.addComponent(textArea)
										.addGroup(hsp);
		layout.setHorizontalGroup(hp1);
		// 垂直布置
		GroupLayout.ParallelGroup vp1 = layout.createParallelGroup()
										.addComponent(listBox)
										.addComponent(textField);
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup()
												.addComponent(label1)
												.addComponent(scroll)
												.addComponent(label2)
												.addComponent(textArea)
												.addGroup(vp1);
		layout.setVerticalGroup(vGroup);
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		Chatroom chat = new Chatroom();
		f.add(chat);
		f.setBounds(new Rectangle(200, 400));
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
