import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;

/**
 * A gui for displaying instructions, register values and memory values
 * as well as providing means to load instructions and advance the simulation
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class GUI {
	private JFrame frame;
	private JCheckBox hexBox;
	private GUIListener listener;
	private String filename;
	private JList instructionList;
	private JList registerList;
	private JList memoryList;
	private JTextField pcPane;


	public GUI() {
		Font mono = new Font("Monospaced", Font.PLAIN, 13);

		instructionList = new JList();
		instructionList.setFont(mono);
		JScrollPane instructionPane = new JScrollPane(instructionList);
		JPanel leftPanel = new JPanel(new BorderLayout());
		leftPanel.add(instructionPane);

		registerList = new JList();
		registerList.setFont(mono);
		memoryList = new JList();
		memoryList.setFont(mono);
		JScrollPane registerPane = new JScrollPane(registerList);
		JScrollPane dataPane = new JScrollPane(memoryList);
		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.add(dataPane, BorderLayout.EAST);
		rightPanel.add(registerPane, BorderLayout.WEST);

		JLabel loadLabel = new JLabel("Load input file");
		JButton chooseButton = new JButton("Choose");
		JButton loadButton = new JButton("Load");
		JPanel topPanel = new JPanel();
		topPanel.add(loadLabel);
		topPanel.add(chooseButton);
		topPanel.add(loadButton);

		final JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));

		chooseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileChooser.showOpenDialog(frame);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					filename = fileChooser.getSelectedFile().getPath();
				}
			}
		});

		loadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(filename != null){
					listener.onLoad(filename);
				}
			}
		});

		pcPane = new JTextField(7);
		pcPane.setBorder(new EmptyBorder(0, 0, 0, 5));
		pcPane.setEditable(false);
		pcPane.setOpaque(false);

		JButton stepButton = new JButton("Step");
		JButton runButton = new JButton("Run");
		JButton stopButton = new JButton("Stop");
		JButton resetButton = new JButton("Reset");
		JPanel botPanel = new JPanel();
		hexBox = new JCheckBox("Hex");

		botPanel.add(pcPane);
		botPanel.add(stepButton);
		botPanel.add(runButton);
		botPanel.add(stopButton);
		botPanel.add(resetButton);
		botPanel.add(hexBox);

		stepButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listener.onStep();			
			}
		});

		runButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listener.onRun();
			}
		});

		stopButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listener.onStop();	
			}
		});

		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listener.onReset();	
			}
		});

		hexBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(hexBox.isSelected()){
					listener.onHex();
				} else {
					listener.onDec();
				}
			}
		});


		frame = new JFrame("MIPS Simulator");
		frame.add(leftPanel, BorderLayout.CENTER);
		frame.add(rightPanel, BorderLayout.EAST);
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(botPanel, BorderLayout.SOUTH);
		frame.setSize(1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		setPc(0);
	}

	public void setGUIListener(GUIListener listener) {
		this.listener = listener;
	}

	public void setInstructionListModel(ListModel model){
		instructionList.setModel(model);
	}

	public void setRegisterListModel(ListModel model){
		registerList.setModel(model);
	}

	public void setMemoryListModel(ListModel model){
		memoryList.setModel(model);
	}

	public interface GUIListener {
		public void onLoad(String filename);
		public void onStep();
		public void onRun();
		public void onStop();
		public void onReset();
		public void onHex();
		public void onDec();
	}

	public void selectInstruction(int index) {
		instructionList.setSelectedIndex(index);
	}

	public void clearInstructionSelection() {
		instructionList.clearSelection();
	}

	public void setPc(int pc) {
		pcPane.setText("PC: " + pc);
	}
}
