package PCTR.practica4.src;

public class algEisenbergMcGuire implements Runnable {

    private static final int N = 2;
    private static int flags[]; //3 posibles estados {0,1,2} = {IDLE, WAITING, ACTIVE}
    private static int turn;
    int i = 0;

    public algEisenbergMcGuire(int i) {
        this.i = i;
    }

    void entrarEnProtocolo(int i) {
        int index;
        do {
            flags[i] = 1;

            index = turn;
            while (index != i) {
                if (flags[index] != 0) {
                    index = turn;
                } else {
                    index = (index + 1) % N;
                }
            }

            flags[i] = 2;

            index = 0;
            while ((index < N) && ((index == i) || (flags[index] != 2))) {
                index++;
            }
        } while (!((index >= N) && ((turn == i) || (flags[turn] == 0))));

        turn = i;
    }

    void salirDeProtocolo(int i) {
        int index = (turn + 1) % N;
        while (flags[index] == 0) {
            index = (index + 1) % N;
        }

        turn = index;
        flags[i] = 0;
    }

    public int getI() {
        return this.i;
    }

    @Override
    public void run() {
        entrarEnProtocolo(getI());
        System.out.println("Hilo " + getI() + " activo.");
        salirDeProtocolo(getI());
    }

    public static void main(String[] args) throws InterruptedException {
        Thread hilos[] = new Thread[N];
        flags = new int[3];
        turn = 0;
        for (int index = 0; index < N; index++) {
            flags[index] = 0; // 0 means IDLE
        }

        for (int i = 0; i < N; i++) {
            hilos[i] = new Thread(new algEisenbergMcGuire(i));
            hilos[i].start();
        }

        for (int i = 0; i < N; i++) {
            hilos[i].join();
        }

    }
}