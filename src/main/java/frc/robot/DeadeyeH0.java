package frc.robot;

import org.jetbrains.annotations.NotNull;
import org.strykeforce.deadeye.Deadeye;
import org.strykeforce.deadeye.Point;
import org.strykeforce.deadeye.TargetDataListener;
import org.strykeforce.deadeye.UprightRectTargetData;
import org.strykeforce.thirdcoast.telemetry.item.Measurable;
import org.strykeforce.thirdcoast.telemetry.item.Measure;

import java.util.Set;
import java.util.function.DoubleSupplier;

public class DeadeyeH0 implements TargetDataListener<UprightRectTargetData>, Measurable {
    private final static String X = "X";
    private final static String Y = "Y";

    private final Deadeye<UprightRectTargetData> deadeye = new Deadeye<>("H0", UprightRectTargetData.class);

    private int x;
    private int y;

    public DeadeyeH0() {
        deadeye.setTargetDataListener(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    ///////////////////////////////////////////////////////////////////////////
    // TargetDataListener
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public void onTargetData(UprightRectTargetData data) {
        Point center = data.center;
        x = center.x;
        y = center.y;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Measurable
    ///////////////////////////////////////////////////////////////////////////
    @NotNull
    @Override
    public String getDescription() {
        return "Deadeye H0";
    }

    @NotNull
    @Override
    public Set<Measure> getMeasures() {
        return Set.of(new Measure(X, "Target X"), new Measure(Y, "Target Y"));
    }

    @NotNull
    @Override
    public String getType() {
        return "deadeye";
    }

    @Override
    public int compareTo(@NotNull Measurable measurable) {
        return Integer.compare(getDeviceId(), measurable.getDeviceId());
    }

    @Override
    public int getDeviceId() {
        return 0;
    }

    @NotNull
    @Override
    public DoubleSupplier measurementFor(@NotNull Measure measure) {
        switch (measure.getName()) {
            case X:
                return this::getX;
            case Y:
                return this::getY;
            default:
                throw new IllegalStateException("Unexpected value: " + measure.getName());
        }
    }
}
