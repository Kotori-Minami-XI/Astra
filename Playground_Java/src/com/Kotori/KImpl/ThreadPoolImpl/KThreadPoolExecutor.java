package com.Kotori.KImpl.ThreadPoolImpl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class KThreadPoolExecutor implements KExecutor{
    private volatile int corePoolSize;
    private volatile int maximumPoolSize;
    private volatile Long keepAliveTime;
    private volatile Boolean allowCoreThreadTimeOut;
    private BlockingQueue<Runnable> workQueue;
    private final AtomicInteger ctl = new AtomicInteger(0);

    public KThreadPoolExecutor(int corePoolSize, int maximumPoolSize, Long keepAliveTime, Boolean allowCoreThreadTimeOut, BlockingQueue<Runnable> workQueue) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
        if (keepAliveTime > 0) {
            allowCoreThreadTimeOut = true;
        }
        this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
        this.workQueue = workQueue;
    }

    @Override
    public void execute(Runnable command) {
        if (command == null) {
            throw new NullPointerException();
        }

        int c = ctl.get();
        if (c < corePoolSize) {
            addWorker(command, true);
        } else if (workQueue.offer(command)){ //核心已满，加入等待队列成功后执行
            addWorker(null, false);
        } else { //核心和等待队列已满，执行停止策略
            reject(command);
        }
    }

    private void addWorker(Runnable task, Boolean core) {
        if (core){ //是核心线程 直接自增
            ctl.incrementAndGet();
        }
        Worker worker = new Worker(task);
        worker.thread.start();
    }

    private void reject(Runnable command) {
        throw new RejectedExecutionException("核心和队列已满");
    }

    /***
     *  具体执行线程的类
     */
    class Worker extends ReentrantLock implements Runnable {
        private Runnable firstTask;
        private Thread thread;

        public Worker(Runnable firstTask) {
            this.firstTask = firstTask;
            thread = new Thread(this);
        }

        @Override
        public void run() {
            runWorker(this);
        }
    }

    private void runWorker(Worker w) {
        try {
            w.lock();
            Runnable task = w.firstTask;
            if (task != null || ((task = getTask()) != null)) {
                task.run();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            processWorkerExit(w);
            w.unlock();
        }
    }

    private void processWorkerExit(Worker w) {
        addWorker(null,false);
    }

    private Runnable getTask() throws InterruptedException {
        if (workQueue.isEmpty()) {
            return null;
        }
        return allowCoreThreadTimeOut ?  workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS) : workQueue.take();
    }

}
