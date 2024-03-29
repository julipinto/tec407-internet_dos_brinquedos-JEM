package controladores;

import comunicacao.ConectionIO;
import comunicacao.Conexao;
import comunicacao.Mensagem;
import comunicacao.Solicitante;
import java.io.IOException;

/**Classe que aplica um protocolo sobre a comunicação
 * com servidor, controlando a instância do cliente.
 * 
 * @author Mateus Guimarães
 * @author Juliana Aragão
 */
public class ControladorDeClientes {
   private Conexao admin;
   
    private final ControllerDeTratamento tratamento;
    private final ControladorDeMensagens mensagens;

    public ControladorDeClientes(ControllerDeTratamento tratamento, ControladorDeMensagens mensagens) {
        this.tratamento = tratamento;
        this.mensagens = mensagens;
    }
    
    
    public void iniciarClienteADM() throws IOException{                                       
        admin = new Conexao(Solicitante.ClienteADM, tratamento, mensagens);
        admin.iniciar();
    }
    
    public ConectionIO getConectionIOADM(){
        return admin.getConectionIO();
    }
    
    
    
}