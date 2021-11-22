public class usaProdCon {
    
    static PCMonitor monitor = new PCMonitor();
    
    static class productor extends Thread {
        public productor() {}
        @Override
        public void run() {
            while(true) {
                try {
                    monitor.Take();
                    monitor.Append(1);
                } catch(Exception E) {
                    System.err.println(E.getMessage());
                }
            }
        }
    }

    static class consumidor extends Thread {
        public consumidor() {}
        @Override
        public void run() {
            while(true) {
                try {
                    monitor.Take();
                    monitor.Append(1);
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
            productores[i] = new productor();
            consumidores[i] = new consumidor();
        }
    }
}
