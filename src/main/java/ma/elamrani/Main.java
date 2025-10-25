package ma.elamrani;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Context context = new Context();
       /* context.effectuerOperation();
        context.setStrategy(new StrategyImpl1());
        context.effectuerOperation();
        context.setStrategy(new StrategyImpl2());
        context.effectuerOperation();
        context.setStrategy(new StrategyImpl3());
        context.effectuerOperation();    */

        Scanner scanner = new Scanner(System.in);
        Map<String, Strategy> strategyMap = new HashMap<>(); // Cash Memoire
        Strategy strategy;

        while(true) {
            System.out.print("Quelle Strategie ? : ");
            String str = scanner.nextLine();
            strategy = strategyMap.get(str);
            if(strategy == null){
                System.out.println(" Creation d un nouvel objet StrategyImpl"+str);
                strategy = (Strategy) Class.forName("ma.elamrani.StrategyImpl"+str).newInstance(); // ici on utilise constructeur par defaut
                strategyMap.put(str, strategy);
            }
            context.setStrategy(strategy);
            context.effectuerOperation();
        }

    }
}