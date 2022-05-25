package Controle.Excecoes;

public class ProdutoNaoEncontradoVendaException extends Exception {
    public ProdutoNaoEncontradoVendaException(String codigo) {
        super("Não foi possível encontrar nenhum produto pelo código: " + codigo);
    }   
}