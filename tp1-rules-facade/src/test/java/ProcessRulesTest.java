import app.Dashboard;
import app.RulesValidatorApp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProcessRulesTest {

    Dashboard rulesEngine;

    @Before
    public void setUp(){
        this.rulesEngine = new Dashboard();
        String defineRule = "[[\"define-counter\",\"string-count\",[],[\"=\",[\"current\",\"name\"],\"John\"]]]";
        rulesEngine.addRules(defineRule);
    }

    @Test
    public void processSimpleRulesTest() {
        String state;

        String data0 = "{\"name\":\"John\",\"age\":30,\"sender\":true}";
        String data1 = "{\"name\":\"Dan\",\"age\":30,\"important\":true}";
        String data2 = "{\"name\":\"John\",\"age\":30,\"spam\":true}";

        state = this.rulesEngine.processData(data0);

        String expectedState = "{\"type\":\"counter\",\"name\":\"string-count\",\"params\":[],\"condition\":[\"=\",[\"current\",\"name\"],\"John\"],\"truth-table\":[{\"key\":[],\"value\":1}],\"step\":1}";
        Assert.assertTrue(state.contains(expectedState));

        state = this.rulesEngine.processData(data1);

        expectedState = "{\"type\":\"counter\",\"name\":\"string-count\",\"params\":[],\"condition\":[\"=\",[\"current\",\"name\"],\"John\"],\"truth-table\":[{\"key\":[],\"value\":1}],\"step\":1}";
        Assert.assertTrue(state.contains(expectedState));

        state = this.rulesEngine.processData(data2);

        expectedState = "{\"type\":\"counter\",\"name\":\"string-count\",\"params\":[],\"condition\":[\"=\",[\"current\",\"name\"],\"John\"],\"truth-table\":[{\"key\":[],\"value\":2}],\"step\":1}";
        Assert.assertTrue(state.contains(expectedState));

    }

    @Test
    public void processSignalTest() {
        addAgeSignal();

        String data0 = "{\"name\":\"John\",\"age\":10,\"sender\":true}";
        sendAndCompare(data0, "[{\"current-age\":10}]");

        String data1 = "{\"name\":\"John\",\"age\":20,\"sender\":true}";
        sendAndCompare(data1, "[{\"current-age\":20}]");

        String data2 = "{\"name\":\"John\",\"age\":30,\"sender\":true}";
        sendAndCompare(data2, "[{\"current-age\":30}]");

    }

    private void addAgeSignal() {
        String signal = "[[\"define-signal\",{\"current-age\": [\"current\",\"age\"]},true]]";
        rulesEngine.addRules(signal);
    }

    private void sendAndCompare(String data, String expectedSignal) {
        rulesEngine.processData(data);
        String signals = rulesEngine.getSignals();
        Assert.assertEquals(signals, expectedSignal);
    }


}
