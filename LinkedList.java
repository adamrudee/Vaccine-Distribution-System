public class LinkedList<E> {
    private Node<E> head, tail;
    private Node<E> current;

    // Constructor to initialize an empty LinkedList
    public LinkedList() {
        head = current = tail = null;
    }

    // Insert a new element to the front of the list
    public void insertAtFront(E data) {
        Node newNode = new Node(data);
        newNode.next = this.head;
        this.head = newNode;
        if (this.tail == null) {
            this.tail = this.head;
        }
    }

    // Insert a new element to the back of the list
    public void insertAtBack(E data) {
        Node newNode = new Node(data);

        if (this.tail == null) {
            this.head = this.tail = newNode;
        } else {
            this.tail.next = newNode;
            this.tail = this.tail.next;
        }
    }

    // Removes from anywhere from the list
    public boolean remove(E data) {
        if (this.isEmpty()) {
            return false; // If the list is empty, there's nothing to remove
        }

        if (this.head.data.equals(data)) { // If the head node contains the target data
            this.head = this.head.next; // Update head to the next node
            if (this.head == null) {
                this.tail = null; // If the list is now empty, reset tail
            }
            return true;
        }

        Node<E> prev = null;
        Node<E> current = this.head;

        // Traverse the list to find the target node
        while (current != null && !current.data.equals(data)) {
            prev = current;
            current = current.next;
        }

        if (current == null) { // If the target node is not found
            return false;
        }

        if (current == this.tail) { //If the target node is the tail
            this.tail = prev; //Update tail to the previous node
        }

        if (prev != null) { //Update the next reference of the previous node
            prev.next = current.next;
        }

        return true; //Node successfully removed
    }
    
    // Removes from anywhere from the first
    public E removeFirst(){
        if (this.isEmpty()) {
            return null;
        }
        else {
            this.current = this.head;
            this.head = this.head.next;            
            if (this.head == null)
                this.tail = null;
            return current.data;
        }
    }

    // Returns the data stored in the head node
    public E getHead() {
        if (this.isEmpty()) {
            return null; // If the list is empty, return null
        } else {
            this.current = this.head; // Set current to the head
            return this.current.data;
        }
    }

    // Returns the data stored in the tail node
    public E getLast() {
        if (this.isEmpty()) {
            return null; // If the list is empty, return null
        } else {
            return this.tail.data; // Return the data in the tail
        }
    }

    // Moves the current pointer to the next node and returns its data
    public E getNext() {
        if (this.current == this.tail) { // If current is at the tail
            return null; // There's no next node
        } else {
            this.current = this.current.next; // Move to the next node
            return this.current.data;
        }
    }

    // Returns the size of the list (number of nodes)
    public int size() {
        int count = 0;
        Node<E> current = this.head;
        while (current != null) {
            count++; // Increment the count for each node
            current = current.next;
        }
        return count; // Return the total count
    }

    // Checks if the list is empty
    public boolean isEmpty() {
        return head == null;
    }
    
    //sort method for earliest date
    public void sortByExpirationDate() {
        boolean swapped = true; // Flag to check if we need to swap again
        while (swapped) {
            swapped = false;
            Node <E> current = head;
    
            // Traverse through the list
            while (current != null && current.next != null) {
                // Check if the current and next nodes' data are instances of Vaccine
                if (current.data instanceof Vaccine && current.next.data instanceof Vaccine) {
                    Vaccine currentVaccine = (Vaccine) current.data;
                    Vaccine nextVaccine = (Vaccine) current.next.data;
    
                    // Compare the expiration dates of the two vaccines
                    if (currentVaccine.getExpirationDate().compareTo(nextVaccine.getExpirationDate()) > 0) {
                        // Swap the data between the two nodes
                        E temp = current.data; //The data of the current node is temporarily stored in the variable temp.
                        current.data = current.next.data; //Replace current.data with current.next.data
                        current.next.data = temp; //Assign the original current.data (stored in temp) to current.next.data
                        swapped = true; // A swap was made, so we need to check again
                    }
                }
                current = current.next; // Move to the next node in the list
            }
        }
    }

    // Displays all elements in the list
    public void display() {
        if (this.isEmpty()) {
            System.out.println("List is empty");
        } else {
            Node<E> current = this.head;
            while (current != null) {
                System.out.print(current.data + " -> "); // Print each node's data
                current = current.next;
            }
            System.out.println("null"); // Indicate the end of the list
        }
    }
}
