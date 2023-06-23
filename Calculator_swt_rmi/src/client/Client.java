package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import Operation.ICalculatorOperation;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class Client {

	protected Shell shell;
	private static ICalculatorOperation serverOperations;
	private Text no1;
	private Text no2;
	/**
	 * Launch the application.
	 * @param args
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 */
	public static void main(String[] args)  {
		try {
            serverOperations = (ICalculatorOperation) Naming.lookup("rmi://localhost:7777/calculator");
        } catch (Exception e) {
            e.printStackTrace();
        }
	
		try {
			Client window = new Client();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		/**
		 * no1-prima caseta text
		 */
		no1 = new Text(shell, SWT.BORDER);
		no1.setBackground(SWTResourceManager.getColor(0, 0, 128));
		no1.setBounds(82, 76, 80, 31);
		
		/**
		 * no2 a2A CASETA TExt
		 */
		no2 = new Text(shell, SWT.BORDER);
		no2.setBackground(SWTResourceManager.getColor(255, 128, 64));
		no2.setBounds(240, 76, 80, 31);
		
		Label lblIntroducetiCeleDoua = new Label(shell, SWT.NONE);
		lblIntroducetiCeleDoua.setBounds(73, 26, 311, 25);
		lblIntroducetiCeleDoua.setText("introduceti cele doua numere:");
		
		Label rezultat = new Label(shell, SWT.NONE);
		rezultat.setBounds(128, 186, 171, 25);
		rezultat.setText("Rezultatul este:");
		
		Button btnAduna = new Button(shell, SWT.NONE);
		/**
		 * daca se apasa butonu aduna
		 */
		btnAduna.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				try {
					//apelam metoda de calcul suma
					 Double res=serverOperations.getSum(Double.parseDouble(no1.getText()), Double.parseDouble(no2.getText()));
					 //setam valoarea labelului rezultat suma celor 2 numere
					 rezultat.setText("Rezultatul este: "+res);
				} catch (NumberFormatException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAduna.setForeground(SWTResourceManager.getColor(128, 128, 255));
		btnAduna.setBounds(43, 130, 105, 35);
		btnAduna.setText("aduna");
		
		Button btnInmulteste = new Button(shell, SWT.NONE);
		
		/**
		 * daca se apasa butonul inmulteste
		 */
		btnInmulteste.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					//apelam metoda de inmultire
					//si extragem din no1 si no2 valorile introduse in casutele text pe care le convertim in double 
					Double res=serverOperations.getMultiplication(Double.parseDouble(no1.getText()), Double.parseDouble(no2.getText()));
					 //setam valoarea labelului rezultat produsul celor 2 numere
					 rezultat.setText("Rezultatul este: "+res);
				} catch (NumberFormatException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnInmulteste.setForeground(SWTResourceManager.getColor(255, 0, 0));
		btnInmulteste.setBounds(238, 130, 105, 35);
		btnInmulteste.setText("inmulteste");

	}
}
