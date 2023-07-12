import java.io.Serializable;

public class HFNode<T> implements Serializable {

	public T item;
	public int freq;
	public HFNode<T> left;
	public HFNode<T> right;

	public HFNode(T item, int freq) {
		this.item = item;
		this.freq = freq;
	}

	public HFNode(T item, int freq, HFNode<T> left, HFNode<T> right) {
		this.item = item;
		this.freq = freq;
		this.left = left;
		this.right = right;
	}

	boolean isLeaf() {
		return left == null && right == null;
	}

	public int compareTo(HFNode<T> other) {

		if (this.freq > other.freq) {
			return 1;
		} else if (this.freq < other.freq) {
			return -1;
		}
		return 0;

	}

}
