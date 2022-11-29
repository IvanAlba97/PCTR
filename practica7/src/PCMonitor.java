/* Copyright (C) 2006 M. Ben-Ari.*/

/**
 * Esta clase contiene los atributos y metodos para el problema del productor-consumidor.
 * @author Ben-Ari
 * @see usaProdCon.java
 */
public class PCMonitor {
  public final int N = 100;
  public int Oldest = 0, Newest = 0;
  public volatile int Count = 0;
  public int Buffer[] = new int[N];

  /**
   * Método para liberar el recurso compartido.
   */
  public synchronized void Append(int V) {
    while (Count == N)
      try {
         wait();
      } catch (InterruptedException e) {}
		Buffer[Newest] = V;
		Newest = (Newest + 1) % N;
		Count = Count + 1;
		notifyAll();
	}

  /**
   * Método para solicitar el recurso compartido.
   */
  public synchronized int Take() {
    int temp;
    while (Count == 0)
      try {
         wait();
      } catch (InterruptedException e) {}
		temp = Buffer[Oldest];
		Oldest = (Oldest + 1) % N;
		Count = Count - 1;
		notifyAll();
		return temp;
	}
}
