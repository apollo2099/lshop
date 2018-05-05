package com.gv.html.core;


/**
 * 通知对象
 * @author caicl
 *
 */
public class HtmlEventNotify {
	/**
	 * 侦听对象
	 */
	private HtmlEventListener listener;

	public HtmlEventNotify(HtmlEventListener listener) {
		super();
		this.listener = listener;
	}
	public void fireEvent(HtmlEventType eventType) {
		fireEvent(eventType, 1);
	}
	public void fireEvent(HtmlEventType event, int num) {
		switch (event) {
		case ENGINSTART:
			listener.onEnginStart();
			break;
		case ENGINEXIT:
			listener.onEngineStop();
			break;
		case ADDPROD:
			listener.onAddProdEvent(num);
			break;
		case ADDCOMS:
			listener.onAddComsEvent(num);
			break;
		case ADDTASK:
			listener.onAddTaskEvent(num);
			break;
		case COMSEXIT:
			listener.onComsExitEvent();
			break;
		case PRODEXIT:
			listener.onProdExitEvent();
			break;
		case COMSTASK:
			listener.onComsTaskEvent();
			break;
		}
	}
}
