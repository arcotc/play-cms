// Copyright (C) 2011-2017 the original author or authors.
// See the LICENCE.txt file distributed with this work for additional
// information regarding copyright ownership.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package controllers

import javax.inject._

import models._
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.Future

@Singleton
class PageController @Inject() extends Controller {
  def defaultPage(group: String): Action[AnyContent] = Action.async {
    val defaultPage = "home"

    val loadedGroup = Group(group)
    val loadedTemplate = stubbedTemplate
    val loadedPage = stubbedPage(defaultPage)
    val loadedNavigation = List(Link(defaultPage, defaultPage, s"$defaultPage page"))

    Future.successful(
      Ok(
        Json.toJson(
          LoadedPage(loadedGroup, loadedTemplate, loadedPage, loadedNavigation)
        )
      )
    )
  }

  def page(group: String, page: String): Action[AnyContent] = Action.async {
    val loadedGroup = Group(group)
    val loadedTemplate = stubbedTemplate
    val loadedPage = stubbedPage(page)
    val loadedNavigation = List(Link(page, page, s"$page page"))

    Future.successful(
      Ok(
        Json.toJson(
          LoadedPage(loadedGroup, loadedTemplate, loadedPage, loadedNavigation)
        )
      )
    )
  }

  private def stubbedTemplate: Template = {
    Template(
      "templateHome",
      Some("%3C!--%20normalizar%20--%3E%0A%3Clink%20href%3D%22http%3A%2F%2Flocalhost%3A9001%2Fassets%2Fany%2Fcss%2Fnormalize.css%22%20rel%3D%22stylesheet%22%3E%0A%0A%3C!--%20Fonts%20--%3E%0A%3Clink%20href%3D%27http%3A%2F%2Ffonts.googleapis.com%2Fcss%3Ffamily%3DMontserrat%3A400%2C700%27%20rel%3D%27stylesheet%27%20type%3D%27text%2Fcss%27%3E%0A%3Clink%20href%3D%27http%3A%2F%2Ffonts.googleapis.com%2Fcss%3Ffamily%3DCabin%3A400%2C500%2C700%27%20rel%3D%27stylesheet%27%20type%3D%27text%2Fcss%27%3E%0A%3Clink%20href%3D%27http%3A%2F%2Ffonts.googleapis.com%2Fcss%3Ffamily%3DLato%3A300%2C400%2C700%27%20rel%3D%27stylesheet%27%20type%3D%27text%2Fcss%27%3E%0A%0A%0A%3C!--%20Bootstrap%20--%3E%0A%3Clink%20href%3D%22http%3A%2F%2Flocalhost%3A9001%2Fassets%2Fany%2Fcss%2Fbootstrap.min.css%22%20rel%3D%22stylesheet%22%3E%0A%0A%3C!--fontawesome--%3E%0A%3Clink%20href%3D%22http%3A%2F%2Flocalhost%3A9001%2Fassets%2Fany%2Fcss%2Ffont-awesome.min.css%22%20rel%3D%22stylesheet%22%3E%0A%0A%3C!--animation--%3E%0A%3Clink%20href%3D%22http%3A%2F%2Flocalhost%3A9001%2Fassets%2Fany%2Fcss%2Fanimate.min.css%22%20rel%3D%22stylesheet%22%3E%0A%0A%3C!--main%20css--%3E%0A%3Clink%20href%3D%22http%3A%2F%2Flocalhost%3A9001%2Fassets%2Fany%2Fcss%2Fstyle.css%22%20rel%3D%22stylesheet%22%3E%0A%0A%3C!--main%20css--%3E%0A%3Clink%20href%3D%22http%3A%2F%2Flocalhost%3A9001%2Fassets%2Fany%2Fcss%2Fresponsive.css%22%20rel%3D%22stylesheet%22%3E%0A%0A%0A%3C!--%20HTML5%20shim%20and%20Respond.js%20for%20IE8%20support%20of%20HTML5%20elements%20and%20media%20queries%20--%3E%0A%3C!--%20WARNING%3A%20Respond.js%20doesn%27t%20work%20if%20you%20view%20the%20page%20via%20file%3A%2F%2F%20--%3E%0A%3C!--%5Bif%20lt%20IE%209%5D%3E%0A%3Cscript%20src%3D%22https%3A%2F%2Foss.maxcdn.com%2Fhtml5shiv%2F3.7.2%2Fhtml5shiv.min.js%22%3E%3C%2Fscript%3E%0A%3Cscript%20src%3D%22https%3A%2F%2Foss.maxcdn.com%2Frespond%2F1.4.2%2Frespond.min.js%22%3E%3C%2Fscript%3E%0A%3C!%5Bendif%5D--%3E"),
      Some("%3Cdiv%20id%3D%22preloader%22%3E%0A%20%20%20%20%3Ci%20class%3D%22fa%20fa-spinner%20fa-pulse%20fa-3x%20fa-fw%20margin-bottom%22%3E%3C%2Fi%3E%0A%3C%2Fdiv%3E%0A%0A%0A%20%0A%3Cheader%20id%3D%22header_area%22%3E%0A%20%20%20%20%3Cdiv%20class%3D%22container%22%3E%0A%20%20%20%20%20%20%20%20%0A%3Cdiv%20class%3D%22row%22%3E%0A%20%20%20%20%3Cdiv%20class%3D%22col-sm-4%22%3E%0A%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22logo%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3Ch2%3E%3Ca%20href%3D%22%2F%22%3EStewart%20Lancaster%20Driving%20School%3C%2Fa%3E%3C%2Fh2%3E%0A%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%3Cdiv%20class%3D%22col-sm-8%22%3E%0A%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22mainmenu%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22navbar%20navbar-nobg%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22navbar-header%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cbutton%20type%3D%22button%22%20class%3D%22navbar-toggle%22%20data-toggle%3D%22collapse%22%20data-target%3D%22.navbar-collapse%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cspan%20class%3D%22sr-only%22%3EToggle%20navigation%3C%2Fspan%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cspan%20class%3D%22icon-bar%22%3E%3C%2Fspan%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cspan%20class%3D%22icon-bar%22%3E%3C%2Fspan%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cspan%20class%3D%22icon-bar%22%3E%3C%2Fspan%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fbutton%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%0A%3Cdiv%20class%3D%22navbar-collapse%20collapse%22%3E%0A%20%20%20%20%3Cul%20class%3D%22nav%20navbar-nav%20navbar-right%22%3E%0A%20%20%20%20%20%20%20%20%3Cli%3E%3Ca%20href%3D%22%2F%22%3EHome%3C%2Fa%3E%26nbsp%3B%3C%2Fli%3E%0A%20%20%20%20%20%20%20%20%0A%20%20%20%20%20%20%20%20%3Cli%3E%3Ca%20href%3D%27%2Fabout%27%3EABOUT%3C%2Fa%3E%26nbsp%3B%3C%2Fli%3E%0A%20%20%20%20%20%20%20%20%0A%20%20%20%20%20%20%20%20%3Cli%3E%3Ca%20href%3D%27%2Fpricing%27%3EPRICING%3C%2Fa%3E%26nbsp%3B%3C%2Fli%3E%0A%20%20%20%20%20%20%20%20%0A%20%20%20%20%20%20%20%20%3Cli%3E%3Ca%20href%3D%27%2Flearning%27%3ELEARNING%3C%2Fa%3E%26nbsp%3B%3C%2Fli%3E%0A%20%20%20%20%20%20%20%20%0A%20%20%20%20%20%20%20%20%3Cli%3E%3Ca%20href%3D%22%2Fcontact%2Fform%22%3EContact%3C%2Fa%3E%26nbsp%3B%3C%2Fli%3E%0A%20%20%20%20%3C%2Ful%3E%0A%3C%2Fdiv%3E%0A%0A%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%3C%2Fdiv%3E%0A%3C%2Fdiv%3E%0A%0A%0A%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22row%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22col-md-12%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22header_area_text%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C!--h2%20class%3D%22wow%20slideInDown%22%20data-wow-duration%3D%222s%22%3EWe%E2%80%99re%20here%20to%20create%20your%20online%20presense%20and%20style%3C%2Fh2--%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C!--p%20class%3D%22wow%20slideInUp%22%3ELorem%20ipsum%20dolor%20sit%20amet%2C%20consectetur%20adipiscing%20elit.%20Suspendisse%20mattis%20orci%20dapibus%20risus%20dignissim%2C%20viverra%20pellentesque%20arcu%20ullamcorper.%20Mauris%20a%20tincidunt%20lectus.%20Proin%20nec%20venenatis%20quam.%20%3C%2Fp--%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Ca%20class%3D%22wow%20slideInUp%22%20data-wow-duration%3D%222s%22%20href%3D%22%2Fcontact%2Fform%22%3ESTART%20%20TODAY%20%20with%20%20us%3C%2Fa%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%3C%2Fdiv%3E%0A%3C%2Fheader%3E%0A%3C!--%20end%20header%20top%20area%20--%3E"),
      Some("%3Cdiv%20id%3D%22preloader%22%3E%0A%20%20%20%20%3Ci%20class%3D%22fa%20fa-spinner%20fa-pulse%20fa-3x%20fa-fw%20margin-bottom%22%3E%3C%2Fi%3E%0A%3C%2Fdiv%3E%0A%0A%0A%3Cfooter%20id%3D%22footer_area%22%3E%0A%20%20%20%20%3Cdiv%20class%3D%22container%22%3E%0A%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22row%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22col-sm-3%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22company_logo%20wow%20slideInDown%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Ch2%3E%7B(json%20%5C%20%22site%22%20%5C%5C%20%22title%22).head.as%5BString%5D%7D%3C%2Fh2%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22col-sm-3%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22company_address%20wow%20slideInDown%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Ch2%3EAbout%20Stewart%3C%2Fh2%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3EI%20am%20a%20fully%20qualified%20DSA%20registered%20driving%20instructor%20(car).%20%20I%20have%20been%20qualified%20since%202005%3B%20I%20am%20friendly%2C%20very%20patient%20and%20understanding%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3EAs%20well%20as%20offering%20tuition%20to%20total%20beginners%20I%20can%20also%20offer%20refresher%20training%20to%20those%20who%20have%20lost%20their%20confidence%20in%20today%E2%80%99s%20traffic.%20%20I%20also%20offer%20Motorway%20Training%20to%20the%20qualified%20driver.%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Ch3%3E%3Ca%20href%3D%22%2Fabout%22%3ERead%20more%20about%20Stewart%3C%2Fa%3E%3C%2Fh3%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22col-sm-3%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22company_address%20wow%20slideInDown%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Ch2%3EOffice%3C%2Fh2%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3E01673%20842936%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Ch2%3EMobile%3C%2Fh2%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3E07778%20456379%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Ch3%3E%3Ca%20href%3D%22%2Fcontact%2Fform%22%3Eor%20use%20our%20Contact%20Form%3C%2Fa%3E%3C%2Fh3%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22col-sm-3%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22company_address%20wow%20slideInDown%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Ch2%3EAreas%20we%20cover%3C%2Fh2%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3EMarket%20Rasen%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3EBinbrook%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3ETealby%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3EWalesby%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3ENth%20Willingham%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3ELudford%2C%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3ECaenby%20Corner%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3EHemswell%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3EGlentham%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3ELouth%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3Eall%20surrounding%20Villages%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%3C%2Fdiv%3E%0A%3C%2Ffooter%3E%0A%0A%3C!--%20main%20jQuery--%3E%0A%3Cscript%20src%3D%22http%3A%2F%2Flocalhost%3A9001%2Fassets%2Fany%2Fjs%2Fjquery-1.11.3.min.js%22%3E%3C%2Fscript%3E%0A%0A%3C!--%20bootstrap%20js%20--%3E%0A%3Cscript%20src%3D%22http%3A%2F%2Flocalhost%3A9001%2Fassets%2Fany%2Fjs%2Fbootstrap.min.js%22%3E%3C%2Fscript%3E%0A%0A%3C!--%20wow%20js%20--%3E%0A%3Cscript%20src%3D%22http%3A%2F%2Flocalhost%3A9001%2Fassets%2Fany%2Fjs%2Fwow.min.js%22%3E%3C%2Fscript%3E%0A%3Cscript%3E%0A%20%20%20%20%20%20%20%20new%20WOW().init()%3B%0A%20%20%20%20%3C%2Fscript%3E%0A%0A%3C!--%20main%20js%20--%3E%0A%3Cscript%20src%3D%22http%3A%2F%2Flocalhost%3A9001%2Fassets%2Fany%2Fjs%2Fmain.js%22%3E%3C%2Fscript%3E")
    )
  }

  private def stubbedPage(page: String) : Page = {
    Page(
      page,
      "Some page content will go here"
    )
  }
}
