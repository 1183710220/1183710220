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
		String[] strs = new String[]{"���ƾ�ʿ", "����ɢ��", "�Ź���ɽ��", "�������", 
									"�����Ϫ1", "��������", "��������", "����������", 
									"�������", "���", "��ɳ����", "ȪŲ�Ӵ�", "������008", 
									"�óԵ��۾�", "�Ķ��ķ�", "����������", "�����", "����ĺ췬��"};
		for(int i=0; i<strs.length; i++) {
			names.add(strs[i]);
		}
		
		// 1:��ǰ���ѱ�ǩ
		JLabel label1 = new JLabel("��ǰ����:");
		
		// 2:��������Pane
		JPanel listPane = new JPanel();
		JList playerList = new JList(names);
		JScrollPane scroll = new JScrollPane(listPane);
		listPane.setLayout(new BorderLayout());
		listPane.add(playerList, BorderLayout.WEST);
		listPane.setBackground(Color.white);
		scroll.setBounds(WIDTH - 50, 20, WIDTH - 50, 200);
		listPane.setPreferredSize(new Dimension(scroll.getWidth()/2, 800));
		
		// 3:�����ұ�ǩ
		JLabel label2 = new JLabel("������");
		
		// 4:��������ʾ��Ϣ
		JTextArea textArea = new JTextArea(50, 100);
		
		// 5:���������б�
		JComboBox listBox = new JComboBox(names);
		
		// 6:����Ի���Ϣ
		JTextField textField = new JTextField(50);
		
		
		// ���ò���
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		// ˮƽ����
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
		// ��ֱ����
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
