import java.util.Collections;
import java.util.Stack;

public class Chain {

    private ChainElement head;
    private ChainElement tail;
    private int left;
    private int right;

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
        this.left = ce.getTile().getLeft();

    }

    public void insertRight(Tile tile){
        ChainElement ce = new ChainElement(tile);
        ce.setPrevious(head);
        this.head = ce;
        this.right = ce.getTile().getRight();
    }

    public boolean isEmpty(){
        return head.getTile() == null && tail.getTile() == null;
    }

    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder();
        
        ChainElement tail = this.tail;
        
        while (tail != null){
            sb.append(tail.getTile().toString());
            tail = tail.getNext();
        }

        ChainElement head = this.head;

        Stack<ChainElement> tmp = new Stack<>();

        while (head != null) {
            tmp.push(head);
            head = head.getPrevious();
        }

        Collections.reverse(tmp);

        for (ChainElement element:tmp){
            sb.append(element.getTile().toString());
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        
        Stock stock = new Stock();

        Chain chain = new Chain();

        Tile draw;

        for (int i = 0; i < 5; i++){

            draw = stock.draw();
            System.out.println(draw.toString());
            chain.insertLeft(draw);

            draw = stock.draw();
            System.out.println(draw.toString());
            chain.insertRight(draw);
        }

        System.out.println(chain.toString());
    }
}
