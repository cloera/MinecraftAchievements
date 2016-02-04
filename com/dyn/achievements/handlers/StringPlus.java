package com.dyn.achievements.handlers;

public class StringPlus {
	private StringPlus parent;
	private String val;

	public StringPlus() {
		this.parent = null;
		this.val = "";
	}

	public StringPlus(StringPlus p, String v) {
		this.parent = p;
		this.val = v;
	}

	public boolean hasParent() {
		return this.parent != null;
	}

	public StringPlus getParent() {
		return this.parent;
	}
	
	@Override
	public String toString(){
		return this.val;
	}

}