package org.firstinspires.ftc.teamcode.bombadil2;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DigitalChannelImpl;
import com.qualcomm.robotcore.hardware.DistanceSensor;
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
     DistanceSensor distance1;
     DistanceSensor distance2;

    private final double SLIDE_PULLY_DIAMETER = 1.504;

    private final double SLIDE_TICKS_PER_REV = 384.5;

    private final double SLIDE_TICKS_PER_INCH = SLIDE_TICKS_PER_REV/(Math.PI * SLIDE_PULLY_DIAMETER);
    public final int MAX_SLIDE_POSITION = (int) (35 * SLIDE_TICKS_PER_INCH);

    public int init(HardwareMap hwMap){

        try {

            leftFront = hwMap.get(DcMotorEx.class, "LeftFront");
            leftBack = hwMap.get(DcMotorEx.class, "LeftBack");
            rightBack = hwMap.get(DcMotorEx.class, "rightBack");
            rightFront = hwMap.get(DcMotorEx.class, "rightFront");
            tilt = hwMap.get(DcMotorEx.class, "Tilt");
            slide = hwMap.get(DcMotorEx.class, "Slide");
            wrist = hwMap.get(Servo.class,"Wrist");
            intake1 = hwMap.get(CRServo.class, "Intake1");
            intake2 = hwMap.get(CRServo.class, "Intake2");

            distance1 = hwMap.get(DistanceSensor.class,"distance1");
            distance2 = hwMap.get(DistanceSensor.class, "distance2");

//            rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
//            rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
            leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
            limit = hwMap.get(DigitalChannel.class, "Limit");

            limit.setMode(DigitalChannel.Mode.INPUT);

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
