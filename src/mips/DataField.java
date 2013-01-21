package mips;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract data field that keeps track of changed values
 */
public abstract class DataField {
	private int[] data;
	private List<Integer> changedFields;

	public DataField(int size) {
		data = new int[size];
		reset();
	}

	public void reset() {
		changedFields = new ArrayList<Integer>();
		for(int i = 0; i < data.length; i++) {
			data[i] = 0;
		}
	}

	protected int get(int index) {
		return data[index];
	}

	protected void set(int index, int value) {
		touch(index);
		data[index] = value;
	}

	private void touch(int index) {
		if(changedFields.contains(index)) {
			changedFields.remove(changedFields.indexOf(index));
		}
		changedFields.add(0, index);
	}

	public int[] getRawData() {
		return data.clone();
	}

	public List<Integer> getChangedIndices() {
		return new ArrayList<Integer>(changedFields);
	}

}
