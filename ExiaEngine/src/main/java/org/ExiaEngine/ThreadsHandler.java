package org.ExiaEngine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ThreadsHandler extends Thread {
	
	public static List<ThreadsHandler> gameThreads= new ArrayList<ThreadsHandler>();
	private boolean canRun = true;
	private Object tClass;
	
	public ThreadsHandler(Object e) {
		ThreadsHandler.addThread(this);
		this.settClass(e);
	}

	@Override
	public void run() {
		if(canRun)
			this.launchJob();
		
	}
	
	public void launchJob() {
		
	}
	
	public static void reset() {
		Iterator<ThreadsHandler> iter = ThreadsHandler.gameThreads.iterator();
		while(iter.hasNext()) {
			ThreadsHandler i = iter.next();
			ThreadsHandler.removeThread(i);
		}
		ThreadsHandler.gameThreads = new ArrayList<ThreadsHandler>();
	}
	
	public static void addThread(ThreadsHandler th) {
		ThreadsHandler.gameThreads.add(th);
	}
	
	public static void removeThread(ThreadsHandler th) {
		th.setCanRun(false);
		ThreadsHandler.gameThreads.remove(th);
	}
	
	public static List<ThreadsHandler> getThreads() {
		return ThreadsHandler.gameThreads;
	}

	public boolean isCanRun() {
		return canRun;
	}

	public void setCanRun(boolean canRun) {
		this.canRun = canRun;
	}

	public Object gettClass() {
		return tClass;
	}

	public void settClass(Object tClass) {
		this.tClass = tClass;
	}
	

}
