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

// 1. Generate dynamic ID
def petId = 1000 + new Random().nextInt(9000)
def petName = 'NegativeGet_' + petId

println "Pet ID: ${petId}"
println "Pet Name: ${petName}"

// 2. Create Pet
def createResponse = WS.sendRequest(
    findTestObject('API-petstore/post-petstore', [
        ('PetID')   : petId,
        ('PetName') : petName
    ])
)

WS.verifyResponseStatusCode(createResponse, 200)

// 3. Delete Pet
def deleteResponse = WS.sendRequest(
    findTestObject('API-petstore/delete-petstore-positive', [
        ('PetID') : petId
    ])
)

WS.verifyResponseStatusCode(deleteResponse, 200)

println "Pet ${petId} berhasil dibuat lalu dihapus."

// 4. GET Pet yang sudah dihapus
def getResponse = WS.sendRequest(
    findTestObject('API-petstore/get-pet-by-pet-id-negative', [
        ('PetID') : petId
    ])
)

println "GET Status: ${getResponse.getStatusCode()}"
println "GET Response: ${getResponse.getResponseBodyContent()}"

// 5. Negative Validation
WS.verifyResponseStatusCode(getResponse, 404)
WS.verifyElementPropertyValue(getResponse, 'code', 1)
WS.verifyElementPropertyValue(getResponse, 'message', 'Pet not found')