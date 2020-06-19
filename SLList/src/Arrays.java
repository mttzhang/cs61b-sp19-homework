public class Arrays {
    /**
     * insert an item in the given position, for example, given arr [5,9,14,15],
     * insert item 6 in the position 2, -->[5,9,6,14,15]
     * or insert item 6 in the position 10,-->[5,9,14,15,6]
     * @param arr the given array
     * @param item the item to be inserted
     * @param position the given postion to insert
     * @return
     */
    public static int[] insert(int[] arr, int item, int position){
        int[] newArray = new int[arr.length + 1];
        int count = 0;
        while (count < position && count < arr.length ){
            newArray[count] = arr[count];
            count ++;
        }
        if(position > arr.length){
            newArray[arr.length] = item;
            return newArray;
        }
        newArray[position] = item;

        while(count < arr.length){
            newArray[count + 1] = arr[count];
            count++;
        }
        return newArray;
    }

    public static void printIntArray(int[] arr){
        System.out.println("The elements in the int [] are:");
        for(int i :arr){
            System.out.print(i+" ");
        }
        System.out.println("\n-------------");
    }

    /**
     * reverse [1, 2, 3] --> [3, 2, 1]
     * @param arr the array needed to be reversed
     */
    public static void reverse(int[] arr){
        int newArray[] = new int [arr.length];
        int count = 0;
        for(int i : arr){
            newArray[arr.length - 1 - count] = i;
            count++;
        }
        for (int j = 0; j < arr.length; j++){
            arr[j] = newArray[j];
        }
    }

    /**
     * [3, 2 ,1] --> [3, 3, 3, 2, 2, 1]
     * @param arr
     * @return
     */
    public static int[] replicate(int[] arr){
        int sum = 0;
        for(int x: arr){
            sum += x;
        }
        int index = 0;
        int[] newArr = new int[sum];
        for(int x :arr){
            for(int i = 1; i <= x; i++){
                newArr[index] = x;
                index++;
            }
        }
        return newArr;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
        Arrays.printIntArray(arr);
        arr = Arrays.insert(arr,100,4);
        Arrays.printIntArray(arr);
        Arrays.reverse(arr);
        Arrays.printIntArray(arr);

        int[] arr1 = {1,2,3,4};
        arr1 = Arrays.replicate(arr1);
        Arrays.printIntArray(arr1);

    }
}
