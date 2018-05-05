package com.lshop.common.thread.impl;

import com.gv.core.email.IMail;
import com.gv.core.email.MailSender;
import com.lshop.common.thread.MailCreator;




public class MailCreatorImpl implements MailCreator {
	
	public MailSender  sender;
	
    public void create(final IMail mail ){
    	new Thread(){
    		@Override
    		public void run() {
    			mail.setSender( sender );
    	    	mail.doSend();
    		}
    		
    	}.start();
    	
    }

	public MailSender getSender() {
		return sender;
	}

	public void setSender(MailSender sender) {
		this.sender = sender;
	}
}
