import java.util.Formatter;

/**
 * A naked recursive list of integers, similar to what we saw in lecture 3, but
 * with a large number of additional methods.
 *
 * @author P. N. Hilfinger, with some modifications by Josh Hug and melaniecebula
 *         [Do not modify this file.]
 */
public class IntList {
    /**
     * First element of list.
     */
    public int first;
    /**
     * Remaining elements of list.
     */
    public IntList rest;

    /**
     * A List with first FIRST0 and rest REST0.
     */
    public IntList(int first0, IntList rest0) {
        first = first0;
        rest = rest0;
    }

    /**
     * A List with null rest, and first = 0.
     */
    public IntList() {
    /* NOTE: public IntList () { }  would also work. */
        this(0, null);
    }

    /**
     * modify the list so that all the elements are squared
     * @param L the given IntList
     */
    public static void dSquareList(IntList L){

        while (L.rest != null){
            L.first = L.first * L.first;
            L = L.rest;
        }
        L.first = L.first * L.first;

    }

    /**
     * return a new list with all elements squared, using iteration.
     * The list is not modified
     * @param L given list
     * @return a new version list whose elements are squared
     */
    public static IntList squareListIterative(IntList L){
        if(L == null){
            return null;
        }
        IntList newL = new IntList(0,null);
        IntList pointer = newL;
        while(L.rest!=null){
            pointer.first = L.first * L.first;
            pointer.rest = new IntList(0,null);
            pointer = pointer.rest;
            L = L.rest;
        }
        pointer.first = L.first * L.first;
        return  newL;
    }

    /**
     * return a new version of list with all elements squared, using recursion
     * @param L the given list
     * @return a new version of list whose elements are all squared
     */
    public static IntList squareListRecursive(IntList L){
        IntList newL = new IntList(L.first * L.first,null);
        if(L.rest == null){
            return newL;
        }
        newL.rest = squareListRecursive(L.rest);
        return newL;
    }

    /**
     * returns a  list consisting of all elements of A, followed by all elements of B,
     * using a destructive way(modify A)
     * @param A given IntList
     * @param B a given IntList
     */
    public static void dcatenate(IntList A, IntList B){
        IntList temp = A;
        while(temp.rest!=null){
            temp = temp.rest;
        }
        temp.rest = B;
    }

    /**
     * returns a list consisting of all elements of A and following by B
     * @param A a given IntList
     * @param B a given IntList
     */
    public static IntList catenate(IntList A, IntList B){
        IntList listA = IntList.copy(A);
        IntList pointer = listA;
        IntList listB = IntList.copy(B);
        while(pointer.rest != null){
            pointer = pointer.rest;
        }
        pointer.rest = listB;
        return listA;

    }

    /**
     * return a new IntList that copy A
     * @param A a given IntList
     * @return a new IntList
     */
    public static IntList copy(IntList A){
        IntList tail;
        if(A.rest == null){
            return tail = new IntList(A.first, null);
        }
        IntList newList = new IntList(A.first,IntList.copy(A.rest));
        return newList;
    }
















    /**
     * DO NOT MODIFY ANYTHING BELOW THIS LINE! Many of the concepts below here
     * will be introduced later in the course or feature some form of advanced
     * trickery which we implemented to help make your life a little easier for
     * the lab.
     */

    @Override
    public int hashCode() {
        return first;
    }

    /**
     * Returns a new IntList containing the ints in ARGS. You are not
     * expected to read or understand this method.
     */
    public static IntList of(Integer... args) {
        IntList result, p;

        if (args.length > 0) {
            result = new IntList(args[0], null);
        } else {
            return null;
        }

        int k;
        for (k = 1, p = result; k < args.length; k += 1, p = p.rest) {
            p.rest = new IntList(args[k], null);
        }
        return result;
    }

    /**
     * Returns true iff X is an IntList containing the same sequence of ints
     * as THIS. Cannot handle IntLists with cycles. You are not expected to
     * read or understand this method.
     */
    public boolean equals(Object x) {
        if (!(x instanceof IntList)) {
            return false;
        }
        IntList L = (IntList) x;
        IntList p;

        for (p = this; p != null && L != null; p = p.rest, L = L.rest) {
            if (p.first != L.first) {
                return false;
            }
        }
        if (p != null || L != null) {
            return false;
        }
        return true;
    }

    /**
     * If a cycle exists in the IntList, this method
     * returns an integer equal to the item number of the location where the
     * cycle is detected.
     * <p>
     * If there is no cycle, the number 0 is returned instead. This is a
     * utility method for lab2. You are not expected to read, understand, or
     * even use this method. The point of this method is so that if you convert
     * an IntList into a String and that IntList has a loop, your computer
     * doesn't get stuck in an infinite loop.
     */

    private int detectCycles(IntList A) {
        IntList tortoise = A;
        IntList hare = A;

        if (A == null) {
            return 0;
        }

        int cnt = 0;


        while (true) {
            cnt++;
            if (hare.rest != null) {
                hare = hare.rest.rest;
            } else {
                return 0;
            }

            tortoise = tortoise.rest;

            if (tortoise == null || hare == null) {
                return 0;
            }

            if (hare == tortoise) {
                return cnt;
            }
        }
    }

    @Override
    /** Outputs the IntList as a String. You are not expected to read
     * or understand this method. */
    public String toString() {
        Formatter out = new Formatter();
        String sep;
        sep = "(";
        int cycleLocation = detectCycles(this);
        int cnt = 0;

        for (IntList p = this; p != null; p = p.rest) {
            out.format("%s%d", sep, p.first);
            sep = ", ";

            cnt++;
            if ((cnt > cycleLocation) && (cycleLocation > 0)) {
                out.format("... (cycle exists) ...");
                break;
            }
        }
        out.format(")");
        return out.toString();
    }
}

