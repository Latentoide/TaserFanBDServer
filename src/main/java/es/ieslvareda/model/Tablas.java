package es.ieslvareda.model;

public enum Tablas {
    COCHE("COCHE"), MOTO("MOTO"), BICICLETA("BICICLETA"), PATINETE("PATINETE");
    private String str;
    Tablas(String str){
        this.str = str;
    }
    public String getStr(){
        return str;
    }

}
