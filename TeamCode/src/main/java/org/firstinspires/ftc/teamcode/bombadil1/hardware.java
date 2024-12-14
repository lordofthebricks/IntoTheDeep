package org.firstinspires.ftc.teamcode.bombadil1;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.CRServo;
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
    public DcMotor Arm;
    public Servo Bucket;
    public CRServo Intake1;
    public CRServo Intake2;
    public Servo Wrist2;
    public Servo HangArm;
    public DcMotor Winch1;
    public DcMotor Winch2;


    public void init(@NonNull HardwareMap hwMp) {



    LeftFront = hwMp.get(DcMotor.class,"FrontL");
    RightFront = hwMp.get(DcMotor.class,"FrontR");
    LeftBack = hwMp.get(DcMotor.class,"BackL");
    RightBack = hwMp.get(DcMotor.class,"BackR");
    Lift = hwMp.get(DcMotor.class,"Lift");
    Arm = hwMp.get(DcMotor.class, "Arm");
    Bucket = hwMp.get(Servo.class, "Bucket");
    Intake1 = hwMp.get(CRServo.class, "Intake1");
    Intake2 = hwMp.get(CRServo.class, "Intake2");
    Wrist2 = hwMp.get(Servo.class, "Wrist2");
    Winch1 = hwMp.get(DcMotor.class, "Winch");
    Winch2 = hwMp.get(DcMotor.class,"HeadingPod");
    HangArm = hwMp.get(Servo.class, "HangArm");
//    Arm.setDirection(DcMotorSimple.Direction.REVERSE);
    Lift.setDirection(DcMotorSimple.Direction.REVERSE);
    Intake1.setDirection(CRServo.Direction.REVERSE);

    LeftFront.setDirection(DcMotorSimple.Direction.REVERSE);
    LeftBack.setDirection(DcMotorSimple.Direction.REVERSE);
    RightBack.setDirection(DcMotorSimple.Direction.FORWARD);
    RightFront.setDirection(DcMotorSimple.Direction.FORWARD);

    Lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    LeftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    LeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    RightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    RightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }
}
