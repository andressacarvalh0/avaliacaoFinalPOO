package Controle;

import java.time.LocalDate;

public class Venda {
    private LocalDate _dataVenda;
    private String _produtoVendido;
    private int _quantidadevendida;
    private double _valorVenda;


    public Venda(LocalDate dataVenda, String produtoVendido, int quantidadeVendida, double valorVenda) {
        _dataVenda = dataVenda;
        _produtoVendido = produtoVendido;
        _quantidadevendida = quantidadeVendida;
        _valorVenda=valorVenda;
    }


    public LocalDate getdataVenda() {
        return _dataVenda;
    }

    public String getprodutoVendido() {
        return _produtoVendido;
    }

    public int getquantidadevendida() {
        return _quantidadevendida;
    }

    public double getvalorVenda() {
        return _valorVenda;
    }


    public void setquantidadevendida(int _quantidadevendida) {
    }
    @Override
    public String toString() {
        return " [data venda=" + _dataVenda + ", produto vendido=" + _produtoVendido + ", quantidade vendida=" + _quantidadevendida + "valor da venda=" + _valorVenda +"]";
    }



}
