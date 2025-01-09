package org.firstinspires.ftc.teamcode.bombadil2.rrhardware;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.bombadil2.BombadilHardware;

public class Slide {
    private DcMotorEx motor;

    public Slide(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotorEx.class, "Slide");
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setTargetPosition(motor.getCurrentPosition());
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public class Out implements Action {
        private boolean initialized = false;
        private double inches;
        private int currentPos;
        public Out(double inches) {
            this.inches = Math.abs(inches);
        }

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                currentPos = motor.getCurrentPosition();
                motor.setTargetPosition( currentPos + (int) (BombadilHardware.SLIDE_TICKS_PER_INCH * inches));
                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motor.setPower(0.6);
                initialized = true;
            }


            return true;
        }
    }

    public Action out(double inches) {
        return new Out(inches);
    }

    public class In implements Action {
        private boolean initialized = false;
        private double inches;
        private int currentPos;
        public In(double inches) {
            this.inches = -Math.abs(inches);
        }

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                currentPos = motor.getCurrentPosition();
                motor.setTargetPosition( currentPos + (int) (BombadilHardware.SLIDE_TICKS_PER_INCH * inches));
                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motor.setPower(0.6);
                initialized = true;
            }


            return true;
        }
    }

    public Action in(double inches) {
        return new In(inches);
    }


}

