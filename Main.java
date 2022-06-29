package com.company;

import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        exercise1();
        System.out.println("\n----------------------------------\n");
        exercise2();
        System.out.println("\n----------------------------------\n");
        exercise3and4();
        System.out.println("\n----------------------------------\n");
        exercise5();
    }

    public static void exercise1() {
        // Create multiplication table in array, output the result

        int[][] arr = new int[10][10];

        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[0].length; j++) {
                arr[i][j] = (i+1)*(j+1);
                System.out.printf("%d * %d = %d%n", i+1, j+1, arr[i][j]);
            }
        }
    }

    public static void exercise2() {
        // Initialize an array with 4 random numbers
        // and copy it to another array by iterating it

        int[] arr = {234, 12, 5, 99231};
        int[] arr_copy = new int[4];

        for(int i=0; i<arr.length; i++) {
            arr_copy[i] = arr[i];
        }
        System.out.println("Original array:");
        System.out.println(Arrays.toString(arr));

        System.out.println("Copy array:");
        System.out.println(Arrays.toString(arr_copy));
    }

    public static void exercise3and4() {
        //  Bank account class with deposit, withdraw, printBalance, transferFrom methods
        //  and private balance attribute

        class BankAccount {
            private float balance = 0;

            BankAccount() { }

            public void deposit(float amount) {
                if (amount <= 5000) {
                    balance += amount;
                } else {
                    System.out.printf("Transaction canceled. Max deposit - 5000 | Your deposit: %.2f%n", amount);
                }
            }

            public boolean withdraw(float amount, boolean local) {
                if (amount <= balance) {
                    balance -= amount;
                    return true;
                } else {
                    if (local) System.out.println("There is not enough funds");
                    return false;
                }
            }

            public void printBalance() {
                System.out.printf("Remaining balance: %f.2%n", balance);
            }

            public float getBalance() {
                return balance;
            }

            public float transferFrom(BankAccount acc, float amount) {
                if (acc.withdraw(amount, false)) {
                    deposit(amount);
                } else {
                    System.out.printf("Transfer canceled. You are trying to transfer %.2f units, but only %.2f are available.%n", amount, acc.getBalance());
                }
                return amount;
            }
        }

        BankAccount account = new BankAccount();
        BankAccount account2 = new BankAccount();
        account.deposit(2000.35f);
        account2.deposit(352.87f);
        account.withdraw(3000, true); // Withdrawing more than there is
        account.deposit(6700); // Depositing more thank 5000
        account.transferFrom(account2, 500);
    }

    public static void exercise5() {
        // Create a parent class with 2 child classes below each other.
        // Create attributes and methods for steering, changing gears and driving.

        class Vehicle {
            private float steerAngle = 0;
            private final int steerMaxLeft = -360;
            private final int steerMaxRight = 360;

            private float speed = 0;
            private int startSpeed = 10;
            private int maxForwardSpeed = 240;
            private int maxReverseSpeed = 30;
            private byte gear = 0;
            private byte maxGear = 5;

            public void steerLeft(float degrees) {
                if (steerAngle - degrees >= steerMaxLeft) {
                    steerAngle -= degrees;
                } else {
                    steerAngle = steerMaxLeft;
                }
                printSteerAngle();
            }

            public void steerRight(float degrees) {
                if (steerAngle + degrees <= steerMaxRight) {
                    steerAngle += degrees;
                } else {
                    steerAngle = steerMaxRight;
                }
                printSteerAngle();
            }

            public void steerCenter() {
                steerAngle = 0;
                printSteerAngle();
            }

            public void printSteerAngle() {
                if (steerAngle == 0) {
                    System.out.println("Steering wheel is straight");
                } else {
                    if (steerAngle > 0) {
                        System.out.printf("Steering wheel is turned right by %.1f degrees%n", steerAngle);
                    } else {
                        System.out.printf("Steering wheel is turned left by %.1f degrees%n", steerAngle * 1);
                    }
                }
            }

            public void changeGear(byte newGear) {
                if (newGear <= maxGear && newGear >= -1) {
                        gear = newGear;
                        printGear();
                } else {
                    System.out.printf("No such gear, gears are in range from -1 to %d", maxGear);
                }
            }

            public void printGear() {
                if (gear > 0) {
                    System.out.printf("You are in gear %d%n", gear);
                } else {
                    if (gear == 0) {
                        System.out.println("You are in neutral");
                    } else {
                        System.out.println("You are in reverse gear");
                    }
                }
            }

            public void startDriving() {
                if (gear == 1) {
                    speed = startSpeed;
                    System.out.println("Starting to drive forward...");
                    printSpeed();
                } else {
                    if (gear == -1) {
                        speed = startSpeed * -1;
                        System.out.println("Starting to drive backwards...");
                        printSpeed();
                    } else {
                        System.out.printf("You can't start driving in gear %d, you must be in reverse or gear 1", gear);
                    }
                }
            }

            public void increaseSpeed(float amount) {
                if (gear > 0) {
                    if (speed + amount <= maxForwardSpeed) {
                        speed += amount;
                        System.out.println("Increasing speed...");
                        printSpeed();
                    } else {
                        speed = maxForwardSpeed;
                        System.out.println("Increasing speed...");
                        printSpeed();
                    }
                } else {
                    if (gear == 0) {
                        System.out.println("Revving the engine...");
                    } else {
                        if (gear == -1) {
                            if (speed - amount <= maxReverseSpeed) {
                                speed -= amount;
                                System.out.println("Increasing speed...");
                                printSpeed();
                            } else {
                                speed = maxReverseSpeed;
                                System.out.println("Increasing speed...");
                                printSpeed();
                            }
                        }
                     }
                }
            }

            public void decreaseSpeed(float amount) {
                if (speed - amount >= 0) {
                    speed -= amount;
                    System.out.println("Decreasing speed...");
                    printSpeed();
                } else {
                    speed = 0;
                    System.out.println("Decreasing speed...");
                    printSpeed();
                }
            }

            public void stopCar() {
                speed = 0;
                System.out.println("Stopping the car...");
            }

            public void printSpeed() {
                if (speed == maxForwardSpeed) {
                    System.out.printf("Driving max speed, at %d%n", maxForwardSpeed);
                } else {
                    if (speed == maxReverseSpeed) {
                        System.out.printf("Driving max reverse speed, at %d%n", maxReverseSpeed);
                    } else {
                        if (speed == 0) {
                            System.out.println("You are stopped");
                        }
                        System.out.printf("Current speed: %.1f km/h%n", speed);
                    }
                }

            }
        }

        class Car extends Vehicle {

        }

        class Sedan extends Car {

        }

        Sedan car = new Sedan();
        car.printGear();
        car.changeGear((byte) 1);
        car.startDriving();
        car.changeGear((byte) 2);
        car.increaseSpeed(280);
        car.changeGear((byte) 5);
        car.decreaseSpeed(100);
        car.changeGear((byte) 2);
        car.stopCar();
        car.changeGear((byte) -1);
        car.increaseSpeed(30);
        car.steerLeft(50);
        car.steerRight(100);
        car.steerCenter();
        car.stopCar();
        car.changeGear((byte) 1);
    }
}