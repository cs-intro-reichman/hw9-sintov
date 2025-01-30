/**
 * Represents a list of Nodes. 
 */
public class LinkedList {
	
	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	private int size;   // number of elements in this list
	
	/**
	 * Constructs a new list.
	 */ 
	public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}
	
	/**
	 * Gets the first node of the list
	 * @return The first node of the list.
	 */		
	public Node getFirst() {
		return this.first;
	}

	/**
	 * Gets the last node of the list
	 * @return The last node of the list.
	 */		
	public Node getLast() {
		return this.last;
	}
	
	/**
	 * Gets the current size of the list
	 * @return The size of the list.
	 */		
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		Node current = this.first;
		for (int i = 0; i < index; i++){
			if(current == null){
				return null;
			}
			current = current.next;
		}
		return current;
	}
	
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		if (index == 0) {
			addFirst(block);
		}else{
			if (index == size){
				addLast(block);
			}else{
				Node newNode = new Node(block);
				Node saveNode = this.first;
				for(int i = 1; i < index; i++){
					saveNode = saveNode.next;
				}
				newNode.next = saveNode.next;
				saveNode.next = newNode;
				this.size++;
			}
		}
	}

	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		Node newNode = new Node(block);
		if (this.first == null){
			this.first = newNode;
			this.last = newNode;
		}else{
		this.last.next = newNode;
		this.last = newNode;
		}
		this.size++;
	}
	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
		Node newNode = new Node(block);
		if(first == null){
			this.first = newNode;
			this.last = this.first;
			this.first.next = last;
		}else{
			newNode.next = this.first;
			this.first = newNode;
		}
		if(this.first.next == null){
			this.first.next = this.last;
		}
		this.size++;
	}

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		if (index < 0 || index > size || this.first == null) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		Node current = this.first;
		for (int i = 0; i < index; i++){
			current = current.next;
		}
		return current.block;
	}	

	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		Node newNode = this.first;
		for (int i=0; i<this.size; i++){
			if(newNode.block == block){
				return i;
			}
			newNode = newNode.next;
		}
		return -1;
	}

	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	public void remove(Node node) {
		if(node == null){
			throw new NullPointerException();
		}
		if (this.first != null)
		{ 
			if (this.size == 1 && node == this.first){
				this.first = null;
				this.last = null;
				this.size = 0;
			}else{
				if(node == first){
					this.first = this.first.next;
					this.size --;
					if(this.size ==1){
						this.last = this.first;
					}
				}else{
					Node currentNode = this.first;
					while (currentNode.next != null) {
						if(currentNode.next == node && node == last) {
							currentNode.next = null;
							this.size --;
							last = currentNode;
							if (this.size == 1) {
								last = first;
							}
							return;
						}
						if (currentNode.next == node) {
							currentNode.next = currentNode.next.next;
							this.size --;
							if(this.size == 1) {
								this.last = this.first;
							}
							return;
						}
						currentNode = currentNode.next;
					}
				}
			}
	}
}

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		if (this.size != 0){
			if(size == 1){
				this.first = null;
				this.last = null;
				this.size = 0;
			}else{
				remove(getBlock(index));
			}
		}
	}

	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {
		if(block == null || indexOf(block) == -1) {
			throw new IllegalArgumentException("index must be between 0 and size");
		}
		Node current = this.first;
		if(this.first == null){
			return;
		}
		if(this.size == 1 && block.equals(first.block)) {
			this.first = null;
			this.last = null;
			this.size = 0;
			return;
		}
		if (block == this.first.block) {
			this.first = this.first.next;
			this.size --;
			if (this.size == 1) {
				this.last = this.first;
			}
			return;
		}
		for(int i = 0 ; i < size ; i ++) {
			if(current.next.block == block && block == this.last.block) {
				current.next = null;
				this.size --;
				this.last = current;
				if (this.size == 1) {
					this.last = this.first;
				}
				return;
			}
			if (current.next.block == block) {
				current.next = current.next.next;
				this.size --;
				if(this.size == 1) {
					this.last = this.first;
				}
				return;
			}
			current = current.next;
		}
	}
		
	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		return new ListIterator(first);
	}
	
	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {
		String result = "";
    	Node current = first;
    	for(int i = 0 ; i < size ; i ++) {
        	result += current.block.toString() + " "; 
        	current = current.next; 
		}
    	return result;
	}

	public void sort() {
		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size - i - 1; j++) {
				Node currentNode = getNode(j);
				Node next = currentNode.next;
				if (currentNode.block.baseAddress > next.block.baseAddress) {
					MemoryBlock temp = currentNode.block;
					currentNode.block = next.block;
					next.block = temp;
				}
			}
		}
}
}