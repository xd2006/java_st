package ru.stqa.pft.addressbook.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * Created by Alex on 31.07.2016.
 */
@CucumberOptions(features = "classpath:bdd", plugin = {"pretty", "html:build/cucumber-report"})
public class GroupTests extends AbstractTestNGCucumberTests {
}
