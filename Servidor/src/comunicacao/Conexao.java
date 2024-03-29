package comunicacao;

import controladores.ControladorDeMensagens;
import controladores.ControllerDeTratamento;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Conexao {

    private ServerSocket serverSocket;
    private final Solicitante id;
    private int porta;
    private ConectionIO io;

    private final int PORTAADM = 5555;
    private final int PORTAXIBICAO = 1234;
    private final int PORTASENSOR = 9999;

    private final ControllerDeTratamento tratamento;
    private final ControladorDeMensagens mensagens;

    public Conexao(Solicitante id, ControllerDeTratamento tratamento, ControladorDeMensagens mensagens) {
        this.tratamento = tratamento;
        this.mensagens = mensagens;
        this.id = id;
    }

    public void iniciar() throws IOException {
        conectar();
        Socket socket = esperandoConexao();

        io = new ConectionIO(socket, id, tratamento, mensagens);
        if (io == null) {
            System.out.println("io null");
        }
    }

    public ConectionIO getConectionIO() {
        return io;
    }

    public ServerSocket getSocket() {
        return serverSocket;

    }

    private void criarServerSocket(int porta) throws IOException {
        this.porta = porta;
        serverSocket = new ServerSocket(porta);
    }

    public int getPorta() {
        return porta;
    }

    private Socket esperandoConexao() throws IOException {
        //Faz o serverSocket esperar uma conexão, só da o retorno quando a conexão não é estabelecida
        System.out.println("Esperando a resposta do cliente .....");
        System.out.println("Fique atento se precisar dar permição ao Firewall do seu Sistema Operacional");
        Socket socket = serverSocket.accept();
        System.out.println("conexão estabelecida com o cliente");
        return socket;
    }

    private void conectar() throws IOException {

        /*
        System.out.println("Qual a porta?");
        Scanner s = new Scanner(System.in);
        String sporta = s.nextLine();   
        int porta = Integer.parseInt(sporta);
        
        
        criarServerSocket(porta);
         */
        if (id == Solicitante.ClienteADM) {
            criarServerSocket(PORTAADM);
        } else if (id == Solicitante.ClienteExib) {
            criarServerSocket(PORTAXIBICAO);
        } else if (id == Solicitante.Sensor) {
            criarServerSocket(PORTASENSOR);
        }
    }

}
