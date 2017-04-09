package scripting;

import java.util.ArrayList;
import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptingExample {
	
	private List<TowerExample> myTowers = new ArrayList<TowerExample>();
	private ScriptEngine engine = new ScriptEngineManager().getEngineByName("groovy");
	
	public ScriptingExample() {
		myTowers.add(new TowerExample("Tower1", 2, 50, 5));
		engine.put("myTowers", myTowers);
	}
	
	public void inputScript(String script) {
		System.out.println(script);
		try {
			engine.eval(script);
		} catch (ScriptException ex) {
			System.out.println(ex);
		}
	}
	
	public void getTowersFromGroovy() {
		System.out.println("Fired");
		engine.get("myTowers");
		for (TowerExample te : myTowers) {
			System.out.println(te);
		}
	}
	
	public void useExample () {
        // make a separate engine each time to avoid accumulating values
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("groovy");
        // some example Java objects
        List<TowerExample> items = null;
        //tower with firing rate of 2 times per second, range of 50 pixels, and damage of -5 per hit
        TowerExample te = new TowerExample("Tower1", 2, 50, 5);

        System.out.println("Before");
        System.out.println(te.use());
        System.out.println(items);
        System.out.println();

        // setup Groovy and make it aware of Java objects
        engine.put("Tower1", te);
        engine.put("items", items);

        // evaluate in Groovy
        System.out.println("During");
        try {
            engine.eval("firingRate = Tower1.getFiringRate()");
            engine.eval("println items?.size()");
            engine.eval("import scripting.TowerExample; items = [ Tower1, new TowerExample('Tower2', 3, 100, 10), new TowerExample('Tower3', 1, 50, 25) ]");
            engine.eval("println items?.size()");
            engine.eval("for (te in items) { te.getFiringRate() }");
            engine.eval("items.each { it.getDamage() }");
        } catch(ScriptException ex) {
            System.out.println(ex);
        }
        System.out.println();

        // get created values back out of Groovy (not necessary for those just changed by method calls)
        String use = (String)engine.get("use");
        items = (List<TowerExample>)engine.get("items");

        System.out.println("After");
        System.out.println(use);
        System.out.println(items);
        System.out.println(te.getDamage());
        System.out.println(items.get(1).getRange());
        System.out.println();
    }
	
	public static void main (String[] args) {
        ScriptingExample se = new ScriptingExample();
        se.useExample();
    }

	//import scripting.TowerExample;
	//myTowers.add(new TowerExample('Tower2', 3, 100, 10));
	//myTowers.add(new TowerExample('Tower3', 1, 50, 25));
	
}
