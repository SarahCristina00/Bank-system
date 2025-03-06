
/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
*/

import com.mycompany.systembank.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

public class TestesCliente {

    private Cliente cliente;
    private ContaBancaria conta;
    private Endereco endereco;

    @BeforeEach
    public void setUp() {
        conta = new ContaBancaria();
        endereco = new Endereco("Rua A", 123, "Bairro X", "Complemento 52", "Cidade Y", "Estado Z", "CEP 253");
        cliente = new Cliente("Cliente Teste", "123.456.789-00", "01/01/2000", "1234-5678", "teste@email.com", 123, endereco, conta);
    }

    @Test
    public void testeTransferir() {
        Cliente clienteDestino = new Cliente("Destino Teste", "987.654.321-00", "02/02/2001", "9876-5432", "destino@email.com", 5678, endereco, new ContaBancaria());
        conta.registraTransacao("Deposito", 500.0, null, conta);
        assertTrue(cliente.transferir(200.0, clienteDestino));
        assertEquals(300.0, conta.getSaldo());
        assertEquals(200.0, clienteDestino.getConta().getSaldo());
    }

    @Test
    public void testeInvestir() {
        Map<String, Object> opcao = new HashMap<>();
        opcao.put("descricao", "Investimento Teste");
        conta.registraTransacao("Deposito", 1000.0, null, conta);
        cliente.investir(opcao, 500.0);
        assertEquals(500.0, conta.getSaldo());
    }
}