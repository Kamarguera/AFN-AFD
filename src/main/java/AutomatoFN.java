import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import java.util.*;


public class AutomatoFN {


    public static List<AutomatoFN> listaDeEstadosAFN = new ArrayList<>();
    public List<AutomatoFN> estadosDeTransicaoAceitos = new ArrayList<>();
    public List<String> cadeiasLidasPeloEstado = new ArrayList<>();
    public ArrayList<AutomatoFN> transicaoDeEstados;
    public List<AutomatoFN> listaDeEstadosAtivos = new ArrayList<>();
    //region variáveis
    Map<String, List<AutomatoFN>> listaDeEstadosAFD = new HashMap<>();
    ListMultimap<String, AutomatoFN> cadeiaEstadoHashMap = ArrayListMultimap.create();
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

        for (String linhas : cadeiaDeEntradaSeparadaPorLinhas) {

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


    public void lerCaracteresUmAUmInserindoNoAutomato(String cadeiaDeDados) {

//        for (int i = 0; i < cadeiaDeDados.length(); i++) {
//            String caractere = String.valueOf(cadeiaDeDados.charAt(i));
//
//
//
//            for (AutomatoFN listaDeEstadosAtivo : listaDeEstadosAtivos) {
//
//                System.out.println(listaDeEstadosAtivo);
//
//                AutomatoFN estado = listaDeEstadosAtivo; //estado q1
//                List<AutomatoFN> listaDeEstadosDestino = estado.cadeiaEstadoHashMap.get(caractere);
//
//
//                System.out.println("lista de estados ativos" + listaDeEstadosAtivos);
//                System.out.println("lista de estados destino" + listaDeEstadosDestino);
//
//
//                System.out.println("usando cadeia " + "'" + caractere + "'" + " no estado " + estado + " " + " irá para:" + listaDeEstadosDestino); //usando cadeia a
//
//
//
//            }
//
//        }
//        AutomatoFN estado = listaDeEstadosAtivos.get(0); //estado q1
//            estado.cadeiaEstadoHashMap.get(cadeiaDeDados);

        listaDeEstadosAtivos.forEach(estadoAtivo -> {
            listaDeEstadosDestino.addAll(estadoAtivo.cadeiaEstadoHashMap.get("_"));
        });

        listaDeEstadosAtivos.addAll(listaDeEstadosDestino);
        listaDeEstadosDestino.clear();

        System.out.println("estados ativos" + listaDeEstadosAtivos + " será lida: '" + cadeiaDeDados + "'");


        for (AutomatoFN listaDeEstadosAtivo : listaDeEstadosAtivos) {

//            System.out.println("lista de estado que devem ser adicionados a lista de estado destino" + listaDeEstadosAtivo.cadeiaEstadoHashMap.get(cadeiaDeDados));


            listaDeEstadosAtivo.cadeiaEstadoHashMap.get(cadeiaDeDados).forEach(estadoDestino -> {

                listaDeEstadosDestino.add(estadoDestino);

//
//                System.out.println("lista destino" + listaDeEstadosDestino);
            });

        }

//        System.out.println("lista de estados ativos" + listaDeEstadosAtivos);
//        System.out.println("lista de estados destino" + listaDeEstadosDestino);

        if (listaDeEstadosDestino.size() > 0) {
            listaDeEstadosAtivos.clear();

        }


        listaDeEstadosDestino.forEach(estadoAtivar -> {
            estadoAtivar.estadoAtivo = true;
            if (!listaDeEstadosAtivos.contains(estadoAtivar)) {
                listaDeEstadosAtivos.add(estadoAtivar);

            }


//            System.out.println("lista de estados ativos" + listaDeEstadosAtivos);
//            System.out.println("lista de estados destino" + listaDeEstadosDestino);

        });

        listaDeEstadosDestino.clear();
        listaDeEstadosAtivos.addAll(listaDeEstadosDestino);

//        System.out.println("ativos" + listaDeEstadosAtivos);

        cadeiaAceitaBool = false;

        for (AutomatoFN listaDeEstadosAtivo : listaDeEstadosAtivos)
            if (listaDeEstadosAtivo.estadoFinal) {
                cadeiaAceitaBool = true;
                break;
            }

//        System.out.println("estados ativos" + listaDeEstadosAtivos + " cadeia lida " + cadeiaDeDados);


//            listaDeEstadosDestino.add(listaDeEstadosAtivo.cadeiaEstadoHashMap.get(cadeiaDeDados));

//            System.out.println("lista de estados destino usando o estado " + listaDeEstadosAtivo + listaDeEstadosAtivo.cadeiaEstadoHashMap.get(cadeiaDeDados) + " e a cadeia " + cadeiaDeDados);


//            System.out.println("lista destino" + listaDeEstadosDestino);


//
//        List<AutomatoFN> listaDeEstadosDestino = estado.cadeiaEstadoHashMap.get(cadeiaDeDados);
//
//        System.out.println("lista de estados ativos" + listaDeEstadosAtivos);
//        System.out.println("lista de estados destino" + listaDeEstadosDestino);
//
//
//        listaDeEstadosDestino.forEach(estadoAtivado -> {
//            estado.estadoAtivo = false;
//            if (listaDeEstadosAtivos.contains(estado)) listaDeEstadosAtivos.remove(estado);
//
//            estadoAtivado.estadoAtivo = true;
//
////            listaDeEstadosDestino.forEach(estadoDestino -> {
//                if (!listaDeEstadosAtivos.contains(estadoDestino)) {
//                    listaDeEstadosAtivos.add(estadoDestino);


    }
//            });
//
//        });
//
//        listaDeEstadosDestino.clear();

//        System.out.println("lista de estados ativos" + listaDeEstadosAtivos);
//        System.out.println("lista de estados destino" + listaDeEstadosDestino);
//

//
//        System.out.println(estado + " " + estado.cadeiaEstadoHashMap.size());

//
//        for (int i = 0; i < listaDeEstadosAtivos.size(); i++) {
//
//            AutomatoFN estado = listaDeEstadosAtivos.get(i);
//
//            System.out.println(listaDeEstadosAtivos.get(i) + " " + listaDeEstadosAtivos.get(i).cadeiaEstadoHashMap.size());
//
//
//            List<AutomatoFN> deveIr = estado.cadeiaEstadoHashMap.get(cadeiaDeDados);
//
//            System.out.println(deveIr.size());
//
//
//            System.out.println(estado.cadeiaEstadoHashMap.get(cadeiaDeDados));
//
//
//            if (deveIr != null) {
//                estado.estadoAtivo = false;
//
//
//
//                for (AutomatoFN deveir : deveIr) {
//                    deveir.estadoAtivo = true;
//                }
//
//
//                atualizaEstadosAtivos();
//            }
//
//
//            System.out.println("estado " + estado + " deve ir: " + deveIr + " usando cadeia " + cadeiaDeDados);
//
//
//        }
//
//        System.out.println(listaDeEstadosAtivos);


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
            lerCaracteresUmAUmInserindoNoAutomato(caractere);
        }
        System.out.println("Após ler cadeia: '" + cadeiaDeDados.substring(cadeiaDeDados.length() - 1) + "' estados ativos: " + listaDeEstadosAtivos);

        verificarSeCadeiaAceita();

    }

    public void criaEstadosDoAFD() {

        System.out.println("Estados do AFD");


//
//        for (int i = 0; i < listaDeEstadosAFN.size(); i++) {
//
//

        for (int i = 0; i < listaDeEstadosAFN.size(); i++) {

            for (int j = 0; j < listaDeEstadosAFN.size(); j++) {


//                System.out.println(j + 1 + " " + (i + 1));


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

                key = "["+ key + "]";

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











        System.out.println(listaDeEstadosAFD.size());


        listaDeEstadosAFD.get(0);

        System.out.println(listaDeEstadosAFD);
////        }
//        System.out.println(listaDeEstadosAFD.get("[q1 q2]"));


    }
}




