
public class prodMatConcurrente extends Thread{
    
    private int[][] A, B, C;
    private int fa, ca, cb;
    
    public prodMatConcurrente(int[][] A, int[][] B, int[][] C, int fa, int ca, int cb){
        this.A = A;
        this.B = B;
        this.C = C;
        this.fa = fa;
        this.ca = ca;
        this.cb = cb;
    }
    
    @Override
    public void run(){
        for(int i=0;i<cb;i++){
            for(int j=0;j<ca;j++){
                C[fa][i] += A[fa][j] * B[j][i];
            }
        }
    }
}
