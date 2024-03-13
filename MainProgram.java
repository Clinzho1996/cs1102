// Module 1: Design and implement an interface named "Vehicle"
interface Vehicle {
    String getMake();
    String getModel();
    int getYear();
}

// Module 2: Car class implementing the Vehicle and CarVehicle interfaces
interface CarVehicle extends Vehicle {
    void setNumDoors(int numDoors);
    int getNumDoors();
    void setFuelType(String fuelType);
    String getFuelType();
}

class Car implements CarVehicle {
    private String make;
    private String model;
    private int year;
    private int numDoors;
    private String fuelType;

    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public void setNumDoors(int numDoors) {
        this.numDoors = numDoors;
    }

    @Override
    public int getNumDoors() {
        return numDoors;
    }

    @Override
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public String getFuelType() {
        return fuelType;
    }
}

// Module 3: Motorcycle class implementing the Vehicle and MotorVehicle interfaces
interface MotorVehicle extends Vehicle {
    void setNumWheels(int numWheels);
    int getNumWheels();
    void setMotorcycleType(String type);
    String getMotorcycleType();
}

class Motorcycle implements MotorVehicle {
    private String make;
    private String model;
    private int year;
    private int numWheels;
    private String motorcycleType;

    public Motorcycle(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public void setNumWheels(int numWheels) {
        this.numWheels = numWheels;
    }

    @Override
    public int getNumWheels() {
        return numWheels;
    }

    @Override
    public void setMotorcycleType(String type) {
        this.motorcycleType = type;
    }

    @Override
    public String getMotorcycleType() {
        return motorcycleType;
    }
}

// Module 4: Truck class implementing the Vehicle and TruckVehicle interfaces
interface TruckVehicle extends Vehicle {
    void setCargoCapacity(double cargoCapacity);
    double getCargoCapacity();
    void setTransmissionType(String transmissionType);
    String getTransmissionType();
}

class Truck implements TruckVehicle {
    private String make;
    private String model;
    private int year;
    private double cargoCapacity;
    private String transmissionType;

    public Truck(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public void setCargoCapacity(double cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    @Override
    public double getCargoCapacity() {
        return cargoCapacity;
    }

    @Override
    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    @Override
    public String getTransmissionType() {
        return transmissionType;
    }
}

// Module 5: Main program to create and display vehicle details
public class MainProgram {
    public static void main(String[] args) {
        // Create car object
        Car car = new Car("Toyota", "Camry", 2022);
        car.setNumDoors(4);
        car.setFuelType("Petrol");

        // Create motorcycle object
        Motorcycle motorcycle = new Motorcycle("Harley", "Sportster", 2021);
        motorcycle.setNumWheels(2);
        motorcycle.setMotorcycleType("Cruiser");

        // Create truck object
        Truck truck = new Truck("Ford", "F-150", 2020);
        truck.setCargoCapacity(2.5);
        truck.setTransmissionType("Automatic");

        // Display vehicle details
        displayVehicleDetails(car);
        displayVehicleDetails(motorcycle);
        displayVehicleDetails(truck);
    }

    private static void displayVehicleDetails(Vehicle vehicle) {
        System.out.println("Make: " + vehicle.getMake());
        System.out.println("Model: " + vehicle.getModel());
        System.out.println("Year: " + vehicle.getYear());

        if (vehicle instanceof CarVehicle) {
            CarVehicle car = (CarVehicle) vehicle;
            System.out.println("Number of Doors: " + car.getNumDoors());
            System.out.println("Fuel Type: " + car.getFuelType());
        } else if (vehicle instanceof MotorVehicle) {
            MotorVehicle motorcycle = (MotorVehicle) vehicle;
            System.out.println("Number of Wheels: " + motorcycle.getNumWheels());
            System.out.println("Motorcycle Type: " + motorcycle.getMotorcycleType());
        } else if (vehicle instanceof TruckVehicle) {
            TruckVehicle truck = (TruckVehicle) vehicle;
            System.out.println("Cargo Capacity: " + truck.getCargoCapacity() + " tons");
            System.out.println("Transmission Type: " + truck.getTransmissionType());
        }

        System.out.println();
    }
}
