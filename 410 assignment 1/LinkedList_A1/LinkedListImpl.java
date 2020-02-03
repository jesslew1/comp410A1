/**
 * COMP 410
 *See inline comment descriptions for methods not described in interface.
 *
*/
package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {
	  Node headCell; //this will be the entry point to your linked list (the head)
	  Node lastCell; // this is the Node at the end of the list... the starting place
	                 // if you wanted to traverse the list backwards
	  private Node getNode (int index){
			if (index >= this.size() || index < 0) {
				return null;
			}
			Node currNode = headCell;
			for (int i = 0; i < index; i++) {
				currNode = currNode.getNext();
			}
			return currNode;
	  }
	  public LinkedListImpl(){//this constructor is needed for testing purposes. Please don't modify!
	    headCell = null; //Note that the root's data is not a true part of your data set!
	    lastCell = null;
	  }
	  
	  //implement all methods in interface, and include the getRoot method we made for testing 
	  // purposes. Feel free to implement private helper methods!

	 // add the fields you need to add to make it work... like a 
	  
	  public Node getRoot(){ //leave this method as is, used by the grader to grab your linkedList easily.
	    return headCell;
	  }
	  public Node getLast(){ //leave this method as is, used by the grader to grab your linkedList easily.
	    return lastCell;
	  }

  	@Override
	public boolean insert(double elt, int index) {
  		if (index == 0) {
  			Node newNode = new Node(elt);
  			if (headCell != null) {
  				Node currNode = headCell;
  	  			newNode.next = currNode;
  	  			currNode.prev = newNode;
  	  			if (headCell == lastCell) {
  	  				lastCell = currNode;
  	  			}
  	  			headCell = newNode;
  			} else {
  				headCell = newNode;
  				lastCell = headCell;
  			}
  			
  			return true;
  		} else if (index == this.size()){
  			Node newNode = new Node(elt);
  			if (lastCell != null) {
  				Node currNode = lastCell;
  	  			currNode.next = newNode;
  	  			newNode.prev = currNode;
  	  			lastCell = newNode;
  			} else {
  				lastCell = newNode;
  			}
  			
  			return true;
  		} else if (index > this.size() || index < 0) {
  			return false;
  		} else {
  			Node currNode = getNode(index);
  	  		Node prevNode = getNode (index-1);
  	  		Node newNode = new Node(elt);
  	  		newNode.next = currNode; 
  	  		newNode.prev = prevNode;
  	  		prevNode.next = newNode;
  	  		currNode.prev = newNode;
  	  		return true;
  		}
	}
	
	@Override
	public boolean insort(double elt) {
		// TODO Auto-generated method stub
		Node currNode = headCell;
		Node newNode = new Node(elt);
		Node nextNode = currNode.next;
		while (currNode != null) {
			if (elt >= currNode.getData() && elt < currNode.next.getData()) {
				newNode.prev = currNode;
				currNode.next = newNode;
				newNode.next = nextNode;
				nextNode.prev = newNode;
			} else if (elt < currNode.getData()){
				//all bigger
				currNode.prev = newNode;
				newNode.next = currNode;
				headCell = newNode;
			} else if (elt > currNode.getData()) {
				//all smaller
				currNode.next = newNode;
				newNode.prev = currNode;
				lastCell = newNode;
			} else if (headCell == null && lastCell == null) {
				//empty list
				headCell = newNode;
				lastCell = newNode;
			} else if (headCell == currNode && lastCell == currNode && elt < currNode.getData()){
				//head=last
				currNode.prev = newNode;
				newNode.next = currNode;
				headCell = newNode;
				lastCell = currNode;
			} else if (headCell == currNode && lastCell == currNode && elt >= currNode.getData()){
				//head=last
				currNode.next = newNode;
				newNode.prev = currNode;
				headCell = currNode;
				lastCell = newNode;
			}
			currNode = currNode.next; 
			return true;
		} 

		return false;
	}
	
	@Override
	public boolean remove(int index) {
		// TODO Auto-generated method stub
		Node currNode = getNode(index);
		int size = this.size();
		if (index == 0) {
			headCell = getNode(index + 1);
			getNode(index+1).prev = null;
			currNode.next = null;
			size--;
			return true;
		} else if (index == this.size() - 1) {
			getNode(index-1).next = null;
			currNode.prev = null;
			lastCell = getNode(index-1);
			size--;
			return true;
		} else if (index >= this.size() || index < 0) {
			return false;
		} else {
			getNode(index-1).next = getNode(index+1);
			getNode(index+1).prev = getNode(index-1);
			currNode.next = null;
			currNode.prev = null;
			size--;
			return true;
		}
	}
	
	@Override
	public double get(int index) {
		Node currNode = this.getNode(index);
		if (index >= this.size() || index < 0) {
			return Double.NaN;
		}
//		Node currNode = headCell;
//		for (int i = 0; i < index; i++) {
//			currNode = currNode.getNext();
//		}
		return currNode.getData();
	}
	
	@Override
	public int size() {
		if (headCell == null) {
			return 0;
		}
		Node currNode = headCell;
		int bunnyHops = 1;
		while (currNode != lastCell) {
			currNode = currNode.getNext();
			bunnyHops+=1;
		}
		return bunnyHops;
	}
	
	@Override
	public boolean isEmpty() {
		return headCell != null;
	}
	
	@Override
	public void clear() {
		headCell = null;
		lastCell = null;
		return;
	}
}