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


// 1. Generate dynamic data
def petId = 1000 + new Random().nextInt(9000)
def petName = 'Buddy_' + petId
def updatedName = 'Updated_' + petName

println "Pet ID       : ${petId}"
println "Pet Name     : ${petName}"
println "Updated Name : ${updatedName}"

// 2. Create Pet

def createResponse = WS.sendRequest(
	findTestObject('API-petstore/post-petstore', [
		('PetID')   : petId,
		('PetName'): petName
	])
)
println "POST Status: ${createResponse.getStatusCode()}"
println "POST Response: ${createResponse.getResponseBodyContent()}"
WS.verifyResponseStatusCode(createResponse, 200)


println "Pet ID hasil POST: ${petId}"
println "Pet Name: ${petName}"

// 3. Update Pet

def updateResponse = WS.sendRequest(
	findTestObject('API-petstore/put-petstore', [
		('PetID'): petId,
		('PetName'): updatedName
	])
)

println "PUT Status: ${updateResponse.getStatusCode()}"
println "PUT Response: ${updateResponse.getResponseBodyContent()}"

// 4. Verification PUT
WS.verifyResponseStatusCode(updateResponse, 200)
WS.verifyElementPropertyValue(updateResponse, 'id', petId)
WS.verifyElementPropertyValue(updateResponse, 'name', updatedName)
WS.verifyElementPropertyValue(updateResponse, 'status', 'available')