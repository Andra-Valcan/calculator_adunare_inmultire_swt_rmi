package Operation;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalculatorOperation extends Remote{
	public double getSum(double no1,double no2) throws RemoteException;
	public double getMultiplication(double no1,double no2) throws RemoteException;
}
