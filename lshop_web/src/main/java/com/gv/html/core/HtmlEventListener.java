package com.gv.html.core;

public interface HtmlEventListener {
	void onAddComsEvent(int num);
	void onAddProdEvent(int num);
	void onAddTaskEvent(int num);
	void onComsExitEvent();
	void onProdExitEvent();
	void onComsTaskEvent();
	void onEnginStart();
	void onEngineStop();
}
