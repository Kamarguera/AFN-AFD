import org.junit.Test;

public class AutomatoFNTest {

    @Test
    public void retornaListaDeEstados() {

        AutomatoFN AutomatoFN = new AutomatoFN();

//        AutomatoFN.criaERetornaUmaListaDeEstados();

        System.out.println(AutomatoFN.getListaDeEstados());
    }


    @Test
    public void criaERetornaUmaListaDeEstados() {

        AutomatoFN AutomatoFN = new AutomatoFN();

        AutomatoFN.decodificaStringParaCriarEstados();

        System.out.println(AutomatoFN.getListaDeEstados());


    }

    @Test
    public void retornaEstadosAtivosAtuais() {


        AutomatoFN AutomatoFN = new AutomatoFN();
        AutomatoFN.decodificaStringParaCriarEstados();

        AutomatoFN.retornaEstadosAtivosAtuais();


    }


    @Test
    public void funcaoDeTransicao() {


        AutomatoFN AutomatoFN = new AutomatoFN();
        AutomatoFN.decodificaStringParaCriarEstados();

        String cadeiaDeDados = "baba";


        AutomatoFN.lerCadeiaDeDados(cadeiaDeDados);





    }


    @Test
    public void criarEstadosAPartirDeUmaString() {

        AutomatoFN AutomatoFN = new AutomatoFN();


    }


    @Test
    public void decodificaStringParaCriarEstados() {

        AutomatoFN AutomatoFN = new AutomatoFN();
        AutomatoFN.decodificaStringParaCriarEstados();
//        System.out.println(AutomatoFN.getListaDeEstados());

        //get estado ativo e insere cadeia;


//        System.out.println(AutomatoFN.listaDeEstados.get(q1));


    }
}