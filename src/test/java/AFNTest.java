import org.junit.Test;

public class AFNTest {

    @Test
    public void retornaListaDeEstados() {

        AFN AFN = new AFN();

//        AFN.criaERetornaUmaListaDeEstados();

        System.out.println(AFN.getListaDeEstados());
    }


    @Test
    public void criaERetornaUmaListaDeEstados() {

        AFN AFN = new AFN();

        AFN.decodificaStringParaCriarEstados();

        System.out.println(AFN.getListaDeEstados());


    }

    @Test
    public void retornaEstadosAtivosAtuais() {


        AFN AFN = new AFN();
        AFN.decodificaStringParaCriarEstados();

        AFN.retornaEstadosAtivosAtuais();


    }


    @Test
    public void funcaoDeTransicao() {

        //obter o estado atual ativo
        //passar uma cadeia para o estado atual ativo
        //atualizar estados ativos


    }


    @Test
    public void criarEstadosAPartirDeUmaString() {

        AFN AFN = new AFN();


    }


    @Test
    public void decodificaStringParaCriarEstados() {

        AFN AFN = new AFN();
        AFN.decodificaStringParaCriarEstados();
        System.out.println(AFN.getListaDeEstados());

        //get estado ativo e insere cadeia;


//        System.out.println(AFN.listaDeEstados.get(q1));


    }
}