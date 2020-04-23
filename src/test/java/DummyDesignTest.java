import org.junit.Assert;
import static org.junit.jupiter.api.Assertions.*;

class DummyDesignTest {

    public void testIntegrante2(){
        DummyDesign unDummy = new DummyDesign();
        Assert.assertEquals(unDummy.integrante2(),2);
    }
}
