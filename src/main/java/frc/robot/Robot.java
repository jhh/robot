package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import org.strykeforce.thirdcoast.telemetry.TelemetryController;
import org.strykeforce.thirdcoast.telemetry.TelemetryService;

public class Robot extends TimedRobot {
    private DeadeyeH0 deadeyeH0;
    private TelemetryService telemetry;

    @Override
    public void robotInit() {
        deadeyeH0 = new DeadeyeH0();
        telemetry = new TelemetryService(TelemetryController::new);

        telemetry.register(deadeyeH0);
        telemetry.start();
    }
}