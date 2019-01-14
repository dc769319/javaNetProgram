package com.charles.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 开启多个线程，执行任务
 * 找出一个数组中最大的指，将数组分成多个任务，每个任务，求一部分数组的最大值。
 * 等所有任务返回后，求出所有返回值中的最大值
 */
public class MultiThreadMaxFinder {
    public static void main(String[] args) {
        int[] data = {81, 22, 33, 121, 2, 45, 23, 76, 35, 13, 65, 90, 12, 45, 76, 65, 33, 67};
        MultiThreadMaxFinder finder = new MultiThreadMaxFinder();
        try {
            System.out.println(finder.find(data));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private int find(int[] data) throws InterruptedException, ExecutionException {
        int mIndex = data.length / 2;
        MaxFinderTask task1 = new MaxFinderTask(data, 0, mIndex);
        MaxFinderTask task2 = new MaxFinderTask(data, mIndex, data.length);
        //创建一个线程池，该线程池重用固定数量的从共享无界队列中运行的线程
        ExecutorService service = Executors.newFixedThreadPool(2);
        //提交任务以执行
        Future<Integer> future1 = service.submit(task1);
        Future<Integer> future2 = service.submit(task2);
        //找出最大值
        return Integer.max(future1.get(), future2.get());
    }
}
