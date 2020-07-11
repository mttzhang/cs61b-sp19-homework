package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound;
    private Maze maze;
    private ArrayListDeque <Integer> list;

    private class ArrayListDeque<T> {

        int first;
        int last;
        int size;
        int capacity;
        T[] contents;

        public ArrayListDeque() {
            capacity = 16;
            contents = (T[]) new Object[capacity];
            size = 0;
            last = 0;
            first = 0;
        }

        public ArrayListDeque(int c) {
            capacity = c;
            contents = (T[]) new Object[capacity];
            size = 0;
            last = 0;
            first = 0;
        }
        public void addLast(T item) {
            if (item == null) {
                return;
            }
            if (isEmpty()) {
                last--;
            }
            //resize();
            size++;
            last++;
            contents[last] = item;
            last = updateIndex(last);
        }
        public int getSize() {
            return size;
        }
        public T getFirst() {
            return contents[first];
        }

        public boolean contain(T item) {
            for (int i = first; i < getSize(); i++) {
                i = Math.floorMod(i, capacity);
                if (contents[i].equals(item)) {
                    return true;
                }
            }
            return false;
        }
        public boolean isEmpty(){
            return size == 0;
        }
        public int updateIndex(int i) {
            return Math.floorMod(i, capacity);
        }
        public T removeFirst() {
            T result = contents[first];
            first++;
            first = updateIndex(first);
            size--;
            if(isEmpty()) {
                first--;
            }
            //resize();
            return result;
        }
        public void resize() {
            double ratio = (double) size / (double) capacity;
            if (ratio > 0.8) {
                capacity *= 2;
                T[] temp = (T[]) new Object[capacity];
                for (int i = 0; i < contents.length; i++) {
                    temp[i] = contents[i];
                }
                contents = temp;
            }

            if (ratio < 0.3 && size != 0) {
                capacity /= 2;
                T[] temp = (T[]) new Object[capacity];
                for (int i = 0; i < size; i++) {
                    temp[i] = contents[first + i];
                    first = 0;
                    last = size - 1;
                }
                contents = temp;
            }

        }
    }


    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = (Integer) m.xyTo1D(sourceX, sourceY);
        t = (Integer) m.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        targetFound = false;
        list = new ArrayListDeque<>(maze.V());
        for (int i = 0; i < marked.length; i++) {
            marked[i] = false;
        }

        // Add more variables here!
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs(Integer v) {
        marked[v] = true;
        announce();

        if (v == t) {
            targetFound = true;
        }
        if (targetFound) {
            return;
        }
        for (Integer adj :maze.adj(v)) {
            if (!marked[adj]) {
                list.addLast(adj);
                edgeTo[adj] = v;
                announce();
                distTo[adj] = distTo[v] + 1;

            }
        }
        while (!list.isEmpty()) {
            Integer curr = list.removeFirst();
            bfs(curr);

        }

        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
    }


    @Override
    public void solve() {
        bfs(s);
    }
}

