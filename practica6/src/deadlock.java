/**
 * https://stackoverflow.com/questions/14299535/java-simulating-a-deadlock-beween-3-threads-and-setting-priorities
 */

public class deadlock {

    final Object resource1 = "resource1";
    final Object resource2 = "resource2";
    final Object resource3 = "resource3";

    Thread t1 = new Thread() {
        public void run() {
            synchronized(resource1) {
                System.out.println("Thread 1: locked resource 1");
                try { Thread.sleep(50); } catch (InterruptedException e) {}
                synchronized(resource2) {
                    System.out.println("Thread 1: locked resource 2");
                }
            }
        }
    };

    Thread t2 = new Thread() {
        public void run() {
            synchronized(resource2) {
                System.out.println("Thread 2: locked resource 2");

                try { Thread.sleep(50); } catch (InterruptedException e) {}
                synchronized(resource1) {
                    System.out.println("Thread 2: locked resource 1");
                }
            }
        }
    };

    Thread t3 = new Thread() {
        public void run() {
            for(int i=1; i<=5;i++) {
                System.out.println("t3 "+i);
            }
            synchronized (resource3) {
                System.out.println("Thread 3: locked resource 3");
                try {  Thread.sleep(50); } catch (InterruptedException e) {}
                synchronized (resource2) {
                    System.out.println("Thread 3: locked resource 2");
                }
            }
        }
    };
}
