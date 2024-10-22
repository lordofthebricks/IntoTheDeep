package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class hardware {

    private Teleop myopmode;
    public DcMotor LeftFront;
    public DcMotor RightFront;
    public DcMotor LeftBack;
    public DcMotor RightBack;
    public DcMotor Lift;
    public DcMotor Uplift;
    public DcMotor Arm;
    public Servo Claw;
    public Servo Wrist1;
    public Servo Wrist2;


    public void init(@NonNull HardwareMap hwMp) {

    LeftFront = hwMp.get(DcMotor.class,"FrontL");
    RightFront = hwMp.get(DcMotor.class,"FrontR");
    LeftBack = hwMp.get(DcMotor.class,"BackL");
    RightBack = hwMp.get(DcMotor.class,"BackR");
    Lift = hwMp.get(DcMotor.class,"Lift");
    Uplift = hwMp.get(DcMotor.class, "Uplift");
    Arm = hwMp.get(DcMotor.class, "Arm");
    Claw = hwMp.get(Servo.class, "Claw");
    Wrist1 = hwMp.get(Servo.class, "Wrist1");
    Wrist2 = hwMp.get(Servo.class, "Wrist2");


    LeftFront.setDirection(DcMotorSimple.Direction.REVERSE);
    LeftBack.setDirection(DcMotorSimple.Direction.REVERSE);

    }
}
