package com.clinet.frame;

import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class ScriptEngineTest {
	public static void main(String[] args) throws Exception {   
		ScriptEngineManager manager = new ScriptEngineManager();   
		ScriptEngine engine = manager.getEngineByName("javascript");     
		String jsFileName = "E:\\eclipse\\workspace2\\Test1\\WebContent\\aa.js";   // ��ȡjs�ļ�   
		FileReader reader = new FileReader(jsFileName);   // ִ��ָ���ű�   
		engine.eval(reader);   
		if(engine instanceof Invocable) {    
		Invocable invoke = (Invocable)engine;    // ����merge��������������������    
		// c = merge(2, 3);    
		Double c = (Double)invoke.invokeFunction("merge", 2, 3);    
		System.out.println("c = " + c);   
		}   
		reader.close();  
	}
}

