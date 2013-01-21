package mips;

public class ProgramCounter {
	private int value;
	public ProgramCounter() {
		value = 0;
	}
	
	public void set(int value) {
		this.value = value;
	}
	
	public int get() {
		return value;
	}
	
	public void reset() {
		value = 0;
	}
}
