package dispatch
import net.liftweb.common._
import net.liftweb.common.Box
import net.liftweb.json.JsonAST
import net.liftweb.json.JsonAST._
import net.liftweb.mongodb._
import net.liftweb.mongodb.BsonDSL._
import net.liftweb.json.JObject
import java.util.Date
import annotation._
import scala.reflect.BeanProperty
import net.liftweb.http.Req
import net.liftweb.http.provider.HTTPRequest

trait BasicService {
  var OKResponse: Map[String, JValue] = Map[String, JValue]("result" -> "OK")
  var ErrorResponse: Map[String, JValue] = Map[String, JValue]("result" -> "ERROR")
  
def handleService(cmd:String,jvalue: Box[JValue]):JObject = {
    
    //var method = this.getClass().getMethods().find(_.getName() == cmd)
    
    var method = this.getClass().getMethods().find(m => 
      m.isAnnotationPresent(classOf[ServiceAPI]) && m.getAnnotation(classOf[ServiceAPI]).name() == cmd
      )
    
	ErrorResponse =  ErrorResponse ++ Map(("time" -> JInt((new Date()).getTime())))
	OKResponse =  OKResponse ++ Map(("time" -> JInt((new Date()).getTime())))
	OKResponse =   OKResponse ++ Map(("msg" -> JString("")));
    
    
    if(!method.isEmpty){
		var jobj = jvalue.openTheBox
		var r = method.get.invoke(this,jobj)
		
		r.asInstanceOf[JObject]
		
    }
    else{
      
    	ErrorResponse ++ Map(("msg" -> JString(this.getClass().toString()+"Invalid command:" +cmd)))
    }
  }
  
def handleServiceWithReq(cmd:String,jvalue: Box[JValue],req:HTTPRequest):JObject = {
    
    //var method = this.getClass().getMethods().find(_.getName() == cmd)
    
    var method = this.getClass().getMethods().find(m => 
      m.isAnnotationPresent(classOf[ServiceAPI]) && m.getAnnotation(classOf[ServiceAPIWithReq]).name() == cmd
      )
    
	ErrorResponse =  ErrorResponse ++ Map(("time" -> JInt((new Date()).getTime())))
	OKResponse =  OKResponse ++ Map(("time" -> JInt((new Date()).getTime())))
	OKResponse =   OKResponse ++ Map(("msg" -> JString("")));
    
    if(!method.isEmpty){
		var jobj = jvalue.openTheBox
		var r = method.get.invoke(this,jobj,req)
		
		r.asInstanceOf[JObject]
		
    }
    else{
      
    	ErrorResponse ++ Map(("msg" -> JString(this.getClass().toString()+"Invalid command:" +cmd)))
    }
  }  
  

}