package Controle;

public class Produto{
    private String _nome;
    private String _codigo;
    private double _valor;
    private int _quantidade;

    public Produto(String nome, String codigo, double valor, int quantidade) {
        _nome = nome;
        _codigo = codigo;
        _valor = valor;
        _quantidade = quantidade;
    }

    public String getnome() {
        return _nome;
    }

    public String getcodigo() {
        return _codigo;
    }

    public double getvalor() {
        return _valor;
    }

    public int getquantidade() {
        return _quantidade;
    }

    @Override
    public String toString() {
        return " [nome=" + _nome + ", codigo=" + _codigo + ", valor=" + _valor + ", quantidade=" + _quantidade+ "]";
    }
}