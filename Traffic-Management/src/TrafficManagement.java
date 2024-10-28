
abstract class Vehicle {

    public abstract void run();
    public abstract void stop();
    public abstract void printType();
}


class Car extends Vehicle {
    @Override
    public void run() {
        System.out.println("Xe hơi đang chạy.");
    }

    @Override
    public void stop() {
        System.out.println("Xe hơi đã dừng.");
    }

    @Override
    public void printType() {
        System.out.println("Đây là xe hơi.");
    }
}


class Bicycle extends Vehicle {
    @Override
    public void run() {
        System.out.println("Xe đạp đang chạy.");
    }

    @Override
    public void stop() {
        System.out.println("Xe đạp đã dừng.");
    }

    @Override
    public void printType() {
        System.out.println("Đây là xe đạp.");
    }
}


public class TrafficManagement {
    public static void main(String[] args){
        Vehicle car = new Car();
        car.printType();
        car.run();
        car.stop();
        System.out.println();
        Vehicle bicycle = new Bicycle();
        bicycle.printType();
        bicycle.run();
        bicycle.stop();
    }
}
