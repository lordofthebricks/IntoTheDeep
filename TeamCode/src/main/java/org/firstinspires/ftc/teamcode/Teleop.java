package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

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
    robot.init(hardwareMap);
}

    @Override
    public void loop() {

        if (gamepad1.left_stick_y == 1) {
            robot.LeftFront.setPower(FrontSpeed);
            robot.LeftBack.setPower(FrontSpeed);
        } else if (gamepad1.left_stick_y == 0) {
            robot.LeftFront.setPower(Rest);
            robot.LeftBack.setPower(Rest);
        }

        if (gamepad1.left_stick_y == -1) {
            robot.LeftFront.setPower(BackSpeed);
            robot.LeftBack.setPower(BackSpeed);
        } else if (gamepad1.left_stick_y == 0) {
            robot.LeftFront.setPower(Rest);
            robot.LeftBack.setPower(Rest);
        }

            if (gamepad1.right_stick_y == 1) {
                robot.RightFront.setPower(FrontSpeed);
                robot.RightBack.setPower(FrontSpeed);
            } else if (gamepad1.right_stick_y == 0) {
                robot.RightFront.setPower(Rest);
                robot.RightBack.setPower(Rest);
            }

            if (gamepad1.right_stick_y == -1) {
                robot.RightFront.setPower(BackSpeed);
                robot.RightBack.setPower(BackSpeed);
            } else if (gamepad1.right_stick_y == 0) {
                robot.RightFront.setPower(Rest);
                robot.RightBack.setPower(Rest);
            }

        if (gamepad1.dpad_left == true) {
            robot.LeftFront.setPower(BackSpeed);
            robot.LeftBack.setPower(FrontSpeed);
            robot.RightFront.setPower(FrontSpeed);
            robot.RightBack.setPower(BackSpeed);
        } else if (gamepad1.dpad_left == false) {
            robot.LeftFront.setPower(Rest);
            robot.LeftBack.setPower(Rest);
            robot.RightFront.setPower(Rest);
            robot.RightBack.setPower(Rest);
        }

        if (gamepad1.dpad_right == true) {
            robot.LeftFront.setPower(FrontSpeed);
            robot.LeftBack.setPower(BackSpeed);
            robot.RightFront.setPower(BackSpeed);
            robot.RightBack.setPower(FrontSpeed);
        } else if (gamepad1.dpad_right == false) {
            robot.LeftFront.setPower(Rest);

            robot.LeftBack.setPower(Rest);
            robot.RightFront.setPower(Rest);
            robot.RightBack.setPower(Rest);
        }



        //if (gamepad1)
    }
}


