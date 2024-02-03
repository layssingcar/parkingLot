package parkingLot;

import java.time.LocalDateTime;
import java.util.Scanner;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ParkingLotHandler {
  
  private Scanner sc;
  private ParkingLot parkingLot;
  
  public ParkingLotHandler(ParkingLot parkingLot) {
    super();
    this.sc = new Scanner(System.in);
    this.parkingLot = parkingLot;
  }
  
  // 입차
  private Vehicle entryVehicle() {
    
    try {
      
      System.out.print("차량 번호: ");
      String vehicleNo = sc.next();
      
      System.out.print("차종 (일반/경차) : ");
      String vehicleType = sc.next();
      
      System.out.print("장애인 여부: ");
      boolean isDisabled = sc.nextBoolean();
      
      switch (vehicleType) {
      case "일반": return new General(vehicleNo, isDisabled, LocalDateTime.now());
      case "경차": return new Light(vehicleNo, isDisabled, LocalDateTime.now());
      default: 
        System.out.println("차종을 다시 입력하세요.");
      }
      
    } catch (Exception e) {
      System.out.println("잘못된 입력입니다.");
      sc.nextLine();
    }
    
    return null;
    
  }
  
  // 차량 번호 입력
  private String inputVehicleNo() {
    System.out.print("차량 번호 입력: ");
    return sc.next();
  }
  
  // 주차 관리
  public void manage() {
    
    System.out.println("=== 주차관리 프로그램 ===");
    
    while(true) {
      
      System.out.print("\n1. 입차 "
          + "\n2. 차량 조회 "
          + "\n3. 전체 차량 조회 "
          + "\n4. 출차 "
          + "\n0. 프로그램 종료"
          + "\n\n메뉴 번호 입력: ");
      String menuNum = sc.next();
      
      switch(menuNum) {
      case "1": parkingLot.parking(entryVehicle()); break;
      case "2": parkingLot.search(inputVehicleNo()); break;
      case "3": parkingLot.searchAll(); break;
      case "4": parkingLot.exit(inputVehicleNo()); break;
      case "0":
        System.out.println("프로그램을 종료합니다.");
        return;
      default: System.out.println("잘못된 입력입니다.");
      }
    
    }
    
  }
  
}
