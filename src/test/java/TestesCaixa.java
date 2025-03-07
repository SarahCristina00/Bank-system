
/**
    @author Lara da Silva Dias (202376010)
    @author Sarah Cristina (202376034)
    @author Wilian Santos (202276040)
 */

import com.mycompany.systembank.Caixa;
import com.mycompany.systembank.Cliente;
import com.mycompany.systembank.ContaBancaria;
import com.mycompany.systembank.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestesCaixa {
private Caixa caixa;
    private Cliente cliente, clienteDestino;
    private ContaBancaria conta, contaDestino;
    private Endereco endereco;

    @BeforeEach
    public void setUp() {
        caixa = new Caixa("Caixa Teste", "123.456.789-00", "01/01/1990", "1234-5678", "caixa@email.com", 123);
        conta = new ContaBancaria();
        contaDestino = new ContaBancaria();
        endereco = new Endereco("Rua A", 123, "Bairro X", "Complemento 52", "Cidade Y", "Estado Z", "CEP 253");
        cliente = new Cliente("Cliente Teste", "987.654.321-00", "02/02/2000", "9876-5432", "cliente@email.com", 123, endereco, conta);
        clienteDestino = new Cliente("Destino Teste", "111.222.333-44", "03/03/2001", "1111-2222", "destino@email.com", 123, endereco, contaDestino);
    }

    @Test
    public void testeSacar() {
        conta.registraTransacao("Deposito", 500.0, null, conta);
        caixa.processarSaque(cliente, 200.0);
        assertEquals(300.0, conta.getSaldo());
    }

    @Test
    public void testeDepositar() {
        caixa.processarDeposito(cliente, 300.0);
        assertEquals(300.0, conta.getSaldo());
    }

    @Test
    public void testeTransferir() {
        
        conta.registraTransacao("Deposito", 500.0, conta, clienteDestino.getConta());
        caixa.processarTransferencia(cliente, clienteDestino, 200.0);
        assertEquals(300.0, conta.getSaldo());
        assertEquals(200.0, clienteDestino.getConta().getSaldo());
    }
}