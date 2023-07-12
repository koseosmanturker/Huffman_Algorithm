import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HuffmanTextComp {

	private HFNode<Character> root;
	private int[] freq;
	private PriorityQueue pqueue = new PriorityQueue();
	private File file;
	private Map<Character, String> codes = new HashMap<>();

	public HuffmanTextComp() {
		this.root = null;
		this.freq = new int[256];
	}

	public HuffmanTextComp(File file) throws IOException {
		this.root = null;
		this.freq = new int[256];
		this.file = file;
		compress();
	}

	public void compress() throws IOException {
		setFreq(file);
		insertPrioQueue();
		buildHuffmanTree();
		makeCode(root, "");
		createFile();
	}

	private void setFreq(File file) throws FileNotFoundException {

		if (!file.exists()) {
			throw new RuntimeException("File Does Not Exist!");
		}

		Scanner scan = new Scanner(file);

		while (scan.hasNext()) {
			String line = scan.nextLine() + "\n";
			for (int i = 0; i < line.length(); i++) {
				char ch = line.charAt(i);
				freq[ch]++;
			}

		}

		scan.close();
	}

	private void insertPrioQueue() {
		for (int i = 0; i < freq.length; i++) {
			if (freq[i] > 0) {
				pqueue.insert(new HFNode<Character>((char) (i), freq[i]));
			}
		}
	}

	private void buildHuffmanTree() {
		while (pqueue.size() > 1) {
			HFNode<Character> left = pqueue.deleteMin();
			HFNode<Character> right = pqueue.deleteMin();
			HFNode<Character> parent = new HFNode<Character>('\0', (left.freq + right.freq), left, right);
			pqueue.insert(parent);
		}

		root = pqueue.deleteMin();
	}

	private void makeCode(HFNode<Character> node, String code) {

		if (node.isLeaf()) {
			codes.put(node.item, code);
			return;
		}

		makeCode(node.left, code + "0");
		makeCode(node.right, code + "1");

	}

	private void createFile() throws IOException {

		FileOutputStream fos = new FileOutputStream(file.getName() + ".hf");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		DataOutputStream dos = new DataOutputStream(fos);

		oos.writeObject(root);
		Scanner scan = new Scanner(file);

		while (scan.hasNext()) {
			String line = scan.nextLine() + "\n";
			for (int i = 0; i < line.length(); i++) {
				char ch = line.charAt(i);
				String code = codes.get(ch);
				int numericValue = Integer.parseInt(code, 2);
				dos.writeInt(code.length());
				dos.writeInt(numericValue);

			}

		}
		oos.close();
		dos.close();
		scan.close();

	}

}
