package test1;

public class Solution4 {
    private volatile int i = 0;
    private Object lock = new Object();
    private volatile int count = 100;

    public void p() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count > 0) {
                    synchronized (lock) {
                        while (i != 0) {
                            try {
                                lock.wait();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        count--;
                        i = 1;
                        System.out.println("p ...... " + System.currentTimeMillis());
                        lock.notify();
                    }
                }

                System.out.println(count);
            }
        }).start();
    }

    public void c() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count >= 0) {
                    synchronized (lock) {
                        while (i == 0) {
                            try {
                                lock.wait();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        count--;
                        i = 0;
                        System.out.println("c ...... " + System.currentTimeMillis());
                        lock.notify();
                    }
                }

                System.out.println(count);
            }
        }).start();


    }

    public static void main(String[] args) {
        Solution4 solution4 = new Solution4();
        solution4.p();
        solution4.c();

        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
