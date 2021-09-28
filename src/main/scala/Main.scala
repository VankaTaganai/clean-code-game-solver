import Main.SelectorParams.apply
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}

import scala.io.StdIn
import scala.language.implicitConversions

object Main extends App {
  val driver: ChromeDriver = new ChromeDriver()

  try {
    driver.get("https://cleancodegame.github.io/")

    StdIn.readInt()

    driver.findElement(selectorByTagAndText("button")("Стажер")).click()

    solveLevelOne()
    solveLevelTwo()
    solveLevelTree()

  } finally {
    StdIn.readInt()
    driver.quit()
  }

  def selectorByText: SelectorParams => By =
    selectorByTagAndText("span")

  def selectorByTagAndText(tag: String)(params: SelectorParams): By =
    By.xpath(s"(//$tag[text()='${params.text}'])${params.filter.map(fl => s"[$fl()]").getOrElse("")}")

  def clickOnElement(selector: By): Unit = {
    val element = new WebDriverWait(driver, 5)
      .until(ExpectedConditions.elementToBeClickable(selector))
    driver.executeScript("arguments[0].scrollIntoView(true);", element)
    element.click()
  }

  case object SelectorParams {
    implicit def apply(text: String): SelectorParams = SelectorParams(text, None)
  }

  case class SelectorParams(text: String, filter: Option[String]) {
    def filterBy(filter: String): SelectorParams = SelectorParams(text, Some(filter))
  }

  def solveTask(textInTags: SelectorParams*): Unit = {
    textInTags.foreach(text => {
      val selector = selectorByText(text)
      clickOnElement(selector)
    })
    clickOnElement(By.className("btn-next"))
  }


  def solveLevelOne(): Unit = {
    solveTask("veryBadVariableName_clickIt")
    solveTask("GetThem", "theBigList", "list1")
    solveTask("n", "86400", "t")
    clickOnElement(selectorByTagAndText("p")("Следующий!"))
  }

  def solveLevelTwo(): Unit = {
    solveTask("rstr", "flag")
    solveTask("colBigrams", "bigrams_list", "I")
    solveTask("arg", "check", "Handle")
    solveTask("IPriceManager")
    solveTask("array1", "array2", "//copy arrays item by item.")
    solveTask("directoryInput", "QualityRender")
    solveTask("H4Te_AUth0R")
    solveTask("ColorCell", "array", "m_brdSz", "n", "//Fill board with black and white colors", "a", "b")
    solveTask("Initialization", "Board")
    clickOnElement(selectorByTagAndText("p")("Следующий!"))
  }

  def solveLevelTree(): Unit = {
    solveTask("GetFactory", "FactoryCreator", "SetTimeout")
    solveTask("//Convert file")
    solveTask("//If employee deserves full benefits", "employee", "//Pay largeAmount")
    solveTask("//foreach", "//try", "//catch")
    solveTask("//Returns the Responder being tested.")
    solveTask(
      "//comparison of this and other object",
      "//compares concatenated names of this and others",
      "//end of CompareTo"
    )
    solveTask(
      "/*Changes (from 11-Oct-2011)",
      "///<summary>Make a decision about logging event.</summary>",
      "///<summary>Property to get and set the next filter</summary>"
    )
    solveTask(
      "///<summary>Обрабатывает столкновение героя с врагом</summary>",
      "CollisionHandler",
      "//If hero and enemy collided",
      "//нужно оповестить подписчиков",
    )
    solveTask(
      "Filename",
      "//Initialize map",
      "HeroLifesCount",
      "//============Select object to put in (x, y) cell;",
      "Length" filterBy "last",
    )
    clickOnElement(selectorByTagAndText("a")("К рейтингу!"))
  }
}
