package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;



public class hardware {

    private Teleop myopmode;
    public DcMotor LeftFront;
    public DcMotor RightFront;
    public DcMotor LeftBack;
    public DcMotor RightBack;
    public DcMotor Lift;

    public void init(@NonNull HardwareMap hwMp) {

    LeftFront = hwMp.get(DcMotor.class,"FrontL");
    RightFront = hwMp.get(DcMotor.class,"FrontR");
    LeftBack = hwMp.get(DcMotor.class,"BackL");
    RightBack = hwMp.get(DcMotor.class,"BackR");
    Lift = hwMp.get(DcMotor.class,"Lift");

    LeftFront.setDirection(DcMotorSimple.Direction.REVERSE);
    LeftBack.setDirection(DcMotorSimple.Direction.REVERSE);

    }
}
