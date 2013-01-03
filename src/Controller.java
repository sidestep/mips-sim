import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import Simulation.Instruction;
import Simulation.Processor;



public class Controller {
	private GUI gui;
	private DefaultListModel instructionModel;
	
	public Controller() {
		gui = new GUI();
		gui.setGUIListener(listener);
		
		instructionModel = new DefaultListModel();
		gui.setInstructionListModel(instructionModel);
	}
	
	private GUI.GUIListener listener = new GUI.GUIListener() {
		
		@Override
		public void onStop() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onStep() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onRun() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onReset() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onLoad(String filename) {
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
					Instruction instruction = new Instruction(line);
					instructions.add(instruction);
					instructionModel.addElement(instruction);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		//	TODO new Processor().setInstructionSet(instructions);
		}
		
		@Override
		public void onHex() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onDec() {
			// TODO Auto-generated method stub
			
		}
	};
	
	
	
	
	
	public static void main(String[] args) {
		new Controller();
	}
}
