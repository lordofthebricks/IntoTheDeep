package org.firstinspires.ftc.teamcode.bombadil2;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.ArrayList;
import java.util.List;


@TeleOp(name = "Road Runner Teleop Bombadil 2.0")
public class BombadilRRTeleop extends OpMode {

    final double MAX_SPEED = 0.8;
    final double STRAFE_SPEED = 0.5;
    BombadilHardware robot = new BombadilHardware();

    private FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();


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

        TelemetryPacket packet = new TelemetryPacket();

        // updated based on gamepads
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

        if (gamepad1.right_bumper){

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
