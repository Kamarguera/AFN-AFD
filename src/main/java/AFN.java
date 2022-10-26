import java.util.ArrayList;
import java.util.List;

public class AFN {
    public static List<AFN> listaDeEstados = new ArrayList<>();
    public List<String> estadosDeTransicao = new ArrayList<>();
    public List<String> cadeiasLidasPeloEstado = new ArrayList<>();
    public ArrayList<AFN> transicaoDeEstados;
    Boolean estadoInicial, estadoFinal, estadoAtivo = false;
    String stringDeEntrada =
            "q1 a,q2,q3,true,true\n" +
                    "q2 a b,q2,q3,false,false\n" +
                    "q3 a b _,q1,false,false";


    String caracteresAceitosParaATransicao;
    char cadeia;
    private String nome;





    //region Construtor
    public AFN() {
    }

    private AFN(String nome, List<String> estadosDeTransicao) {

        this.nome = nome;
        this.estadosDeTransicao = estadosDeTransicao;
    }


    //endregion Construtor


    //region getters&setters

    public List<String> getCadeiasLidasPeloEstado() {
        return cadeiasLidasPeloEstado;
    }

    public void setCadeiasLidasPeloEstado(List<String> cadeiasLidasPeloEstado) {
        this.cadeiasLidasPeloEstado = cadeiasLidasPeloEstado;
    }

    public List<String> getEstadosDeTransicao() {
        return estadosDeTransicao;
    }

    public ArrayList<AFN> getTransicaoDeEstados() {
        return transicaoDeEstados;
    }

    public String getStringDeEntrada() {
        return stringDeEntrada;
    }

    public Boolean getEstadoFinal() {
        return estadoFinal;
    }

    public void setEstadoFinal(Boolean estadoFinal) {
        this.estadoFinal = estadoFinal;
    }

    public Boolean getEstadoAtivo() {
        return estadoAtivo;
    }

    public void setEstadoAtivo(Boolean estadoAtivo) {
        this.estadoAtivo = estadoAtivo;
    }

    public List<AFN> getListaDeEstados() {
        return listaDeEstados;
    }

    public void setListaDeEstados(List<AFN> listaDeEstados) {
        this.listaDeEstados = listaDeEstados;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCaracteresAceitosParaATransicao() {
        return caracteresAceitosParaATransicao;
    }

    public void setCaracteresAceitosParaATransicao(String caracteresAceitosParaATransicao) {
        this.caracteresAceitosParaATransicao = caracteresAceitosParaATransicao;
    }

    public char getCadeia() {
        return cadeia;
    }

    public void setCadeia(char cadeia) {
        this.cadeia = cadeia;
    }


    public Boolean getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(Boolean estadoInicial) {
        this.estadoInicial = estadoInicial;
    }
    //endregion getters&setters


    public void retornaEstadosAtivosAtuais() {

        System.out.print("estados ativos atuais: ");

        listaDeEstados.forEach(AFN -> {
            if (AFN.estadoAtivo) System.out.print("[" + AFN + "]");
        });


    }


    public void decodificaStringParaCriarEstados() {
        String[] myData = stringDeEntrada.split("\n");
        for (String linhas : myData) {

//            System.out.println(linhas);

            String[] estados = linhas.split(",");
            AFN q = new AFN();


            insereEstadosAtivos(estados, q);

            importarCadeiasLidasPeloEstado(estados[0], q);

            insereNomeDoEstado(estados[0], q);

            adicionarEstadosDeTransicao(estados, q);

            adicionaEstadosNaListaDeEstados(q);

            printEstadosAceitosECadeias(q);

        }


    }


    //region métodos utilizados por  decodificaStringParaCriarEstados
    private void printEstadosAceitosECadeias(AFN q) {

        System.out.println("estados aceitos por " +
                q +
                " " +
                q.getEstadosDeTransicao() +
                " " +
                "cadeias " +
                q.getCadeiasLidasPeloEstado());

    }


    private void adicionaEstadosNaListaDeEstados(AFN q) {
        listaDeEstados.add(q);
    }

    private void adicionarEstadosDeTransicao(String[] estados, AFN q) {


        for (int i = 1; i < estados.length - 2; i++) {

            q.estadosDeTransicao.add(estados[i]);


        }

    }


    private void insereNomeDoEstado(String estados, AFN q) {
        String[] split = estados.split(" ");
        String nomeDoEstado = split[0];
        q.setNome(nomeDoEstado);
    }

    private void insereEstadosAtivos(String[] estados, AFN q) {

        q.setEstadoAtivo(Boolean.valueOf(estados[estados.length - 2]));
        q.setEstadoFinal(Boolean.valueOf(estados[estados.length - 1]));

    }

    private String retornaNomeCadeiaRemovendoCadeias(String estados) {
        String[] split = estados.split(" ");
        String nomeDoEstado = split[0];
        return nomeDoEstado;
    }

    private void importarCadeiasLidasPeloEstado(String estados, AFN q) {
        String[] cadeias = estados.split(" ");


        for (int i = 1; i < cadeias.length; i++) {
            q.cadeiasLidasPeloEstado.add(cadeias[i]);

        }


    }

   //endregion métodos utilizados por decodificaStringParaCriarEstados



    @Override
    public String toString() {
        return this.nome;

    }


}




