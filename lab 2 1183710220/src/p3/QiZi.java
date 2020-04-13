import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


public class QiZi {
	public static final int SIZE = 25;
	private static int count = 0;
	private Color white;
	
	private BitSet qiziAddress = null;
	private HashSet<Integer> qiziArray = null;
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;
	
	public QiZi(boolean b) {
		qiziAddress = new BitSet(8);
		white = new Color(255, 255, 255);
		if(b == true) {
			count++;
		}
	}
	
	public boolean isBlack() {
		if(count%2==1) {
			return true;
		}
		return false;
	}
	
	public boolean isBlack( Integer[] pointsState ,int order) {
		if(pointsState[order] == QiPang.BLACK) {
			return true;
		}
		return false;
	}
	
	public boolean isWhite( Integer[] pointsState ,int order) {
		if(pointsState[order] == QiPang.WHITE) {
			return true;
		}
		return false;
	}
	
	public void clear() {
		count--;
	}
	
	public boolean isHasEmpty(Integer[] pointsState ,int order) {
		int point = 0;
		for(int i=0; i<4; i++) {
			int a = -1;
			if(i==0 && order%19 != 0) {
				a = order - 1;
			} else if(i==1 && order > 18) {
				a = order - 19;
			} else if(i==2 && order%19 != 18) {
				a = order + 1;
			} else if (i== 3 && order<342){
				a = order + 19;
			}
			if( a != -1 && pointsState[a] == QiPang.EMPTY) {
				return true;
			}
			point = a;
		}
		return false;
	}
	
	public void drawQiZi(Graphics g, int address, boolean b) {
		int x = address%19;
		int y = address/19;
		int xSpace = x*QiPang.SPACE + QiPang.QIPANGSPACE;
		int ySpace = y*QiPang.SPACE + QiPang.QIPANGSPACE;
		if(b == true) {
			g.setColor(Color.black);
			g.fillOval(xSpace - SIZE/2, ySpace - SIZE/2, SIZE, SIZE);
		}
		else {
			g.setColor(white);
			g.fillOval(xSpace - SIZE/2, ySpace - SIZE/2, SIZE, SIZE);
			g.setColor(Color.black);
			g.drawOval(xSpace - SIZE/2, ySpace - SIZE/2, SIZE, SIZE);
		}
	}
	
	//设置第一位为为左边有相同的子，第二位为上边有相同的子，第三位为右边有相同的子，第四位为下边有相同的子
	public BitSet sameQiZi(Integer[] pointsState, int target) {
		int same = pointsState[target];
		BitSet bit = new BitSet(4);
		if(target%19!=0 && pointsState[target - 1] == same) {
			bit.set(0, true);
		}
		if(target>18 && pointsState[target - 19] == same) {
			bit.set(1, true);
		}
		if(target%19 != 18 && pointsState[target + 1] == same) {
			bit.set(2, true);
		}
		if(target<342 && pointsState[target + 19] == same) {
			bit.set(3, true);
		}
		return bit;
	}
	
	//设置第一位为为左边有不同的子，第二位为上边有不同的子，第三位为右边有不同的子，第四位为下边有不同的子
	public BitSet differQiZi(Integer[] pointsState, int target) {
		int differ = 0;
		if(isBlack(pointsState, target)) {
			differ = QiPang.WHITE;
		} else {
			differ = QiPang.BLACK;
		}
		BitSet bit = new BitSet(4);
		
		if(target%19==0 || pointsState[target - 1] == differ) {
			bit.set(0, true);
		}
		if(target<19 || pointsState[target - 19] == differ) {
			bit.set(1, true);
		}
		if(target%19 == 18 || pointsState[target + 1] == differ) {
			bit.set(2, true);
		}
		if(target>341 || pointsState[target + 19] == differ) {
			bit.set(3, true);
		}
		return bit;
	}
	
