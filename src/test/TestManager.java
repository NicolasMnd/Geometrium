package test;

public class TestManager {

    public static void runTests() {
        TestBase[] tests = new TestBase[] {

        };

        for(TestBase t : tests)
            t.test();
    }

}
