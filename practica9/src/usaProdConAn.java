public class usaProdConAn {
    
    static prodConAN monitor = new prodConAN();
    
    static class productor extends Thread {
        int hilo;
        public productor(int hilo) {
            this.hilo = hilo;
        }
        @Override
        public void run() {
            while(true) {
                try {
                    monitor.Take(hilo);
                    monitor.Append(hilo);
                } catch(Exception E) {
                    System.err.println(E.getMessage());
                }
            }
        }
    }

    static class consumidor extends Thread {
        int hilo;
        public consumidor(int hilo) {
            this.hilo = hilo;
        }
        @Override
        public void run() {
            while(true) {
                try {
                    monitor.Take(hilo);
                    monitor.Append(hilo);
                } catch(Exception E) {
                    System.err.println(E.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        int N = 5;
        productor[] productores = new productor[N];
        consumidor[] consumidores = new consumidor[N];
        for(int i = 0; i < N; i++) {
            productores[i] = new productor(i);
            consumidores[i] = new consumidor(i);
        }
    }
}
