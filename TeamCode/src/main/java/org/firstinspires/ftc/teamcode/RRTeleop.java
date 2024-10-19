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

import java.util.ArrayList;
import java.util.List;
@TeleOp
public class RRTeleop extends OpMode {

    private FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();
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
        TelemetryPacket packet = new TelemetryPacket();

        // updated based on gamepads
        if (gamepad1.a) {
            runningActions.add(new SequentialAction(
                    new SleepAction(0.8),
                    new InstantAction(() -> robot.Lift.setPower(1)),
                    new SleepAction(0.8),
                    new InstantAction(() -> robot.Lift.setPower(0)),
                    new SleepAction(0.8),
                    new InstantAction(() -> robot.Lift.setPower(-1)),
                    new SleepAction(0.8),
                    new InstantAction(() -> robot.Lift.setPower(0))
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
    }

}
