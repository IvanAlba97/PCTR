public class forkMonitor {

    private static int[] fork = new int[5];
    private static int N;

    public forkMonitor(int n) {
        forkMonitor.N = n;
        for(int i = 0; i < 4; i++) fork[i] = 2; 
    }
    
    public synchronized void takeForks(int i) throws InterruptedException {
        while(fork[i] != 2) wait();
        switch(i) {
            case 0:
                fork[i+1] -= 1;
                fork[N-1] -= 1;
                break;
            case 4:
                fork[0] -= 1;
                fork[i-1] -= 1;
                break;
            default:
                fork[i+1] -= 1;
                fork[i-1] -= 1;
                break;
        }
        System.out.println("Filosofo " + i + " obtiene tenedores.");
    }

    public synchronized void releaseForks(int i) throws InterruptedException {
        switch(i) {
            case 0:
                fork[i+1] += 1;
                fork[N-1] += 1;
                if(fork[i+1] == 2) notifyAll();
                if(fork[N-1] == 2) notifyAll();
                break;
            case 4:
                fork[0] += 1;
                fork[i-1] += 1;
                if(fork[0] == 2) notifyAll();
                if(fork[i-1] == 2) notifyAll();
                break;
            default:
                fork[i+1] += 1;
                fork[i-1] += 1;
                if(fork[i+1] == 2) notifyAll();
                if(fork[i-1] == 2) notifyAll();
                break;
        }
        System.out.println("Filosofo " + i + " libera tenedores.");
    }

    // TERMINARLO CON EL MONITOR TEORICO DE LAS DIAPOSITIVAS DE LA PRÁCTICA
}
