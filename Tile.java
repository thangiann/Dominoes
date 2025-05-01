public class Tile {

    private int left;
    private int right;

    //constructor
    public Tile(int n1, int n2){
        this.left = Math.min(n1, n2);
        this.right = Math.max(n1, n2);
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

    //methods 
    @Override
    public String toString(){
        return String.format("[%d|%d]", this.left, this.right);
    }

    public void rotate(){
        int tmp = this.left;
        this.left = this.right;
        this.right = tmp;
    }

    public int points(){
        return this.left + this.right;
    }
}
