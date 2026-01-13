package KTopElements;


import java.util.PriorityQueue;

/*
215. Kth Largest Element in an Array
Given an integer array nums and an integer k, return the kth largest element in the array.
Can you solve it without sorting?
Example 1:
Input: nums = [3,2,1,5,6,4], k = 2
Output: 5

Complexity
Time Complexity: O(nlogk)
Each of the n elements is processed once. However, heap operations take O(logk) time, leading to an overall complexity of O(nlogk).

Space Complexity: O(k)
The solution uses a heap with a maximum of k elements.
 */
public class LC215 {
    public static void main(String[] args) {
        System.out.println("LC215:: Running in Progress");
        int k=2;
        int result = new LC215().findKthLargest(new int[] {3, 2, 1, 5, 6, 4}, 2);
        System.out.println(k + " Largest Elements in given Array = "+ result);

    }
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }

        for (int i = k; i < nums.length; i++) {
            if (nums[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }

        return minHeap.peek();
    }
}
