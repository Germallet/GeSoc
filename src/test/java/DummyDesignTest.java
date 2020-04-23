import org.junit.*;

public class DummyDesignTest {
    @Test
    public void testIntegrante2(){
        DummyDesign unDummy = new DummyDesign();
        Assert.assertEquals(unDummy.integrante2(),2);
    }
    @Test
    public void testIntegrante3(){
        DummyDesign unDummy = new DummyDesign();
        Assert.assertEquals(unDummy.integrante3(),3);
    }
}
