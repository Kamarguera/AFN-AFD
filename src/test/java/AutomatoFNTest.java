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
    public void criarEstadosAPartirDeUmaString() {

        AutomatoFN AutomatoFN = new AutomatoFN();


    }


    @Test
    public void decodificaStringParaCriarEstados() {

        AutomatoFN AutomatoFN = new AutomatoFN();
        AutomatoFN.decodificaStringParaCriarEstados();
//        System.out.println(AutomatoFN.getListaDeEstados());

        //get estado ativo e insere cadeia;


//        System.out.println(AutomatoFN.listaDeEstadosAFN.get(q1));


    }


    @Test
    public void funcaoDeTransicao() {
        String cadeiaDeDados = "ab";


        AutomatoFN AutomatoFN = new AutomatoFN();
        AutomatoFN.decodificaStringParaCriarEstados();


        AutomatoFN.lerCadeiaDeDados(cadeiaDeDados);

    }

    @Test
    public void criaEstadosDoAFD() {

        AutomatoFN AutomatoFN = new AutomatoFN();
        AutomatoFN.decodificaStringParaCriarEstados();
        AutomatoFN.criaEstadosDoAFD();


    }


    @Test
    public void lol() {
        AutomatoFN AutomatoFN = new AutomatoFN();
        AutomatoFN.decodificaStringParaCriarEstados();

        AutomatoFN.criaEstadosDoAFD();
        AutomatoFN.lerCadeiaDeDadosAFD("abaaaaa");
//        AutomatoFN.lerCadeiaDeDadosAFD(cadeiaDeDados);
    }
}


