package org.firstinspires.ftc.teamcode.bombadil2.rrhardware;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Tilt {
    private DcMotorEx motor;

    public Tilt(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotorEx.class, "Tilt");
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public class ToAngle implements Action {
        private boolean initialized = false;
        private double targetAngle;
        private double currentAngle;
        public ToAngle(double angle) {
            //multiply the the angle by the encoder resolution divided by 360
            targetAngle = angle * (537.7 / 360);
        }

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {

                currentAngle = motor.getCurrentPosition();

                motor.setTargetPosition((int) targetAngle);

                motor.setPower(0.5);
                initialized = true;
            }


            return targetAngle < (int) (currentAngle - motor.getTargetPositionTolerance());
        }
    }

    public Action toAngle(double angle) {
        return new ToAngle(angle);
    }
}

