
import com.mycompany.calculyator2.Calc;
import org.junit.Assert;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */

public class TestCalc {
 @Test
    public  void eval (){
     String expr= "1+1";     
            
     int result = 2;
    Assert.assertEquals(Calc.eval(expr), result);
    
    }

    
}
