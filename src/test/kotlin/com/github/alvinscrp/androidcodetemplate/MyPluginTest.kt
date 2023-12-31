package com.github.alvinscrp.androidcodetemplate

import com.android.tools.idea.wizard.template.underscoreToLowerCamelCase
import com.github.alvinscrp.androidcodetemplate.generator.util.layoutPrefix
import com.intellij.ide.highlighter.XmlFileType
import com.intellij.openapi.components.service
import com.intellij.psi.xml.XmlFile
import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.intellij.util.PsiErrorElementUtil
import com.github.alvinscrp.androidcodetemplate.services.MyProjectService

@TestDataPath("\$CONTENT_ROOT/src/test/testData")
class MyPluginTest : BasePlatformTestCase() {

    fun testXMLFile() {
        val psiFile = myFixture.configureByText(XmlFileType.INSTANCE, "<foo>bar</foo>")
        val xmlFile = assertInstanceOf(psiFile, XmlFile::class.java)

        assertFalse(PsiErrorElementUtil.hasErrors(project, xmlFile.virtualFile))

        assertNotNull(xmlFile.rootTag)

        xmlFile.rootTag?.let {
            assertEquals("foo", it.name)
            assertEquals("bar", it.value.text)
        }
    }

    fun testRename() {
        myFixture.testRename("foo.xml", "foo_after.xml", "a2")
    }

    fun testProjectService() {
        val projectService = project.service<MyProjectService>()

        assertNotSame(projectService.getRandomNumber(), projectService.getRandomNumber())
    }

    fun testUnderscoreToCamelCase(){
        underscoreToLowerCamelCase("sd_te").let {
            println(it)
            assertEquals(it,"sdTe")
        }

        layoutPrefix("SD_TE","SsddDefggDD").let{
            println(it)
            println(underscoreToLowerCamelCase(it))
        }


    }
    override fun getTestDataPath() = "src/test/testData/rename"
}
