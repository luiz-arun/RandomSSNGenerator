package main.java.Addon;

import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.GenericAction;
import io.testproject.java.sdk.v2.addons.helpers.AddonHelper;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;


@Action(name = "Random SSN Generator", description = "Generate Random SSN")
public class RandomSSNGenerator implements GenericAction {

    @Parameter(description = "Random Output value", direction = ParameterDirection.OUTPUT)
    public String output;

    public static String GenerateAreaNumber() {
        int areaNumber = 0;
        int maxDigit = 3;
        int maxNumber = 899;
        boolean isGoodAreaNumber = false;
        while (isGoodAreaNumber == false) {
            areaNumber = (int) Math.ceil(Math.random() * maxNumber);
            if (areaNumber == 000) {
                isGoodAreaNumber = false;
            } else if (areaNumber == 666) {
                isGoodAreaNumber = false;
            } else {
                isGoodAreaNumber = true;
            }
        }
        return padWithZero(areaNumber, maxDigit);
    }

    public static String GenerateGroupNumber() {
        int maxDigit = 2;
        int maxNumber = 99;
        return padWithZero((int) Math.ceil(Math.random() * maxNumber), maxDigit);
    }

    public static String GenerateSerialNumber() {
        int maxDigit = 4;
        int maxNumber = 9999;
        return padWithZero((int) Math.ceil(Math.random() * maxNumber), maxDigit);
    }

    public static String padWithZero(int num, int size) {
        String stringNum = num + "";
        while (stringNum.length() < size) stringNum = "0" + stringNum;
        return stringNum;

    }

    @Override
        public ExecutionResult execute (AddonHelper helper) throws FailureException {
        RandomSSNGenerator result = new RandomSSNGenerator();

        output = result.GenerateAreaNumber();
        output = output.concat(result.GenerateGroupNumber());
        output = output.concat(result.GenerateSerialNumber());

        ActionReporter report = helper.getReporter();
        report.result("SSN Number Generated Successfully: "+ output);

        return ExecutionResult.PASSED;

        }
    }

