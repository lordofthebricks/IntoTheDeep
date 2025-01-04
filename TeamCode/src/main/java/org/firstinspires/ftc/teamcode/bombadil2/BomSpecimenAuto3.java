package org.firstinspires.ftc.teamcode.bombadil2;



import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
@Autonomous(name = "Bombadil2_Specimen_Autonomous", group = "Autonomous")

public class BomSpecimenAuto3 extends LinearOpMode {

    BombadilHardware robot = new BombadilHardware();

    private ElapsedTime runtime = new ElapsedTime();
    static final double DISTANCE_FROM_WALL = 17.35;

    // was put as 537.7
    static final double COUNTS_PER_MOTOR_REV = 384.5;
    static final double DRIVE_GEAR_REDUCTION = 1.0;
    static final double WHEEL_DIAMETER_INCHES = 4.0;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * Math.PI);

    public void runOpMode() {

        robot.init(hardwareMap);

        waitForStart();


        //come off of the wall slightly
        robot.leftFront.setPower(0.5);
        robot.rightFront.setPower(0.5);
        robot.leftBack.setPower(0.5);
        robot.rightBack.setPower(0.5);
        sleep(90);
        robot.leftFront.setPower(0);
        robot.rightFront.setPower(0);
        robot.leftBack.setPower(0);
        robot.rightBack.setPower(0);

    }
}
