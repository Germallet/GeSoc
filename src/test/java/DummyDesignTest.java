import org.junit.*;

public class DummyDesignTest {
    @Test
    public void testIntegrante1(){
        DummyDesign unDummy = new DummyDesign();
        Assert.assertEquals(unDummy.integrante1(),1);
    }
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
    @Test
    public void testIntegrante4(){
        DummyDesign unDummy = new DummyDesign();
        Assert.assertEquals(unDummy.integrante4(), 4);
    }
    @Test
    public void testIntegrante5(){
        DummyDesign unDummy = new DummyDesign();
        Assert.assertEquals(unDummy.integrante5(), 5);
    }
}
