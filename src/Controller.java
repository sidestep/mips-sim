

public class Controller {
	private GUI gui;
	
	public Controller() {
		gui = new GUI();
		gui.setGUIListener(listener);
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
			// TODO Auto-generated method stub
			
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
