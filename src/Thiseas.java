import java.io.*;
import java.util.Scanner;

public class Thiseas {
	
	//statikh klash position opou krataw
	// tis theseis y kai x
	//wste otan ftasw sthn eksodo na tis exw swsmenes
	// ta antikeimena position bainoun sto stack
	
	static class Position{
		public int row;
		public int column;
		Position(int row,int column){
			this.row=row;
			this.column=column;
		}
		
		
	}

	public static void main(String[] args) throws Exception {
		
		try {
			
			//to path tou text file
			String path=args[0];
			
			//antikeimeno file
			File file=new File(path);
			
			//antikeimeno scanner gia epeksergasia tou 
			//text arxeiou character by character
		    Scanner input1=new Scanner(file);
			
			// xrhsimopoihsa bufferedreader gia to diavasma grammwn giati 
			//me antikeimena scanner de diavaze swsta tis grammes gia kapoio logo
			
			BufferedReader reader=new BufferedReader(new FileReader(path));
			String line=reader.readLine();
			int line_counter =0;
			while(line!=null) {
				
				line_counter++;
				line=reader.readLine();
			}
			reader.close();
			line_counter=line_counter-2;
			
			//kalw th methodo check_maze gia na dw kata poso einai swsta
			//ta dedomena eisodou se arxikh fash, arguments to scanner input1 kai to line_counter
			
			check_maze(input1,line_counter);
			input1.close();
			
		
			//Dhmiourgia deuterou antikeimenou scanner
			//gia na epeksergastw to keimeno kai na to valw
			// se pinaka xarakthrwn
		
			Scanner input2=new Scanner(file);
			
			//afou exw perasei to check_maze
			// exw vevaiwthei oti stis prwtes 4 grammes
			//exw swsth morfh opote ta vazw se antistoixes metavlhtes
			//pou ta ekproswpoun
			
			int table[]=new  int[4];
			for(int i=0; i<4;i++) {
				table[i]=Integer.parseInt(input2.next());
			}
		
			int rows=table[0];
			int columns=table[1];
			int e_x=table[2];
			int e_y=table[3];
			
			//ftiaxnw antikeimeno position me th thesh ths eisodou
			Position E_pos=new Position(e_x,e_y);
		
		
			String character="";
			char buffer[][]=new char[rows][columns];
		
			//ftiaxnw ton pinaka xarakthrwn me
			//ola ta stoixeia tou text file
			//pera apo tis diastaseis kai th
			//thesh tou E.
				
			for(int i=0;i<=buffer.length-1;i++) {
				for (int j=0;j<=buffer[0].length-1;j++) {
					character=input2.next();
					buffer[i][j]=character.charAt(0);
				}
			}
			input2.close();
			
			//kanw elegxo gia mia polu eidikh periptwsh
			//opou to E den vrisketai sto position pou
			//leei to text arxeio alla se allo
			
			if(buffer[e_x][e_y]!='E') {
				throw new Exception();
			}
			
			//kalw th methodo solve_maze
			//gia na vrw exit an uparxei
			
			solve_maze(E_pos,buffer);
		}
		
		//kaleitai se opoiadhpote periptwsh
		//ta dedomena afou exw gurisei sth main den exoun swsth morfh
		//kai tupwnei antistoixo mhnuma
		
		catch(Exception e) {
			System.out.println("\n\nINCORRECT FORM OF TEXT DOCUMENT, TERMINATING THE PROGRAM!");
		}
		
	}	
			
	
	
	
	//methodos solve_maze
	private static void solve_maze(Position Entry_pos,char buffer[][]){
		
		//ftiaxnw antikeimeno stringstackimpl gia na xrhsimopoihsw th stoiva
		
		StringStackImpl <Position> stack=new StringStackImpl<>();
		
		//vazw sto top to Entry Point
		
		stack.push(Entry_pos);
		
		//kanei print to entry position
		
		System.out.print("\n\nENTRY POSITION:("+Entry_pos.row+","+Entry_pos.column+")\n\n");
		
		
		//elegxos olwn twn dunatwn kinhsewn se mia while
		while(true) {
			
			//check an borei na paei deksia
			//an borei print move right
			//vale 'd' sto antistoixo stoixeio tou
			//pinaka char gia na kseroume oti to xoume episkeftei
			//kai vale to antikeimeno tupou position me to position tou
			//sto top tou stack
			
			if((stack.peek().column+1)<=(buffer[0].length-1) && buffer[stack.peek().row][stack.peek().column+1]=='0') {
				System.out.println("Move right");
				int y=stack.peek().column+1;
					
					
				stack.push(new Position(stack.peek().row,y));
				buffer[stack.peek().row][y]='d';
			}
			
			//idia logikh pou ekshgeitai parapanw aplws check an borw na paw aristera
			
			else if((stack.peek().column-1)>=0 && buffer[stack.peek().row] [stack.peek().column-1]=='0'){
					
				System.out.println("Move left");
					
				int y=stack.peek().column-1;
					
				stack.push(new Position(stack.peek().row,y));
					
				buffer[stack.peek().row][y]='d';
			}
			
			//idia logikh pou ekshgeitai parapanw aplws check an borw na paw panw	
			
			else if((stack.peek().row-1)>=0 && buffer[stack.peek().row-1][stack.peek().column]=='0'){
				System.out.println("Move up");
				int x=stack.peek().row-1;
				stack.push(new Position(x,stack.peek().column));
				buffer[x][stack.peek().column]='d';
			}
			//idia logikh pou ekshgeitai parapanw aplws check an borw na paw katw	
			
			else if((stack.peek().row+1)<=(buffer.length-1) && buffer[stack.peek().row+1][stack.peek().column]=='0'){
				System.out.println("Move down");
				int x=stack.peek().row+1;
					
					
				stack.push(new Position(x,stack.peek().column));
				buffer[x][stack.peek().column]='d';
					
			}
			
			//edw erxetai otan sto shmeio pou einai den exei pouthena na paei
			else {
				//an eimai sto entry point tote den uparxei eksodos
				if(stack.peek().row==Entry_pos.row && stack.peek().column==Entry_pos.column) {
					System.out.println("BACK TO ENTRY POINT, THERE IS NO ESCAPE!");
					
					break;
				}
				
				//alliws kanw backtracking kai afairw to current top ths stoivas...
				System.out.println("Dead End! Backtracking...\n");
				stack.pop();
				System.out.println("Current Position:("+stack.peek().row+","+stack.peek().column+")"+"\n");
				continue;
					
				}
			
			//edw erxetai mono se periptwsh pou xei kanei kapoia kinhsh kai printarei
			//to current position
			
			System.out.println("Current Position:("+stack.peek().row+","+stack.peek().column+")"+"\n");
				
			
			//kanw check an meta thn kinhsh pou ekana vriskomai se prwth,teleutaia grammh,sthlh
			//an nai vrhka exit
			
			if(stack.peek().row==buffer.length-1 ||stack.peek().column==buffer[0].length-1 ||stack.peek().row==0 ||stack.peek().column==0)  {
				System.out.println("CONGRATULATIONS,YOU FOUND A WAY OUT! EXIT OF THE LABYRINTH IS:("+stack.peek().row+","+stack.peek().column+")");
				break;
			}
				
		}
			
	}
	//methodos check_maze gia thn orthothta twn dedomenwn
	private static void check_maze(Scanner input,int line_counter) throws Exception{
		
		
		
		String character="";
		int size_counter=0;
		int rows=0;  //grammes
		int columns=0;  //sthles
		int e_x=0;      //x tou E
		int e_y=0;      //y tou E
		int E_counter=0; //Counter gia to poses fores sunantame to E ston pinaka
		
		try {
			while (input.hasNext()) {
				
				//krataw to megethos twn xarakthrwn tou text arxeiou
				size_counter++;
				
				
				//arxikopoiw ta rows,columns kai e_x, e_y
				//an den borei na ginei conversion ap to String se int
				//dhladh exw mia timh pou den einai arithmos sto text arxeio
				// vgainei exception
				
				if (size_counter==1) {
					rows=Integer.parseInt(input.next());
					
				}
				else if(size_counter==2) {
					columns=Integer.parseInt(input.next());
				}
				else if(size_counter==3) {
					e_x=Integer.parseInt(input.next());
				}
				else if(size_counter==4) {
					e_y=Integer.parseInt(input.next());
				}
				
				else {
					
						
					character=input.next();
					
					//metraw poses fores vriskw E ston pinaka
					if (character.charAt(0)=='E') {
						
						E_counter++;
					}
					else {
						//an sto text arxeio periexetai kati pou den einai 0 h 1 tote vgale Exception
						if(character.charAt(0)!='0' && character.charAt(0)!='1' &&size_counter>4) {
							
							throw new Exception();
							
						}
					}
				
				} 
			}
		
				
			
			//afairw 4 gia na xw mono to megethos tou pinaka
			//xarakthrwn pou thelw na ftiaksw
			
			size_counter=size_counter-4;
			
			//an ikanopoieitai kapoia ap autes tis sunthikes tote uparxei lathos sta dedomena tou text arxeiou kai vgainei exception
			if(line_counter!=rows || size_counter!=rows*columns  || E_counter!=1 ||((e_x!=0) && (e_x != rows-1)&&(e_y!=0 && e_y!=columns-1))){
				
				throw new Exception();
			}

			//alliws epistrefei sth main
			else {
				
				return;
			}
		}
		catch(Exception e) {
			
			System.out.println("\n\nINCORRECT FORM OF TEXT DOCUMENT, TERMINATING THE PROGRAM!");
			System.exit(1);
		}
	}
}


	

	   		
	   
		
		
	   
	
				
		
	
