package stevesun.algorithms;

import java.util.*;
/**
 * Created by fishercoder1534 on 9/30/16.
 */
public class LongestConsecutiveSequence {
    //inspired by this solution: https://discuss.leetcode.com/topic/29286/my-java-solution-using-unionfound
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap();//<value, index>
        UnionFind uf = new UnionFind(nums);
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])) continue;
            map.put(nums[i], i);
            if(map.containsKey(nums[i]-1)){
                uf.union(i, map.get(nums[i]-1));//note: we want to union this index and nums[i]-1's root index which we can get from the map
            }
            if(map.containsKey(nums[i]+1)){
                uf.union(i, map.get(nums[i]+1));
            }
        }
        return uf.maxUnion();
    }

    class UnionFind{
        int[] ids;

        public UnionFind(int[] nums){
            ids = new int[nums.length];
            for(int i = 0; i < nums.length; i++) ids[i] = i;
        }

        public void union(int i, int j){
            int x = find(ids, i);
            int y = find(ids, j);
            ids[x] = y;
        }

        public int find(int[] ids, int i){
            while(i != ids[i]){
                ids[i] = ids[ids[i]];
                i = ids[i];
            }
            return i;
        }

        public boolean connected(int i, int j){
            return find(ids, i) == find(ids, j);
        }

        public int maxUnion(){//this is O(n)
            int max = 0;
            int[] count = new int[ids.length];
            for(int i = 0; i < ids.length; i++){
                count[find(ids, i)]++;
                max = max < count[find(ids, i)] ? count[find(ids, i)] : max;
            }
            return max;
        }
    }

    class Solution_using_HashSet{
    //inspired by this solution: https://discuss.leetcode.com/topic/25493/simple-fast-java-solution-using-set
        public int longestConsecutive(int[] nums) {
            if(nums == null || nums.length == 0) return 0;

            Set<Integer> set = new HashSet();
            for(int i : nums) set.add(i);
            int max = 1;

            for(int num : nums){
                if(set.remove(num)){
                    int val = num;
                    int count = 1;
                    while(set.remove(val-1)) val--;//we find all numbers that are smaller than num and remove them from the set
                    count += num - val;

                    val = num;
                    while(set.remove(val+1)) val++;//then we find all numbers that are bigger than num and also remove them from the set
                    count += val - num;

                    max = Math.max(max, count);
                }
            }
            return max;
        }

    }
}
