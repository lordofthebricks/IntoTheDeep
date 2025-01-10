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
import com.qualcomm.robotcore.hardware.TouchSensor;

public class BombadilHardware {

   public DcMotorEx leftFront;
   public DcMotorEx leftBack;
   public DcMotorEx rightBack;
   public DcMotorEx rightFront;
   public TouchSensor LimitHang;
   public TouchSensor LimitSlide;

    DcMotorEx tilt;
    DcMotorEx slide;

    CRServo intake1;
    CRServo intake2;
    Servo wrist;
    TouchSensor limit;
    //DistanceSensor distance1;
    //DistanceSensor distance2;

    private static final double SLIDE_PULLY_DIAMETER = 1.504;

    private static final double SLIDE_TICKS_PER_REV = 384.5;
    public static final double TILT_TICKS_PER_REV = 1425.1;
    public static final double SLIDE_TICKS_PER_INCH = SLIDE_TICKS_PER_REV/(Math.PI * SLIDE_PULLY_DIAMETER);
    public static final double TILT_TICKS_PER_INCH = TILT_TICKS_PER_REV * 0.2/(Math.PI * 1.0);
    public static final int MAX_SLIDE_POSITION = (int) (35 * SLIDE_TICKS_PER_INCH);

    public int init(HardwareMap hwMap){

        try {

            LimitHang = hwMap.get(TouchSensor.class, "LimitHang");
            LimitSlide = hwMap.get(TouchSensor.class, "LimitSlide");
            //LimitHang = hwMap.get(DigitalChannel.class, "LimitHang");
            //LimitSlide = hwMap.get(DigitalChannel.class, "LimitSlide");
            leftFront = hwMap.get(DcMotorEx.class, "LeftFront");
            leftBack = hwMap.get(DcMotorEx.class, "LeftBack");
            rightBack = hwMap.get(DcMotorEx.class, "rightBack");
            rightFront = hwMap.get(DcMotorEx.class, "rightFront");
            tilt = hwMap.get(DcMotorEx.class, "Tilt");
            slide = hwMap.get(DcMotorEx.class, "Slide");
            wrist = hwMap.get(Servo.class,"Wrist");
            intake1 = hwMap.get(CRServo.class, "Intake1");
            intake2 = hwMap.get(CRServo.class, "Intake2");


            rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
            rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
            leftFront.setDirection(DcMotorSimple.Direction.REVERSE);

            rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        } catch (Exception e) {
            throw e;

        }

        return 1;
    }

}
