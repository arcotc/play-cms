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

import models.{Group, Link, Page, Template, LoadedPage}
import play.api.libs.json.{Json, Writes}

package object controllers {

  implicit val groupWrites = new Writes[Group] {
    def writes(group: Group) = Json.obj(
      "name" -> group.name
    )
  }

  implicit val templateWrites = new Writes[Template] {
    def writes(template: Template) = Json.obj(
      "name" -> template.name
    )
  }

  implicit val pageWrites = new Writes[Page] {
    def writes(page: Page) = Json.obj(
      "name" -> page.name,
      "content" -> page.content
    )
  }

  implicit val linkWrites = new Writes[Link] {
    def writes(link: Link) = Json.obj(
      "text" -> link.text,
      "page" -> link.page,
      "altText" -> link.altText
    )
  }

  implicit val loadedPageWrites = new Writes[LoadedPage] {
    def writes(loadedPage: LoadedPage) = Json.obj(
      "group" -> Json.toJson(loadedPage.group),
      "template" -> Json.toJson(loadedPage.template),
      "page" -> Json.toJson(loadedPage.page),
      "navigation" -> Json.toJson(loadedPage.links)
    )
  }
}
