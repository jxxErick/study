package br.com.hcode.designpattern.factory.vehicles;

public class Bike implements IVehicle {

    @Override
    public void startRoute() {
        getCargo();
        System.out.println("Bike startRoute");
    }

    @Override
    public void getCargo() {
        System.out.println("Bike getCargo");
    }

}
