package ticketmachine;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@Before
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation
	public void priceIsCorrectlyInitialized() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
		assertEquals("Initialisation incorrecte du prix", PRICE, machine.getPrice());
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	public void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		assertEquals("La balance n'est pas correctement mise à jour", 10 + 20, machine.getBalance()); // Les montants ont été correctement additionnés               
	}
        
        @Test
	// S3 :  on n’imprime pas le ticket si le montant inséré est insuffisant
	public void noPrintTicket() {                			
		assertEquals("La montant inséré est suffisant", false, machine.printTicket());             
	}
        
        @Test
	// S4 :  on imprime le ticket si le montant inséré est suffisant
	public void PrintTicket() {
                machine.insertMoney(50);			
		assertEquals("La montant inséré est insuffisant", true, machine.printTicket());              
	}
        
        @Test
	// S5 :  quand on imprime le ticket la balance est décrémentée du prix du ticket
	public void decrementeBlance() {
                machine.insertMoney(50);
                machine.printTicket();
		assertEquals("La balance n'est pas décrémenté corectement", 0, machine.getBalance());              
	}
        
        @Test
	// S6 :  le montant collecté est mis à jour quand on imprime un ticket (pas avant)
	public void majMontant() {
                machine.insertMoney(50);
                machine.printTicket();
		assertEquals("La montant collecté ne correspond pas", 50, machine.getTotal());              
	}
        
        @Test
	// S7 :  refund() rend correctement la monnaie
	public void refundMonnaie() {
                machine.insertMoney(50);                
		assertEquals("La montant rendu n'est pas le bon", 50, machine.refund());              
	}
        
        @Test
	// S8 :  refund() remet la balance à zéro
	public void refundBalanceZero() {
                machine.insertMoney(50);
                machine.refund();
		assertEquals("La balance n'est pas remis à zéro", 0, machine.getBalance());              
	}
        
        @Test
	// S9 :  on ne peut pas insérer un montant négatif
	public void insertNegativeMontant() {
            try {
                 machine.insertMoney(-50);
            } catch (IllegalArgumentException e) {
                System.out.println("Le montant doit être positif");
            }
	}
        
        @Test
	// S10 :  on ne peut pas créer de machine qui délivre dse tickets dont le prix est négatif
	public void negativeTicket() {
            try {
                int price2 = -50; 
                TicketMachine machine2;
                machine2 = new TicketMachine(price2);
                fail();
            } catch (IllegalArgumentException e) {
                System.out.println("Le montant des tickets de la machine doit être positif");
            }              
	}
        
        
       
        

}
