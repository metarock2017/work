package readable;
import java.nio.*;
import java.util.*;

public class RandomWords implements Readable{
	private int readCount = 1;
	private int loopCount = 1;
	public RandomWords() {}
	public int read(CharBuffer cb) {
		if (loopCount == 1) {
			System.out.println("����readִ�е�" + readCount + "��");
			readCount++;
			cb.append("aa bb cc ");
			loopCount++;
			return 1;
		}
		if (loopCount == 2) {
			System.out.println("����readִ�е�" + readCount + "��");
			readCount++;
			loopCount++;
			return 1;
		}
		if (loopCount == 3) {
			System.out.println("����readִ�е�" + readCount + "��");
			readCount++;
			loopCount++;
			return 1;
		}
		if (loopCount == 4) {
			System.out.println("����readִ�е�" + readCount + "��");
			readCount++;
			loopCount++;
			return 1;
		}
		if (loopCount == 5) {
			System.out.println("����readִ�е�" + readCount + "��");
			readCount++;
			loopCount++;
			return 1;
		}
		
		if (loopCount == 6) {
			System.out.println("����readִ�е�" + readCount + "��");
			readCount++;
			cb.append("oo pp qq");
			loopCount++;
			return 1;
		}
		if (loopCount == 7) {
			System.out.println("����readִ�е�" + readCount + "��");
			readCount++;
			loopCount++;
			return 1;
		}
		if (loopCount == 8) {
			System.out.println("����readִ�е�" + readCount + "��");
			readCount++;
			loopCount++;
			return 1;
		}
		if (loopCount == 9) {
			System.out.println("����readִ�е�" + readCount + "��");
			readCount++;
			loopCount++;
			return 1;
		}
		if (loopCount == 10) {
			System.out.println("����readִ�е�" + readCount + "��");
			readCount++;
			return -1;
		}
		return 1;
	}
	
	public static void main(String[] args) {
		int a = 1;
		
		System.out.println("1");
		Scanner s = new Scanner(new RandomWords());
		System.out.println("2");
		
		while(s.hasNext()) {
			System.out.println("��" + a + "��ִ�����ѭ��");
			a++;
			System.out.println("��cb�ж���" + s.next());
		}
	}
}
