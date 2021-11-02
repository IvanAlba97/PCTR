package PCTR.practica4.src;

public class algHyman extends Thread {

    public int tipohilo;
    public static boolean c0;
    public static boolean c1;
    public static int turno;
    public static int n = 0;
    public static int nVueltas = 10000;

    public algHyman(int tipohilo) {
        this.tipohilo = tipohilo;
    }

    @Override
    public void run() {
        switch (tipohilo) {
            case 1:
                for (int i = 0; i < nVueltas; i++) {
                    c0 = true;
                    while (turno != 0) {
                        while (c1);
                        turno = 0;
                    }
                    n++;
                    c0 = false;
                }
                break;
            case 2:
                for (int i = 0; i < nVueltas; i++) {
                    c1 = true;
                    while (turno != 1) {
                        while (c0);
                        turno = 1;
                    }
                    n--;
                    c1 = false;
                }
                break;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread hilo1 = new algHyman(1);
        Thread hilo2 = new algHyman(2);
        hilo1.start();
        hilo2.start();
        hilo1.join();
        hilo2.join();
        System.out.println(n);
    }

}
