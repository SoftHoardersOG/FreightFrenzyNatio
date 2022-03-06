package com.example.meepmeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class TrajectoryVisualizer {
    public static Pose2d caruselPosition;
    public static Pose2d shippingHubPose;
    public static Pose2d gapPose;
    public static Pose2d warehousePose;
    public static Pose2d intakePose;
    public static Pose2d parkPose;
    public static Pose2d shippingHubReturnPose;
    public static Pose2d secondIntakeIncrementer;
    public static Pose2d warehouseParkSharedPose1;
    public static Pose2d warehouseParkSharedPose2;
    public static Pose2d shippingHubCaruselSidePose;
    public static Pose2d duckCollectPose;
    public static Pose2d duckCollectPoseIntermediary;
    public static Pose2d wallPose;
    public static int incrementer = 0;
    ////
    public static Pose2d goOverPose;
    public static Pose2d warehouseOverPose;
    public static void main(String[] args) {
        caruselPosition = new Pose2d(-54.6, -60.8, java.lang.Math.toRadians(55.75));
        shippingHubPose = new Pose2d(-11, -44, java.lang.Math.toRadians(90)); /// suprascris dupa initializare, e in fiecare a b c alta pozitie
        shippingHubCaruselSidePose = new Pose2d(-19, -43, java.lang.Math.toRadians(70)); /// suprascris dupa initializare, e in fiecare a b c alta pozitie
        gapPose = new Pose2d(12, -60.3, Math.toRadians(0));
        warehousePose = new Pose2d(25, -60.3, Math.toRadians(0));
        intakePose = new Pose2d(42.5, -60.3, Math.toRadians(0));
        parkPose = new Pose2d(42, -60.3, Math.toRadians(0));
        shippingHubReturnPose = new Pose2d(8.4, -39.6, Math.toRadians(180 + 135));
        secondIntakeIncrementer = new Pose2d(6.5, 0, 0);
        Pose2d startPosition = new Pose2d(-36, -60.5,Math.toRadians(90));
        warehouseParkSharedPose1 = new Pose2d(43, -37, Math.toRadians(0));
        warehouseParkSharedPose2 = new Pose2d(55, -37, Math.toRadians(270));
        duckCollectPose = new Pose2d(-55.71, -60, Math.toRadians(226));
        duckCollectPoseIntermediary = new Pose2d(-45, -45, Math.toRadians(226));
        wallPose = new Pose2d(-20, -60.3, Math.toRadians(0));
        /////

        goOverPose = new Pose2d(5, -43, Math.toRadians(0));
        warehouseOverPose = new Pose2d(50, -43, Math.toRadians(0));

        ////

        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setConstraints(55, 50, Math.toRadians(360), Math.toRadians(360), 10)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPosition)
                                .lineToLinearHeading(shippingHubCaruselSidePose)
                                .waitSeconds(0.35)
                                .setReversed(true)
                                .splineToLinearHeading(caruselPosition, Math.toRadians(180))
                                .setReversed(false)
                                .waitSeconds(2.050)
                                .lineToSplineHeading(duckCollectPoseIntermediary)
                                .lineToLinearHeading(duckCollectPose)
                                .turn(Math.toRadians(-25), 5, 4)
                                .turn(Math.toRadians(50), 5, 4)
                                .lineToLinearHeading(shippingHubCaruselSidePose)
                                .waitSeconds(0.35)
                                .waitSeconds(4)
                                .lineToSplineHeading(goOverPose)
                                .lineToLinearHeading(warehouseOverPose)
                                .lineToLinearHeading(warehouseParkSharedPose2)

//                                .lineToLinearHeading(caruselPosition)
//
//                                .lineToLinearHeading(shippingHubPose)
//
//                                .lineToLinearHeading(gapPose)
//                                .lineToLinearHeading(parkPose)
//                                .lineToLinearHeading(warehouseParkSharedPose1)
//                                .lineToLinearHeading(warehouseParkSharedPose2)
                                .build()
                );
        myBot.setDimensions(12, 18);
        meepMeep.setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}