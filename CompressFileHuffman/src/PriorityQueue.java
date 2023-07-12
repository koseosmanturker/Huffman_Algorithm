
public class PriorityQueue {
	
	private static final int CAP = 1000000;
	private HFNode<Character>[] list = new HFNode[CAP];
	private int size;
	
	public PriorityQueue() {
		size = 0;
	}

	public HFNode<Character> peek() {
		return list[0];
	}

	public void insert(HFNode<Character> item) {
		
		if(size < CAP) {
			list[size] = item;
			heapUp(size);
			size++;
		}
		else {
			throw new RuntimeException("Over the capacity!");
		}
		
	}

	public HFNode<Character> deleteMin() {
		
		if(size == 0) {
			throw new RuntimeException();
		}
		
		HFNode<Character> itm = list[0];
		list[0] = list[size - 1];
		size--;
		heapDown(0);
		
		return itm;
	}

	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		
		if(size == 0) {
			return true;
		}
		
		return false;
	}
	
	private void heapDown(int loc) {
		
		while(loc < size) {
			
			if(Right(loc) < size) {
				
				if(list[Left(loc)].freq <= list[Right(loc)].freq) {
					
					if(list[loc].freq > list[Left(loc)].freq) {
						loc = swap(loc, Left(loc));
						continue;
					}
					else {
						break;
					}
				}
				
				if(list[Right(loc)].freq <= list[Left(loc)].freq) {
					
					if(list[loc].freq > list[Right(loc)].freq) {
						loc = swap(loc, Right(loc));
						continue;
					}
					else {
						break;
					}
					
				}
				
			}
			
			else if(Left(loc) < size) {
				
				if(list[loc].freq > list[Left(loc)].freq) {
					loc = swap(loc, Left(loc));
					continue;
				}
				else {
					break;
				}
			}
			else {
				break;
			}
		}
			
	}
		
	
	
	private void heapUp(int loc) {
		
		while(loc > 0) {
			
			if(list[loc].freq < list[Parent(loc)].freq) {
				loc = swap(loc, Parent(loc));	
			}
			else {
				break;
			}
			
		}
		
	}
	
	private int Parent(int n) {
		return (n - 1) / 2;
	}
	
	private int Right(int n) {
		return (2 * n) + 2;
	}
	
	private int Left(int n) {
		return (2 * n) + 1;
	}
	
	public int swap(int loc, int swapLoc) {
		
		HFNode<Character> e = list[loc];
		list[loc] = list[swapLoc];
		list[swapLoc] = e;
		loc = swapLoc;
		
		return loc;
	}

}
