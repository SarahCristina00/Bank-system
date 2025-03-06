
/**
    @author Lara da Silva Dias (202376010)
    @author Sarah Cristina (202376034)
    @author Wilian Santos (202276040)
 */

import com.mycompany.systembank.ContaBancaria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestesContaBancaria {

    private ContaBancaria conta;

    @BeforeEach
    public void setUp() {
        conta = new ContaBancaria();
    }

    @Test
    public void testDeposito() {
        conta.registraTransacao("Deposito", 100.0, null, conta);
        assertEquals(100.0, conta.getSaldo());
    }

    @Test
    public void testeSaque() {
        conta.registraTransacao("Deposito", 200.0, null, conta);
        conta.registraTransacao("Saque", 50.0, conta, null);
        assertEquals(150.0, conta.getSaldo());
    }

    @Test
    public void testeTransferencia() {
        ContaBancaria contaDestino = new ContaBancaria();
        conta.registraTransacao("Deposito", 300.0, null, conta);
        conta.transfereSaldo(100.0, contaDestino);
        assertEquals(200.0, conta.getSaldo());
        assertEquals(100.0, contaDestino.getSaldo());
    }

    @Test
    public void testeInvestimento() {
        conta.registraTransacao("Deposito", 500.0, null, conta);
        conta.registraTransacao("Investimento Teste", 200.0, conta, null);
        assertEquals(300.0, conta.getSaldo());
    }

    @Test
    public void testeExtrato() {
        conta.registraTransacao("Deposito", 100.0, null, conta);
        conta.registraTransacao("Saque", 50.0, conta, null);
        assertEquals(2, conta.getExtrato().size());
    }
}