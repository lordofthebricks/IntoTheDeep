package org.firstinspires.ftc.teamcode.hardwareclasses;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.List;

public class Claw {
    public Limelight3A limelight3A;
    private Servo Wrist1;
    private Servo Wrist2;
    private Servo Claw;

    private DcMotor LeftFront;
    private DcMotor LeftBack ;
    private DcMotor RightFront;
    private DcMotor RightBack;


    public Claw(HardwareMap hwMap, DcMotor LF, DcMotor LB, DcMotor RF, DcMotor RB ) {
        limelight3A = hwMap.get(Limelight3A.class, "LL3A");
        Claw = hwMap.get(Servo.class, "Claw");
        Wrist1 = hwMap.get(Servo.class, "Wrist1");
        Wrist2 = hwMap.get(Servo.class, "Wrist2");

        LeftFront = LF;
        LeftBack = LB;
        RightFront = RF;
        RightBack = RB;






    }

    public void init(){

        limelight3A.pipelineSwitch(1);


        limelight3A.start();

    }


    public class ReadyToGrab implements Action {

        boolean initialized = false;
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (!initialized) {
                Wrist1.setPosition(0.5);
                Wrist2.setPosition(0.5);
                Claw.setPosition(0.8);
                initialized = true;
            }


            // returns true if all the servos are not at their correct positions
            return (Wrist1.getPosition() != 0.5 || Wrist2.getPosition() != 0.5 || Claw.getPosition() != 0.8);
        }
    }

    public class Grab implements Action {
        boolean initialized = false;
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if( !initialized) {
                Claw.setPosition(0.5);
                initialized = true;
            }

            return (Claw.getPosition() != 0.5);
        }
    }

    public class BringUp implements Action {
        boolean initialized = false;
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if( !initialized) {
                Wrist2.setPosition(0.05);
                Wrist1.setPosition(0.2);
                initialized = true;
            }

            return (Wrist1.getPosition() != 0.2 || Wrist2.getPosition() != 0.05);
        }
    }

    public class LLLineUpHorizontal implements Action {
        private boolean initialized = false;

        LLResult result;
        LLResultTypes.ColorResult colorResult;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                limelight3A.pipelineSwitch(1);
                result = limelight3A.getLatestResult();
                if(result.isValid()) {
                    colorResult = result.getColorResults().get(0);

                    if (colorResult.getTargetXDegrees() > 2) {
                        LeftFront.setPower(-0.2);
                        LeftBack.setPower(0.2);
                        RightFront.setPower(0.2);
                        RightBack.setPower(-0.2);
                    }

                    if (colorResult.getTargetXDegrees() < -2) {
                        LeftFront.setPower(0.2);
                        LeftBack.setPower(-0.2);
                        RightFront.setPower(-0.2);
                        RightBack.setPower(0.2);
                    }

                    initialized = true;
                }else {
                    return true;
                }
            }


            if (colorResult.getTargetXDegrees() < 2 && colorResult.getTargetXDegrees() > -2){
                LeftFront.setPower(0);
                LeftBack.setPower(0);
                RightFront.setPower(0);
                RightBack.setPower(0);
                return false;
            }else{
                return true;
            }


        }
    }

    public class LLLineUpVertical implements Action {
        private boolean initialized = false;

        LLResult result;
        LLResultTypes.ColorResult colorResult;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                limelight3A.pipelineSwitch(1);
                result = limelight3A.getLatestResult();
                if(result.isValid()) {
                    colorResult = result.getColorResults().get(0);

                    if (colorResult.getTargetYDegrees() > 2) {
                        LeftFront.setPower(-0.2);
                        LeftBack.setPower(-0.2);
                        RightFront.setPower(-0.2);
                        RightBack.setPower(-0.2);
                    }

                    if (colorResult.getTargetYDegrees() < -2) {
                        LeftFront.setPower(0.2);
                        LeftBack.setPower(0.2);
                        RightFront.setPower(0.2);
                        RightBack.setPower(0.2);
                    }

                    initialized = true;
                }else {
                    return true;
                }
            }


            if (colorResult.getTargetYDegrees() < 2 && colorResult.getTargetYDegrees() > -2){

                LeftFront.setPower(0);
                LeftBack.setPower(0);
                RightFront.setPower(0);
                RightBack.setPower(0);

                return false;
            }else{
                return true;
            }


        }
    }

    public class llGrab implements Action {


        @Override
        public boolean run(@NonNull TelemetryPacket packet) {

            limelight3A.pipelineSwitch(1);
            LLResult result = limelight3A.getLatestResult();
            LLResultTypes.ColorResult colorResults = result.getColorResults().get(0);


            if (colorResults != null) {
                List<List<Double>> targetCorners = colorResults.getTargetCorners();
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
                        targetAngle = 0.2 - (Math.atan2(c1c2YOffset,c1c2XOffset) * (1/300)) ;
                    }

                } else {
                    if (c1c2XOffset == 0){
                        targetAngle = 0.5;
                    }else {
                        targetAngle = 0.5 - (Math.atan2(c1c2YOffset,c1c2XOffset) * (1/300)) ;
                    }
                }

                Wrist2.setPosition(0.2);
                Wrist1.setPosition(targetAngle);
                Wrist2.setPosition(0.5);

                Claw.setPosition(0.8);
            } else {
                packet.put("No Target Found", null);
                return true;
            }

            return false;
        }
    }

    public Action LLGrab() {
        return new llGrab();
    }

    public Action Grab(){ return new Grab();}

    public Action ReadyToGrab() { return new ReadyToGrab();}

    public Action BringUp() { return new BringUp();}

    public Action LLLineupHorizontal() { return new LLLineUpHorizontal(); }

    public Action LLLVertical() {return new LLLineUpVertical(); }



//    public LLResultTypes.DetectorResult getDistanceToTarget(String color){
//
//
//        List<LLResultTypes.DetectorResult> results = limelight3A.getLatestResult().getDetectorResults();
//        if (results == null) {
//            return null;
//        }
//        for (LLResultTypes.DetectorResult result: results) {
//            double targetOffsetAngle_Vertical = result.getTargetYDegrees();
//
//            // how many degrees back is your limelight rotated from perfectly vertical?
//            double limelightMountAngleDegrees = 45;
//
//            // distance from the center of the Limelight lens to the floor
//            double limelightLensHeightInches = 5.5;
//
//            // distance from the target to the floor
//            double goalHeightInches = 1.5;
//
//            double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
//            double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);
//
//            //calculate distance
//            double distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches) / Math.tan(angleToGoalRadians);
//            double desiredDistance = 8.5;
//            if (distanceFromLimelightToGoalInches >= (desiredDistance - 1.75) && distanceFromLimelightToGoalInches <= (desiredDistance + 1.75)){
//                if(result.getClassName() == color) {
//                    return result;
//                }
//                break;
//            }
//
//        }
//        return null;
//    }
}




