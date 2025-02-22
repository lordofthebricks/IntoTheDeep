package org.firstinspires.ftc.teamcode.bombadil2;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;


@TeleOp(name = "Basic Teleop Bombadil 2.0")
public class BombadilTeleop extends OpMode {

    final double MAX_SPEED = 0.8;
    final double STRAFE_SPEED = 0.9;
    int slideRestingPos = 0;
    int slideMaxPosition;
    int slideCurrentPosition;
    BombadilHardware robot = new BombadilHardware();
    int normal = 1;

    @Override
    public void init() {
        if (robot.init(hardwareMap) != 1) {
            telemetry.addLine("Issue Initalizing");
            telemetry.update();
            stop();
        }
        robot.slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideRestingPos = robot.slide.getCurrentPosition();
        slideMaxPosition = slideRestingPos - (BombadilHardware.MAX_SLIDE_POSITION);
        robot.slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.tilt.setTargetPosition(robot.tilt.getCurrentPosition());
        robot.slide.setTargetPosition(robot.slide.getCurrentPosition());
    }


    @Override
    public void loop() {
        telemetry.clearAll();
        telemetry.addData("limit switch", robot.LimitHang.isPressed());
        telemetry.addData("Slide Length", robot.slide.getCurrentPosition() / BombadilHardware.SLIDE_TICKS_PER_INCH);
        telemetry.update();
        if (gamepad1.start) {
            normal = 0;
           }

        if (gamepad1.back) {
            normal = 1;
        }



        // if ()

        if (normal == 1) {

            if (gamepad1.right_stick_y >= 0.2) {
                robot.rightFront.setPower(gamepad1.right_stick_y * MAX_SPEED);
                robot.rightBack.setPower(gamepad1.right_stick_y * MAX_SPEED);
            } else if (gamepad1.right_stick_y <= -0.2) {
                robot.rightFront.setPower(gamepad1.right_stick_y * MAX_SPEED);
                robot.rightBack.setPower(gamepad1.right_stick_y * MAX_SPEED);
            } else {
                robot.rightFront.setPower(0);
                robot.rightBack.setPower(0);
            }

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

            //dpad precision controls
            if (gamepad1.dpad_up) {
                robot.leftFront.setPower(-0.4);
                robot.leftBack.setPower(-0.4);
                robot.rightFront.setPower(-0.4);
                robot.rightBack.setPower(-0.4);
            } else if (gamepad1.dpad_down) {
                robot.leftFront.setPower(0.4);
                robot.leftBack.setPower(0.4);
                robot.rightFront.setPower(0.4);
                robot.rightBack.setPower(0.4);
            }

            //dpad strafing controls
            if (gamepad1.dpad_right) {
                robot.leftFront.setPower(-STRAFE_SPEED);
                robot.leftBack.setPower(STRAFE_SPEED);
                robot.rightFront.setPower(STRAFE_SPEED);
                robot.rightBack.setPower(-STRAFE_SPEED);
            } else if (gamepad1.dpad_left) {
                robot.leftFront.setPower(STRAFE_SPEED);
                robot.leftBack.setPower(-STRAFE_SPEED);
                robot.rightFront.setPower(-STRAFE_SPEED);
                robot.rightBack.setPower(STRAFE_SPEED);
            }

            /*if (gamepad1.right_bumper) {
                robot.tilt.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.tilt.setPower(0.4);
                robot.tilt.setTargetPosition(robot.tilt.getCurrentPosition());
            } else if (gamepad1.right_trigger == 1) {
                robot.tilt.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.tilt.setPower(-0.4);
                robot.tilt.setTargetPosition(robot.tilt.getCurrentPosition());
            } else {
                robot.tilt.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.tilt.setPower(0.5);
            }*/

//left bumper backup code
            /*if (gamepad1.left_bumper) {
                robot.slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.slide.setPower(0.6);
                robot.slide.setTargetPosition(robot.slide.getCurrentPosition());
            } else if (gamepad1.left_trigger == 1) {
                robot.slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.slide.setPower(-0.6);
                robot.slide.setTargetPosition(robot.slide.getCurrentPosition());
            } else {


                robot.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.slide.setPower(0);
            }


            //left bumper = slide

            //a = out
            //b = in
            if (gamepad1.a) {
                robot.intake1.setPower(-0.8);
                robot.intake2.setPower(0.8);
            } else if (gamepad1.b) {
                robot.intake1.setPower(0.8);
                robot.intake2.setPower(-0.8);
            } else {
                robot.intake1.setPower(0);
                robot.intake2.setPower(0);
            }

            //x = bring
            if (gamepad1.x) {
                robot.wrist.setPosition(1);
            }

            //y = deploy
            if (gamepad1.y) {
                robot.wrist.setPosition(0.3);
            }

            //position for sample scoring and specimen pickup
            if (gamepad1.left_stick_button) {
                robot.wrist.setPosition(0.5);
            }

            //position for scoring specimens
            if (gamepad1.right_stick_button) {
                robot.wrist.setPosition(0.8);
            }

            if (gamepad1.right_stick_button) {
                robot.tilt.setPower(0.3);

            }*/

        } else if (normal == 0) {
            if (gamepad1.left_stick_y >= 0.2) {
                robot.leftFront.setPower(0.2);
                robot.leftBack.setPower(0.2);
                robot.rightBack.setPower(0.2);
                robot.rightFront.setPower(0.2);
            } else if (gamepad1.left_stick_y <= -0.2) {
                robot.leftFront.setPower(-0.2);
                robot.leftBack.setPower(-0.2);
                robot.rightBack.setPower(-0.2);
                robot.rightFront.setPower(-0.2);
            } else if (gamepad1.left_stick_y == 0) {
                robot.leftFront.setPower(0);
                robot.leftBack.setPower(0);
                robot.rightBack.setPower(0);
                robot.rightFront.setPower(0);
            }

            if (gamepad1.left_stick_x == -1) {
                robot.leftFront.setPower(STRAFE_SPEED);
                robot.leftBack.setPower(-STRAFE_SPEED);
                robot.rightFront.setPower(-STRAFE_SPEED);
                robot.rightBack.setPower(STRAFE_SPEED);
            } else if (gamepad1.left_stick_x == 1) {
                robot.leftFront.setPower(-STRAFE_SPEED);
                robot.leftBack.setPower(STRAFE_SPEED);
                robot.rightFront.setPower(STRAFE_SPEED);
                robot.rightBack.setPower(-STRAFE_SPEED);
            }

        }

        if (gamepad1.a) {
            robot.intake1.setPower(-0.8);
            robot.intake2.setPower(0.8);
        } else if (gamepad1.b) {
            robot.intake1.setPower(0.8);
            robot.intake2.setPower(-0.8);
        } else {
            robot.intake1.setPower(0);
            robot.intake2.setPower(0);
        }

        if (gamepad1.right_bumper) {
            robot.tilt.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.tilt.setPower(0.4);
            robot.tilt.setTargetPosition(robot.tilt.getCurrentPosition());
        } else if (gamepad1.right_trigger == 1) {
            robot.tilt.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.tilt.setPower(-0.4);
            robot.tilt.setTargetPosition(robot.tilt.getCurrentPosition());
        } else {
            robot.tilt.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.tilt.setPower(0.5);
        }

//left bumper backup code
        if(robot.LimitHang.isPressed()){
//            telemetry.clearAll();
//
//            telemetry.addLine("Length Limit off");
//            telemetry.update();
            if (gamepad1.left_bumper) {
                robot.slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.slide.setPower(0.6);
                robot.slide.setTargetPosition(robot.slide.getCurrentPosition());
            } else if (gamepad1.left_trigger == 1) {
                robot.slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.slide.setPower(-0.6);
                robot.slide.setTargetPosition(robot.slide.getCurrentPosition());
            } else {


                robot.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.slide.setPower(0);
            }
        }else{
//            telemetry.clearAll();
//
//            telemetry.addLine("Length Limit On");
//            telemetry.update();
            if (gamepad1.left_bumper) {
                robot.slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.slide.setPower(0.6);
                robot.slide.setTargetPosition(robot.slide.getCurrentPosition());
            } else if (gamepad1.left_trigger == 1 && robot.slide.getCurrentPosition() > slideMaxPosition) {
                robot.slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.slide.setPower(-0.6);
                robot.slide.setTargetPosition(robot.slide.getCurrentPosition());
            } else {
                if(robot.slide.getTargetPosition() < slideMaxPosition){
                    robot.slide.setTargetPosition(slideMaxPosition);
                }

                robot.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.slide.setPower(0);
            }
        }


        if (gamepad1.a) {
            robot.intake1.setPower(-0.8);
            robot.intake2.setPower(0.8);
        } else if (gamepad1.b) {
            robot.intake1.setPower(0.8);
            robot.intake2.setPower(-0.8);
        } else {
            robot.intake1.setPower(0);
            robot.intake2.setPower(0);
        }

        //x = bring
        if (gamepad1.x) {
            robot.wrist.setPosition(1);
        }

        //y = deploy
        if (gamepad1.y) {
            robot.wrist.setPosition(0.3);
        }

        //position for sample scoring and specimen pickup
        if (gamepad1.left_stick_button) {
            robot.wrist.setPosition(0.5);
        }

        //position for scoring specimens
        if (gamepad1.right_stick_button) {
            robot.wrist.setPosition(0.8);
        }

        if (gamepad1.right_stick_button) {
            robot.tilt.setPower(0.3);
        }
        if (gamepad1.back){
            encoderSlide( 0.7, -18);
        }
        
    }

    public void encoderSlide ( double speed, double slideInches){
        int newslideTarget;

            slideInches = Math.abs(slideInches);



            newslideTarget = slideRestingPos + (int) (slideInches * BombadilHardware.SLIDE_TICKS_PER_INCH);
            robot.slide.setTargetPosition(newslideTarget);

            robot.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            robot.slide.setPower(Math.abs(speed));



    }

}




