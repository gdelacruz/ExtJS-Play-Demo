package models;

import play.api.libs.functional.syntax._
import play.api.libs.json.__
import play.api.data.validation.ValidationError
import play.api.libs.json.Reads._
import play.api.libs.json.Writes
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import play.api.libs.json.Reads

case class Dato(id: Long, nombre: String, apellido: String, fechaNac: String, correo: String)

/*
//http://play.lighthouseapp.com/projects/82401/tickets/932-new-json-api-and-companion-objects
object implicits{
  implicit val userReads:Reads[Dato] = (
    (__ \ "id").read[Long] ~
    (__ \ "nombre").read[String] ~
    (__ \ "apellido").read[String] ~
    (__ \ "fechaNac").read[String] ~
    (__ \ "correo").read(email))(Dato) // Si pongo (Dato.apply _) se puede poner en el companion object

  implicit val datoWrites: Writes[Dato] = (
    (__ \ "id").write[Long] ~
    (__ \ "nombre").write[String] ~
    (__ \ "apellido").write[String] ~
    (__ \ "fechaNac").write[String] ~
    (__ \ "correo").write[String])(unlift(Dato.unapply))  
  
}
*/

object Dato {
  
  implicit val datoFMT = Json.format[Dato]
 
  def buscar: List[Dato] = ???

  def save(dato: Dato) = ???

  def update(dato: Dato) = ???

  def delete(dato: Dato) = ???

}
	

