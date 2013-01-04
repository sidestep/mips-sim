package mips;

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
		touch(index);
		data[index] = value;
	}
	
	private void touch(int index) {
		if(changedFields.contains(index)) {
			changedFields.remove(changedFields.indexOf(index));
		}
		changedFields.add(0, index);
	}
	
	public byte[] getBytes() {
		return data.clone();
	}
	
	public List<Integer> getChangedIndices() {
		return new ArrayList<Integer>(changedFields);
	}

}
