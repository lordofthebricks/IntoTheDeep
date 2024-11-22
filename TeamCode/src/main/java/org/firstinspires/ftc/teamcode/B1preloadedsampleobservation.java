/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardwareclasses.Lift;

@Autonomous(name="B1Preloaded_Sample_Observation", group="Autonomous")

public class B1preloadedsampleobservation extends LinearOpMode {

    /* Declare OpMode members. */
    //HardwarePushbot robot   = new HardwarePushbot();   // Use a Pushbot's hardware

    hardware robot = new hardware();
    private ElapsedTime runtime = new ElapsedTime();


    static final double FORWARD_SPEED = -0.8;
    static final double REVERSE_SPEED = 0.8;
    static final double TURN_SPEED = 0.5;
    static final double LIFT_SPEED = 0.5;
    static final double STRAFE_SPEED = -0.5;

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way

        // Step 1:  Drive forward for 3 seconds
        robot.LeftFront.setPower(FORWARD_SPEED);
        robot.RightFront.setPower(FORWARD_SPEED);
        robot.LeftBack.setPower(FORWARD_SPEED);
        robot.RightBack.setPower(FORWARD_SPEED);
        sleep(430);
        robot.LeftFront.setPower(0);
        robot.RightFront.setPower(0);
        robot.LeftBack.setPower(0);
        robot.RightBack.setPower(0);

        // Step 2:  Spin right for 1.3 seconds
        // robot.leftDrive.setPower(TURN_SPEED);
        //robot.rightDrive.setPower(-TURN_SPEED);

        robot.LeftFront.setPower(-TURN_SPEED);
        robot.RightFront.setPower(TURN_SPEED);
        robot.LeftBack.setPower(-TURN_SPEED);
        robot.RightBack.setPower(TURN_SPEED);
        sleep(330);
        robot.LeftFront.setPower(0);
        robot.RightFront.setPower(0);
        robot.LeftBack.setPower(0);
        robot.RightBack.setPower(0);

        // Step 3:  Drive Backwards for 1 Second
        robot.LeftFront.setPower(REVERSE_SPEED);
        robot.RightFront.setPower(REVERSE_SPEED);
        robot.LeftBack.setPower(REVERSE_SPEED);
        robot.RightBack.setPower(REVERSE_SPEED);
        sleep(670);
        robot.LeftFront.setPower(0);
        robot.RightFront.setPower(0);
        robot.LeftBack.setPower(0);
        robot.RightBack.setPower(0);

        robot.Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        robot.Lift.setTargetPosition((int) (39 * Lift.InchesPerTick) + robot.Lift.getCurrentPosition());
        robot.Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.Lift.setPower(LIFT_SPEED);
        while (opModeIsActive() && robot.Lift.isBusy()){

        }
        sleep(250);
        robot.Bucket.setPosition(0);
        sleep(1500);
        robot.Lift.setPower(0);




        robot.Bucket.setPosition(0.5);
        sleep(2000);

        robot.Lift.setTargetPosition( robot.Lift.getCurrentPosition() - (int) (34 * Lift.InchesPerTick));
        robot.Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.Lift.setPower(LIFT_SPEED);
        while (opModeIsActive() && robot.Lift.isBusy()){

        }
        robot.Lift.setPower(0);

        robot.LeftFront.setPower(TURN_SPEED);
        robot.RightFront.setPower(TURN_SPEED);
        robot.LeftBack.setPower(TURN_SPEED);
        robot.RightBack.setPower(-TURN_SPEED);
        sleep(340);
        robot.LeftFront.setPower(0);
        robot.RightFront.setPower(0);
        robot.LeftBack.setPower(0);
        robot.RightBack.setPower(0);

        robot.LeftFront.setPower(FORWARD_SPEED);
        robot.RightFront.setPower(FORWARD_SPEED);
        robot.LeftBack.setPower(FORWARD_SPEED);
        robot.RightBack.setPower(FORWARD_SPEED);
        sleep(250);
        robot.LeftFront.setPower(0);
        robot.RightFront.setPower(0);
        robot.LeftBack.setPower(0);
        robot.RightBack.setPower(0);

        robot.LeftFront.setPower(STRAFE_SPEED);
        robot.RightFront.setPower(-STRAFE_SPEED);
        robot.LeftBack.setPower(-STRAFE_SPEED);
        robot.RightBack.setPower(STRAFE_SPEED);
        sleep(7500);
        robot.LeftFront.setPower(0);
        robot.RightFront.setPower(0);
        robot.LeftBack.setPower(0);
        robot.RightBack.setPower(0);

        robot.LeftFront.setPower(REVERSE_SPEED);
        robot.RightFront.setPower(REVERSE_SPEED);
        robot.LeftBack.setPower(REVERSE_SPEED);
        robot.RightBack.setPower(REVERSE_SPEED);
        sleep(300);
        robot.LeftFront.setPower(0);
        robot.RightFront.setPower(0);
        robot.LeftBack.setPower(0);
        robot.RightBack.setPower(0);

        // Step 4:  Stop and close the claw.
        //robot.leftDrive.setPower(0);
        //robot.rightDrive.setPower(0);
        //robot.leftClaw.setPosition(1.0);
        //robot.rightClaw.setPosition(0.0);
        //robot.Lift.setPower(0.5);
        //telemetry.addData("Path", "Complete");
        //telemetry.update();
        //sleep(4000);
    }
}
