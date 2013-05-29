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
        Dato.save(dato)
        Ok(Json.toJson(Map("success" -> "1", "msg" -> "Los datos de guardaron exitosamente!")))
      }
    }.recoverTotal {
      e => BadRequest("Detected error:" + JsError.toFlatJson(e))
    }
    //BadRequest(Json.toJson(Map("success" -> "0", "msg" -> errors.toString))),

  }
  def listar = Action {
    Ok(Json.toJson(Dato.buscar))
  }

  def actualizar = Action(parse.json) { request =>

    request.body.validate[Dato].map {
      case dato => {
        Dato.update(dato)
        Ok(Json.toJson(Map("success" -> "1", "msg" -> "Los datos de modificaron exitosamente!")))
      }
    }.recoverTotal {
      e => BadRequest("Detected error:" + JsError.toFlatJson(e))
    }
    //BadRequest(Json.toJson(Map("success" -> "0", "msg" -> errors.toString))),
  }

  def eliminar = Action(parse.json) { request =>

    request.body.validate[Dato].map {
      case dato => {
        Dato.delete(dato)
        Ok(Json.toJson(Map("success" -> "1", "msg" -> "Los datos de eliminaron exitosamente!")))
      }
    }.recoverTotal {
      e => BadRequest("Detected error:" + JsError.toFlatJson(e))
    }
    //BadRequest(Json.toJson(Map("success" -> "0", "msg" -> errors.toString))),
  }
}
