package server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import Operation.ICalculatorOperation;


public class ServerCalculator extends UnicastRemoteObject implements ICalculatorOperation{
	public ServerCalculator() throws RemoteException {
		super();
	}

	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		try {
			Registry registry  =  LocateRegistry.createRegistry(7777);//port
			registry.bind("calculator", new ServerCalculator());
			System.out.println("The RMI server IS RUNNING AND READY...");
		} catch (Exception e) {
			System.out.println("ERROR: The RMI server is not running...");
		}
		
		
	}


	@Override
	public double getSum(double no1, double no2) throws RemoteException {
		// TODO Auto-generated method stub
		return no1+no2;
	}

	@Override
	public double getMultiplication(double no1, double no2) throws RemoteException {
		// TODO Auto-generated method stub
		return no1*no2;
	}

}
