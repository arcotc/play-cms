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

import java.net.{URLDecoder, URLEncoder}
import javax.inject._

import models._
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.Future

@Singleton
class PageController @Inject() extends Controller {
  def defaultPage(group: String): Action[AnyContent] = Action.async {
    val defaultPage = "home"

    val loadedGroup = stubbedGroup(group)
    val loadedPage = stubbedPage(defaultPage)
    val loadedNavigation = stubbedLinks(loadedPage)
    val loadedTemplate = stubbedTemplate(loadedGroup, loadedNavigation)

    Future.successful(
      Ok(
        Json.toJson(
          LoadedPage(loadedGroup, loadedTemplate, loadedPage, loadedNavigation)
        )
      )
    )
  }

  def page(group: String, page: String): Action[AnyContent] = Action.async {
    val loadedGroup = stubbedGroup(group)
    val loadedPage = stubbedPage(page)
    val loadedNavigation = stubbedLinks(loadedPage)
    val loadedTemplate = stubbedTemplate(loadedGroup, loadedNavigation)

    Future.successful(
      Ok(
        Json.toJson(
          LoadedPage(loadedGroup, loadedTemplate, loadedPage, loadedNavigation)
        )
      )
    )
  }

  // TODO: To be removed when data loaded from the database
  private def stubbedGroup(group: String) = {
    Group(
      group,
      group
    )
  }

