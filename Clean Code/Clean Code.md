# SOLID e Design Patterns

## O que é SOLID

SOLID é um conjunto de 5 princípios que ajudam a escrever código:

- Mais flexível  
- Mais manutenível  
- Mais fácil de evoluir sem quebrar  

Observação:  
“Não se repita (DRY)” não faz parte do SOLID, mas é um princípio complementar importante.

---

## Os 5 princípios

- S → Single Responsibility Principle (SRP)  
- O → Open/Closed Principle (OCP)  
- L → Liskov Substitution Principle (LSP)  
- I → Interface Segregation Principle (ISP)  
- D → Dependency Inversion Principle (DIP)  

---

## 1. Single Responsibility Principle (SRP)

### Ideia
Uma classe deve ter apenas **um motivo para mudar**.

### Problema comum
Misturar responsabilidades diferentes na mesma classe.

### Exemplo errado
```java
class CustomerService {

    public void createCustomer() {
        // cria cliente
    }

    public void updateCustomer() {
        // atualiza cliente
    }

    public void notifyCustomer() {
        // envia email
    }
}
```

### Exemplo correto
```java
class CustomerService {

    public void createCustomer() {
        // cria cliente
    }

    public void updateCustomer() {
        // atualiza cliente
    }
}

class NotificationService {

    public void notifyCustomer() {
        // envia email
    }
}
```

---

## 2. Open/Closed Principle (OCP)

### Ideia
Código deve estar:
- Aberto para extensão  
- Fechado para modificação  

Ou seja, você não altera código existente, você estende.

### Exemplo errado
```java
class DiscountService {

    public double applyDiscount(String type, double value) {
        if (type.equals("VIP")) {
            return value * 0.9;
        } else if (type.equals("NORMAL")) {
            return value * 0.95;
        }
        return value;
    }
}
```

### Exemplo correto
```java
interface DiscountStrategy {
    double apply(double value);
}

class VipDiscount implements DiscountStrategy {
    public double apply(double value) {
        return value * 0.9;
    }
}

class NormalDiscount implements DiscountStrategy {
    public double apply(double value) {
        return value * 0.95;
    }
}

class DiscountService {
    public double applyDiscount(DiscountStrategy strategy, double value) {
        return strategy.apply(value);
    }
}
```

---

## 3. Liskov Substitution Principle (LSP)

### Ideia
Uma classe filha deve poder substituir a classe pai sem quebrar o comportamento.

### Exemplo errado
```java
class Bird {
    public void fly() {
        // voa
    }
}

class Penguin extends Bird {
    public void fly() {
        throw new RuntimeException("Pinguim não voa");
    }
}
```

### Exemplo correto
```java
class Bird {
}

interface FlyingBird {
    void fly();
}

class Sparrow extends Bird implements FlyingBird {
    public void fly() {
        // voa
    }
}

class Penguin extends Bird {
}
```

---

## 4. Interface Segregation Principle (ISP)

### Ideia
Nenhuma classe deve ser forçada a implementar métodos que não usa.

### Exemplo errado
```java
interface Worker {
    void work();
    void eat();
}

class Robot implements Worker {

    public void work() {
        // trabalha
    }

    public void eat() {
        throw new RuntimeException("Robô não come");
    }
}
```

### Exemplo correto
```java
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class Human implements Workable, Eatable {
    public void work() {}
    public void eat() {}
}

class Robot implements Workable {
    public void work() {}
}
```

---

## 5. Dependency Inversion Principle (DIP)

### Ideia
- Módulos de alto nível não devem depender de baixo nível  
- Ambos devem depender de abstrações  

### Exemplo errado
```java
class MySQLDatabase {
    public void save() {}
}

class UserService {
    private MySQLDatabase db = new MySQLDatabase();

    public void saveUser() {
        db.save();
    }
}
```

### Exemplo correto
```java
interface Database {
    void save();
}

class MySQLDatabase implements Database {
    public void save() {}
}

class MongoDatabase implements Database {
    public void save() {}
}

class UserService {
    private Database db;

    public UserService(Database db) {
        this.db = db;
    }

    public void saveUser() {
        db.save();
    }
}
```

---

## Resumo mental rápido

- SRP → uma classe = uma responsabilidade  
- OCP → não altera, estende  
- LSP → filho não pode quebrar o pai  
- ISP → interfaces pequenas e específicas  
- DIP → depende de abstração, não de implementação  
