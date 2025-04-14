import java.util.Random;

public class Stock {

    private Tile[] stack = new Tile[28];

    Random random = new Random();

    public Stock(){
        int pos = 0;
        for (int i = 0; i < 7; i++){

            for (int j = i; j < 7; j++){

                Tile tile = new Tile(i, j);
                stack[pos] = tile;
                pos++;
            }
        }

        shuffle();
    }

    //helper method
    public void shuffle(){
        for (int i = stack.length; i > 0; i--){
            int j = random.nextInt(i);

            Tile tmp = stack[j];
            stack[j] = stack[i];
            stack[i] = tmp; 
        }
    }

    //methods
    public boolean isEmpty(){
        //check if every item in list is null(if it has been removed or "drew")
        return stack == null;
    }

    public Tile draw(){
        for (int i = 0; i < stack.length; i++){

            if (stack[i] != null) {

                //save the tile, remove it and then return it
                Tile tmp = stack[i];
                stack[i] = null;
                return tmp;
            }
        }

        return null;
    }

    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < stack.length; i++){
            sb.append(stack[i]);
        }

        return sb.toString();
    }
}
