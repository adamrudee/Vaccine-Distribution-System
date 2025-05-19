public class Queue <E> 
{
    private LinkedList <E> list;
    
    public Queue() {
        list = new LinkedList<E>();
    }
    
    public void enqueue(E data) {
        list.insertAtBack(data);
    }
    
    public E dequeue() {
        return list.removeFirst();
    }
    
    public E getFront() {
        return list.getHead();
    }
    
    public void sortByExpirationDate() {
        list.sortByExpirationDate();
    }
    
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
