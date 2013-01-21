package mips;

public class ProgramCounter {
	private int value;
	public ProgramCounter() {
		value = 0;
	}

	public void set(int value) {
		assert value%4 == 0;
		this.value = value;
	}

	public int get() {
		return value;
	}

	public void reset() {
		value = 0;
	}
}
