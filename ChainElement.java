public class ChainElement {
    
    private Tile tile;
    private ChainElement nextElement = null;
    private ChainElement previousElement = null;

    public ChainElement(Tile tile){
        this.tile = tile;
    }

    //setters and getters
    public Tile getTile(){
        return this.tile;
    }

    public ChainElement getNext(){
        return this.nextElement;
    }
        
    public void setNext(ChainElement element){
        nextElement = element;
    }
    
    public ChainElement getPrevious(){
        return this.previousElement;
    }
        
    public void setPrevious(ChainElement element){
        previousElement = element;
    }
}
