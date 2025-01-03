package org.firstinspires.ftc.teamcode.bombadil2.rrhardware;


import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    private CRServo intake1;
    private CRServo intake2;

    public Intake(HardwareMap hardwareMap) {
        intake1 = hardwareMap.get(CRServo.class, "Intake1");
        intake2 = hardwareMap.get(CRServo.class, "Intake2");
    }

    public class TakeIn implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                intake1.setPower(-0.8);
                intake2.setPower(0.8);
                initialized = true;
            }

            return false;
        }
    }

    public Action takeIn() {
        return new TakeIn();
    }

    public class PutOut implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                intake1.setPower(0.8);
                intake2.setPower(-0.8);
                initialized = true;
            }

            return false;
        }
    }

    public Action putOut() {
        return new PutOut();
    }

    public class Stop implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                intake1.setPower(0);
                intake2.setPower(0);
                initialized = true;
            }

            return false;
        }
    }

    public Action stop() {
        return new Stop();
    }

}

