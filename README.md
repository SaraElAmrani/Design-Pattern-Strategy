### **Definition – Design Patterns**

A **Design Pattern** describes both:
· A problem that occurs very frequently in a given context,
· And the architecture of the solution to that problem,
in such a way that the solution can be reused thousands of times.

Design patterns successfully describe **recurring types of solutions** to **common problems** in specific types of situations.

---

Design patterns provide:
· Documentation of **proven design experience**,
· Identification and specification of **abstractions** that go beyond simple classes and instances,
· A **common vocabulary** and better understanding of design principles,
· A **means of software documentation**,
· And **assistance in building complex and heterogeneous software** that meets specific requirements.

---

### **Strategy Pattern**

**Category:** Behavioral

**Objectives:**
· Define a family of algorithms, encapsulate each one, and make them interchangeable, while ensuring that each algorithm can evolve independently from the clients that use it.
The **Strategy Pattern** is a **behavioral design pattern** that allows you to define a family of algorithms, encapsulate each one, and make them interchangeable.  

**Reason for use:**
· An object must be able to dynamically change part of its algorithm.

**Result:**
· The Design Pattern allows the isolation of algorithms that belong to the same family of algorithms.

---

#  Design Pattern – Strategy (Java Example)

##  Overview
This project demonstrates the **Strategy Design Pattern** in Java.  

---

##  Concept

- **Problem:**  
  Sometimes, an object needs to perform an operation, but the way it performs it (the *algorithm*) can change dynamically at runtime.

- **Solution (Strategy Pattern):**  
  The pattern separates the algorithm from the object using it.  
  Instead of hardcoding multiple algorithms inside one class, each algorithm is encapsulated in its **own class** that implements a common interface called `Strategy`.

---

##  Project Structure

```
Design-Pattern-Strategy/
│
├── .idea/                          # IntelliJ IDEA configuration files
├── src/
│   └── main/
│       └── java/
│           └── ma/elamrani/
│               ├── Context.java
│               ├── DefaultStrategyImpl.java
│               ├── Strategy.java
│               ├── StrategyImpl1.java
│               ├── StrategyImpl2.java
│               ├── StrategyImpl3.java
│               └── Main.java
│
└── resources/                      # (optional) configuration or text resources
```

---

##  **Classes Description**

###  `Strategy.java`

**Interface Strategy**
Defines a common interface for all strategies.
Every concrete strategy will implement this interface and provide its own version of the `operation()` method.

```java
public interface Strategy {
    void operation();
}
```

---

### 🟩 `StrategyImpl1.java`, `StrategyImpl2.java`, `StrategyImpl3.java`

**Concrete Strategies**
Each class implements the `Strategy` interface and provides a specific behavior.

Example:

```java
public class StrategyImpl1 implements Strategy {
    @Override
    public void operation() {
        System.out.println("Executing Strategy 1...");
    }
}
```

These can represent different algorithms (for example, different discount strategies, payment methods, or sorting algorithms).

---

### 🟧 `DefaultStrategyImpl.java`

Provides a **default strategy** used by the `Context` when no specific strategy has been set.

---

### 🟪 `Context.java`

**Context class**
Holds a reference to a `Strategy` object.
It doesn’t know the concrete class of the strategy—it only uses the interface.

```java
public class Context {
    private Strategy strategy = new DefaultStrategyImpl();

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void effectuerOperation() {
        strategy.operation();
    }
}
```

This allows the strategy (algorithm) to be **changed at runtime**.

---

### 🟥 `Main.java`

**Entry point of the program**

This class demonstrates the **dynamic selection** of strategies using user input.

```java
public class Main {
    public static void main(String[] args) throws Exception {
        Context context = new Context();
        Scanner scanner = new Scanner(System.in);
        Map<String, Strategy> strategyMap = new HashMap<>();
        Strategy strategy;

        while (true) {
            System.out.print("Quelle Strategie ? : ");
            String str = scanner.nextLine();
            strategy = strategyMap.get(str);

            if (strategy == null) {
                System.out.println("Creation d'un nouvel objet StrategyImpl" + str);
                strategy = (Strategy) Class.forName("ma.elamrani.StrategyImpl" + str).newInstance();
                strategyMap.put(str, strategy);
            }

            context.setStrategy(strategy);
            context.effectuerOperation();
        }
    }
}
```

#### 💡 Explanation:

* `Scanner` → reads user input.
* `Map<String, Strategy>` → acts as a **cache** to store already created strategy objects (avoiding multiple instantiations).
* `Class.forName()` → loads the class dynamically based on user input.
* `context.setStrategy(strategy)` → changes the algorithm dynamically.
* `context.effectuerOperation()` → executes the chosen strategy.

---

## 🧩 **How It Works (Example)**

**When you run `Main.java`:**

```
Quelle Strategie ? : 1
Creation d'un nouvel objet StrategyImpl1
Executing Strategy 1...

Quelle Strategie ? : 2
Creation d'un nouvel objet StrategyImpl2
Executing Strategy 2...

Quelle Strategie ? : 2
Executing Strategy 2...   (no new object created — reused from cache)
```

---

## ⚙️ **Advantages**

✅ Promotes **open/closed principle** (easy to add new strategies without modifying existing code).
✅ Makes algorithms **interchangeable**.
✅ Simplifies testing and maintenance.
✅ Supports **dynamic behavior change** at runtime.

---

## 🧾 **README Summary**

| Component         | Description                                  |
| ----------------- | -------------------------------------------- |
| **Strategy**      | Common interface for all algorithms          |
| **StrategyImplX** | Different algorithm implementations          |
| **Context**       | Uses a strategy to perform an operation      |
| **Main**          | Demonstrates runtime strategy switching      |
| **Map cache**     | Avoids re-creating strategies multiple times |

---

## 🚀 **Run the Project**

1. Open the project in IntelliJ IDEA or any Java IDE.
2. Make sure the package name is `ma.elamrani`.
3. Run `Main.java`.
4. Enter a number (1, 2, or 3) when prompted to choose a strategy.

---

Would you like me to generate a **markdown-formatted README.md file** (ready to put in your GitHub project) with this same content?

