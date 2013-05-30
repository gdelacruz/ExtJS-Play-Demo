package controllers;

import play.api.mvc._
import play.api.data.Forms._
import play.api.data._
import play.api.libs.json._
import models.Dato
import models.Dato._
import play.api.data.Mapping
import scala.collection.immutable.Map

object Datos extends Controller {

  def index = Action { Ok(views.html.index.render) }

  def crear = Action(parse.json) { request =>
    request.body.validate[Dato].map {
      case dato => {
        val newDato = Dato.save(dato)
         Ok(Json.obj("success" -> "true", "data" -> Json.toJson(newDato)))
      }
    }.recoverTotal {
      e => BadRequest(Json.obj("success" -> "false", "msg" -> JsError.toFlatJson(e)))
    }
    //BadRequest(Json.toJson(Map("success" -> "0", "msg" -> errors.toString))),

  }
  def listar = Action {
    Ok(Json.obj("success" -> "true", "data" -> Json.toJson(Dato.buscar)))
  }

  def actualizar = Action(parse.json) { request =>
  	println("pase actualizar")
    request.body.validate[Dato].map {
      case dato => {
        Dato.update(dato)
        Ok(Json.obj("success" -> "true", "data" -> Json.toJson(dato)))
      }
    }.recoverTotal {
      e => BadRequest(Json.obj("success" -> "false", "msg" -> JsError.toFlatJson(e)))
    }
    //BadRequest(Json.toJson(Map("success" -> "0", "msg" -> errors.toString))),
  }

  def eliminar = Action(parse.json) { request =>

    request.body.validate[Dato].map {
      case dato => {
        Dato.delete(dato)
        Ok(Json.obj("success" -> "true", "data" -> Json.toJson(dato)))
      }
    }.recoverTotal {
      e => BadRequest(Json.obj("success" -> "false", "msg" -> JsError.toFlatJson(e)))
    }
    //BadRequest(Json.toJson(Map("success" -> "0", "msg" -> errors.toString))),
  }
}
