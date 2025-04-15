public class ChainElement {
    
    private Tile tile;
    private ChainElement nexElement = null;

    public ChainElement(Tile tile){
        this.tile = tile;
    }

    //setters and getters
    public Tile getTile(){
        return this.tile;
    }

    public ChainElement getNext(){
        return this.nexElement;
    }
        
    public void setNext(ChainElement element){
        nexElement = element;
    }
        
}
