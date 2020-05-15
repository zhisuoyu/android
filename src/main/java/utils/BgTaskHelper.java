package utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class BgTaskHelper{


    public static void main(String[] args) {
        final BgTaskHelper helper = new BgTaskHelper();
//        helper.start();
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                for (int i = 0; i < 10; i++) {
//                    try {
//                        TimeUnit.SECONDS.sleep(i);
//                        final int finalI = i;
//                        helper.addTask(new Runnable() {
//                            @Override
//                            public void run() {
//                                System.out.println("hello:" + Thread.currentThread().getId());
//                            }
//                        });
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();
    }

    private final ExecutorService TaskPool = Executors.newFixedThreadPool(4);
//    private final LinkedBlockingDeque<Runnable> TaskQueue = new LinkedBlockingDeque<>();


    private static final BgTaskHelper Instance = new BgTaskHelper();

    private BgTaskHelper() {
    }

    public static BgTaskHelper getInstance() {
        return Instance;
    }

    public void addTask(Runnable task) {
    	TaskPool.submit(task);
    }


//    @Override
//    public void run() {
//        super.run();
//        while (true) {
//            try {
//                Runnable task = TaskQueue.take();
//                threadPool.submit(task);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
