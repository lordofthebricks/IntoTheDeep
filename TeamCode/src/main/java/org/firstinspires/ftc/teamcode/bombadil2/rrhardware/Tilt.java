package org.firstinspires.ftc.teamcode.bombadil2.rrhardware;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class Tilt {
    public DcMotorEx motor;
    private TouchSensor limitHang;
    private TouchSensor limitSlide;

    public Tilt(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotorEx.class, "Tilt");
        limitHang = hardwareMap.get(TouchSensor.class, "LimitHang");
        limitSlide = hardwareMap.get(TouchSensor.class, "LimitSlide");
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setTargetPosition(motor.getTargetPosition());
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public class ToAngle implements Action {
        private boolean initialized = false;
        private double targetAngle;
        private double currentAngle;
        public ToAngle(double angle) {
            //multiply the the angle by the encoder resolution divided by 360
            targetAngle = angle * (1425.1 / 360);
        }

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {

                currentAngle = motor.getCurrentPosition();

                motor.setTargetPosition((int) targetAngle);
                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motor.setPower(0.5);
                initialized = true;
            }
            currentAngle = motor.getCurrentPosition();

            return motor.isBusy();
        }
    }

    public Action toAngle(double angle) {
        return new ToAngle(angle);
    }

    public class ToUpRightPosition implements Action{

        int currentPos;
        boolean initialized = false;
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if(!initialized){
                motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                motor.setPower(-0.5);

            }
            currentPos = motor.getCurrentPosition();
            if(!limitHang.isPressed()){
                return true;
            }
            motor.setTargetPosition(currentPos);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setPower(0.5);
            return false;
        }
    }

    public class ToOutPosition implements Action{

        int currentPos;
        boolean initialized = false;
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if(!initialized){
                motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                motor.setPower(0.5);

            }
            currentPos = motor.getCurrentPosition();
            if(!limitSlide.isPressed()){
                return true;
            }
            motor.setTargetPosition(currentPos + (1425/360) * 8);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            return false;
        }
    }

    public Action toOutPosition(){
        return new ToOutPosition();
    }

    public Action toUprightPosition(){
        return new ToUpRightPosition();
    }

}

