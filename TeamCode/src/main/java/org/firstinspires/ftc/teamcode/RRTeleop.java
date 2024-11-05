package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardwareclasses.FrontSlide;
import org.firstinspires.ftc.teamcode.hardwareclasses.Lift;

import java.util.ArrayList;
import java.util.List;
@TeleOp
public class RRTeleop extends OpMode {

    FrontSlide frontSlide;
    Lift lift;
    private FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();
    private hardware robot;
    double FrontSpeed = 0.8;
    double BackSpeed = -0.8;
    double MaxSpeed = 1;
    double Rest = 0;
    final double MAX_SPEED = 1;
    private double outInchs = 18;
    private boolean firstRun = true;
    private double inInchs = 15.5;


    @Override
    public void init() {
        robot = new hardware();
        robot.init(hardwareMap);
        frontSlide = new FrontSlide(hardwareMap);
        lift = new Lift(hardwareMap);
        firstRun = true;
        outInchs = 8;
    }

    @Override
    public void loop() {
        TelemetryPacket packet = new TelemetryPacket();

        // updated based on gamepads
        if (gamepad1.a) {
            if(!firstRun){
                outInchs = 6;
            }
            firstRun = false;
            runningActions.add(new SequentialAction(
                    frontSlide.slideOut(outInchs),
                    new InstantAction(() -> robot.Wrist2.setPosition(0.5)),
                    new InstantAction(() -> robot.Wrist1.setPosition(0.5)),
                    new InstantAction(() -> robot.Claw.setPosition(0.8)),
                    frontSlide.slideOut( 10),
                    new SleepAction(1),
                    new InstantAction(() -> robot.Claw.setPosition(0.5)),
                    new SleepAction(0.5),
                    new InstantAction(() -> robot.Wrist2.setPosition(0.05)),
                    new InstantAction(()-> robot.Wrist1.setPosition(0.2)),
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
        }

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
    }

}
