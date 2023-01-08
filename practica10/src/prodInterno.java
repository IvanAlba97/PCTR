//COMPILACION:javac -cp .;%MPJ_HOME%/lib/mpj.jar prodInterno.java
//EJECUCION: mpjrun.bat -np 2 prodInterno

import mpi.*;
import java.util.Arrays;

/**
 * Esta clase contiene los atributos y metodos para la creación de hebras y el uso de barreras.
 * @author Iván Alba Gómez
 * @version 3.0
 */
public class prodInterno {

    /**
     * Método principal.
     */
    public static void main(String[] args) {
        
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int emisor = 0; int receptor = 1;
        int tag = 100;
        
        if(rank == emisor) {    // Código del emisor
            int buffer1[] = new int[4];
            int buffer2[] = new int[4];
            int revBuffer[] = new int[1];
            for(int i = 0; i < buffer1.length; i++) {
                buffer1[i] = i;
                buffer2[i] = i * 2;
            }
            MPI.COMM_WORLD.Send(buffer1, 0, buffer1.length, MPI.INT, receptor, tag);
            MPI.COMM_WORLD.Send(buffer2, 0, buffer2.length, MPI.INT, receptor, tag);
            MPI.COMM_WORLD.Recv(revbuffer, 0, 1, MPI.INT, receptor, tag);
            System.out.println("Producto = " + revBuffer[0]);
        } else {    // Código del receptor
            int revBuffer1 = new int[4];
            int revBuffer2 = new int[4];
            MPI.COMM_WORLD.Recv(revbuffer1, 0, revbuffer1.length, MPI.INT, emisor, tag);
            MPI.COMM_WORLD.Recv(revbuffer2, 0, revbuffer2.length, MPI.INT, emisor, tag);
            int res = producto(revBuffer1, revBuffer2);
            MPI.COMM_WORLD.Send(buffer, 0, 1, MPI.INT, emisor, tag);
        }
        MPI.Finalize();
    }

    /**
     * Este método realiza el producto de dos buffers, en este caso interpretados por vectores.
     * @param revBuffer1 Buffer 1
     * @param revBuffer2 Buffer 2
     * @return res
     */
    int producto(int[] revBuffer1, int[] revBuffer2) {
        int res = 0;
        for(int i = 0; i < revBuffer1.length; i++) {
            res += revBuffer1[i] * revBuffer2[i];
        }
        return res;
    }
}
