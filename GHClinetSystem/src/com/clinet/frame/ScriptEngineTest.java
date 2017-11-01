package com.clinet.frame;

import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class ScriptEngineTest {
	public static void main(String[] args) throws Exception {   
		ScriptEngineManager manager = new ScriptEngineManager();   
		ScriptEngine engine = manager.getEngineByName("javascript");     
		String jsFileName = "E:\\eclipse\\workspace2\\Test1\\WebContent\\aa.js";   // 读取js文件   
		FileReader reader = new FileReader(jsFileName);   // 执行指定脚本   
		engine.eval(reader);   
		if(engine instanceof Invocable) {    
		Invocable invoke = (Invocable)engine;    // 调用merge方法，并传入两个参数    
		// c = merge(2, 3);    
		Double c = (Double)invoke.invokeFunction("merge", 2, 3);    
		System.out.println("c = " + c);   
		}   
		reader.close();  
	}
}

