import java.awt.*;

public class IntList {
    public int first;
    public IntList rest;

    /**
     * constructor
     */
    public IntList(int item, IntList next){
        first = item;
        rest = next;
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
     * returns a  list consisting of all elements of A, followed by all elements of B
     * @param A
     * @param B
     */
    public static void dcatenate(IntList A, IntList B){
        // TODO: 2020/6/19
    }

    public static void catenate(IntList A, IntList B){
        // TODO: 2020/6/19
    }

    public static void printIntList(IntList L){
        System.out.println("The elements in the IntList are:");
        while(L.rest != null){
            System.out.print(L.first + ",");
            L = L.rest;
        }
        System.out.println(L.first);
        System.out.println("--------------");
    }

    public static void main(String[] args) {
        IntList c = new IntList(3,null);
        IntList b = new IntList(2,c);
        IntList a = new IntList(1,b);
        IntList.printIntList(a);
        //dSquareList(a);
        //IntList.printIntList(a);
        IntList a1= squareListRecursive(a);
        IntList.printIntList(a);
        IntList.printIntList(a1);
        IntList a2 = squareListIterative(a);
        IntList.printIntList(a);
        IntList.printIntList(a2);


    }
}
