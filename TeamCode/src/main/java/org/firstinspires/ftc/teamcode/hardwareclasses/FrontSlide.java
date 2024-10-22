package org.firstinspires.ftc.teamcode.hardwareclasses;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;

public class FrontSlide {
    DcMotor slide;

    double WheelInchesPerRotation = 4.72441;
    double MotorTicksPerRotation = 537.7;
    double InchesPerTick = 4.722441 / 537.7;

    public FrontSlide(DcMotor slide){
        this.slide = slide;
    }

    public class SlideOut implements Action {
        private boolean initialized = false;
        private double inches;

        public SlideOut(double inches){
            slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.inches = inches;
        }



        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (!initialized){
                slide.setTargetPosition((int) (inches * InchesPerTick));
                slide.setPower(0.4);
                initialized = true;
            }

            if (slide.getCurrentPosition() < slide.getTargetPosition()){
                return true;
            }
            slide.setPower(0);
            return false;
        }
    }

    public class SlideIn implements Action {
        private boolean initialized = false;
        private double inches;

        public SlideIn(double inches){
            slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            this.inches = inches;
        }



        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (!initialized){
                slide.setTargetPosition( -((int) (inches * InchesPerTick)));
                slide.setPower(0.4);
                initialized = true;
            }

            if (slide.getCurrentPosition() > slide.getTargetPosition()){
                return true;
            }
            slide.setPower(0);
            return false;
        }
    }


    public Action slideOut(double inches) {
        return new SlideOut(inches);
    }
    public Action slideIn(double inches) {
        return new SlideIn(inches);
    }

}
