public class Chain {

    private ChainElement head;
    private ChainElement tail;
    private int left;
    private int right;

    //constructor
    public Chain(ChainElement ce){
        this.head = ce;
        this.tail = ce;
        this.left = ce.getTile().getLeft();
        this.right = ce.getTile().getRight();
    }

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
        this.head.setNext(ce);

        this.head = ce;
        this.right = this.head.getTile().getRight();
    }

    public boolean isEmpty(){
        return head.getTile() == null && tail.getTile() == null;
    }

    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder();
        
        ChainElement ce = this.tail;
        
        while (ce != null){
            sb.append(ce.getTile().toString());
            ce = ce.getNext();
        }
        
        return sb.toString();
    }

    public static void main(String[] args) {
        
        Stock stock = new Stock();

        Tile draw = stock.draw();
        Chain chain = new Chain(new ChainElement(draw));

        for (int i = 0; i < 5; i++){

            draw = stock.draw();
            System.out.println("tile is " + draw.toString());
            chain.insertLeft(draw);

            draw = stock.draw();
            System.out.println(draw.toString());
            chain.insertRight(draw);
        }

        System.out.println("right is " + chain.getHead().getTile().toString());
        System.out.println(chain.toString());
    }
}
