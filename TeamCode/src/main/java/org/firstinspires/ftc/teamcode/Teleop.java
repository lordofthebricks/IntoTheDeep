package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Teleop extends OpMode {

private hardware robot;
double FrontSpeed = 0.8;
double BackSpeed = -0.8;
double MaxSpeed = 1;
double Rest = 0;

@Override
    public void init() {

    robot = new hardware();

}

    @Override
    public void loop() {

    if (gamepad1.left_stick_y == 1) {
        robot.LeftFront.setPower(FrontSpeed);
        robot.LeftBack.setPower(FrontSpeed);
    } else if (gamepad1.left_stick_y == 1) {
        robot.LeftFront.setPower(Rest);
        robot.LeftBack.setPower(Rest);
    }

    if (gamepad1.left_stick_y == -1) {
        robot.LeftFront.setPower(BackSpeed);
        robot.LeftBack.setPower(BackSpeed);
    } else if (gamepad1.left_stick_y == -1) {
        robot.LeftFront.setPower(Rest);
        robot.LeftBack.setPower(Rest);
    }


    }
}