  // TODO: To be removed when data loaded from the database
  private def stubbedTemplate(group: Group, links: List[Link]): Template = {
    val head = gelComponents("%3C!--%20normalizar%20--%3E%0A%3Clink%20href%3D%22%7BserverAssets%7D%2Fcss%2Fnormalize.css%22%20rel%3D%22stylesheet%22%3E%0A%0A%3C!--%20Fonts%20--%3E%0A%3Clink%20href%3D%27http%3A%2F%2Ffonts.googleapis.com%2Fcss%3Ffamily%3DMontserrat%3A400%2C700%27%20rel%3D%27stylesheet%27%20type%3D%27text%2Fcss%27%3E%0A%3Clink%20href%3D%27http%3A%2F%2Ffonts.googleapis.com%2Fcss%3Ffamily%3DCabin%3A400%2C500%2C700%27%20rel%3D%27stylesheet%27%20type%3D%27text%2Fcss%27%3E%0A%3Clink%20href%3D%27http%3A%2F%2Ffonts.googleapis.com%2Fcss%3Ffamily%3DLato%3A300%2C400%2C700%27%20rel%3D%27stylesheet%27%20type%3D%27text%2Fcss%27%3E%0A%0A%0A%3C!--%20Bootstrap%20--%3E%0A%3Clink%20href%3D%22%7BserverAssets%7D%2Fcss%2Fbootstrap.min.css%22%20rel%3D%22stylesheet%22%3E%0A%0A%3C!--fontawesome--%3E%0A%3Clink%20href%3D%22%7BserverAssets%7D%2Fcss%2Ffont-awesome.min.css%22%20rel%3D%22stylesheet%22%3E%0A%0A%3C!--animation--%3E%0A%3Clink%20href%3D%22%7BserverAssets%7D%2Fcss%2Fanimate.min.css%22%20rel%3D%22stylesheet%22%3E%0A%0A%3C!--main%20css--%3E%0A%3Clink%20href%3D%22%7BserverAssets%7D%2Fcss%2Fstyle.css%22%20rel%3D%22stylesheet%22%3E%0A%0A%3C!--main%20css--%3E%0A%3Clink%20href%3D%22%7BserverAssets%7D%2Fcss%2Fresponsive.css%22%20rel%3D%22stylesheet%22%3E%0A%0A%0A%3C!--%20HTML5%20shim%20and%20Respond.js%20for%20IE8%20support%20of%20HTML5%20elements%20and%20media%20queries%20--%3E%0A%3C!--%20WARNING%3A%20Respond.js%20doesn%27t%20work%20if%20you%20view%20the%20page%20via%20file%3A%2F%2F%20--%3E%0A%3C!--%5Bif%20lt%20IE%209%5D%3E%0A%3Cscript%20src%3D%22https%3A%2F%2Foss.maxcdn.com%2Fhtml5shiv%2F3.7.2%2Fhtml5shiv.min.js%22%3E%3C%2Fscript%3E%0A%3Cscript%20src%3D%22https%3A%2F%2Foss.maxcdn.com%2Frespond%2F1.4.2%2Frespond.min.js%22%3E%3C%2Fscript%3E%0A%3C!%5Bendif%5D--%3E", group, links)
    val header = gelComponents("%3Cdiv%20id%3D%22preloader%22%3E%0A%20%20%20%20%3Ci%20class%3D%22fa%20fa-spinner%20fa-pulse%20fa-3x%20fa-fw%20margin-bottom%22%3E%3C%2Fi%3E%0A%3C%2Fdiv%3E%0A%0A%0A%20%0A%3Cheader%20id%3D%22header_area%22%3E%0A%20%20%20%20%3Cdiv%20class%3D%22container%22%3E%0A%20%20%20%20%20%20%20%20%0A%3Cdiv%20class%3D%22row%22%3E%0A%20%20%20%20%3Cdiv%20class%3D%22col-sm-4%22%3E%0A%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22logo%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3Ch2%3E%3Ca%20href%3D%22%2F%22%3E%7BgroupTitle%7D%3C%2Fa%3E%3C%2Fh2%3E%0A%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%3Cdiv%20class%3D%22col-sm-8%22%3E%0A%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22mainmenu%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22navbar%20navbar-nobg%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22navbar-header%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cbutton%20type%3D%22button%22%20class%3D%22navbar-toggle%22%20data-toggle%3D%22collapse%22%20data-target%3D%22.navbar-collapse%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cspan%20class%3D%22sr-only%22%3EToggle%20navigation%3C%2Fspan%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cspan%20class%3D%22icon-bar%22%3E%3C%2Fspan%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cspan%20class%3D%22icon-bar%22%3E%3C%2Fspan%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cspan%20class%3D%22icon-bar%22%3E%3C%2Fspan%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fbutton%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%0A%3Cdiv%20class%3D%22navbar-collapse%20collapse%22%3E%0A%20%20%20%20%3Cul%20class%3D%22nav%20navbar-nav%20navbar-right%22%3E%0A%7Bforeach%20link%7D%0A%20%20%20%20%20%20%20%20%3Cli%3E%3Ca%20href%3D%22%7Blink.page%7D%22%3E%7Blink.text%7D%3C%2Fa%3E%26nbsp%3B%3C%2Fli%3E%0A%7B%2Fforeach%7D%0A%20%20%20%20%3C%2Ful%3E%0A%3C%2Fdiv%3E%0A%0A%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%3C%2Fdiv%3E%0A%3C%2Fdiv%3E%0A%0A%0A%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22row%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22col-md-12%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22header_area_text%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C!--h2%20class%3D%22wow%20slideInDown%22%20data-wow-duration%3D%222s%22%3EWe%E2%80%99re%20here%20to%20create%20your%20online%20presense%20and%20style%3C%2Fh2--%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C!--p%20class%3D%22wow%20slideInUp%22%3ELorem%20ipsum%20dolor%20sit%20amet%2C%20consectetur%20adipiscing%20elit.%20Suspendisse%20mattis%20orci%20dapibus%20risus%20dignissim%2C%20viverra%20pellentesque%20arcu%20ullamcorper.%20Mauris%20a%20tincidunt%20lectus.%20Proin%20nec%20venenatis%20quam.%20%3C%2Fp--%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Ca%20class%3D%22wow%20slideInUp%22%20data-wow-duration%3D%222s%22%20href%3D%22%2Fcontact%2Fform%22%3ESTART%20%20TODAY%20%20with%20%20us%3C%2Fa%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%3C%2Fdiv%3E%0A%3C%2Fheader%3E%0A%3C!--%20end%20header%20top%20area%20--%3E", group, links)
    val footer = gelComponents("%3Cdiv%20id%3D%22preloader%22%3E%0A%20%20%20%20%3Ci%20class%3D%22fa%20fa-spinner%20fa-pulse%20fa-3x%20fa-fw%20margin-bottom%22%3E%3C%2Fi%3E%0A%3C%2Fdiv%3E%0A%0A%0A%3Cfooter%20id%3D%22footer_area%22%3E%0A%20%20%20%20%3Cdiv%20class%3D%22container%22%3E%0A%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22row%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22col-sm-3%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22company_logo%20wow%20slideInDown%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Ch2%3E%7BgroupTitle%7D%3C%2Fh2%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22col-sm-3%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22company_address%20wow%20slideInDown%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Ch2%3EAbout%20Stewart%3C%2Fh2%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3EI%20am%20a%20fully%20qualified%20DSA%20registered%20driving%20instructor%20(car).%20%20I%20have%20been%20qualified%20since%202005%3B%20I%20am%20friendly%2C%20very%20patient%20and%20understanding%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3EAs%20well%20as%20offering%20tuition%20to%20total%20beginners%20I%20can%20also%20offer%20refresher%20training%20to%20those%20who%20have%20lost%20their%20confidence%20in%20today%E2%80%99s%20traffic.%20%20I%20also%20offer%20Motorway%20Training%20to%20the%20qualified%20driver.%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Ch3%3E%3Ca%20href%3D%22%2Fabout%22%3ERead%20more%20about%20Stewart%3C%2Fa%3E%3C%2Fh3%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22col-sm-3%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22company_address%20wow%20slideInDown%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Ch2%3EOffice%3C%2Fh2%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3E01673%20842936%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Ch2%3EMobile%3C%2Fh2%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3E07778%20456379%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Ch3%3E%3Ca%20href%3D%22%2Fcontact%2Fform%22%3Eor%20use%20our%20Contact%20Form%3C%2Fa%3E%3C%2Fh3%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22col-sm-3%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22company_address%20wow%20slideInDown%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Ch2%3EAreas%20we%20cover%3C%2Fh2%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3EMarket%20Rasen%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3EBinbrook%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3ETealby%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3EWalesby%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3ENth%20Willingham%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3ELudford%2C%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3ECaenby%20Corner%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3EHemswell%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3EGlentham%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3ELouth%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3Eall%20surrounding%20Villages%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%3C%2Fdiv%3E%0A%3C%2Ffooter%3E%0A%0A%3C!--%20main%20jQuery--%3E%0A%3Cscript%20src%3D%22%7BserverAssets%7D%2Fjs%2Fjquery-1.11.3.min.js%22%3E%3C%2Fscript%3E%0A%0A%3C!--%20bootstrap%20js%20--%3E%0A%3Cscript%20src%3D%22%7BserverAssets%7D%2Fjs%2Fbootstrap.min.js%22%3E%3C%2Fscript%3E%0A%0A%3C!--%20wow%20js%20--%3E%0A%3Cscript%20src%3D%22%7BserverAssets%7D%2Fjs%2Fwow.min.js%22%3E%3C%2Fscript%3E%0A%3Cscript%3E%0A%20%20%20%20%20%20%20%20new%20WOW().init()%3B%0A%20%20%20%20%3C%2Fscript%3E%0A%0A%3C!--%20main%20js%20--%3E%0A%3Cscript%20src%3D%22%7BserverAssets%7D%2Fjs%2Fmain.js%22%3E%3C%2Fscript%3E", group, links)
    Template(
      "templateHome",
      Some(head),
      Some(header),
      Some(footer)
    )
  }

