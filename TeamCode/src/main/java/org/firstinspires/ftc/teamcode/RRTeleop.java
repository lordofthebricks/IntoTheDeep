package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardwareclasses.FrontSlide;

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
    final double MAX_SPEED = 0.8;


    @Override
    public void init() {
        robot = new hardware();
        robot.init(hardwareMap);
        frontSlide = new FrontSlide(hardwareMap);
        lift = new Lift(robot.Lift);
    }

    @Override
    public void loop() {
        TelemetryPacket packet = new TelemetryPacket();

        // updated based on gamepads
        if (gamepad1.a) {
            runningActions.add(new SequentialAction(
                    frontSlide.slideOut(22),
                    new SleepAction(1000),
                    new InstantAction(() -> robot.Wrist2.setPosition(135)),
                    new InstantAction(() -> robot.Wrist1.setPosition(135)),
                    new InstantAction(() -> robot.Claw.setPosition(135)),
                    new SleepAction(0.25),
                    new InstantAction(() -> robot.Claw.setPosition(260)),
                    new InstantAction(() -> robot.Wrist2.setPosition(20)),
                    frontSlide.slideIn(22),
                    new InstantAction(() -> robot.Claw.setPosition(180)),
                    frontSlide.slideOut(2)

            ));
        }

        if (gamepad1.b) {
            runningActions.add(new SequentialAction(
                    lift.upLift(48),
                    new InstantAction(() -> robot.Bucket.setPosition(135)),
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
