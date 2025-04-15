import java.util.Stack;

public class Chain {

    private ChainElement head;
    private ChainElement tail;
    private int left = tail.getTile().getLeft();
    private int right = head.getTile().getRight();

    //setters and getters
    public ChainElement getHead() {
        return head;
    }
    
    public void setHead(ChainElement head) {
        this.head = head;
    }
    
    public ChainElement getTail() {
        return tail;
    }
    
    public void setTail(ChainElement tail) {
        this.tail = tail;
    }
    
    public int getLeft() {
        return left;
    }
    
    public void setLeft(int left) {
        this.left = left;
    }
    
    public int getRight() {
        return right;
    }
    
    public void setRight(int right) {
        this.right = right;
    }

    //methods
    public void insertLeft(Tile tile){
        ChainElement ce = new ChainElement(tile);
        ce.setNext(tail);
        this.tail = ce;

    }

    public void insertRight(Tile tile){
        ChainElement ce = new ChainElement(tile);
        ce.setNext(head);
        this.head = ce;
    }

    public boolean isEmpty(){
        return head.getTile() == null && tail.getTile() == null;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        ChainElement head = this.head;

        Stack<ChainElement> tmp = new Stack<>();

        while (head.getNext() != null) {
            tmp.push(head);
            head = head.getNext();
        }

        for (ChainElement element:tmp){
            sb.append(element.getTile().toString());
        }

        ChainElement tail = this.tail;
        
        while (tail.getNext() != null){
            sb.append(tail.getTile().toString());
            tail = tail.getNext();
        }

        return sb.toString();
    }
    
}
