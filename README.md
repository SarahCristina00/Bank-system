## Tecnologias e Conceitos Utilizados 💻

- **Linguagem:** Java
- **Paradigma:** Orientação a Objetos (Herança, Polimorfismo, Encapsulamento, Abstração)
- **Persistência:** JSON e XML (usando a biblioteca **Gson**)
- **Interface Gráfica:** Java Swing
- **Testes:** JUnit para testes de unidade
- **Versionamento:** Git e GitHub

---

## Como Executar o Projeto ▶️

### Pré-requisitos:
- Java Development Kit (JDK) 11 ou superior.
- IDE de sua preferência (Eclipse, IntelliJ, VS Code, etc.).

### Passos para Execução:
1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/sistema-bancario-java.git
Abra o projeto na sua IDE.

Compile e execute a classe BankSystem para iniciar o sistema.

Utilize as credenciais de teste ou cadastre novos usuários para interagir com o sistema.

Estrutura do Projeto 📂
Copy
sistema-bancario-java/
├── src/
│   ├── com.mycompany.systembank/       # Classes principais (Cliente, Conta, Usuário, etc.)
│   ├── com.mycompany.interfaces/       # Telas e interfaces gráficas
│   ├── com.mycompany.persistencia/     # Persistência de dados (JSON/XML)
│   └── com.mycompany.tests/            # Testes de unidade
├── data/                               # Arquivos de dados (usuarios.json, contas.json)
└── README.md                           # Documentação do projeto



### Desafios e Aprendizados 🧠
Durante o desenvolvimento, enfrentamos desafios como:

Associação entre Cliente e Conta: Garantir que a conta fosse corretamente vinculada ao cliente após o carregamento dos dados.

Validação de Dados: Implementar verificações robustas para CPF, saldo e campos obrigatórios.

Persistência de Dados: Garantir que os dados fossem corretamente salvos e carregados em JSON/XML.

Esses desafios foram superados com a aplicação de conceitos de Orientação a Objetos e boas práticas de programação.

### Próximos Passos 🚧
Melhorar a Interface: Adotar JavaFX para uma experiência de usuário mais moderna.

Reforçar a Segurança: Implementar criptografia para senhas e dados sensíveis.

Integrar Banco de Dados: Migrar para um banco de dados relacional (MySQL/PostgreSQL).

Adicionar Funcionalidades: Incluir gerenciamento de cartões de crédito, notificações e mais opções de investimento.

Expandir Testes: Aumentar a cobertura de testes automatizados.
