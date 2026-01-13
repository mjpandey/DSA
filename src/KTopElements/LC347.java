package KTopElements;


import java.util.*;

/*
347. Top K Frequent Elements
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
Example 1:
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
 */
public class LC347 {
    public static void main(String[] args) {
        System.out.println("LC347:: Running in Progress");
        int k=2;
        List<Integer> result = new LC347().topKFrequent(new int[] {1,1,1,2,2,3}, 2);
        System.out.println(k + " Largest Elements in given Array = "+ result);

    }


//Solution1:
// use maxHeap. Put entry into maxHeap so we can always poll a number with largest frequency

        public List<Integer> topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            for(int n: nums){
                map.put(n, map.getOrDefault(n,0)+1);
            }

            PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
                    new PriorityQueue<>((a,b)->(b.getValue()-a.getValue()));
            for(Map.Entry<Integer,Integer> entry: map.entrySet()){
                maxHeap.add(entry);
            }

            List<Integer> res = new ArrayList<>();
            while(res.size()<k){
                Map.Entry<Integer, Integer> entry = maxHeap.poll();
                res.add(entry.getKey());
            }
            return res;
        }
    }

/*

//Solution 2:
    // use treeMap. Use freqncy as the key so we can get all freqencies in order
    public class Solution {
        public List<Integer> topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            for(int n: nums){
                map.put(n, map.getOrDefault(n,0)+1);
            }

            TreeMap<Integer, List<Integer>> freqMap = new TreeMap<>();
            for(int num : map.keySet()){
                int freq = map.get(num);
                if(!freqMap.containsKey(freq)){
                    freqMap.put(freq, new LinkedList<>());
                }
                freqMap.get(freq).add(num);
            }

            List<Integer> res = new ArrayList<>();
            while(res.size()<k){
                Map.Entry<Integer, List<Integer>> entry = freqMap.pollLastEntry();
                res.addAll(entry.getValue());
            }
            return res;
        }
    }

    Solution3:

    // use an array to save numbers into different bucket whose index is the frequency
    public class Solution {
        public List<Integer> topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            for(int n: nums){
                map.put(n, map.getOrDefault(n,0)+1);
            }

            // corner case: if there is only one number in nums, we need the bucket has index 1.
            List<Integer>[] bucket = new List[nums.length+1];
            for(int n:map.keySet()){
                int freq = map.get(n);
                if(bucket[freq]==null)
                    bucket[freq] = new LinkedList<>();
                bucket[freq].add(n);
            }

            List<Integer> res = new LinkedList<>();
            for(int i=bucket.length-1; i>0 && k>0; --i){
                if(bucket[i]!=null){
                    List<Integer> list = bucket[i];
                    res.addAll(list);
                    k-= list.size();
                }
            }

            return res;
        }
    }
 */
