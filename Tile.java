public class Tile {
    private int left;
    private int right;

    //constructor
    public Tile(int n1, int n2){
        this.left = Math.min(n1, n2);
        this.right = Math.min(n1, n2);
    }

    // Getters and Setters
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

    
}
