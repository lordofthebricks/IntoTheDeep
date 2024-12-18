package org.firstinspires.ftc.teamcode.bombadil2;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp
public class BombadilTeleop extends OpMode {

    final double MAX_SPEED = 0.8;
    final double STRAFE_SPEED = 0.5;
    BombadilHardware robot = new BombadilHardware();



    @Override
    public void init() {
        if (robot.init(hardwareMap) != 1 ){
            telemetry.addLine("Issue Initalizing");
            telemetry.update();
            stop();
        }

    }


    @Override
    public void loop() {

        if (gamepad1.left_stick_y >= 0.2) {
            robot.leftFront.setPower(gamepad1.left_stick_y * MAX_SPEED);
            robot.leftBack.setPower(gamepad1.left_stick_y * MAX_SPEED);
        } else if (gamepad1.left_stick_y <= -0.2) {
            robot.leftFront.setPower(gamepad1.left_stick_y * MAX_SPEED);
            robot.leftBack.setPower(gamepad1.left_stick_y * MAX_SPEED);
        } else {
            robot.leftFront.setPower(0);
            robot.leftBack.setPower(0);
        }
        //
        if (gamepad1.right_stick_y >= 0.2) {
            robot.rightFront.setPower(gamepad1.right_stick_y * MAX_SPEED);
            robot.rightBack.setPower(gamepad1.right_stick_y * MAX_SPEED);
        } else if (gamepad1.right_stick_y <= -0.2) {
            robot.rightFront.setPower(gamepad1.right_stick_y * MAX_SPEED);
            robot.rightBack.setPower(gamepad1.right_stick_y * MAX_SPEED);
        } else {
            robot.rightBack.setPower(0);
            robot.rightFront.setPower(0);
        }

        if (gamepad1.left_stick_x == -1) {
            robot.leftFront.setPower(STRAFE_SPEED);
            robot.leftBack.setPower(-STRAFE_SPEED);
            robot.rightFront.setPower(-STRAFE_SPEED);
            robot.rightBack.setPower(STRAFE_SPEED);
        } else if (gamepad1.right_stick_x == 1) {
            robot.leftFront.setPower(-STRAFE_SPEED);
            robot.leftBack.setPower(STRAFE_SPEED);
            robot.rightFront.setPower(STRAFE_SPEED);
            robot.rightBack.setPower(-STRAFE_SPEED);
        }
    }
}
