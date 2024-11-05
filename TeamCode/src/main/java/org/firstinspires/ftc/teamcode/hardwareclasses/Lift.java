package org.firstinspires.ftc.teamcode.hardwareclasses;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Lift {

    DcMotor lift;

    double WheelInchesPerRotation = 4.72441;
    double MotorTicksPerRotation = 537.7;
    double InchesPerTick = MotorTicksPerRotation / WheelInchesPerRotation;


    public Lift(HardwareMap hwMp) {hwMp.get(DcMotor.class, "Lift");}



    public class UpLift implements Action {
        private boolean initialized = false;
        private double inches;

        public UpLift(double inches){
            lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            this.inches = inches;
        }



        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (!initialized) {
                int targetInches = (int) (lift.getCurrentPosition() + (inches * InchesPerTick));
                lift.setTargetPosition(targetInches);
                lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                lift.setTargetPosition(targetInches);
                lift.setPower(0.4);
                initialized = true;
            }

            if (lift.getCurrentPosition() < lift.getTargetPosition()){
                return true;
            } else {
                lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                lift.setPower(0);
                lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                return false;
            }
        }
    }

    public class Downlift implements Action {
        private boolean initialized = false;
        private double inches;

        public Downlift(double inches){
            lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            this.inches = inches;
        }



        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (!initialized){
                int targetInches = (int) (lift.getCurrentPosition() - (inches * InchesPerTick));
                lift.setTargetPosition(targetInches);
                lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                lift.setTargetPosition(targetInches);
                lift.setPower(0.4);
                initialized = true;
            }

            if (lift.getCurrentPosition() > lift.getTargetPosition()){
                return true;
            }
            lift.setPower(0);
            lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            return false;
        }
    }


    public Action upLift(double inches) {
        return new UpLift(inches);
    }
    public Action downLift(double inches) {
        return new Downlift(inches);
    }

}


