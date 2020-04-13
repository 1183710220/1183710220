import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class QiPang extends JPanel {

	public static final int SPACE = 26;
	public static final int QIPANGSPACE = 20;
	public static final int QIPANGWIDTH = SPACE * 18 + QIPANGSPACE * 2;
	public static final int QIPANGHEIGHT = SPACE * 18 + QIPANGSPACE * 2;
	public static final int MOUSESPACE = 10;
	public static final int POINTCOUNT = 19 * 19;

	// �жϸ������״̬
	public static final int EMPTY = 0;
	public static final int WHITE = 1;
	public static final int BLACK = 2;
	public static Map<Integer, Integer> mapStation = new HashMap<Integer, Integer>(); 
	public static QiPang currentQiPang = null;
	public static int qiziCount = 0;
	public static boolean showNum = false;
	public static Integer[] pointState = null;

	private List<Integer> xPoints = null;
	private List<Integer> yPoints = null;
	
	

	// ѭ���Ľ��ŵ�Ĳ���
	private int iForbit = -1;

	public QiPang() {
		super();
		this.initPoints();
		this.setMinimumSize(new Dimension(QIPANGWIDTH, QIPANGHEIGHT));
		this.setMaximumSize(new Dimension(QIPANGWIDTH, QIPANGHEIGHT));
		this.mouseClick();
		this.setVisible(true);
		repaint();
	}

	// ���������
	public void drawBoard(Graphics g) {
		g.setColor(new Color(254, 215, 124));
		g.fillRect(0, 0, QIPANGWIDTH, QIPANGHEIGHT);
	}

	// ������ֱ��
	public void drawLine(Graphics g) {
		g.setColor(Color.black);
		for (int i = 0; i < 19; i++) {
			g.drawLine(i * SPACE + QIPANGSPACE, QIPANGSPACE, i * SPACE
					+ QIPANGSPACE, SPACE * 18 + QIPANGSPACE);
			g.drawLine(QIPANGSPACE, i * SPACE + QIPANGSPACE, SPACE * 18
					+ QIPANGSPACE, i * SPACE + QIPANGSPACE);
		}
	}
	
	// ����������ֺ���ĸ
	public void drawXY(Graphics g) {
		g.setColor(Color.black);
		for(int i=0; i<19; i++) {
			String str1 = ""+(i+1);
			String str2 = ""+(char)(('A')+i);
			g.drawString(str2, i*SPACE + QIPANGSPACE  - 3, QIPANGSPACE - 3);
			g.drawString(str2, i*SPACE + QIPANGSPACE  - 3, 19*SPACE + 10);
			g.drawString(str1, QIPANGSPACE/2 - 3*str1.length(), i*SPACE + QIPANGSPACE + 5);
			g.drawString(str1, 19*SPACE - 2, i*SPACE + QIPANGSPACE + 5);
		}
	}

	// ����λ
	public void drawPoint(Graphics g, int x, int y) {
		int ovalspace = 8;
		g.setColor(Color.black);
		g.fillOval(x - ovalspace / 2, y - ovalspace / 2, ovalspace, ovalspace);
	}

	// �������е���λ
	public void drawAllPoint(Graphics g) {
		g.setColor(Color.black);
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				if (i == 3 || i == 9 || i == 15) {
					if (j == 3 || j == 9 || j == 15) {
						drawPoint(g, i * SPACE + QIPANGSPACE, j * SPACE
								+ QIPANGSPACE);
					}
				}
			}
		}
	}

	// �ػ�ʱ���������Ѿ����ڵ�����
	public void drawExistQiZi(Graphics g) {
		boolean isBlack = true;
		for (int i = 0; i < pointState.length; i++) {
			if (pointState[i] == BLACK) {
				isBlack = true;
				new QiZi(false).drawQiZi(g, i, isBlack);
			} else if (pointState[i] == WHITE) {
				isBlack = false;
				new QiZi(false).drawQiZi(g, i, isBlack);
			}
		}
	}

	// ����������ĵ�
	public void mouseClick() {
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				drawMouseClickQiZi(x, y);
			}
		});
	}

	// ��ʾ����λ�õ�����
	public void drawMouseClickQiZi (int x, int y) {
		Graphics g = this.getGraphics();
		int xi = 0, yi = 0, xa = 0, ya = 0;
		for (int i = 0; i < 19; i++) {
			xa = Math.abs(x - xPoints.get(i));
			if (xa < MOUSESPACE) {
				xi = i;
				break;
			}
		}
		for (int i = 0; i < 19; i++) {
			ya = Math.abs(y - yPoints.get(i));
			if (ya < MOUSESPACE) {
				yi = i;
				break;
			}
		}

		int address = yi * 19 + xi;
		// �����ѭ�����ŵ�_2
		if (iForbit == address) {
			return;
		} else {
			iForbit = -1;
		}
		boolean isBlack = false;
		if ((xa + ya < MOUSESPACE * 2) && pointState[address] == EMPTY) {
			QiZi qizi = new QiZi(true);
			qiziCount++;
			// �Ѹ�λ�õĵ��״̬���óɺڻ��߰���
			if (qiziCount % 2 == 0) {
				pointState[address] = WHITE;
			} else {
				pointState[address] = BLACK;
				isBlack = true;
			}

			// �жϵ�ǰ�Ƿ��������;
			boolean bDead = false;
			int deadNum = 0;
			Set<Integer> differs = qizi.differQiZiArray(pointState, address);
			for (Iterator it = differs.iterator(); it.hasNext();) {
				int target = (Integer) it.next();
				int size = 0;
				if (pointState[target] != EMPTY
						&& qizi.isDeadQiZi(pointState, target)) {
					size = qizi.relationQiZi(pointState, target).length;
					pointState = qizi.handleDead(pointState, target);

					// �����ѭ�����ŵ�_1
					if (size == 1) {
						if(pointState[address] == BLACK) {
							pointState[target] = WHITE;
						} else {
							pointState[target] = BLACK;
						}
						if(qizi.isDeadQiZi(pointState, address) && qizi.relationQiZi(pointState, address).length == 1) {
							iForbit = target;
						}				
						pointState[target] = EMPTY;
					}
					bDead = true;
					deadNum = deadNum + size;
				}
			}
			
			// ����Ľ��ŵ�
			if (bDead == false) {
				if (qizi.isDeadQiZi(pointState, address)) {
					qizi.clear();
					pointState[address] = EMPTY;
					qiziCount--;
					return;
				}
			}
			repaint();

			mapStation.put(qiziCount, address);
			
			// ��������������ֱ����ò�ͬ������
			if(deadNum == 0) {
				try {
					PlaySound.play("src/sound/PlayMove.wav");
				} catch(Exception e){
					System.out.println("�Ҳ������ļ�");
				}
			} else if(deadNum < 5) {
				try {
					PlaySound.play("src/sound/killLittle.wav");
				} catch(Exception e){
					System.out.println("�Ҳ������ļ�");
				}
			} else {
				try {
					PlaySound.play("src/sound/killMore.wav");
				} catch(Exception e){
					System.out.println("�Ҳ������ļ�");
				}
			}
		}
		
		
	}

	// ��ʼ���������������е��λ��
	public void initPoints() {
		xPoints = new ArrayList<Integer>();
		yPoints = new ArrayList<Integer>();
		pointState = new Integer[POINTCOUNT];
		for (int i = 0; i < pointState.length; i++) {
			pointState[i] = EMPTY;
		}
		for (int i = 0; i < 19; i++) {
			xPoints.add(i * SPACE + QIPANGSPACE);
			yPoints.add(i * SPACE + QIPANGSPACE);
		}
	}
	
	// ��ʾ����
	public void showNumber(Graphics g) {
		
		for(int i=0; i<pointState.length; i++) {
			if(pointState[i] != EMPTY) {
				for(int j=mapStation.size(); j>0; j--) {
					if(mapStation.get(j) == i) {
						if(j%2 == 0) {
							g.setColor(Color.black);
						} else {
							g.setColor(Color.white);
						}
						String str = ""+j;
						g.drawString(str, qiziPoint(i).x - str.length()*3, qiziPoint(i).y + 5);
						break;
					}
				}
			}
		}
	}
	
	public Point qiziPoint(int target) {
		Point point = new Point();
		point.x = target%19*SPACE + QIPANGSPACE;
		point.y = target/19*SPACE + QIPANGSPACE;
		return point;
	}
	
	public void paint(Graphics g) {
		drawBoard(g);
		drawLine(g);
		drawXY(g);
		drawAllPoint(g);
		this.drawExistQiZi(g);
		if(showNum) {
			this.showNumber(g);
		}
		g.setColor(Color.black);
		if (qiziCount % 2 == 0) {
			g.drawString("�ֵ�����", 10, 10);
		} else {
			g.drawString("�ֵ�����", 10, 10);
		}
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		QiPang qipang = new QiPang();
		f.getContentPane().add(qipang);
		f.setBounds(new Rectangle(550, 550));
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static QiPang getCurrentQiPang() {
		return currentQiPang;
	}

	public void setCurrentQiPang() {
		QiPang.currentQiPang = this;
	}

	public Integer[] getPointState() {
		return pointState;
	}

	public void setPointState(Integer[] pointState) {
		this.pointState = pointState;
	}
}
