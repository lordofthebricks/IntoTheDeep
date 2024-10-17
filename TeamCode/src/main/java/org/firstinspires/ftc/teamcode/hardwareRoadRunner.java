package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class hardwareRoadRunner {


    public DcMotor LeftFront;
    public DcMotor RightFront;
    public DcMotor LeftBack;
    public DcMotor RightBack;



    public void init(@NonNull HardwareMap hwMp) {

        LeftFront = hwMp.get(DcMotor.class,"FrontL");
        RightFront = hwMp.get(DcMotor.class,"FrontR");
        LeftBack = hwMp.get(DcMotor.class,"BackL");
        RightBack = hwMp.get(DcMotor.class,"BackR");


        LeftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        LeftBack.setDirection(DcMotorSimple.Direction.REVERSE);

    }
    public class Claw {
        private Servo claw;

        public Claw(HardwareMap hwmap){
            claw = hwmap.get(Servo.class, "claw");
        }
        public class CloseClaw implements Action  {

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                return false;
            }
        }
    }


}
