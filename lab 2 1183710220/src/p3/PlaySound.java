import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;


public class PlaySound {
	private String filepath;
	public static final int BUFSIZE = 9600;
	
	public PlaySound(String filepath) {
		this.setFilepath(filepath);
	}
	
	// 播放文件
	public static void play(String filepath) throws Exception {
		AudioInputStream in = AudioSystem.getAudioInputStream(new File(filepath));
		AudioFormat auf = in.getFormat();
		DataLine.Info info = new DataLine.Info(javax.sound.sampled.SourceDataLine.class, auf);
		SourceDataLine line = (SourceDataLine)AudioSystem.getLine(info);
		line.open(auf);
		line.start();
		
		// 开始读取并播放文件
		byte[] buffer = new byte[BUFSIZE];
		int readNum = 0;
		while(true) {
			readNum = in.read(buffer, 0, buffer.length);
			if(readNum != -1) {
				line.write(buffer, 0, readNum);
			} else {
				break;
			}
		}
		// 这个函数能让所有声音播放完;
		line.drain();
		line.close();
	}
	
	public void printFormateInfo(AudioFormat auf) {
		System.out.println("PlayMove.wav information:");
		System.out.println("channels is " + auf.getChannels());
		System.out.println("encoding is " + auf.getEncoding());
		System.out.println("frameRate is " + auf.getFrameRate());
		System.out.println("frameSize is " + auf.getFrameSize());
		System.out.println("sampleRate is " + auf.getSampleRate());
		System.out.println("sampleSizeInBits is " + auf.getSampleSizeInBits());
	}
	
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	public static void main(String[] args) throws Exception {
		PlaySound.play("src/sound/杨千嬅-美味情缘.wav");
	}
}