	// 记录该棋子身边的不同棋子的位置
	public Set<Integer> differQiZiArray(Integer[] pointsState, int target) { 
		Set<Integer> differArray = new HashSet<Integer>();
		BitSet differs = this.differQiZi(pointsState, target);
		if(differs.get(0) == true && target%19!=0) {
			differArray.add(target - 1);
		}
		if(differs.get(1) == true && target>18) {
			differArray.add(target - 19);
		}
		if(differs.get(2) == true && target%19 != 18) {
			differArray.add(target + 1);
		}
		if(differs.get(3) == true && target<342) {
			differArray.add(target + 19);
		}
		return differArray;
	}
	
	// 记录相同的棋子相连的位置 (递归算法——关联算法)
	public Map<Integer, Integer> relationQiZi(
									Integer[] pointsState, 
									int target, 
									Map<Integer, Integer> maps ) {
		
		if(maps.isEmpty()) {
			maps.put(target, -1);
		}
		
		// 当target左、上、右、下边有子的情况下
		for(int i=0; i<4; i++) {
			if(this.sameQiZi(pointsState, target).get(i) == true) {
				int a = -1;
				if(i==0) {
					a = target - 1;
				} else if(i==1) {
					a = target -19;
				} else if(i==2) {
					a = target + 1;
				} else if(i==3){
					a = target + 19; 
				} 
				if(a != -1) {
					if(maps.containsKey(a) == false) {
						maps.put(a, -1);
					} else {
						maps.put(a, 0);
					}
				}
			}
			maps.put(target, 0);
		}
				
		// 对map当中的值为-1的键赋值给target,并且此target不能等于赋值前的target
		Set<Integer> keys = maps.keySet();
		Iterator it = keys.iterator();
		int same = 0;
		while(it.hasNext()) {
			int key = (Integer)it.next();
			if(maps.get(key) == -1) {
				target = key;
				break;
			} else {
				same++;
			}
			if(same == maps.size()) {
				return maps;
			}
		}
		return this.relationQiZi(pointsState, target, maps);
	}
	
	// 重载——记录相同的棋子相连的位置
	public Integer[] relationQiZi(Integer[] pointsState, int target) {
		Map<Integer, Integer> maps = new Hashtable<Integer, Integer>();
		Integer[] members = new Integer[this.relationQiZi(pointsState, target, maps).size()];
		
		Set<Integer> keys = new HashSet<Integer>();
		keys = this.relationQiZi(pointsState, target, maps).keySet();
		int i=0;
		for(Iterator it = keys.iterator(); it.hasNext();) {
			members[i++] = (Integer)it.next();
		}
		return members;
	}
	
	// 返回身边相同棋子的数目 
	public int sameSideCount(Integer[] pointsState, int target) {
		int number = 0;
		for(int i=0; i<4; i++) {
			if(this.sameQiZi(pointsState, target).get(i) == true) {
				number++;
			}
		}
		return number;
	}
	
	// 返回身边不相同棋子的数目
	public int differSideCount(Integer[] pointsState, int target) {
		int number = 0;
		BitSet bits = this.differQiZi(pointsState, target);
		for(int i=0; i<4; i++) {
			if(bits.get(i) == true) {
				number++;
			}
		}
		return number;
	}
	
	// 返回一团棋子被包围的数目
	public int surroundedCount(Integer[] pointsState, int target) {
		Integer[] members = this.relationQiZi(pointsState, target);
		int count = 0;
		for(int i=0; i<members.length; i++) {
			count = count + this.differSideCount(pointsState, members[i]);
		}
		return count;
	}
	
	// 判断当前是否有死子
	public boolean isDeadQiZi(Integer[] pointsState, int target) {
		Integer[] sameArray = this.relationQiZi(pointsState, target);
		for(int i=0; i<sameArray.length; i++) {
			if(this.isHasEmpty(pointsState, sameArray[i])) {
				return false;
			}
		}
		return true;
	}
	public Integer[] handleDead(Integer[] pointsState, int target) {
		Integer[] deads = this.relationQiZi(pointsState, target);
		for(int i=0; i<deads.length; i++) {
			pointsState[deads[i]] = QiPang.EMPTY;
		}
		return pointsState;
	}
	
	// 经过处理之后，返回各个子的状态
	public Integer[] qiziStates(Integer[] pointsState) {
		return pointsState;
	}
}
