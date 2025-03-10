# Sistema Bancário em Java 🏦

Este projeto é um **sistema bancário** desenvolvido em Java, como parte de um trabalho prático da disciplina de **Orientação a Objetos**. O sistema permite a gestão de clientes, contas bancárias e operações financeiras, como depósitos, saques, transferências e investimentos. O projeto foi desenvolvido seguindo os princípios de **Orientação a objetos**, com foco em boas práticas de organização de código.

---
## Tecnologias e Conceitos Utilizados 💻

- **Linguagem:** Java
- **Paradigma:** Orientação a Objetos (Herança, Polimorfismo, Encapsulamento, Abstração)
- **Persistência:** JSON  (usando a biblioteca **Gson**)
- **Interface Gráfica:** Java Swing
- **Testes:** JUnit para testes de unidade
- **Versionamento:** Git e GitHub

---

## Como Executar o Projeto ▶️

### Pré-requisitos:
- Java Development Kit (JDK) 11 ou superior.
- IDE de sua preferência (NetBeans, Eclipse, IntelliJ, VS Code, etc.).

### Passos para Execução:
1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/sistema-bancario-java.git
Abra o projeto na sua IDE.

Compile e execute a classe BankSystem para iniciar o sistema.

Somente o administrador pode acessar a tela de cadastro através de seu login que definiu-se como CPF: 000.000.000-00, Senha: 123.


## Estrutura do Projeto 📂

sistema-bancario-java/
├── src/
│   ├── com.mycompany.systembank/       # Classes principais (Cliente, Conta, Usuário, etc.)
│   ├── com.mycompany.interfaces/       # Telas e interfaces gráficas
│   ├── com.mycompany.persistencia/     # Persistência de dados (JSON)
│   └── com.mycompany.tests/            # Testes de unidade
├── data/                               # Arquivos de dados (usuarios.json, contas.json)
└── README.md                           # Apresentação e documentação do projeto



### Desafios e Aprendizados 🧠
Durante o desenvolvimento, enfrentamos desafios como:

Associação entre Cliente e Conta: Garantir que a conta fosse corretamente vinculada ao cliente após o carregamento dos dados.

Validação de Dados: Implementar verificações robustas para CPF, saldo e campos obrigatórios.

Persistência de Dados: Garantir que os dados fossem corretamente salvos e carregados em JSON utilizando Gson do Google.

Esses desafios foram superados com a aplicação de conceitos de Orientação a Objetos e boas práticas de programação, além de diversas revisões feitas.

### Próximos Passos 🚧
Melhorar a Interface: Melhorar a interface para uma experiência de usuário mais moderna.

Reforçar a Segurança: Implementar criptografia para senhas e dados sensíveis.

Integrar Banco de Dados: Migrar para um banco de dados relacional (MySQL/PostgreSQL).

Adicionar Funcionalidades: Incluir gerenciamento de cartões de crédito, notificações e mais opções de investimento.

Expandir Testes: Aumentar a cobertura de testes automatizados.

---
## Autores do projeto
Lara Dias(https://github.com/Lara-Dias)
Sarah Cristina(https://github.com/SarahCristina00)
Wilian Santos(https://github.com/Wilian2012)

GitHub: seu-usuario
