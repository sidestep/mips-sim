import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;

import mips.Instruction;
import mips.Processor;
import mips.RegisterFile;





public class Controller {
	private GUI gui;
	private Processor processor;
	
	private DefaultListModel instructionModel;
	private DefaultListModel registerModel;
	private DefaultListModel memoryModel;
	
	
	private volatile boolean running = false;
	private boolean hexadecimal = false;
	
	public Controller() {
		gui = new GUI();
		gui.setGUIListener(listener);
		
		processor = new Processor();
		
		instructionModel = new DefaultListModel();
		gui.setInstructionListModel(instructionModel);
		
		registerModel = new DefaultListModel();
		gui.setRegisterListModel(registerModel);
		
		memoryModel = new DefaultListModel();
		gui.setMemoryListModel(memoryModel);
	}
	
	/**
	 * Refresh the interface with the current processor state
	 */
	private void refresh() {
		int instructionIndex = processor.getPc()/4;
		if(instructionIndex >= instructionModel.getSize()) {
			gui.clearInstructionSelection();
		} else {
			gui.selectInstruction(instructionIndex);
		}
		
		registerModel.clear();
		memoryModel.clear();
		
		byte[] registerData = processor.getRegisters();
		List<Integer> changedRegisters = processor.getChangedRegisters();
		for(int index : changedRegisters) {
			String repr = String.format(
					"%s: %s", RegisterFile.name(index), string_value(registerData[index]));
			registerModel.addElement(repr);
		}
		
		byte[] memoryData = processor.getMemory();
		List<Integer> changedMemory = processor.getChangedMemory();
		for(int index : changedMemory) {
			String repr = String.format(
					"%s: %s", string_value((short)index), string_value(memoryData[index]));
			memoryModel.addElement(repr);
		}
		
		
	}
	
	private String string_value(short b) {
		if(hexadecimal) {
			return String.format("0x%x", ((int)b) & 0xff);
		} else {
			return String.format("%d", ((int)b) & 0xff);
		}
	}

	private void refreshLater() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				refresh();
			}
		});
	}
	
	/**
	 * Run the simulation until it ends or the user stops
	 */
	private void run() {
		if(running) {
			return;
		}
		new Thread(){
			public void run() {
				if(running) {
					return;
				}
				running = true;
				while(running && !processor.isDone()) {
					step();
				}
				refreshLater();
			};
		}.run();
	}
	
	/**
	 * Stop automatic running
	 */
	private void stop() {
		running = false;
	}
	
	/**
	 * Step the simulation, effectively moving the simulation forward
	 */
	private synchronized void step() {
		processor.step();
	}
	
	/**
	 * Reset the simulation to initial state
	 */
	private void reset() {
		stop();
		step(); //Block until running stops
		processor.reset();
	}
	
	private void load(String filename) {
		String line;
		BufferedReader reader = null;
		ArrayList<Instruction> instructions = new ArrayList<Instruction>();
		
		instructionModel.clear();
		
		try {
			reader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while((line = reader.readLine()) != null){
				try {
						Instruction instruction = new Instruction(line);
						instructions.add(instruction);
						instructionModel.addElement(instruction);
				} catch (Exception e) {
					System.out.printf("Could not translate \"%s\" into an instruction\n", line);
				}
			}
		} catch (IOException e) {
			System.out.printf("File reading error: %s \n", e.getMessage());
		}
		processor.setInstructionSet(instructions);
		refresh();
	}
	
	private GUI.GUIListener listener = new GUI.GUIListener() {
		
		@Override
		public void onStop() {
			stop();
			refresh();
		}
		
		@Override
		public void onStep() {
			step();
			refresh();
		}
		
		@Override
		public void onRun() {
			run();
		}
		
		@Override
		public void onReset() {
			reset();
			refresh();
		}
		
		@Override
		public void onLoad(String filename) {
			load(filename);
		}
		
		@Override
		public void onHex() {
			hexadecimal = true;
		}
		
		@Override
		public void onDec() {
			hexadecimal = false;
		}
	};
	
	public static void main(String[] args) {
		new Controller();
	}
}
