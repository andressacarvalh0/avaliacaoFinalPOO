import java.util.Scanner;
import java.util.stream.Collectors;
import Controle.Produto;
import Controle.Venda;
import Controle.Excecoes.ProdutoNaoEncontradoBuscaException;
import Controle.Excecoes.ProdutoNaoEncontradoVendaException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class App{
    private static Scanner scanner = new Scanner(System.in);
    static List<Produto> list_Produtos = new ArrayList<>();
    static List<Venda> list_Vendas = new ArrayList<>();
    private static String pesquisa = "0";


    public static void main(String[] args) throws Exception {
        boolean continuarExecutando = true;
        do {
            try {
                imprimirMenu();
                int opcao = lerOpcao();
                continuarExecutando = executarOpcao(opcao);
            } catch (Exception e) {
                System.out.println("Ocorreu um erro durante a operação: " + e.getMessage());
                continuarExecutando = true;
            }
        } while (continuarExecutando);
    }
    private static boolean executarOpcao(int opcao) throws Exception {
        switch (opcao) {
            case 1: {
                cadastrarProduto();
                break;
            }
            case 2: {
                consultarProduto();
                break;
            }
            case 3: {
                listarProdutos();
                break;
            }
            case 4: {
                relatoriodevendasporperiodo();
                break;
            }
            case 5: {
                lancarVenda();
                break;
            }
            case 0: {
                System.out.println("Saindo do sistema...");
                return false;
            }
            default: {
                System.out.println("Ainda não implementado!");
                break;
            }
        }
        return true;
    }

    private static void cadastrarProduto() {
        //Cadastro do produto
        System.out.println("Cadastrar Produto");
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        System.out.println("Codigo: ");
        String codigo = scanner.nextLine();
        System.out.println("Valor: ");
        double valor = scanner.nextDouble();
        System.out.println("Quantidade: ");
        int quantidade = scanner.nextInt();
    
        //Salvando produto na lista
        Produto produto = new Produto(nome, codigo, valor, quantidade);
        list_Produtos.add(produto);
        System.out.println("Produto cadastrado com sucesso");
        scanner.nextLine();
    }

    private static Produto consultarProduto() throws ProdutoNaoEncontradoBuscaException{
        System.out.println("Digite o codigo do produto: ");
        //percorre lista para encontrar produto
        pesquisa = scanner.nextLine();
        for (Produto produto: list_Produtos) {
            if (produto != null && produto.getcodigo().equals(pesquisa)) {
                System.out.println(produto);
                return (produto);
            }
        }
        // Quando produto não encontrado
        throw new ProdutoNaoEncontradoBuscaException(pesquisa);
    }

        private static Produto listarProdutos(){
        // Verificando se existem produtos cadastrados
            if(list_Produtos.size()==0){
                System.out.println("\nNão há produtos cadastrados para exibir.");
            }
        // Listando Produtos
            else{DoubleSummaryStatistics resumo = list_Produtos.stream()
            .collect(Collectors.summarizingDouble(Produto::getvalor));
            System.out.println("-------------------------Listagem de Produtos----------------------------");
            System.out.printf("%7s %20s %20s %20s\n", "Nome", "Código", "Valor","Quantidade");
            System.out.println("-------------------------------------------------------------------------");
            for (Produto produto : list_Produtos) {
                System.out.printf( "%7s %20s %20s %20s\n",produto.getnome(), produto.getcodigo(), produto.getvalor(), produto.getquantidade());
        }
            System.out.println("-----------------------------Resumo Médio--------------------------------");
            System.out.printf( "Menor valor %s - Média de valor %s - Maior valor %s\n",resumo.getMin(), resumo.getAverage(),resumo.getMax());
    }   
        return null;
    }
        private static Venda lancarVenda() throws ProdutoNaoEncontradoVendaException{
            System.out.println("Registrar Venda");
            System.out.println("Digite o código do produto vendido:");
            String produtoVendido = scanner.nextLine();
            for (Produto produto: list_Produtos) {
                if (produto != null && produto.getcodigo().equals(produtoVendido)) {
                    System.out.println("Data da venda no formato dd/MM/yy: ");
                    String datavenda = scanner.nextLine();
                    System.out.println("Quantidade vendida: ");
                    int quantidadeVendida = scanner.nextInt();

                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy");
                    LocalDate dataVenda = LocalDate.parse(datavenda, dtf);
                    //Salvando produto na lista
                    Double valorVenda = produto.getvalor()*quantidadeVendida;
                    Venda venda = new Venda(dataVenda, produtoVendido, quantidadeVendida,valorVenda);
                    list_Vendas.add(venda);
                    System.out.println("Venda lançada com sucesso");
                    scanner.nextLine();
                    return(venda);
                }
            }
            throw new ProdutoNaoEncontradoVendaException(produtoVendido);
        }

        private static Venda relatoriodevendasporperiodo(){
            System.out.println("Relatório de Vendas");
            System.out.println("Digite a data inícial:");
            String dataInicio = scanner.nextLine();
            System.out.println("Digite a data final:");
            String dataFim = scanner.nextLine();

            // definindo a formatação da data
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy");
            LocalDate dataInicioLocalDate = LocalDate.parse(dataInicio, dtf);
            LocalDate dataFimLocalDate = LocalDate.parse(dataFim, dtf);
            
            //Filtrando a lista de vendas pelo período
            list_Vendas.stream()
            .filter( venda-> 
            venda.getdataVenda().compareTo(dataInicioLocalDate) > -1 
                && venda.getdataVenda().compareTo(dataFimLocalDate) < 1 );
            DoubleSummaryStatistics resumovenda = list_Vendas.stream()
            .collect(Collectors.summarizingDouble(Venda::getvalorVenda));
            System.out.println("--------------------------------Vendas por Período------------------------------------");
            System.out.printf("%7s %20s %20s %20s\n", "Data Venda", "Produto", "Quantidade","Valor Total");
            System.out.println("--------------------------------------------------------------------------------------");
            for (Venda venda : list_Vendas) {
                System.out.printf( "%7s %20s %20s %20s\n",venda.getdataVenda(), venda.getprodutoVendido(), venda.getquantidadevendida(), venda.getvalorVenda());
                }
            System.out.println("----------------------------------Resumo Médio---------------------------------------");
            System.out.printf( "Menor valor %s - Média de valor %s - Maior valor %s\n",resumovenda.getMin(), resumovenda.getAverage(),resumovenda.getMax());

            return null;
            
        }
        //Menu inicial
        private static void imprimirMenu() {
            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Consultar produto");
            System.out.println("3 - Listar produtos");
            System.out.println("4 - Vendas por período - Detalhado");
            System.out.println("5 - Lançar venda");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
        }
    
        private static boolean validarOpcaoMenu(int opcao) {
            return (opcao >= 0 && opcao <= 5);
        }
    
        private static int lerOpcao() {
            int opcao = 0;
            do {
                System.out.println("Selecione a opção desejada: ");
                try {
                    opcao = Integer.parseInt(scanner.nextLine());
                    if (!validarOpcaoMenu(opcao)) {
                        System.out.println("Opção inválida!");
                    }
                } catch (Exception e) {
                    System.out.println("Opção inválida!");
                    scanner.nextLine();
                }
            } while (!validarOpcaoMenu(opcao));
    
            return opcao;
        }
    }