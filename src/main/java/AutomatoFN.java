import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import java.util.*;
import java.util.stream.Collectors;


public class AutomatoFN {


    public static List<AutomatoFN> listaDeEstadosAFN = new ArrayList<>();
    public List<AutomatoFN> estadosDeTransicaoAceitos = new ArrayList<>();
    public List<String> cadeiasLidasPeloEstado = new ArrayList<>();
    public ArrayList<AutomatoFN> transicaoDeEstados;
    public List<AutomatoFN> listaDeEstadosAtivos = new ArrayList<>();
    public Map<String, List<AutomatoFN>> listaDeEstadosAtivosAFD = new HashMap<>();
    //region variáveis
    Map<String, List<AutomatoFN>> listaDeEstadosAFD = new HashMap<>();
    ListMultimap<String, AutomatoFN> cadeiaEstadoHashMap = ArrayListMultimap.create();
    ListMultimap<String, AutomatoFN> cadeiaEstadoHashMapAFN = ArrayListMultimap.create();
    Boolean cadeiaAceitaBool = false;
    List<AutomatoFN> listaDeEstadosDestino = new ArrayList<>();
    //    public HashMap<String>, AutomatoFN> cadeiaEstadoHashMap = new HashMap<>();
    Boolean estadoInicial, estadoFinal, estadoAtivo = false;
    String caracteresAceitosParaATransicao;
    char cadeia;
    String stringDeEntrada = "q1, _ q2, b q3, true,true\n" +
            "q2, a q1,  false,false\n" +
            "q3, a q3, a q2, b q2, false,false";

    private String nome;


    //endregion variáveis

    //region Construtor
    public AutomatoFN() {
    }


    //endregion Construtor

    //region getters&setters

    public ListMultimap<String, AutomatoFN> getCadeiaEstadoHashMap() {
        return cadeiaEstadoHashMap;
    }

    public void setCadeiaEstadoHashMap(ListMultimap<String, AutomatoFN> cadeiaEstadoHashMap) {
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
        return listaDeEstadosAFN;
    }

    public void setListaDeEstados(List<AutomatoFN> listaDeEstados) {
        this.listaDeEstadosAFN = listaDeEstados;
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

        System.out.println("str entrada:" + stringDeEntrada);

        for (String linhas : cadeiaDeEntradaSeparadaPorLinhas) {
            System.out.println(linhas);

            AutomatoFN q = new AutomatoFN();


            String[] estados = linhas.split(",");

            insereNomeDoEstado(estados[0], q);

            adicionaEstadosNaLista(q);
            insereEstadosAtivos(estados, q);
            adicionaEstadosAtivosNaListaDeEstadosAtivos(q);


        }

        for (int i = 0; i < cadeiaDeEntradaSeparadaPorLinhas.length; i++) {

            String[] estados = cadeiaDeEntradaSeparadaPorLinhas[i].split(",");

//            adicionarEstadosDeTransicao(estados, listaDeEstadosAFN.get(i));
            importarCadeiasLidasPeloEstado(cadeiaDeEntradaSeparadaPorLinhas[i], listaDeEstadosAFN.get(i));


        }


        printEstadosAceitosECadeias();


    }

    //region métodos utilizados por  decodificaStringParaCriarEstados
    private void adicionarEstadosDeTransicao(String[] estados, AutomatoFN q) {


        for (int i = 1; i < estados.length - 2; i++) {

            System.out.println(estados[i]);
            q.estadosDeTransicaoAceitos.add(

                    listaDeEstadosAFN.get(i)

            );


        }

    }

    private void printEstadosAceitosECadeias() {

        listaDeEstadosAFN.forEach((estado) -> {

            System.out.println("Estado: [" + estado + "] " + "Transições: " + estado.getCadeiaEstadoHashMap());

        });

        System.out.println("----------------------------------------------------------------");
    }

    private void adicionaEstadosNaLista(AutomatoFN q) {
        listaDeEstadosAFN.add(q);
    }

    private void adicionaEstadosAtivosNaListaDeEstadosAtivos(AutomatoFN q) {
        if (q.getEstadoAtivo() && !listaDeEstadosAtivos.contains(q)) listaDeEstadosAtivos.add(q);
    }

