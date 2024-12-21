package org.firstinspires.ftc.teamcode.bombadil2;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
@Autonomous(name = "Bombadil2_Specimen_Autonomous", group = "Autonomous")

public class Bom2specimenauto extends LinearOpMode {

    BombadilHardware robot = new BombadilHardware();

    public void runOpMode() {

        robot.init(hardwareMap);

        waitForStart();

        robot.leftFront.setPower(1);
        robot.rightFront.setPower(1);
        robot.leftBack.setPower(1);
        robot.rightBack.setPower(1);
        sleep(2000);

    }
}
