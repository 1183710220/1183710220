import java.awt.Rectangle;
import javax.swing.GroupLayout;
import javax.swing.JFrame;




public class GameFrame extends JFrame {
	public final int GAMEWIDTH = 800;
	public final int GAMEHEIGHT = 570;
	private QiPang qipang = null;
	
	public GameFrame(QiPang qipang) {
		this.setQipang(qipang);
		qipang.setCurrentQiPang();
		this.init();
		this.setBounds(new Rectangle(GAMEWIDTH, GAMEHEIGHT));
		this.setTitle("围棋天地");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	
	public void init() {
		// 创建各个组件
		PlayerPane player1 = new PlayerPane(new Player(QiPang.BLACK));
		PlayerPane player2 = new PlayerPane(new Player(QiPang.WHITE));
		Chatroom chat = new Chatroom();
		ButtonPane btPane = new ButtonPane();
		
		// 布置各个组件
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		// 水平布置
		GroupLayout.SequentialGroup sga = layout.createSequentialGroup()
											.addComponent(player1)
											.addComponent(player2);
		GroupLayout.ParallelGroup hp1 = layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(qipang)
										.addComponent(btPane);
		GroupLayout.ParallelGroup hp2 = layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(sga)
										.addComponent(chat);
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup()
											.addGroup(hp1)
											.addGroup(hp2);
		layout.setHorizontalGroup(hGroup);
		// 垂直布置
		GroupLayout.ParallelGroup vpa = layout.createParallelGroup()
										.addComponent(player1)
										.addComponent(player2);
		GroupLayout.SequentialGroup sgb = layout.createSequentialGroup()
											.addComponent(qipang)
											.addComponent(btPane);
		GroupLayout.SequentialGroup sgc = layout.createSequentialGroup()
											.addGroup(vpa)
											.addComponent(chat);
		GroupLayout.ParallelGroup vp1 = layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(sgb)
										.addGap(5)
										.addGroup(sgc);
		layout.setVerticalGroup(vp1);
	}

	public QiPang getQipang() {
		return qipang;
	}

	public void setQipang(QiPang qipang) {
		this.qipang = qipang;
	}
	
	public static void main(String[] args) {
		new GameFrame(new QiPang());
	}
}