    private void removeEstadosAtivosNaListaDeEstadosAtivos(AutomatoFN q) {
        if (!q.getEstadoAtivo() && listaDeEstadosAtivos.contains(q)) listaDeEstadosAtivos.remove(q);
    }


    private void insereNomeDoEstado(String estados, AutomatoFN q) {
        String[] split = estados.split(" ");
        String nomeDoEstado = split[0];
        q.setNome(nomeDoEstado);
    }

    private void insereEstadosAtivos(String[] estados, AutomatoFN q) {

        q.setEstadoAtivo(Boolean.valueOf(estados[estados.length - 2].trim()));
        q.setEstadoFinal(Boolean.valueOf(estados[estados.length - 1].trim()));


    }

    private void importarCadeiasLidasPeloEstado(String linha, AutomatoFN q) {


        String[] stringConjuntoDeCadeiaEstadosHashMap = linha.split(",");

        for (int i = 1; i < stringConjuntoDeCadeiaEstadosHashMap.length - 2; i++) {

//            System.out.println(stringConjuntoDeCadeiaEstadosHashMap[i]);

            String[] stringCadeiaEstado = stringConjuntoDeCadeiaEstadosHashMap[i].trim().split(" ");

            String cadeia = stringCadeiaEstado[0];
            String nomeDoestado = stringCadeiaEstado[1];
            AutomatoFN estado = retornaEstadoAPartirDoNome(nomeDoestado);
////
//            System.out.println("cadeia " + cadeia);
//            System.out.println("estado " + estado);

            q.cadeiaEstadoHashMap.put(cadeia, estado);

//            System.out.println(q.cadeiaEstadoHashMap.get(cadeia));


        }
    }

    public AutomatoFN retornaEstadoAPartirDoNome(String nomeDoEstado) {
        AutomatoFN retornaEstado = null;


        for (AutomatoFN estado : listaDeEstadosAFN) {
            if (nomeDoEstado.equals(estado.getNome())) {

                retornaEstado = estado;

//                System.out.println("nome do estado que sera retornado: " + retornaEstado);
            }

        }

        return retornaEstado;
    }

    //endregion métodos utilizados por decodificaStringParaCriarEstados


    private void atualizaEstadosAtivos() {


        for (int i = 0; i < listaDeEstadosAtivos.size(); i++) {


            System.out.println(listaDeEstadosAtivos.size());

            AutomatoFN estado = listaDeEstadosAtivos.get(i);

            if (estado.estadoAtivo) adicionaEstadosAtivosNaListaDeEstadosAtivos(estado);
            if (!estado.estadoAtivo) removeEstadosAtivosNaListaDeEstadosAtivos(estado);
            System.out.println(estado + "esta marcado como ativo: " + estado.estadoAtivo);
//


        }


    }

    public AutomatoFN retornaEstadosAtivosAtuais() {

        AutomatoFN retornaEstadoAtivo = null;


        for (AutomatoFN estado : listaDeEstadosAFN) {
            if (estado.getEstadoAtivo()) {
                System.out.print("[" + estado + "]");
                retornaEstadoAtivo = estado;
            }

        }


        return retornaEstadoAtivo;

    }


    public void verificarSeCadeiaAceita() {

        System.out.println("Cadeia aceita: " + cadeiaAceitaBool);

    }

    @Override
    public String toString() {
        return this.nome;

    }

    public void lerCadeiaDeDados(String cadeiaDeDados) {

        String caractere = cadeiaDeDados;

        for (int i = 0; i < cadeiaDeDados.length(); i++) {
            caractere = String.valueOf(cadeiaDeDados.charAt(i));
            lerCaracteresUmAUmInserindoNoAutomatoAFD(caractere);
        }
        System.out.println("Após ler cadeia: '" + cadeiaDeDados.substring(cadeiaDeDados.length() - 1) + "' estados ativos: " + listaDeEstadosAtivos);

        verificarSeCadeiaAceita();
    }