  // TODO: To be removed when data loaded from the database
  private def stubbedPage(page: String) : Page = {
    Page(
      page,
      page,
      "%20%3Csection%20id%3D%22intro%22%20class%3D%22section_padding%20text-center%22%3E%0A%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22container%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22col-md-12%20text-center%20page_title%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Ch2%3EWelcome%20to%20Stewart%20Lancaster%20Driving%20School%3C%2Fh2%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22row%20text-center%22%20%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22col-md-8%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22container%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22row%20text-left%20pad-row%20%20%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22col-md-4%20col-sm-4%22%3E%0A%20%20%3Cp%3E%0A%20%20%20%20Hello%20and%20welcome%20to%20Stewart%20Lancaster%20Driving%20School%20Web%20site.%20%20I%20hope%20while%20you%20are%20reading%20through%20you%20find%20the%20information%20you%20are%20looking%20for%0A%20%20%3C%2Fp%3E%0A%20%20%3Cp%3E%0A%20%20%20%20Vouchers%20are%20available%20so%20why%20not%20think%20about%20buying%20a%20voucher%20for%20friends%20and%20family%20who%20are%20hard%20to%20buy%20for%2C%20look%20on%20the%20pricing%20tab%20and%20choose%20from%20there.%0A%20%20%3C%2Fp%3E%0A%20%20%3Cp%3E%0A%20%20%20%20The%20car%20you%20will%20be%20driving%20when%20you%20book%20any%20Driving%20lessons%20with%20me%20is%20a%201.5dci%20Renault%20Clio.%20%0A%20%20%3C%2Fp%3E%0A%20%20%3Cp%3E%0A%20%20%20%20To%20be%20able%20to%20drive%20this%20car%20you%20are%20required%20to%20be%2017%20years%20of%20age%20and%20in%20possession%20of%20a%20provisional%20driving%20licence.%0A%20%20%3C%2Fp%3E%0A%3C%2Fdiv%3E%0A%3Cdiv%20class%3D%22col-md-4%20col-sm-4%22%3E%0A%20%20%3Cp%3E%0A%20%20%20%20To%20apply%20for%20your%20licence%20follow%20the%20link%20in%20%27useful%20links%27%20and%20all%20is%20explained.%20%20To%20become%20a%20full%20British%20licence%20holder%20you%20need%20to%20pass%20the%20theory%20test%20(both%20the%20questions%20and%20hazard%20perception)%20and%20of%20course%20the%20practical%20Driving%20Test.%0A%20%20%3C%2Fp%3E%0A%20%20%3Cp%3E%0A%20%20%20%20Once%20you%20have%20passed%20the%20theory%20test%20you%20can%20then%20apply%20for%20a%20practical%20driving%20test%20(when%20you%20are%20able%20to%20drive%20to%20a%20high%20standard).%0A%20%20%3C%2Fp%3E%0A%20%20%3Cp%3E%0A%20%20%20%20Payments%20can%20also%20be%20made%20through%20Pay%20Pal%20for%20driving%20lessons%20see%20pricing%0A%20%20%3C%2Fp%3E%0A%3C%2Fdiv%3E%0A%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22col-md-4%20col-sm-4%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Ch4%3ENews%3C%2Fh4%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22col-sm-12%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22clients_say%20wow%20slideInUp%20animated%22%20style%3D%22visibility%3A%20visible%3B%20animation-name%3A%20slideInUp%3B%20-webkit-animation-name%3A%20slideInUp%3B%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Ch5%3ECGI%20makeover%20for%20driving%20theory%20test%3C%2Fh5%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20Blue%20Light%20Aware%20online%20road%20safety%20video%20launches%20today%20%3Cbr%2F%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Ch6%3E%3Ca%20href%3D%22%2Fblog%2Fcgi-makeover-for-driving-theory-test%22%3ERead%20more%20about%20CGI%20makeover%20for%20driving%20theory%20test%3C%2Fa%3E%3C%2Fh6%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22col-sm-12%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22clients_say%20wow%20slideInUp%20animated%22%20style%3D%22visibility%3A%20visible%3B%20animation-name%3A%20slideInUp%3B%20-webkit-animation-name%3A%20slideInUp%3B%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Ch5%3EDriving%20Licence%20Changes%3C%2Fh5%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20Updates%20and%20advice%20to%20motorists%20on%20abolition%20of%20the%20counterpart%20to%20the%20photocard%20driving%20l...%3Cbr%2F%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fp%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Ch6%3E%3Ca%20href%3D%22%2Fblog%2Fdriving-licence-changes%22%3ERead%20more%20about%20Driving%20Licence%20Changes%3C%2Fa%3E%3C%2Fh6%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22container%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22row%20alert%20alert-info%22%20%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22col-md-8%20col-sm-8%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cimg%20src%3D%22http%3A%2F%2Fapp.freedrivingtheoryapp.co.uk%2Fassets%2Fimg%2Finstructors%2Fsmartdriving-landscape.png%22%20style%3D%22height%3A%2095px%3B%20width%3A%20575px%3B%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Cdiv%20class%3D%22col-md-4%20col-sm-4%22%20style%3D%22padding-top%3A%2015px%3B%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Ca%20href%3D%22http%3A%2F%2Fapp.freedrivingtheoryapp.co.uk%2Finstructors%2Fstudent%3Fdriver%3D1571%26image%3Dlandscape%22%20target%3D%22_blank%22%20class%3D%22%20btn%20btn-primary%20btn-lg%22%3EGRAB%20IT%20HERE%20NOW%3C%2Fa%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%20%20%20%20%3C%2Fdiv%3E%0A%20%20%20%20%3C%2Fsection%3E"
    )
  }

  // TODO: To be removed when data loaded from the database
  private def stubbedLinks(page: Page): List[Link] = {
    List(
      Link("home", "Home", "Home page"),
      Link("about", "About", "About page"),
      Link("pricing", "Pricing", "Pricing page"),
      Link("learning", "Learning", "Learning page"),
      Link("contact", "Contact", "Contact page")
    )
  }

  private def gelComponents(encodedContent: String, group: Group, links: List[Link]): String = {
    val decodedContent = URLDecoder.decode(encodedContent, "UTF-8")
    val gelledContent = decodedContent.
      replaceAll("\\{serverAssets\\}", s"http://localhost:9001/assets/${group.name}").
      replaceAll("\\{groupTitle\\}", s"${group.title}")

    val updatedContent = gelledContent.contains("\\{foreach link\\}") match {
      case true => {
        gelLinks(gelledContent, links)
      }
      case false => gelledContent
    }

    URLEncoder.encode(updatedContent, "UTF-8")
  }

  private def gelLinks(decodedContent: String, links: List[Link]): String = {
    decodedContent
  }
}
