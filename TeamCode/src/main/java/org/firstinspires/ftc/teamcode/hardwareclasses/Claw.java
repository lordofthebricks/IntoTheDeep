package org.firstinspires.ftc.teamcode.hardwareclasses;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.List;

public class Claw {
    private Limelight3A limelight3A;
    private Servo Wrist1;
    private Servo Wrist2;
    private Servo Claw;


    public Claw(HardwareMap hwMap) {
        limelight3A = hwMap.get(Limelight3A.class, "LL3A");
        Claw = hwMap.get(Servo.class, "Claw");
        Wrist1 = hwMap.get(Servo.class, "Wrist1");
        Wrist2 = hwMap.get(Servo.class, "Wrist2");


    }


    public class llGrab implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {

                initialized = true;
                limelight3A.pipelineSwitch(0);
                LLResultTypes.DetectorResult result = getDistanceToTarget("Yellow");
                if (result != null) {
                    List<List<Double>> targetCorners = result.getTargetCorners();
                    List<Double> c1 = targetCorners.get(0);
                    List<Double> c2 = targetCorners.get(1);
                    List<Double> c3 = targetCorners.get(2);
                    List<Double> c4 = targetCorners.get(3);

                    double c1c2YOffset = c1.get(1) - c2.get(1);
                    double c1c2XOffset = c1.get(0) - c2.get(1);
                    double c1c3XOffset = c1.get(0) - c3.get(0);
                    double c1c3YOffset = c1.get(1) - c3.get(1);
                    double targetAngle;
                    boolean isHorizontal = false;

                    if (c1c2XOffset > c1c3YOffset){
                        isHorizontal = true;
                    }else{
                        isHorizontal = false;
                    }


                    if (isHorizontal){
                        if (c1c2XOffset == 0){
                            targetAngle = 0.2;
                        }else {
                            targetAngle = 0.2 + (Math.atan2(c1c2YOffset,c1c2XOffset) * (1/300)) ;
                        }

                    } else {
                        if (c1c2XOffset == 0){
                            targetAngle = 0.5;
                        }else {
                            targetAngle = 0.5 + (Math.atan2(c1c2YOffset,c1c2XOffset) * (1/300)) ;
                        }
                    }
                    Wrist1.setPosition(targetAngle);
                    Claw.setPosition(0.8);
                }




            }

            return false;
        }
    }

    public Action spinUp() {
        return new llGrab();
    }




    public LLResultTypes.DetectorResult getDistanceToTarget(String color){
        List<LLResultTypes.DetectorResult> results = limelight3A.getLatestResult().getDetectorResults();
        for (LLResultTypes.DetectorResult result: results) {
            double targetOffsetAngle_Vertical = result.getTargetYDegrees();

            // how many degrees back is your limelight rotated from perfectly vertical?
            double limelightMountAngleDegrees = 45;

            // distance from the center of the Limelight lens to the floor
            double limelightLensHeightInches = 5.5;

            // distance from the target to the floor
            double goalHeightInches = 1.5;

            double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
            double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);

            //calculate distance
            double distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches) / Math.tan(angleToGoalRadians);
            double desiredDistance = 8.5;
            if (distanceFromLimelightToGoalInches >= (desiredDistance - 1.75) && distanceFromLimelightToGoalInches <= (desiredDistance + 1.75)){
                if(result.getClassName() == color) {
                    return result;
                }
                break;
            }

        }
        return null;
    }
}




