import app.Dashboard;
import app.RulesValidatorApp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddRulesTest {

    Dashboard rulesEngine;

    @Before
    public void setUp() {
        this.rulesEngine = new Dashboard();
    }

    @Test
    public void defineSimpleRulesTest() {
        String defineRule = "[[\"define-counter\",\"string-count\",[],[\"=\",[\"current\",\"name\"],\"John\"]]]";
        String defineSignal = "[[\"define-signal\",{\"spam-fraction\":[\"\\/\",[\"counter-value\",\"spam-count\",[]],[\"counter-value\",\"email-count\",[]]]},true]]";

        String state = this.rulesEngine.addRules(defineRule);

        String expectedState = "{\"type\":\"counter\",\"name\":\"string-count\",\"params\":[],\"condition\":[\"=\",[\"current\",\"name\"],\"John\"],\"truth-table\":[],\"step\":1}";
        Assert.assertTrue(state.contains(expectedState));

        state = this.rulesEngine.addRules(defineSignal);

        String expectedStateSignal = "{\"type\":\"counter\",\"name\":\"string-count\",\"params\":[],\"condition\":[\"=\",[\"current\",\"name\"],\"John\"],\"truth-table\":[],\"step\":1}";
        String expectedStateCounter = "{\"type\":\"signal\",\"name\":\"spam-fraction\",\"operation\":[\"\\/\",[\"counter-value\",\"spam-count\",[]],[\"counter-value\",\"email-count\",[]]],\"condition\":true}";

        Assert.assertTrue(state.contains(expectedStateSignal));
        Assert.assertTrue(state.contains(expectedStateCounter));
    }

    @Test
    public void defineManyRulesTest(){
        String manyRules = "[[\"define-counter\",\"string-count\",[],[\"=\",[\"current\",\"name\"],\"John\"]],[\"define-signal\",{\"spam-fraction\":[\"\\/\",[\"counter-value\",\"spam-count\",[]],[\"counter-value\",\"email-count\",[]]]},true]]";
        String state = this.rulesEngine.addRules(manyRules);

        String expectedStateSignal = "{\"type\":\"counter\",\"name\":\"string-count\",\"params\":[],\"condition\":[\"=\",[\"current\",\"name\"],\"John\"],\"truth-table\":[],\"step\":1}";
        String expectedStateCounter = "{\"type\":\"signal\",\"name\":\"spam-fraction\",\"operation\":[\"\\/\",[\"counter-value\",\"spam-count\",[]],[\"counter-value\",\"email-count\",[]]],\"condition\":true}";

        Assert.assertTrue(state.contains(expectedStateSignal));
        Assert.assertTrue(state.contains(expectedStateCounter));
    }
}
