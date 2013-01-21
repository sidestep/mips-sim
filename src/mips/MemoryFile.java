package mips;

/**
 * The processor memory.
 */
public class MemoryFile extends DataField {
	public MemoryFile() {
		super(1000);
	}

	public int cycle(int addr, int write_data, boolean memRead,
			boolean memWrite) {
		if(memWrite) {
			set(addr, write_data);
		}

		if(memRead) {
			return get(addr);
		}

		return 0;
	}
}
