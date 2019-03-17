/*
 *  
 */
package weaver.aop;

import java.lang.instrument.Instrumentation;
import weaver.aop.Redefiner;

public class WStubPreMain {
    private static Instrumentation instLocal = null;
    private static Redefiner redefiner = null;

    public static void premain(String string, Instrumentation instrumentation) {
        instLocal = instrumentation;
    }

    public static void doLoadConfig() {
        if (null == redefiner) {
            redefiner = new Redefiner(instLocal);
        }
        redefiner.loadConfig();
    }
}