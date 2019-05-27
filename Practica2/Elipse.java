
public class Elipse {

    double foco1, foco2;
    double semiejemayor, semiejemenor;
    double centrox = 0, centroy = 0;

    public Elipse(double foco1, double foco2, double semiejemayor, double semiejemenor) {
        this.foco1 = foco1;
        this.foco2 = foco2;
        this.semiejemayor = semiejemayor;
        this.semiejemenor = semiejemenor;
    }

    public double getFoco1() {
        return foco1;
    }

    public double getFoco2() {
        return foco2;
    }

    public double getSemiejemayor() {
        return semiejemayor;
    }

    public double getSemiejemenor() {
        return semiejemenor;
    }

    public void pertenece(double x, double y) {
        double Y;
        Y = (Math.pow(x - centrox, 2) / (semiejemayor * semiejemayor)) + (Math.pow(y - centroy, 2) / (semiejemenor * semiejemenor));
        if (Y == 1) {
            System.out.println("El punto esta dentro de la elipse");
        } else {
            System.out.println("El punto NO esta dentro de la elipse");
        }
    }
}
