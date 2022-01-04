//COMPILACION:javac -cp .;%MPJ_HOME%/lib/mpj.jar escakMultiple.java
//EJECUCION: mpjrun.bat -np 5 escalMultiple

import mpi .*;
public class escalMultiple {
    public static void main (String[] args) throws Exception {
        MPI.Init (args);
        int rank = MPI.COMM_WORLD.Rank ();
        int size = MPI.COMM_WORLD.Size ();
        int emisor = 0;
        int tag = 100;
        int unitSize = 10;

        int bufer[] = new int[10];
        if(rank == 0) {
            System.out.println("Soy el proceso máster.");
            for(int i = 0; i < bufer.length; i++) {
                bufer[i] = i;
                System.out.print(bufer[i] );
            }
            System.out.println();
        }
        MPI.COMM_WORLD.Bcast(bufer, 0, unitSize, MPI.INT, 0);
        if(rank == 1 || rank == 2 || rank == 3 || rank == 4) {
            System.out.println("Soy el proceso " + rank + ".");
            for(int i = 0; i < bufer.length; i++) {
                bufer[i] *= rank;
                System.out.println(bufer[i] );
            }
            System.out.println();
        }
        MPI . Finalize ();
    }
}