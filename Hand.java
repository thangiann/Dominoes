import java.util.ArrayList;

public class Hand {
    
    ArrayList<Tile> hand = new ArrayList<>();
    
    //getters
    public ArrayList<Tile> getHand(){
        return this.hand;
    }

    //methods
    public void add(Tile tile){
        this.hand.add(tile);
    }

    public Tile getTile(int index){
        return hand.remove(index);
    }

    public Tile getBestTile(Board board){
        int points = 0;
        int bestIndex = -1;

        for (int i = 0; i < hand.size(); i++){

            if (bestIndex == -1 && board.match(hand.get(i))) {
                points = hand.get(i).points();
                bestIndex = i;
            }

            else if (hand.get(i).points() > points && board.match(hand.get(i))) {
                points = hand.get(i).points();
                bestIndex = i;
            }
        }

        if (bestIndex == -1) {
            return null;
        }
        else{ return hand.remove(bestIndex);}
    }

    public boolean isEmpty(){
        return hand.isEmpty();
    }

    public int points(){
        int points = 0;

        for (Tile tile:hand){
            points += tile.points(); 
        }

        return points;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        for (Tile tile:hand){
            sb.append(tile.toString() + " ");
        }

        sb.append("\n " + " " );

        for (int i = 0; i < hand.size(); i++){
            sb.append(+ i + "     ");
        }

        return sb.toString();
    }
}
