import java.awt.BorderLayout;
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


public class GUI {
	private JFrame frame;
	private JCheckBox hexBox;
	
	
	public GUI() {
		
		JList instructionList = new JList();
		JScrollPane instructionPane = new JScrollPane(instructionList);
		JPanel leftPanel = new JPanel(new BorderLayout());
		leftPanel.add(instructionPane);
		
		JList registerList = new JList();
		JList dataList = new JList();
		JScrollPane registerPane = new JScrollPane(registerList);
		JScrollPane dataPane = new JScrollPane(dataList);
		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.add(dataPane, BorderLayout.WEST);
		rightPanel.add(registerPane, BorderLayout.EAST);
		
		JLabel loadLabel = new JLabel("Load input file");
		JButton chooseButton = new JButton("Choose");
		JButton loadButton = new JButton("Load");
		JPanel topPanel = new JPanel();
		topPanel.add(loadLabel);
		topPanel.add(chooseButton);
		topPanel.add(loadButton);
		
		final JFileChooser fileChooser = new JFileChooser();
		
		chooseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			        int returnVal = fileChooser.showOpenDialog(frame);

			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			            //	This is where a real application would open the file.
			        } else {
			            //	Do stuff...
			        }
			   }
		});
		
		loadButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//	Reload file and reset everything				
			}
		});
		
		
		JButton stepButton = new JButton("Step");
		JButton runButton = new JButton("Run");
		JButton stopButton = new JButton("Stop");
		JButton resetButton = new JButton("Reset");
		JPanel botPanel = new JPanel();
		hexBox = new JCheckBox("Hex");
		botPanel.add(stepButton);
		botPanel.add(runButton);
		botPanel.add(stopButton);
		botPanel.add(resetButton);
		botPanel.add(hexBox);
		
		stepButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//	Call step function in controller				
			}
		});
		
		runButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//	Call run function in controller	
			}
		});
		
		stopButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//	Call stop function in controller	
			}
		});
		
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//	Call reset function in controller	
			}
		});
		
		hexBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(hexBox.isSelected()){
					// 	Call setHex function in controller
				} else {
					//	Call setDec function in controller
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
		
	}	
}
