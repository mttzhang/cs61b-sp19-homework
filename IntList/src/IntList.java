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
     *  an easy way to create a new IntList
     * @param x int with no specific number
     * @return a new IntList consisting of int xs
     */
    public static IntList of(int... x){
        IntList head = new IntList(x[0],null);
        IntList current = head;
        for(int index = 1; index < x.length ;index++){
            IntList list = new IntList(x[index],null);
            current.rest = list;
            current = current.rest;
        }
        return head;
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
    public static IntList dcatenate(IntList A, IntList B){
        if(A == null){
            return A = IntList.copy(B);

        }
        IntList temp = A;
        while(temp.rest!=null){
            temp = temp.rest;
        }
        temp.rest = B;
        return A;
    }

    /**
     * returns a list consisting of all elements of A and following by B
     * @param A a given IntList
     * @param B a given IntList
     */
    public static IntList catenate(IntList A, IntList B){

        IntList listB = IntList.copy(B);
        if(A == null){
            return listB;
        }

        IntList listA = IntList.copy(A);
        IntList pointer = listA;

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
        if(A == null){
            return null;
        }
        IntList tail;
        if(A.rest == null){
            return tail = new IntList(A.first, null);
        }
        IntList newList = new IntList(A.first,IntList.copy(A.rest));
        return newList;
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
        //IntList c = new IntList(3,null);
        //IntList b = new IntList(2,c);
        //IntList a = new IntList(1,b);
        //IntList.printIntList(a);
        //dSquareList(a);
        //IntList.printIntList(a);
        //IntList a1= squareListRecursive(a);
        //IntList.printIntList(a);
        //IntList.printIntList(a1);
        //IntList a2 = squareListIterative(a);
        //IntList.printIntList(a);
        //IntList.printIntList(a2);

        IntList list = IntList.of(1,4,3);
        IntList.printIntList(list);
        IntList a3 = IntList.copy(list);
        IntList.printIntList(a3);
        IntList.printIntList(IntList.catenate(list,a3));
        IntList.printIntList(list);
        IntList.printIntList(a3);
        IntList.dcatenate(list,a3);
        IntList.printIntList(list);


    }
}
