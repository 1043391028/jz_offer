package guanweizheng.jz_63_middleNumberInDataStream;

// 思路4：双堆；思路如下：
//       -> 将读入的数据分为几乎数量相同的两部分，一部分数字小，另一部分大。小的一部分采用大顶堆存放，大的一部分采用小顶堆存放。
//          这样两个堆的堆顶就是整个数据流中，最中间的两个数。当总个数为偶数时，使两个堆的数目相同，
//          则中位数=大顶堆的最大数字与小顶堆的最小数字的平均值；而总个数为奇数时，使小顶堆的个数比大顶堆多一，则中位数=小顶堆的最小数字。
//插入的步骤如下：
//　　1.若已读取的个数为偶数（包括0）时，两个堆的数目已经相同，再插入一个数时，应该选一个数插入到小顶堆中，从而实现小顶堆的个数多一。
//      但是，不能直接插到小顶堆，本应该选择一个数加入到小顶堆中，但是必须选一个较大的数放入小顶堆，而插入的这个数不一定符合要求（大顶堆的数不服它），
//      所以这个数要和大顶堆的最大数（先进大顶堆）打一群架，谁赢了（谁大）谁进小顶堆。
//　　2。若已读取的个数为奇数时，小顶堆的个数多一，所以要将某个数字插入到大顶堆中，此时方法与上面类似。
//       新进来的数要和小顶堆的堆顶（最小值）打一架，打输的（更小的那个数）进入大顶堆。
//    ->本方法的空间复杂度是O(1)，空间复杂度是O(logn)，相比于以上几个方法，可以说是最优选择。因此也是大家使用最多的解法。
//     堆有多种方式实现，数组或者基于队列实现。这里使用PriorityQueue实现，PriorityQueue默认是一个小顶堆，因此我们需要自己实现大顶堆,
//     这里传入自定义的Comparator函数可以实现大顶堆

import java.util.PriorityQueue;

public class Solution4 {
    PriorityQueue<Integer> minQue = new PriorityQueue<>();
    PriorityQueue<Integer> maxQue = new PriorityQueue<>((o1,o2)->o2-o1);
    int count = 0;  //计数，计算目前加入两个堆中总个数；
    public void Insert(Integer num) {
//      如果当前加入个数为偶数个，则加入小顶堆，在此之前需要先加入大顶堆比较，把最大的弹出大顶堆，然后加入小顶堆；
        if(count%2 == 0){
            maxQue.offer(num);
            int max = maxQue.poll();
            minQue.offer(max);
        }else
//            如果加入个数为奇数，则加入到大顶堆，同样在此之前，先进入小顶堆比较，弹出最小数，然后加入到大顶堆
        {
            minQue.offer(num);
            int min = minQue.poll();
            maxQue.offer(min);
        }
        count++;
    }

    public Double GetMedian() {
        //当前为偶数个，则取小顶堆和大顶堆的堆顶元素求平均
        if(count % 2 == 0){
            return new Double(minQue.peek() + maxQue.peek())/2;
        }else{
            //当前为奇数个，则直接从小顶堆中取元素即可，所以我们要保证小顶堆中的元素的个数。
            return ((double)minQue.peek());
        }

    }

}
