# 🎮 Aura de Chumbo RPG

Um RPG de texto desenvolvido em Java para demonstrar conceitos fundamentais de Programação Orientada a Objetos (POO).

## 🚀 Como Rodar o Jogo

### Pré-requisitos
- Java JDK 8 ou superior instalado
- Terminal/Prompt de Comando

### Passos para Execução

1. **Clone o repositório (se ainda não fez)**
   ```bash
   git clone https://github.com/riessz/AuraDeChumbo.git
   cd auraDeChumboRPG
   ```

2. **Compile o projeto**
   ```bash
   javac -d bin src/modelo/*.java src/util/*.java src/app/*.java
   ```

3. **Execute o jogo**
   ```bash
   java -cp bin app.AppInterativo
   ```

4. **Comece a jogar!**
   - Crie seu jogador
   - Escolha entre 3 classes: Guerreiro, Mago ou Arqueiro
   - Batalhe contra monstros
   - Ganhe XP e suba de nível
   - Aprenda novas habilidades

## 🎯 Como Jogar

### Menu Principal
1. **Criar Personagem** - Escolha uma classe e crie seu herói
2. **Listar Personagens** - Veja todos os seus personagens criados
3. **Ensinar Habilidade** - Aprenda habilidades específicas da sua classe
4. **Iniciar Batalha** - Enfrente monstros em combate por turnos
5. **Estatísticas** - Veja informações do jogador
6. **Sair** - Encerra o jogo

### Classes Disponíveis

#### ⚔️ Guerreiro
- **Estilo:** Tank corpo a corpo
- **Vida:** 120 | **Ataque:** 25 | **Defesa:** 15
- **Mecânica:** Sistema de Fúria
  - Ganha 10 de fúria a cada ataque (máx: 100)
  - Dano extra: +1 por cada 10 de fúria
- **Habilidade Especial:** FÚRIA BERSERK
  - Custo: 30 de fúria
  - Efeito: Ataque triplo devastador

#### 🔮 Mago
- **Estilo:** Vidro canhão mágico
- **Vida:** 80 | **Ataque:** 20 | **Defesa:** 8
- **Mecânica:** Sistema de Mana
  - Inicia com 100 de mana
  - Regenera 5 de mana por ataque
- **Habilidade Especial:** BOLA DE FOGO
  - Custo: 40 de mana
  - Efeito: Dano mágico duplo + 10 (ignora defesa)

#### 🏹 Arqueiro
- **Estilo:** Atirador de longo alcance
- **Vida:** 90 | **Ataque:** 22 | **Defesa:** 10
- **Mecânica:** Sistema de Precisão
  - Inicia com 100 de precisão
  - Crítico quando precisão > 80 (1.5x dano)
  - Perde 10 de precisão por ataque
- **Habilidade Especial:** TIRO PERFURADOR
  - Efeito: Crítico garantido (2.5x dano)

### Sistema de Combate
- **Turnos:** Você ataca primeiro, depois o monstro
- **Opções:**
  1. Atacar - Ataque básico
  2. Habilidade Especial - Use sua habilidade única
  3. Curar - Recupera 30 HP

### Sistema de Progressão
- **XP por Vitória:** 50 + (nível do monstro × 20)
- **XP para Upar:** nível × 100
- **Bônus ao Subir de Nível:**
  - +10 Vida Máxima
  - +2 Ataque
  - +1 Defesa
  - Cura completa

## 📚 Conceitos de POO Aplicados

### 1. **Classes e Objetos**
Cada elemento do jogo é representado por uma classe:
- `Personagem` - Base para heróis jogáveis
- `Monstro` - Inimigos que você enfrenta
- `Jogador` - Representa o usuário
- `Habilidade` - Skills que podem ser aprendidas

```java
Guerreiro heroi = new Guerreiro("Conan");  // Criando um objeto
```

### 2. **Encapsulamento**
Atributos são privados e acessados via getters/setters:
```java
private int vida;
private int ataque;

public int getVida() { return vida; }
```

**Benefício:** Protege os dados e controla o acesso.

### 3. **Herança**
Classes especializadas herdam de uma classe base:
```java
public class Guerreiro extends Personagem { }
public class Mago extends Personagem { }
public class Arqueiro extends Personagem { }
```

**Benefício:** Reutilização de código - todas as classes compartilham atributos e métodos de `Personagem`.

