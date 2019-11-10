package com.argonautas.translate_app;

public class poslenguaje
{
    int pos;
    String lenguaje, lenguajeCompleto;

    public poslenguaje(int pos, String lenguaje, String lenguajeCompl) {
        this.pos = pos;
        this.lenguaje = lenguaje;
        lenguajeCompleto = lenguajeCompl;

    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }
}
