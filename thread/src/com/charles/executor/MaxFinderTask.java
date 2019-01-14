package com.charles.executor;

import java.util.concurrent.Callable;

/**
 * 找出最大值任务
 */
public class MaxFinderTask implements Callable<Integer> {

    private int[] data;
    private int start;
    private int end;

    MaxFinderTask(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        int max = Integer.MIN_VALUE;
        //找出数组中某一部分的最大值
        for (int i = start; i < end; i++) {
            if (max < data[i]) {
                max = data[i];
            }
        }
        return max;
    }
}
