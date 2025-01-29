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
		for (int i = 1; i < index; i++){
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
		if (first == null){
			first = newNode;
			last = newNode;
			this.first.next = this.last;
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
			first.next = last;
		}else{
			newNode.next = first;
			first = newNode;
		}
		if(first.next == null){
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
		if (index < 0 || index > size) {
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
		for (int i=0; i<this.size; i++){
			if(this.getBlock(i) == block){
				return i;
			}
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
		int nodeIndex = 0;
		if(this.getNode(nodeIndex) == node){
			Node newFirst = this.first.next;
			this.first.next = null;
			this.first = newFirst;
		}else{
		while(this.getNode(nodeIndex).next != node){
			nodeIndex++;
		}
		if(this.getNode(nodeIndex).next == this.last){
			this.getNode(nodeIndex).next = null;
			this.last = this.getNode(nodeIndex);
		}else{
			Node toSave = this.getNode(nodeIndex).next.next;
			this.getNode(nodeIndex).next.next = null;
			this.getNode(nodeIndex).next = toSave;
		}
	}
		this.size--;
	}

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		int nodeIndex = 0;
		if(index == 0){
			Node newFirst = this.first.next;
			this.first.next = null;
			this.first = newFirst;
		}else{
		while(this.getNode(nodeIndex).next != this.getNode(index)){
			nodeIndex++;
		}
		if(this.getNode(nodeIndex).next == this.last){
			this.getNode(nodeIndex).next = null;
			this.last = this.getNode(nodeIndex);
		}
		else{
			Node toSave = this.getNode(nodeIndex).next.next;
			this.getNode(nodeIndex).next.next = null;
			this.getNode(nodeIndex).next = toSave;
		}
	}
		this.size--;
	}

	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {
		int nodeIndex = 0;
		if(this.getBlock(0) == block){
			Node newFirst = this.first.next;
			this.first.next = null;
			this.first = newFirst;
		}else{
			while(this.getNode(nodeIndex).next.block != block || nodeIndex < this.size-1){
				nodeIndex++;
			}
			if (nodeIndex == this.size - 1) {
				throw new IllegalArgumentException(
					"index must be between 0 and size");
			}
			if(this.getNode(nodeIndex).next.block == this.last.block){
				this.getNode(nodeIndex).next = null;
				this.last = this.getNode(nodeIndex);
			}else{
				Node toSave = this.getNode(nodeIndex).next.next;
				this.getNode(nodeIndex).next.next = null;
				this.getNode(nodeIndex).next = toSave;
			}
		}
		this.size --;
		
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
		//// Replace the following statement with your code
		return "";
	}
}