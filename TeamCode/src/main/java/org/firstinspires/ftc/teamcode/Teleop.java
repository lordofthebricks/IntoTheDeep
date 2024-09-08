package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Teleop extends OpMode {

private hardware robot;
double FrontSpeed = 0.8;
double BackSpeed = 1;


@Override
    public void init() {

    robot = new hardware();

}

    @Override
    public void loop() {


    }
}


