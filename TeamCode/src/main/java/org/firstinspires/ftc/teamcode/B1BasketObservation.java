package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
@Autonomous(name = "B1_Basket_Observation", group = "Autonomous")
public class B1BasketObservation extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();
    static final double     MAX_SPEED = 1;
    static final double     FORWARD_SPEED = 0.8;
    static final double     REVERSE_SPEED = -0.6;
    static final double     STRAFE_SPEED = 0.6;
    static final double     TURN_SPEED = 0.5;
    @Override
    public void runOpMode() {

        hardware robot = new hardware();

        waitForStart();

        robot.LeftFront.setPower(-MAX_SPEED);
        robot.RightFront.setPower(-MAX_SPEED);
        robot.LeftBack.setPower(-MAX_SPEED);
        robot.RightBack.setPower(-MAX_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.5)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        robot.LeftFront.setPower(-TURN_SPEED);
        robot.RightFront.setPower(TURN_SPEED);
        robot.LeftBack.setPower(-TURN_SPEED);
        robot.RightBack.setPower(TURN_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2)) {
            telemetry.addData("Path", "Leg 2: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }
    }
}
