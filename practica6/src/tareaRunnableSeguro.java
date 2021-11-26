public class tareaRunnableSeguro {
    
    private int n = 0;
    
    public tareaRunnableSeguro() {}
    public synchronized void inc() { n++; }
    public synchronized void dec() { n--; }
    public int getN() { return n; }
}