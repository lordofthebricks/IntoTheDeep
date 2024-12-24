package org.firstinspires.ftc.teamcode.bombadil2;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DigitalChannelImpl;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DigitalChannel;

public class BombadilHardware {

    DcMotorEx leftFront;
    DcMotorEx leftBack;
    DcMotorEx rightBack;
    DcMotorEx rightFront;

    DcMotorEx tilt;
    DcMotorEx slide;

    CRServo intake1;
    CRServo intake2;
    Servo wrist;
    DigitalChannel limit;


    public int init(HardwareMap hwMap){

        try {

            leftFront = hwMap.get(DcMotorEx.class, "LeftFront");
            leftBack = hwMap.get(DcMotorEx.class, "LeftBack");
            rightBack = hwMap.get(DcMotorEx.class, "rightBack");
            rightFront = hwMap.get(DcMotorEx.class, "rightFront");
            tilt = hwMap.get(DcMotorEx.class, "Tilt");
            slide = hwMap.get(DcMotorEx.class, "Slide");

            intake1 = hwMap.get(CRServo.class, "Intake1");
            intake2 = hwMap.get(CRServo.class, "Intake2");
            wrist = hwMap.get(Servo.class,"Wrist");
            limit = hwMap.get(DigitalChannel.class,"Limit");


//            rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
//            rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
            leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
//            Limit = hwMap.get(DigitalChannel.class, "");


        } catch (Exception e) {
            return 0;
        }
//            Limit.setMode(DigitalChannel.Mode.INPUT);

        return 1;
    }

    public int initRR(HardwareMap hwMap){

        try {
            tilt = hwMap.get(DcMotorEx.class, "Arm");
            slide = hwMap.get(DcMotorEx.class, "Slide");
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }


}
