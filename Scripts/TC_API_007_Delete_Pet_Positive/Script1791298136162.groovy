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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import groovy.json.JsonSlurper



import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

// Generate ID 1000 - 9999
def petId = 1000 + new Random().nextInt(9000)

def petName = 'DeleteTest_' + petId

println "Generated Pet ID: ${petId}"
println "Pet Name: ${petName}"

// CREATE PET
def createResponse = WS.sendRequest(
    findTestObject('API-petstore/post-petstore', [
        ('PetID')   : petId,
        ('PetName') : petName
    ])
)

WS.verifyResponseStatusCode(createResponse, 200)

println "Create Response: ${createResponse.getResponseBodyContent()}"

// DELETE PET
def deleteResponse = WS.sendRequest(
    findTestObject('API-petstore/delete-petstore-positive', [
        ('PetID') : petId
    ])
)

println "Delete Response: ${deleteResponse.getResponseBodyContent()}"

// VALIDATION
WS.verifyResponseStatusCode(deleteResponse, 200)
WS.verifyElementPropertyValue(deleteResponse, 'code', 200)
WS.verifyElementPropertyValue(deleteResponse, 'message', petId.toString())