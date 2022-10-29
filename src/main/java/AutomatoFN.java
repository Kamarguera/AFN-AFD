import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AutomatoFN {

    //region variáveis
    public static List<AutomatoFN> listaDeEstados = new ArrayList<>();
    public List<AutomatoFN> estadosDeTransicaoAceitos = new ArrayList<>();
    public List<String> cadeiasLidasPeloEstado = new ArrayList<>();
    public ArrayList<AutomatoFN> transicaoDeEstados;

    public HashMap<String, AutomatoFN> cadeiaEstadoHashMap = new HashMap<>();
    Boolean estadoInicial, estadoFinal, estadoAtivo = false;


    String caracteresAceitosParaATransicao;
    char cadeia;
    String stringDeEntrada =
            "q1, b q2, _ q3, true,true\n" +
                    "q2, a q2, ab q3, false,false\n" +
                    "q3, a q1,        false,false";
    private String nome;


    //endregion variáveis

    //region Construtor
    public AutomatoFN() {
    }


    //endregion Construtor

    //region getters&setters

    public HashMap<String, AutomatoFN> getCadeiaEstadoHashMap() {
        return cadeiaEstadoHashMap;
    }

    public void setCadeiaEstadoHashMap(HashMap<String, AutomatoFN> cadeiaEstadoHashMap) {
        this.cadeiaEstadoHashMap = cadeiaEstadoHashMap;
    }

    public List<String> getCadeiasLidasPeloEstado() {
        return cadeiasLidasPeloEstado;
    }

    public void setCadeiasLidasPeloEstado(List<String> cadeiasLidasPeloEstado) {
        this.cadeiasLidasPeloEstado = cadeiasLidasPeloEstado;
    }

    public List<AutomatoFN> getEstadosDeTransicaoAceitos() {
        return estadosDeTransicaoAceitos;
    }

    public void setEstadosDeTransicaoAceitos(List<AutomatoFN> estadosDeTransicaoAceitos) {
        this.estadosDeTransicaoAceitos = estadosDeTransicaoAceitos;
    }

    public ArrayList<AutomatoFN> getTransicaoDeEstados() {
        return transicaoDeEstados;
    }

    public void setTransicaoDeEstados(ArrayList<AutomatoFN> transicaoDeEstados) {
        this.transicaoDeEstados = transicaoDeEstados;
    }

    public String getStringDeEntrada() {
        return stringDeEntrada;
    }

    public void setStringDeEntrada(String stringDeEntrada) {
        this.stringDeEntrada = stringDeEntrada;
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

    public List<AutomatoFN> getListaDeEstados() {
        return listaDeEstados;
    }

    public void setListaDeEstados(List<AutomatoFN> listaDeEstados) {
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


    public void decodificaStringParaCriarEstados() {
        String[] cadeiaDeEntradaSeparadaPorLinhas = stringDeEntrada.split("\n");

        for (String linhas : cadeiaDeEntradaSeparadaPorLinhas) {

            AutomatoFN q = new AutomatoFN();


            String[] estados = linhas.split(",");

            insereNomeDoEstado(estados[0], q);

            adicionaEstadosNaListaDeEstados(q);
            insereEstadosAtivos(estados, q);


        }

        for (int i = 0; i < cadeiaDeEntradaSeparadaPorLinhas.length; i++) {

            String[] estados = cadeiaDeEntradaSeparadaPorLinhas[i].split(",");

            adicionarEstadosDeTransicao(estados, listaDeEstados.get(i));
            importarCadeiasLidasPeloEstado(cadeiaDeEntradaSeparadaPorLinhas[i], listaDeEstados.get(i));


        }


        printEstadosAceitosECadeias();


    }

    //region métodos utilizados por  decodificaStringParaCriarEstados
    private void adicionarEstadosDeTransicao(String[] estados, AutomatoFN q) {

        for (int i = 1; i < estados.length - 2; i++) {

            q.estadosDeTransicaoAceitos.add(

                    listaDeEstados.get(i)

            );


        }

    }

    private void printEstadosAceitosECadeias() {

        listaDeEstados.forEach((estado) -> {

            System.out.println("estados aceitos por " +
                    estado +
                    " " +
                    estado.getEstadosDeTransicaoAceitos() +
                    " " +
                    "cadeias " +
                    estado.getCadeiaEstadoHashMap());

        });


    }

    private void adicionaEstadosNaListaDeEstados(AutomatoFN q) {
        listaDeEstados.add(q);
    }

    private void insereNomeDoEstado(String estados, AutomatoFN q) {
        String[] split = estados.split(" ");
        String nomeDoEstado = split[0];
        q.setNome(nomeDoEstado);
    }

    private void insereEstadosAtivos(String[] estados, AutomatoFN q) {

        q.setEstadoAtivo(Boolean.valueOf(estados[estados.length - 2]));
        q.setEstadoFinal(Boolean.valueOf(estados[estados.length - 1]));

    }

    private void importarCadeiasLidasPeloEstado(String linha, AutomatoFN q) {

        String[] stringConjuntoDeCadeiaEstadosHashMap = linha.split(",");

        for (int i = 1; i < stringConjuntoDeCadeiaEstadosHashMap.length - 2; i++) {

//            System.out.println(stringConjuntoDeCadeiaEstadosHashMap[i]);

            String[] stringCadeiaEstado = stringConjuntoDeCadeiaEstadosHashMap[i].trim().split(" ");

            String cadeia = stringCadeiaEstado[0];
            String nomeDoestado = stringCadeiaEstado[1];
            AutomatoFN estado = retornaEstadoAPartirDoNome(nomeDoestado);

//            System.out.println("cadeia " + cadeia);
//            System.out.println("estado " + estado);

            q.cadeiaEstadoHashMap.put(cadeia, estado);

        }
    }

    public AutomatoFN retornaEstadoAPartirDoNome(String nomeDoEstado) {
        AutomatoFN retornaEstado = null;


        for (AutomatoFN estado : listaDeEstados) {
            System.out.println(estado);
            if (nomeDoEstado.equals(estado.getNome())) {

                retornaEstado = estado;
                System.out.println(estado);

//                System.out.println("nome do estado que sera retornado: " + retornaEstado);
            }

        }

        return retornaEstado;
    }

    //endregion métodos utilizados por decodificaStringParaCriarEstados


    public void lerCadeiaDeDados(String cadeiaDeDados) {




    }

    public void retornaEstadosAtivosAtuais() {

        System.out.print("estados ativos atuais: ");

        listaDeEstados.forEach(AutomatoFN -> {
            if (AutomatoFN.estadoAtivo) System.out.print("[" + AutomatoFN + "]");
        });


    }

    @Override
    public String toString() {
        return this.nome;

    }

}




