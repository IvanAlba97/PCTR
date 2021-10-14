package PCTR.practica1.src;

public class jerarquiaClases {
    
    public static void main(String[] args) {

        conejo co = new conejo(2, 4, true, PCTR.practica1.src.herbivoro.tipo.GRANÍVORO);
        leon le = new leon(2, 4, true, PCTR.practica1.src.carnivoro.tipo.ESTRICTO);
        hiena hi = new hiena(2, 4, true, PCTR.practica1.src.carnivoro.tipo.ESTRICTO);
        hombre ho = new hombre(2, 2, false, true);

        System.out.println("Caracteristicas del conejo: ");
        System.out.println("Numero de ojos: " + co.getNOjos());
        System.out.println("Numero de patas: " + co.getNPatas());
        System.out.println("Pelo: " + co.getPelo());
        System.out.println("Tipo: " + co.getTipoHerbivoro());
        System.out.println();

        System.out.println("Caracteristicas del leon: ");
        System.out.println("Numero de ojos: " + le.getNOjos());
        System.out.println("Numero de patas: " + le.getNPatas());
        System.out.println("Pelo: " + le.getPelo());
        System.out.println("Tipo: " + le.getTipoCarnivoro());
        System.out.println();

        System.out.println("Caracteristicas de la hiena: ");
        System.out.println("Numero de ojos: " + hi.getNOjos());
        System.out.println("Numero de patas: " + hi.getNPatas());
        System.out.println("Pelo: " + hi.getPelo());
        System.out.println("Tipo: " + hi.getTipoCarnivoro());
        System.out.println();

        System.out.println("Caracteristicas del hombre: ");
        System.out.println("Numero de ojos: " + ho.getNOjos());
        System.out.println("Numero de patas: " + ho.getNPatas());
        System.out.println("Pelo: " + ho.getPelo());
        System.out.println("Humano: " + ho.getHumano());
        System.out.println();

    }
}
