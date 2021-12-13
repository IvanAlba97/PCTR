import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class pcMonitorApiAN {
    private final int N = 100;
    private int Oldest = 0, Newest = 0;
    private volatile int Count = 0;
    private int Buffer[] = new int[N];
    public static ReentrantLock lock = new ReentrantLock();
    final Condition c1 = lock.newCondition();
    final Condition c2 = lock.newCondition();
  
    public void Append(int V) throws InterruptedException {
        lock.lock();
        try{
            while(Count == N) c1.await();
            Buffer[Newest] = V;
            Newest = (Newest + 1) % N;
            Count = Count + 1;
            c1.signalAll();
        } finally {
            lock.unlock();
        }
    }
  
    public int Take() throws InterruptedException {
        lock.lock();
        try {
            int temp;
            while (Count == 0) c2.await();
            temp = Buffer[Oldest];
            Oldest = (Oldest + 1) % N;
            Count = Count - 1;
            c2.signalAll();
            return temp;
        } finally {
            lock.unlock();
        }
      }
  }