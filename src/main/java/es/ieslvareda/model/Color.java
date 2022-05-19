package es.ieslvareda.model;

public enum Color {
    VERDE("verde"), AMARILLO("amarillo"), ROJO("rojo"), BLANCO("blanco"), NEGRO("negro"), AZUL("azul");
    private String str;
    Color(String str){
        this.str = str;
    }

    public String getColor(){
        return str;
    }
}
