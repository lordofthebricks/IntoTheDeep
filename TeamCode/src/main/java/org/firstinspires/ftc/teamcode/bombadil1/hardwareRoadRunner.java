package org.firstinspires.ftc.teamcode.bombadil1;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


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


}
