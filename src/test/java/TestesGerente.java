
/**
    @author Lara da Silva Dias (202376010)
    @author Sarah Cristina (202376034)
    @author Wilian Santos (202276040)
 */

import com.mycompany.systembank.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;
import java.util.List;

public class TestesGerente {

    private Gerente gerente;

    @BeforeEach
    public void setUp() {
        BankSystem.opcoesInvestimento.clear();
        gerente = new Gerente("Gerente Teste", "123.456.789-00", "01/01/1980", "1234-5678", "gerente@email.com", 123);
    }

    @Test
    public void testeAdicionarOpcaoInvestimento() {
        Map<String, Object> opcao = Map.of(
                "descricao", "Investimento Teste",
                "tipo", "renda fixa",
                "taxa", 10.0,
                "rentabilidade", 5.0,
                "prazoMinimo", 12,
                "prazoMaximo", 24
        );
        gerente.cadastrarOpcaoInvestimento(opcao);
        List<Map<String, Object>> opcoes = Gerente.getOpcoesInvestimento();
        assertEquals(1, opcoes.size());
        assertEquals("Investimento Teste", opcoes.get(0).get("descricao"));
    }


    @Test
    public void testeAprovarSolicitacao() {
        // Simulando uma solicitação pendente
        Map<String, Object> solicitacao = Map.of(
                "tipo", "credito",
                "nomeCliente", "Cliente Teste",
                "cpfCliente", "123.456.789-00",
                "contaCliente", 1001,
                "valor", 1000.0,
                "status", "pendente"
        );
        BankSystem.solicitacoes.add(solicitacao);
        gerente.analisarSolicitacoes(0, "Aprovado");
        assertEquals("Aceito", BankSystem.solicitacoes.get(0).get("status"));
    }
}