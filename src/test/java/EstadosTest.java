import org.junit.Test;

public class EstadosTest {

    @Test
    public void retornaListaDeEstados() {

        Estados estados = new Estados();

//        estados.criaERetornaUmaListaDeEstados();

        System.out.println(estados.getListaDeEstados());
    }


    @Test
    public void criaERetornaUmaListaDeEstados() {

        Estados estados = new Estados();

        estados.decodificaStringParaCriarEstados();

        System.out.println(estados.getListaDeEstados());


    }

    @Test
    public void retornaEstadosAtivosAtuais() {


        Estados estados = new Estados();
        estados.decodificaStringParaCriarEstados();

        estados.retornaEstadosAtivosAtuais();


    }


    @Test
    public void funcaoDeTransicao() {

        //obter o estado atual ativo
        //passar uma cadeia para o estado atual ativo
        //atualizar estados ativos


    }


    @Test
    public void criarEstadosAPartirDeUmaString() {

        Estados estados = new Estados();


    }


    @Test
    public void decodificaStringParaCriarEstados() {

        Estados estados = new Estados();
        estados.decodificaStringParaCriarEstados();
        System.out.println(estados.getListaDeEstados());

        //get estado ativo e insere cadeia;


//        System.out.println(Estados.listaDeEstados.get(q1));


    }
}