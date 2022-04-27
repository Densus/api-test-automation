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

import org.assertj.core.api.Assertions
import org.openqa.selenium.Keys
import org.testng.asserts.Assertion as Keys


//Generate Random value for page variable
pageNumber = (int) (Math.random() * 2 + 1)

//Send the request data and capture the response
ResponseObject response = WS.sendRequest(findTestObject('Reqres_ObjectRepository/GET list users', [('page') : pageNumber]))

//Added the assertion on status code
Assertions.assertThat(response.getStatusCode()).isEqualTo(200)

//Print the response at console
println response.getResponseBodyContent()

//Parse the json response
JsonSlurper parser = new JsonSlurper()
def responseAfterParsing = parser.parseText(response.getResponseBodyContent())

System.out.println("page: " + responseAfterParsing.page)

//Added the assertion on pageNumber
Assertions.assertThat(responseAfterParsing.page).isEqualTo(pageNumber)


