package parkingLot;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Light extends Vehicle {

  public Light(String vehicleNo, boolean disabled, LocalDateTime entryTime) {
    super(vehicleNo, disabled, entryTime);
  }
  
}
