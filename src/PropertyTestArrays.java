
import java.util.Random;

/**
 *
 * @author Joe Meszar
 * 
 * - length same 
 * - once sorted only increasing
 * - class type same (array = array)
 * - sort sorted array results in the same
 */
public class PropertyTestArrays {
    public static Random random = new Random();
    
    public static void main(String args[]) {
        // create an int array to hold arrays
        int[][] arrays = createNewArrayHolder(9001); // "IT'S OVER 9000!!"
        
        // loop through and fill each array
        fillArray(arrays);
        
        System.out.println("asdfads");
    }
    
    /**
     * Creates a new int array of the given size. This array is used
     *      to hold int arrays.
     * 
     * @param size The size of the array to create.
     * @return An array of arrays, of the given size.
     */
    public static int[][] createNewArrayHolder(int size) {
        return new int[size][];
    }
    
    /**
     * Fills an array with randomly-sized arrays.
     * 
     * @param arrays A 2D integer array to be randomly-filled.
     */
    public static void fillArray(int[][] arrays) {
        for (int i=0; i<arrays.length; i++) {
            // fill this specific array position with a 
            //      randomly-sized array of random values
            arrays[i] = createRandomIntArray();
        }
    }
    
    /**
     * Creates a randomly-sized integer array of random ints. The array
     *      will be between the length of 0 and 32767
     * 
     * @return A random array of random values.
     */
    public static int[] createRandomIntArray() {
        // create an array of random length between (0, 32767)
        int arrLength = random.nextInt(32767);
        int[] array = new int[arrLength];
        
        // fill the array
        for (int i=0; i<arrLength; i++) {
            // random number between int min and max
            array[i] = random.nextInt(32767+32768) - 32768;
        }
        
        return array;
    }
}
