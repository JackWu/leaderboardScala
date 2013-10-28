package dispatch
import net.liftweb.http.JsonResponse
import net.liftweb.json._
import net.liftweb.json.JsonDSL._
import scala.collection.immutable.Nil
import net.liftweb.http.OkResponse
import net.liftweb.common._
import net.liftweb.http._
import net.liftweb.mongodb.JObjectParser



case class AllowOptionResponse() extends LiftResponse with HeaderDefaults {
  var ownHeaders = headers ::: List("Access-Control-Allow-Origin" -> "*",
      "Access-Control-Allow-Methods" -> "POST, GET, OPTIONS",
      "Access-Control-Max-Age" -> "1728000",
      "Access-Control-Allow-Headers"-> "content-type,x-requested-with",
      "Connection" -> "Keep-Alive",
      "Access-Control-Allow-Credentials" -> "false",
      "Vary" -> "Accept-Encoding",
      "Keep-Alive" -> "timeout=2,max=100",
      "Content-Type" -> "text/plain "
      );

  def toResponse = InMemoryResponse(Array(), ownHeaders, cookies, 200)
}


class RequestTypeImproved(requestType: RequestType) {
    def options_? : Boolean = requestType.method == "OPTIONS"
}

trait RestHelper extends net.liftweb.http.rest.RestHelper {
    implicit def req2ReqImproved(requestType: RequestType): RequestTypeImproved = {
        new RequestTypeImproved(requestType)
    }

    protected object Options {
        def unapply(r: Req): Option[(List[String], Req)] =
            if (r.requestType.options_?) Some(r.path.partPath -> r) else None
    }
}


object BasicDispatch extends RestHelper {
  
  
   var jsonHeaders = JsonResponse.headers ::: List(
       "Access-Control-Allow-Origin" -> "*"
   );
  
  serve {
    
    case "leaderboard" :: command :: Nil  Options json=>
          {
            
             AllowOptionResponse()
          }
          
//    case "leaderboard" :: command :: Nil JsonPost json =>
//          {
//        	  command match
//        	  {
//        	    case "getState" =>
//        	      {
//		           var jr =  JsonResponse.apply(null,
//		               jsonHeaders,JsonResponse.cookies,
//		               200);
//		           jr
//        	      }
//        	      
//        	    case "sendState" => 
//        	      {
//		           var jr =  JsonResponse.apply(null,
//		               jsonHeaders,JsonResponse.cookies,
//		               200);
//		           jr
//        	      }
//        	      
//        	    case "sendState" => 
//        	      {
//		           var jr =  JsonResponse.apply(null,
//		               jsonHeaders,JsonResponse.cookies,
//		               200);
//		           jr
//        	      }
//        	    
//
//        	      
//		        case _ =>
//		          {
//		           var jr =  JsonResponse.apply(null,
//		               jsonHeaders,JsonResponse.cookies,
//		               200);
//		           jr
//		          }
//        	  }
//
//          }
 
  }
}
