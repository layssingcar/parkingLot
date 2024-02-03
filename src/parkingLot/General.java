package parkingLot;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class General extends Vehicle {
  
  public General(String vehicleNo, boolean disabled, LocalDateTime entryTime) {
    super(vehicleNo, disabled, entryTime);
  }
  
}
