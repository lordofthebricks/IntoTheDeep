package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;
import java.util.List;

@TeleOp
public class Teleop extends OpMode {

private hardware robot;

final double MAX_SPEED = 0.8;
final double STRAFE_SPEED = 0.6;

double Rest = 0;

    private FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();


@Override
    public void init() {

    robot = new hardware();
    robot.init(hardwareMap);
}

    @Override
    public void loop() {
        TelemetryPacket packet = new TelemetryPacket();

        // updated based on gamepads
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
            robot.LeftFront.setPower(gamepad1.right_stick_y * MAX_SPEED);
            robot.LeftBack.setPower(gamepad1.right_stick_y * MAX_SPEED);
        } else if (gamepad1.right_stick_y <= -0.2) {
            robot.LeftFront.setPower(gamepad1.right_stick_y * MAX_SPEED);
            robot.LeftBack.setPower(gamepad1.right_stick_y * MAX_SPEED);
        } else {
            robot.RightBack.setPower(Rest);
            robot.RightFront.setPower(Rest);
        }




        //simple tank drive
//        robot.LeftBack.setPower(gamepad1.left_stick_y);
//        robot.leftFront.setPower(gamepad1.left_stick_y);
//        robot.RightBack.setPower(gamepad1.right_stick_y);
//        robot.RightFront.setPower(gamepad1.right_stick_y);

        if (gamepad1.dpad_left == true) {
            robot.LeftFront.setPower(STRAFE_SPEED);
            robot.LeftBack.setPower(-STRAFE_SPEED);
            robot.RightFront.setPower(-STRAFE_SPEED);
            robot.RightBack.setPower(STRAFE_SPEED);
        } else if (gamepad1.dpad_left == false) {
            robot.LeftFront.setPower(Rest);
            robot.LeftBack.setPower(Rest);
            robot.RightFront.setPower(Rest);
            robot.RightBack.setPower(Rest);
        }

        if (gamepad1.dpad_right == true) {
            robot.LeftFront.setPower(STRAFE_SPEED);
            robot.LeftBack.setPower(-STRAFE_SPEED);
            robot.RightFront.setPower(-STRAFE_SPEED);
            robot.RightBack.setPower(STRAFE_SPEED);
        } else if (gamepad1.dpad_right == false) {
            robot.LeftFront.setPower(Rest);

            robot.LeftBack.setPower(Rest);
            robot.RightFront.setPower(Rest);
            robot.RightBack.setPower(Rest);
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
    }
}


