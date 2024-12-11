package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardwareclasses.Claw;
import org.firstinspires.ftc.teamcode.hardwareclasses.FrontSlide;
import org.firstinspires.ftc.teamcode.hardwareclasses.Lift;

import java.util.ArrayList;
import java.util.List;
@TeleOp
public class RRTeleop extends OpMode {

    FrontSlide frontSlide;
    Lift lift;
    Claw claw;
    private FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();
    private hardware robot;
    double FrontSpeed = 1;
    double BackSpeed = -1;
    double MaxSpeed = 1;
    double Rest = 0;
    final double MAX_SPEED = 0.8;
    private double outInchs = 18;
    private boolean firstRun = true;
    private double inInchs = 16;
    private double WristPosition1 = 0.5;
    private double WristPosition2 = 0.5;

    @Override
    public void init() {
        robot = new hardware();
        robot.init(hardwareMap);
        frontSlide = new FrontSlide(hardwareMap);
        lift = new Lift(hardwareMap);
        firstRun = true;
        outInchs = 18;
        claw = new Claw(hardwareMap, robot.LeftFront, robot.LeftBack, robot.RightFront, robot.RightBack);
    }

    @Override
    public void loop() {
        TelemetryPacket packet = new TelemetryPacket();

        // updated based on gamepads
     /*   if (gamepad1.a) {
            if(!firstRun){
                outInchs = 16;
            }
            firstRun = false;
            runningActions.add(new SequentialAction(
                    frontSlide.slideOut(outInchs),
                    claw.ReadyToGrab(),
//                    frontSlide.slideOut( 4),
                    new SleepAction(1),
                    claw.Grab(),
                    new SleepAction(0.5),
                    claw.BringUp(),
                    new SleepAction(0.5),
                    frontSlide.slideIn(inInchs),
                    new InstantAction(() -> robot.Claw.setPosition(0.70))
//                    frontSlide.slideOut(2)


            ));
        }

        if (gamepad1.b) {
            runningActions.add(new SequentialAction(
                    lift.upLift(48),
                    new InstantAction(() -> robot.Bucket.setPosition(1)),
                    new SleepAction(0.5),
                    new InstantAction(() -> robot.Bucket.setPosition(0)),
                    new SleepAction(0.5),
                    lift.downLift(48)
            ));
        } */

        // update running actions
        List<Action> newActions = new ArrayList<>();
        for (Action action : runningActions) {
            action.preview(packet.fieldOverlay());
            if (action.run(packet)) {
                newActions.add(action);
            }
        }
        runningActions = newActions;

        dash.sendTelemetryPacket(packet);

        if (gamepad1.left_stick_y >= 0.2) {
            robot.LeftFront.setPower(gamepad1.left_stick_y * MAX_SPEED);
            robot.LeftBack.setPower(gamepad1.left_stick_y * MAX_SPEED);
        } else if (gamepad1.left_stick_y <= -0.2) {
            robot.LeftFront.setPower(gamepad1.left_stick_y * MAX_SPEED);
            robot.LeftBack.setPower(gamepad1.left_stick_y * MAX_SPEED);
        } else {
            robot.LeftFront.setPower(Rest);
            robot.LeftBack.setPower(Rest);
        }
        //
        if (gamepad1.right_stick_y >= 0.2) {
            robot.RightFront.setPower(gamepad1.right_stick_y * MAX_SPEED);
            robot.RightBack.setPower(gamepad1.right_stick_y * MAX_SPEED);
        } else if (gamepad1.right_stick_y <= -0.2) {
            robot.RightFront.setPower(gamepad1.right_stick_y * MAX_SPEED);
            robot.RightBack.setPower(gamepad1.right_stick_y * MAX_SPEED);
        } else {
            robot.RightBack.setPower(Rest);
            robot.RightFront.setPower(Rest);
        }

        if (gamepad1.left_stick_x == -1) {
            robot.LeftFront.setPower(-BackSpeed);
            robot.LeftBack.setPower(-FrontSpeed);
            robot.RightFront.setPower(-FrontSpeed);
            robot.RightBack.setPower(-BackSpeed);
        } else if (gamepad1.right_stick_x == 1) {
            robot.LeftFront.setPower(-FrontSpeed);
            robot.LeftBack.setPower(-BackSpeed);
            robot.RightFront.setPower(-BackSpeed);
            robot.RightBack.setPower(-FrontSpeed);
        }


        if (gamepad1.dpad_left == true) {
            robot.LeftFront.setPower(-BackSpeed);
            robot.LeftBack.setPower(-FrontSpeed);
            robot.RightFront.setPower(-FrontSpeed);
            robot.RightBack.setPower(-BackSpeed);
        }

        if (gamepad1.dpad_right == true) {
            robot.LeftFront.setPower(-FrontSpeed);
            robot.LeftBack.setPower(-BackSpeed);
            robot.RightFront.setPower(-BackSpeed);
            robot.RightBack.setPower(-FrontSpeed);
        }

        if (gamepad1.dpad_up == true) {
            robot.LeftFront.setPower(-FrontSpeed);
            robot.LeftBack.setPower(-FrontSpeed);
            robot.RightFront.setPower(-FrontSpeed);
            robot.RightBack.setPower(-FrontSpeed);
        } else if (gamepad1.dpad_down == true) {
            robot.LeftFront.setPower(-BackSpeed);
            robot.LeftBack.setPower(-BackSpeed);
            robot.RightFront.setPower(-BackSpeed);
            robot.RightBack.setPower(-BackSpeed);
        }

        if (gamepad1.a == true){
            robot.HangArm.setPosition(0.6);
        }
        if(gamepad1.b == true){
            robot.HangArm.setPosition(0);
        }
        if(gamepad1.right_bumper == true){
            robot.Winch1.setPower(-0.9);
            robot.Winch2.setPower(1);
        }else if (gamepad1.right_trigger == 1){
            robot.Winch1.setPower(0.9);
            robot.Winch2.setPower(-1);
        }else{
            robot.Winch1.setPower(0);
            robot.Winch2.setPower(0);
        }
        //HERE IT'S RIGHT HERE!!!
        if (gamepad2.right_trigger == 1) {
            robot.Arm.setPower(0.5);
        } else if (gamepad2.right_bumper == true) {
            robot.Arm.setPower(-0.5);
        } else {
            robot.Arm.setPower(0);
        }

        if (gamepad2.a == true) {
         robot.Intake1.setPower(1);
         robot.Intake2.setPower(1);
        }

        if (gamepad2.b == true) {
            robot.Intake1.setPower(-1);
            robot.Intake2.setPower(-1);
        }

        if (gamepad2.x == true) {
        }

        if (gamepad2.y == true) {
        }
        //HERE IT'S ALSO RIGHT HERE!!!
        if (gamepad2.left_trigger == 1) {
            robot.Lift.setPower(0.5);
        } else if (gamepad2.left_bumper == true)  {
            robot.Lift.setPower(-0.5);
        } else {
            robot.Lift.setPower(0.08);
        }

        if (gamepad2.dpad_up == true) {
            robot.Bucket.setPosition(0);
        }
        if (gamepad2.dpad_down == true) {
            robot.Bucket.setPosition(0.5);
        }

       /* if (gamepad2.left_stick_y == 1 && WristPosition1 < 0.8) {
            WristPosition1 = WristPosition1 + 0.01;
            Wrist1.setPosition(WristPosition1);
        } else if (gamepad2.left_stick_y == -1 && WristPosition1 > 0.2) {
            WristPosition1 = WristPosition1 - 0.01;
            robot.Wrist1.setPosition(WristPosition1);
        } else if (gamepad2.left_stick_x == 1 && WristPosition2 < 0.8) {
            WristPosition2 = WristPosition2 + 0.01;
            robot.Wrist2.setPosition(WristPosition2);
        } else if (gamepad2.left_stick_x == -1 && WristPosition2 > 0.2) {
            WristPosition2 = WristPosition2 -0.01;
            robot.Wrist2.setPosition(WristPosition2);
        } */


        }
}


