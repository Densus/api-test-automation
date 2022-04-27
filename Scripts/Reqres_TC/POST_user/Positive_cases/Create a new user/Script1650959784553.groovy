import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import groovy.json.JsonSlurper
import internal.GlobalVariable

import org.apache.commons.lang.RandomStringUtils
import org.assertj.core.api.Assertions
import org.openqa.selenium.Keys as Keys

//Given a random name
String randomName = RandomStringUtils.randomAlphabetic(8)

//Given a random job
String randomJob = RandomStringUtils.randomAlphabetic(8)

//Send the request data and capture the response
ResponseObject response = WS.sendRequest(findTestObject('Reqres_ObjectRepository/POST a new user', [('name') : randomName, ('job') : randomJob]))

//Added assertion on the status code 201
Assertions.assertThat(response.getStatusCode()).isEqualTo(201)

//Parsing the response to json 
JsonSlurper parser = new JsonSlurper()
def responseParsed = parser.parseText(response.getResponseBodyContent())

//Check that name from the response body is equal to given random name
Assertions.assertThat(responseParsed.name).isEqualTo(randomName)

//Check that job from the response body is equal to given random job
Assertions.assertThat(responseParsed.job).isEqualTo(randomJob)



