import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class prodConAN {
    public final int N = 100;
    public int Oldest = 0, Newest = 0;
    public volatile int Count = 0;
    public int Buffer[] = new int[N];
    private final ReentrantLock cerrojo = new ReentrantLock();
    private Condition OKtoAppend[];

    public prodConAN() {
        for(int i = 0; i < N; i++) {
            OKtoAppend[i] = cerrojo.newCondition();
        }
    }

    public void Append(int V) {
        cerrojo.lock();
        try {
            while (Count == N)
                try {
                    OKtoAppend[V].await();
                } catch (InterruptedException e) {}
            Buffer[Newest] = V;
            Newest = (Newest + 1) % N;
            Count = Count + 1;
            OKtoAppend[V].signalAll();
        } finally {
            cerrojo.unlock();
        }
    }

    public int Take(int V) {
        cerrojo.lock();
        try {
            int temp;
            while (Count == 0)
                try {
                    OKtoAppend[V].await();
                } catch (InterruptedException e) {
                }
            temp = Buffer[Oldest];
            Oldest = (Oldest + 1) % N;
            Count = Count - 1;
            OKtoAppend[V].signalAll();
            return temp;
        } finally {
            cerrojo.unlock();
        }
    }
}