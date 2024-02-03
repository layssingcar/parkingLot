package parkingLot;

public class MainClass {
  
  public static void main(String[] args) {
    
    ParkingLot parkingLot = new ParkingLot();
    new ParkingLotHandler(parkingLot).manage();
    
  }

}
