import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class HuffmanTextDecomp {

	private HFNode<Character> root;
	private File file;
	private Map<String, Character> codeTable = new HashMap<>();

	public HuffmanTextDecomp() {
		this.root = null;
	}

	public HuffmanTextDecomp(File file) throws ClassNotFoundException, IOException {
		this.root = null;
		this.file = file;
		decompress();
	}

	private void decompress() throws IOException, ClassNotFoundException {

		FileInputStream fis = new FileInputStream(file.getName());
		ObjectInputStream ois = new ObjectInputStream(fis);
		DataInputStream dis = new DataInputStream(fis);
		root = (HFNode<Character>) ois.readObject();
		makeCode(root, "");

		File outFile = new File(file.getName() + ".decomp");
		PrintWriter writer = new PrintWriter(outFile);
		while (dis.available() > 0) {

			int format = dis.readInt();
			int numericValue = dis.readInt();

			String bits = Integer.toBinaryString(numericValue);
			if (numericValue <= 256) {
				bits = String.format("%" + format + "s", Integer.toBinaryString(numericValue & 0xFF)).replace(" ", "0");
			}

			writer.print(codeTable.get(bits));
		}

		writer.close();
		dis.close();
		ois.close();
		fis.close();

	}

	private void makeCode(HFNode<Character> node, String code) {

		if (node.isLeaf()) {
			codeTable.put(code, node.item);
			return;
		}

		makeCode(node.left, code + "0");
		makeCode(node.right, code + "1");

	}

}
