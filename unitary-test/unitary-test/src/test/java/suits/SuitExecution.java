package suits;

import br.ce.wcaquino.servicos.CalculatorServiceTest;
import br.ce.wcaquino.servicos.LocacaoServiceTest;
import br.ce.wcaquino.servicos.OrderTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalculatorServiceTest.class,
        LocacaoServiceTest.class,
        OrderTest.class
})
public class SuitExecution {
        @BeforeClass
        public static void before(){
            System.out.println("Before Class");
        }

        @AfterClass
        public static void after(){
            System.out.println("After Class");
        }
}
