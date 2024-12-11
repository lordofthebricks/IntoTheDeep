package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.ArrayList;
import java.util.List;

@TeleOp
public class ServoTester extends OpMode {

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
        double Wrist2Pos = robot.Wrist2.getPosition();

    //    double Wrist1Pos = robot.Wrist1.getPosition();

     //   double ClawPos = robot.Claw.getPosition();
     //   if(gamepad1.a) robot.Claw.setPosition(robot.Claw.getPosition() + 0.01);

    //    if(gamepad1.b) robot.Claw.setPosition(robot.Claw.getPosition() - 0.01);

        if(gamepad1.x) robot.Wrist2.setPosition(robot.Wrist2.getPosition() + 0.01);

        if(gamepad1.y) robot.Wrist2.setPosition(robot.Wrist2.getPosition() - 0.01);

     //   if(gamepad1.dpad_up) robot.Wrist1.setPosition(robot.Wrist1.getPosition() + 0.01);

      //  if(gamepad1.dpad_down) robot.Wrist1.setPosition(robot.Wrist1.getPosition() - 0.01);

        telemetry.addData("Wrist 2 Position: ", Wrist2Pos);

      //  telemetry.addData("Wrist 1 Position: ", Wrist1Pos);

      //  telemetry.addData("Claw Pos: ", ClawPos);

        telemetry.update();


    }
}