    public String stringDeEntrada(String transicoes){

        this.stringDeEntrada = transicoes;
        return transicoes;
    }
    public List<AutomatoFN> lerCadeiaDeDadosAFD(String cadeiaDeDados) {

        System.out.println("Cadeia de entrada: "+cadeiaDeDados);

        String caractere = cadeiaDeDados;

        for (int i = 0; i < cadeiaDeDados.length(); i++) {
            caractere = String.valueOf(cadeiaDeDados.charAt(i));
            lerCaracteresUmAUmInserindoNoAutomatoAFD(caractere);
        }
        System.out.println("Após ler cadeia: '" + cadeiaDeDados.substring(cadeiaDeDados.length() - 1) + "' estados ativos: " + listaDeEstadosAtivos);

        verificarSeCadeiaAceita();
        return listaDeEstadosAtivos;
    }


    public void criaEstadosDoAFD() {
//
//        System.out.println("-----------------------------------------");
//        System.out.println("Estados do AFD");

        for (int i = 0; i < listaDeEstadosAFN.size(); i++) {

            for (int j = 0; j < listaDeEstadosAFN.size(); j++) {

                List<AutomatoFN> value = new ArrayList<>();

                value.add(listaDeEstadosAFN.get(j));
                if (!value.contains(listaDeEstadosAFN.get(i))) {
                    value.add(listaDeEstadosAFN.get(i));
                }

                String key2 = "";
                if (!Objects.equals(listaDeEstadosAFN.get(i).toString(), listaDeEstadosAFN.get(j).toString())) {
                    key2 = " " + listaDeEstadosAFN.get(i).toString();
                }
                ;

                String key = listaDeEstadosAFN.get(j).toString() + "" + key2;


//                System.out.println(key);

                key = "[" + key + "]";

                listaDeEstadosAFD.put(key, value);


                if (i + 1 == j + 1) {
                    j++;
                }

                if (j - j == i && j + 1 < listaDeEstadosAFN.size()) {
                    j++;
                }


            }

        }


        List<AutomatoFN> value = new ArrayList<>();

        for (AutomatoFN automatoFN : listaDeEstadosAFN) {
            value.add(automatoFN);

        }

        String key = "";
        for (int i = 0; i < listaDeEstadosAFN.size(); i++) {

            if (i > 0) {
                key = key + " " + listaDeEstadosAFN.get(i).toString();

            } else {
                key = key + listaDeEstadosAFN.get(i).toString();
            }


        }

        key = "[" + key + "]";

//        System.out.println(key);

        listaDeEstadosAFD.put(key, value);

//        System.out.println(listaDeEstadosAFN);

//
//        System.out.println(listaDeEstadosAFN.get(0));
//        System.out.println(listaDeEstadosAFN.get(1));
////
//


        ///estado vazio
        List<AutomatoFN> listaVazia = new ArrayList<>();
        listaDeEstadosAFD.put("_", listaVazia);


//        System.out.println(listaDeEstadosAFD.size());


//        System.out.println(listaDeEstadosAFD);

//print todos os estados
        listaDeEstadosAFD.keySet().forEach(stringEstadoAFN -> {
//            System.out.println("Estado: " + stringEstadoAFN);
        });

//        System.out.println(listaDeEstadosAFD.get("[q1 q2]"));


        //OBTER ESTADO ATIVO AFd
        //= ESTADOS ATIVOS INICIAIS AFN


        listaDeEstadosAtivos.forEach(estadoAtivo -> {
            listaDeEstadosDestino.addAll(estadoAtivo.cadeiaEstadoHashMap.get("_"));
        });

        listaDeEstadosAtivos.addAll(listaDeEstadosDestino);
        listaDeEstadosDestino.clear();

        removeDuplicatasDaLista(listaDeEstadosAtivos);


        //obter estado ativo
        String stringEstadoAtivoAFD = listaDeEstadosAtivos.toString().replace(",", "");
//        System.out.println(listaDeEstadosAFD.get(stringEstadoAtivoAFD));


        //insere estado ativo na lista ativo AFD
        listaDeEstadosAtivosAFD.put(stringEstadoAtivoAFD, listaDeEstadosAFD.get(stringEstadoAtivoAFD));

//        System.out.println("Estado: " + listaDeEstadosAtivosAFD.keySet());


//        System.out.println("Estado: " + listaDeEstadosAtivosAFD.keySet() + " Transições: b" + ));


//        System.out.println("Após ler cadeia: '" + cadeiaDeDados.substring(cadeiaDeDados.length() - 1) + "' estados ativos: " + listaDeEstadosAtivos);


//        lerCadeiaDeDadosAFD("b").forEach(System.out::println);


//        System.out.println();

//        System.out.println("estado ativo afn: " + lerCadeiaDeDadosAFD("b"));


//        System.out.println("Após ler cadeia: '" + cadeiaDeDados.substring(cadeiaDeDados.length() - 1) + "' estados ativos: " + listaDeEstadosAtivos);

//        System.out.println(listaDeEstadosAFD.get("[q1 q2]"));

//        verificarSeCadeiaAceita();
    }

