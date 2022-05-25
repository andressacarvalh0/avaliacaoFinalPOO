package Controle.Excecoes;

public class ProdutoNaoEncontradoBuscaException extends Exception {
    public ProdutoNaoEncontradoBuscaException(String codigo) {
        super("Não foi possível encontrar nenhum produto pelo código: " + codigo);
    } 
}
