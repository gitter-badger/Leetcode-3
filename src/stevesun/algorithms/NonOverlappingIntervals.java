package stevesun.algorithms;

import stevesun.common.classes.Interval;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class NonOverlappingIntervals {
/**Looked at these two posts: https://discuss.leetcode.com/topic/65828/java-solution-with-clear-explain
 * and https://discuss.leetcode.com/topic/65594/java-least-is-most
 * Sort the intervals by their end time, if equal, then sort by their start time.*/
    public static int eraseOverlapIntervals(Interval[] intervals) {
        Collections.sort(Arrays.asList(intervals), new Comparator<Interval>(){
            @Override
            public int compare(Interval o1, Interval o2) {
                if(o1.end != o2.end) return o1.end - o2.end;
                else return o2.start - o1.start;
            }
        });
        int end = Integer.MIN_VALUE;
        int count = 0;
        for(Interval interval : intervals){
            if(interval.start >= end) end = interval.end;
            else count++;
        }
        return count;
    }
    
    public static void main(String...args){
        //[[1,100],[11,22],[1,11],[2,12]]
        Interval interval1 = new Interval(1,100);
        Interval interval2 = new Interval(11,22);
        Interval interval3 = new Interval(1,11);
        Interval interval4 = new Interval(2,12);
        Interval[] intervals = new Interval[]{interval1, interval2, interval3, interval4};
        
        
        System.out.println(eraseOverlapIntervals(intervals));
    }

}
