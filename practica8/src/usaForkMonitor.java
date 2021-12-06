public class usaForkMonitor extends Thread {

    private int id;
    private static final int N = 5;
    private static forkMonitor monitor = new forkMonitor(N);

    public usaForkMonitor(int hilo) {
        this.id = hilo;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(5000);
                monitor.takeForks(id);
                Thread.sleep(1000);
                monitor.releaseForks(id);
            } catch (InterruptedException E) { E.printStackTrace(); }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        usaForkMonitor[] filosofos = new usaForkMonitor[N];
        for(int i = 0; i < N; i++) {
            filosofos[i] = new usaForkMonitor(i);
            filosofos[i].start();
        }
    }
}