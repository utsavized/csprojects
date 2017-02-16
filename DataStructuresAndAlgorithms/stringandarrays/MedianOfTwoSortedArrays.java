package stringandarrays;

public class MedianOfTwoSortedArrays {
	public double findMedianSortedArrays(int A[], int B[]) {
        return getMedianRec(A, B, 0, A.length - 1, A.length);
    }
	
	/* A recursive function to get the median of ar1[] and ar2[]
	   using binary search */
	double getMedianRec(int ar1[], int ar2[], int left, int right, int n)
	{
	    int i, j;
	 
	    /* We have reached at the end (left or right) of ar1[] */
	    if (left > right)
	        return getMedianRec(ar2, ar1, 0, n-1, n);
	 
	    i = (left + right)/2;
	    j = n - i - 1;  /* Index of ar2[] */
	 
	    /* Recursion terminates here.*/
	    if (ar1[i] > ar2[j] && (j == n-1 || ar1[i] <= ar2[j+1]))
	    {
	        /* ar1[i] is decided as median 2, now select the median 1
	           (element just before ar1[i] in merged array) to get the
	           average of both*/
	        if (i == 0 || ar2[j] > ar1[i-1])
	            return (ar1[i] + ar2[j])/2;
	        else
	            return (ar1[i] + ar1[i-1])/2;
	    }
	 
	    /*Search in left half of ar1[]*/
	    else if (ar1[i] > ar2[j] && j != n-1 && ar1[i] > ar2[j+1])
	        return getMedianRec(ar1, ar2, left, i-1, n);
	 
	    /*Search in right half of ar1[]*/
	    else /* ar1[i] is smaller than both ar2[j] and ar2[j+1]*/
	        return getMedianRec(ar1, ar2, i+1, right, n);
	} 
}
