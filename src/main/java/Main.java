import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) throws Exception {
//
//
//

//        inserirCadeiaDeDadosNoAFN();
        lerCadeiaTransicoes();


//
//
//
//
   }

    private static void lerCadeiaTransicoes() throws Exception {


//        String path = "C:\\PROJETOS\\CRESCER\\AFN-AFD\\src\\main\\java\\Cadeia_de_estados_transicoes.txt";
//
//        System.out.println(System.getProperty("user.dir") + "\\src\\main\\java\\Cadeia_de_estados_transicoes.txt");

        File file = new File(
                System.getProperty("user.dir") + "\\src\\main\\java\\Cadeia_de_estados_transicoes.txt"
                );

        BufferedReader br
                = new BufferedReader(new FileReader(file));


        String cadeiaDeDados = "";

        String st;
        // Condition holds true till
        // there is character in a string
        while ((st = br.readLine()) != null)
            cadeiaDeDados = st;
//            System.out.println(st);

        // Print the string


//        System.out.println(cadeiaDeDados);
        System.out.println(cadeiaDeDados);


    }

    //
    private static void inserirCadeiaDeDadosNoAFN() throws Exception {

//        System.out.println(
//                System.getProperty("user.dir") + "\\src\\main\\java\\Cadeia_de_caracteres.txt"
//        );



        // File path is passed as parameter
        File file = new File(
                System.getProperty("user.dir") + "\\src\\main\\java\\Cadeia_de_caracteres.txt");

        // Note:  Double backquote is to avoid compiler
        // interpret words
        // like \test as \t (ie. as a escape sequence)

        // Creating an object of BufferedReader class
        BufferedReader br
                = new BufferedReader(new FileReader(file));

        String cadeiaDeDados = "";
        // Declaring a string variable
        String st;
        // Condition holds true till
        // there is character in a string
        while ((st = br.readLine()) != null)
            cadeiaDeDados = st;
//            System.out.println(st);

            // Print the string


//        System.out.println(cadeiaDeDados);
        System.out.println(cadeiaDeDados);



        AutomatoFN AutomatoFN = new AutomatoFN();
        AutomatoFN.decodificaStringParaCriarEstados();

        AutomatoFN.criaEstadosDoAFD();
        AutomatoFN.lerCadeiaDeDadosAFD("ababa");


    }

}


//TEST








