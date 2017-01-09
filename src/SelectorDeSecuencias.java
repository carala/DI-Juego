import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class SelectorDeSecuencias{
	
public static ArrayList<Integer> seleccionarSecuenciaDeTxt() throws IOException{
		File f = new File("FicheroSecuencias.txt");
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String linea = "init";
		ArrayList<String> lineas = new ArrayList<String>();
		ArrayList<Integer> resultado = new ArrayList<Integer>();
		
		while(linea!=null){
			linea = br.readLine();
			lineas.add(linea);
		}
		System.out.println(lineas);
		
		int opc=-1;
		
		do{
			switch(JOptionPane.showOptionDialog(null, "Seleccione la secuencia", "Opcion", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Facil","Medio","Dificil"},"0"))
			 {
				 case 0: opc=0;
				         break;
			       
				 case 1: opc=1;
				         break; 
				         
				 case 2: opc=2;
					 	 break;

				 default:
					 	 JOptionPane.showMessageDialog(null, "Escoja de nuevo.");
					 	 break;
			 }
			}while(opc==-1);
		
		if(opc == 0 ){
			linea = String.valueOf(lineas.get(opc));
			StringTokenizer tokens = new StringTokenizer(linea);
			while(tokens.hasMoreTokens()){
				resultado.add(Integer.parseInt(tokens.nextToken()));
			}
			}else if(opc == 1 ){
				linea = String.valueOf(lineas.get(opc));
				StringTokenizer tokens = new StringTokenizer(linea);
				while(tokens.hasMoreTokens()){
					resultado.add(Integer.parseInt(tokens.nextToken()));
				}
				}else if(opc == 2 ){
					linea = String.valueOf(lineas.get(opc));
					StringTokenizer tokens = new StringTokenizer(linea);
					while(tokens.hasMoreTokens()){
						resultado.add(Integer.parseInt(tokens.nextToken()));
					}
			}
		
		return resultado;
		
	}
}
