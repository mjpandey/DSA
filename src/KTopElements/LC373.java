package KTopElements;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
373. Find K Pairs with Smallest Sums
You are given two integer arrays nums1 and nums2 sorted in non-decreasing order and an integer k.
Define a pair (u, v) which consists of one element from the first array and one element from the second array.
Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.

Example 1:
Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]]
Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 */
public class LC373 {
    public static void main(String[] args) {
        System.out.println("LC373:: Running in Progress");
        int k=2;
        List<List<Integer>> result = new LC373().kSmallestPairs(new int[] {1,7,11}, new int[] {2, 4, 6}, 3);
        System.out.println(k + " Largest Elements in given Array = "+ result);

    }
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // min queue, sorted by pair sum
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> (a[0] + a[1]) - (b[0] + b[1]));
        List<int[]> res = new ArrayList();
        int N1 = nums1.length, N2 = nums2.length;
        if (N1 == 0 || N2 == 0) return new ArrayList(); // no pairs to form, just return an empty res list
        // offer initial pairs {num1, num2, index_of_num2}
        for (int i = 0; i < Math.min(N1, k); i++) q.offer(new int[]{nums1[i], nums2[0], 0});
        // get 1st k elem into result, each time, offer potential better pairs into queue
        // if there r not enough pair, just return all pairs
        for (int i = 0; i < Math.min(N1 * N2, k); i++) {
            // get the best pair and put into res
            int[] cur = q.poll();
            res.add(new int[]{cur[0], cur[1]});
            // next better pair could with be A: {after(num1), num2} or B: {num1. after(num2)}
            // for A, we've already added top possible k into queue, so A is either in the queue already, or not qualified
            // for B, it might be a better choice, so we offer it into queue
            if (cur[2] < N2 - 1 ) { // still at least one elem after num2 in array nums2
                int idx = cur[2] + 1;
                q.offer(new int[]{cur[0], nums2[idx], idx});
            }
        }

        //return res;

        //convert to int[] as needed, not mandatory step;
        List<List<Integer>> result = res.stream()
                .map(arr -> Arrays.stream(arr).boxed().toList())
                .toList();

        return result;
    }

    // Fully worked LC Solution below, above solution failed one corner case in LC

    /*

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> resV = new ArrayList<>(); // Result list to store the pairs
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // Priority queue to store pairs with smallest sums, sorted by the sum

        // Push the initial pairs into the priority queue
        for (int x : nums1) {
            pq.offer(new int[]{x + nums2[0], 0}); // The sum and the index of the second element in nums2
        }

        // Pop the k smallest pairs from the priority queue
        while (k > 0 && !pq.isEmpty()) {
            int[] pair = pq.poll();
            int sum = pair[0]; // Get the smallest sum
            int pos = pair[1]; // Get the index of the second element in nums2

            List<Integer> currentPair = new ArrayList<>();
            currentPair.add(sum - nums2[pos]);
            currentPair.add(nums2[pos]);
            resV.add(currentPair); // Add the pair to the result list

            // If there are more elements in nums2, push the next pair into the priority queue
            if (pos + 1 < nums2.length) {
                pq.offer(new int[]{sum - nums2[pos] + nums2[pos + 1], pos + 1});
            }

            k--; // Decrement k
        }

        return resV; // Return the k smallest pairs
    }

     */
}
