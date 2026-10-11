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

// ========================================
// 1. GENERATE DYNAMIC DATA
// ========================================

def petId = 1000 + new Random().nextInt(9000)
def petName = 'E2E_Pet_' + petId
def updatedPetName = 'E2E_Updated_' + petId

println "Pet ID       : ${petId}"
println "Pet Name     : ${petName}"
println "Updated Name : ${updatedPetName}"

// ========================================
// 2. CREATE PET - POST
// ========================================

def createResponse = WS.sendRequest(
	findTestObject('API-petstore/post-petstore', [
		('PetID')   : petId,
		('PetName') : petName
	])
)

WS.verifyResponseStatusCode(createResponse, 200)
WS.verifyElementPropertyValue(
	createResponse,
	'id',
	petId
)
WS.verifyElementPropertyValue(
	createResponse,
	'name',
	petName
)

println "POST Response: ${createResponse.getResponseBodyContent()}"

// ========================================
// 3. GET PET
// ========================================

def getResponse = WS.sendRequest(
	findTestObject('API-petstore/get-pet-by-pet-id-positive', [
		('PetID') : petId
	])
)

WS.verifyResponseStatusCode(getResponse, 200)

WS.verifyElementPropertyValue(
	getResponse,
	'id',
	petId
)

WS.verifyElementPropertyValue(
	getResponse,
	'name',
	petName
)

println "GET Response: ${getResponse.getResponseBodyContent()}"

// ========================================
// 4. UPDATE PET - PUT
// ========================================

def updateResponse = WS.sendRequest(
	findTestObject('API-petstore/put-petstore', [
		('PetID')   : petId,
		('PetName') : updatedPetName
	])
)

WS.verifyResponseStatusCode(updateResponse, 200)

WS.verifyElementPropertyValue(
	updateResponse,
	'id',
	petId
)

WS.verifyElementPropertyValue(
	updateResponse,
	'name',
	updatedPetName
)

WS.verifyElementPropertyValue(
	updateResponse,
	'status',
	'available'
)

println "PUT Response: ${updateResponse.getResponseBodyContent()}"

// ========================================
// 5. DELETE PET
// ========================================

def deleteResponse = WS.sendRequest(
	findTestObject('API-petstore/delete-petstore-positive', [
		('PetID') : petId
	])
)

WS.verifyResponseStatusCode(deleteResponse, 200)

WS.verifyElementPropertyValue(
	deleteResponse,
	'code',
	200
)

WS.verifyElementPropertyValue(
	deleteResponse,
	'message',
	petId.toString()
)

println "DELETE Response: ${deleteResponse.getResponseBodyContent()}"

println "===== E2E TEST COMPLETED SUCCESSFULLY ====="