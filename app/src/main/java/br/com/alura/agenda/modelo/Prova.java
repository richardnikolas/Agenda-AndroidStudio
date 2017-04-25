package br.com.alura.agenda.modelo;

import java.io.Serializable;
import java.util.List;

public class Prova implements Serializable{
    private String tipo;
    private String nivel;
    private List<String> topicos;

    public Prova(String tipo, String nivel, List<String> topicos) {
        this.tipo = tipo;
        this.nivel = nivel;
        this.topicos = topicos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public List<String> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<String> topicos) {
        this.topicos = topicos;
    }

    @Override
    public String toString() {
        return this.tipo;
    }
}