    private void removeDuplicatasDaLista(List<AutomatoFN> listaDeEstadosAtivos) {

        this.listaDeEstadosAtivos = listaDeEstadosAtivos.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public void lerCaracteresUmAUmInserindoNoAutomato(String cadeiaDeDados) {


        listaDeEstadosAtivos.forEach(estadoAtivo -> {
            listaDeEstadosDestino.addAll(estadoAtivo.cadeiaEstadoHashMap.get("_"));
        });

        listaDeEstadosAtivos.addAll(listaDeEstadosDestino);
        listaDeEstadosDestino.clear();

        removeDuplicatasDaLista(listaDeEstadosAtivos);


        System.out.println("estados ativos" + listaDeEstadosAtivos + " será lida: '" + cadeiaDeDados + "'");


        for (AutomatoFN listaDeEstadosAtivo : listaDeEstadosAtivos) {
//            System.out.println("lista de estado que devem ser adicionados a lista de estado destino" + listaDeEstadosAtivo.cadeiaEstadoHashMap.get(cadeiaDeDados));


            listaDeEstadosAtivo.cadeiaEstadoHashMap.get(cadeiaDeDados).forEach(estadoDestino -> {

                listaDeEstadosDestino.add(estadoDestino);

//
//                System.out.println("lista destino" + listaDeEstadosDestino);
            });

        }


//

//        System.out.println("a" + listaDeEstadosAtivos);
//        System.out.println("d" + listaDeEstadosDestino);

        if (listaDeEstadosDestino.size() > 0 && !listaDeEstadosDestino.get(0).getCadeiaEstadoHashMap().containsKey("_")) {
            listaDeEstadosAtivos.clear();
        }


//        System.out.println("lista destiaaaaaaaano" + listaDeEstadosDestino);

//        System.out.println("d" + listaDeEstadosDestino);
//        System.out.println("vai ler:" + listaDeEstadosDestino.get(0).getCadeiaEstadoHashMap().containsKey("_"));
//        System.out.println("cehmp:" + listaDeEstadosDestino.get(0).getCadeiaEstadoHashMap());


//        System.out.println(listaDeEstadosDestino.size());
        for (int i = 0; i < listaDeEstadosDestino.size(); i++) {
//            System.out.println(i);
            if (listaDeEstadosDestino.get(i).getCadeiaEstadoHashMap().containsKey("_")) {


                obterTransicaoEspontanea(listaDeEstadosDestino, cadeiaDeDados);
            }
//            System.out.println(i);


        }


        listaDeEstadosDestino.forEach(estadoAtivar -> {
            estadoAtivar.estadoAtivo = true;
            if (!listaDeEstadosAtivos.contains(estadoAtivar)) {
                listaDeEstadosAtivos.add(estadoAtivar);

            }


        });


        listaDeEstadosDestino.clear();
        listaDeEstadosAtivos.addAll(listaDeEstadosDestino);

        removeDuplicatasDaLista(listaDeEstadosAtivos);


//        System.out.println("ativos" + listaDeEstadosAtivos);

        cadeiaAceitaBool = false;

        for (AutomatoFN listaDeEstadosAtivo : listaDeEstadosAtivos)
            if (listaDeEstadosAtivo.estadoFinal) {
                cadeiaAceitaBool = true;
                break;
            }

    }


    public void lerCaracteresUmAUmInserindoNoAutomatoAFD(String cadeiaDeDados) {


        listaDeEstadosAtivos.forEach(estadoAtivo -> {
            listaDeEstadosDestino.addAll(estadoAtivo.cadeiaEstadoHashMap.get("_"));
        });

        listaDeEstadosAtivos.addAll(listaDeEstadosDestino);
        listaDeEstadosDestino.clear();

        removeDuplicatasDaLista(listaDeEstadosAtivos);


        System.out.println("estados ativos" + listaDeEstadosAtivos + " será lida: '" + cadeiaDeDados + "'");


        for (AutomatoFN listaDeEstadosAtivo : listaDeEstadosAtivos) {
//            System.out.println("lista de estado que devem ser adicionados a lista de estado destino" + listaDeEstadosAtivo.cadeiaEstadoHashMap.get(cadeiaDeDados));

            listaDeEstadosAtivo.cadeiaEstadoHashMap.get(cadeiaDeDados).forEach(estadoDestino -> {

                listaDeEstadosDestino.add(estadoDestino);

//
            });

        }


//

//        System.out.println("a" + listaDeEstadosAtivos);
//        System.out.println("d" + listaDeEstadosDestino);

        Boolean contains = false;

        //se não contem, deve executar "clear" estados ativos;

        for (int i = 0; i < listaDeEstadosDestino.size(); i++) {
            if (listaDeEstadosDestino.size() > 0 && listaDeEstadosDestino.get(i).getCadeiaEstadoHashMap().containsKey("_")) {
                contains = true;
                break;

            }


        }

        if (!contains) listaDeEstadosAtivos.clear();


//        System.out.println("lista destiaaaaaaaano" + listaDeEstadosDestino);

//        System.out.println("d" + listaDeEstadosDestino);
//        System.out.println("vai ler:" + listaDeEstadosDestino.get(0).getCadeiaEstadoHashMap().containsKey("_"));
//        System.out.println("cehmp:" + listaDeEstadosDestino.get(0).getCadeiaEstadoHashMap());


//        System.out.println(listaDeEstadosDestino.size());
//        for (int i = 0; i < listaDeEstadosDestino.size(); i++) {
////            System.out.println(i);
//            if (listaDeEstadosDestino.get(i).getCadeiaEstadoHashMap().containsKey("_")) {
//                obterTransicaoEspontanea(listaDeEstadosDestino, cadeiaDeDados);
//            }
////            System.out.println(i);
//
//
//        }


        listaDeEstadosAtivos.addAll(listaDeEstadosDestino);

        removeDuplicatasDaLista(listaDeEstadosAtivos);
//
//        System.out.println("lista destinoa" + listaDeEstadosDestino);
//        System.out.println("lista ativoos" + listaDeEstadosAtivos);


//        listaDeEstadosDestino.forEach(estadoAtivar -> {
//            estadoAtivar.estadoAtivo = true;
//
//
//
//            if (!listaDeEstadosAtivos.contains(estadoAtivar)) {
//                listaDeEstadosAtivos.add(estadoAtivar);
//
//            }
//
//
//        });


        listaDeEstadosDestino.clear();



//        System.out.println("ativos" + listaDeEstadosAtivos);

        cadeiaAceitaBool = false;

        for (AutomatoFN listaDeEstadosAtivo : listaDeEstadosAtivos)
            if (listaDeEstadosAtivo.estadoFinal) {
                cadeiaAceitaBool = true;
                break;
            }

    }


    private void obterTransicaoEspontanea(List<AutomatoFN> listaDeEstadosAtivos, String cadeiaDeDados) {


        listaDeEstadosAtivos.clear();
////
//        System.out.println("da" + listaDeEstadosDestino);
//        System.out.println("a" + listaDeEstadosAtivos);

        listaDeEstadosAtivos.forEach(estadoAtivo -> {
            listaDeEstadosDestino.addAll(estadoAtivo.cadeiaEstadoHashMap.get("_"));
        });


        for (AutomatoFN listaDeEstadosAtivo : listaDeEstadosAtivos) {

//            System.out.println("lista de estado que devem ser adicionados a lista de estado destino" + listaDeEstadosAtivo.cadeiaEstadoHashMap.get(cadeiaDeDados));

            listaDeEstadosAtivo.cadeiaEstadoHashMap.get(cadeiaDeDados).forEach(estadoDestino -> {

                listaDeEstadosDestino.add(estadoDestino);
                removeDuplicatasDaLista(listaDeEstadosDestino);


            });


        }
//        System.out.println("lista destino" + listaDeEstadosDestino);
    }

}


