package org.firstinspires.ftc.teamcode.bombadil1.hardwareclasses;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class FrontSlide {
    DcMotor slide;

    double WheelInchesPerRotation = 4.72441;
    double MotorTicksPerRotation = 537.7;
    double InchesPerTick = MotorTicksPerRotation / WheelInchesPerRotation;

    public FrontSlide(HardwareMap hwMp) {
        slide = hwMp.get(DcMotor.class, "Arm");
//        slide.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public class SlideOut implements Action {
        private boolean initialized = false;
        private double inches;

        public SlideOut(double inches) {
            slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            this.inches = inches;
        }


        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (!initialized) {
                int targetInches = (int) (slide.getCurrentPosition() + (inches * InchesPerTick));
                slide.setTargetPosition(targetInches);
                slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                slide.setTargetPosition(targetInches);
                slide.setPower(0.4);
                initialized = true;
            }

            if (slide.getCurrentPosition() < slide.getTargetPosition()) {
                return true;
            } else {
                slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                slide.setPower(0);
                slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                return false;
            }
        }
    }

    public class SlideIn implements Action {
        private boolean initialized = false;
        private double inches;

        public SlideIn(double inches) {
            slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            this.inches = inches;
        }


        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (!initialized) {
                int targetInches = (int) (slide.getCurrentPosition() - (inches * InchesPerTick));
                slide.setTargetPosition(targetInches);
                slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                slide.setTargetPosition(targetInches);
                slide.setPower(0.4);
                initialized = true;
            }

            if (slide.getCurrentPosition() > slide.getTargetPosition()) {
                return true;
            } else {
                slide.setPower(0);
                slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                return false;

            }
        }




    }

    public Action slideOut(double inches) {
        return new SlideOut(inches);
    }

    public Action slideIn(double inches) {
        return new SlideIn(inches);
    }
}
