package models;

import play.api.libs.functional.syntax._
import play.api.libs.json.__
import play.api.data.validation.ValidationError
import play.api.libs.json.Reads._
import play.api.libs.json.Writes
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import play.api.libs.json.Reads
import scala.collection.mutable.ArrayBuffer

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
  
  val datos = ArrayBuffer[Option[Dato]](Some(Dato(1,"a","a","2001-01-01","a@a.com")))
  
  implicit val datoFMT = Json.format[Dato]
 
  def buscar: List[Dato] = datos.filter(p => !p.isEmpty).map(o => o.get).toList

  def save(dato: Dato) = {
    val d = Dato(datos.length+1,dato.nombre,dato.apellido,dato.fechaNac,dato.correo)
    datos += Some(d)
    println("SAVE "+d.id)
  }

  def update(dato: Dato) = {
    if(datos.toList.filterNot(p=>p.isEmpty).filter(p => p.get.nombre != dato.nombre).isEmpty) throw new RuntimeException("Nombre "+dato.nombre+" ya existe")
    datos((dato.id-1).toInt) = Some(dato)
    println("UPDATE "+dato.id)
  }

  def delete(dato: Dato) = {
    datos((dato.id-1).toInt) = None
    println("DELETE "+dato.id)
  }

}
	

