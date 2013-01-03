package Simulation;

import java.util.ArrayList;
import java.util.List;

public abstract class DataField {
	private byte[] data;
	private List<Integer> changedFields;

	public DataField(int size) {
		data = new byte[size];
		reset();
	}

	public void reset() {
		changedFields = new ArrayList<Integer>();
		for(int i = 0; i < data.length; i++) {
			data[i] = 0;
		}
	}

	public byte get(int index) {
		return data[index];
	}

	protected void set(int index, byte value) {
		changedFields.add(index);
		data[index] = value;
	}


}
