import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joe Meszar (jwm54@pitt.edu)
 */
public class PropertyTestArraysTest {
    private static Integer[][] arrays;
    private static Integer[][] sortedArrays;
    private static Random random = new Random();
    private static final int testLength = 666; // # of arrays to create
    
    /**
     * Build an array of int arrays. This 2D object will have 9001
     *      randomly-sized arrays inside of it--and those randomly-
     *      sized arrays will each have randomly-assigned values.
     * 
     *      NOTE: - Each randomly-sized array will have a size
     *              between (0) and (32767).
     * 
     *            - Each randomly-assigned value will have a value
     *              between (-32768) and (32767)
     * 
     * Once the array is built, a sorted copy of that array will
     *      be created to compare against.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println(String.format("setUpClass: Creating a 2D, %s-sized array of "
                + "randomly-sized arrays with random values.", testLength));
        
        // Create a new integer array of the given size.
        arrays = new Integer[testLength][]; 
        sortedArrays = new Integer[testLength][];
        
        // Fill the array with randomly-sized arrays,
        //      and randomly-assigned integer values.
        int randomArrayLength;
        Integer[] array;
        for (int i=0; i<arrays.length; i++) {
            // create an array of random length between (0, testLength)
            randomArrayLength = random.nextInt(testLength);
            if (i==0) { randomArrayLength=0; } // force at least one zero-length array
            
            array = new Integer[randomArrayLength];

            // fill the array with random integer values, positive and negative
            for (int j=0; j<randomArrayLength; j++) {
                // random number between (-32768) and (32767)
                array[j] = random.nextInt(32767+32768) - 32768;
            }
            
            // place the randomly-created array into the 2D arrays list
            arrays[i] = array;
        }
        
        System.out.println("\tCopying the 2D array into a new object that will be sorted.");
        
        // create a copy of the 2D arrays object
        for (int i=0; i<arrays.length; i++) {
            sortedArrays[i] = arrays[i].clone();
        }
        // make sure the copy is equal to the original
        assertArrayEquals(arrays, sortedArrays);
        
        // sort this new copy
        for (Integer[] arr : sortedArrays) {
            Arrays.sort(arr);
        }
        // the sorted arrays should NOT be equal to the original array
        assertFalse(Arrays.equals(arrays, sortedArrays));
        
        System.out.println("\tsetUpClass: Success!");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testArrayCloneEquals() {
        System.out.println("testArrayCloneEquals: Making sure a cloned copy of an array is equal to the original array.");
        
        // create a clone of the original 2D array of int arrays
        Integer[][] cloneArray = cloneArray(arrays);
        
        // make sure the clone is equal to the original
        assertArrayEquals(arrays, cloneArray);

        System.out.println("\ttestArrayCloneEquals: Success!");
    }
    
    /**
     * Test that the Arrays.sort(array) method correctly sorts into Ascending manner.
     */
    @Test
    public void testArraySortAsc() {
        System.out.println("testArraySortAsc: Sorting all of the arrays in ascending order.");
        
        // create a clone of the original 2D array of int arrays
        Integer[][] ascArrays = cloneArray(arrays);
        
        // sort the clone into ascending order using Arrays.sort
        System.out.println(String.format("\tPerforming ascending sort on each of the %s arrays "
                + "and testing they have the same length and hash...", testLength));
        
        int length, origHash;
        for (Integer[] arr : ascArrays) {
            length = arr.length;
            origHash = arr.hashCode();
            
            Arrays.sort(arr);
            
            // simple test to make sure length is the same
            assertEquals(String.format("Ascending sort failed: The size of the sorted array "
                    + "(%s) is different than the original array (%s)", arr.length, length), 
                    arr.length, length);
            
            // the pre-calculated hash should be the same
            assertEquals(String.format("Descending sort failed: The hash of the sorted array "
                    + "(%s) is different than the original array (%s)", arr.hashCode(), origHash), 
                    arr.hashCode(), origHash);
        }
        
        // make sure the arrays are actually sorted correctly in ASCENDING order
        for (Integer[] arr : ascArrays) {
            
            // test only if more than one element exists in the array
            if (arr.length > 1) {
                Integer prev = arr[0];
                for (int i=1; i<arr.length; i++) {
                    assertTrue(String.format("Ascending sort failed: current value '%s' less than "
                            + "previous value '%s'", arr[i], prev), arr[i] >= prev);
                    
                    prev = arr[i]; // keep this array for reference
                }
            }
        }
        
        System.out.println("\ttestArraySortAsc: Success!");
    }
    
    /**
     * Test that the Arrays.sort(array, Collections.reverseOrder()) method 
     *      correctly sorts into Descending manner.
     */
    @Test
    public void testArraySortDesc() {
        System.out.println("testArraySortDesc: Sorting all of the arrays in descending order.");
        
        // create a clone of the original 2D array of int arrays
        Integer[][] descArrays = cloneArray(arrays);
        
        // sort the clone into descending order using Arrays.sort
        System.out.println(String.format("\tPerforming descending sort on each of the %s arrays "
                + "and testing they have the same length and hash...", testLength));
        
        int length, origHash;
        for (Integer[] arr : descArrays) {
            length = arr.length;
            origHash = arr.hashCode();
            
            Arrays.sort(arr, Collections.reverseOrder());
            
            // simple test to make sure length is the same
            assertEquals(String.format("Descending sort failed: The size of the sorted array "
                    + "(%s) is different than the original array (%s)", arr.length, length), 
                    arr.length, length);
            
            // the pre-calculated hash should be the same
            assertEquals(String.format("Descending sort failed: The hash of the sorted array "
                    + "(%s) is different than the original array (%s)", arr.hashCode(), origHash), 
                    arr.hashCode(), origHash);
        }
        
        // make sure the arrays are actually sorted correctly in DESCENDING order
        for (Integer[] arr : descArrays) {
            
            // test only if more than one element exists in the array
            if (arr.length > 1) {
                Integer prev = arr[0];
                for (int i=1; i<arr.length; i++) {
                    assertTrue(String.format("Descending sort failed: current value '%s' greater than "
                            + "previous value '%s'", arr[i], prev), arr[i] <= prev);
                    
                    prev = arr[i]; // keep this array for reference
                }
            }
        }
        
        System.out.println("\ttestArraySortDesc: Success!");
    }
    
    /**
     * Helper method for cloning an array of int arrays.
     * 
     * @param origArray The original array of int arrays to clone.
     * @return A cloned version of the original array of int arrays.
     */
    public Integer[][] cloneArray(Integer[][] origArray) {
        Integer[][] cloneArray = new Integer[origArray.length][];
        
        // create a copy of the 2D arrays object
        for (int i=0; i<origArray.length; i++) {
            cloneArray[i] = origArray[i].clone();
        }
        System.out.println("\tMaking sure the cloned array equals the original array...");
        assertArrayEquals(origArray, cloneArray);
        
        System.out.println("\tMaking sure the cloned array's deep hash is the same as the original array...");
        assertEquals(Arrays.deepHashCode(origArray), Arrays.deepHashCode(cloneArray));
        
        return cloneArray;
    }
}
