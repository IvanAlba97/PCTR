import mpi.*;

/**
 * Esta clase contiene los atributos y metodos para la búsqueda de números primos de manera distribuida usando MPJ-Express.
 * @author Iván Alba Gómez
 * @version 3.0
 */
public class distributedIntegers {

    /**
     * Método principal.
     */
    public static void main(String[] args) {
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int emisor = 0;
        long[] rango = new long[size - 1];
        int[] cont = new int[1];
        int[] datos = new int[1];

        if(rank == emisor) {
            cont[0] = 0;
            long rango = (long)1e7/(size - 1);
            int i;

            for(i = 0; i < size - 2; i++) {
                rango[i] = rango;
                rango += (long)1e7/size;
                System.out.println("Rango: " + rango);
            }

            rango[i] = (long)1e7;
            System.out.println("Rango: " + rango[i]);
        }

        MPI.COMM_WORLD.Bcast(rango, 0, size - 1, MPI.LONG, emisor);

        if (rank != emisor) {
            long inicio;
            boolean primo = true;
            cont[0] = 0;
            
            if(rank == 1) inicio = 2;
            else inicio = rango[rank - 2];

            for (long i = inicio; i < rango[rank - 1]; i++) {
                primo = true;
                for (long j = 2; j <= Math.sqrt(i); j++) if(i%j == 0 && i != j) primo = false;
                if(primo) cont[0]++;
            }
            
            System.out.println("Proceso: " + rank +" encontrado "+ cont[0]);
            
        }

        MPI.COMM_WORLD.Reduce(cont,0,datos,0,1,MPI.INT,MPI.SUM,emisor);

        if(rank == emisor) {
            System.out.println("Resultado: " + datos[0]);
        }

        MPI.Finalize();
    }
}