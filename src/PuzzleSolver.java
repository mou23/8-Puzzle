import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PuzzleSolver {
	char [][]goal={{'1','2','3'},{'4','5','6'},{'7','8',' '}};
	ArrayList<State>previous=new ArrayList<State>();
	ArrayList<JPanel>panels=new ArrayList<JPanel>();
	
	JFrame board;
	JLabel lbl;
	int currentState;
	int row,column;

	public int findPosition(ArrayList <State>list, State s){
		int position=list.size();
		for(int i=0;i<list.size();i++){
			if(s.numberOfMisplacedTiles<=list.get(i).numberOfMisplacedTiles){
				position=i;
				break;
			}
		}
		return position;
	}

	private boolean stateExists(char [][]mat){
		boolean matched=false;
		for(int l=0;l<previous.size() && !matched;l++){
			matched=true;
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					if(previous.get(l).matrix[i][j]!=mat[i][j]){
						matched=false;
						break;
					}
				}
			}
		}
		return matched;
	}

	private boolean checkSolvability(char [][]initialMatrix){
		char arr[]=new char[8];
		int index=0;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(initialMatrix[i][j]!=' '){
					arr[index]=initialMatrix[i][j];
					index++;
				}
			}
		}
		int count=0;
		for(int i=0;i<7;i++){
			for(int j=i+1;j<8;j++){
				if(arr[i]>arr[j]){
					count++;
				}
			}
		}
		if(count%2==1){
			return false;
		}
		return true;
	}

	public void solvePuzzle(char [][]initialMatrix){
		boolean isSolvable=checkSolvability(initialMatrix);
		if(isSolvable==true){
			int step=0;
			State s=new State();
			s.matrix=initialMatrix;
			s.numberOfMisplacedTiles=calculateHeuristic(s.matrix);
			ArrayList <State>pq ;
			pq= new ArrayList<State>(); 
			pq.add(s);
			while(!pq.isEmpty()){
				State top=new State();
				top=pq.get(0);
				pq.remove(0);

				if(!stateExists(top.matrix)){
					previous.add(top);
					//System.out.println("Size "+previous.size());
					//printMatrix(top.matrix,panel);
					if(top.numberOfMisplacedTiles==0){
						findPath();
						break;
					}
					findBlankSpace(top.matrix);
					char tempMatrix[][]=new char[3][3];

					if(checkBoundary(row+1)){
						State temp=new State();
						tempMatrix=generateMatrix(top.matrix,row+1,column);
						temp.matrix=tempMatrix;
						temp.numberOfMisplacedTiles=calculateHeuristic(temp.matrix);
						temp.parent=top.matrix;
						pq.add(findPosition(pq,temp),temp);
					}

					if(checkBoundary(column+1)){
						State temp=new State();
						tempMatrix=generateMatrix(top.matrix,row,column+1);
						temp.matrix=tempMatrix;
						temp.numberOfMisplacedTiles=calculateHeuristic(temp.matrix);
						temp.parent=top.matrix;
						pq.add(findPosition(pq,temp),temp);
					}

					if(checkBoundary(row-1)){
						State temp=new State();
						tempMatrix=generateMatrix(top.matrix,row-1,column);
						temp.matrix=tempMatrix;
						temp.numberOfMisplacedTiles=calculateHeuristic(temp.matrix);
						temp.parent=top.matrix;
						pq.add(findPosition(pq,temp),temp);
					}

					if(checkBoundary(column-1)){
						State temp=new State();
						tempMatrix=generateMatrix(top.matrix,row,column-1);
						temp.matrix=tempMatrix;
						temp.numberOfMisplacedTiles=calculateHeuristic(temp.matrix);
						temp.parent=top.matrix;
						pq.add(findPosition(pq,temp),temp);
					}
				}	
			}
		}
		else{
			JOptionPane.showMessageDialog(null,"Puzzle not solvable");
		}
	}
	
	private void findPath(){
		JPanel panel=createBoard();
		State state=previous.get(previous.size()-1);
		while(true){
			createPanel(state.matrix, panel);
			char [][]currentMatrix=state.parent;
			if(state.parent==null){
				break;
			}
			for(int i=0;i<previous.size();i++){
				int index=i;
				for(int j=0;j<3;j++){
					for(int k=0;k<3;k++){
						if(currentMatrix[j][k]!=previous.get(i).matrix[j][k]){
							index=-1;
						}
					}
				}
				if(index!=-1){
					state=previous.get(i);
					previous.remove(i);
					break;
				}
			}
		}
		currentState=panels.size()-1;
		lbl.setText("Step: "+ currentState);
		JPanel currentPanel=panels.get(currentState);
		panel.add(currentPanel);
		panel.repaint();
		panel.revalidate();
		currentPanel.setVisible(true);
	}
	
	private char[][] generateMatrix(char [][]matrix,int r,int c){
		char tempMatrix[][]=new char[3][3];
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				tempMatrix[i][j]=matrix[i][j];
			}
		}
		tempMatrix[row][column]=tempMatrix[r][c];
		tempMatrix[r][c]=' ';

		return tempMatrix;
	}

	private boolean checkBoundary(int value){
		if(value>=0 && value<3){
			return true;
		}
		return false;
	}

	private void findBlankSpace(char [][]matrix){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(matrix[i][j]==' '){
					row=i;
					column=j;
					break;
				}
			}
		}
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	private JPanel createBoard(){
		board=new JFrame("8 Puzzle");
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setResizable(false);
		board.setBounds(100, 100, 310, 363);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		board.setContentPane(contentPane);
		
		contentPane.setLayout(null);

		JPanel panel1 = new JPanel();
		panel1.setBounds(0, 0, 304, 25);
		contentPane.add(panel1);
		panel1.setLayout(null);

		lbl = new JLabel();
		lbl.setBounds(111, 0, 80, 25);
		panel1.add(lbl);

		JPanel panel3 = new JPanel();
		panel3.setBounds(0, 287, 304, 48);
		contentPane.add(panel3);
		panel3.setLayout(null);
		

		JPanel panel2 = new JPanel();
		panel2.setBounds(0, 25, 304, 261);
		contentPane.add(panel2);
		panel2.setLayout(new CardLayout(0, 0));

		JButton btnPrev = new JButton("Previous");
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentState-1>=0){
					panel2.removeAll();
					currentState--;
					lbl.setText("Step: "+ currentState);
					JPanel currentPanel=panels.get(currentState);
					panel2.add(currentPanel);
					currentPanel.setVisible(true);
					panel2.repaint();
					panel2.revalidate();
				}
			}
		});
		btnPrev.setBounds(53, 11, 87, 31);
		panel3.add(btnPrev);

		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentState+1<panels.size()){
					panel2.removeAll();
					currentState++;
					lbl.setText("Step: "+ currentState);
					JPanel currentPanel=panels.get(currentState);
					panel2.add(currentPanel);
					currentPanel.setVisible(true);
					panel2.repaint();
					panel2.revalidate();
				}
			}
		});

		btnNext.setBounds(168, 11, 87, 31);
		panel3.add(btnNext);

		board.setVisible(true);
		return panel2;
	}

	private void createPanel(char [][]matrix,JPanel panel){
		JPanel newPanel = new JPanel();
		newPanel.setLayout(new GridLayout(3, 3, 1, 1));
		//lbl.setText("Step: "+ step);
		//System.out.println("Step: "+step);
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				System.out.print(matrix[i][j]+"\t");
				JButton button=new JButton(""+matrix[i][j]);
				button.setFont(new Font(Font.SERIF, Font.BOLD, 30));
				newPanel.add(button);
			}
			System.out.println();
		}
		newPanel.repaint();
		newPanel.revalidate();
		
		newPanel.setBackground(new Color(100,40,150));
		panels.add(0,newPanel);
	}

	private int calculateHeuristic(char [][]matrix){
		int count=0;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(matrix[i][j]!=' ' && goal[i][j]!=matrix[i][j]){
					count++;
				}
			}
		}

		return count;
	}
}
