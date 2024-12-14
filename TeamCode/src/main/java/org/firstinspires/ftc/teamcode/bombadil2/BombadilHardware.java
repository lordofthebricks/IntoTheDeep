package org.firstinspires.ftc.teamcode.bombadil2;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DigitalChannelImpl;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class BombadilHardware {

    DcMotorEx leftFront;
    DcMotorEx leftBack;
    DcMotorEx rightBack;
    DcMotorEx rightFront;

    DcMotorEx tilt;
    DcMotorEx slide;

    DigitalChannelImpl Limit;


    public int init(HardwareMap hwMap){

        try {

            leftFront = hwMap.get(DcMotorEx.class, "LeftFront");
            leftBack = hwMap.get(DcMotorEx.class, "LeftBack");
            rightBack = hwMap.get(DcMotorEx.class, "rightBack");
            rightFront = hwMap.get(DcMotorEx.class, "rightFront");
            tilt = hwMap.get(DcMotorEx.class, "Tilt");
            slide = hwMap.get(DcMotorEx.class, "Slide");

            leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
            leftBack.setDirection(DcMotorSimple.Direction.REVERSE);

            Limit = hwMap.get(DigitalChannelImpl.class, "");


        } catch (Exception e) {
            return 0;
        }
            Limit.setMode(DigitalChannel.Mode.INPUT);

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
