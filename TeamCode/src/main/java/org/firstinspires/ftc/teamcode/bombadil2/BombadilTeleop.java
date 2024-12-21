package org.firstinspires.ftc.teamcode.bombadil2;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name = "Basic Teleop Bombadil 2.0")
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
            robot.rightFront.setPower(gamepad1.left_stick_y * MAX_SPEED);
            robot.rightBack.setPower(gamepad1.left_stick_y * MAX_SPEED);
        } else if (gamepad1.left_stick_y <= -0.2) {
            robot.rightFront.setPower(gamepad1.left_stick_y * MAX_SPEED);
            robot.rightBack.setPower(gamepad1.left_stick_y * MAX_SPEED);
        } else {
            robot.rightFront.setPower(0);
            robot.rightBack.setPower(0);
        }
        //
        if (gamepad1.right_stick_y >= 0.2) {
            robot.leftFront.setPower(gamepad1.right_stick_y * MAX_SPEED);
            robot.leftBack.setPower(gamepad1.right_stick_y * MAX_SPEED);
        } else if (gamepad1.right_stick_y <= -0.2) {
            robot.leftFront.setPower(gamepad1.right_stick_y * MAX_SPEED);
            robot.leftBack.setPower(gamepad1.right_stick_y * MAX_SPEED);
        } else {
            robot.leftFront.setPower(0);
            robot.leftBack.setPower(0);
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

        if (gamepad1.right_bumper){
            robot.tilt.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.tilt.setPower(0.3);
        }else if (gamepad1.right_trigger == 1){
            robot.tilt.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.tilt.setPower(-0.3);
        }else {
            robot.tilt.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.tilt.setTargetPosition(robot.tilt.getCurrentPosition());
            robot.tilt.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.tilt.setTargetPosition(robot.tilt.getCurrentPosition());
            robot.tilt.setPower(0.3);
        }

        if (gamepad1.left_bumper){
            robot.slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.slide.setPower(0.3);
        }else if (gamepad1.left_trigger == 1){
            robot.slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.slide.setPower(-0.3);
        }else {
            robot.slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.slide.setTargetPosition(robot.slide.getCurrentPosition());
            robot.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.slide.setPower(0.3);
        }

        if (gamepad1.a){
            robot.intake1.setPower(0.8);
            robot.intake2.setPower(-0.8);
        } else if (gamepad1.b) {
            robot.intake1.setPower(-0.8);
            robot.intake2.setPower(0.8);
        }else{
            robot.intake1.setPower(0);
            robot.intake2.setPower(0);
        }

        if (gamepad1.x ){
            robot.wrist.setPosition(1);
        }

        if(gamepad1.y ){
            robot.wrist.setPosition(0.3);
        }

    }
}
