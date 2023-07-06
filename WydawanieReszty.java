import java.util.InputMismatchException;
import java.util.Scanner;

public class WydawanieReszty {
	private int[] monety = {5, 2, 1, 50, 20, 10, 5, 2, 1};
	private int[] ileMonet = {1, 3, 5, 10, 20, 200, 100, 100, 10000};
	private double sumaWKasie = 0.0;

	private void setSuma() {
	    for (int i = 0; i < monety.length; i++) {
	    	if (i > 2) {
	    		sumaWKasie += ((double) monety[i] / 100.0) * ileMonet[i];
	    	}
	    	else{ 
	    		sumaWKasie += monety[i] * ileMonet[i];
	    	}
	    }
	}
    
    private double doWydania() {
    	setSuma();
    	Scanner scanner = new Scanner(System.in);
    	double scanResult = 0;
    	while(true) {
    		System.out.print("Podaj ile chcesz wydac: ");
	    	try {
	    		scanResult = scanner.nextDouble();
	    	}
	    	catch (InputMismatchException e){
	    		System.out.println("Wprowadzono zle dane");
	    		scanner.nextLine();
	    		continue;
	    	}
	    	if(scanResult < 0.0) {
	    		System.out.println("Wprowadzono zle dane");
	    		scanner.nextLine();
	    	}
	    	if(scanResult > sumaWKasie) {
	    		System.out.println("Nie mamy tyle monet w kasie");
	    		System.out.println("Liczba pieniedzy = " + sumaWKasie);
	    		scanner.nextLine();
	    	}
	    	if(scanResult != 0.0 && scanResult < sumaWKasie) {
	    		break;
	    	}
    	}
    	return scanResult;
    }
    
    private void wydawanie(double doWydania) {
    	int[] listaMonet = new int[9];
    	int[] listaMonetGr = new int[9];
    	int zlote = (int) doWydania;
    	int grosze = (int) (doWydania%1*100);
    	System.out.print(zlote + "." + grosze + " zl\n");
    	for(int i = 0; i < 3; i++) {
    		listaMonet[i] = zlote / monety[i];
    		listaMonet[i] = (listaMonet[i] > ileMonet[i]) ? ileMonet[i] : listaMonet[i];
    		zlote -= (listaMonet[i] * monety[i]);
    		ileMonet[i] -= listaMonet[i];
    	}
    	if(zlote != 0) {
    		zlote *= 100;
    		for(int i = 3; i < 9; i++) {
        		listaMonet[i] = zlote / monety[i];
        		listaMonet[i] = (listaMonet[i] > ileMonet[i]) ? ileMonet[i] : listaMonet[i];
        		zlote -= (listaMonet[i] * monety[i]);
        		ileMonet[i] -= listaMonet[i];
        	}
    	}
    	for(int i = 3; i < 9; i++) {
    		listaMonetGr[i] += (grosze / monety[i]);
    		listaMonetGr[i] = (listaMonetGr[i] > ileMonet[i]) ? ileMonet[i] : listaMonetGr[i];
    		grosze -= (listaMonetGr[i] * monety[i]);
    		ileMonet[i] -= listaMonetGr[i];
    	}
    	setSuma();
    	int sumaZl = 0, sumaGr = 0;
    	for(int i = 0; i < 9; i++) {
    		if(i < 3) {
    			sumaZl += listaMonet[i] * monety[i];
    		}
    		if(i > 2) {
    			sumaZl += (listaMonet[i] * monety[i])/100;
    		}
    	}
    	System.out.println((int) doWydania + "=" + sumaZl);
    	for(int i = 0; i < 9; i++) {
    		sumaGr += listaMonetGr[i] * monety[i];
    	}
    	System.out.println((int) (doWydania%1*100) + "=" + sumaGr);
    	
    	if(sumaZl == (int) doWydania && sumaGr == (int) (doWydania%1*100)) {
	    	for(int i = 0; i < 9; i++) {
	    		if(listaMonet[i] != 0 && i < 3) {
	    			System.out.print("Wydaj " + listaMonet[i] + " monet " + monety[i] + " zl\n");
	    		}
	    		if(listaMonet[i] != 0 && i > 2) {
	    			System.out.print("Wydaj " + listaMonet[i] + " monet " + monety[i] + " gr\n");
	    		}
	    		if(listaMonetGr[i] != 0 && i > 2) {
	    			System.out.print("Wydaj " + listaMonetGr[i] + " monet " + monety[i] + " gr\n");    			
	    		}
	    	}
    	}
    	else {
    		System.out.println("Nie mozna wydac reszty - brak monet!");
    	}
    }
    public static void main(String[] args) {
    	WydawanieReszty w = new WydawanieReszty ();
    	while(true) {
	    	double wartosc = w.doWydania();
	    	w.wydawanie(wartosc);
    	}
    }
}
