import mpi.*;
import java.util.*;

public class MPIBitonicSort{
		
		/* "dir" indicates sorting direction: ASCENDING or DESCENDING */ 
	public void compAndSwap(int a[], int i, int j, int dir){
		if ( (a[i] > a[j] && dir == 1) || (a[i] < a[j] && dir == 0)){ // Swapping elements
			int buffer = a[i];
			a[i] = a[j];
			a[j] = buffer;
		}
	}
		
		
	/* Recursively merges bitonic sequences starting at position "low", 
	The parameter "j" is index of elements to be sorted */
	public void bitonicMerge(int a[], int low, int j, int dir){
		if (j > 1){
			int k = j/2;
			for (int i=low; i < low + k; i++){ 
				compAndSwap(a,i, i + k, dir);
			}
			bitonicMerge(a, low, k, dir);
			bitonicMerge(a, low + k, k, dir);
		}
	}
		
	/* Produce a bitonic sequence by recursively sorting two halves and merges */
	public void bitonicSort(int a[], int low, int j, int dir, int base){
		if (j > base){
			int k = j / 2;
			bitonicSort(a, low, k, 1, base); // sort in ascending order
			bitonicSort(a, low + k, k, 0, base); // sort in descending order
			bitonicMerge(a, low, j, dir); // Merge whole sequence in de/ascending order
		}
	}

		
	/* Driver to initiate array bitonic sort in a given order */
	public void sort(int a[], int size, int dir, int base){
		bitonicSort(a, 0, size, dir, base);
	}

	/* A utility function to print array of size n */
	public static String printArray(int arr[]){
		return Arrays.toString(arr);
	}


	public static void main(String[] args) throws Exception{
		MPI.Init(args);
		MPIBitonicSort ob = new MPIBitonicSort();
		int dir = 1;
		int base = 1;  // recoursion depth 
		int tag = 1;
		int root = 0;
		int rank = MPI.COMM_WORLD.Rank();
		int size = MPI.COMM_WORLD.Size();
		int arr1[] = {30, 1, 17, 6, 3, 18, 15, 19, 27, 7, 20, 14, 21, 11, 31, 28, 22, 4, 23, 10, 32, 24, 29, 13, 8, 12, 25, 26, 2, 16, 5, 9};
		int new_arr_size = (arr1.length/(size-1));
		
		if(rank == root) {
			int dst = 0;
			System.out.println("From <" + rank +"> initial array: " + Arrays.toString(arr1));

			//TODO: 1 Implement root process "Send" call to each worker a proportional part of the input array
			

			//TODO: 5 Implement "Receive" call by root from all workers to store all sorted sub-array back into intput array 			
			
			
			System.out.println("From <" + rank +"> final array: " + Arrays.toString(arr1));
			
			
			//TODO: 6 code for "root" process optimized bitonic search.						

			
			System.out.println("From <" + rank +"> sorted array: " + Arrays.toString(arr1));	
		}
		else {
			int arr2[] = new int[new_arr_size];
			
			//TODO: 2 - Implement worker process "Receive" call from root and display the received sub-array.
			
			
			System.out.println("Process <" + rank +"> Unsorted: " + Arrays.toString(arr2)); // showing received unsorted array

			//TODO: 6 code for "worker" optimized bitonic search.			
			
			
			//TODO: 3 Implement worker process to perform bitonic-sort of received sub-array and display the sorted array.
			
			
			System.out.println("\nProcess <" + rank + "> Sorted " + ob.printArray(arr2)); // showing sent sorted array

			//TODO: 4 Implement worker process "Send" call to sent it's sorted array back to root.


		}
		MPI.Finalize(); 
	}
}

