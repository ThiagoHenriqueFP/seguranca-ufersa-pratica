package calc;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalcServiceImpl extends UnicastRemoteObject implements CalcService {
    protected CalcServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public double sum(double a, double b) {
        return a + b;
    }

    @Override
    public double mul(double a, double b) {
        return a * b;
    }

    @Override
    public double div(double a, double b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a / b;
    }

    @Override
    public double less(double a, double b) {
        return a - b;
    }
}
