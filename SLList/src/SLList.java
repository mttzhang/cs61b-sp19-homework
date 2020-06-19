public class SLList {
    private class IntNode{
        public int item;
        public IntNode next;
        public IntNode(int item, IntNode next){
            this.item = item;
            this.next = next;
        }



    }

    private IntNode first;

    public void addFirst(int x){
        first = new IntNode(x,first);
    }

    /**
     * inserts x in the given position
     * @param item the item to insert
     * @param position the given position to insert the int
     */
    public void insert(int item,int position){
        if (position == 0||first==null){
            addFirst(item);
        }
        else {
            IntNode NextBeChanged = first;
            for(int i = 0; i < position-1;i++){
                if(NextBeChanged.next != null){
                    NextBeChanged = NextBeChanged.next;
                }
                else {
                    break;
                }
            }
            IntNode toBeChanged = NextBeChanged.next;
            toBeChanged = new IntNode(item, toBeChanged);
            NextBeChanged.next = toBeChanged;
        }
    }

    /**
     * reverse the elements
     */
    public void reverse(){
        if(this.first==null){
            return;
        }

        IntNode body = first.next;
        first.next = null;

        while (body.next!=null){
            IntNode tail= body.next;
            body.next = first;
            first = body;
            body = tail;
        }
        body.next = first;
        first = body;
    }



    /**
     * print all the elements in the SLList
     */
    public void printLList(){
        IntNode printLList = first;
        System.out.println("The elements in the SLList are:");
        while(printLList.next!=null){
            System.out.print(printLList.item+",");
            printLList = printLList.next;
        }
        System.out.print(printLList.item);
        System.out.println("\n"+"------------------");

    }

    public static void main(String[] args) {
        SLList l = new SLList();

        //l.insert(100,0);
        l.insert(99,1);

        l.addFirst(1);
        l.addFirst(2);
        l.insert(9,0);
        l.insert(8,8);
        l.insert(7,1);
        l.printLList();
        l.reverse();
        l.printLList();
    }
}
