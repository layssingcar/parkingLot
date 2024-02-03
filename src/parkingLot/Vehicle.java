package parkingLot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Vehicle {
  
  private String vehicleNo;
  private boolean isdisabled;
  private LocalDateTime entryTime;
  
  public void info() {
    
    System.out.println("\n차량 번호: " + vehicleNo);
    System.out.println("장애인 여부: " + isdisabled);
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd(E) HH:mm:ss");
    System.out.println("입차 시간: " + formatter.format(entryTime));
    
  }

}