### 4. **Polimorfismo**
Mesmo método, comportamentos diferentes:
```java
// Cada classe implementa atacar() de forma única
@Override
public void atacar(Atacavel alvo) {
    // Guerreiro ganha fúria
    // Mago regenera mana
    // Arqueiro perde precisão
}
```

**Benefício:** Flexibilidade - cada classe tem seu próprio comportamento.

### 5. **Abstração**
Classe `Personagem` é abstrata - não pode ser instanciada diretamente:
```java
public abstract class Personagem {
    public abstract void usarHabilidadeEspecial();
}
```

**Benefício:** Force as subclasses a implementarem métodos específicos.

### 6. **Interfaces**
`Atacavel` define um contrato para entidades que podem combater:
```java
public interface Atacavel {
    void atacar(Atacavel alvo);
    void receberDano(int dano);
    boolean estaVivo();
    String getNome();
}
```

**Benefício:** Garante que `Personagem` e `Monstro` podem interagir em batalha.

### 7. **Composição**
Personagem contém uma lista de Habilidades:
```java
private List<Habilidade> habilidades;
```

**Benefício:** Relacionamento "tem-um" - Personagem tem habilidades.

### 8. **Agregação**
Jogador e Personagens têm relacionamento bidirecional:
```java
// Jogador conhece seus personagens
private List<Personagem> personagens;

// Personagem conhece seu jogador
private Jogador jogador;
```

### 9. **Enum**
Tipo seguro para classes de personagens:
```java
public enum TipoPersonagem {
    GUERREIRO, MAGO, ARQUEIRO
}
```

**Benefício:** Evita erros de digitação e limita opções válidas.

### 10. **Sobrecarga de Métodos**
Múltiplas versões do mesmo método:
```java
public abstract void usarHabilidadeEspecial();
public abstract void usarHabilidadeEspecial(Atacavel alvo);
```

### 11. **Tratamento de Exceções**
Validação de entrada do usuário:
```java
try {
    opcao = scanner.nextInt();
} catch (InputMismatchException e) {
    System.out.println("❌ Digite um número válido!");
}
```

## 🏗️ Estrutura do Projeto

```
auraDeChumboRPG/
├── src/
│   ├── modelo/              # Classes do domínio
│   │   ├── Personagem.java      # Classe abstrata base
│   │   ├── Guerreiro.java       # Classe concreta
│   │   ├── Mago.java            # Classe concreta
│   │   ├── Arqueiro.java        # Classe concreta
│   │   ├── Monstro.java         # Inimigos
│   │   ├── Jogador.java         # Usuário
│   │   ├── Habilidade.java      # Skills
│   │   ├── Atacavel.java        # Interface de combate
│   │   └── TipoPersonagem.java  # Enum de classes
│   ├── util/                # Utilitários
│   │   └── ComparadorNivel.java # Comparator para ordenação
│   └── app/                 # Aplicação
│       └── AppInterativo.java   # Main + menu
└── README.md
```

## 🎓 Princípios SOLID Aplicados

### S - Single Responsibility Principle
Cada classe tem uma responsabilidade única:
- `Personagem` - gerencia atributos e combate
- `Jogador` - gerencia personagens
- `Habilidade` - representa uma skill

### O - Open/Closed Principle
Classes abertas para extensão, fechadas para modificação:
- Fácil adicionar novas classes (ex: `Paladino`, `Ladino`)
- Não precisa modificar código existente

### L - Liskov Substitution Principle
Subclasses podem substituir a classe base:
```java
Personagem p = new Guerreiro("Conan");  // Funciona!
p.atacar(monstro);
```

### I - Interface Segregation Principle
Interface `Atacavel` é específica e enxuta.

### D - Dependency Inversion Principle
Código depende de abstrações (`Personagem`, `Atacavel`), não de implementações concretas.

## 🎨 Recursos do Jogo

- ✅ Sistema de batalha por turnos
- ✅ 3 classes jogáveis únicas
- ✅ Sistema de experiência e level up
- ✅ Habilidades específicas por classe
- ✅ Validação de duplicação de habilidades
- ✅ Tratamento de erros de entrada
- ✅ Progressão de atributos
- ✅ Mecânicas únicas por classe (Fúria, Mana, Precisão)

## 👨‍💻 Desenvolvido por

Projeto educacional para demonstração de conceitos de POO.

**Repositório:** [AuraDeChumbo](https://github.com/riessz/AuraDeChumbo)

---

**Divirta-se jogando e aprendendo POO! 🎮📚**
