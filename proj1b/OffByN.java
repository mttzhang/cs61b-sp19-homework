public class OffByN implements CharacterComparator {

    /**
     * instance variable
     */
    private int n;

    /**
     * constructor
     */
    public OffByN(int N) {
        this.n = N;
    }

    /**
     * Returns true if characters are equal if their diff is N.
     *
     * @param x
     * @param y
     */
    @Override
    public boolean equalChars(char x, char y) {
        int diff = Math.abs(x - y);
        return diff == n;
    }
}
