package es.us.isa.androidtesting;

import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;

import es.us.isa.androidtesting.TestCase;
import org.junit.Test;

import es.us.isa.androidtesting.inagraph.INAGraph;
import es.us.isa.androidtesting.inagraph.INAGraphBuilder;
import es.us.isa.androidtesting.algorithms.RandomSearch;
import es.us.isa.androidtesting.objectivefunctions.ApplicationCrashObjectiveFunction;
import es.us.isa.androidtesting.objectivefunctions.ObjectiveFunction;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * Created by japar on 17/03/2018.
 */

public class RandomSearchForAbruptShutdown {

    @Test
    public void test1() throws UiObjectNotFoundException, InterruptedException {
        UiDevice mDevice;
        mDevice = UiDevice.getInstance(getInstrumentation());
        INAGraph graph= INAGraphBuilder.getInstance().build(mDevice,"com.example.testingandroid2");
        ObjectiveFunction abruptShutdown=new ApplicationCrashObjectiveFunction();
        RandomSearch algorithm=new RandomSearch(abruptShutdown,100,3);
        TestCase testCase=algorithm.run(graph,"com.example.testingandroid2");
        System.out.println("Test case found: "+testCase);
        System.out.println("Runnig it...");
        testCase.executeBefore();
        testCase.executeTest();
        testCase.executeAfter();
        System.out.println("Done!");
    }
}
