package com.clinet.frame;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.JSValue;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

public class RM extends JPanel {

	/**
	 * Create the panel.
	 */
	
	public static final String url = "http://lbsyun.baidu.com/jsdemo.htm#j1_3";
	public RM() {
		setLayout(new BorderLayout(0, 0));		
		Browser browser = new Browser();  
        BrowserView browserView = new BrowserView(browser);
        browser.loadURL(url);
        browser.addLoadListener(new LoadAdapter() {  
            @Override  
            public void onFinishLoadingFrame(FinishLoadingEvent event) {
                JSValue window = browser.executeJavaScriptAndReturnValue("window");
                JSValue a = window.asObject().getProperty("a");
                a.asFunction().invokeAsync(window.asObject());
            }  
        }); 
		add(browserView,BorderLayout.CENTER);
	}

}
