package mips;

public class RegisterFile extends DataField {
	public RegisterFile() {
		super(32);
	}

	@Override
	public byte get(int index) {
		if(index == 0) {
			return 0; // $zero register
		}
		return super.get(index);
	}

}
