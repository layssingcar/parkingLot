package parkingLot;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ParkingLot {
  
  private List<Vehicle> vehicles;
  private static final int GENERAL_MAX = 10;
  private static final int DISABLED_MAX = 3;
  private int generalCount = 0;
  private int disabledCount = 0;
  private static final int STANDARD_TIME = 1;
  private static final int BASIC_RATE = 1;

  public ParkingLot() {
    this.vehicles = new ArrayList<Vehicle>();
    
    vehicles.add(new General("99구9999", false, LocalDateTime.now()));
    vehicles.add(new General("88파8888", true , LocalDateTime.now()));
    vehicles.add(new Light  ("77치7777", false, LocalDateTime.now()));
    vehicles.add(new Light  ("66육6666", true , LocalDateTime.now()));
    
    generalCount += 2;
    disabledCount += 2;
  }
  
  // 주차
  public void parking(Vehicle vehicle) {
    
    if (vehicle == null) return;
    
    if (vehicles.contains(vehicle)) {
      System.out.println("이미 주차된 차량입니다.");
      return;
    }
    
    if (vehicle.isIsdisabled() && disabledCount < DISABLED_MAX) {
      vehicles.add(vehicle);
      disabledCount++;
      System.out.println("주차가 완료되었습니다.");
      return;
    }
    
    if (!vehicle.isIsdisabled() && generalCount < GENERAL_MAX) {
      vehicles.add(vehicle);
      generalCount++;
      System.out.println("주차가 완료되었습니다.");
      return;
    }
    
    System.out.println("만차입니다.");
    
  }
  
  // 차량 조회
  public void search(String vehicleNo) {
    
    if (vehicles.isEmpty()) {
      System.out.println("주차된 차량이 없습니다.");
      return;
    }
    
    for (Vehicle v : vehicles) {
      if (v.getVehicleNo().equals(vehicleNo)) {
        v.info();
        return;
      }
    }
    
    System.out.println("일치하는 차량 번호가 없습니다.");
    
  }
  
  // 전체 차량 조회
  public void searchAll() {
    
    if (vehicles.isEmpty()) {
      System.out.println("주차된 차량이 없습니다.");
      return;
    }
    
    for (Vehicle v : vehicles) v.info();
    
  }
  
  // 차종 판별
  public String distinct(Vehicle v) {
    
    if (v instanceof General) return "일반";
    return "경차";
    
  }
  
  // 출차
  public void exit(String vehicleNo) {
    
    if (vehicles.isEmpty()) {
      System.out.println("주차된 차량이 없습니다.");
      return;
    }
    
    for (int i = 0, size = vehicles.size(); i < size; i++) {
      
      if (vehicles.get(i).getVehicleNo().equals(vehicleNo)) {
        
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(vehicles.get(i).getEntryTime(), now);
        long usedTime = duration.getSeconds();
        
        int rate = (int)(usedTime / STANDARD_TIME * BASIC_RATE);
        
        if (distinct(vehicles.get(i)).equals("경차")) {
          rate *= 0.5;
          System.out.println("주차요금: " + rate + "원 (경차 할인 50%)");
          
        } else if (vehicles.get(i).isIsdisabled()) {
          rate *= 0.5;
          System.out.println("주차요금: " + rate + "원 (장애인 할인 50%)");
          
        } else {
          System.out.println("주차요금: " + rate + "원");
        }
        
        receipt(vehicles.get(i), now, rate);
        vehicles.remove(i);
        System.out.println("이용해 주셔서 감사합니다.");
        return;
        
      }
      
    }
    
    System.out.println("일치하는 차량 번호가 없습니다.");
    
  }

  // 영수증 출력
  public void receipt(Vehicle v, LocalDateTime exitTime, int rate) {
    
    File dir = new File("\\storage");
    if (!dir.exists()) dir.mkdirs();
    
    File file = new File(dir, v.getVehicleNo() + " 주차요금 영수증.txt");
    
    BufferedOutputStream out = null;
    
    try {
      
      out = new BufferedOutputStream(new FileOutputStream(file));
      
      StringBuilder builder = new StringBuilder();
      builder.append("구디 주차장 사용료 영수증");
      builder.append("\n\n 차량 번호: ").append(v.getVehicleNo());
      builder.append("\n 차종: ").append(distinct(v));
      
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd(E) HH:mm:ss");
      builder.append("\n 입차 시간: ").append(formatter.format(v.getEntryTime()));
      builder.append("\n 출차 시간: ").append(formatter.format(exitTime));
      
      builder.append("\n 주차 요금: ").append(rate).append("원");
      builder.append("\n\n이용해 주셔서 감사합니다.");
      
      out.write(builder.toString().getBytes());
      out.flush();
      System.out.println("영수증 출력 완료");
      
    } catch (Exception e) {
      e.printStackTrace();
      
    } finally {
      
      try {
        if (out != null) out.close();
        
      } catch (Exception e) {
        e.printStackTrace();
        
      }
      
    }
    
  }
  
}
